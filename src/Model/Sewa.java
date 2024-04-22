/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author peter
 */
public class Sewa {

    private int idSewa;
    private double harga;
    private int hari;
    private Date date;
    private Mobil mobil;

    public Sewa(int idSewa, double harga, int hari, Date date, Mobil mobil, String lokasi) {
        this.idSewa = idSewa;
        this.harga = harga;
        this.hari = hari;
        this.date = date;
        this.mobil = mobil;
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    private String lokasi;

    public Sewa() {
    }


    public int getHari() {
        return hari;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public int getIdSewa() {
        return idSewa;
    }

    public void setIdSewa(int idSewa) {
        this.idSewa = idSewa;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
