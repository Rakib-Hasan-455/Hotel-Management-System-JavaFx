package sample.manager.ManagerPages;

import javafx.event.ActionEvent;
import sample.Main;
import sample._BackEnd.CommonTask;

import java.io.IOException;

public class ManagerHome {
    public void ManagerInfo(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("ManagerInfo/ManagerInfo.fxml", null, this.getClass(),"User Home", 550, 400);
//        CommonTask.pageNavigation("/sample/manager/ManagerPages/RoomInfoEdit/roomInfoEdit.fxml", null, this.getClass(),"Edit Info", 550, 400);

    }

    public void LogOut(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("/sample/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);

    }
}
