/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.MhsController;
import Model.Mahasiswa;
import Model.Matkul;

import javax.swing.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 *
 * @author M Zayan Hafizh H
 */
public class AddMahasiswaMatkul extends javax.swing.JFrame {
    private Matkul new_matkul;
    private Mahasiswa mhs; // Tambahkan variabel untuk referensi ke objek mahasiswa
    /**
     * Creates new form addMahasiswa_matkul
     */
    public AddMahasiswaMatkul(Mahasiswa mhs) { // Terima objek mahasiswa sebagai parameter konstruktor
        this.mhs = mhs; // Inisialisasi objek mahasiswa dari parameter konstruktor
        new_matkul = new Matkul();
        new_matkul.setNim(mhs.getNim());
        initComponents();
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
        submit_button = new javax.swing.JButton();
        nilaiakhir_button = new javax.swing.JButton();
        grade_button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        namamatkul_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nilaitugas_txt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nilaiuts_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nilaiuas_txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nilaiakhir_txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        grade_txt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel1.setText("TAMBAHKAN DATA MATA KULIAH");

        submit_button.setBackground(new java.awt.Color(0, 0, 255));
        submit_button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        submit_button.setForeground(new java.awt.Color(255, 255, 255));
        submit_button.setText("Submit");
        submit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_buttonActionPerformed(evt);
            }
        });

        nilaiakhir_button.setBackground(new java.awt.Color(0, 0, 255));
        nilaiakhir_button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        nilaiakhir_button.setForeground(new java.awt.Color(255, 255, 255));
        nilaiakhir_button.setText("Tampilkan Nilai Akhir");
        nilaiakhir_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilaiakhir_buttonActionPerformed(evt);
            }
        });

        grade_button.setBackground(new java.awt.Color(0, 0, 255));
        grade_button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        grade_button.setForeground(new java.awt.Color(255, 255, 255));
        grade_button.setText("Tampilkan Grade");
        grade_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_buttonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel2.setText("Nama Matkul :");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel3.setText("Nilai Tugas :");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel7.setText("Nilai UTS :");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel6.setText("Nilai UAS :");

        nilaiuas_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilaiuas_txtActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel8.setText("Nilai Akhir :");

        nilaiakhir_txt.setEditable(false);
        nilaiakhir_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilaiakhir_txtActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel9.setText("Grade :");

        grade_txt.setEditable(false);
        grade_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_txtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namamatkul_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaitugas_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaiuts_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaiuas_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilaiakhir_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namamatkul_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilaitugas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(nilaiuts_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nilaiuas_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilaiakhir_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grade_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(submit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(nilaiakhir_button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(grade_button))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jLabel1)))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit_button)
                    .addComponent(nilaiakhir_button)
                    .addComponent(grade_button))
                .addGap(35, 35, 35))
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
        new_matkul.setGrade();
        grade_txt.setText(String.valueOf(new_matkul.getGrade()));
    }//GEN-LAST:event_grade_txtActionPerformed

    private void submit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_buttonActionPerformed
        // TODO add your handling code here:
         // Validation to ensure all fields are filled
        if (namamatkul_txt.getText().isEmpty() || 
            nilaitugas_txt.getText().isEmpty() || 
            nilaiuts_txt.getText().isEmpty() || 
            nilaiuas_txt.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill out all fields before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Prevent submission
        }

        // Existing submission logic
        MhsController addMhs = new MhsController();
        int jawab = JOptionPane.showOptionDialog(this,
            "Apakah Anda ingin memasukkan matkul lain?",
            "Pilihan",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, null, null);
        if (jawab == JOptionPane.YES_OPTION) {
            int insertedRows = addMhs.addMatkul(new_matkul.getNim(),new_matkul.getNamaMatkul(), new_matkul.getNilaiTugas(), new_matkul.getNilaiUTS(), new_matkul.getNilaiUAS(), new_matkul.getNilaiAkhir(),String.valueOf(new_matkul.getGrade()));
            if(insertedRows > 0) {
                new AddMahasiswaMatkul(mhs).setVisible(true);
                this.dispose(); // Close the current form
            }
        } else {
            int insertedRows = addMhs.addMatkul(new_matkul.getNim(), new_matkul.getNamaMatkul(), new_matkul.getNilaiTugas(), new_matkul.getNilaiUTS(), new_matkul.getNilaiUAS(), new_matkul.getNilaiAkhir(), String.valueOf(new_matkul.getGrade()));
            if(insertedRows > 0) {
                 new MainMenu().setVisible(true);
                 this.dispose(); // Close the current form
             }
        }
       
    }//GEN-LAST:event_submit_buttonActionPerformed

    private void nilaiakhir_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilaiakhir_buttonActionPerformed
        // TODO add your handling code here:
        new_matkul.setNamaMatkul(String.valueOf(namamatkul_txt.getText()));
        new_matkul.setNilaiTugas(parseDouble(nilaitugas_txt.getText()));
        new_matkul.setNilaiUTS(parseDouble(nilaiuts_txt.getText()));
        new_matkul.setNilaiUAS(parseDouble(nilaiuas_txt.getText()));
        new_matkul.setNilaiAkhir();
        nilaiakhir_txt.setText(String.valueOf(new_matkul.getNilaiAkhir()));
    }//GEN-LAST:event_nilaiakhir_buttonActionPerformed

    private void grade_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_buttonActionPerformed
        // TODO add your handling code here:
        new_matkul.setGrade();
        grade_txt.setText(String.valueOf(new_matkul.getGrade()));
    }//GEN-LAST:event_grade_buttonActionPerformed

    private void nilaiuas_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilaiuas_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nilaiuas_txtActionPerformed

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
            java.util.logging.Logger.getLogger(AddMahasiswaMatkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMahasiswaMatkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMahasiswaMatkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMahasiswaMatkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        // Buat objek mahasiswa dari jendela addMahasiswa_identitas
        Mahasiswa mhs = new Mahasiswa();
        mhs.setNama("Nama Mahasiswa"); // Ganti dengan nama mahasiswa yang sesuai
        mhs.setNim(1234567890); // Ganti dengan nim mahasiswa yang sesuai
        mhs.setKelas("Kelas Mahasiswa"); // Ganti dengan kelas mahasiswa yang sesuai
        mhs.setDosen_pa("Dosen PA Mahasiswa"); // Ganti dengan dosen PA mahasiswa yang sesuai

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddMahasiswaMatkul(mhs).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton grade_button;
    private javax.swing.JTextField grade_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField namamatkul_txt;
    private javax.swing.JButton nilaiakhir_button;
    private javax.swing.JTextField nilaiakhir_txt;
    private javax.swing.JTextField nilaitugas_txt;
    private javax.swing.JTextField nilaiuas_txt;
    private javax.swing.JTextField nilaiuts_txt;
    private javax.swing.JButton submit_button;
    // End of variables declaration//GEN-END:variables
}
