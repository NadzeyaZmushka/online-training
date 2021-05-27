package com.epam.jwd.training.entity;

public class Admin extends Account {

    public Admin() {
    }

    public Admin(String name, String surname, String login, String password) {
        super(name, surname, login, password);
    }

    public Admin(Long id, String name, String surname, String login, String password) {
        super(id, name, surname, login, password);
    }

    @Override
    public String toString() {
        return "Admin " + super.toString();
    }

}
