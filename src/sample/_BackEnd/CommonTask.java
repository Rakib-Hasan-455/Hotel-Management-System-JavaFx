package sample._BackEnd;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample._BackEnd.TableView.ResizeHelper;

import java.io.IOException;
import java.util.Objects;

public class CommonTask extends Main {
    public static Stage newStage;
    public static double xx, yy;

    public static void pageNavigation(String to, Stage stage, Class<?> classes, String title, int width, int height) throws IOException {
        double xTemp = x;
        double yTemp = y;
        if (stage == null) {
            xTemp = x + (width/5.0);
            yTemp = y + (height/7.0);
            stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            newStage = stage;
        }
        newStage = stage;
        //stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(Objects.requireNonNull(classes.getResource(to)));
        stage.setTitle(title);
//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        System.out.println("x->"+x+" y->"+y);
        stage.setX(xTemp);
        stage.setY(yTemp);
        stage.setScene(new Scene(root, width, height));

        root.setOnMousePressed(event -> {
            xx = event.getSceneX();
            yy = event.getSceneY();
        });
        Stage finalStage = stage;
        root.setOnMouseDragged(event -> {
//            if(event.getButton() == MouseButton.SECONDARY) {
                finalStage.setX(event.getScreenX() - xx);
                finalStage.setY(event.getScreenY() - yy);
                x = finalStage.getX();
                y = finalStage.getY();
//            }
        });
        x = finalStage.getX();
        y = finalStage.getY();
//        ResizeHelper.addResizeListener(stage);
        stage.show();
    }

    public static void showAlert(Alert.AlertType type, String title, String header){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
       // alert.setContentText(message);
        alert.show();
    }

}
