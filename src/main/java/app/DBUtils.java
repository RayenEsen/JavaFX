package app;

import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Opens connection to database and ensures the required database exists.
 */
public class DBUtils {

    private static String serverUrl = "jdbc:mysql://localhost:3306"; // Only server, no database
    private static String databaseName = "ysofthr";
    private static String fullUrl = serverUrl + "/" + databaseName; // Full URL once DB exists
    private static String username = "root";
    private static String password = ""; // No password

    /**
     * Creates the 'ysofthr' database if it doesn't exist.
     */
    public static void createDatabase() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName + " CHARACTER SET utf8";

        try (Connection con = DriverManager.getConnection(serverUrl, username, password);
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.execute();
            System.out.println("Database '" + databaseName + "' checked or created.");
        } catch (SQLException e) {
            showError("Database Creation Error", "There was an error while creating or checking the database.");
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection to the MySQL database.
     * @return Connection to the database
     * @throws SQLException if the connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(fullUrl, username, password);
    }

    /**
     * Utility method to show an error alert.
     * @param title The title of the error alert
     * @param message The message to show in the alert
     */
    private static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
