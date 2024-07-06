package com.miguelangelvinas.rotaapp;

import com.almasb.fxgl.entity.action.Action;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable
{
    //These are all my buttons from the Top Menu.
    @FXML
    private Button button_monthlyRota;
    @FXML
    private Button button_weeklyRota;
    @FXML
    private Button button_dailyRota;
    @FXML
    private Button button_departmentRota;
    @FXML
    private Button button_requestHolidays;
    @FXML
    private Button button_account;

    //We are going to click on the button MONTHLY ROTA
    @FXML
    private void buttonMonthlyRotaOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/Dashboard.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_monthlyRota.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

    //We are going to click on the button WEEKLY ROTA
    @FXML
    private void buttonWeeklyRotaOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/WeeklyRota.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_weeklyRota.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

    //We are going to click on the button DAILY ROTA
    @FXML
    private void buttonDailyRotaOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/DailyRota.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_dailyRota.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

    //We are going to click on the button DEPARTMENT ROTA
    @FXML
    private void buttonDepartmentRotaOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/DepartmentRota.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_departmentRota.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

    //We are going to click on the button REQUEST HOLIDAYS
    @FXML
    private void buttonRequestHolidaysOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/RequestHolidays.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_requestHolidays.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

    //We are going to click on the button ACCOUNT
    @FXML
    private void buttonAccountOnAction (ActionEvent event)
    {
        try
        {
            Parent signUpPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.miguelangelvinas.rotaapp/Account.fxml")));
            Scene signUpPageScene = new Scene(signUpPageParent);
            Stage stage = (Stage) button_account.getScene().getWindow();
            stage.setScene(signUpPageScene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
