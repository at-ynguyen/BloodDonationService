package com.congybk.service;

import com.congybk.entity.Event;
import com.congybk.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YNC on 30/03/2017.
 */
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository mEventRepository;

    @Override
    public List<Event> getAll() {
        return (List<Event>) mEventRepository.findAll();
    }

    @Override
    public void delete(int id) {
        mEventRepository.delete(id);
    }

    @Override
    public Event findById(int id) {
        return mEventRepository.findOne(id);
    }

    @Override
    public Event update(Event event) {
        return mEventRepository.save(event);
    }

    @Override
    public Event create(Event event) {
        return mEventRepository.save(event);
    }
}
