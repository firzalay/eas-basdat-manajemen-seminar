package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.util.Properties;

public class ConnectionProvider {
    public static Connection getCon() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {

            fileInputStream = new FileInputStream("config.properties");
            properties.load(fileInputStream);

            String url = properties.getProperty("database.url");
            String username = properties.getProperty("database.username");
            String password = properties.getProperty("database.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    url,
                    username, password);
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}