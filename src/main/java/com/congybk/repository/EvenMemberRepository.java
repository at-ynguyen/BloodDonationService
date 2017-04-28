package com.congybk.repository;

import com.congybk.entity.Event;
import com.congybk.entity.EventMember;
import com.congybk.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author YNC on 05/04/2017.
 */
@Repository
public interface EvenMemberRepository extends CrudRepository<EventMember, Integer> {
    List<EventMember> getListMemberByEvent(Event event);

    @Query(value = "SELECT * FROM member_event WHERE event_id=:event AND user_id=:user", nativeQuery = true)
    List<EventMember> findByEventAndUser(@Param("event") int start, @Param("user") int end);
}
