package com.example.employeemanagementsystem;
import com.example.employeemanagementsystem.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage window) throws IOException {
        try {
            MainController.currentWindow = window;
            FXMLLoader design = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
            window.setTitle("Employee Management System Main Page");
            window.setScene(new Scene(design.load(), 800, 515));
            window.show();
        } catch (Exception e) {
            System.out.println("ERROR LOADING FXML FILE");
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}