package com.example.saloninventorymanager.view;

import com.example.saloninventorymanager.controller.CreateAccountController;
import com.example.saloninventorymanager.util.AlertResponse;
import com.example.saloninventorymanager.util.SceneSwitcher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateAccountView implements View {
    private final AnchorPane mainAnchorPane;
    private final TextField userNameField;
    private final PasswordField passwordField;
    private final PasswordField confirmPasswordField;
    private final TextField firstNameField;
    private final TextField lastNameField;

    public CreateAccountView(CreateAccountController controller) {

        // initialize labels
        Label userNameLabel = new Label("Username");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label passwordLabel = new Label("Password");
        Label confirmPasswordLabel = new Label("Confirm Password");


        // initialize fields
        userNameField = new TextField();
        userNameField.setPromptText("Username");
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        passwordField = new PasswordField();
        confirmPasswordField = new PasswordField();


        // initialize buttons
        Button submitButton = new Button("Create Account");
        submitButton.setDefaultButton(true);
        submitButton.setOnAction(e -> {
            String username = userNameField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            AlertResponse result = controller.submitCreateAccount(firstName, lastName, username, password, confirmPassword);

            Alert alert;
            if (result.getResponseType().equals("error")) {
                alert = new Alert(Alert.AlertType.ERROR);
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
            }
            alert.setTitle(result.getResponseTitle());
            alert.setContentText(result.getResponseText());
            alert.showAndWait();

            SceneSwitcher.openNewView(new LoginView(), e, "Login");
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);


        // configure HBoxes
        HBox usernameHBox = new HBox(8.0);
        usernameHBox.setAlignment(Pos.CENTER_LEFT);
        usernameHBox.getChildren().add(userNameLabel);
        usernameHBox.getChildren().add(userNameField);

        HBox firstNameHBox = new HBox(8.0);
        firstNameHBox.setAlignment(Pos.CENTER_LEFT);
        firstNameHBox.getChildren().add(firstNameLabel);
        firstNameHBox.getChildren().add(firstNameField);

        HBox lastNameHBox = new HBox(8.0);
        lastNameHBox.setAlignment(Pos.CENTER_LEFT);
        lastNameHBox.getChildren().add(lastNameLabel);
        lastNameHBox.getChildren().add(lastNameField);

        HBox passwordHBox = new HBox(8.0);
        passwordHBox.setAlignment(Pos.CENTER_LEFT);
        passwordHBox.getChildren().add(passwordLabel);
        passwordHBox.getChildren().add(passwordField);

        HBox confirmPasswordHBox = new HBox(8.0);
        confirmPasswordHBox.setAlignment(Pos.CENTER_LEFT);
        confirmPasswordHBox.getChildren().add(confirmPasswordLabel);
        confirmPasswordHBox.getChildren().add(confirmPasswordField);

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(cancelButton, submitButton);


        // set VBox
        VBox mainVBox = new VBox(12.0);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(24.0));
        mainVBox.getChildren().addAll(usernameHBox, firstNameHBox, lastNameHBox, passwordHBox, confirmPasswordHBox, buttonBar);

        // Create and configure Pane
        mainAnchorPane = new AnchorPane();
        mainAnchorPane.getChildren().add(mainVBox);
    }

    public AnchorPane getAnchorPane() {
        return mainAnchorPane;
    }

}
