package com.congybk.response;

import com.google.gson.annotations.SerializedName;

/**
 * @Author YNC on 25/04/2017.
 */
public class FindBloodBody {
    @SerializedName("address")
    private String address;
    @SerializedName("blood_type")
    private String bloodType;
    @SerializedName("post_content")
    private String postContent;
    @SerializedName("post_name")
    private String postName;

    public FindBloodBody() {
    }

    public FindBloodBody(String address, String bloodType, String postContent, String postName) {
        this.address = address;
        this.bloodType = bloodType;
        this.postContent = postContent;
        this.postName = postName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
