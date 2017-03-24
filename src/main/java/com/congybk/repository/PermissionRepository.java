package com.congybk.repository;

import com.congybk.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ynguyen on 23/03/2017.
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, Integer> {
}
