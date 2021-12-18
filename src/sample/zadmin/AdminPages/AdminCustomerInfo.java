package sample.zadmin.AdminPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.AdminCustomerTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminCustomerInfo extends DBConnection implements Initializable{
    public TableView<AdminCustomerTable> customerTable;
    public TableColumn<AdminCustomerTable, String> nidCol;
    public TableColumn<AdminCustomerTable, String> nameCol;
    public TableColumn<AdminCustomerTable, String> emailCol;
    public TableColumn<AdminCustomerTable, String> phoneCol;
    public TableColumn<AdminCustomerTable, String> addressCol;
    public TableColumn<AdminCustomerTable, String> passCol;

    private ObservableList<AdminCustomerTable> TABLEROW = FXCollections.observableArrayList();


    public void searchBtn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TABLEROW.clear();
        nameCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Name")); //adminCustomerTable variable name
        nidCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("NID"));
        emailCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Address"));
        passCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Pass"));
        showCustomerTable();
    }

    public void showCustomerTable(){
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String NID = resultSet.getString("NID"); //SQL COL NAMES NID
                    String NAME = resultSet.getString("NAME");
                    String EMAIL = resultSet.getString("EMAIL");
                    String PHONE = resultSet.getString("PHONE");
                    String ADDRESS = resultSet.getString("ADDRESS");
                    String PASS = resultSet.getString("PASSWORD");

                    AdminCustomerTable custTable = new AdminCustomerTable(NID, NAME, EMAIL, PHONE, ADDRESS, PASS);

                    TABLEROW.add(custTable);
                }
                customerTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }
}
