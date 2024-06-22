/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.database;  // Import kelas database
import java.sql.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M Zayan Hafizh H
 */
public class listMahasiswa extends javax.swing.JFrame {

    /**
     * Creates new form listMahasiswa
     */
    public listMahasiswa() {
        initComponents();
        fetchData();  // Memanggil method fetchData saat frame dibuka
        addTableClickListener();  // Tambahkan pemanggilan method addTableClickListener

    }

     // Method untuk koneksi ke database dan mengambil data
    private void fetchData() {
        Connection conn = database.java_db();  // Mendapatkan koneksi dari kelas database

        if (conn != null) {
            // Query SQL untuk mengambil data dari tabel mahasiswa
            String query = "SELECT nim, nama, kelas, dosen_pa, jenis_kelamin FROM mahasiswa";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                // Membuat model untuk JTable
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);  // Menghapus baris yang ada

                // Menambahkan kolom ke model
                model.setColumnIdentifiers(new String[]{"NIM", "Nama", "Kelas", "Dosen PA", "Jenis Kelamin"});

                // Menambahkan baris ke model
                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getString("nim"));
                    row.add(rs.getString("nama"));
                    row.add(rs.getString("kelas"));
                    row.add(rs.getString("dosen_pa"));
                    row.add(rs.getString("jenis_kelamin"));
                    model.addRow(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection(conn);  // Menutup koneksi setelah selesai
            }
        }
    }

    // Method for fetching data from the database
    private void search(String keyword) {
        Connection conn = database.java_db();  // Get connection from database class

        if (conn != null) {
            // SQL query to fetch data from the mahasiswa table
            String query = "SELECT nim, nama, kelas, dosen_pa, jenis_kelamin FROM mahasiswa";
            if (!keyword.isEmpty()) {
                query += " WHERE nim LIKE ? OR nama LIKE ? OR kelas LIKE ? OR dosen_pa LIKE ? OR jenis_kelamin LIKE ?";
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                if (!keyword.isEmpty()) {
                    String searchKeyword = "%" + keyword + "%";
                    pstmt.setString(1, searchKeyword);
                    pstmt.setString(2, searchKeyword);
                    pstmt.setString(3, searchKeyword);
                    pstmt.setString(4, searchKeyword);
                    pstmt.setString(5, searchKeyword);
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    // Create model for JTable
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);  // Clear existing rows

                    // Add columns to the model
                    model.setColumnIdentifiers(new String[]{"NIM", "Nama", "Kelas", "Dosen PA", "Jenis Kelamin"});

                    // Add rows to the model
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getString("nim"));
                        row.add(rs.getString("nama"));
                        row.add(rs.getString("kelas"));
                        row.add(rs.getString("dosen_pa"));
                        row.add(rs.getString("jenis_kelamin"));
                        model.addRow(row);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection(conn);  // Close the connection after use
            }
        }
    }
    
    // Method untuk menambahkan listener klik pada tabel
    private void addTableClickListener() {
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    int selectedRow = jTable1.getSelectedRow();
                    String nim = jTable1.getValueAt(selectedRow, 0).toString(); // Ambil NIM dari kolom pertama
                    editMhs edit_mhs = new editMhs(nim);
                    edit_mhs.setVisible(true); // Tampilkan form editMhs
                    dispose(); // Tutup form listMahasiswa
                }
            }
        });
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        keyword_txt = new javax.swing.JTextField();
        search_button = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        back_button = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("Daftar Nilai Mahasiswa");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NIM", "Nama", "Kelas", "Dosen PA", "Jenis Kelamin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        search_button.setText("Cari");
        search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_buttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel2.setText("Cari Mahasiswa");

        back_button.setText("Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(keyword_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jLabel1)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyword_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_button)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_buttonActionPerformed
        // TODO add your handling code here:
         String keyword = keyword_txt.getText().trim();
         search(keyword);  // Fetch data with the keyword
    }//GEN-LAST:event_search_buttonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        // TODO add your handling code here:
        mainMenu main = new mainMenu();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(listMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton back_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField keyword_txt;
    private javax.swing.JToggleButton search_button;
    // End of variables declaration//GEN-END:variables
}
