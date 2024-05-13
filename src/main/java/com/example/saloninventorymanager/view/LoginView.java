package com.example.saloninventorymanager.view;

import com.example.saloninventorymanager.controller.CreateAccountController;
import com.example.saloninventorymanager.controller.InventoryViewController;
import com.example.saloninventorymanager.controller.LoginViewController;
import com.example.saloninventorymanager.model.UsersModel;
import com.example.saloninventorymanager.util.SceneSwitcher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView implements View {
    private final LoginViewController controller;
    private final AnchorPane anchorPane;

    final TextField usernameTextField;
    final PasswordField passwordField;


    public LoginView() {
        this.controller = new LoginViewController();
        this.anchorPane = new AnchorPane();
        VBox mainVBox = new VBox(4.0);
        mainVBox.setPadding(new Insets(24.0));

        Text title = new Text("Login");
        title.setFont(new Font(24.0));

        HBox usernameHBox = new HBox(8.0);
        usernameHBox.setAlignment(Pos.CENTER_LEFT);
        HBox passwordHBox = new HBox(8.0);
        passwordHBox.setAlignment(Pos.CENTER_LEFT);

        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        this.usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");

        this.passwordField = new PasswordField();

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            Stage stage = (Stage) this.getAnchorPane().getScene().getWindow();
            stage.close();
        });

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {

            String username = usernameTextField.getText();
            String password = passwordField.getText();
            Boolean loginResult;

            if ((username != null) && (password != null)) {
                loginResult = controller.login(username, password);
                if (loginResult) {
                    SceneSwitcher.openNewView(new InventoryView(new InventoryViewController()), e, "Inventory");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Could not Log In");
                    alert.setContentText("Username or password is incorrect. Please try again.");
                    alert.showAndWait();
                }
            }
        });

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(e -> SceneSwitcher.openNewView(new CreateAccountView(new CreateAccountController(new UsersModel())), e, "Create Account"));

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(loginButton, createAccountButton);

        ButtonBar buttonBar2 = new ButtonBar();
        buttonBar2.getButtons().add(exitButton);

        // put view together
        anchorPane.getChildren().add(mainVBox);
        usernameHBox.getChildren().addAll(usernameLabel, usernameTextField);
        passwordHBox.getChildren().addAll(passwordLabel, passwordField);
        mainVBox.getChildren().addAll(title, usernameHBox, passwordHBox, buttonBar, buttonBar2);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}
