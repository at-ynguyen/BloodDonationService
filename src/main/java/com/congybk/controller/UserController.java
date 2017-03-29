package com.congybk.controller;


import com.congybk.entity.Role;
import com.congybk.entity.User;
import com.congybk.security.JwtAuthenticationResponse;
import com.congybk.security.JwtTokenUtil;
import com.congybk.service.PermissionService;
import com.congybk.service.RoleService;
import com.congybk.service.UserService;
import com.congybk.utlis.UserUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author YNC
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter.User", SimpleBeanPropertyFilter
                        .serializeAllExcept("password"));
        mappingJacksonValue.setFilters(filters);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<MappingJacksonValue> getById(@PathVariable int userId) {
        User user = userService.findById(userId);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter.User", SimpleBeanPropertyFilter
                        .serializeAllExcept("password"));
        mappingJacksonValue.setFilters(filters);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    //TODO: Send welcome email
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String password = data.get("password");
        String fullName = data.get("full_name");
        String cardId = data.get("card_id");
        String roleIds = data.get("role_id");
        User userByEmail = userService.findByEmail(email);
        User userByCardId = userService.findByCardId(cardId);
        if (userByEmail != null || userByCardId != null) {
            return new ResponseEntity<>("Email has been registered already!", HttpStatus.CONFLICT);
        } else {
            String[] roleIdList = roleIds.split(",");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncoded = passwordEncoder.encode(password);
            User user = new User(new Integer(0), email, passwordEncoded, cardId, fullName);
            user.setCardId(user.getCardId());
            User newUser = userService.create(user);
            for (String roleId : roleIdList) {
                Role role = roleService.findById(Integer.parseInt(roleId.trim()));
                permissionService.create(newUser, role);
            }
            String token = this.jwtTokenUtil.generateToken(this.userDetailsService.loadUserByUsername(user.getCardId()));
            return new ResponseEntity<>(new JwtAuthenticationResponse(token), HttpStatus.CREATED);
        }
    }


    //TODO: Check object type received
    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        User updatedUser = userService.update(user);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(updatedUser);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter.User", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "name", "email"));
        mappingJacksonValue.setFilters(filters);
        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<?> delete(@PathVariable int userId) {
        userService.delete(userId);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/info")
    public ResponseEntity<?> getInformation() {
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter.User", SimpleBeanPropertyFilter
                        .serializeAllExcept("password"));
        mappingJacksonValue.setFilters(filters);
        return new ResponseEntity<Object>(mappingJacksonValue, HttpStatus.OK);
    }

    //TODO
    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/profile", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> update(@RequestPart("profile") String profile, @RequestPart(name = "avatar", required = false) MultipartFile avatar) throws IOException {
        String accountCode = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = new ObjectMapper().readValue(profile, User.class);
        user.setCardId(accountCode);
        User updatedUser = userService.update(user);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(updatedUser);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter.User", SimpleBeanPropertyFilter
                        .serializeAllExcept("password"));
        mappingJacksonValue.setFilters(filters);

        if (updatedUser != null) {
            return new ResponseEntity<Object>(mappingJacksonValue, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("Failed", HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/profile/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody Map<String, String> passwordMap) {
        String currentPassword = passwordMap.get("currentPassword");
        String newPassword = passwordMap.get("newPassword");
        String confirmPassword = passwordMap.get("confirmPassword");
        String accountCode = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByCardId(accountCode);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            if (newPassword != null && confirmPassword != null && newPassword.equals(confirmPassword)) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userService.updatePassword(user);
                return new ResponseEntity<Object>("Change password successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>("Confirmed password doesn't match new password", HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<Object>("Current password is not correct", HttpStatus.BAD_REQUEST);
        }
    }

}
