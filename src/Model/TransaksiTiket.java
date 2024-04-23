/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class TransaksiTiket extends Transaksi{
    private Tiket tiket = new Tiket();
    
    
    public TransaksiTiket() {
    }

    public TransaksiTiket(int idTransaksi, double totalPembayaran, Date date, String caraPembayaran, Member member) {
        super(idTransaksi, totalPembayaran, date, caraPembayaran, member);
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }
    
}
