/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author M Zayan Hafizh H
 */
public class matkul {
    public static int instancecount = 0;
    private int id;
    private String nama_matkul;
    private int nilai_uts;
    private int nilai_uas;
    private int nilai_tugas;
    private int nilai_akhir;

    public int getNilai_akhir() {
        return nilai_akhir;
    }

    public void setNilai_akhir(int nilai_akhir) {
        this.nilai_akhir = nilai_akhir;
    }
    private char grade;

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public int getNilai_uts() {
        return nilai_uts;
    }

    public void setNilai_uts(int nilai_uts) {
        this.nilai_uts = nilai_uts;
    }

    public int getNilai_uas() {
        return nilai_uas;
    }

    public void setNilai_uas(int nilai_uas) {
        this.nilai_uas = nilai_uas;
    }

    public int getNilai_tugas() {
        return nilai_tugas;
    }

    public void setNilai_tugas(int nilai_tugas) {
        this.nilai_tugas = nilai_tugas;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
    
    
}
