package sample.manager.ManagerPages.RoomInfoEdit;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.ManagerRoomTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample._BackEnd.DBConnection.closeConnections;
import static sample._BackEnd.DBConnection.getConnections;
import static sample.customer.Login.UserLogin.currentCustomerNID;

public class RoomInfoEdit implements Initializable {
    public Button UserConfirm;

    public TextField roomNoField;
    public TextField roomTypeField;
    public TextField capacityField;
    public TextField priceDayField;
    public JFXComboBox statusCbox;
    private String[] roomStats = {"Available", "Unavailable"};

    public void closeBtn(ActionEvent event) {
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    public void saveBtn(ActionEvent event) {
        Connection connection = getConnections();
        try {
            if (!connection.isClosed()){
                String sql = "UPDATE RoomInfo SET TYPE = ?, CAPACITY = ?, PRICE_DAY = ?, STATUS = ? where ROOM_NO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomTypeField.getText());
                statement.setString(2, capacityField.getText());
                statement.setString(3, priceDayField.getText());
                statement.setString(4, statusCbox.getValue()+"");
                statement.setString(5, roomNoField.getText());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeConnections();
        }
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusCbox.getItems().setAll(roomStats);
    }

    public void setRoomInfo(String roomNo, String type, String capacity, String priceDay, String status) {
        roomNoField.setText(roomNo);
        roomNoField.setDisable(true);
        roomTypeField.setText(type);
        capacityField.setText(capacity);
        priceDayField.setText(priceDay);
        statusCbox.setValue(status);
    }
}
