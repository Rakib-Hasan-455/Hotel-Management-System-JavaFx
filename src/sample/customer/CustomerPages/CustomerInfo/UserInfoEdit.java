package sample.customer.CustomerPages.CustomerInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import static sample.customer.Login.UserLogin.currentCustomerNID;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserInfoEdit implements Initializable {

    public Button UserConfirm;
    @FXML
    private TextField UserNameEdit;

    @FXML
    private TextField UserNidEdit;

    @FXML
    private TextField UserEmailEdit;

    @FXML
    private TextField UserPhoneEdit;

    @FXML
    private TextField UserPassEdit;

    @FXML
    private TextArea UserAddressEdit;

    @FXML
    void UserConfirmEdit(ActionEvent event) throws IOException, SQLException {
        Connection connection = DBConnection.getConnections();
        String customerName = UserNameEdit.getText();
        String customerNID = UserNidEdit.getText();
        String customerPassword = UserPassEdit.getText();
        String customerEmail = UserEmailEdit.getText();
        String customerPhone = UserPhoneEdit.getText();
        String customerAddress = UserAddressEdit.getText();
        if (customerName.isEmpty() || customerNID.isEmpty() || customerPassword.isEmpty() || customerEmail.isEmpty() || customerAddress.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE CUSTOMERINFO SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE NID = ?";
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sql);
            preparedStatementUpdate.setString(1, customerName);
            preparedStatementUpdate.setString(2, customerPassword);
            preparedStatementUpdate.setString(3, customerEmail);
            preparedStatementUpdate.setString(4, customerPhone);
            preparedStatementUpdate.setString(5, customerAddress);
            preparedStatementUpdate.setString(6, currentCustomerNID);
            try{
                preparedStatementUpdate.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Update Successful!");
                CommonTask.pageNavigation("UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
            } catch (SQLException e){
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Maybe Sql Error!");
            } finally {
                DBConnection.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerInfo();
    }

    public void setCustomerInfo(){
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerNameSet = resultSet.getString("NAME");
                    String customerNIDSet = resultSet.getString("NID");
                    String customerEmailSet = resultSet.getString("EMAIL");
                    String customerPhoneSet = resultSet.getString("PHONE");
                    String customerPasswordSet = resultSet.getString("PASSWORD");
                    String customerAddressSet = resultSet.getString("ADDRESS");

                    UserNameEdit.setText(customerNameSet);
                    UserNidEdit.setText(customerNIDSet);
                    UserNidEdit.setDisable(true);
                    UserEmailEdit.setText(customerEmailSet);
                    UserPhoneEdit.setText(customerPhoneSet);
                    UserPassEdit.setText(customerPasswordSet);
                    UserAddressEdit.setText(customerAddressSet);
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

    public void BackBtn(ActionEvent event) throws IOException {
        CommonTask.pageNavigation("UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
    }
}
