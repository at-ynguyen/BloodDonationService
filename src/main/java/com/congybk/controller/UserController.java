package com.congybk.controller;


import com.congybk.entity.Role;
import com.congybk.entity.User;
import com.congybk.response.ObjectResponse;
import com.congybk.response.UserBody;
import com.congybk.security.JwtAuthenticationResponse;
import com.congybk.security.JwtTokenUtil;
import com.congybk.service.PermissionService;
import com.congybk.service.RoleService;
import com.congybk.service.UserService;
import com.congybk.utlis.UserUtils;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        Boolean gender = Boolean.valueOf(data.get("gender"));
        User userByEmail = userService.findByEmail(email);
        User userByCardId = userService.findByCardId(cardId);
        if (userByEmail != null || userByCardId != null) {
            return new ResponseEntity<>("Email has been registered already!", HttpStatus.CONFLICT);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncoded = passwordEncoder.encode(password);
            User user = new User(new Integer(0), email, passwordEncoded, cardId, fullName, gender);
            user.setCardId(user.getCardId());
            User newUser = userService.create(user);
            Role role = roleService.findById(2);
            permissionService.create(newUser, role);
            String token = this.jwtTokenUtil.generateToken(this.userDetailsService.loadUserByUsername(user.getEmail()));
            return new ResponseEntity<>(new JwtAuthenticationResponse(token), HttpStatus.CREATED);
        }
    }


    //TODO: Check object type received
    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@Valid @RequestBody UserBody user) {
        User userCurrent = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        userCurrent.setAddress(user.getAddress());
        userCurrent.setFullName(user.getFullName());
        userCurrent.setPhoneNumber(user.getPhoneNumber());
        userCurrent.setTown(user.getTown());
        userCurrent.setWeight(user.getWeight());
        userCurrent.setBloodType(user.getBloodType());
        userCurrent.setGender(user.isGender());
        System.out.println(user.getBirthDay() + "");
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = dt.parse(user.getBirthDay());


        } catch (ParseException e) {
            e.printStackTrace();
        }
        userCurrent.setBirthDay(date);
        User updatedUser = userService.update(userCurrent);
        return new ResponseEntity<Object>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/update/notification/{token}")
    public ResponseEntity<?> updateNotification(@PathVariable String token) {
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        if (token != null || !token.equals("")) {
            System.out.print(token + "//////////////////////////////////");
            user.setTokenPushNotification(token);
            userService.update(user);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
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


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/profile/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody Map<String, String> passwordMap) {
        String currentPassword = passwordMap.get("current_password");
        String newPassword = passwordMap.get("new_password");
        String confirmPassword = passwordMap.get("confirm_password");
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            if (newPassword != null && confirmPassword != null && newPassword.equals(confirmPassword)) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userService.update(user);
                return new ResponseEntity<Object>(new ObjectResponse(200, "Thay đổi thành công."), HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(new ObjectResponse(200, "Mật khẩu xác thực không đúng."), HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<Object>(new ObjectResponse(200, "Mật khẩu khẩu cũ không đúng."), HttpStatus.OK);
        }
    }

}
