/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerA_voucher.conn;
import static Controller.Controller_tiket.printRute;
import Model.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jonjovio
 */
public class ControllerA_updateTiket {
    
    static DatabaseHandler conn = new DatabaseHandler();
    
    public static ArrayList<String> getRute(){
        conn.connect();

        String query = "SELECT * FROM rute";
        ArrayList<String> rute = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                rute.add(rs.getInt("idRute") + " - " + printRute(rs.getInt("berangkat"), rs.getInt("tujuan")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rute;
    }
    
    public static ArrayList<String> getTiket(){
        conn.connect();
        String query = "select * from tiket";
        java.sql.ResultSet rs;
        ArrayList<String> tiket = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tiket.add(rs.getInt("idTiket") + " Id Rute = " + rs.getInt("idRute") + ", Id Mobil = " + rs.getInt("idMobil") + ", Jam = " + rs.getInt("jam") + ", Harga = " + rs.getInt("hargaTiket"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiket;
    }
    
    public static ArrayList<String> getMobil(){
        conn.connect();

        String query = "SELECT * FROM mobil";
        ArrayList<String> mobil = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                mobil.add(rs.getInt("idMobil") + " - " + rs.getString("jenisMobil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobil;
    }
    
    public static boolean addTiket(Tiket tiket){
        conn.connect();
        String query = "INSERT INTO tiket VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, tiket.getIdTiket());
            stmt.setInt(2, tiket.getRute().getIdRute());
            stmt.setInt(3, tiket.getMobil().getIdMobil());
            stmt.setString(4, tiket.getJam());
            stmt.setDate(5, (java.sql.Date) tiket.getDate());
            stmt.setDouble(6, tiket.getHarga());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean deleteTiket(Tiket tiket) {
        conn.connect();
        int a = tiket.getIdTiket();
        String query = "DELETE FROM tiket WHERE idTiket = '" + a + "'";
        try {
            Statement stmt = conn.con.createStatement();
            
            stmt.executeUpdate(query);
            System.out.println(a);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    public ArrayList<Tiket> selectDataTiket(int idTiket){
        ArrayList<Tiket>listTiket = new ArrayList<>();
        conn.connect();
        
        System.out.println(idTiket);
        String query = "SELECT * FROM tiket WHERE idTiket = " +idTiket +" ";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Tiket tiket = new Tiket();
                tiket.setIdTiket(rs.getInt("idTiket"));
                tiket.getRute().setIdRute(rs.getInt("idRute"));
                tiket.getMobil().setIdMobil(rs.getInt("idMobil"));
                tiket.setJam(rs.getString("jam"));
                tiket.setDate(rs.getDate("tanggalTiket"));
                tiket.setHarga(rs.getInt("hargaTiket"));
                listTiket.add(tiket);
            }
            return listTiket;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }      
    }
    
    public boolean updateTiket(Tiket tiket, int idTiket) {
        conn.connect();
        String query = "UPDATE tiket SET jam = ?,tanggalTiket = ?,hargaTiket = ? WHERE idTiket = " + idTiket + ";" ;
        System.out.println(idTiket);
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, tiket.getJam());
            stmt.setDate(2, (Date) tiket.getDate());
            stmt.setDouble(3, tiket.getHarga());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    

}
