package com.congybk.repository;

import com.congybk.entity.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YNC on 02/04/2017.
 */
@Repository
public interface TownRepository extends CrudRepository<Town, Integer> {
}
