package com.example.saloninventorymanager.controller;

import com.example.saloninventorymanager.domain_objects.InventoryItem;
import com.example.saloninventorymanager.model.ModifyInventoryModel;
import com.example.saloninventorymanager.util.AlertResponse;

public class ModifyInventoryViewController {
    ModifyInventoryModel model;

    public ModifyInventoryViewController() {
        this.model = new ModifyInventoryModel();
    }

    public AlertResponse submitModifyInventory(InventoryItem item) {
        AlertResponse response;

        if (model.updateInventory(item)) {
            return response = new AlertResponse("success", "Inventory Updated Successfully", "Inventory was successfully updated.");
        } else {
            return response = new AlertResponse("error", "There Was an Error", "There was an error trying to update inventory. Please ensure name is unique and all fields contain appropriate data. Quantity, cost, and retail fields cannot be negative.");
        }
    }
}
