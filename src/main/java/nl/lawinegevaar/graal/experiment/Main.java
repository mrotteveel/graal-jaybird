package nl.lawinegevaar.graal.experiment;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    static void main(String[] args) throws SQLException {
        String url = args.length > 0 ? args[0] : "jdbc:firebird://127.0.0.1:3050/graaldb.fdb?createDatabaseIfNotExist=true";
        String user = args.length > 1 ? args[1] : "SYSDBA";
        String password = args.length > 2 ? args[2] : "masterkey";
        try (var connection = DriverManager.getConnection(url, user, password)) {
            try (var statement = connection.createStatement();
                 var resultSet = statement.executeQuery("select rdb$get_context('SYSTEM', 'ENGINE_VERSION') from rdb$database")) {
                resultSet.next();
                System.out.println("Firebird " + resultSet.getString(1));
            }
            try (var statement = connection.prepareStatement("select cast(? as integer) from rdb$database")) {
                statement.setInt(1, 42);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    System.out.println("Prepared statement: " + resultSet.getInt(1));
                }
            }
            // The following produces an error and can be used to verify error messages are included
            try (var statement = connection.prepareStatement("select ? from rdb$database")) {
                statement.setInt(1, 42);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    System.out.println("Prepared statement: " + resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Driver loaded; the connection failed: " + e.getMessage());
        }
    }
}
