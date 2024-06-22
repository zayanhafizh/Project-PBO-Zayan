/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.matkul;
import Model.database;
import com.itextpdf.text.BaseColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author M Zayan Hafizh H
 */
public class editNilai extends javax.swing.JFrame {
private String id;
private matkul new_matkul;
private String nim;
    /**
     * Creates new form editNilai
     */
    public editNilai(String id, String nim) {
        initComponents();
        this.nim = nim;
        this.id = id;
        this.new_matkul = new matkul();
        displayNilai();
        addListeners();
    }

    private void displayNilai() {
        Connection conn = database.java_db(); // Mendapatkan koneksi dari kelas database

        if (conn != null) {
            String query = "SELECT * FROM matkul WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    
                    String nama_matkul = rs.getString("nama_matkul");
                    
                    String nilai_tugas = rs.getString("nilai_tugas");
                    new_matkul.setNilaiTugas(Double.parseDouble(nilai_tugas));
                    
                    String nilai_uts = rs.getString("nilai_uts");
                    new_matkul.setNilaiUTS(Double.parseDouble(nilai_uts));
                    
                    String nilai_uas = rs.getString("nilai_uas");
                    new_matkul.setNilaiUAS(Double.parseDouble(nilai_uas));
                    
                    String nilai_akhir = rs.getString("nilai_akhir");
                    new_matkul.setNilaiAkhir();
                    
                    String grade = rs.getString("grade");
                    new_matkul.setGrade();
                    
                    namamatkul_txt.setText(nama_matkul);
                    nilaitugas_txt.setText(nilai_tugas);
                    nilaiuts_txt.setText(nilai_uts);
                    nilaiuas_txt.setText(nilai_uas);
                    nilaiakhir_txt.setText(String.valueOf(new_matkul.getNilaiAkhir()));
                    grade_txt.setText(String.valueOf(new_matkul.getGrade()));
                } else {
                    JOptionPane.showMessageDialog(this, "Data nilai tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
                    this.dispose(); // Tutup form jika data tidak ditemukan
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection(conn);
            }
        }
    }
    
     private void addListeners() {
        nilaitugas_txt.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
            public void removeUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
            public void insertUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
        });

        nilaiuts_txt.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
            public void removeUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
            public void insertUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
        });

        nilaiuas_txt.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
            public void removeUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
            public void insertUpdate(DocumentEvent e) {
                updateNilaiAkhirAndGrade();
            }
        });
    }

    private void updateNilaiAkhirAndGrade() {
        try {
            double nilaiTugas = Double.parseDouble(nilaitugas_txt.getText());
            double nilaiUTS = Double.parseDouble(nilaiuts_txt.getText());
            double nilaiUAS = Double.parseDouble(nilaiuas_txt.getText());
            new_matkul.setNilaiTugas(nilaiTugas);
            new_matkul.setNilaiUTS(nilaiUTS);
            new_matkul.setNilaiUAS(nilaiUAS);
            new_matkul.setNilaiAkhir();
            new_matkul.setGrade();
            nilaiakhir_txt.setText(String.valueOf(new_matkul.getNilaiAkhir()));
            grade_txt.setText(String.valueOf(new_matkul.getGrade()));
        } catch (NumberFormatException e) {
            // Handle potential parsing errors here if needed
        }
    }
    
    private void editNilaiToDatabase() {
        Connection conn = database.java_db();
        if (conn != null) {
            String query = "UPDATE matkul SET nilai_tugas = ?, nilai_uts = ?, nilai_uas = ?, nilai_akhir = ?, grade = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDouble(1, new_matkul.getNilaiTugas());
                stmt.setDouble(2, new_matkul.getNilaiUTS());
                stmt.setDouble(3, new_matkul.getNilaiUAS());
                stmt.setDouble(4, new_matkul.getNilaiAkhir());
                stmt.setString(5, String.valueOf(new_matkul.getGrade()));
                stmt.setString(6, id);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Data nilai berhasil diperbarui", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Data nilai gagal diperbarui", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection(conn);
            }
        }
    }

    private void deleteNilaiFromDatabase() {
        Connection conn = database.java_db();
        if (conn != null) {
            String query = "DELETE FROM matkul WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, id);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Data nilai berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); // Tutup form setelah data dihapus
                } else {
                    JOptionPane.showMessageDialog(this, "Data nilai gagal dihapus", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection(conn);
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

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        namamatkul_txt = new javax.swing.JTextField();
        nilaitugas_txt = new javax.swing.JTextField();
        nilaiuts_txt = new javax.swing.JTextField();
        nilaiuas_txt = new javax.swing.JTextField();
        nilaiakhir_txt = new javax.swing.JTextField();
        grade_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        back_button = new javax.swing.JButton();
        edit_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        nilaiakhir_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilaiakhir_txtActionPerformed(evt);
            }
        });

        grade_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_txtActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel1.setText("Nama Matkul :");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel2.setText("Nilai Tugas :");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel3.setText("Nilai UTS :");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel4.setText("Nilai UAS :");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel5.setText("Nilai Akhir :");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel6.setText("Grade :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grade_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaitugas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namamatkul_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaiuts_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaiuas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaiakhir_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namamatkul_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilaitugas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilaiuts_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilaiuas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilaiakhir_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grade_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(24, 24, 24))
        );

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel7.setText("DETAIL INFORMASI NILAI MAHASISWA");

        back_button.setText("Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        edit_button.setText("Edit");
        edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_buttonActionPerformed(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 195, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(162, 162, 162))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nilaiakhir_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilaiakhir_txtActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nilaiakhir_txtActionPerformed

    private void grade_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_txtActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_grade_txtActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        // TODO add your handling code here:
        listNilai list_nilai = new listNilai(nim);
        list_nilai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

    private void edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_buttonActionPerformed
        // TODO add your handling code here:
        editNilaiToDatabase();
    }//GEN-LAST:event_edit_buttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        deleteNilaiFromDatabase();

    }//GEN-LAST:event_delete_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(editNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editNilai("","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton edit_button;
    private javax.swing.JTextField grade_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField namamatkul_txt;
    private javax.swing.JTextField nilaiakhir_txt;
    private javax.swing.JTextField nilaitugas_txt;
    private javax.swing.JTextField nilaiuas_txt;
    private javax.swing.JTextField nilaiuts_txt;
    // End of variables declaration//GEN-END:variables
}
