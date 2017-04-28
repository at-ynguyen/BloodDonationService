package com.congybk.entity;

import javax.persistence.*;

/**
 * @Author YNC on 05/04/2017.
 */
@Entity
@Table(name = "member_event")
public class EventMember {
    private int id;
    private Event event;
    private User user;
    private boolean status;
    private String note;

    public EventMember(int id, Event event, User user, boolean status, String note) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.status = status;
        this.note = note;
    }

    public EventMember(Event event, User user, boolean status, String note) {
        this.event = event;
        this.user = user;
        this.status = status;
        this.note = note;
    }

    public EventMember() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
