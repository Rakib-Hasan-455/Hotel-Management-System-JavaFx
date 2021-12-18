package sample._BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static final String DB_NAME = "HMS0";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";

    public static Connection connection = null;

    public static Connection getConnections(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+DB_NAME, USERNAME, PASSWORD
            );
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean checkConnections(){
        return connection != null;
    }

    public static void closeConnections(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
