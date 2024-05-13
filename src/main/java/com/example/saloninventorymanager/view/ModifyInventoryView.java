package com.example.saloninventorymanager.view;

import com.example.saloninventorymanager.controller.InventoryViewController;
import com.example.saloninventorymanager.domain_objects.InventoryItem;
import com.example.saloninventorymanager.util.SceneSwitcher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;

public class ModifyInventoryView implements View {
    public final InventoryItem selectedItem;

    private final AnchorPane anchorPane;


    public ModifyInventoryView(InventoryItem selectedItem) {
        this.selectedItem = selectedItem;

        // init UI components
        this.anchorPane = new AnchorPane();

        VBox mainVBox = new VBox(12.0);
        mainVBox.setPadding(new Insets(24.0));

        Text title = new Text("Modify Inventory");
        title.setFont(new Font(24.0));

        HBox productIdHBox = new HBox(8.0);
        HBox productNameHBox = new HBox(8.0);
        HBox productCostHBox = new HBox(8.0);
        HBox productRetailHBox = new HBox(8.0);
        HBox productQuantityHBox = new HBox(8.0);

        Label productIdLabel = new Label("Product/Item ID");
        Label productNameLabel = new Label("Product Name");
        Label productCostLabel = new Label("Cost");
        Label productRetailLabel = new Label("Retail Price");
        Label productQuantityLabel = new Label("Current Quantity");

        TextField productIdField = new TextField(String.valueOf(selectedItem.getProdId()));
        productIdField.setEditable(false);
        productIdField.setDisable(true);
        TextField productNameField = new TextField(selectedItem.getProdName());
        TextField productCostField = new TextField(selectedItem.getProdCostFormatted());
        TextField productRetailField = new TextField(selectedItem.getProdRetailFormatted());
        TextField productQuantityField = new TextField(String.valueOf(selectedItem.getProdQuantity()));

        ButtonBar buttonBar = new ButtonBar();
        Button cancelButton = new Button("Cancel");
        Button submitButton = new Button("Submit");

        // on submit action

        // on cancel action
        cancelButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Are you sure you want to cancel?");
            alert.setContentText("Your changes will not be saved.");

            Optional<ButtonType> confirmCancel = alert.showAndWait();

            if (confirmCancel.isPresent() && confirmCancel.get() == ButtonType.OK) {
                View view = new InventoryView(new InventoryViewController());
                SceneSwitcher.openNewView(view, e, "Inventory");
            }
        });

        // set buttons in buttonbar
        buttonBar.getButtons().addAll(cancelButton, submitButton);

        // put the view together
        anchorPane.getChildren().add(mainVBox);
        mainVBox.getChildren().addAll(title, productIdHBox, productNameHBox, productCostHBox, productRetailHBox, productQuantityHBox, buttonBar);

        productIdHBox.getChildren().addAll(productIdLabel, productIdField);
        productIdHBox.setAlignment(Pos.CENTER_LEFT);
        productNameHBox.getChildren().addAll(productNameLabel, productNameField);
        productNameHBox.setAlignment(Pos.CENTER_LEFT);
        productCostHBox.getChildren().addAll(productCostLabel, productCostField);
        productCostHBox.setAlignment(Pos.CENTER_LEFT);
        productRetailHBox.getChildren().addAll(productRetailLabel, productRetailField);
        productRetailHBox.setAlignment(Pos.CENTER_LEFT);
        productQuantityHBox.getChildren().addAll(productQuantityLabel, productQuantityField);
        productQuantityHBox.setAlignment(Pos.CENTER_LEFT);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}
