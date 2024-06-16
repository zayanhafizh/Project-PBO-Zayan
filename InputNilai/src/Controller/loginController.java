/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.user;
import Model.database;
import View.mainMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author M Zayan Hafizh H
 */
public class loginController {
    public boolean validateLogin(String Username, String Password, Connection conn) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        // Use hashed password comparison in practice, here just for demonstration
        String sql = "SELECT * FROM users WHERE (username = ? AND password = ?)";
        try {
            // Prepare the SQL statement
            pst = conn.prepareStatement(sql);
            pst.setString(1, Username);
            pst.setString(2, Password);  // Replace with hashed password

            // Execute query
            rs = pst.executeQuery();

            // Process the result set
            if (rs.next()) {
                int id = rs.getInt("id");
                JOptionPane.showMessageDialog(null, "Login successful. Welcome!");
                mainMenu main = new mainMenu();
                main.setVisible(true);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid login or user not found.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Resource Closing Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }
}
