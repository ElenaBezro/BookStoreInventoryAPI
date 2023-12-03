package com.bookstore.bookManagement;

import com.bookstore.DBConnection;
import com.bookstore.authorManagement.Author;
import com.bookstore.authorManagement.AuthorDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<GetBookDTO> getAllBooks() {
        List<GetBookDTO> books = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT id, a.author_id, name AS author_name, email, title, price, quantity" +
                    " FROM Books b JOIN Authors a" +
                    " ON b.author_id = a.author_id;";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) {
                        GetBookDTO book = new GetBookDTO();
                        Author author = new Author();
                        book.setId(result.getInt("id"));
                        author.setId(result.getInt("author_id"));
                        author.setName(result.getString("author_name"));
                        author.setEmail(result.getString("email"));
                        book.setAuthor(author);
                        book.setTitle(result.getString("title"));
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

    public GetBookDTO getBookById(int id) throws Exception {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Books WHERE id = ?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        GetBookDTO book = new GetBookDTO();
                        book.setId(result.getInt("id"));
                        book.setAuthor(new AuthorDao().getAuthorById(result.getInt("author_id")));
                        book.setTitle(result.getString("title"));
                        book.setPrice(result.getDouble("price"));
                        book.setQuantity(result.getInt("quantity"));

                        System.out.println(book);
                        return book;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addBook(BookDTO book) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO Books (author_id, title, price, quantity) VALUES(?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, book.getAuthorId());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setDouble(3, book.getPrice());
                preparedStatement.setInt(4, book.getQuantity());

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBook(int id, BookDTO book) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE Books SET author_id = ?, title = ?, price = ?, quantity = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, book.getAuthorId());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setDouble(3, book.getPrice());
                preparedStatement.setInt(4, book.getQuantity());
                preparedStatement.setInt(5, id);

                int affectedRecords = preparedStatement.executeUpdate();
                return affectedRecords > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBook(int id) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM Books WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords > 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Error occurred during the delete operation");
        }
    }

}
