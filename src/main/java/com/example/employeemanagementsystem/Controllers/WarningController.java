package com.example.employeemanagementsystem.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class WarningController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!heading.isEmpty()) headingLabel.setText(heading);
        if(!description.isEmpty()) descriptionLabel.setText(description);
        else {
            button.setText("Ok");
            button.setPrefWidth(500);
            cancel.setVisible(false);
        }
    }
    @FXML
    private Label headingLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button button;
    @FXML
    private Button cancel;
    @FXML
    private void agreeAction() {
        isAgree = true;
        currentWindow.close();
    }
    @FXML
    private void cancelAction() {
        isAgree = false;
        currentWindow.close();
    }
    public static String heading;
    public static String description;
    public static boolean isAgree;
    public static Stage currentWindow;
}