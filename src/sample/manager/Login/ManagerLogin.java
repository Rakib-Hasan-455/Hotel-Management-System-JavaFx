package sample.manager.Login;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample._BackEnd.CommonTask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerLogin implements Initializable {
    public ImageView closeWindow;

    public void ManagerLogin(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("../ManagerPages/ManagerMain.fxml", Main.stage,this.getClass(),"Manager Dashboard", 800, 600);
    }


    public void BackToMain(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("../../sample.fxml", Main.stage,this.getClass(),"Hotel Management System", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });

    }
}
