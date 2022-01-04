package sample.customer.CustomerPages.CustomerInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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

public class UserInfo implements Initializable {

    public Button UserBackToHome;

    @FXML
    private Label UserNameLabel;

    @FXML
    private Label UserNIDlabel;

    @FXML
    private Label UserEmailLabel;

    @FXML
    private Label UserPhoneLabel;

    @FXML
    private Label UserPasswordLabel;

    @FXML
    private Label UserAddressLabel;

    @FXML
    void UserBackToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) UserBackToHome.getScene().getWindow();
        stage.close();
    }

    @FXML
    void UserInfoEdit(ActionEvent event) throws IOException {
        CommonTask.pageNavigation("/sample/customer/CustomerPages/CustomerInfo/UserInfoEdit.fxml", (Stage) UserBackToHome.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCustomerInfo();
    }

    public void showCustomerInfo(){
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
                    String customerPassword = resultSet.getString("PASSWORD");
                    String customerAddress = resultSet.getString("ADDRESS");

                    UserNameLabel.setText(customerName);
                    UserNIDlabel.setText(customerNID);
                    UserEmailLabel.setText(customerEmail);
                    UserPhoneLabel.setText(customerPhone);
                    UserPasswordLabel.setText(customerPassword);
                    UserAddressLabel.setText(customerAddress);
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
