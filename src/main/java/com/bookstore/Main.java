package com.bookstore;

import com.bookstore.bookManagement.Book;
import com.bookstore.bookManagement.BookDao;
import com.bookstore.bookManagement.BookResource;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;


public class Main {
    public static void main(String[] args) {
        String BASE_URI = "http://localhost:8080/";
        ResourceConfig resourceConfig = new ResourceConfig(BookResource.class);
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
        System.out.println("Server started at: " + BASE_URI);

        System.out.println("Test");

        BookDao bookDao = new BookDao();
        Book book = new Book("The Witcher: The Last Wish", "Andrzej Sapkowski", 29.99, 15);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 29.99, 9);
        Book book3;
        try {
            book3 = bookDao.getBookById(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(bookDao.addBook(book));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(bookDao.updateBook(1, book2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(bookDao.deleteBook(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
