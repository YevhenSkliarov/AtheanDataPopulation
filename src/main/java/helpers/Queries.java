package helpers;

import Entity.Customer;
import Entity.CustomerDao;

import java.sql.*;

public class Queries {
    Config config = new Config();
    public void copyFromExistingTable(Connection conn, String table) {
        String copyQuery = "INSERT INTO " + config.getDatabaseName() + "." + table + " SELECT * FROM "
                + config.getDatabaseName() + "." + config.getCopyFrom();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(copyQuery);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("data imported in " + table);
    }

    public void createTableIfNotExist(Connection conn, String table, String output) {
        String createTableQuery = "CREATE EXTERNAL TABLE IF NOT EXISTS " + config.getDatabaseName() + "." + table +
                "(id int, first_name string, last_name string, full_name string, address string, " +
                "email string, birthday string, zipcode string, country string ) " +
                "LOCATION " + output;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableIfNotExist(Connection conn, String table) {
        String addColumn = "ALTER TABLE " + config.getDatabaseName() + "." + table + " ADD COLUMNS (country string)";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(addColumn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataInTable(Connection conn,String athenaJDBCUrl, String table, int rowsCount) {
        for (int j = 0; j < rowsCount; j++) {
            try {
                Customer customer = new CustomerDao().getData();
                if (conn.isClosed()) {
                    conn = DriverManager.getConnection(athenaJDBCUrl);
                }
                System.out.println("row " + (j + 1) + ", from " + rowsCount);
                // Define the SQL INSERT statement.
                String insertQuery = "INSERT INTO " + config.getDatabaseName() + "." + table +
                        " (id, first_name, last_name, full_name, address, email, birthday, zipcode, country) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";
                // Create a prepared statement.
                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
                // Set the parameter values.
                preparedStatement.setInt(1, customer.getId());
                preparedStatement.setString(2, customer.getFirst_name());
                preparedStatement.setString(3, customer.getLast_name());
                preparedStatement.setString(4, customer.getFull_name());
                preparedStatement.setString(5, customer.getAddress());
                preparedStatement.setString(6, customer.getEmail());
                preparedStatement.setString(7, customer.getBirthday());
                preparedStatement.setString(8, customer.getZipcode());
                preparedStatement.setString(9, customer.getCountry());
                // Execute the INSERT statements.
                preparedStatement.executeUpdate();
                System.out.println((j + 1) + " row inserted into " + config.getDatabaseName() + "." + table);
                // Close the connection.
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
