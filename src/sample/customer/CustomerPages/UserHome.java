package sample.customer.CustomerPages;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Main;
import sample._BackEnd.CommonTask;

import java.io.IOException;

public class UserHome {
    public void BackToLoginPage(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("../../sample.fxml", Main.stage,this.getClass(),"User Home", 550, 400);
    }

    public void UserInfo(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("CustomerInfo/Userinfo.fxml", null,this.getClass(),"User Home", 550, 400);
    }
}
