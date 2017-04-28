package com.congybk.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author YNC on 19/04/2017.
 */
@Entity
@Table(name = "find_blood")
public class FindBlood {
    private int id;
    private User user;
    private String postName;
    private String postContent;
    private String bloodType;
    private String address;
    private String image;
    private boolean approved;
    private Date createAt;

    public FindBlood() {
    }

    public FindBlood(int id, User user, String postName, String postContent, String bloodType, String address, String image, boolean approved, Date createAt) {
        this.id = id;
        this.user = user;
        this.postName = postName;
        this.postContent = postContent;
        this.bloodType = bloodType;
        this.address = address;
        this.image = image;
        this.approved = approved;
        this.createAt = createAt;
    }


    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "post_name")
    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Column(name = "post_content")
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Column(name = "blood_type")
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "approved")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
