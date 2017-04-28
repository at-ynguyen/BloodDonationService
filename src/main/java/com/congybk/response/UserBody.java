package com.congybk.response;

import com.congybk.entity.Town;

/**
 * @Author YNC on 26/04/2017.
 */
public class UserBody {
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
    private String cardId;
    private String fullName;
    private String birthDay;
    private String address;
    private Town town;
    private boolean gender;
    private float weight;
    private String bloodType;
    private String tokenPushNotification;

    public UserBody() {
    }

    public UserBody(int id, String email, String password, String phoneNumber, String cardId, String fullName, String birthDay, String address, Town town, boolean gender, float weight, String bloodType, String tokenPushNotification) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.cardId = cardId;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.address = address;
        this.town = town;
        this.gender = gender;
        this.weight = weight;
        this.bloodType = bloodType;
        this.tokenPushNotification = tokenPushNotification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getTokenPushNotification() {
        return tokenPushNotification;
    }

    public void setTokenPushNotification(String tokenPushNotification) {
        this.tokenPushNotification = tokenPushNotification;
    }
}
