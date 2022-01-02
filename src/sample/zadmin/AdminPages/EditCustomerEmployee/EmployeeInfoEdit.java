package sample.zadmin.AdminPages.EditCustomerEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.AdminEmployeeTable;
import sample.zadmin.AdminPages.AdminEmployeeInfo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.customer.Login.UserLogin.currentCustomerNID;

public class EmployeeInfoEdit implements Initializable {

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
        String employeeName = UserNameEdit.getText();
        String employeeNID = UserNidEdit.getText();
        String employeePassword = UserPassEdit.getText();
        String employeeEmail = UserEmailEdit.getText();
        String employeePhone = UserPhoneEdit.getText();
        String employeeAddress = UserAddressEdit.getText();
        if (employeeName.isEmpty() || employeeNID.isEmpty() || employeePassword.isEmpty() || employeePhone.isEmpty() || employeeAddress.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE EMPLOYEEINFO SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE NID = ?";
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sql);
            preparedStatementUpdate.setString(1, employeeName);
            preparedStatementUpdate.setString(2, employeePassword);
            preparedStatementUpdate.setString(3, employeeEmail);
            preparedStatementUpdate.setString(4, employeePhone);
            preparedStatementUpdate.setString(5, employeeAddress);
            preparedStatementUpdate.setString(6, employeeNID);
            try{
                preparedStatementUpdate.execute();
//                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Update Successful!");
//                CommonTask.pageNavigation("UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
                Stage stage = (Stage) UserConfirm.getScene().getWindow();
                stage.close();
            } catch (SQLException e){
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Maybe Sql Error!");
            } finally {
                DBConnection.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void BackBtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    public void setEmployeeInfo(AdminEmployeeTable adminEmployeeTable) {
        UserNameEdit.setText(adminEmployeeTable.getName());
        UserNidEdit.setText(adminEmployeeTable.getNID());
        UserNidEdit.setDisable(true);
        UserEmailEdit.setText(adminEmployeeTable.getEmail());
        UserPhoneEdit.setText(adminEmployeeTable.getPhone());
        UserPassEdit.setText(adminEmployeeTable.getPass());
        UserAddressEdit.setText(adminEmployeeTable.getAddress());
    }
}
