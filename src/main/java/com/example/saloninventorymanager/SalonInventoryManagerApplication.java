package com.example.saloninventorymanager;

import com.example.saloninventorymanager.util.DatabaseAccess;
import com.example.saloninventorymanager.view.LoginView;
import com.example.saloninventorymanager.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SalonInventoryManagerApplication extends Application {
    @Override
    public void start(Stage stage) {
//        View entryView = new InventoryView(new InventoryViewController(new InventoryModel()));
        View entryView = new LoginView();
        Scene scene = new Scene(entryView.getAnchorPane());
        stage.setScene(scene);
        stage.setTitle("Inventory Manager");
        stage.show();

        System.out.println("Hello");
    }

    public static void main(String[] args) {
        DatabaseAccess.openConnection();
        launch();
        DatabaseAccess.closeConnection();
    }
}