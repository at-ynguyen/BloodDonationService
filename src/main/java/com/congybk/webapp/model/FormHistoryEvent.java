package com.congybk.webapp.model;

/**
 * @Author YNC on 28/04/2017.
 */
public class FormHistoryEvent {
    private int id;
    private int eventId;
    private String time;
    private String bloodType;
    private String note;

    public FormHistoryEvent() {
    }

    public FormHistoryEvent(int id, int eventId, String time, String bloodType, String note) {
        this.id = id;
        this.eventId = eventId;
        this.time = time;
        this.bloodType = bloodType;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
