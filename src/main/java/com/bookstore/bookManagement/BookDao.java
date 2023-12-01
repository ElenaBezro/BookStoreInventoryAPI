package com.bookstore.bookManagement;

import com.bookstore.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Books;";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) {
                        Book book = new Book();
                        book.setId(result.getInt("id"));
                        book.setTitle(result.getString("title"));
                        book.setAuthor(result.getString("author"));
                        book.setPrice(result.getDouble("price"));
                        book.setQuantity(result.getInt("quantity"));
                        books.add(book);
                    }
                }
            }
            System.out.println(books);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Book getBookById(int id) throws Exception {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Books WHERE id = ?;";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try(ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        Book book = new Book();
                        book.setId(result.getInt("id"));
                        book.setTitle(result.getString("title"));
                        book.setAuthor(result.getString("author"));
                        book.setPrice(result.getDouble("price"));
                        book.setQuantity(result.getInt("quantity"));

                        System.out.println(book);
                        return book;
                    }
                    else {
                        throw new Exception("Invalid id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBook(Book book) {
        //TODO: add book to DB
        //TODO: maybe return boolean true if successfully
    }

    public void updateBook(int id, Book book) {
        //TODO: update book in DB
        //TODO: maybe return boolean true if successfully or throw error
    }

    public void deleteBook(int id) {
        //TODO: delete book in DB
        //TODO: maybe return boolean true if successfully or throw error
    }

}
