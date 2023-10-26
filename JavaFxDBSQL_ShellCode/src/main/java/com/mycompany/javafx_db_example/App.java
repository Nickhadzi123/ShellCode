package com.mycompany.javafx_db_example;

import com.mycompany.javafx_db_example.db.ConnDbOps;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    /* Got rid of the switch that allowed users to type into the terminal window,
     all the functionality has been integrated into the gui window, must close and
     reopen application to see any updated or added records
     */

    private static Scene scene;
    private static ConnDbOps cdbop;

    private Stage primaryStage;

    public void start(Stage primaryStage) throws Exception {
        cdbop = new ConnDbOps();
        cdbop.connectToDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
        this.primaryStage = primaryStage;
        Scene scene = new Scene(root, 600, 350);
        scene.getStylesheets().add("style.css");
        this.primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args);}
}
