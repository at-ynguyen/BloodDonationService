package com.congybk.response;

import com.congybk.entity.User;

/**
 * @Author YNC on 18/04/2017.
 */
public class TopHistory {
    private int number;
    private User user;

    public TopHistory(int number, User user) {
        this.number = number;
        this.user = user;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
