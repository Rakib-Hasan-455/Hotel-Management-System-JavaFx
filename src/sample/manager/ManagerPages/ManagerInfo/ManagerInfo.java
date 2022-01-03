package sample.manager.ManagerPages.ManagerInfo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.customer.Login.UserLogin.currentCustomerNID;
import static sample.manager.Login.ManagerLogin.currentEmployeeNID;

public class ManagerInfo implements Initializable {
    public Button CloseWindow;
    public Label managerName;
    public Label managerNID;
    public Label managerEmail;
    public Label managerPhone;
    public Label managerPassword;
    public Label managerAddress;

    public void CloseWindow(ActionEvent actionEvent) {
        Stage stage = (Stage)CloseWindow.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setManagerData();
    }

    private void setManagerData() {
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM EMPLOYEEINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentEmployeeNID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    String customerNID = resultSet.getString("NID");
                    String customerEmail = resultSet.getString("EMAIL");
                    String customerPhone = resultSet.getString("PHONE");
                    String customerPassword = resultSet.getString("PASSWORD");
                    String customerAddress = resultSet.getString("ADDRESS");

                    managerName.setText(customerName);
                    managerNID.setText(customerNID);
                    managerEmail.setText(customerEmail);
                    managerPhone.setText(customerPhone);
                    managerPassword.setText(customerPassword);
                    managerAddress.setText(customerAddress);
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
