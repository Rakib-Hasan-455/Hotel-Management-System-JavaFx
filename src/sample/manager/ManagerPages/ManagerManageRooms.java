package sample.manager.ManagerPages;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.ManagerRoomTable;
import sample.manager.ManagerPages.RoomInfoEdit.RoomInfoEdit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static sample.Main.xxx;
import static sample.Main.yyy;

public class ManagerManageRooms extends DBConnection implements Initializable {

    

    public TableView<ManagerRoomTable> roomTable;
    public TableColumn<ManagerRoomTable, String> roomNoCol;
    public TableColumn<ManagerRoomTable, String> roomTypeCol;
    public TableColumn<ManagerRoomTable, String> roomCapacityCol;
    public TableColumn<ManagerRoomTable, String> price_DayCol;
    public TableColumn<ManagerRoomTable, String> roomStatusCol;
    public JFXComboBox roomStatusChoiceBox;
    public JFXTextField roomTypeField;
    public JFXTextField bedCapacityField;
    public JFXTextField price_dayField;
    public JFXTextField roomNoField;
    public TableColumn actionCol;

    private String[] roomStats = {"Available", "Unavailable"};

    private ObservableList<ManagerRoomTable> TABLEROW = FXCollections.observableArrayList();

    public void addRoom(ActionEvent actionEvent) throws SQLException {
        Connection connection = getConnections();
        String roomNo = roomNoField.getText();
        String bedCapacity = bedCapacityField.getText();
        String roomType = roomTypeField.getText();
        String price_day = price_dayField.getText();
        String roomStatus = roomStatusChoiceBox.getValue()+"";

        if (roomNo.isEmpty() || bedCapacity.isEmpty()  || roomType.isEmpty() || price_day.isEmpty() || roomStatus.equals("null")) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "INSERT INTO ROOMINFO (ROOM_NO, TYPE, CAPACITY, PRICE_DAY, STATUS) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomNo);
            preparedStatement.setString(2, roomType);
            preparedStatement.setString(3, bedCapacity);
            preparedStatement.setString(4, price_day);
            preparedStatement.setString(5, roomStatus);
            try{
                preparedStatement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Room Added Successfully!");
                showRoomTable();
            } catch (SQLException e){
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room no. already exists!");
            } finally {
                closeConnections();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomStatusChoiceBox.getItems().setAll(roomStats);
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("ROOMNO"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("TYPE"));
        roomCapacityCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("CAPACITY"));
        price_DayCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("PRICEDAY"));
        roomStatusCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("STATUS"));
        showRoomTable();
        actionButtons();
    }

    public void showRoomTable(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM ROOMINFO ORDER BY STATUS";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String ROOMNO = resultSet.getString("ROOM_NO"); //SQL COL NAMES NID
                    String TYPE = resultSet.getString("TYPE");
                    String CAPACITY = resultSet.getString("CAPACITY");
                    String PRICEDAY = resultSet.getString("PRICE_DAY");
                    String STATUS = resultSet.getString("STATUS");

                    ManagerRoomTable roomTablee = new ManagerRoomTable(ROOMNO, TYPE, CAPACITY, PRICEDAY, STATUS);

                    TABLEROW.add(roomTablee);
                }
                roomTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    private void actionButtons() {
        Callback<TableColumn<ManagerRoomTable, String>, TableCell<ManagerRoomTable, String>> cellCallback =
                new Callback<TableColumn<ManagerRoomTable, String>, TableCell<ManagerRoomTable, String>>() {
                    @Override
                    public TableCell<ManagerRoomTable, String> call(TableColumn<ManagerRoomTable, String> param) {

                        TableCell<ManagerRoomTable, String> cell = new TableCell<ManagerRoomTable, String>() {

                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

                            public HBox hBox = new HBox(25, editIcon, deleteIcon);

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty){
                                    setGraphic(null);
                                    setText(null);
                                }else{

                                    deleteIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    deleteIcon.setOnMouseEntered((MouseEvent event) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    deleteIcon.setOnMouseExited((MouseEvent event2) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    deleteIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );

                                        ManagerRoomTable managerRoomTable = getTableView().getItems().get(getIndex());
                                        tableRowDelete(managerRoomTable);

                                    });

                                    editIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    editIcon.setOnMouseEntered((MouseEvent event) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    editIcon.setOnMouseExited((MouseEvent event2) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    editIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );
                                        ManagerRoomTable managerRoomTable = getTableView().getItems().get(getIndex());
//                                        System.out.println(managerRoomTable.getROOMNO());
                                        try {
                                            editTableRowInfo(managerRoomTable);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    });



                                    hBox.setStyle("-fx-alignment:center");
//                                    hBox.setMaxWidth(40);
//                                    HBox.setMargin(editIcon, new Insets(2, 10, 0, 10));
//                                    HBox.setMargin(deleteIcon, new Insets(2, 10, 0, 10));
                                    setGraphic(hBox);
                                }
                            }
                        };

                        return cell;
                    }
                };
        actionCol.setCellFactory(cellCallback);
    }

    public void tableRowDelete(ManagerRoomTable managerRoomTable) {
        String roomStatus = managerRoomTable.getSTATUS();
        if (!roomStatus.equals("Booked")) {
            Connection connection = getConnections();
            try {
                if (!connection.isClosed()) {
                    String sql = "DELETE FROM RoomInfo where ROOM_NO=?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, managerRoomTable.getROOMNO());

                    statement.execute();

                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Delete Operation Successfull", "Room No " + managerRoomTable.getROOMNO() + " is deleted from database!");

                    roomTable.getItems().remove(managerRoomTable);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                closeConnections();
            }
        } else {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Delete Unsuccessful", "Can't delete. It's currently booked by a customer.");
        }
    }

    public void editTableRowInfo(ManagerRoomTable managerRoomTable) throws IOException {
        if (!(managerRoomTable.getSTATUS()).equals("Booked")) {
            Connection connection = getConnections();
            try {
                if (!connection.isClosed()) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/manager/ManagerPages/RoomInfoEdit/roomInfoEdit.fxml"));
                    Parent viewContact = loader.load();
                    Scene scene = new Scene(viewContact);
                    // update information
                    RoomInfoEdit roomInfoEdit = loader.getController();
                    roomInfoEdit.setRoomInfo(managerRoomTable.getROOMNO(), managerRoomTable.getTYPE(), managerRoomTable.getCAPACITY(), managerRoomTable.getPRICEDAY(), managerRoomTable.getSTATUS());
//                    System.out.println(managerRoomTable.getROOMNO() + " " + managerRoomTable.getTYPE() + " " + managerRoomTable.getCAPACITY() + " " + managerRoomTable.getPRICEDAY());
                    Stage window = new Stage();
                    window.setScene(scene);
                    window.initStyle(StageStyle.UNDECORATED);

                    stagePosition(window, viewContact);

                    window.showAndWait();
                    showRoomTable();
                }

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            } finally {
                closeConnections();
            }
        } else {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Can't Edit!", " Currently booked by a customer.");
        }
    }

    public void stagePosition(Stage primaryStage, Parent root) {
        AtomicReference<Double> x = new AtomicReference<>(primaryStage.getX());
        AtomicReference<Double> y = new AtomicReference<>(primaryStage.getY());
        root.setOnMousePressed(event -> {
            xxx = event.getSceneX();
            yyy = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
//            if(event.getButton() == MouseButton.SECONDARY) {
            primaryStage.setX(event.getScreenX() - xxx);
            primaryStage.setY(event.getScreenY() - yyy);
            x.set(primaryStage.getX());
            y.set(primaryStage.getY());
//            }
        });
    }

}

