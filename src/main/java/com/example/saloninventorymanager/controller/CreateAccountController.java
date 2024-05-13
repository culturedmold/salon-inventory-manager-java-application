package com.example.saloninventorymanager.controller;

import com.example.saloninventorymanager.model.UsersModel;
import com.example.saloninventorymanager.util.AlertResponse;

public class CreateAccountController {

    public final UsersModel model;

    public CreateAccountController(UsersModel model) {
        this.model = model;
    }

    // Will return an alert response based on the result of model.createUser()
    public AlertResponse submitCreateAccount(String firstName, String lastName, String username, String password, String confirmPassword) {

        return model.createUser(firstName, lastName, username, password, confirmPassword);
    }
}
