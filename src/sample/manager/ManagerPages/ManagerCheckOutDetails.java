package sample.manager.ManagerPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample._BackEnd.TableView.ManagerCheckOutTable;
import sample._BackEnd.TableView.ManagerRoomTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample._BackEnd.DBConnection.closeConnections;
import static sample._BackEnd.DBConnection.getConnections;

public class ManagerCheckOutDetails implements Initializable {

    public TableView<ManagerCheckOutTable> checkInoutTable;
    public TableColumn<ManagerCheckOutTable, String> nidCol;
    public TableColumn<ManagerCheckOutTable, String> roomNoCol;
    public TableColumn<ManagerCheckOutTable, String> roomTypeCol;
    public TableColumn<ManagerCheckOutTable, String> checkedInCol;
    public TableColumn<ManagerCheckOutTable, String> checkedOutCol;
    public TableColumn<ManagerCheckOutTable, String> priceDayCol;
    public TableColumn<ManagerCheckOutTable, String> totalPriceCol;
    public TableColumn<ManagerCheckOutTable, String> slipCol;

    private ObservableList<ManagerCheckOutTable> TABLEROW = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nidCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("nid"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("roomno"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("type"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("checkedin"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("checkedout"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("priceday"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("totalprice"));
        //slipCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("nid"));

        showCheckInOutInfo();
    }

    public void showCheckInOutInfo(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CHECKINOUTINFO";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String ROOMNO = resultSet.getString("ROOMNO"); //SQL COL NAMES NID
                    String TYPE = resultSet.getString("ROOMTYPE");
                    String CAPACITY = resultSet.getString("CAPACITY");
                    String PRICEDAY = resultSet.getString("PRICEDAY");
                    String TOTALPRICE = resultSet.getString("TOTALPRICE");
                    String CHECKEDIN = resultSet.getString("CHECKEDIN");
                    String CHECKEDOUT = resultSet.getString("CHECKEDOUT");
                    String NID = resultSet.getString("NID");
                    ManagerCheckOutTable roomTablee = new ManagerCheckOutTable(NID, ROOMNO, TYPE, CAPACITY, PRICEDAY, TOTALPRICE, CHECKEDIN, CHECKEDOUT);

                    TABLEROW.add(roomTablee);
                }
                checkInoutTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }
}
