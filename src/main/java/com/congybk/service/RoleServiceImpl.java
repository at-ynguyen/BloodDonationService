package com.congybk.service;

import com.congybk.entity.Role;
import com.congybk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YNC
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return (List<Role>) this.roleRepository.findAll();
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public Role create(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return this.roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    @Override
    public Role findById(int id) {
        return this.roleRepository.findOne(id);
    }
}
