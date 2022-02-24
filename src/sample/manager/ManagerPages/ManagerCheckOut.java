package sample.manager.ManagerPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.ManagerCheckInDetailsTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;


public class ManagerCheckOut extends DBConnection implements Initializable {

    public TableView<ManagerCheckInDetailsTable> checkInInfoTable;
    public TableColumn<ManagerCheckInDetailsTable, String> siCol;
    public TableColumn<ManagerCheckInDetailsTable, String> nameCol;
    public TableColumn<ManagerCheckInDetailsTable, String> roomNoCol;
    public TableColumn<ManagerCheckInDetailsTable, String> priceDayCol;
    public TableColumn<ManagerCheckInDetailsTable, String> checkedInCol;
    public TableColumn<ManagerCheckInDetailsTable, String> addressCol;
    public Label nameField;
    public Label checkedInField;
    public Label priceDayField;
    public Label daysTotalField;
    public Label totalPriceField;
    public DatePicker checkOutDatepicker;
    public Label roomNoField;
    public Label siNoField;


    private ObservableList<ManagerCheckInDetailsTable> TABLEROW = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TABLEROW.clear();
        nameCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("name")); //managerCustomerTable variable name
        siCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("sino"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("roomno"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("priceday"));
        addressCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("address"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("checkedin"));

        showCheckedInTable();

    }

    @FXML
    private void onCheckOutPick(ActionEvent event) throws ParseException {
        LocalDate myDate = checkOutDatepicker.getValue();
        String checkInDate = checkedInField.getText();
        if(!checkInDate.isEmpty()) {
            String checkOutDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long days = (dayDifference(checkOutDate, checkInDate))+1;
            daysTotalField.setText(days+"");
            String priceDay = priceDayField.getText();
            boolean isNumeric = priceDay.chars().allMatch(Character::isDigit);
            if(isNumeric) {
                long pricePerDay = Long.parseLong(priceDay);
                long totalPrice = pricePerDay * days;
                totalPriceField.setText(totalPrice+"");
            }

        } else {
            //checkOutDatepicker.getEditor().clear();
//            checkOutDatepicker.setValue(null);
            CommonTask.showAlert(Alert.AlertType.WARNING, "Warning", "Checked-In Date is empty!");
       }
    }

    private long dayDifference(String checkOut, String checkIn) throws ParseException {
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = obj.parse(checkIn);
        Date date2 = obj.parse(checkOut);
        long time_difference = date2.getTime() - date1.getTime();
        long days_difference = (time_difference / (1000*60*60*24)) % 365;
        return days_difference;
    }

    private void showCheckedInTable() {
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CHECKINOUTINFO WHERE CHECKEDOUT IS NULL ORDER BY SI_NO DESC";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String SI_NO = resultSet.getString("SI_NO"); //SQL COL NAMES NID
                    String NAME = resultSet.getString("NAME");
                    String ROOMNO = resultSet.getString("ROOMNO");
                    String PRICEDAY = resultSet.getString("PRICEDAY");
                    String CHECKEDIN = resultSet.getString("CHECKEDIN");
                    String ADDRESS = resultSet.getString("ADDRESS");

                    ManagerCheckInDetailsTable checkInTable = new ManagerCheckInDetailsTable(SI_NO, NAME, ROOMNO, PRICEDAY, CHECKEDIN, ADDRESS);

                    TABLEROW.add(checkInTable);
                }
                checkInInfoTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public int selectIndex = -1;

    public void onTableRowSelect(MouseEvent mouseEvent) {
        selectIndex = checkInInfoTable.getSelectionModel().getSelectedIndex();
        if(selectIndex <= -1){
            return;
        }
        checkedInField.setText(checkedInCol.getCellData(selectIndex).toString());
        nameField.setText(nameCol.getCellData(selectIndex).toString());
        priceDayField.setText(priceDayCol.getCellData(selectIndex).toString());
        roomNoField.setText(roomNoCol.getCellData(selectIndex).toString());
        siNoField.setText(siCol.getCellData(selectIndex).toString());
    }

    public void checkOutBtn(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getConnections();
        String checkOutDate = checkOutDatepicker.getValue() + "";
        String daysTotal = daysTotalField.getText();
        String totalPrice = totalPriceField.getText();
        String roomNo = roomNoField.getText();
        String siNo = siNoField.getText();
        if (siNo.equals("") || checkOutDate.equals("null") || daysTotal.isEmpty() || totalPrice.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE CHECKINOUTINFO SET CHECKEDOUT = ?, TOTALDAYS = ?, TOTALPRICE = ? WHERE SI_NO = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, checkOutDate);
            preparedStatement.setString(2, daysTotal);
            preparedStatement.setString(3, totalPrice);
            preparedStatement.setString(4, siNo);
            try {
                preparedStatement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Check-Out Successful!");
                String sql1 = "UPDATE ROOMINFO SET STATUS = ? WHERE ROOM_NO = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, "Available");
                preparedStatement1.setString(2, roomNo);
                preparedStatement1.execute();
                showCheckedInTable();
            } catch (SQLException e) {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Exception detected. Probably Sql!");
            } finally {
                DBConnection.closeConnections();
            }
        }
        clearTextFields();
    }

    private void clearTextFields() {
        siNoField.setText("");
        nameField.setText("");
        checkedInField.setText("");
        checkOutDatepicker.getEditor().clear();
        roomNoField.setText("");
        priceDayField.setText("");
        daysTotalField.setText("");
        totalPriceField.setText("");
    }

}
