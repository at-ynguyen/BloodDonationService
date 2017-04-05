package com.congybk.repository;

import com.congybk.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YNC on 01/04/2017.
 */
@Repository
public interface OrganizatioinRepository extends CrudRepository<Organization,Integer> {
}
