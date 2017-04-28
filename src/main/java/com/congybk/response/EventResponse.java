package com.congybk.response;

import com.congybk.entity.Event;

/**
 * @Author YNC on 20/04/2017.
 */
public class EventResponse {
    private int members;
    private Event event;

    public EventResponse(int members, Event event) {
        this.members = members;
        this.event = event;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
