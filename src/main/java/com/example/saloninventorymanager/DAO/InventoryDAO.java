package com.example.saloninventorymanager.DAO;

import com.example.saloninventorymanager.domain_objects.InventoryItem;
import com.example.saloninventorymanager.util.DatabaseAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAO {
    private static final String findInventoryStatement = "SELECT * FROM products";
    private static final String createInventoryStatement = "INSERT INTO products (prod_name, prod_cost, prod_retail, prod_qty) VALUES (?, ?, ?, ?)";
    private static final String updateInventoryStatement = "UPDATE products SET prod_name = ?, prod_cost = ?, prod_retail = ?, prod_qty = ? WHERE prod_id = ?";
    private static final String deleteInventoryStatement = "DELETE FROM products WHERE prod_id = ?";

    public static ObservableList<InventoryItem> findInventory() {
        ObservableList<InventoryItem> inventoryList = FXCollections.observableArrayList();

        try (PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(findInventoryStatement)) {
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Integer prodId = rs.getInt("prod_Id");
                String prodName = rs.getString("prod_name");
                Double prodCost = rs.getDouble("prod_cost");
                Double prodRetail = rs.getDouble("prod_retail");
                Integer prodQuantity = rs.getInt("prod_qty");

                InventoryItem item = new InventoryItem(prodId, prodName, prodCost, prodRetail, prodQuantity);
                inventoryList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryList;
    }

    public static boolean createInventory(String name, Double cost, Double retail, Integer quantity) {
        try (PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(createInventoryStatement)) {

            ps.setString(1, name);
            ps.setDouble(2, cost);
            ps.setDouble(3, retail);
            ps.setInt(4, quantity);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateInventory(Integer productId, String productName, Double productCost, Double productRetail, Integer productQuantity) {
        try (PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(updateInventoryStatement)) {
            ps.setString(1, productName);
            ps.setDouble(2, productCost);
            ps.setDouble(3, productRetail);
            ps.setInt(4, productQuantity);
            ps.setInt(5, productId);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteInventory(Integer productId) {
        try (PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(deleteInventoryStatement)) {

            ps.setInt(1, productId);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
