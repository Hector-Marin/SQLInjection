package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgConnection{

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://35.188.56.158:5432/SqlInjectionDB",
                    "postgres",
                    "notiene");
        }
        return connection;
    }
}
