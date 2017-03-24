package com.congybk.security.service;


import com.congybk.entity.User;
import com.congybk.security.JwtUser;
import com.congybk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return new JwtUser(
                    (long) user.getId(),
                    user.getEmail(),
                    user.getFullName(),
                    this.mapToGrantedAuthorities(user.getId()));
        }
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(int userId) {
        return this.userService.getAllRoleByUserId(userId)
                .stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());
    }
}
