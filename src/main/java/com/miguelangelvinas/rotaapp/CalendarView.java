package com.miguelangelvinas.rotaapp;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.miguelangelvinas.rotaapp.User;
import com.miguelangelvinas.rotaapp.RotaEvent;  // Update this import

public class CalendarView {

    private GridPane calendarGrid;
    private YearMonth currentYearMonth;
    private List<RotaEvent> events;  // Update this line
    private User currentUser;

    public CalendarView(User user) {
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

    public void addEvent(RotaEvent event) {  // Update this line
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
                DayCell dayCell = new DayCell();
                dayCell.setDate(date);

                if (date.getMonthValue() == monthValue) {
                    dayCell.setCurrentMonth(true);
                } else {
                    dayCell.setCurrentMonth(false);
                }

                // Add events for this day
                for (RotaEvent event : events) {  // Update this line
                    if (event.getDate().equals(date)) {
                        dayCell.addEvent(event);
                    }
                }

                calendarGrid.add(dayCell, j, i);

                date = date.plusDays(1);
            }
        }
    }

    public String getCurrentMonthYear() {
        return currentYearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    }

    private class DayCell extends VBox {
        private Label dateLabel;
        private VBox eventsBox;

        public DayCell() {
            dateLabel = new Label();
            eventsBox = new VBox(5);
            getChildren().addAll(dateLabel, eventsBox);
            setOnMouseClicked(e -> openDayViewPopup());
        }

        public void setDate(LocalDate date) {
            dateLabel.setText(String.valueOf(date.getDayOfMonth()));
        }

        public void setCurrentMonth(boolean isCurrentMonth) {
            if (isCurrentMonth) {
                dateLabel.setTextFill(Color.BLACK);
            } else {
                dateLabel.setTextFill(Color.GRAY);
            }
        }

        public void addEvent(RotaEvent event) {  // Update this line
            if (eventsBox.getChildren().size() < 3) {
                Label eventLabel = new Label(event.getShortSummary());
                eventLabel.setStyle(event.getColorStyle());
                eventsBox.getChildren().add(eventLabel);
            } else if (eventsBox.getChildren().size() == 3) {
                Label moreLabel = new Label("+1 more");
                eventsBox.getChildren().add(moreLabel);
            } else {
                Label moreLabel = (Label) eventsBox.getChildren().get(3);
                moreLabel.setText("+" + (eventsBox.getChildren().size() - 2) + " more");
            }
        }

        private void openDayViewPopup() {
            // Implement popup logic here
        }
    }
}