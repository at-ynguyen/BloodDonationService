package com.congybk.response;

/**
 * @Author YNC on 26/04/2017.
 */
public class ObjectResponse {
    private int code;
    private String message;

    public ObjectResponse() {
    }

    public ObjectResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
