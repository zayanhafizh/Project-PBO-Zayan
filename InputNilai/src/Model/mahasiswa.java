/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author M Zayan Hafizh H
 */
public class mahasiswa {
    public static int instancecount = 0;
    private int id;
    private int nim;
    private String nama;
    private String kelas;
    private ArrayList<matkul> daftarMatkul;
    private String dosen_pa;
    private String lulus;
    private double ipk;

    public mahasiswa(){
        this.id = ++instancecount;
        daftarMatkul = new ArrayList<>();
    }

    public static int getInstancecount() {
        return instancecount;
    }

    public static void setInstancecount(int instancecount) {
        mahasiswa.instancecount = instancecount;
    }

    public int getId() {
        return id;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public void tambahMatkul(matkul m) {
        daftarMatkul.add(m);
    }

    public String getDosen_pa() {
        return dosen_pa;
    }

    public void setDosen_pa(String dosen_pa) {
        this.dosen_pa = dosen_pa;
    }

    public String getLulus() {
        if (hitungIPK() > 2.75) {
            return "lulus";
        }
        return "tidak lulus";
    }

    public void setLulus(String lulus) {
        this.lulus = lulus;
    }

    public double hitungIPK() {
        double totalBobotNilai = 0.0;
        double totalSKS = 0.0;

        for (matkul m : daftarMatkul) {
            m.setNilaiAkhir(); // Hitung nilai akhir setiap mata kuliah
            totalBobotNilai += m.getNilaiAkhir(); // Tambahkan bobot nilai dari setiap mata kuliah
            totalSKS += m.getBobotSks(); // Tambahkan bobot SKS dari setiap mata kuliah
        }

        if (totalSKS == 0) {
            ipk = 0.0; // Menghindari pembagian dengan nol
        } else {
            ipk = totalBobotNilai / totalSKS; // Hitung IPK
        }
    return ipk;
    }

}
