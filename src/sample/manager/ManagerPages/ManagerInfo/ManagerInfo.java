package sample.manager.ManagerPages.ManagerInfo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerInfo {
    public Button CloseButton;

    public void CloseWindow(ActionEvent actionEvent) {
// get a handle to the stage
        Stage stage = (Stage)CloseButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
