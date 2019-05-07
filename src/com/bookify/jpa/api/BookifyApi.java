package com.bookify.jpa.api;

import com.bookify.jpa.models.User;
import com.bookify.jpa.repositrories.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1.0")
@ApplicationPath("/api")
public class BookifyApi extends Application {

    @Inject
    private UserRepository ur;

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserName(){
        return ur.getAllUsers();
    }

}
