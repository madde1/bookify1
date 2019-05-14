package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@JsonPropertyOrder({"friendsId", "friendsUId1", "friendsUId2"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friendsId")
    private int id;

    @Column(name = "friendsUId1")
    private int friend1Id;

    @Column(name = "friendsUId2")
    private int getFriend2Id;

    public Friend() {
    }

    public Friend(int friend1Id, int getFriend2Id) {
        this.friend1Id = friend1Id;
        this.getFriend2Id = getFriend2Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriend1Id() {
        return friend1Id;
    }

    public void setFriend1Id(int friend1Id) {
        this.friend1Id = friend1Id;
    }

    public int getGetFriend2Id() {
        return getFriend2Id;
    }

    public void setGetFriend2Id(int getFriend2Id) {
        this.getFriend2Id = getFriend2Id;
    }
}
