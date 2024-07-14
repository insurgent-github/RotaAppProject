package com.miguelangelvinas.rotaapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "Filosofia1!";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching all users: " + e.getMessage());
        }

        return users;
    }

    public User getUserByName(String name) {
        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching user by name: " + e.getMessage());
        }

        return null;
    }

    public boolean saveUser(User user) {
        String query = "INSERT INTO users (name, password, email, isAdministrator) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setBoolean(4, user.isAdministrator());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving user: " + e.getMessage());
        }

        return false;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        boolean isAdministrator = resultSet.getBoolean("isAdministrator");

        return new User(id, email, password, name, isAdministrator);
    }
}