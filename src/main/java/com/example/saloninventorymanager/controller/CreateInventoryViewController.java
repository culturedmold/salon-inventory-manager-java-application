package com.example.saloninventorymanager.controller;

import com.example.saloninventorymanager.model.CreateInventoryModel;
import com.example.saloninventorymanager.util.AlertResponse;

public class CreateInventoryViewController {
    final CreateInventoryModel model;


    public CreateInventoryViewController() {
        this.model = new CreateInventoryModel();
    }

    public AlertResponse createInventory(String name, Double cost, Double retail, Integer quantity) {
        AlertResponse alertResponse;

        if (model.createInventory(name, cost, retail, quantity)) {
            alertResponse = new AlertResponse("success", "Success", "Item successfully added to inventory.");
        } else {
            alertResponse = new AlertResponse("error", "There Was a Problem", "There was a problem adding this item to inventory. Please try again.");
        }

        return alertResponse;
    }
}
