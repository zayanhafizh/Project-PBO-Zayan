/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.mahasiswa;

/**
 *
 * @author M Zayan Hafizh H
 */
public class addMhsController {
    public void addIdentity(String nama) {
        mahasiswa newmahasiswa = new mahasiswa();
        newmahasiswa.setNama(nama);
        System.out.println(newmahasiswa.getNama());
        System.out.println(newmahasiswa.getId());
    }
}
