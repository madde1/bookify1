package com.bookify.jpa.api;

import com.bookify.jpa.models.Book;
import com.bookify.jpa.models.Review;
import com.bookify.jpa.models.User;
import com.bookify.jpa.repositrories.ReviewRepository;
import com.bookify.jpa.repositrories.UserRepository;
import com.bookify.jpa.repositrories.bookRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;


@Path("/v1.0")
@ApplicationPath("/api")
public class BookifyApi extends Application {

    @Inject
    private UserRepository ur;

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserName(){
        List<User> u = ur.getAllUsers();
        return ur.getAllUsers();
    }

    //get user by id (for admin purposes)
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) { return ur.findById(id);
    }

    //get user by username
    @GET
    @Path("/users/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUserName(@PathParam("userName") String userName) { return ur.findByUserName(userName);
    }

    @GET
    @Path("/users/{id}/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendsByUserId(@PathParam("id") int id) {
        User u = ur.findById(id);
        return Response.ok(u.getFriends()).build();
    }

    //Post metod för att lägga till användare.
    @POST
    @Path("/users")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(User user){
        ur.create(user);
        return Response.ok(user.getUserName() + " added as user").build();

    }

    @POST
    @Path("/users/{id}/addfriend/{friendId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addFriend(@PathParam("id") int id, @PathParam("friendId") int friendId){
        User user = ur.findById(id);
        User friend = ur.findById(friendId);
        if(user.isFriendWith(friend)) {
            return Response.status(400).build();
        } else {
            user.addFriend(friend);
            return Response.status(201).build();
        }
    }

    @DELETE
    @Path("/users/{id}/removefriend/{friendId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeFriend(@PathParam("id") int id, @PathParam("friendId") int friendId){
        User user = ur.findById(id);
        User friend = ur.findById(friendId);
        if(user.isFriendWith(friend)) {
            user.removeFriend(friend);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @Inject
    private bookRepository br;

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBookTitel(){
        return br.getAllBooks();
    }

    @POST
    @Path("/books")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public  Response postBook(Book book){
        br.create(book);
        return Response.ok(book.getBookTitel() + " " + book.getBookAuthor() + " book added to the database").build();
    }

    @GET
    @Path ("/books/{bookTitle}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByTitle(@PathParam("bookTitle") String bookTitle) { return br.findByBookTitle(bookTitle);
    }

    @GET
    @Path ("/books/{bookAuthor}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByAuthor(@PathParam("bookAuthor") String bookAuthor) { return br.findByBookAuthor(bookAuthor);
    }



    /**Recensions delen av API:et med CRUD metoder*/
    @Inject
    private ReviewRepository rr;

    @POST
    @Path("/review")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postReview(Review review){
        rr.create(review);
        return Response.ok("En recension skriven för boken med id: " +review.getReviewbookId()).build();
    }

    @GET
    @Path("/review")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReview(){
        return rr.getAllReviews();
    }

    //Get review by bookTitle
    @GET
    @Path("/review/{bookTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviewForBook(@PathParam("bookTitle") String bookTitle) {return rr.viewReviewForBook(bookTitle);}




}

