package com.bookstore;

import com.bookstore.bookManagement.BookDao;

public class Main {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        bookDao.getAllBooks();
    }
}
