package sample.customer.CustomerPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.CustomerCheckOutTable;
import sample._BackEnd.TableView.CustomerRoomTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.customer.Login.UserLogin.currentCustomerNID;

public class UserCheckOutDetails extends DBConnection implements Initializable {

    @FXML
    private TableView<CustomerCheckOutTable> UserCheckOutDetailsTable;
    public TableColumn<CustomerCheckOutTable, String> nidCol;
    public TableColumn<CustomerCheckOutTable, String> roomNoCol;
    public TableColumn<CustomerCheckOutTable, String> checkedInCol;
    public TableColumn<CustomerCheckOutTable, String> checkedOutCol;
    public TableColumn<CustomerCheckOutTable, String> priceDayCol;
    public TableColumn<CustomerCheckOutTable, String> totalPriceCol;
    public TableColumn slipCol;

    private ObservableList<CustomerCheckOutTable> TABLEROW = FXCollections.observableArrayList();
    @FXML
    private TextField UserCheckOutDetailsSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nidCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("nid"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("roomNo"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("checkedIndate"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("checkedOutDate"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("priceDay"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("totalPrice"));

        showcheckInOutDetails();
        slipDownloadBtn();
    }


    public void showcheckInOutDetails(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CHECKINOUTINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String NID = resultSet.getString("NID");
                    String RoomNo = resultSet.getString("ROOMNO");
                    String CheckedInDate = resultSet.getString("CHECKEDIN");
                    String CheckedOutDate = resultSet.getString("CHECKEDOUT");
                    String PriceDay = resultSet.getString("PRICEDAY");
                    String TotalPrice = resultSet.getString("TOTALPRICE");

                    CustomerCheckOutTable roomTablee = new CustomerCheckOutTable(NID, RoomNo, CheckedInDate, CheckedOutDate, PriceDay, TotalPrice);

                    TABLEROW.add(roomTablee);
                }
                UserCheckOutDetailsTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    private void slipDownloadBtn() {
        Callback<TableColumn<CustomerCheckOutTable, String>, TableCell<CustomerCheckOutTable, String>> cellCallback =
                new Callback<TableColumn<CustomerCheckOutTable, String>, TableCell<CustomerCheckOutTable, String>>() {
                    @Override
                    public TableCell<CustomerCheckOutTable, String> call(TableColumn<CustomerCheckOutTable, String> param) {

                        TableCell<CustomerCheckOutTable, String> cell = new TableCell<CustomerCheckOutTable, String>() {

                            FontAwesomeIconView downloadIcon = new FontAwesomeIconView(FontAwesomeIcon.DOWNLOAD);

                            final HBox hBox = new HBox(downloadIcon);
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty){
                                    setGraphic(null);
                                    setText(null);
                                }else{

                                    downloadIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                            + "-glyph-size:20px;"
                                            + "-fx-fill:#ffffff;"
                                    );

                                    downloadIcon.setOnMouseEntered((MouseEvent event) ->{
                                        downloadIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                "-glyph-size:20px;"
                                                +"-fx-fill:khaki;"
                                        );
                                    });

                                    downloadIcon.setOnMouseExited((MouseEvent event2) ->{
                                            downloadIcon.setStyle(
                                                    " -fx-cursor: hand ;"
                                                            +
                                                            "-glyph-size:20px;"
                                                            + "-fx-fill:white;"
                                            );
                                    });

                                    downloadIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        downloadIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );

                                        //PDF generate function

                                    });

//                                    downloadIcon.setOnMouseClicked((MouseEvent event)->{
//
//                                    });

                                    hBox.setStyle("-fx-alignment:center");
//                                    HBox.setMargin(download, new Insets(2, 7, 0, 2));
//                                    HBox.setMargin(download, new Insets(2, 2, 0, 7));
                                    setGraphic(hBox);
                                }
                            }
                        };

                        return cell;
                    }
                };
        slipCol.setCellFactory(cellCallback);
    }
}