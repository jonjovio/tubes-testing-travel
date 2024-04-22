/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Transaksi {
    
    private int idTransaksi;
    private double totalPembayaran;
    private Date date;
    private String caraPembayaran;
    private boolean refund;
    private Member member = new Member();

    public Transaksi(){
    }
    
    public Transaksi(int idTransaksi, double totalPembayaran, Date date, String caraPembayaran, Member member) {
        this.idTransaksi = idTransaksi;
        this.totalPembayaran = totalPembayaran;
        this.date = date;
        this.caraPembayaran = caraPembayaran;
        this.member = member;
    }
    
    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCaraPembayaran() {
        return caraPembayaran;
    }

    public void setCaraPembayaran(String caraPembayaran) {
        this.caraPembayaran = caraPembayaran;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }
    
    
    
}
