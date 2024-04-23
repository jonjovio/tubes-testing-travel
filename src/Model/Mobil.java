/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ASUS
 */
public class Mobil {
    private int idMobil;
    private String jenisMobil;
    private int banyakKursi;

    public Mobil(){
    }
    
    public Mobil(String jenisMobil, int banyakKursi) {
        this.jenisMobil = jenisMobil;
        this.banyakKursi = banyakKursi;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    public void setJenisMobil(String jenisMobil) {
        this.jenisMobil = jenisMobil;
    }

    public int getBanyakKursi() {
        return banyakKursi;
    }

    public void setBanyakKursi(int banyakKursi) {
        this.banyakKursi = banyakKursi;
    }

    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }
    
    
}
