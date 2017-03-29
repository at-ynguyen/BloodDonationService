package com.congybk.service;

import com.congybk.entity.Role;

import java.util.List;

/**
 * @author YNC
 */
public interface RoleService {
    List<Role> getAll();

    boolean deleteAll();

    Role create(String roleName);

    Role findByName(String roleName);

    Role findById(int id);
}