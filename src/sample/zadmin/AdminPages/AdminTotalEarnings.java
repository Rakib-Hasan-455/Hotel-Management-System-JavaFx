package sample.zadmin.AdminPages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import sample._BackEnd.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AdminTotalEarnings extends DBConnection implements Initializable {
    public Label earnMonth;
    public Label earnYear;
    public Label earnAll;

    public static long currMonthSum = 0;
    public static long currYearSum = 0;
    public static long allYearSum = 0;
    public PieChart piechart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currMonthSum = 0;
        currYearSum = 0;
        allYearSum = 0;
        setEarnInfo();
        loadPieChart();
    }

    private void loadPieChart() {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.add(new PieChart.Data("Earned This Month", currMonthSum));
        list.add(new PieChart.Data("Earned Current Year", currYearSum));
        piechart.setData(list);
//        piechart.setTitle("Earning Comparison");

//        piechart.getData().forEach(data -> {
//            String percentage = (data.getPieValue()+"");
//            Tooltip tooltip = new Tooltip(percentage);
//            Tooltip.install(data.getNode(), tooltip);
//        });
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


    public void reloadChart(MouseEvent event) {
        loadPieChart();
    }
}
