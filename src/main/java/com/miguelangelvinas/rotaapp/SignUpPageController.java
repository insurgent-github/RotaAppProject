package com.miguelangelvinas.rotaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.*;
import java.io.IOException;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpPageController
{
    @FXML
    private TextField textField_name;
    @FXML
    private TextField textField_email;
    @FXML
    private PasswordField passwordField_password;
    @FXML
    private PasswordField passwordField_confirmPassword;

    @FXML
    private Label label_errorMessage;

    @FXML
    private void buttonSignUpOnAction(ActionEvent event) {
        String name= textField_name.getText();
        String email = textField_email.getText();
        String password = passwordField_password.getText();
        String confirmPassword = passwordField_confirmPassword.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
        {
            label_errorMessage.setText("Please enter your credentials in order to sign up.");
        }
        else
        {
            // Clear any previous error message
            label_errorMessage.setText("");

            // Insert email and password into the database
            try
            {
                // Establish database connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Filosofia1!");

                // Define SQL query to insert data
                String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, password);

                //Execute the update statement.
                statement.executeUpdate();

                // Close resources
                statement.close();
                connection.close();

                // Load the dashboard scene
                Parent dashboardParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/Dashboard.fxml")));
                Scene dashboardScene = new Scene(dashboardParent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard Page");
                stage.setScene(dashboardScene);
                stage.show();
            }
            catch (SQLException | IOException e) {
                e.printStackTrace();
                label_errorMessage.setText("An error has occurred.");
                // Handle database or I/O exception gracefully
            }
        }
    }


    @FXML
    private void handleBackToLoginScreenButtonAction (ActionEvent event)
    {
        try
        {
            // Load the SignUpPage.fxml file
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/LoginPage.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setTitle("Login Page");
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
}



