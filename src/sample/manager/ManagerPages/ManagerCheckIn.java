package sample.manager.ManagerPages;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.AdminCustomerTable;
import sample._BackEnd.TableView.ManagerCustomerTable;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerCheckIn extends DBConnection implements Initializable {
    public TableView<ManagerCustomerTable> customerTable;
    public TableColumn<ManagerCustomerTable, String> nidCol;
    public TableColumn<ManagerCustomerTable, String> nameCol;
    public TableColumn<ManagerCustomerTable, String> phoneCol;
    public TableColumn<ManagerCustomerTable, String> emailCol;
    public TableColumn<ManagerCustomerTable, String> addressCol;

    public Label nidField;
    public Label nameField;
    public Label phoneField;
    public Label emailField;
    public Label addressField;
    public int selectIndex = -1;
    public Label roomTypeField;
    public Label roomCapacityField;
    public Label roomPriceField;
    public DatePicker UserCheckIndate;
    public JFXComboBox roomChoiceBox;
    private ObservableList<ManagerCustomerTable> TABLEROW = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateChoiceBox();
        TABLEROW.clear();
        nameCol.setCellValueFactory(new PropertyValueFactory<ManagerCustomerTable, String>("Name")); //managerCustomerTable variable name
        nidCol.setCellValueFactory(new PropertyValueFactory<ManagerCustomerTable, String>("NID"));
        emailCol.setCellValueFactory(new PropertyValueFactory<ManagerCustomerTable, String>("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<ManagerCustomerTable, String>("Phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<ManagerCustomerTable, String>("Address"));
        showCustomerTable();
        roomChoiceBox.setOnAction(this::setRoomInfoo);
    }

    private void setRoomInfoo(javafx.event.Event event) {
        String roomNo = roomChoiceBox.getValue()+"";
        if(!roomNo.equals("null")) {
            Connection connection = DBConnection.getConnections();
            try {
                if (!connection.isClosed()) {
                    String sql = "SELECT * FROM ROOMINFO WHERE ROOM_NO = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, roomNo);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
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
    }

    private void updateChoiceBox() {
        List<String> rooms = new ArrayList<String>();

        int count = 0;
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
        roomChoiceBox.getItems().setAll(rooms);
        roomChoiceBox.setValue(null);
    }

    private void showCustomerTable() {
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO ORDER BY NID";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String NID = resultSet.getString("NID"); //SQL COL NAMES NID
                    String NAME = resultSet.getString("NAME");
                    String EMAIL = resultSet.getString("EMAIL");
                    String PHONE = resultSet.getString("PHONE");
                    String ADDRESS = resultSet.getString("ADDRESS");

                    ManagerCustomerTable custTable = new ManagerCustomerTable(NID, NAME, EMAIL, PHONE, ADDRESS);

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

    public void onSelect(MouseEvent mouseEvent) {
        selectIndex = customerTable.getSelectionModel().getSelectedIndex();
        if(selectIndex <= -1){
            return;
        }
        nidField.setText(nidCol.getCellData(selectIndex).toString());
        nameField.setText(nameCol.getCellData(selectIndex).toString());
        emailField.setText(emailCol.getCellData(selectIndex).toString());
        phoneField.setText(phoneCol.getCellData(selectIndex).toString());
        addressField.setText(addressCol.getCellData(selectIndex).toString());
    }

    public void checkInButton(ActionEvent actionEvent) throws SQLException {
        String name = nameField.getText();
        String NID = nidField.getText();
        String Email = emailField.getText();
        String Phone = phoneField.getText();
        String Address = addressField.getText();
        String RoomNo = roomChoiceBox.getValue()+"";
        String CheckInDate = UserCheckIndate.getValue()+"";
        String roomCapacity = roomCapacityField.getText();
        String roomType = roomTypeField.getText();
        String roomPrice = roomPriceField.getText();
        System.out.println(roomCapacity +" "+roomType+" "+roomPrice);
       // System.out.println(name+" "+RoomNo+" "+CheckInDate);
        Connection connection = DBConnection.getConnections();
        if (name.isEmpty() || RoomNo.equals("null") || CheckInDate.equals("null")) {
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
            updateChoiceBox();
            clearTextFields();
        }
    }

    private void clearTextFields() {
        nidField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");
        roomTypeField.setText("");
        roomCapacityField.setText("");
        roomPriceField.setText("");
        UserCheckIndate.getEditor().clear();
//        roomChoiceBox.setValue(null);
    }
}
