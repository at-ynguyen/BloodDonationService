package com.congybk.webapp.model;

/**
 * @Author YNC on 03/04/2017.
 */
public class FormHistory {
    private int id;
    private String time;
    private String note;

    public FormHistory(int id, String time, String note) {
        this.id = id;
        this.time = time;
        this.note = note;
    }

    public FormHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
