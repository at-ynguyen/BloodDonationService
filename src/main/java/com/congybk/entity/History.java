package com.congybk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author YNC on 03/04/2017.
 */
@Entity
@Table(name = "history")
public class History {
    private int id;
    private User user;
    private Date time;
    private String note;

    public History(int id, User user, Date time, String note) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.note = note;
    }

    public History() {
    }

    @Id
    @GeneratedValue
    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    @Column(name = "note")
    public void setNote(String note) {
        this.note = note;
    }
}
