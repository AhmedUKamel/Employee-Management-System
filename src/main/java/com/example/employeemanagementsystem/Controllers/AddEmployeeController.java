package com.example.employeemanagementsystem.Controllers;
import com.example.employeemanagementsystem.Employee;
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
public class AddEmployeeController implements Initializable {
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
            Employee employee = getEmployee();
            if(MainController.isArrayList && MainController.arrayList != null && employee != null) {
                if (!MainController.arrayList.addEmployee(employee))
                    System.out.println("USER IS NOT BEEN ADDED");
            } else if (!MainController.isArrayList && MainController.linkedList != null && employee != null)
                if (!MainController.linkedList.addEmployee(employee))
                    System.out.println("USER IS NOT BEEN ADDED");
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
    public Employee getEmployee() {
        try {
            Employee employee = new Employee();
            employee.setId(Long.parseLong(idTextField.getText().strip()));
            employee.setName(nameTextField.getText().strip());
            employee.setSalary(Double.parseDouble(salaryTextField.getText().strip()));
            employee.setDepartment(departmentTextField.getText().strip());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            employee.setDob(formatter.format(datePicker.getValue()));
            return employee;
        } catch (Exception e) {
            System.out.println("ERROR GETTING EMPLOYEE FROM TEXT FIELDS");
            System.out.println(e.getMessage());
            return null;
        }
    }
    private void setup() {
        label.setText("ADD EMPLOYEE WINDOW");
        button.setText("Add");
        idTextField.setPromptText("Enter ID");
        nameTextField.setPromptText("Enter name");
        salaryTextField.setPromptText("Enter salary");
        departmentTextField.setPromptText("Enter department");
        datePicker.setPromptText("Select birth date");
    }
}