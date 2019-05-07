package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "users")
@JsonPropertyOrder({"userId","userName","userEmail","userPassword"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class User {
//hej
    @Id
    @GeneratedValue
    @Column(name = "usersId")
    private  Integer userId;

    @Column(name = "usersName")
    private String userName;
//hej
    @Column(name = "usersEmail")
    private String userEmail;

    @Column(name = "usersPassword")
    private String userPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
