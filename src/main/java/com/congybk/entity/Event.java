package com.congybk.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author YNC on 27/03/2017.
 */
@Entity
@Table(name="event")
public class Event {
    private int id;
    private User user;
    private String eventName;
    private Organization organization;
    private String bloodType;
    private Date time;
    private String content;
    private String address;
    private boolean status;

    public Event(int id, User user, String eventName, Organization organization, String bloodType, Date time, String content, String address, boolean status) {
        this.id = id;
        this.user = user;
        this.eventName = eventName;
        this.organization = organization;
        this.bloodType = bloodType;
        this.time = time;
        this.content = content;
        this.address = address;
        this.status = status;
    }

    public Event() {
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
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Column(name="name_event")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    @ManyToOne
    @JoinColumn(name = "organization__id", nullable = false)
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    @Column(name="blood_type")
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    @Column(name="time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    @Column(name="content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name="status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
