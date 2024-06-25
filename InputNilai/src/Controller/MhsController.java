package Controller;

import Model.Database;
import View.MainMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MhsController {
    Connection conn = null;
    PreparedStatement pst = null;

    public int addIdentity(int nim, String nama, String kelas, String dosen_pa, String jenis_kelamin) {
        conn = Database.java_db(); // Koneksi ke database
        String sql = "INSERT INTO mahasiswa (nim, nama, kelas, dosen_pa, jenis_kelamin) VALUES (?, ?, ?, ?, ? )";
        int rowsInserted = 0;

        // Convert NIM to String for validation
        String nimStr = String.valueOf(nim);

        // Validate NIM format
        if (nimStr.length() != 9 ||
                !(nimStr.startsWith("22") || nimStr.startsWith("21") || nimStr.startsWith("11")) ||
                !(nimStr.substring(2, 4).equals("23") || nimStr.substring(2, 4).equals("22") || nimStr.substring(2, 4).equals("21") || nimStr.substring(2, 4).equals("20")) ||
                !nimStr.substring(4, 6).equals("12")) {
            JOptionPane.showMessageDialog(null, "NIM yang anda masukkan salah.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return rowsInserted;
        }

        try {
            // Prepare the SQL statement
            pst = conn.prepareStatement(sql);
            pst.setInt(1, nim);
            pst.setString(2, nama);
            pst.setString(3, kelas);
            pst.setString(4, dosen_pa);
            pst.setString(5, jenis_kelamin);

            // Execute the insert operation
            rowsInserted = pst.executeUpdate();

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
        return rowsInserted;
    }


    
    public int addMatkul(int nim_mhs, String nama_matkul, double nilai_tugas, double nilai_uts, double nilai_uas, double nilai_akhir, String grade) {
        // Validate the input values
        if (nilai_tugas < 0 || nilai_tugas > 100 || nilai_uts < 0 || nilai_uts > 100 || nilai_uas < 0 || nilai_uas > 100) {
            JOptionPane.showMessageDialog(null, "Nilai tugas, UTS, dan UAS harus di antara 0 dan 100.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
        conn = Database.java_db(); // Koneksi ke database
        String sql = "INSERT INTO matkul(nama_matkul, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, nim_mhs, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int rowsInserted = 0;
        
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
            rowsInserted = pst.executeUpdate();

            // Check if the insert was successful
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data successfully inserted.");
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
        return rowsInserted;
    }
    
    public int editMhs(int id, int nim, String nama, String kelas, String dosen_pa, String jenis_kelamin) {
        Connection conn = Database.java_db(); // Mendapatkan koneksi dari kelas database
        int rowsUpdated = 0;

        // Convert NIM to String for validation
        String nimStr = String.valueOf(nim);

        // Validate NIM format
        if (nimStr.length() != 9 ||
                !(nimStr.startsWith("22") || nimStr.startsWith("21") || nimStr.startsWith("11")) ||
                !(nimStr.substring(2, 4).equals("23") || nimStr.substring(2, 4).equals("22") || nimStr.substring(2, 4).equals("21") || nimStr.substring(2, 4).equals("20")) ||
                !nimStr.substring(4, 6).equals("12")) {
            JOptionPane.showMessageDialog(null, "NIM yang anda masukkan salah.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return rowsUpdated;
        }

        if (conn != null) {
            String query = "UPDATE mahasiswa SET nama = ?, kelas = ?, dosen_pa = ?, jenis_kelamin = ?, nim = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nama);
                stmt.setString(2, kelas);
                stmt.setString(3, dosen_pa);
                stmt.setString(4, jenis_kelamin);
                stmt.setInt(5, nim);
                stmt.setInt(6, id);

                rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diupdate");
                    // Perbarui tampilan di listMahasiswa jika berhasil update
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal mengupdate data mahasiswa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengupdate data", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                Database.closeConnection(conn);
            }
        }

        return rowsUpdated;
    }
    
    public int editMatkul(int id, String nama_matkul, double nilai_tugas, double nilai_uts, double nilai_uas, double nilai_akhir, String grade) {
        // Validate the input values
        if (nilai_tugas < 0 || nilai_tugas > 100 || nilai_uts < 0 || nilai_uts > 100 || nilai_uas < 0 || nilai_uas > 100) {
            JOptionPane.showMessageDialog(null, "Nilai tugas, UTS, dan UAS harus di antara 0 dan 100.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        Connection conn = Database.java_db();
        int rowsUpdated = 0;

        if (conn != null) {
            String query = "UPDATE matkul SET nama_matkul = ?, nilai_tugas = ?, nilai_uts = ?, nilai_uas = ?, nilai_akhir = ?, grade = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nama_matkul);
                stmt.setDouble(2, nilai_tugas);
                stmt.setDouble(3, nilai_uts);
                stmt.setDouble(4, nilai_uas);
                stmt.setDouble(5, nilai_akhir);
                stmt.setString(6, grade);
                stmt.setInt(7, id);

                rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data nilai berhasil diperbarui", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data nilai gagal diperbarui", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengupdate data", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                Database.closeConnection(conn);
            }
        }
        return rowsUpdated;
    }
}
