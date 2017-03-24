package com.congybk.security.controller;


import com.congybk.entity.User;
import com.congybk.security.JwtAuthenticationRequest;
import com.congybk.security.JwtAuthenticationResponse;
import com.congybk.security.JwtTokenUtil;
import com.congybk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * @author YNC
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationRestController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(
            @Valid @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        System.out.println(authenticationRequest.getEmail());
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (user == null || !passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("Account not found!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        final String token = this.jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<Object>(new JwtAuthenticationResponse(token), HttpStatus.OK);
    }
}
