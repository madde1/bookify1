/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.Book;
import com.bookify.jpa.models.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for book table and genre table.
 * @see Book
 */

public class bookRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<Book> getAllBooks(){
        Query query = em.createQuery("SELECT book FROM Book book order by book.bookTitel");
        return query.getResultList();
    }

    public Book findByBookId(int bookId){
        TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b WHERE b.id = :bookId", Book.class);
        return q.setParameter("bookId", bookId ).getSingleResult();
    }

    public Book findByBookTitle(String bookTitle){
        TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b WHERE b.bookTitel = :bookTitle", Book.class);
        return q.setParameter("bookTitle", bookTitle ).getSingleResult();
    }

    public Book findByBookAuthor(String bookAuthor) {
        TypedQuery<Book> authorQuery = em.createQuery("SELECT b FROM Book b WHERE b.bookAuthor = :bookAuthor", Book.class);
        return authorQuery.setParameter("bookAuthor", bookAuthor).getSingleResult();
    }

    public List<Book> getAllGenres() {
        Query query = em.createQuery("SELECT genre.genreNewName FROM Genre genre");
        return query.getResultList();
    }

    //last change was that I tried to set parameter as type List, instead of a single String!
   public List<Book> findByBookGenre(List<String> genreName) {
        TypedQuery <Book> genreQuery = em.createQuery("SELECT b FROM Book b JOIN b.booksGenre bg WHERE bg.genreNewName = :genreName", Book.class);

        return genreQuery.setParameter("genreName", genreName).getResultList();
    }

    public Book create(Book book){
        em.persist(book);
        return book;
    }

    public void removeBook(Book book){
        em.remove(book);
    }

}