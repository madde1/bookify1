package com.bookify.jpa.api;

import com.bookify.jpa.businesslogic.BL;
import com.bookify.jpa.models.Book;
import com.bookify.jpa.models.HaveRead;
import com.bookify.jpa.models.Review;
import com.bookify.jpa.models.User;
import javax.inject.Inject;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    private BL bl;

    @GET
    @Path("/secured")
    @Produces(MediaType.TEXT_PLAIN)
    public String secureApi() {
        return "This requires login and you are verified";
    }

    @GET
    @Path("/open")
    @Produces(MediaType.TEXT_PLAIN)
    public String openApi() {
        return "Open: no login required.";
    }


    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserName() {
        return bl.getUserName();
    }


    //get user by id (for admin purposes)
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) {
        return bl.getUserById(id);

    }

    //get user by username
    @GET
    @Path("/users/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUserName(@PathParam("userName") String userName) {
        return bl.getUserByUserName(userName);
    }



    @GET
    @Path("/users/{id}/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendsByUserId(@PathParam("id") int id) {
        return bl.getFriendsByUserId(id);
    }


    @GET
    @Path("/users/{id}/wanttoread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWantToReadByUserId(@PathParam("id") int id) {
       return bl.getWantToReadByUserId(id);
    }


    @GET
    @Path("/users/{id}/haveread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHaveReadByUserId(@PathParam("id") int id) {
        return bl.getHaveReadByUserId(id);
    }

    @GET
    @Path("/users/{id}/favourites")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavouritesByUserId(@PathParam("id") int userId) {
        return bl.getFavouritesByUserId(userId);
    }


    //Post metod för att lägga till användare.
    @POST
    @Path("/users")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(User user){
        return bl.postUser(user);

    }


    @POST
    @Path("/users/{id}/friends/{friendId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addFriend(@PathParam("id") int id, @PathParam("friendId") int friendId){
        return bl.addFriend(id, friendId);
    }


    @DELETE
    @Path("/users/{id}/friends/{friendId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeFriend(@PathParam("id") int id, @PathParam("friendId") int friendId){
     return bl.removeFriend(id, friendId);
    }

    @POST
    @Path("/users/{userId}/favourites/{bookId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(@PathParam("userId")int userId, @PathParam("bookId")int bookId){
      return bl.postUser(userId, bookId);
    }

    @DELETE
    @Path("/users/{id}/wanttoread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeWantToRead(@PathParam("id") int id, @PathParam("book") int bookId){
        return bl.removeWantToRead(id, bookId);
    }

    @PATCH
    @Path("/users/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    // Update name and email
    public Response editUser(@PathParam("id") int id, User user){
        return bl.editUser(id, user);
    }

    @DELETE
    @Path("/users/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeUser(@PathParam("id") int id){
       return bl.removeUser(id);
    }

    @POST
    @Path("/users/{id}/wanttoread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addWantToRead(@PathParam("id") int id, @PathParam("book") int bookId){
        return bl.addWantToRead(id, bookId);
    }

    @DELETE
    @Path("/users/{id}/haveread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeHaveRead(@PathParam("id") int id, @PathParam("book") int bookId){
        return bl.removeHaveRead(id, bookId);
    }

    @POST
    @Path("/users/{id}/haveread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addHaveRead(@PathParam("id") int id, @PathParam("book") int bookId){
        return bl.addHaveRead(id,bookId);
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBookTitel(){
        return bl.getBookTitel();
    }

    //Delete book by ID
    @DELETE
    @Path("/books/{bookId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteBook(@PathParam("bookId") int bookId ){
        return bl.deleteBook(bookId);
    }


    //get book by id (for admin purposes)
    @GET
    @Path("/books/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("bookId") int id) {
        return bl.getBookById(id);
    }

    @PATCH
    @Path("/books/{bookId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateBook(@PathParam("bookId") int bookId, Book book ){
        return bl.updateBook(bookId,book);
    }

    @POST
    @Path("/secured/books")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postBook(Book book){
        return bl.postBook(book);
    }


    @GET
    @Path ("/books/title/{bookTitle}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByTitle(@PathParam("bookTitle") String bookTitle) {
        return bl.getBookByTitle(bookTitle);
    }

    @GET
    @Path ("/books/author/{bookAuthor}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByAuthor(@PathParam("bookAuthor") String bookAuthor) {
        return bl.getBookByAuthor(bookAuthor);
    }


    @GET
    @Path ("/books/genre")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Book> getAllGenre() {
        return bl.getAllGenre();
    }

    @GET
    @Path ("/books/genre/{genreName}")
    @Produces (MediaType.APPLICATION_JSON)
    public Book getBookByGenre(@PathParam("genreName") List<String> genreName) {
        return bl.getBookByGenre(genreName);
    }


    /**Recensions delen av API:et med CRUD metoder*/


    @POST
    @Path("/review")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postReview(Review review){
        return bl.postReview(review);
    }


    @GET
    @Path("/review")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReview(){
        return bl.getReview();
    }


    //Get review by bookTitle
    @GET
    @Path("/review/{bookTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviewForBook(@PathParam("bookTitle") String bookTitle) {
        return bl.getReviewForBook(bookTitle);
    }


    //Delete review by id
    @DELETE
    @Path("/review/{reviewId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeReview(@PathParam("reviewId") int reviewId) {
        return bl.removeReview(reviewId);
    }

    //Update review by id
    @PUT
    @Path("/review/{reviewId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateReview(@PathParam("reviewId") int reviewId, String newReview){
        return bl.updateReview(reviewId, newReview);
        }


    }
