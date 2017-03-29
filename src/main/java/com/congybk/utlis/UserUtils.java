package com.congybk.utlis;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by ynguyen on 23/03/2017.
 */
public class UserUtils {
    public static String getAccountCodeByAuthorization() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
