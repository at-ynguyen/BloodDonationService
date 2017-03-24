package com.congybk.service;

import com.congybk.entity.Permission;
import com.congybk.entity.Role;
import com.congybk.entity.User;
import com.congybk.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author YNC
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission create(User user, Role role) {
        Permission permission = new Permission();
        permission.setRole(role);
        permission.setUser(user);
        return this.permissionRepository.save(permission);
    }
}
