package com.example.saloninventorymanager.util;

import com.example.saloninventorymanager.view.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SceneSwitcher {
    public static void openNewView(View view, ActionEvent event, String title) {
        try {
            Scene scene = new Scene(view.getAnchorPane());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
