package com.bookstore.userManagement;

import com.bookstore.DBConnection;
import com.bookstore.authorManagement.AuthorDao;
import com.bookstore.bookManagement.GetBookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean registerUser(AddUserDTO user) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO Users (username, password, role) VALUES(?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole().getValue());

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginUser(GetUserLoginPasswordDTO loginPasswordDTO) throws Exception {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE userName = ?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, loginPasswordDTO.getUserName());

                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        return loginPasswordDTO.getPassword().equals(result.getString("password"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
