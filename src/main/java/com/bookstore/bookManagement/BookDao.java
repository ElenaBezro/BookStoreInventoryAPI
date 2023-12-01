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

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        Book book = new Book();
                        book.setId(result.getInt("id"));
                        book.setTitle(result.getString("title"));
                        book.setAuthor(result.getString("author"));
                        book.setPrice(result.getDouble("price"));
                        book.setQuantity(result.getInt("quantity"));

                        System.out.println(book);
                        return book;
                    } else {
                        throw new Exception("Invalid id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addBook(Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO Books (title, author, price, quantity) VALUES(?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setDouble(3, book.getPrice());
                preparedStatement.setInt(4, book.getQuantity());

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateBook(int id, Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE Books SET title = ?, author = ?, price = ?, quantity = ? WHERE id = ?";


            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setDouble(3, book.getPrice());
                preparedStatement.setInt(4, book.getQuantity());
                preparedStatement.setInt(5, id);

                int affectedRecords = preparedStatement.executeUpdate();
                return affectedRecords;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteBook(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM Books WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
