/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jonjovio
 */
public class Controller_riwayatTransaksi {
    
    static DatabaseHandler conn = new DatabaseHandler();
    Member member = Member.getMemberInst();
    
    public static ArrayList<TransaksiTiket> getTransaksiTiket(){
        conn.connect();
        Member member = Member.getMemberInst();
        
        String query = "SELECT * FROM transaksi a INNER JOIN transaksi_tiket b ON a.idTransaksi = b.idTransaksi INNER JOIN tiket c ON b.idTiket = c.idTiket INNER JOIN rute d ON c.idRute = d.idRute WHERE idMember = '" + member.getIdUser() + "'";
        ArrayList<TransaksiTiket> transTik = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TransaksiTiket t = new TransaksiTiket();
                t.setIdTransaksi(rs.getInt("idTransaksi"));
                t.setTotalPembayaran(rs.getDouble("totalPembayaran"));
                t.setCaraPembayaran(rs.getString("caraPembayaran"));
                t.setDate(rs.getDate("tanggal"));
                t.setRefund(checkRefund(rs.getInt("refund")));
                t.getTiket().setIdTiket(rs.getInt("idTiket"));
                t.getTiket().setHarga(rs.getDouble("hargaTiket"));
                t.getTiket().setDate(rs.getDate("tanggalTiket"));
                t.getTiket().setJam(rs.getString("jam"));
                t.getTiket().getRute().setIdRute(rs.getInt("idRute"));
                t.getTiket().getRute().setKeberangkatan(rs.getInt("berangkat"));
                t.getTiket().getRute().setTujuan(rs.getInt("tujuan"));
                transTik.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transTik;
    }
    
    public static boolean checkRefund(int refund){
        if (refund == 0) {
            return false;
        }else {
            return true;
        }
    }
    
    public static int banyakTiket(int idTransaksi){
        conn.connect();
        
        String query = "SELECT count(idTransaksi) AS banyakTiket FROM seat WHERE idTransaksi = " + idTransaksi;
        int bnyk = -1;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                bnyk = rs.getInt("banyakTiket");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bnyk;
    }
    
    public static ArrayList<TransaksiSewa> getTransaksiSewa(){
        conn.connect();
        Member member = Member.getMemberInst();
        
        String query = "SELECT * FROM transaksi a INNER JOIN transaksi_sewa b ON a.idTransaksi = b.idTransaksi INNER JOIN sewa c ON b.idSewa = c.idSewa INNER JOIN mobil d ON c.idMobil = d.idMobil WHERE idMember = '" + member.getIdUser() + "'";
        ArrayList<TransaksiSewa> transSewa = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TransaksiSewa t = new TransaksiSewa();
                t.setIdTransaksi(rs.getInt("idTransaksi"));
                t.setTotalPembayaran(rs.getDouble("totalPembayaran"));
                t.setCaraPembayaran(rs.getString("caraPembayaran"));
                t.getSewa().setDate(rs.getDate("tanggalSewa"));
                t.getSewa().setHari(rs.getInt("lamaSewa"));
                t.getSewa().setHarga(rs.getDouble("harga"));
                t.getSewa().setIdSewa(rs.getInt("idSewa"));
                t.getSewa().getMobil().setIdMobil(rs.getInt("idMobil"));
                t.getSewa().getMobil().setJenisMobil(rs.getString("jenisMobil"));
                t.getSewa().getMobil().setBanyakKursi(rs.getInt("banyakKursi"));
                transSewa.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transSewa;
    }
    
    
    public static ArrayList<TransaksiVoucher> getTransaksiVoucher(){
        conn.connect();
        Member member = Member.getMemberInst();
        
        String query = "SELECT * FROM transaksi a INNER JOIN transaksi_voucher b ON a.idTransaksi = b.idTransaksi INNER JOIN voucher c ON b.idVoucher = c.idVoucher WHERE idMember = '" + member.getIdUser() + "'";
        ArrayList<TransaksiVoucher> transVoucher = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TransaksiVoucher t = new TransaksiVoucher();
                t.setIdTransaksi(rs.getInt("idTransaksi"));
                t.setTotalPembayaran(rs.getDouble("totalPembayaran"));
                t.setCaraPembayaran(rs.getString("caraPembayaran"));
                t.getVoucher().setIdVoucher(rs.getInt("idVoucher"));
                
                transVoucher.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transVoucher;
    }
    
    
}
