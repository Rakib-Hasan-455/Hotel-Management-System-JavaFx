package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import sample._BackEnd.CommonTask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public ImageView closeWihdow;
    public JFXButton customerLoginBtn;
    public JFXButton managerLoginBtn;
    public JFXButton adminLoginBtn;
    public JFXButton btn;

    @FXML
    void Customer_Login(ActionEvent event) throws IOException {
        CommonTask.pageNavigation("customer/Login/UserLogin.fxml",Main.stage,this.getClass(),"Customer Login", 600, 400);
    }

    @FXML
    void Manager_Login(ActionEvent event) throws IOException {
        CommonTask.pageNavigation("manager/Login/ManagerLogin.fxml",Main.stage,this.getClass(),"Manager Login", 600, 400);
    }

    @FXML
    void Admin_Login(ActionEvent event) throws IOException {
        CommonTask.pageNavigation("zadmin/Login/AdminLogin.fxml", Main.stage,this.getClass(),"Admin Login", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeWihdow.setOnMouseClicked(event -> {
            System.exit(0);
        });

    }
}
