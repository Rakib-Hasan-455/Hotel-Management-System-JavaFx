package sample.zadmin.Login;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

public class AdminLogin implements Initializable {

    public TextField adminNIDField;
    public PasswordField adminPassField;
    public static String currentAdminNID;
    public ImageView closeWindow;

    public void LoginAdminHome(ActionEvent actionEvent) throws IOException {
        String adminNID = adminNIDField.getText();
        currentAdminNID = adminNID;
        String adminPass = adminPassField.getText();
        try {
            Connection connection = DBConnection.getConnections();
            if (adminNID.isEmpty() || adminPass.isEmpty()) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
            } else {
                String sql = "SELECT * FROM ADMININFO WHERE NID = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, adminNID);
                preparedStatement.setString(2, adminPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    CommonTask.pageNavigation("/sample/zadmin/AdminPages/AdminMain.fxml", Main.stage,this.getClass(),"Admin Dashboard", 1000, 600);
                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect NID or Password!");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
        }

    public void BackToMain(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("/sample/sample.fxml", Main.stage,this.getClass(),"Hotel Management System", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}
