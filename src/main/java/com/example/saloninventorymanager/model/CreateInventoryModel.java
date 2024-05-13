package com.example.saloninventorymanager.model;

import com.example.saloninventorymanager.DAO.InventoryDAO;
import com.example.saloninventorymanager.domain_objects.InventoryItem;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class CreateInventoryModel {
    final HashMap<String, InventoryItem> inventoryMap;
    public CreateInventoryModel() {

        inventoryMap = new HashMap<>();
        ObservableList<InventoryItem> inventoryList = InventoryDAO.findInventory();

        if (inventoryList.size() != 0) {
            for (InventoryItem item : inventoryList) {
                inventoryMap.put(item.getProdName(), item);
            }
        }
    }

    public boolean createInventory(String itemName, Double cost, Double retail, Integer quantity) {
        if (inventoryMap.containsKey(itemName)) {
            return false;
        } else {
            return InventoryDAO.createInventory(itemName, cost, retail, quantity);
        }
    }
}
