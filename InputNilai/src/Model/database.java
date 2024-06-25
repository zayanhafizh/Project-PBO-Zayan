/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author M Zayan Hafizh H
 */
public class Database {
    public static Connection java_db() {
        Connection conn = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish the connection using the correct SQLite database URL
            String dbPath = "D:\\Semester 4\\PBO\\InputNilai\\InputNilai\\input_nilai.sqlite";
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            return conn;
        } catch (ClassNotFoundException e) {
            // Handle the case where the JDBC driver is not found
            JOptionPane.showMessageDialog(null, "SQLite JDBC driver not found: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQL-related errors
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Failed to close database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
