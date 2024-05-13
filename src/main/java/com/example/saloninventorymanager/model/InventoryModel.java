package com.example.saloninventorymanager.model;

import com.example.saloninventorymanager.DAO.InventoryDAO;
import com.example.saloninventorymanager.domain_objects.InventoryItem;
import javafx.collections.ObservableList;

public class InventoryModel {
    ObservableList<InventoryItem> inventoryList;

    public InventoryModel() {
        inventoryList = InventoryDAO.findInventory();
    }

    public Boolean deleteInventory(Integer inventoryID) {
        return InventoryDAO.deleteInventory(inventoryID);
    }

    public ObservableList<InventoryItem> getInventoryList() {
        return inventoryList;
    }

    public ObservableList<InventoryItem> refreshInventoryList() {
        inventoryList = InventoryDAO.findInventory();
        return this.getInventoryList();
    }


}
