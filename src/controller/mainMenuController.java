package controller;

import application.DbConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainMenuController {

    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableColumn<Department, String> Dname;
    @FXML
    private TableColumn<Department, Integer> Dnumber;
    @FXML
    private TableColumn<Department, Integer> Mgr_ssn;
    @FXML
    private TableColumn<Department, String> Dlocation;
    @FXML
    private Button employeeBtn;

    private Stage stage;
    private AnchorPane root;
    private Scene scene;

    @FXML
    public void initialize() {
//        stationListView.setItems(getStationList());
        departmentTable.setItems(getDepartmentList());



        Dname.setCellValueFactory(new PropertyValueFactory<Department, String>("Dname"));
        Dnumber.setCellValueFactory(new PropertyValueFactory<Department, Integer>("Dnumber"));
        Mgr_ssn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("Mgr_ssn"));
        Dlocation.setCellValueFactory(new PropertyValueFactory<Department, String>("Dlocation"));

//        departmentTable.setItems(getDepartmentList());
    }

    public ObservableList<Department>/*<String>*/  getDepartmentList()
    {
        ObservableList<Department>/*<String>*/ departments = FXCollections.observableArrayList();

        String SQLQuery = "select Dname, Dnumber, Mgr_ssn, Dlocation FROM department";

        //ResultSet rs = null;

        try(
                Connection conn = DbConnector.getConnection();
                PreparedStatement displayprofile = conn.prepareStatement(SQLQuery);
                ResultSet resultSet = displayprofile.executeQuery();
        ){
            //displayprofile.setInt(1, cutomerId);
            //rs = displayprofile.executeQuery();

            // check to see if receiving any data
            while (resultSet.next()){
                departments.add(new Department(resultSet.getString("Dname"), resultSet.getString("Dlocation"),
                        resultSet.getInt("Dnumber"), resultSet.getInt("Mgr_ssn") ));

            }
        }catch(SQLException ex){
            DbConnector.displayException(ex);
            return null;
        }
        return departments;
    }
    public void toProjectView(ActionEvent event) throws Exception {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("../view/projectView.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
    }
    public void toEmployeeView(ActionEvent event) throws Exception {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("../view/employeeView.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
    }
    public void toDepartmentView(ActionEvent event) throws Exception {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("../view/departmentView.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
    }
    public void toWorksonView(ActionEvent event) throws Exception {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("../view/worksonView.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
