package sample.customer.Login;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLogin implements Initializable {

    public TextField customerNIDField;
    public PasswordField customerPassField;
    public static String currentCustomerNID;
    public ImageView closeWindow;

    public StackPane rootPane;
    public AnchorPane rootAnchorPane;

    @FXML
    public void UserLoginn(ActionEvent actionEvent) throws IOException, SQLException {
        Connection connection = DBConnection.getConnections();
        String customerNID = customerNIDField.getText();
        currentCustomerNID = customerNID;
        String customerPass = customerPassField.getText();
        try {

            if (customerNID.isEmpty() == true || customerPass.isEmpty() == true) {
//                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
                CommonTask.showJFXAlert(rootPane, rootAnchorPane, "warning", "Warning!", "Field Can't be Empty!", JFXDialog.DialogTransition.CENTER);
            } else {
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customerNID);
                preparedStatement.setString(2, customerPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    //CommonTask.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    CommonTask.pageNavigation("/sample/customer/CustomerPages/UserMain.fxml", Main.stage, this.getClass(), "User Dashboard", 800, 400);
                } else {
                    CommonTask.showJFXAlert(rootPane, rootAnchorPane, "warning", "Login Failed!", "Wrong UserNid/Password !", JFXDialog.DialogTransition.CENTER);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }
    @FXML
    public void UserSignup(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("UserSignup.fxml", Main.stage ,this.getClass(),"User Signup", 600, 400);
    }
@FXML
    public void BackToMain(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("/sample/sample.fxml", Main.stage,this.getClass(),"Hotel Management System", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });


    }
}
