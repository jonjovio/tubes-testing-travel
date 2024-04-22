/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;


public class Tiket {
    private int idTiket;
    private Date date;
    private String jam;
    private double harga;
    private Mobil mobil = new Mobil();
    private Rute rute = new Rute();

    public Tiket(){
    }
   
    public Tiket(int idTiket, Date date, String jam, double harga, Mobil mobil, Rute rute) {
        this.idTiket = idTiket;
        this.date = date;
        this.jam = jam;
        this.harga = harga;
        this.mobil = mobil;
        this.rute = rute;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public Rute getRute() {
        return rute;
    }

    public void setRute(Rute rute) {
        this.rute = rute;
    }

    
   
   
}
