/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.Controller_tiket.conn;
import Model.Transaksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Gibran<>
 */
public class ControllerA_LihatUser {

    
    public ControllerA_LihatUser(){
        
    }
    
    public ArrayList<Transaksi> getAllTransaksi() {
        ArrayList<Transaksi> trans = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM transaksi";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaksi a = new Transaksi();
                a.setIdTransaksi(rs.getInt("idTransaksi"));
                a.getMember().setFirstName(rs.getString("idMember"));
                a.setTotalPembayaran(rs.getDouble("totalPembayaran"));
                a.setDate(rs.getDate("tanggal"));
                a.setCaraPembayaran(rs.getString("caraPembayaran"));
                String m ;
//                if(rs.getBoolean("refund") == true){
//                    m = "true";
//                } else {
//                    m = "false";
//                }
//                System.out.println(rs.getB);
                a.setRefund(rs.getBoolean(6));
//                System.out.println(a.setRefund(true));
                trans.add(a);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (trans);
    }
    
    public Object[] buatTabel(Transaksi trans){
        int idTransaksi = trans.getIdTransaksi();
        String Member = trans.getMember().getFirstName();
        Double totalBayar = trans.getTotalPembayaran();
        Date tanggal = trans.getDate();
        String caraBayar = trans.getCaraPembayaran();
        boolean refund = trans.isRefund();
        Object data[] = {idTransaksi,Member,totalBayar,tanggal,caraBayar,refund};
        return data;
    }
}
