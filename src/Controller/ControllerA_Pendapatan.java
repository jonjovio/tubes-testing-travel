/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerA_voucher.conn;
import Model.Tiket;
import Model.Transaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gibran<>
 */
public class ControllerA_Pendapatan {
    

    public ArrayList<Transaksi> cariPendapatan(int n, String m) {
        conn.connect();
        int pendapatan = 0;
        ArrayList<Transaksi> trans = new ArrayList<>();
        String query = "SELECT MONTH(tanggal) Bulan, YEAR(tanggal) Tahun, totalPembayaran, refund FROM transaksi WHERE MONTH(tanggal) = " + n +" && YEAR(tanggal) = " + m +" ORDER BY MONTH(tanggal), YEAR(tanggal)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Transaksi transaksi = new Transaksi();
                transaksi.setTotalPembayaran(rs.getDouble("totalPembayaran"));
                transaksi.setRefund(rs.getBoolean("refund"));
                trans.add(transaksi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return trans;
    }
    
    public double hitungPendapatan(ArrayList<Transaksi> trans){
        double total = 0;
        for (int i = 0; i < trans.size(); i++) {
            if (trans.get(i).isRefund()) {
                total += trans.get(i).getTotalPembayaran() - (trans.get(i).getTotalPembayaran() * 0.2);
            }else{
                total += trans.get(i).getTotalPembayaran();
            }
        }
        return total;
    }
}
