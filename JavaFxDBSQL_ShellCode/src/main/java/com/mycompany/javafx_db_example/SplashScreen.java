package com.mycompany.javafx_db_example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SplashScreen {

    @FXML
    Button loginButton;

    public void switchScene() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("db_interface_gui.fxml"));

        Stage window = (Stage) loginButton.getScene().getWindow();
        window.setScene(new Scene(root,850,520));
    }

}