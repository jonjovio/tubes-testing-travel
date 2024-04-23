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
public class Voucher {
    private int idVoucher;
    private double harga;
    private int banyak;

    public Voucher() {
    }
    
    public Voucher(int idVoucher, double harga, int banyak) {
        this.idVoucher = idVoucher;
        this.harga = harga;
        this.banyak = banyak;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getBanyak() {
        return banyak;
    }

    public void setBanyak(int banyak) {
        this.banyak = banyak;
    }

    
}
