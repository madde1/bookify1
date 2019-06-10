/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/

package com.bookify.jpa.businesslogic;


import com.bookify.jpa.models.*;
import com.bookify.jpa.repositrories.ReviewRepository;
import com.bookify.jpa.repositrories.UserRepository;
import com.bookify.jpa.repositrories.bookRepository;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.*;


public class BL {

    @Inject
    private UserRepository ur;

    @Inject
    private bookRepository br;

    @Inject
    private ReviewRepository rr;

    public List<User> getUserName() {
        return ur.getAllUsers();
    }

    private User getUserById(int id) {
        return ur.findById(id);
    }

    public User getUser(String userName) {
        return ur.findUserByUsernameOrId(userName);
    }

    public Response getFriendsByUserId(int id) {
        User u = ur.findById(id);
        return Response.ok(u.getFriends()).build();
    }

    public Response getWantToReadByUserId(int id) {
        User u = ur.findById(id);
        return Response.ok(u.getBooksToRead()).build();
    }

    public Response getHaveReadByUserId(int id) {
        User u = ur.findById(id);
        return Response.ok(u.getBooksHaveRead()).build();
    }

    /**
     * Gives points to a book based on if its for example the same genre or author as a book the user have read before.
     * @param id user id
     * @return Recommended books
     */
    public Response getUserRecommendation(int id) {
        User u = ur.findById(id);
        Set<Genre> allGenresRead = new HashSet<>();
        for(Book b : u.getBooksHaveRead()) {
            allGenresRead.addAll(b.getBooksGenre());
        }
        Set<String> allAuthorsRead = new HashSet<>();
        for(Book b : u.getBooksHaveRead()) {
            allAuthorsRead.add(b.getBookAuthor());
        }

        Map<Book, Integer> recommendations = new HashMap<>();
        for(Book b : getBookTitel()) {
            //1 point for same genre
            for (Genre g : b.getBooksGenre()) {
                if(allGenresRead.contains(g)) {
                    recommendations.put(b, 1);
                }
            }
            //3 points for same author
            if(allAuthorsRead.contains(b.getBookAuthor())) {
                recommendations.put(b, getNewRecommendationValue(recommendations, b, 3));
            }
        }

        //Remove books already read
        Iterator it = recommendations.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            for(Book b : u.getBooksHaveRead()) {
                if(pair.getKey().equals(b)) {
                    it.remove();
                }
            }
        }

       //return Response.ok(recommendations.keySet()).build();
        return Response.ok(recommendations).build();
    }

    private int getNewRecommendationValue(Map<Book, Integer> recommendations, Book b, int addAmount) {
        int value = recommendations.get(b);
        return value + addAmount;
    }

    public Response getFavouritesByUserId(int userId) {
        User u = getUserById(userId);
        Set<Book> haveReads = u.getBooksHaveRead();
        List<Book> favourites = new ArrayList<>();
        for (Book b : haveReads) {
            if (b.isFavourite(userId)) {
                favourites.add(b);
            }
        }
        return Response.ok(favourites).build();
    }

    public Response postUser(User user) {
        ur.create(user);
        return Response.ok(user.getUserName() + " added as user").build();

    }

    public Response addFriend(int id, int friendId) {
        User user = getUserById(id);
        User friend = getUserById(friendId);
        if (user.isFriendWith(friend)) {
            return Response.status(400).build();
        } else {
            user.addFriend(friend);
            return Response.status(201).build();
        }
    }

    public Response removeFriend(int id, int friendId) {
        User user = getUserById(id);
        User friend = getUserById(friendId);
        if (user.isFriendWith(friend)) {
            user.removeFriend(friend);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    public Response postUser(int userId, int bookId) {
        Book book = br.findByBookId(bookId);
        for (HaveRead hr : book.getHaveReads()) {
            if (hr.getUserId() == userId) {
                hr.setIsFavourite(1);
                return Response.ok().build();
            }
        }
        return Response.status(404).build();
    }

    public Response removeWantToRead(int id, int bookId) {
        User user = getUserById(id);
        Book book = br.findByBookId(bookId);
        if (user.getBooksToRead().contains(book)) {
            user.removeWantToRead(book);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    public Response editUser(int id, User user) {
        getUserById(id).setUserName(user.getUserName());
        getUserById(id).setUserEmail(user.getUserEmail());
        return Response.ok().build();
    }

    public Response removeUser(int id) {
        ur.delete(getUserById(id));
        return Response.ok().build();
    }

    public Response addWantToRead(int id, int bookId) {
        User user = getUserById(id);
        Book book = br.findByBookId(bookId);
        if (user.getBooksToRead().contains(book)) {
            return Response.status(400).build();
        } else {
            user.addWantToRead(book);
            return Response.status(201).build();
        }
    }

    public Response removeHaveRead(int id, int bookId) {
        User user = getUserById(id);
        Book book = br.findByBookId(bookId);
        if (user.getBooksHaveRead().contains(book)) {
            user.removeHaveRead(book);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    public Response addHaveRead(int id, int bookId) {
        User user = getUserById(id);
        Book book = br.findByBookId(bookId);
        if (user.getBooksHaveRead().contains(book)) {
            return Response.status(400).build();
        } else {
            user.addHaveRead(book);
            return Response.status(201).build();
        }
    }

    public List<Book> getBookTitel() {
        List<Book> r = br.getAllBooks();
        return r;
    }

    public Response deleteBook(int bookId) {
        Book book = br.findByBookId(bookId);
        if (book.getBookId().equals(bookId)) {
            br.removeBook(book);
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    public Book getBookById(int id) {
        return br.findByBookId(id);
    }

    public Response updateBook(int bookId, Book book) {
        getBookById(bookId).setBookAuthor(book.getBookAuthor());
        getBookById(bookId).setBookTitel(book.getBookTitel());
        getBookById(bookId).setBookDate(book.getBookDate());

        return Response.ok("The book has been updated").build();
    }

    public Response postBook(Book book) {
        br.create(book);
        return Response.ok(book.getBookTitel() + " " + book.getBookAuthor() + " book added to the database").build();
    }

    public Book getBookByTitle(String bookTitle) {
        return br.findByBookTitle(bookTitle);
    }

    public Book getBookByAuthor(String bookAuthor) {
        return br.findByBookAuthor(bookAuthor);
    }

    public List<Book> getAllGenre() {
        return br.getAllGenres();
    }

    public List<Book> getBookByGenre(List<String> genreName) {
        return br.findByBookGenre(genreName);
    }

    public Response postReview(Review review){
        rr.create(review);
        return Response.ok("En recension skriven för boken med id: " +review.getReviewbookId()).build();
    }

    public List<Review> getReview(){
        return rr.getAllReviews();
    }

    public List<Review> getReviewForBook(String bookTitle) {
        return rr.viewReviewForBook(bookTitle);
    }

    public Response removeReview(int reviewId) {
        rr.findById(reviewId);
        return Response.ok("Recension borttagen" + rr.removeReview(reviewId)).build();
    }


    public Response updateReview(int reviewId, String newReview){
        return Response.ok("Uppdaterat recention" + rr.updateReview(reviewId, newReview)).build();


    }




}


