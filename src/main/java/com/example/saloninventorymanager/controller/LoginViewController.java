package com.example.saloninventorymanager.controller;

import com.example.saloninventorymanager.model.UsersModel;

public class LoginViewController {
    public Boolean login(String username, String password) {
        return UsersModel.login(username, password);
    }
}
