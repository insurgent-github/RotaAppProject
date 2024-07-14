package com.miguelangelvinas.rotaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import javafx.scene.control.Button;

public class LoginPageController {

    @FXML
    private Button button_login;
    @FXML
    private Button button_cancel;
    @FXML
    private Button button_signUp;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private PasswordField textField_password;
    @FXML
    private TextField textField_username;

    @FXML
    public void buttonLoginOnAction(ActionEvent event) {
        if (!textField_username.getText().isBlank() && !textField_password.getText().isBlank()) {
            validateLogin(event);
        } else {
            loginMessageLabel.setText("Please enter your credentials");
        }
    }

    private void validateLogin(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Filosofia1!");

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, textField_username.getText());
            statement.setString(2, textField_password.getText());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User loggedInUser = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("isAdministrator")
                );

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.miguelangelvinas.rotaapp/Dashboard.fxml"));
                Parent dashboardParent = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.initData(loggedInUser);

                Scene dashboardScene = new Scene(dashboardParent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard");
                stage.setScene(dashboardScene);
                stage.show();
            } else {
                loginMessageLabel.setText("Your credentials are not valid. Please try again or contact your administrator.");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            loginMessageLabel.setText("An error has occurred. Please try again.");
        }
    }

    @FXML
    public void buttonCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buttonSignUpOnAction(ActionEvent event) {
        try {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/SignUpPage.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_signUp.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
}