package sample.customer.CustomerPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.CustomerCheckOutTable;
import sample._BackEnd.TableView.CustomerRoomTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.customer.Login.UserLogin.currentCustomerNID;

public class UserCheckOutDetails extends DBConnection implements Initializable {
    @FXML
    private TableView<CustomerCheckOutTable> UserCheckOutDetailsTable;
    public TableColumn<CustomerCheckOutTable, String> nidCol;
    public TableColumn<CustomerCheckOutTable, String> roomNoCol;
    public TableColumn<CustomerCheckOutTable, String> checkedInCol;
    public TableColumn<CustomerCheckOutTable, String> checkedOutCol;
    public TableColumn<CustomerCheckOutTable, String> priceDayCol;
    public TableColumn<CustomerCheckOutTable, String> totalPriceCol;
    @FXML
    private Label UserPhoneLabel1;

    private ObservableList<CustomerCheckOutTable> TABLEROW = FXCollections.observableArrayList();
    @FXML
    private TextField UserCheckOutDetailsSearch;
    @FXML
    private Button UserCheckOutSearchBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nidCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("nid"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("roomNo"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("checkedIndate"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("checkedOutDate"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("priceDay"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("totalPrice"));

        showcheckInOutDetails();
    }

    public void showcheckInOutDetails(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CHECKINOUTINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String NID = resultSet.getString("NID");
                    String RoomNo = resultSet.getString("ROOMNO");
                    String CheckedInDate = resultSet.getString("CHECKEDIN");
                    String CheckedOutDate = resultSet.getString("CHECKEDOUT");
                    String PriceDay = resultSet.getString("PRICEDAY");
                    String TotalPrice = resultSet.getString("TOTALPRICE");

                    CustomerCheckOutTable roomTablee = new CustomerCheckOutTable(NID, RoomNo, CheckedInDate, CheckedOutDate, PriceDay, TotalPrice);

                    TABLEROW.add(roomTablee);
                }
                UserCheckOutDetailsTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }
}
