import dao.implemetations.UsersDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tester {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/news-project",
                    "postgres",
                    "postgres");


            UsersDaoImpl usersDao = new UsersDaoImpl(connection);
            usersDao.delete(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
