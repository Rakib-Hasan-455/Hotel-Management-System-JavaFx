package sample.zadmin.AdminPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample._BackEnd.TableView.AdminEarningTable;
import sample._BackEnd.TableView.ManagerCheckOutTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample._BackEnd.DBConnection.closeConnections;
import static sample._BackEnd.DBConnection.getConnections;

public class AdminEarningLog implements Initializable {

    public TableView earningLogTable;
    public TableColumn nidCol;
    public TableColumn roomNoCol;
    public TableColumn roomTypeCol;
    public TableColumn checkedInCol;
    public TableColumn checkedOutCol;
    public TableColumn priceDayCol;
    public TableColumn totalPriceCol;
    public TableColumn slipCol;

    private ObservableList<AdminEarningTable> TABLEROW = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nidCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("nid"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("roomno"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("type"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("checkedin"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("checkedout"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("priceday"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("totalprice"));
        showEarningLog();
    }

    public void showEarningLog(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CHECKINOUTINFO WHERE CHECKEDOUT IS NOT NULL";
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
                    AdminEarningTable roomTablee = new AdminEarningTable(NID, ROOMNO, TYPE, CHECKEDIN, CHECKEDOUT, PRICEDAY, TOTALPRICE);

                    TABLEROW.add(roomTablee);
                }
                earningLogTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }
}
