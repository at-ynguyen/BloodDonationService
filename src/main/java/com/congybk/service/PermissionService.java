package com.congybk.service;

import com.congybk.entity.Permission;
import com.congybk.entity.Role;
import com.congybk.entity.User;

/**
 * @author YNC
 */
public interface PermissionService {
    Permission create(User user, Role role);
}