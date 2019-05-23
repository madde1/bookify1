package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.Book;
import com.bookify.jpa.models.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

//hej
public class bookRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<Book> getAllBooks(){
        Query query = em.createQuery("SELECT book FROM Book book order by book.bookTitel");
        return query.getResultList();
    }

    public Book findByBookTitle(String bookTitle){
        TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b WHERE b.bookTitel = :bookTitle", Book.class);
        return q.setParameter("bookTitle", bookTitle ).getSingleResult();
    }

    public Book findByBookAuthor(String bookAuthor) {
        TypedQuery<Book> authorQuery = em.createQuery("SELECT b FROM Book b WHERE b.bookAuthor = :bookAuthor", Book.class);
        return authorQuery.setParameter("bookAuthor", bookAuthor).getSingleResult();
    }

    public Book create(Book book){
        em.persist(book);
        return book;
    }

}