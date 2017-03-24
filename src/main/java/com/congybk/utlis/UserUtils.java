package com.congybk.utlis;

import com.congybk.security.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by ynguyen on 23/03/2017.
 */
public class UserUtils {
    public static Long getIdByAuthorization(){
        return ((JwtUser) (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static String getAccountCodeByAuthorization(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
