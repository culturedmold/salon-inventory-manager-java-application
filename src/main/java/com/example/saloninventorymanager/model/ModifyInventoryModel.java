package com.example.saloninventorymanager.model;

import com.example.saloninventorymanager.DAO.InventoryDAO;
import com.example.saloninventorymanager.domain_objects.InventoryItem;

public class ModifyInventoryModel {

    public ModifyInventoryModel() {
    }

    public boolean updateInventory(InventoryItem item) {
        if (item.getProdQuantity() < 0 || item.getProdCost() < 0 || item.getProdRetail() < 0) {
            return false;
        } else {
            return InventoryDAO.updateInventory(item.getProdId(), item.getProdName(), item.getProdCost(), item.getProdRetail(), item.getProdQuantity());
        }
    }
}
