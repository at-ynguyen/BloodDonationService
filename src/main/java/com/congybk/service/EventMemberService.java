package com.congybk.service;

import com.congybk.entity.Event;
import com.congybk.entity.EventMember;

import java.util.List;

/**
 * @Author YNC on 05/04/2017.
 */
public interface EventMemberService {
    List<EventMember> getAll();

    void delete(int id);

    EventMember findById(int id);

    EventMember update(EventMember History);

    EventMember create(EventMember History);

    List<EventMember> getListMemberByEvent(Event event);

    List<EventMember> getListMemberPost(int start, int end);

    List<EventMember> findByEventAndUser(int event, int user);
}
