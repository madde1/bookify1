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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Path("/v1.0")
@ApplicationPath("/api")
public class BookifyApi extends Application {

    @Inject
    private UserRepository ur;

    @Inject
    private bookRepository br;

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

    @GET
    @Path("/users/{id}/wanttoread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWantToReadByUserId(@PathParam("id") int id) {
        User u = ur.findById(id);
        return Response.ok(u.getBooksToRead()).build();
    }

    @GET
    @Path("/users/{id}/haveread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHaveReadByUserId(@PathParam("id") int id) {
        User u = ur.findById(id);
        return Response.ok(u.getBooksHaveRead()).build();
    }

    @GET
    @Path("/users/{id}/favourites")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavouritesByUserId(@PathParam("id") int userId) {
        User u = ur.findById(userId);
        Set<Book> haveReads = u.getBooksHaveRead();
        List<Book> favourites = new ArrayList<>();
        for(Book b : haveReads) {
            if(b.isFavourite(userId)) {
                favourites.add(b);
            }
        }
        return Response.ok(favourites).build();
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
    @Path("/users/{id}/friends/{friendId}")
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
    @Path("/users/{id}/friends/{friendId}")
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

    @DELETE
    @Path("/users/{id}/wanttoread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeWantToRead(@PathParam("id") int id, @PathParam("book") int bookId){
        User user = ur.findById(id);
        Book book = br.findByBookId(bookId);
        if(user.getBooksToRead().contains(book)) {
            user.removeWantToRead(book);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/users/{id}/wanttoread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addWantToRead(@PathParam("id") int id, @PathParam("book") int bookId){
        User user = ur.findById(id);
        Book book = br.findByBookId(bookId);
        if(user.getBooksToRead().contains(book)) {
            return Response.status(400).build();
        } else {
            user.addWantToRead(book);
            return Response.status(201).build();
        }
    }

    @DELETE
    @Path("/users/{id}/haveread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeHaveRead(@PathParam("id") int id, @PathParam("book") int bookId){
        User user = ur.findById(id);
        Book book = br.findByBookId(bookId);
        if(user.getBooksHaveRead().contains(book)) {
            user.removeHaveRead(book);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/users/{id}/haveread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addHaveReadRead(@PathParam("id") int id, @PathParam("book") int bookId){
        User user = ur.findById(id);
        Book book = br.findByBookId(bookId);
        if(user.getBooksHaveRead().contains(book)) {
            return Response.status(400).build();
        } else {
            user.addHaveRead(book);
            return Response.status(201).build();
        }
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBookTitel(){
        List<Book> r = br.getAllBooks();
        return r;
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
    @Path ("/books/author/{bookAuthor}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByAuthor(@PathParam("bookAuthor") String bookAuthor) { return br.findByBookAuthor(bookAuthor);
    }


    @GET
    @Path ("/books/genre")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Book> getAllGenre() { return br.getAllGenres();
    }

    @GET
    @Path ("/books/genre/{genreName}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByGenre(@PathParam("genreName") List<String> genreName) { return br.findByBookGenre(genreName);
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

    //Delete review by id
    @DELETE
    @Path("/review/{reviewId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeReview(@PathParam("reviewId") int reviewId) {
        rr.findById(reviewId);
           return Response.ok("Recension borttagen" + rr.removeReview(reviewId)).build();
    }


    //Update review by id
    @PUT
    @Path("/review/{reviewId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateReview(@PathParam("reviewId") int reviewId, String newReview){
      //  rr.findById(reviewId);
        return Response.ok("Uppdaterat recention" + rr.updateReview(reviewId, newReview)).build();


        }
    }
