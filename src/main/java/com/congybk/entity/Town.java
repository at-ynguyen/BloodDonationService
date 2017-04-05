package com.congybk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author YNC on 02/04/2017.
 */
@Entity
@Table(name = "town")
public class Town {
    private int id;
    private String name;
    private Set<Event> event;
    private Set<User> user;

    public Town(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Town() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "town")
    @JsonIgnore
    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }

    @OneToMany(mappedBy = "town")
    @JsonIgnore
    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
