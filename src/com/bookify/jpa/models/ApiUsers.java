/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;

/**
 * For users that are using the API.
 */

    @Entity
    @Table(name = "apiusers")
    @JsonPropertyOrder({"apiUsersId","apiUsersName","apiUsersPassword","apiUsersSalt"})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="apiUsersId")
    @JsonFormat()
    @SessionScoped
    public class ApiUsers implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "apiUsersId")
        private  Integer apiUserId;

        @Column(name = "apiUsersName")
        private String apiUserName;

        @Column(name = "apiUsersPassword")
        private String apiUserPassword;

        @Column(name = "apiUsersSalt")
        private String apiUserSalt;

        public ApiUsers(String apiUserName, String apiUserPassword, String apiUserSalt) {
            this.apiUserName = apiUserName;
            this.apiUserPassword = apiUserPassword;
            this.apiUserSalt = apiUserSalt;
        }

        public ApiUsers(){}

        public Integer getApiUserId() {
            return apiUserId;
        }

        public void setApiUserId(Integer apiUserId) {
            this.apiUserId = apiUserId;
        }

        public String getApiUserName() {
            return apiUserName;
        }

        public void setApiUserName(String apiUserName) {
            this.apiUserName = apiUserName;
        }

        public String getApiUserPassword() {
            return apiUserPassword;
        }

        public void setApiUserPassword(String apiUserPassword) {
            this.apiUserPassword = apiUserPassword;
        }

        public String getApiUserSalt() {
            return apiUserSalt;
        }

        public void setApiUserSalt(String apiUserSalt) {
            this.apiUserSalt = apiUserSalt;
        }
    }
