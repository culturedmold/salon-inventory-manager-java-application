package com.example.saloninventorymanager.view;

import com.example.saloninventorymanager.controller.CreateInventoryViewController;
import com.example.saloninventorymanager.controller.InventoryViewController;
import com.example.saloninventorymanager.util.AlertResponse;
import com.example.saloninventorymanager.util.SceneSwitcher;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;

public class CreateInventoryView implements View {
    private final CreateInventoryViewController controller;
    private final AnchorPane anchorPane;
    private final TextField inventoryNameField;
    private final TextField inventoryCostField;
    private final TextField inventoryRetailField;
    private final TextField inventoryQuantityField;


    public CreateInventoryView() {
        controller = new CreateInventoryViewController();

        anchorPane = new AnchorPane();
        VBox mainVBox = new VBox(8.0);
        mainVBox.setPadding(new Insets(24.0));

        Label inventoryNameLabel = new Label("Item Name");
        inventoryNameField = new TextField();
        inventoryNameField.setPromptText("Item/Inventory Name");
        HBox inventoryNameHBox = new HBox(4.0);
        inventoryNameHBox.getChildren().addAll(inventoryNameLabel, inventoryNameField);

        Label inventoryCostLabel = new Label("Cost");
        inventoryCostField = new TextField();
        inventoryCostField.setPromptText("Cost (USD)");
        HBox inventoryCostHBox = new HBox(4.0);
        inventoryCostHBox.getChildren().addAll(inventoryCostLabel, inventoryCostField);

        Label inventoryRetailLabel = new Label("Retail Price");
        inventoryRetailField = new TextField();
        inventoryRetailField.setPromptText("Retail Price (USD)");
        HBox inventoryRetailHBox = new HBox(4.0);
        inventoryRetailHBox.getChildren().addAll(inventoryRetailLabel, inventoryRetailField);

        Label inventoryQuantityLabel = new Label("Item Quantity");
        inventoryQuantityField = new TextField();
        inventoryQuantityField.setPromptText("Quantity");
        HBox inventoryQuantityHBox = new HBox(4.0);
        inventoryQuantityHBox.getChildren().addAll(inventoryQuantityLabel, inventoryQuantityField);

        Text title = new Text("Add Inventory");
        title.setFont(new Font(24.0));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            try {
                String inventoryName = inventoryNameField.getText();
                Double inventoryCost = Double.valueOf(inventoryCostField.getText());
                Double inventoryRetail = Double.valueOf(inventoryRetailField.getText());
                Integer inventoryQuantity = Integer.valueOf(inventoryQuantityField.getText());

                AlertResponse alertResponse = controller.createInventory(inventoryName, inventoryCost, inventoryRetail, inventoryQuantity);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(alertResponse.getResponseTitle());
                alert.setContentText(alertResponse.getResponseText());
                alert.showAndWait();

                inventoryNameField.clear();
                inventoryCostField.clear();
                inventoryRetailField.clear();
                inventoryQuantityField.clear();

            } catch (Exception error) {
                error.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("There Was a Problem With Your Data");
                alert.setContentText("Please ensure cost, quantity, and retail fields only contain numeric characters and that all fields are filled out.");
                alert.showAndWait();

            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Your Changes Will Not Be Saved");
            alert.setContentText("Are you sure you want to cancel? Your changes will not be saved.");

            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                SceneSwitcher.openNewView(new InventoryView(new InventoryViewController()), e, "Inventory");
            }
        });

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(submitButton, cancelButton);

        anchorPane.getChildren().add(mainVBox);
        mainVBox.getChildren().addAll(title, inventoryNameHBox, inventoryCostHBox, inventoryRetailHBox, inventoryQuantityHBox, buttonBar);
    }

    public AnchorPane getAnchorPane() {
        return this.anchorPane;
    }
}
