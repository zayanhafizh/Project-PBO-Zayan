package Controller;

import Model.database;
import View.mainMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class addMhsController {
    Connection conn = null;
    PreparedStatement pst = null;

    public void addIdentity(int nim, String nama, String kelas, String dosen_pa, String jenis_kelamin) {
        conn = database.java_db(); // Koneksi ke database
        String sql = "INSERT INTO mahasiswa (nim, nama, kelas, dosen_pa, jenis_kelamin) VALUES (?, ?, ?, ?, ? )";

        try {
            // Prepare the SQL statement
            pst = conn.prepareStatement(sql);
            pst.setInt(1, nim);
            pst.setString(2, nama);
            pst.setString(3, kelas);
            pst.setString(4, dosen_pa);
            pst.setString(5, jenis_kelamin);

            // Execute the insert operation
            int rowsInserted = pst.executeUpdate();

            // Check if the insert was successful
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data mahasiswa successfully inserted.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Resource Closing Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void addMatkul(int nim_mhs, String nama_matkul, double nilai_tugas, double nilai_uts, double nilai_uas, double nilai_akhir, String grade) {
        conn = database.java_db(); // Koneksi ke database
        String sql = "INSERT INTO matkul(nama_matkul, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, nim_mhs, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Prepare the SQL statement
            pst = conn.prepareStatement(sql);
            pst.setString(1, nama_matkul);
            pst.setDouble(2, nilai_tugas);
            pst.setDouble(3, nilai_uts);
            pst.setDouble(4, nilai_uas);
            pst.setDouble(5, nilai_akhir);
            pst.setInt(6, nim_mhs);
            pst.setString(7, grade);

            // Execute the insert operation
            int rowsInserted = pst.executeUpdate();

            // Check if the insert was successful
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data successfully inserted.");
                mainMenu main = new mainMenu();
                main.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Resource Closing Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
