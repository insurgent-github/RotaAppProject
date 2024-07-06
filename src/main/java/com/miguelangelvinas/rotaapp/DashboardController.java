package com.miguelangelvinas.rotaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

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
    @FXML
    private GridPane calendarContainer;
    @FXML
    private Label monthYearLabel;

    private CalendarView calendarView;
    private boolean isAdministrator; // This should be set based on user login

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Set isAdministrator based on user login
        isAdministrator = true; // For testing purposes, set to true or false

        calendarView = new CalendarView(isAdministrator);
        calendarContainer.getChildren().add(calendarView.getView());
        updateMonthYearLabel();

        if (isAdministrator) {
            Button addEventButton = new Button("Add Event");
            addEventButton.setOnAction(e -> showAddEventDialog());
            calendarContainer.add(addEventButton, 0, 0);
        }
    }

    private void updateMonthYearLabel() {
        monthYearLabel.setText(calendarView.getCurrentMonthYear());
    }

    private void showAddEventDialog() {
        Dialog<Event> dialog = new Dialog<>();
        dialog.setTitle("Add Event");
        dialog.setHeaderText("Enter event details");

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField description = new TextField();
        TextField assignedUser = new TextField();

        grid.add(new Label("Date:"), 0, 0);
        grid.add(datePicker, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(description, 1, 1);
        grid.add(new Label("Assigned User:"), 0, 2);
        grid.add(assignedUser, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new Event(datePicker.getValue(), description.getText(), assignedUser.getText());
            }
            return null;
        });

        Optional<Event> result = dialog.showAndWait();

        result.ifPresent(event -> {
            calendarView.addEvent(event);
            updateMonthYearLabel(); // Refresh the calendar view
        });
    }

    @FXML
    private void previousMonth(ActionEvent event) {
        calendarView.previousMonth();
        updateMonthYearLabel();
    }

    @FXML
    private void nextMonth(ActionEvent event) {
        calendarView.nextMonth();
        updateMonthYearLabel();
    }

    @FXML
    private void buttonMonthlyRotaOnAction(ActionEvent event) {
        loadFXML("/com.miguelangelvinas.rotaapp/Dashboard.fxml", button_monthlyRota);
    }

    @FXML
    private void buttonWeeklyRotaOnAction(ActionEvent event) {
        loadFXML("/com.miguelangelvinas.rotaapp/WeeklyRota.fxml", button_weeklyRota);
    }

    @FXML
    private void buttonDailyRotaOnAction(ActionEvent event) {
        loadFXML("/com.miguelangelvinas.rotaapp/DailyRota.fxml", button_dailyRota);
    }

    @FXML
    private void buttonDepartmentRotaOnAction(ActionEvent event) {
        loadFXML("/com.miguelangelvinas.rotaapp/DepartmentRota.fxml", button_departmentRota);
    }

    @FXML
    private void buttonRequestHolidaysOnAction(ActionEvent event) {
        loadFXML("/com.miguelangelvinas.rotaapp/RequestHolidays.fxml", button_requestHolidays);
    }

    @FXML
    private void buttonAccountOnAction(ActionEvent event) {
        loadFXML("/com.miguelangelvinas.rotaapp/Account.fxml", button_account);
    }

    private void loadFXML(String fxmlPath, Button sourceButton) {
        try {
            Parent pageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Scene pageScene = new Scene(pageParent);
            Stage stage = (Stage) sourceButton.getScene().getWindow();
            stage.setScene(pageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
}