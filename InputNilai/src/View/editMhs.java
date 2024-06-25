/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.MhsController;
import javax.swing.JOptionPane;
import Model.database;
import Model.mahasiswa;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
/**
 *
 * @author M Zayan Hafizh H
 */
public class editMhs extends javax.swing.JFrame {
private String nim;
private String id;
private mahasiswa mhs;
    /**
     * Creates new form editMhs
     */
    public editMhs(String nim) {
        initComponents();
        this.nim = nim;
        displayMahasiswa();
    }

    private void displayMahasiswa() {
        Connection conn = database.java_db(); // Mendapatkan koneksi dari kelas database

        if (conn != null) {
            String query = "SELECT * FROM mahasiswa WHERE nim = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nim);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String nama = rs.getString("nama");
                    String kelas = rs.getString("kelas");
                    String dosenPA = rs.getString("dosen_pa");
                    String jenis_kelamin = rs.getString("jenis_kelamin");
                    id = String.valueOf(rs.getInt("id"));

                    nama_txt.setText(nama);
                    nim_txt.setText(nim);
                    kelas_txt.setText(kelas);
                    dosenpa_txt.setText(dosenPA);
                    jk_combo.setSelectedItem(jenis_kelamin);
                } else {
                    JOptionPane.showMessageDialog(this, "Data mahasiswa tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
                    this.dispose(); // Tutup form jika data tidak ditemukan
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection(conn);
            }
        }
    }
    
    private void exportDataToPDF() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                // Menambahkan paragraf pembuka
                document.add(new Paragraph("Detail Identitas Mahasiswa\n\n"));

                // Menambah data mahasiswa
                Connection conn = database.java_db();
                if (conn != null) {
                    String query = "SELECT * FROM mahasiswa WHERE nim = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, nim);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            document.add(new Paragraph("Nama    : " + rs.getString("nama")));
                            document.add(new Paragraph("NIM     : " + rs.getString("nim")));
                            document.add(new Paragraph("Kelas   : " + rs.getString("kelas")));
                            document.add(new Paragraph("Dosen PA: " + rs.getString("dosen_pa")));
                            document.add(new Paragraph("\nDaftar nilai mahasiswa :\n"));
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Gagal mengambil data dari database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        database.closeConnection(conn);
                    }
                }

                // Membuat tabel dengan header
                PdfPTable table = new PdfPTable(6); // 6 kolom: nama_matkul, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, grade
                table.setWidthPercentage(100); // Set lebar tabel menjadi 100%
                table.setSpacingBefore(10f); // Spasi sebelum tabel
                table.setSpacingAfter(10f); // Spasi setelah tabel

                // Set kolom lebar (opsional, dapat disesuaikan)
                float[] columnWidths = {2f, 1f, 1f, 1f, 1f, 1f};
                table.setWidths(columnWidths);

                // Menambah header tabel
                String[] headers = {"Nama Matkul", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Grade"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(cell);
                }

                // Mengisi tabel dengan data matkul
                conn = database.java_db();
                if (conn != null) {
                    String query = "SELECT * FROM matkul WHERE nim_mhs = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, nim);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            PdfPCell cell1 = new PdfPCell(new Paragraph(rs.getString("nama_matkul")));
                            PdfPCell cell2 = new PdfPCell(new Paragraph(rs.getString("nilai_tugas")));
                            PdfPCell cell3 = new PdfPCell(new Paragraph(rs.getString("nilai_uts")));
                            PdfPCell cell4 = new PdfPCell(new Paragraph(rs.getString("nilai_uas")));
                            PdfPCell cell5 = new PdfPCell(new Paragraph(rs.getString("nilai_akhir")));
                            PdfPCell cell6 = new PdfPCell(new Paragraph(rs.getString("grade")));

                            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);

                            table.addCell(cell1);
                            table.addCell(cell2);
                            table.addCell(cell3);
                            table.addCell(cell4);
                            table.addCell(cell5);
                            table.addCell(cell6);
                        }
                        document.add(table); // Menambahkan tabel ke dokumen
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Gagal mengambil data dari database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        database.closeConnection(conn);
                    }
                }

                // Menutup dokumen PDF
                document.close();
                JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke PDF", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (DocumentException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengekspor data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        edit_button = new javax.swing.JButton();
        hapus_button = new javax.swing.JButton();
        listNilai_button = new javax.swing.JButton();
        export_button = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        nama_txt = new javax.swing.JTextField();
        nim_txt = new javax.swing.JTextField();
        kelas_txt = new javax.swing.JTextField();
        dosenpa_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jk_combo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        back_button.setBackground(new java.awt.Color(0, 0, 255));
        back_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        back_button.setForeground(new java.awt.Color(255, 255, 255));
        back_button.setText("Kembali");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        edit_button.setBackground(new java.awt.Color(0, 0, 255));
        edit_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_button.setText("Edit");
        edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_buttonActionPerformed(evt);
            }
        });

        hapus_button.setBackground(new java.awt.Color(0, 0, 255));
        hapus_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hapus_button.setForeground(new java.awt.Color(255, 255, 255));
        hapus_button.setText("Hapus");
        hapus_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_buttonActionPerformed(evt);
            }
        });

        listNilai_button.setBackground(new java.awt.Color(0, 0, 255));
        listNilai_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        listNilai_button.setForeground(new java.awt.Color(255, 255, 255));
        listNilai_button.setText("Lihat Daftar Nilai");
        listNilai_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listNilai_buttonActionPerformed(evt);
            }
        });

        export_button.setBackground(new java.awt.Color(0, 0, 255));
        export_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        export_button.setForeground(new java.awt.Color(255, 255, 255));
        export_button.setText("Export to PDF");
        export_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_buttonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        nama_txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        nim_txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        kelas_txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        dosenpa_txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nama :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("NIM :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Kelas :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Dosen PA :");

        jk_combo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jk_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - laki", "Perempuan", " " }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Jenis Kelamin :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dosenpa_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jk_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nim_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                        .addComponent(kelas_txt)
                        .addComponent(nama_txt, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nama_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nim_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kelas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jk_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dosenpa_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(38, 38, 38))
        );

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel6.setText("Detail Informasi Mahasiswa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 83, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(hapus_button, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(listNilai_button)
                .addGap(32, 32, 32)
                .addComponent(export_button, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(264, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(219, 219, 219))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listNilai_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(export_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_buttonActionPerformed
        // TODO add your handling code here:
        int id_baru = Integer.parseInt(id);
        int nim_baru = Integer.parseInt(nim_txt.getText());
        String nama = nama_txt.getText();
        String kelas = kelas_txt.getText();
        String dosenPA = dosenpa_txt.getText();
        String jenisKelamin = jk_combo.getSelectedItem().toString();

        Connection conn = database.java_db(); // Mendapatkan koneksi dari kelas database

        MhsController edit = new MhsController();
        int rowsUpdated = edit.editMhs(id_baru, nim_baru, nama, kelas, dosenPA, jenisKelamin);
        
//        if (conn != null) {
//            String query = "UPDATE mahasiswa SET nama = ?, kelas = ?, dosen_pa = ?, jenis_kelamin = ? ,nim = ? WHERE id = ?";
//
//            try (PreparedStatement stmt = conn.prepareStatement(query)) {
//                stmt.setString(1, nama);
//                stmt.setString(2, kelas);
//                stmt.setString(3, dosenPA);
//                stmt.setString(4, jenisKelamin);
//                stmt.setString(5, nim);
//                stmt.setString(6, id);
//
//                int rowsUpdated = stmt.executeUpdate();
//
//                if (rowsUpdated > 0) {
//                    JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil diupdate");
//                    // Perbarui tampilan di listMahasiswa jika berhasil update
//                } else {
//                    JOptionPane.showMessageDialog(this, "Gagal mengupdate data mahasiswa", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengupdate data", "Error", JOptionPane.ERROR_MESSAGE);
//            } finally {
//                database.closeConnection(conn);
//            }
//        }
    }//GEN-LAST:event_edit_buttonActionPerformed

    private void hapus_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_buttonActionPerformed
        // TODO add your handling code here:
        Connection conn = database.java_db(); // Mendapatkan koneksi dari kelas database

        if (conn != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data mahasiswa ini?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String query = "DELETE FROM mahasiswa WHERE id = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, id);

                    int rowsDeleted = stmt.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil dihapus");
                        this.dispose(); // Tutup form editMhs setelah berhasil hapus
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal menghapus data mahasiswa", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    database.closeConnection(conn);
                }
            }
        }
    }//GEN-LAST:event_hapus_buttonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        // TODO add your handling code here:
        listMahasiswa listMhs = new listMahasiswa();
        listMhs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

    private void listNilai_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listNilai_buttonActionPerformed
        // TODO add your handling code here:
        listNilai listNilai = new listNilai(nim);
        listNilai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_listNilai_buttonActionPerformed

    private void export_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_buttonActionPerformed
        // TODO add your handling code here:
        exportDataToPDF();
    }//GEN-LAST:event_export_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editMhs("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private javax.swing.JTextField dosenpa_txt;
    private javax.swing.JButton edit_button;
    private javax.swing.JToggleButton export_button;
    private javax.swing.JButton hapus_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jk_combo;
    private javax.swing.JTextField kelas_txt;
    private javax.swing.JButton listNilai_button;
    private javax.swing.JTextField nama_txt;
    private javax.swing.JTextField nim_txt;
    // End of variables declaration//GEN-END:variables
}
