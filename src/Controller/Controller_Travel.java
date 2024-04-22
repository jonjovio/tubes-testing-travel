/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.sql.*;
;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author peter
 */


public class Controller_Travel implements Kota {

    static DatabaseHandler conn = new DatabaseHandler();
    public static ArrayList<String> getLokasi() {
        conn.connect();
        String query = "SELECT * FROM kota";
        ArrayList<String> kota = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                kota.add(rs.getString("namaKota"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kota;
    }
    public static ArrayList<String> getMobil() {
        conn.connect();
        String query = "SELECT * FROM mobil";
        ArrayList<String> mobil = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                mobil.add(rs.getString("jenisMobil"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobil;
    }
    public static ArrayList<String> getSewa(String lokasi,String date) {
        conn.connect();
        int lok = -1;
        for (int i = 0; i < 5; i++) {
            if (lokasi.equals("Bandung")) {
                lok = BANDUNG;
            } else if (lokasi.equals("Jakarta")) {
                lok = JAKARTA;
            } else if (lokasi.equals("Depok")) {
                lok = DEPOK;
            } else if (lokasi.equals("Tanggerang")) {
                lok = TANGGERANG;
            } else if (lokasi.equals("Bekasi")) {
                lok = BEKASI;
            }
        }
        
        String query = "SELECT a.idSewa,c.jenisMobil, b.namaKota, FROM `sewa` a INNER JOIN `kota` b ON a.idKota=b.idKota INNER JOIN `mobil` c ON c.idMobil=a.idMobil WHERE a.idKota='"+lok+"' GROUP BY a.idMobil";
        ArrayList<String> sewa = new ArrayList<>();
        System.out.println(date);
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            sewa.add(rs.getInt("idSewa") +" - "+ rs.getString("jenisMobil") +" - "+ rs.getString("namaKota"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sewa;
    }
    public static ArrayList<Sewa> getStruk(String lokasi, String date, int sewa) {
        conn.connect();

        int lok = -1;
        for (int i = 0; i < 5; i++) {
            if (lokasi.equals("Bandung")) {
                lok = BANDUNG;
            } else if (lokasi.equals("Jakarta")) {
                lok = JAKARTA;
            } else if (lokasi.equals("Depok")) {
                lok = DEPOK;
            } else if (lokasi.equals("Tanggerang")) {
                lok = TANGGERANG;
            } else if (lokasi.equals("Bekasi")) {
                lok = BEKASI;
            }
        }

        String query = "SELECT * FROM sewa a JOIN mobil b ON a.idMobil = b.idMobil JOIN kota c ON a.idKota = c.idKota WHERE c.idKota = '" + lok + "' AND tanggalTiket = '" + date + "'";
        
        ArrayList<Sewa> borrow = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Sewa t = new Sewa();
                t.setIdSewa(rs.getInt("idSewa"));
                t.setDate(rs.getDate("tanggalSewa"));
                t.setHari(rs.getInt("hari"));
                t.setHarga(rs.getDouble("hargaSewa"));
                t.getMobil().setJenisMobil(rs.getString("jenisMobil"));
                borrow.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow;
    }

    public static Transaksi transaksi(String caraPembayaran,int hari,  double hargaSewa) {
        Transaksi trans = new Transaksi();
        Member member = Member.getMemberInst();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        trans.setDate(java.sql.Date.valueOf(formatter.format(date)));
        trans.setCaraPembayaran(caraPembayaran);
        trans.setTotalPembayaran(hargaSewa * hari);
        trans.getMember().setIdUser(member.getIdUser());

        return trans;
    }
    public static String setTransaksi(int idTiket, Transaksi trans) {
        conn.connect();
        String query = "INSERT INTO transaksi VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, trans.getIdTransaksi());
            stmt.setInt(2, trans.getMember().getIdUser());
            stmt.setDouble(3, trans.getTotalPembayaran());
            stmt.setDate(4, (java.sql.Date) trans.getDate());
            stmt.setString(5, trans.getCaraPembayaran());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setTransaksiTravel(idTiket, trans);
    }

    public static String setTransaksiTravel(int idTiket, Transaksi trans) {
        conn.connect();
        String query2 = "SELECT idTransaksi FROM transaksi WHERE idMember = '" + trans.getMember().getIdUser() + "' && tanggal = '" + trans.getDate() + "' && totalPembayaran = '" + trans.getTotalPembayaran() + "' && caraPembayaran = '" + trans.getCaraPembayaran() + "'";
        int idTransaksi = -1;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query2);
            while (rs.next()) {
                idTransaksi = rs.getInt("idTransaksi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String query3 = "INSERT INTO transaksi_tiket VALUES(?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query3);
            stmt.setInt(1, idTransaksi);
            stmt.setInt(2, idTiket);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query3;
    }
//    public static void main(String[] args) {
//        new Controller_Travel();
//    }
}
