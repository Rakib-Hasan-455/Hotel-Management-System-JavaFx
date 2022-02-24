package sample._BackEnd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample._BackEnd.TableView.ResizeHelper;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class CommonTask extends Main {
    public static Stage newStage;
    public static double xx, yy;

    public static void pageNavigation(String to, Stage stage, Class<?> classes, String title, int width, int height) throws IOException {
        double xTemp = x;
        double yTemp = y;
//        String stageValue = String.valueOf(stage);
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
//        System.out.println(stageValue);
//        if(!stageValue.equals("null")) {
//            System.out.println(stageValue);
            stage.show();
//        } else {
//            System.out.println(stageValue);
//            stage.showAndWait();
//        }
    }

    public static void showAlert(Alert.AlertType type, String title, String header){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
       // alert.setContentText(message);
        alert.show();
    }

    public static void showJFXAlert(StackPane rootPane, AnchorPane rootAnchorPane, String type, String heading, String message, JFXDialog.DialogTransition transition){
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setMaxWidth(230);
        layout.setMaxHeight(100);

        JFXButton okayBtn = new JFXButton("Okay");
        okayBtn.setStyle("-fx-background-color:#4A4A4A; -fx-text-fill: white; -fx-border-color: white");
//        layout.setStyle("-fx-background-color: #25263A; -fx-text-fill: white; -fx-border-color: white; -fx-fill: white; -fx-progress-color: white");
//        layout.lookup(".dialog-pane:header .header-panel .label").setStyle("-fx-text-fill: white; -fx-background-color: #25263a");
//        layout.getStylesheets().add(getClass().getResource("/sample/customer/CustomerPages/CSS/imageSet.css").toExternalForm());
        layout.getStylesheets().add("/sample/_BackEnd/dialogPane.css");
        JFXDialog dialog = new JFXDialog(rootPane, layout, transition);
        okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
        });
        if(type.toLowerCase(Locale.ROOT).equals("information")) {
            layout.setHeading(new Label("✅  "+heading));
        } else if(type.toLowerCase(Locale.ROOT).equals("warning")) {
            layout.setHeading(new Label("💢  "+heading));
        } else if(type.toLowerCase(Locale.ROOT).equals("error")) {
            layout.setHeading(new Label("❌ "+heading));
        } else {
            layout.setHeading(new Label("⚠ "+heading));
        }
        layout.setBody(new Label(""+message));
        layout.setActions(okayBtn);
        layout.setPadding(new Insets(0, 15, 13, 0));
        dialog.show();

        BoxBlur blur = new BoxBlur(5, 5, 4);
        rootAnchorPane.setEffect(blur);
        dialog.setOnDialogClosed((JFXDialogEvent event) -> {
            rootAnchorPane.setEffect(null);
        });
    }

}
