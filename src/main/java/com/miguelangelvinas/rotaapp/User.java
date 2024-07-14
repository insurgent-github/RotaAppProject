package com.miguelangelvinas.rotaapp;

import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private boolean isAdministrator;

    public User(int id, String email, String password, String name, boolean isAdministrator) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isAdministrator = isAdministrator;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isAdministrator == user.isAdministrator &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, isAdministrator);
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}