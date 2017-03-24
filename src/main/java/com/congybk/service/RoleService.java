package com.congybk.service;

import com.congybk.entity.Role;

import java.util.List;

/**
 * @author YNC
 */
public interface RoleService {
    public List<Role> getAll();

    public boolean deleteAll();

    public Role create(String roleName);

    public Role findByName(String roleName);

    public Role findById(int id);
}