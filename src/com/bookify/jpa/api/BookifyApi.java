/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/

package com.bookify.jpa.api;

import com.bookify.jpa.businesslogic.BL;
import com.bookify.jpa.models.Book;
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
import java.util.List;

/**This class contains the API Methods that calls on the business logic part
 *  and returns information to the users. */
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

    /**From here are the User methods**/

    /**
     * Gets all the users
     *
     * @return getUserName
     */
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserName() {
        return bl.getUserName();
    }

    /**
     * get user by username or id
     */
    @GET
    @Path("/users/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userName") String userName) {
        return bl.getUser(userName);
    }

    /**
     * Gets all the friends from one user.
     */
    @GET
    @Path("/users/{id}/recommendation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRecommendation(@PathParam("id") int id) {
        return bl.getUserRecommendation(id);
    }

    @GET
    @Path("/users/{id}/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendsByUserId(@PathParam("id") int id) {
        return bl.getFriendsByUserId(id);
    }

    /**
     * Gets all the books one user wants to read.
     */
    @GET
    @Path("/users/{id}/wanttoread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWantToReadByUserId(@PathParam("id") int id) {
        return bl.getWantToReadByUserId(id);
    }

    /**
     * Gets all the books one user have read.
     */
    @GET
    @Path("/users/{id}/haveread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHaveReadByUserId(@PathParam("id") int id) {
        return bl.getHaveReadByUserId(id);
    }

    /**
     * Gets all favorites of one user.
     */
    @GET
    @Path("/users/{id}/favourites")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavouritesByUserId(@PathParam("id") int userId) {
        return bl.getFavouritesByUserId(userId);
    }

    /**
     * Post Method too add Users.
     */
    @POST
    @Path("/users")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(User user) {
        return bl.postUser(user);

    }

    /**
     * Post method for adding a friend to one specific user
     */
    @POST
    @Path("/users/{id}/friends/{friendId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addFriend(@PathParam("id") int id, @PathParam("friendId") int friendId) {
        return bl.addFriend(id, friendId);
    }

    /**
     * Delete mehtod for removing a friend from one specific user.
     */
    @DELETE
    @Path("/users/{id}/friends/{friendId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeFriend(@PathParam("id") int id, @PathParam("friendId") int friendId) {
        return bl.removeFriend(id, friendId);
    }

    /**
     * Post method to add a book in favourites for one user.
     */
    @POST
    @Path("/users/{userId}/favourites/{bookId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(@PathParam("userId") int userId, @PathParam("bookId") int bookId) {
        return bl.postUser(userId, bookId);
    }

    /**
     * Delete a book from a users want to read list.
     */
    @DELETE
    @Path("/users/{id}/wanttoread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeWantToRead(@PathParam("id") int id, @PathParam("book") int bookId) {
        return bl.removeWantToRead(id, bookId);
    }

    /**
     * Patch method to update users, updates email and name.
     */
    @PATCH
    @Path("/users/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    // Update name and email
    public Response editUser(@PathParam("id") int id, User user) {
        return bl.editUser(id, user);
    }

    /**
     * Delete method that removes a user.
     */
    @DELETE
    @Path("/users/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeUser(@PathParam("id") int id) {
        return bl.removeUser(id);
    }

    /**
     * Post method that adds a book to the users want to read list.
     */
    @POST
    @Path("/users/{id}/wanttoread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addWantToRead(@PathParam("id") int id, @PathParam("book") int bookId) {
        return bl.addWantToRead(id, bookId);
    }

    /**
     * Delete method for have read , removes one book from a users have read list.
     */
    @DELETE
    @Path("/users/{id}/haveread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeHaveRead(@PathParam("id") int id, @PathParam("book") int bookId) {
        return bl.removeHaveRead(id, bookId);
    }

    /**
     * Post method for adding a book to a users have read list
     */
    @POST
    @Path("/users/{id}/haveread/{book}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addHaveRead(@PathParam("id") int id, @PathParam("book") int bookId) {
        return bl.addHaveRead(id, bookId);
    }

    /**
     * Here starts the books Methods
     * <p>
     * Get method for books. Gets all the books in the database
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBookTitel() {
        return bl.getBookTitel();
    }

    /**
     * Delete book by ID
     */
    @DELETE
    @Path("/books/{bookId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteBook(@PathParam("bookId") int bookId) {
        return bl.deleteBook(bookId);
    }

    /**
     * Get book by id (for admin purposes)
     */
    @GET
    @Path("/books/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("bookId") int id) {
        return bl.getBookById(id);
    }

    /**
     * Patch to update a book
     */
    @PATCH
    @Path("/books/{bookId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateBook(@PathParam("bookId") int bookId, Book book) {
        return bl.updateBook(bookId, book);
    }

    /**
     * Post to add a new book to the database. Only admin can use this one.
     */
    @POST
    @Path("/secured/books")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postBook(Book book) {
        return bl.postBook(book);
    }

    /**
     * Get method to get book titles.
     */
    @GET
    @Path("/books/title/{bookTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookByTitle(@PathParam("bookTitle") String bookTitle) {
        return bl.getBookByTitle(bookTitle);
    }

    /**
     * Get the books authors.
     */
    @GET
    @Path("/books/author/{bookAuthor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookByAuthor(@PathParam("bookAuthor") String bookAuthor) {
        return bl.getBookByAuthor(bookAuthor);
    }

    /**
     * Gets the books by genre
     */
    @GET
    @Path("/books/genre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllGenre() {
        return bl.getAllGenre();
    }

    /**
     * Gets the books in one genre
     */
    @GET
    @Path("/books/genre/{genreName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBookByGenre(@PathParam("genreName") List<String> genreName) {
        return bl.getBookByGenre(genreName);
    }


    /**
     * Review parts starts here
     * <p>
     * Post mehtod to add a review to a book
     */
    @POST
    @Path("/reviews")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postReview(Review review) {
        return bl.postReview(review);
    }

    /**
     * Get all the review
     */
    @GET
    @Path("/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReview() {
        return bl.getReview();
    }


    /**
     * Get review by bookTitle
     */
    @GET
    @Path("/reviews/{bookTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviewForBook(@PathParam("bookTitle") String bookTitle) {
        return bl.getReviewForBook(bookTitle);
    }


    /**
     * Delete review by id
     */
    @DELETE
    @Path("/reviews/{reviewId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeReview(@PathParam("reviewId") int reviewId) {
        return bl.removeReview(reviewId);
    }

    /**
     * Update review by id
     */
    @PUT
    @Path("/reviews/{reviewId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateReview(@PathParam("reviewId") int reviewId, String newReview) {
        return bl.updateReview(reviewId, newReview);
    }

}