package com.example.employeemanagementsystem.Controllers;
import com.example.employeemanagementsystem.Employee;
import com.example.employeemanagementsystem.EmployeeArrayList;
import com.example.employeemanagementsystem.EmployeeLinkedList;
import com.example.employeemanagementsystem.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableView();
        setupComboBoxes();
        emptySearchTextFieldAction();
        if(isArrayList) arrayListAction();
        else linkedListAction();
    }
    // ATTRIBUTES
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn salaryColumn;
    @FXML
    private TableColumn departmentColumn;
    @FXML
    private TableColumn dobColumn;
    @FXML
    private ComboBox nameSort;
    @FXML
    private ComboBox idSort;
    @FXML
    private Label label;
    public static boolean isArrayList;
    private static String pathArrayList;
    private static String pathLinkedList;
    public static EmployeeLinkedList linkedList;
    public static EmployeeArrayList arrayList;
    public static Stage currentWindow;
    public static Employee employee;
    // METHODS
    @FXML
    private void chooseFileAction() {
        try {
            if (isArrayList) {
                if (arrayList != null)
                    if (!warningWindow("BY CHOOSING FILE YOU WILL OVERRIDE CURRENT DATA", "")) return;
                Stage window = new Stage();
                FileChooser chooser = new FileChooser();
                File file = chooser.showOpenDialog(window);
                pathArrayList = file.toString();
                Scanner reader = new Scanner(file);
                arrayList = new EmployeeArrayList();
                while (reader.hasNextLine()) {
                    Employee employee = Employee.toEmployee(reader.nextLine());
                    if(employee != null) if(!arrayList.addEmployee(employee)) System.out.println("EMPLOYEE IS NOT BEEN ADDED");
                }
            } else {
                if (linkedList != null)
                    if (!warningWindow("BY CHOOSING FILE YOU WILL OVERRIDE CURRENT DATA", "")) return;
                Stage window = new Stage();
                FileChooser chooser = new FileChooser();
                File file = chooser.showOpenDialog(window);
                pathLinkedList = file.toString();
                Scanner reader = new Scanner(file);
                linkedList = new EmployeeLinkedList();
                while (reader.hasNextLine()) {
                    Employee employee = Employee.toEmployee(reader.nextLine());
                    if(employee != null) if(!linkedList.addEmployee(employee)) System.out.println("EMPLOYEE IS NOT BEEN ADDED");
                }
            } refreshTableView();
        } catch (Exception e) {
            System.out.println("ERROR CHOOSING FILE");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void saveFileAction() {
        try {
            if(isArrayList) {
                if (arrayList != null) {
                    File file = new File(pathArrayList);
                    FileWriter writer = new FileWriter(file);
                    for (int index = 0; index < arrayList.length(); index++)
                        writer.write(arrayList.get(index).toLine() + "\n");
                    writer.close();
                } else if(warningWindow("NO DATA TO SAVE", "You may create new file"))
                    newFileAction();
            } else {
                if(linkedList != null) {
                    File file = new File(pathLinkedList);
                    FileWriter writer = new FileWriter(file);
                    for (int index = 0; index < linkedList.length(); index++)
                        writer.write(linkedList.get(index).toLine() + "\n");
                    writer.close();
                } else if(warningWindow("NO DATA TO SAVE", "You may create new file"))
                    newFileAction();
            }
        } catch (Exception e) {
            System.out.println("ERROR SAVING FILE");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void saveAsAction() {}
    @FXML
    private void newFileAction() {}
    @FXML
    private void linkedListAction() {
        isArrayList = false;
        label.setText("Linked List");
        refreshTableView();
    }
    @FXML
    private void arrayListAction() {
        isArrayList = true;
        label.setText("Array List");
        refreshTableView();
    }
    @FXML
    private void linearSearchAction() {
        if(isArrayList && arrayList != null) {
            if(!searchTextField.getText().isEmpty()) {
                tableView.getItems().clear();
                Employee result = arrayList.linearSearch(searchTextField.getText().strip());
                if(result != null) tableView.getItems().add(result);
            } else warningWindow("NO DATA TO SEARCH BY", "");
        } else if (!isArrayList && linkedList != null) {
            if(!searchTextField.getText().isEmpty()) {
                tableView.getItems().clear();
                Employee result = linkedList.linearSearch(searchTextField.getText().strip());
                if(result != null) tableView.getItems().add(result);
            } else warningWindow("NO DATA TO SEARCH BY", "");
        } else if (warningWindow("NO DATA TO SEARCH IN", "You may choose file"))
            chooseFileAction();
    }
    @FXML
    private void binarySearchAction() {
        if(isArrayList && arrayList != null) {
            if(!searchTextField.getText().isEmpty()) {
                tableView.getItems().clear();
                Employee result = arrayList.binarySearch(searchTextField.getText().strip());
                if(result != null) tableView.getItems().add(result);
            } else warningWindow("NO DATA TO SEARCH BY", "");
        } else if(!isArrayList && linkedList != null) {
            if(!searchTextField.getText().isEmpty()) {
                tableView.getItems().clear();
                Employee result = linkedList.binarySearch(searchTextField.getText().strip());
                if(result != null) tableView.getItems().add(result);
            } else warningWindow("NO DATA TO SEARCH BY", "");
        } else if (warningWindow("NO DATA TO SEARCH IN", "You may choose file"))
            chooseFileAction();
    }
    @FXML
    private void addEmployeeAction() {
        try {
            if(isArrayList && arrayList != null || !isArrayList && linkedList != null) {
                FXMLLoader design = new FXMLLoader(MainApplication.class.getResource("addAndModify-view.fxml"));
                design.setController(new AddEmployeeController());
                currentWindow.setTitle("Employee Management System Add Employee Page");
                currentWindow.setScene(new Scene(design.load()));
                refreshTableView();
                if (isArrayList) arrayListAction();
                else linkedListAction();
            } else if (warningWindow("NO DATA TO ADD INTO",
                    "You must choose file or create new one, click agree to choose"))
                chooseFileAction();
        } catch (Exception e) {
            System.out.println("ERROR LOADING ADD EMPLOYEE WINDOW");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void delEmployeeAction() {
        try {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                if (isArrayList) {
                    if (!arrayList.delEmployee((Employee) tableView.getSelectionModel().getSelectedItem()))
                        System.out.println("EMPLOYEE IS NOT BEEN DELETED");
                    else refreshTableView();
                } else {
                    if (!linkedList.delEmployee((Employee) tableView.getSelectionModel().getSelectedItem()))
                        System.out.println("EMPLOYEE IS NOT BEEN DELETED");
                    else refreshTableView();
                }
            } else if(isArrayList && arrayList == null || !isArrayList && linkedList == null) {
                if(warningWindow("NO DATA TO DELETE FROM", "You may choose file or create new one, click agree to choose"))
                    chooseFileAction();
            } else warningWindow("NO SELECTED ROW TO DELETE","");
        } catch (Exception e) {
            System.out.println("ERROR DELETING EMPLOYEE");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void updateEmployeeAction() {
        try {
            employee = (Employee) tableView.getSelectionModel().getSelectedItem();
            if (employee != null) {
                FXMLLoader design = new FXMLLoader(MainApplication.class.getResource("addAndModify-view.fxml"));
                design.setController(new UpdateEmployeeController());
                currentWindow.setTitle("Employee Management System Update Employee Page");
                currentWindow.setScene(new Scene(design.load()));
                refreshTableView();
                if (isArrayList) arrayListAction();
                else linkedListAction();
            } else if(isArrayList && arrayList == null || !isArrayList && linkedList == null) {
                if(warningWindow("NO DATA TO UPDATE INTO", "You may choose file or create new one, click agree to choose"))
                    chooseFileAction();
            } else warningWindow("NO SELECTED ROW TO UPDATE","");
        } catch (Exception e) {
            System.out.println("ERROR LOADING UPDATE WINDOW");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void nameSortAction() {
        if(isArrayList && arrayList != null) {
            switch(nameSort.getSelectionModel().getSelectedIndex()) {
                case 0:
                    arrayList.bubbleSortByName();
                    break;
                case 1:
                    arrayList.insertionSortByName();
                    break;
                case 2:
                    arrayList.selectionSortByName();
                    break;
                case 3:
                    arrayList.mergeSortByName(0, arrayList.length() - 1);
                    break;
                case 4:
                    arrayList.heapSortByName();
                    break;
            }
        } else if (!isArrayList && linkedList != null) {
            switch(nameSort.getSelectionModel().getSelectedIndex()) {
                case 0:
                    linkedList.bubbleSortByName();
                    break;
                case 1:
                    linkedList.insertionSortByName();
                    break;
                case 2:
                    linkedList.selectionSortByName();
                    break;
                case 3:
                    linkedList.setHead(linkedList.mergeSortByName(linkedList.getHead()));
                    break;
                case 4:
                    linkedList.heapSortByName();
                    break;
            }
        } else if (warningWindow("NO DATA TO SORT",
                "You must choose file or create new one, click agree to choose"))
            chooseFileAction();
        refreshTableView();
    }
    @FXML
    private void idSortAction() {
        if(isArrayList && arrayList != null) {
            switch(idSort.getSelectionModel().getSelectedIndex()) {
                case 0:
                    arrayList.bubbleSortById();
                    break;
                case 1:
                    arrayList.insertionSortById();
                    break;
                case 2:
                    arrayList.selectionSortById();
                    break;
                case 3:
                    arrayList.mergeSortById(0, arrayList.length() - 1);
                    break;
                case 4:
                    arrayList.heapSortById();
                    break;
            }
        } else if (!isArrayList && linkedList != null) {
            switch(idSort.getSelectionModel().getSelectedIndex()) {
                case 0:
                    linkedList.bubbleSortById();
                    break;
                case 1:
                    linkedList.insertionSortById();
                    break;
                case 2:
                    linkedList.selectionSortById();
                    break;
                case 3:
                    linkedList.setHead(linkedList.mergeSortById(linkedList.getHead()));
                    break;
                case 4:
                    linkedList.heapSortById();
                    break;
            }
        } else if (warningWindow("NO DATA TO SORT",
                "You must choose file or create new one, click agree to choose"))
            chooseFileAction();
        refreshTableView();
    }
    @FXML
    private void reverseAction() {
        if(isArrayList && arrayList != null) {
            arrayList.reverse();
        } else if (!isArrayList && linkedList != null) {
            linkedList.reverse();
        } else if (warningWindow("NO DATA TO REVERSE",
                "You must choose file or create new one, click agree to choose"))
            chooseFileAction();
        refreshTableView();
    }
    @FXML
    private void clearAction() {
        if(isArrayList && arrayList != null) {
            arrayList.clear();
        } else if (!isArrayList && linkedList != null) {
            linkedList.clear();
        } else if (warningWindow("NO DATA TO CLEAR",
                "You must choose file or create new one, click agree to choose"))
            chooseFileAction();
        refreshTableView();
        refreshTableView();
    }
    private void setupTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("dob"));
    }
    private void refreshTableView() {
        tableView.getItems().clear();
        if(isArrayList && arrayList != null) {
            for(int i = 0; i < arrayList.length(); i++)
                tableView.getItems().add(arrayList.get(i));
        } else if (!isArrayList && linkedList != null) {
            for(int i = 0; i < linkedList.length(); i++)
                tableView.getItems().add(linkedList.get(i));
        }
    }
    private void setupComboBoxes() {
        nameSort.getItems().clear();
        nameSort.getItems().add("Bubble sort");
        nameSort.getItems().add("Insertion sort");
        nameSort.getItems().add("Selection sort");
        nameSort.getItems().add("Merge sort");
        nameSort.getItems().add("Heap sort");
        idSort.getItems().clear();
        idSort.getItems().add("Bubble sort");
        idSort.getItems().add("Insertion sort");
        idSort.getItems().add("Selection sort");
        idSort.getItems().add("Merge sort");
        idSort.getItems().add("Heap sort");
    }
    private void emptySearchTextFieldAction() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()) {
                refreshTableView();
                searchTextField.setPromptText("Name or ID");
            }
        });
    }
    public static boolean warningWindow(String heading, String description) {
        try {
            FXMLLoader design = new FXMLLoader(MainApplication.class.getResource("warning-view.fxml"));
            WarningController.heading = heading;
            WarningController.description = description;
            Stage newWindow = new Stage();
            newWindow.setTitle("Warning window");
            WarningController.currentWindow = newWindow;
            newWindow.setScene(new Scene(design.load(), 600, 400));
            newWindow.showAndWait();
            return WarningController.isAgree;
        } catch (Exception e) {
            System.out.println("ERROR LOADING WARNING WINDOW");
            System.out.println(e.getMessage());
            return WarningController.isAgree;
        }
    }
}