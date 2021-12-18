package sample.zadmin.AdminPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.AdminEmployeeTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminEmployeeInfo extends DBConnection implements Initializable {
    public TextField searchEmployeeTable;
    public Button searchBtn;
    public TableView<AdminEmployeeTable> employeeTable;
    public TableColumn<AdminEmployeeTable, String> nidCol;
    public TableColumn<AdminEmployeeTable, String> nameCol;
    public TableColumn<AdminEmployeeTable, String> emailCol;
    public TableColumn<AdminEmployeeTable, String> phoneCol;
    public TableColumn<AdminEmployeeTable, String> addressCol;
    public TableColumn<AdminEmployeeTable, String> passCol;

    private ObservableList<AdminEmployeeTable> TABLEROW = FXCollections.observableArrayList();


    public void searchBtn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TABLEROW.clear();
        nameCol.setCellValueFactory(new PropertyValueFactory<AdminEmployeeTable, String>("Name")); //adminEmployeeTable variable name
        nidCol.setCellValueFactory(new PropertyValueFactory<AdminEmployeeTable, String>("NID"));
        emailCol.setCellValueFactory(new PropertyValueFactory<AdminEmployeeTable, String>("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<AdminEmployeeTable, String>("Phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<AdminEmployeeTable, String>("Address"));
        passCol.setCellValueFactory(new PropertyValueFactory<AdminEmployeeTable, String>("Pass"));
        showEmployeeTable();
    }

    public void showEmployeeTable(){
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM EMPLOYEEINFO";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String NID = resultSet.getString("NID"); //SQL COL NAMES NID
                    String NAME = resultSet.getString("NAME");
                    String EMAIL = resultSet.getString("EMAIL");
                    String PHONE = resultSet.getString("PHONE");
                    String ADDRESS = resultSet.getString("ADDRESS");
                    String PASS = resultSet.getString("PASSWORD");

                    AdminEmployeeTable empTable = new AdminEmployeeTable(NID, NAME, EMAIL, PHONE, ADDRESS, PASS);

                    TABLEROW.add(empTable);
                }
                employeeTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }
}
