package com.congybk.webapp.model;

/**
 * @Author YNC on 02/04/2017.
 */
public class FormEvent {
    private String name;
    private String a;
    private String ab;
    private String b;
    private String o;
    private String organization;
    private String time;
    private String address;
    private String town;
    private String content;
    private String option;

    public FormEvent(String name, String a, String ab, String b, String organization, String time, String address, String town, String option) {
        this.name = name;
        this.a = a;
        this.ab = ab;
        this.b = b;
        this.organization = organization;
        this.time = time;
        this.address = address;
        this.town = town;
        this.option = option;
    }

    public FormEvent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getOption() {
        return option;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
