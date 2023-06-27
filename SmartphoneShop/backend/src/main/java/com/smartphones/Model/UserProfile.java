package com.smartphones.Model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.Date;

@Entity
@Table(name="userprofile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;
    private String location;
    private Date birthday;
    private String gender;
    private String maritalStatus;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserProfile(){}
    public UserProfile(Long id, String bio, String location, Date birthday, String gender, String maritalStatus, User user) {
        this.id = id;
        this.bio = bio;
        this.location = location;
        this.birthday = birthday;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
