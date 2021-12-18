package sample.zadmin.AdminPages;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.CustomerCheckOutTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import static sample._BackEnd.DBConnection.closeConnections;
import static sample.customer.Login.UserLogin.currentCustomerNID;

public class AdminTotalEarnings extends DBConnection implements Initializable {
    public Label earnMonth;
    public Label earnYear;
    public Label earnAll;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEarnInfo();
    }

    private void setEarnInfo() {
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
                String sql1 = "SELECT TOTALPRICE FROM CHECKINOUTINFO WHERE MONTH (CHECKEDOUT) = MONTH (curdate()) AND YEAR (CHECKEDOUT) = ? AND CHECKEDOUT IS NOT NULL";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setString(1, year);
                ResultSet resultSet1 = statement1.executeQuery();
                long currMonthSum = 0;
                long currYearSum = 0;
                long allYearSum = 0;
                while (resultSet1.next()){
                    currMonthSum += Long.parseLong(resultSet1.getString("TOTALPRICE"));
                }
                String sql2 = "SELECT TOTALPRICE FROM CHECKINOUTINFO WHERE YEAR (CHECKEDOUT) = YEAR (NOW()) AND CHECKEDOUT IS NOT NULL;";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet resultSet2 = statement2.executeQuery();
                while (resultSet2.next()){
                    currYearSum += Long.parseLong(resultSet2.getString("TOTALPRICE"));
                }
                String sql3 = "SELECT TOTALPRICE FROM CHECKINOUTINFO WHERE CHECKEDOUT IS NOT NULL;";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                ResultSet resultSet3 = statement3.executeQuery();
                while (resultSet3.next()){
                    allYearSum += Long.parseLong(resultSet3.getString("TOTALPRICE"));
                }
                earnMonth.setText(currMonthSum+"");
                earnYear.setText(currYearSum+"");
                earnAll.setText(allYearSum+"");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }


}
