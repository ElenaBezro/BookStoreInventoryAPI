package com.bookstore;

import com.bookstore.bookManagement.Book;
import com.bookstore.bookManagement.BookDao;


public class Main {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        Book book = new Book("The Witcher: The Last Wish", "Andrzej Sapkowski", 29.99, 15);
        try {
            bookDao.getBookById(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(bookDao.addBook(book));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
