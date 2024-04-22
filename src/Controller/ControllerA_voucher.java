/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import static Controller.Controller_user.conn;
import Model.Voucher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gibran<>
 */
public class ControllerA_voucher {
    
    static DatabaseHandler conn = new DatabaseHandler();
    
    public boolean insertNewVoucher(Voucher voucher) {
        conn.connect();
        String query = "INSERT INTO voucher VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, voucher.getIdVoucher());
            stmt.setDouble(2, voucher.getHarga());
            stmt.setInt(3, voucher.getBanyak());
            
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    
    public static boolean deleteVoucher(Voucher voucher) {
        conn.connect();
        int a = voucher.getIdVoucher();
        String query = "DELETE FROM voucher WHERE idVoucher = '" + a + "'";
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
    
    public ArrayList<Voucher> selectDataVoucher(int idVoucher){
        ArrayList<Voucher>listVoucher = new ArrayList<>();
        conn.connect();
        
        
        String query = "SELECT * FROM voucher WHERE idVoucher = " +idVoucher +" ";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Voucher vcr = new Voucher();
                vcr.setIdVoucher(rs.getInt(1));
                vcr.setBanyak(rs.getInt(3));
                vcr.setHarga(rs.getInt(2));
                listVoucher.add(vcr);
            }
            return listVoucher;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }      
    }
    
    public boolean updateVoucher(Voucher voucher, int idVoucher) {
        conn.connect();
        String query = "UPDATE voucher SET idVoucher = ?,hargaVoucher = ?,banyakVoucher = ? WHERE idVoucher = " + idVoucher + ";" ;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, voucher.getIdVoucher());
            stmt.setDouble(2, voucher.getHarga());
            stmt.setInt(3, voucher.getBanyak());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    

    
}
