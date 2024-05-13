package com.example.saloninventorymanager.view;

import com.example.saloninventorymanager.controller.InventoryViewController;
import com.example.saloninventorymanager.domain_objects.InventoryItem;
import com.example.saloninventorymanager.util.SceneSwitcher;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class InventoryView implements View {
    private final AnchorPane inventoryAnchorPane;
    private final TableView<InventoryItem> inventoryTable;


    public InventoryView(InventoryViewController controller) {

        inventoryAnchorPane = new AnchorPane();
        VBox mainVBox = new VBox(12.0);
        mainVBox.setPadding(new Insets(24.0));

        // title
        Text title = new Text("Inventory");
        title.setFont(new Font(24));

        // init tableview and set column properties
        inventoryTable = new TableView<>();
        inventoryTable.setItems(controller.getInventoryModel().getInventoryList());

        // init columns
        TableColumn<InventoryItem, Integer> productIdColumn = new TableColumn<>();
        TableColumn<InventoryItem, String> productNameColumn = new TableColumn<>();
        TableColumn<InventoryItem, String> productCostColumn = new TableColumn<>();
        TableColumn<InventoryItem, String> productRetailColumn = new TableColumn<>();
        TableColumn<InventoryItem, Integer> productQuantityColumn = new TableColumn<>();

        // set column values
        productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdName()));
        productNameColumn.setText("Name");
        productIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProdId()).asObject());
        productIdColumn.setText("ID");
        productCostColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdCostFormatted()));
        productCostColumn.setText("Cost");
        productRetailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdRetailFormatted()));
        productRetailColumn.setText("Retail Price");
        productQuantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProdQuantity()).asObject());
        productQuantityColumn.setText("Quantity");

        // set columns on table
        inventoryTable.getColumns().setAll(productIdColumn, productNameColumn, productCostColumn, productRetailColumn, productQuantityColumn);

        // controls and buttons
        ButtonBar buttonBar = new ButtonBar();

        Button addInventoryButton = new Button("Add");
        addInventoryButton.setOnAction(e -> {
            View createInventoryView = new CreateInventoryView();
            SceneSwitcher.openNewView(createInventoryView, e, "Add Inventory");
        });

        Button modifyInventoryButton = new Button("Edit");
        modifyInventoryButton.setOnAction(e -> {
            InventoryItem selectedItem = inventoryTable.getSelectionModel().getSelectedItem();

            if (!(selectedItem == null)) {
                View modifyInventoryView = new ModifyInventoryView(selectedItem);
                SceneSwitcher.openNewView(modifyInventoryView, e, "Modify Inventory");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Item Selected");
                alert.setContentText("No item selected. Please select an item and try again.");
                alert.showAndWait();
            }
        });

        Button deleteInventoryButton = new Button("Delete");
        deleteInventoryButton.setOnAction(e -> {
            Integer productId = inventoryTable.getSelectionModel().getSelectedItem().getProdId();
            Alert alert;
            if (!(productId == null)) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Are You Sure?");
                alert.setContentText("Are you sure you want to delete this item from inventory? This action cannot be undone.");

                Optional<ButtonType> confirmDelete = alert.showAndWait();
                if (confirmDelete.isPresent() && confirmDelete.get() == ButtonType.OK) {
                    boolean deleteInventoryResult = controller.deleteInventory(productId);
                    inventoryTable.setItems(controller.refreshInventory());

                    if (!deleteInventoryResult) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Could Not Delete Item");
                        alert.setContentText("There was a problem trying to delete this item. Please try again.");
                        alert.showAndWait();
                    }
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Item Selected");
                alert.setContentText("Please select an inventory item from the table and try again.");
                alert.showAndWait();
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            Stage stage = (Stage) this.getAnchorPane().getScene().getWindow();
            stage.close();
        });

        Button refreshTableButton = new Button("Refresh");
        refreshTableButton.setOnAction(e -> inventoryTable.refresh());

        buttonBar.getButtons().addAll(addInventoryButton, modifyInventoryButton, deleteInventoryButton, refreshTableButton, exitButton);

        // put the view together
        inventoryAnchorPane.getChildren().add(mainVBox);
        mainVBox.getChildren().addAll(title, inventoryTable, buttonBar);


    }

    public AnchorPane getAnchorPane() {
        return inventoryAnchorPane;
    }

}
