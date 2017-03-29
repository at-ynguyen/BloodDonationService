package com.congybk.webapp.model;

/**
 * @Author YNC on 29/03/2017.
 */
public class FromLogin {
    private String email;
    private String password;

    public FromLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public FromLogin() {
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
}
