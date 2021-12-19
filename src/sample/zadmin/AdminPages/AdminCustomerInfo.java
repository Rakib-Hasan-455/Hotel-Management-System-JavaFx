package sample.zadmin.AdminPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.AdminCustomerTable;
import sample._BackEnd.TableView.ManagerRoomTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminCustomerInfo extends DBConnection implements Initializable{
    public TableView<AdminCustomerTable> customerTable;
    public TableColumn<AdminCustomerTable, String> nidCol;
    public TableColumn<AdminCustomerTable, String> nameCol;
    public TableColumn<AdminCustomerTable, String> emailCol;
    public TableColumn<AdminCustomerTable, String> phoneCol;
    public TableColumn<AdminCustomerTable, String> addressCol;
    public TableColumn<AdminCustomerTable, String> passCol;
    public TableColumn actionCol;

    private ObservableList<AdminCustomerTable> TABLEROW = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TABLEROW.clear();
        nameCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Name")); //adminCustomerTable variable name
        nidCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("NID"));
        emailCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Address"));
        passCol.setCellValueFactory(new PropertyValueFactory<AdminCustomerTable, String>("Pass"));
        showCustomerTable();
        actionButtons();
    }

    public void showCustomerTable(){
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String NID = resultSet.getString("NID"); //SQL COL NAMES NID
                    String NAME = resultSet.getString("NAME");
                    String EMAIL = resultSet.getString("EMAIL");
                    String PHONE = resultSet.getString("PHONE");
                    String ADDRESS = resultSet.getString("ADDRESS");
                    String PASS = resultSet.getString("PASSWORD");

                    AdminCustomerTable custTable = new AdminCustomerTable(NID, NAME, EMAIL, PHONE, ADDRESS, PASS);

                    TABLEROW.add(custTable);
                }
                customerTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    private void actionButtons() {
        Callback<TableColumn<AdminCustomerTable, String>, TableCell<AdminCustomerTable, String>> cellCallback =
                new Callback<TableColumn<AdminCustomerTable, String>, TableCell<AdminCustomerTable, String>>() {
                    @Override
                    public TableCell<AdminCustomerTable, String> call(TableColumn<AdminCustomerTable, String> param) {

                        TableCell<AdminCustomerTable, String> cell = new TableCell<AdminCustomerTable, String>() {

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

                                        //delete sql statements

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

                                        //edit in new stage, need to do some code+fxml file stuff ahh

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

}
