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
public class TransaksiVoucher extends Transaksi{
   private int idVoucher;

    private Voucher voucher = new Voucher();

    public TransaksiVoucher() {
    }

    public TransaksiVoucher(int idTransaksi, double totalPembayaran, Date date, String caraPembayaran, Member member) {
        super(idTransaksi, totalPembayaran, date, caraPembayaran, member);
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    
    
}
