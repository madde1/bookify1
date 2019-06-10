/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friends")
@JsonPropertyOrder({"friendsId", "friendsUId1", "friendsUId2"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class Friend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friendsId")
    private Integer id;

    @Column(name = "friendsUId1")
    private Integer friend1Id;

    @Column(name = "friendsUId2")
    private Integer friend2Id;

    public Friend() {
    }

    public Friend(Integer friend1Id, Integer friend2Id) {
        this.friend1Id = friend1Id;
        this.friend2Id = friend2Id;
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
        return friend2Id;
    }

    public void setGetFriend2Id(int friend2Id) {
        this.friend2Id = friend2Id;
    }
}
