package com.congybk.service;

import com.congybk.entity.Event;

import java.util.List;

/**
 * @Author YNC on 30/03/2017.
 */
public interface EventService {
    List<Event> getAll();

    void delete(int id);

    Event findById(int id);

    Event update(Event event);

    Event create(Event event);
}
