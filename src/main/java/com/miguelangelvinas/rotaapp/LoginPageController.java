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


public class LoginPageController
{

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


    public void buttonLoginOnAction (ActionEvent event)
    {

        if (textField_username.getText().isBlank() == false && textField_password.getText().isBlank() == false)
        {
            validateLogin(event);
        }
        else
        {
            loginMessageLabel.setText("Please enter your credentials");
        }
    }

    private void validateLogin(ActionEvent event)
    {
        try
        {
            //We are establishing the database connection here.
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Filosofia1!");

            //And we are going to define the SQL Query to SELECT the user based on the USERNAME and the PASSWORD.
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, textField_username.getText());
            statement.setString(2, textField_password.getText());

            //We are going to execute the query
            ResultSet resultSet = statement.executeQuery();

            //And we are going to check if there is any matching users
            if (resultSet.next())
            {
                //Load the dashboard scene
                Parent dashboardParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/Dashboard.fxml")));
                Scene dashboardScene = new Scene(dashboardParent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard Page");
                stage.setScene(dashboardScene);
                stage.show();
            }
            else
            {
                //The credentials are not valid, therefore there is no matching user!.
                loginMessageLabel.setText("Your credentials are not valid. Please try again or contact your administrator.");
            }

            //We are closing the resources as best practice.
            resultSet.close();;
            statement.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            e.printStackTrace();
            loginMessageLabel.setText("An error has occurred. Please try again.");
        }
    }

    @FXML
    public void buttonCancelOnAction(ActionEvent event)
    {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buttonSignUpOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/SignUpPage.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_signUp.getScene().getWindow();
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
