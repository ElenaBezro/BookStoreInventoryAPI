package com.bookstore.bookManagement;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/book")
public class BookResource {
    public static BookDao bookDao = new BookDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") int id) {
        try {
            //TODO return status OK.
            return bookDao.getBookById(id);
        } catch (Exception e) {
            //TODO return status BAD_REQUEST.
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBook(Book book) {
        //TODO: check if bookDao.addBook(book) return 1 or 0? Response if successfully
        bookDao.addBook(book);
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("id") int id, Book updatedBook) {
        //TODO: check if bookDao.updateBook(id,book) return 1 or 0? Response if successfully
        bookDao.updateBook(id, updatedBook);
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteBook(@PathParam("id") int id) {
        //TODO: check if bookDao.deleteBook(id) return 1 or 0? Response if successfully
        bookDao.deleteBook(id);
    }

}


