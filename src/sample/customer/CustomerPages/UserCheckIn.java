package sample.customer.CustomerPages;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample._BackEnd.DBConnection.connection;
import static sample.customer.Login.UserLogin.currentCustomerNID;
public class UserCheckIn implements Initializable {

    public Label roomCapacityField;
    public Label roomTypeField;
    public Label roomPriceField;
    public AnchorPane userCheckInPane;
    public JFXComboBox userRoomChoicebox;
    @FXML
    private Label UserNameField;
    @FXML
    private Label UserNIDField;
    @FXML
    private Label UserEmailField;
    @FXML
    private Label UserPhoneField;
    @FXML
    private Label UserAddressField;
    @FXML
    public DatePicker UserCheckIndate;



    @FXML
    void UserCheckInSubmitBtn(ActionEvent event) throws SQLException {
        String name = UserNameField.getText();
        String NID = UserNIDField.getText();
        String Email = UserEmailField.getText();
        String Phone = UserPhoneField.getText();
        String Address = UserAddressField.getText();
        String RoomNo = userRoomChoicebox.getValue()+"";
        String CheckInDate = UserCheckIndate.getValue()+"";
        String roomCapacity = roomCapacityField.getText();
        String roomType = roomTypeField.getText();
        String roomPrice = roomPriceField.getText();
        Connection connection = DBConnection.getConnections();
        if (RoomNo.isEmpty() || CheckInDate.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "INSERT INTO CHECKINOUTINFO (NAME, NID, EMAIL, PHONE, ADDRESS, ROOMNO, CHECKEDIN, ROOMTYPE, CAPACITY, PRICEDAY) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, NID);
            preparedStatement.setString(3, Email);
            preparedStatement.setString(4, Phone);
            preparedStatement.setString(5, Address);
            preparedStatement.setString(6, RoomNo);
            preparedStatement.setString(7, CheckInDate);
            preparedStatement.setString(8, roomType);
            preparedStatement.setString(9, roomCapacity);
            preparedStatement.setString(10, roomPrice);
            try{
                preparedStatement.execute();
                String sql1 = "UPDATE ROOMINFO SET STATUS = 'Booked' WHERE ROOM_NO = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, RoomNo);
                preparedStatement1.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Check-in Successful!");
                } catch (SQLException e){
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "SQL Exception found!");
            } finally {
                DBConnection.closeConnections();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateChoiceBox();
        setAndShowCustomerInfo();
        userRoomChoicebox.setOnAction(this::setRoomInfo);
    }

    public void setRoomInfo(Event event) {
        String roomNo = userRoomChoicebox.getValue()+"";
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM ROOMINFO WHERE ROOM_NO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomNo);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String roomCapacity = resultSet.getString("CAPACITY");
                    String roomType = resultSet.getString("TYPE");
                    String roomPriceDay = resultSet.getString("PRICE_DAY");

                    roomCapacityField.setText(roomCapacity);
                    roomPriceField.setText(roomPriceDay);
                    roomTypeField.setText(roomType);
                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }


    public void updateChoiceBox(){
        List<String> rooms = new ArrayList<String>();
        Connection connection = DBConnection.getConnections();
        try{
            if(!connection.isClosed()) {
                String sql = "SELECT * FROM ROOMINFO WHERE STATUS = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "Available");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    rooms.add(resultSet.getString("ROOM_NO"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
        userRoomChoicebox.getItems().setAll(rooms);
    }

    public void setAndShowCustomerInfo(){
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    String customerNID = resultSet.getString("NID");
                    String customerEmail = resultSet.getString("EMAIL");
                    String customerPhone = resultSet.getString("PHONE");
                    //String customerPassword = resultSet.getString("PASSWORD");
                    String customerAddress = resultSet.getString("ADDRESS");

                    UserNameField.setText(customerName);
                    UserNIDField.setText(customerNID);
                    UserEmailField.setText(customerEmail);
                    UserPhoneField.setText(customerPhone);
                    //UserPasswordLabel.setText(customerPassword);
                    UserAddressField.setText(customerAddress);
                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }
}
