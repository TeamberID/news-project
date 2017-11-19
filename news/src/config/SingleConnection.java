package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingleConnection {
    private static Connection connection = null;


    public static Connection getConnection() {
        if (connection == null){
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news-project",
                        "postgres",
                        "postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return  connection;
        }
        return connection;
    }
}