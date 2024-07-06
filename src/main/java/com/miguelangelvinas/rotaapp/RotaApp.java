package com.miguelangelvinas.rotaapp;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
For the Database
username: root
password: Filosofia1!
 */

public class RotaApp extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        try
        {
            //Loads the login page FXML file
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/LoginPage.fxml")));
            primaryStage.setTitle("RotaApp Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
            //Handles the exception (It will show an error message)
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
