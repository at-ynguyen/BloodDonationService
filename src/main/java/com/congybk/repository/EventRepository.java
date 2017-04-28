package com.congybk.repository;

import com.congybk.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author YNC on 30/03/2017.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    @Query(value = "SELECT * FROM event ORDER BY create_at DESC LIMIT :start,:ends  ", nativeQuery = true)
    List<Event> getListEvent(@Param("start") int start, @Param("ends") int end);

}
