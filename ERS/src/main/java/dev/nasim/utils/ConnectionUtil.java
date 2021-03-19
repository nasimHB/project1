package dev.nasim.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){

        //"jdbc:postgresql://35.223.183.33:5432/ERSDB?user=nasim&password=Younajigari@8"
        String details = System.getenv("CONN_DETAILS");

        try {
            Connection conn = DriverManager.getConnection(details);
            return  conn;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }
}
