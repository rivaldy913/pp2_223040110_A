package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_database"; // Replace with your DB URL
        String user = "root"; // Replace with your DB username
        String password = ""; // Replace with your DB password
        return DriverManager.getConnection(url, user, password);
    }
}
