package com.congybk.webapp.model;

/**
 * @Author YNC on 27/04/2017.
 */
public class FormAddUser {
    private String id;
    private String fullName;
    private String cardId;
    private String birthDay;
    private String bloodType;

    public FormAddUser() {
    }

    public FormAddUser(String fullName, String cardId, String birthDay, String bloodType) {
        this.fullName = fullName;
        this.cardId = cardId;
        this.birthDay = birthDay;
        this.bloodType = bloodType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
