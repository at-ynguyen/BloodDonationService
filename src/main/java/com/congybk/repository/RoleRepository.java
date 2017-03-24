package com.congybk.repository;

import com.congybk.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YNC
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String roleName);
}
