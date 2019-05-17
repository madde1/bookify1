package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonPropertyOrder({"userId","userName","userEmail","userPassword"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
@SessionScoped
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "usersId")
    private  Integer userId;

    @Column(name = "usersName")
    private String userName;

    @Column(name = "usersEmail")
    private String userEmail;

    @Column(name = "usersPassword")
    private String userPassword;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "wanttoread",
            joinColumns = @JoinColumn(name = "wantToReadUserId"),
            inverseJoinColumns = @JoinColumn(name = "wantToReadBookId"))
    private Set<Book> booksToRead = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "haveread",
                joinColumns = @JoinColumn(name = "havereadUserId"),
                inverseJoinColumns = @JoinColumn(name = "havereadBookId"))
    private Set<Book> booksHaveRead = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friendsUId1"),
            inverseJoinColumns = @JoinColumn(name = "friendsUId2"))
    private Set<User> friends = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friendsUId2"),
            inverseJoinColumns = @JoinColumn(name = "friendsUId1"))
    private Set<User> friendOf = new HashSet<>();

    public User(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User(){}


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

    public Set<Book> getBooksToRead() { return booksToRead; }

    public Set<Book> getBooksHaveRead() {
        return booksHaveRead;
    }

    public Set<User> getFriends() {
        friends.addAll(friendOf);
        return friends;
    }

    public void removeFriends() {
        this.friends = new HashSet<>();
        this.friendOf = new HashSet<>();
    }
}
