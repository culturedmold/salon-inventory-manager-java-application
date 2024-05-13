package com.example.saloninventorymanager.controller;

import com.example.saloninventorymanager.domain_objects.InventoryItem;
import com.example.saloninventorymanager.model.InventoryModel;
import javafx.collections.ObservableList;

public class InventoryViewController {
    final InventoryModel inventoryModel;

    public InventoryViewController() {

        this.inventoryModel = new InventoryModel();
    }


    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    public boolean deleteInventory(Integer productId) {
        return inventoryModel.deleteInventory(productId);
    }

    public ObservableList<InventoryItem> refreshInventory() {
        return inventoryModel.refreshInventoryList();
    }

    public ObservableList<InventoryItem> searchInventory(String s) {
        if (s.isEmpty()) {
            return inventoryModel.getInventoryList();
        } else {
            return inventoryModel.searchInventory(s);
        }
    }
}
