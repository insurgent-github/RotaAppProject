package com.miguelangelvinas.rotaapp;

import java.time.LocalDate;
import javafx.scene.paint.Color;

public class RotaEvent {
    private LocalDate date;
    private String description;
    private String assignedUser;
    private Color color;

    public RotaEvent(LocalDate date, String description, String assignedUser) {
        this.date = date;
        this.description = description;
        this.assignedUser = assignedUser;
        this.color = generateRandomColor();
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public Color getColor() {
        return color;
    }

    // Setters
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getShortSummary() {
        return description.length() > 20 ? description.substring(0, 17) + "..." : description;
    }

    public String getColorStyle() {
        return String.format("-fx-background-color: rgb(%d, %d, %d);",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private Color generateRandomColor() {
        return Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", assignedUser='" + assignedUser + '\'' +
                '}';
    }
}