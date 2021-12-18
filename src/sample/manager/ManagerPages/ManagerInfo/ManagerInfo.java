package sample.manager.ManagerPages.ManagerInfo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerInfo {
    public Button CloseWindow;

    public void CloseWindow(ActionEvent actionEvent) {
        Stage stage = (Stage)CloseWindow.getScene().getWindow();
        stage.close();
    }
}
