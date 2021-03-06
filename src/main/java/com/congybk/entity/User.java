package com.congybk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author YNC
 */
@Entity
@Table(name = "user")
public class User {
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
    private String cardId;
    private String fullName;
    private Date birthDay;
    private String address;
    private Town town;
    private boolean gender;
    private float weight;
    private String bloodType;
    private String tokenPushNotification;
    private List<Permission> permissionList = new ArrayList<>();
    private Set<Event> event;
    private Set<History> history;
    private Set<EventMember> eventMember;

    public User() {
        super();
    }

    public User(int id, String email, String password, String cardId, String fullName, Boolean gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.cardId = cardId;
        this.fullName = fullName;
        this.gender = gender;
    }

    public User(String cardId, String fullName, Town town, Date birthDay, String bloodType) {
        this.cardId = cardId;
        this.town = town;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.bloodType = bloodType;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", nullable = false)
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @JsonIgnore
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "card_id", nullable = false)
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "birthday", nullable = false)
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Column(name = "gender", nullable = false)
    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Column(name = "weight", nullable = false)
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Column(name = "blood_type", nullable = false)
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @JsonIgnore
    @Column(name = "token_push_notification", nullable = false)
    public String getTokenPushNotification() {
        return tokenPushNotification;
    }

    public void setTokenPushNotification(String tokenPushNotification) {
        this.tokenPushNotification = tokenPushNotification;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public Set<History> getHistory() {
        return history;
    }

    public void setHistory(Set<History> history) {
        this.history = history;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public Set<EventMember> getEventMember() {
        return eventMember;
    }

    public void setEventMember(Set<EventMember> eventMember) {
        this.eventMember = eventMember;
    }
}
