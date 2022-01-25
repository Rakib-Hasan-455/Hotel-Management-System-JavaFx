package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample._BackEnd.TableView.ResizeHelper;

public class Main extends Application {

    public static Stage stage;
    public static double x, y;
    public static double xxx, yyy;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Hotel Management System");
        primaryStage.setScene(new Scene(root, 600, 400));
//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        primaryStage.setX((primScreenBounds.getWidth() - 600) / 2);
//        primaryStage.setY((primScreenBounds.getHeight() - 400) / 2);
        primaryStage.setX(140);
        primaryStage.setY(130);
        x = primaryStage.getX();
        y = primaryStage.getY();
        root.setOnMousePressed(event -> {
            xxx = event.getSceneX();
            yyy = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
//            if(event.getButton() == MouseButton.SECONDARY) {
                primaryStage.setX(event.getScreenX() - xxx);
                primaryStage.setY(event.getScreenY() - yyy);
                x = primaryStage.getX();
                y = primaryStage.getY();
//            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
