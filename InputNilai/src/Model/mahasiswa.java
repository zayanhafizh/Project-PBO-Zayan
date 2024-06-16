/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
    private matkul matkul;
    private String dosen_pa;
    private String lulus;

    public mahasiswa(){
        this.id = ++instancecount;
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

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getDosen_pa() {
        return dosen_pa;
    }

    public void setDosen_pa(String dosen_pa) {
        this.dosen_pa = dosen_pa;
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

    public matkul getMatkul() {
        return matkul;
    }

    public void setMatkul(matkul matkul) {
        this.matkul = matkul;
    }

    public String isLulus() {
        return lulus;
    }

    public void setLulus(String lulus) {
        this.lulus = lulus;
    }
    
    
}
