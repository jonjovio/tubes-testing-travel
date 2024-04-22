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
 * @author jonjovio
 */


public class Controller_tiket implements Kota {

    static DatabaseHandler conn = new DatabaseHandler();

    public static ArrayList<String> getKota() {
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

    public static ArrayList<String> getRute(String lokasi, String date) {
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

        String query = "SELECT a.idRute, a.tujuan, a.berangkat, (SELECT jenisMobil FROM mobil WHERE idMobil = b.idMobil) AS 'jenisMobil' FROM rute a INNER JOIN tiket b ON a.idRute = b.idRute WHERE a.berangkat = '" + lok + "' AND a.idRute = b.idRute AND tanggalTiket = '" + date + "' GROUP BY b.idMobil";
        ArrayList<String> rute = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String brkt = "", tjn = "";
            while (rs.next()) {
                rute.add(rs.getInt("idRute") + " - " + printRute(rs.getInt("berangkat"), rs.getInt("tujuan")) + " - " + rs.getString("jenisMobil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rute;
    }

    public static ArrayList<Tiket> getTiket(String lokasi, String date, int rute, String mobil, String jam) {
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

        String query;
        if (jam.equals("")) {
            query = "SELECT * FROM tiket a JOIN mobil b ON a.idMobil = b.idMobil JOIN rute c ON a.idRute = c.idRute WHERE c.berangkat = '" + lok + "' AND a.tanggalTiket = '" + date + "' AND a.idRute = '" + rute + "' AND jenisMobil = '" + mobil + "'";
        } else {
            query = "SELECT * FROM tiket a JOIN mobil b ON a.idMobil = b.idMobil JOIN rute c ON a.idRute = c.idRute WHERE c.berangkat = '" + lok + "' AND a.tanggalTiket = '" + date + "' AND a.idRute = '" + rute + "' AND jenisMobil = '" + mobil + "' AND jam = '" + jam + "'";
        }
        ArrayList<Tiket> tik = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tiket t = new Tiket();
                t.setIdTiket(rs.getInt("idTiket"));
                t.setDate(rs.getDate("tanggalTiket"));
                t.setJam(rs.getString("jam"));
                t.setHarga(rs.getDouble("hargaTiket"));
                t.getMobil().setBanyakKursi(rs.getInt("banyakKursi"));
                t.getMobil().setJenisMobil(rs.getString("jenisMobil"));
                t.getRute().setKeberangkatan(rs.getInt("berangkat"));
                t.getRute().setTujuan(rs.getInt("tujuan"));
                tik.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tik;
    }

    public static Transaksi transaksi(String caraPembayaran, int jumlahTiket, double hargaTiket) {
        Transaksi trans = new Transaksi();
        Member member = Member.getMemberInst();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        trans.setDate(java.sql.Date.valueOf(formatter.format(date)));
        trans.setCaraPembayaran(caraPembayaran);
        trans.setTotalPembayaran(hargaTiket * jumlahTiket);
        trans.getMember().setIdUser(member.getIdUser());

        return trans;
    }

    public static boolean setTransaksi(int idTiket, Transaksi trans, Seat seat) {
        conn.connect();
        String query = "INSERT INTO transaksi VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, trans.getIdTransaksi());
            stmt.setInt(2, trans.getMember().getIdUser());
            stmt.setDouble(3, trans.getTotalPembayaran());
            stmt.setDate(4, (java.sql.Date) trans.getDate());
            stmt.setString(5, trans.getCaraPembayaran());
            stmt.setBoolean(6, trans.isRefund());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return setTransaksiTiket(idTiket, trans, seat);
    }

    public static boolean setTransaksiTiket(int idTiket, Transaksi trans, Seat seat) {
        conn.connect();

        String query = "SELECT idTransaksi FROM transaksi WHERE idMember = '" + trans.getMember().getIdUser() + "' && tanggal = '" + trans.getDate() + "' && totalPembayaran = '" + trans.getTotalPembayaran() + "' && caraPembayaran = '" + trans.getCaraPembayaran() + "'";
        int idTransaksi = -1;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                idTransaksi = rs.getInt("idTransaksi");
                seat.getTransaksiTiket().setIdTransaksi(idTransaksi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        query = "INSERT INTO transaksi_tiket VALUES(?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, idTransaksi);
            stmt.setInt(2, idTiket);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return setSeat(seat);
    }

    public static boolean setSeat(Seat seat) {
        conn.connect();
        for (int i = 0; i < seat.getSeat().size(); i++) {

            String query = "INSERT INTO seat VALUES(?,?,?)";
            try {
                PreparedStatement stmt = conn.con.prepareStatement(query);
                stmt.setInt(1, seat.getIdSeat());
                stmt.setInt(2, seat.getTransaksiTiket().getIdTransaksi());
                stmt.setInt(3, seat.getSeat().get(i));
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> getSeatIsi(int idTiket) {
        conn.connect();

        String query = "SELECT a.idTransaksi FROM transaksi a INNER JOIN transaksi_tiket b ON a.idTransaksi = b.idTransaksi WHERE b.idTiket='" + idTiket + "' && a.refund = 0";
        ArrayList<Integer> idTik = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                idTik.add(rs.getInt("idTransaksi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> seat = new ArrayList<>();
        for (int i = 0; i < idTik.size(); i++) {
            query = "SELECT seat FROM seat WHERE idTransaksi = '" + idTik.get(i) + "'";
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    seat.add(rs.getInt("seat"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return seat;
    }
    
    public static String printRute(int berangkat, int tujuan) {
        String brkt = null, tjn = null;
        if (berangkat == BANDUNG) {
            brkt = "Bandung";
        } else if (berangkat == JAKARTA) {
            brkt = "Jakarta";
        } else if (berangkat == DEPOK) {
            brkt = "Depok";
        } else if (berangkat == TANGGERANG) {
            brkt = "Tanggerang";
        } else if (berangkat == BEKASI) {
            brkt = "Bekasi";
        }
        if (tujuan == BANDUNG) {
            tjn = "Bandung";
        } else if (tujuan == JAKARTA) {
            tjn = "Jakarta";
        } else if (tujuan == DEPOK) {
            tjn = "Depok";
        } else if (tujuan == TANGGERANG) {
            tjn = "Tanggerang";
        } else if (tujuan == BEKASI) {
            tjn = "Bekasi";
        }
        return brkt + "-" + tjn;
    }
    
    public static boolean setPembatalanTiket(int idTransaksi){
        conn.connect();
        String query = "UPDATE transaksi SET refund = ? WHERE idTransaksi = " + idTransaksi + ";" ;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.executeUpdate();
            return deleteSeat(idTransaksi);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteSeat(int idTransaksi){
        conn.connect();
        String query = "DELETE FROM `seat` WHERE idTransaksi = ?;" ;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, idTransaksi);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
