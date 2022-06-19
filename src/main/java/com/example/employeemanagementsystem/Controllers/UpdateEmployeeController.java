package com.example.employeemanagementsystem.Controllers;
import com.example.employeemanagementsystem.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
public class UpdateEmployeeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
    }
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField idTextField, nameTextField, salaryTextField, departmentTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private void acceptAction() {
        if( !idTextField.getText().isEmpty() &&
            !nameTextField.getText().isEmpty() &&
            !salaryTextField.getText().isEmpty() &&
            !departmentTextField.getText().isEmpty() &&
            datePicker.getValue() != null) {
            MainController.employee.setId(Long.parseLong(idTextField.getText().strip()));
            MainController.employee.setName(nameTextField.getText().strip());
            MainController.employee.setSalary(Double.parseDouble(salaryTextField.getText().strip()));
            MainController.employee.setDepartment(departmentTextField.getText().strip());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            MainController.employee.setDob(formatter.format(datePicker.getValue()));
            cancelAction();
        }
    }
    @FXML
    private void cancelAction() {
        try {
            FXMLLoader design = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
            MainController.currentWindow.setTitle("Employee Management System Main Page");
            MainController.currentWindow.setScene(new Scene(design.load()));
        } catch (Exception e) {
            System.out.println("ERROR LOADING MAIN VIEW");
            System.out.println(e.getMessage());
        }
    }
    private void setup() {
        label.setText("UPDATE EMPLOYEE WINDOW");
        button.setText("Update");
        idTextField.setText(MainController.employee.getId().toString());
        nameTextField.setText(MainController.employee.getName());
        salaryTextField.setText(MainController.employee.getSalary().toString());
        departmentTextField.setText(MainController.employee.getDepartment());
        datePicker.setPromptText(MainController.employee.getDob());
    }
}