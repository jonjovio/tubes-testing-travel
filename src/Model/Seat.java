/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author jonjovio
 */
public class Seat {
    private TransaksiTiket transaksiTiket = new TransaksiTiket();
    private int idSeat;
    private ArrayList<Integer> seat = new ArrayList<>();

    public Seat(){
    }
    
    public Seat(TransaksiTiket transaksiTiket, int idSeat) {
        this.transaksiTiket = transaksiTiket;
        this.idSeat = idSeat;
    }

    public TransaksiTiket getTransaksiTiket() {
        return transaksiTiket;
    }

    public void setTransaksiTiket(TransaksiTiket transaksiTiket) {
        this.transaksiTiket = transaksiTiket;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public ArrayList<Integer> getSeat() {
        return seat;
    }

    public void setSeat(ArrayList<Integer> seat) {
        this.seat = seat;
    }
    
    
    
}
