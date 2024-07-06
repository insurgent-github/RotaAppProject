package com.miguelangelvinas.rotaapp;

public class User {
    private int id;
    private String username;
    private String password;  // In a real application, this should be hashed
    private String email;
    private boolean isAdministrator;

    public User(int id, String username, String password, String email, boolean isAdministrator) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdministrator = isAdministrator;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
}