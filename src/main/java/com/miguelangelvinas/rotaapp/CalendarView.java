package com.miguelangelvinas.rotaapp;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarView {

    private GridPane calendarGrid;
    private YearMonth currentYearMonth;
    private List<Event> events;
    private User currentUser;
    private boolean isAdministrator;

    public CalendarView(User user) {
        this.isAdministrator = isAdministrator;
        this.currentUser = user;
        currentYearMonth = YearMonth.now();
        calendarGrid = new GridPane();
        calendarGrid.setHgap(5);
        calendarGrid.setVgap(5);
        events = new ArrayList<>();
        updateCalendar();
    }

    public Node getView() {
        return calendarGrid;
    }

    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar();
    }

    public void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar();
    }

    public void addEvent(Event event) {
        if (currentUser.isAdministrator()) {
            events.add(event);
            updateCalendar();
        }
    }

    private void updateCalendar() {
        calendarGrid.getChildren().clear();

        String[] dayNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < 7; i++) {
            calendarGrid.add(new Label(dayNames[i]), i, 0);
        }

        LocalDate date = currentYearMonth.atDay(1);
        int monthValue = date.getMonthValue();

        int dayOfWeekValue = date.getDayOfWeek().getValue();
        date = date.minusDays(dayOfWeekValue - 1);

        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                VBox dayBox = new VBox(5);
                Rectangle background = new Rectangle(30, 30);
                background.setFill(Color.LIGHTGRAY);
                background.setStroke(Color.BLACK);

                Text dayNumber = new Text(String.valueOf(date.getDayOfMonth()));

                if (date.getMonthValue() == monthValue) {
                    dayNumber.setFill(Color.BLACK);
                } else {
                    dayNumber.setFill(Color.GRAY);
                }

                dayBox.getChildren().addAll(background, dayNumber);

                // Add events for this day
                for (Event event : events) {
                    if (event.getDate().equals(date)) {
                        Text eventText = new Text(event.getDescription());
                        dayBox.getChildren().add(eventText);
                    }
                }

                calendarGrid.add(dayBox, j, i);

                date = date.plusDays(1);
            }
        }
    }

    public String getCurrentMonthYear() {
        return currentYearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    }
}