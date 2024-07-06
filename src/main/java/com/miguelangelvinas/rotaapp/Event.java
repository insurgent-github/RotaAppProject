package com.miguelangelvinas.rotaapp;

import java.time.LocalDate;

public class Event
{
    private LocalDate date;
    private String description;
    private String assignedUser;

    public Event(LocalDate date, String description, String assignedUser)
    {
        this.date = date;
        this.description = description;
        this.assignedUser = assignedUser;
    }

    // Getters
    public LocalDate getDate()
    {
        return date;
    }

    public String getDescription()
    {
        return description;
    }

    public String getAssignedUser()
    {
        return assignedUser;
    }

    // Setters
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setAssignedUser(String assignedUser)
    {
        this.assignedUser = assignedUser;
    }

    @Override
    public String toString()
    {
        return "Event{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", assignedUser='" + assignedUser + '\'' +
                '}';
    }
}