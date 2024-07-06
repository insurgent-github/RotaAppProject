package com.miguelangelvinas.rotaapp;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MonthlyRotaController implements Initializable
{
    @FXML
    private StackPane calendarViewPlaceholder;
    private User currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CalendarView calendarView = new CalendarView(currentUser);
        calendarViewPlaceholder.getChildren().add(calendarView.getView());
    }
}