package com.congybk.repository;

import com.congybk.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YNC on 30/03/2017.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
