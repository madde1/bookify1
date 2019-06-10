/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/

package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.*;


import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A user model. Also saves books that the user have read and wants to read. And friends of the user.
 */

@Entity
@Table(name = "users")
@JsonPropertyOrder({"userId","userName","userEmail","userPassword"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
@JsonFormat()
@SessionScoped
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usersId")
    private  Integer userId;

    @Column(name = "usersName")
    private String userName;

    @Column(name = "usersEmail")
    private String userEmail;

    @Column(name = "usersPassword")
    private String userPassword;

    @Column(name = "usersSalt")
    private String userSalt;

    @Column(name = "usersIsAdmin")
    private String userIsAdmin;

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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friendsUId1"),
            inverseJoinColumns = @JoinColumn(name = "friendsUId2"))
    private Set<User> friends = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
        friendOf.remove(friend);
    }

    public boolean isFriendWith(User user) {
        return getFriends().contains(user);
    }

    public void addWantToRead(Book book) {
        booksToRead.add(book);
    }

    public void removeWantToRead(Book book) {
        booksToRead.remove(book);
    }

    public void addHaveRead(Book book) {
        booksHaveRead.add(book);
    }

    public void removeHaveRead(Book book) {
        booksHaveRead.remove(book);
    }

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

    public String getUserSalt() { return userSalt; }

    public void setUserSalt(String userSalt) { this.userSalt = userSalt; }

    public String getUserIsAdmin() { return userIsAdmin;  }

    public void setUserIsAdmin(String userIsAdmin) { this.userIsAdmin = userIsAdmin; }

    public Set<User> getFriends() {
        friends.addAll(friendOf);
        return friends;
    }

    public Set<Book> getFavourites() {
        Set<Book> favourites = getBooksHaveRead();
        return favourites;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Set<User> friendOf) {
        this.friendOf = friendOf;
    }

}
