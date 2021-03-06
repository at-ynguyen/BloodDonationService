package com.congybk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author YNC on 27/03/2017.
 */
@Entity
@Table(name = "organization")
public class Organization {
    private int id;
    private String name;
    private Set<Event> event;

    public Organization(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Organization() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }
}
