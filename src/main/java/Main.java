import helpers.Config;
import helpers.Queries;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            String configFilePath = "./src/main/resources/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String athenaJDBCUrl = "jdbc:awsathena://AwsRegion="+prop.getProperty("REGION")+";" +
                    "User=" + prop.getProperty("ACCESS_KEY") + ";" +
                    "Password=" + prop.getProperty("SECRET_KEY") + ";" +
                    "S3OutputLocation=" + prop.getProperty("S3_OUTPUT_LOCATION") + ";";
            Config config = new Config();
            Queries query = new Queries();

            try {
                // Register the Athena JDBC driver.
                Class.forName("com.simba.athena.jdbc.Driver");

                // Create the JDBC connection.
                Connection conn = DriverManager.getConnection(athenaJDBCUrl);
                for (int i = 0; i < config.getTablesCount(); i++) {
                    if (conn.isClosed()) {
                        conn = DriverManager.getConnection(athenaJDBCUrl);
                    }
                    String table = config.getTableName() + (i + 1);
                    String output = String
                            .format(prop.getProperty("OUTPUT_SUBFOLDER"), (i + 1) + "/");
                    System.out.println(output);
                    // Create table.
                    query.createTableIfNotExist(conn, table, output);
                    if(config.isCopyFromExistingTable()) {
                        query.copyFromExistingTable(conn, table);
                        conn.close();
                    } else {
                        if (config.isAddColumnToExistingTable()) {
                            query.createTableIfNotExist(conn, table);
                        }
                        query.insertDataInTable(conn, athenaJDBCUrl, table, config.getRowsCount());
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
