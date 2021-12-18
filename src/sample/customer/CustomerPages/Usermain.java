package sample.customer.CustomerPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static sample._BackEnd.CommonTask.newStage;

public class Usermain implements Initializable {

    public BorderPane borderpane;
    public Button goHomeID;
    public Button roomDetailsID;
    public Button checkInID;
    public Button checkInOutID;
    public FontAwesomeIconView closeWindow;
    public FontAwesomeIconView minimizeWindow;
    public FontAwesomeIconView maximizeWindow;
    public AnchorPane userMainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        windowLoad("UserHome.fxml");

        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });

        minimizeWindow.setOnMouseClicked(event -> {
            minimizeStageOfNode((Node) event.getSource());
        });

        AtomicInteger maxWindow = new AtomicInteger();
        maximizeWindow.setOnMouseClicked(event -> {
            Stage stage1 = (Stage) userMainPane.getScene().getWindow();
            stage1.setMaximized(!stage1.isMaximized());
        });
    }

    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    public void windowLoad(String URL)
    {
        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(pane);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
        }
    }

    public void GoHome(ActionEvent actionEvent) {
        windowLoad("UserHome.fxml");
    }

    public void GoRoomDetails(ActionEvent actionEvent) {
        windowLoad("UserRoomDetails.fxml");
    }

    public void GoCheckIn(ActionEvent actionEvent) {
        windowLoad("UserCheckIn.fxml");
    }

    public void GoCheckDetails(ActionEvent actionEvent) {
        windowLoad("UserCheckOutDetails.fxml");
    }
}
