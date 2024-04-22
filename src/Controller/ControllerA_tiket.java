/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerA_voucher.conn;
import Model.Tiket;
import Model.Voucher;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gibran<>
 */
public class ControllerA_tiket {
    
    
    
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
    
    public boolean insertNewTiket(Tiket tiket) {
        conn.connect();
        String query = "INSERT INTO tiket VALUES(?,?,?,?,?,?)";
        int idMobil;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, tiket.getIdTiket());
            stmt.setInt(2, tiket.getRute().getIdRute());
            if(tiket.getMobil().getJenisMobil() == "van"){
                idMobil = 1;
            } else {
                idMobil = 2;
            }
            stmt.setInt(3, idMobil);
            stmt.setString(4, tiket.getJam());
            stmt.setDate(5, (java.sql.Date) tiket.getDate());
            stmt.setDouble(6, tiket.getHarga());
            
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
