/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerA_voucher;
import Controller.DatabaseHandler;
import Model.Voucher;
import java.awt.Color;
import java.awt.Font;
import static java.awt.SystemColor.control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Gibran<>
 */
public class MenuA_updateVoucher implements ActionListener {
    static int a; //untuk ngeparse input update voucher ke contrller
    static int b;
    static int c;
    JFrame frameUpdateVoucher;
    JPanel panelAwal, panelForm1, panelForm2, panelForm3, panelForm4;
    JLabel title2;
    JButton updateVoucher, ButtonSubmitDelete, ButtonSubmitTambah, ButtonSubmit, deleteVoucher, backKeMenu, backFromUpdate, backFromDelete, tambahVoucher, backFromTambah;
    JComboBox  VoucherUpdate,VoucherDel;
    JTextField InputIdVoucher, InputHargaVoucher, InputBanyakVoucher,InputIdVoucherUp;

    Voucher voucher = new Voucher();
    ControllerA_voucher ctrl = new ControllerA_voucher();
    
    ArrayList<Voucher>listVoucher = new ArrayList<>();
                
//    listVoucher = ctrl.selectDataVoucher(Integer.parseInt((String) VoucherUpdate.getSelectedItem()));
    MenuA_updateVoucher() {
        frameUpdateVoucher = new JFrame("MENU ADMIN UPDATE VOUCHER");
        frameUpdateVoucher.pack();
        frameUpdateVoucher.setSize(1000, 700);
        frameUpdateVoucher.setLocationRelativeTo(null);
        frameUpdateVoucher.getContentPane().setBackground(new Color(51, 153, 255));
        frameUpdateVoucher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150, 153, 255));
        panelAwal.setBounds(0, 20, 480, 600);

        JLabel title = new JLabel("MENU VOUCHER");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title2 = new JLabel("UPDATE / DELETE");
        title2.setBounds(30, 210, 400, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        panelAwal.add(title);
        panelAwal.add(title2);

        form1();
        frameUpdateVoucher.add(panelAwal);
        frameUpdateVoucher.setLayout(null);
        frameUpdateVoucher.setVisible(true);
    }

    public void form1() { //MENU AWAL

        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);

        tambahVoucher = new JButton("Tambah Voucher");
        tambahVoucher.setBounds(130, 100, 250, 100);
        tambahVoucher.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        tambahVoucher.addActionListener(this);

        updateVoucher = new JButton("Update Voucher");
        updateVoucher.setBounds(130, 220, 250, 100);
        updateVoucher.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        updateVoucher.addActionListener(this);

        deleteVoucher = new JButton("Delete Voucher");
        deleteVoucher.setBounds(130, 340, 250, 100);
        deleteVoucher.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        deleteVoucher.addActionListener(this);

        backKeMenu = new JButton("Back Ke Menu");
        backKeMenu.setBounds(130, 460, 250, 100);
        backKeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backKeMenu.addActionListener(this);

        panelForm1.add(tambahVoucher);
        panelForm1.add(updateVoucher);
        panelForm1.add(deleteVoucher);
        panelForm1.add(backKeMenu);

        frameUpdateVoucher.add(panelForm1);
    }

    public void form2() { //UPDATE
        panelForm2 = new JPanel();
        panelForm2.setLayout(null);
        panelForm2.setBackground(Color.white);
        panelForm2.setBounds(480, 20, 500, 600);

        JLabel labelVoucher = new JLabel("Pilih Voucher");
        labelVoucher.setBounds(50, 120, 200, 30);
        labelVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        VoucherUpdate = new JComboBox();
        VoucherUpdate.removeAllItems();
        ambilDataUpdate();
        VoucherUpdate.setBounds(50, 150, 200, 30);
        VoucherUpdate.addActionListener(this);

        JLabel labelIDVoucher = new JLabel("ID VOUCHER BARU :");
        labelIDVoucher.setBounds(50, 170, 250, 30);
        labelIDVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputIdVoucherUp = new JTextField();
        InputIdVoucherUp.setBounds(50, 200, 200, 30);
        InputIdVoucherUp.setEnabled(false);

        JLabel labelHargaVoucher = new JLabel("HARGA VOUCHER BARU :");
        labelHargaVoucher.setBounds(50, 240, 250, 30);
        labelHargaVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputHargaVoucher = new JTextField("");
        InputHargaVoucher.setBounds(50, 270, 200, 30);
        InputHargaVoucher.setEnabled(false);

        JLabel labelBanyakVoucher = new JLabel("BANYAK VOUCHER BARU :");
        labelBanyakVoucher.setBounds(50, 310, 250, 30);
        labelBanyakVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputBanyakVoucher = new JTextField("");
        InputBanyakVoucher.setBounds(50, 340, 200, 30);
        InputBanyakVoucher.setEnabled(false);

        backFromUpdate = new JButton("BACK");
        backFromUpdate.setBounds(80, 450, 150, 50);
        backFromUpdate.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backFromUpdate.addActionListener(this);

        ButtonSubmit = new JButton("SUBMIT");
        ButtonSubmit.setBounds(300, 450, 150, 50);
        ButtonSubmit.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmit.addActionListener(this);

        
        panelForm2.add(labelVoucher);
        panelForm2.add(VoucherUpdate);
        panelForm2.add(labelIDVoucher);
        panelForm2.add(InputIdVoucherUp);
        panelForm2.add(labelHargaVoucher);
        panelForm2.add(InputHargaVoucher);
        panelForm2.add(labelBanyakVoucher);
        panelForm2.add(InputBanyakVoucher);
        panelForm2.add(backFromUpdate);
        panelForm2.add(ButtonSubmit);

        frameUpdateVoucher.add(panelForm2);
    }

    public void form3() {  //DELETE
        panelForm3 = new JPanel();
        panelForm3.setLayout(null);
        panelForm3.setBackground(Color.white);
        panelForm3.setBounds(480, 20, 500, 600);

        JLabel labelVoucher = new JLabel("Pilih Voucher");
        labelVoucher.setBounds(50, 170, 200, 30);
        labelVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
//        String IsiVoucher[]={"Voucher1","Voucher2","Voucher3","Voucher2","Voucher1"};
        VoucherDel = new JComboBox();
        VoucherDel.removeAllItems();
        ambilDataDelete();
        VoucherDel.setBounds(50, 200, 200, 30);
        VoucherDel.addActionListener(this);

        ButtonSubmitDelete = new JButton("HAPUS");
        ButtonSubmitDelete.setBounds(300, 450, 150, 50);
        ButtonSubmitDelete.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmitDelete.addActionListener(this);

        backFromDelete = new JButton("BACK");
        backFromDelete.setBounds(80, 450, 150, 50);
        backFromDelete.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backFromDelete.addActionListener(this);

        panelForm3.add(backFromDelete);
        panelForm3.add(ButtonSubmitDelete);
        panelForm3.add(labelVoucher);
        panelForm3.add(VoucherDel);

        frameUpdateVoucher.add(panelForm3);
    }

    public void form4() {  //TAMBAH
        panelForm4 = new JPanel();
        panelForm4.setLayout(null);
        panelForm4.setBackground(Color.white);
        panelForm4.setBounds(480, 20, 500, 600);

        JLabel labelIDVoucher = new JLabel("ID VOUCHER :");
        labelIDVoucher.setBounds(50, 170, 200, 30);
        labelIDVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputIdVoucher = new JTextField("");
        InputIdVoucher.setBounds(50, 200, 200, 30);

        JLabel labelHargaVoucher = new JLabel("HARGA VOUCHER :");
        labelHargaVoucher.setBounds(50, 240, 200, 30);
        labelHargaVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputHargaVoucher = new JTextField("");
        InputHargaVoucher.setBounds(50, 270, 200, 30);

        JLabel labelBanyakVoucher = new JLabel("BANYAK VOUCHER :");
        labelBanyakVoucher.setBounds(50, 310, 200, 30);
        labelBanyakVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputBanyakVoucher = new JTextField("");
        InputBanyakVoucher.setBounds(50, 340, 200, 30);

        backFromTambah = new JButton("BACK");
        backFromTambah.setBounds(80, 450, 150, 50);
        backFromTambah.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backFromTambah.addActionListener(this);

        ButtonSubmitTambah = new JButton("Submit");
        ButtonSubmitTambah.setBounds(260, 450, 150, 50);
        ButtonSubmitTambah.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmitTambah.addActionListener(this);

        panelForm4.add(labelBanyakVoucher);
        panelForm4.add(labelHargaVoucher);
        panelForm4.add(labelIDVoucher);
        panelForm4.add(backFromTambah);
        panelForm4.add(InputIdVoucher);
        panelForm4.add(InputHargaVoucher);
        panelForm4.add(InputBanyakVoucher);
        panelForm4.add(ButtonSubmitTambah);

        frameUpdateVoucher.add(panelForm4);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == VoucherUpdate) {
//            if (VoucherUpdate.getSelectedItem().equals("Voucher")) {
//                InputIdVoucher.setEnabled(false);
//                InputBanyakVoucher.setEnabled(false);
//                InputHargaVoucher.setEnabled(false);
//
//            } else {
//                InputIdVoucherUp.setEnabled(true);
//                InputBanyakVoucher.setEnabled(true);
//                InputHargaVoucher.setEnabled(true);
////                InputIdVoucherUp.setText("as");
//
//            }
//        }
        if (ae.getSource() == deleteVoucher) {
            if (panelForm1.isVisible()) {
                panelForm1.setVisible(false);
                title2.setText("DELETE VOUCHER");
                form3();
            }
        }
        if (ae.getSource() == updateVoucher) {
            if (panelForm1.isVisible()) {
                panelForm1.setVisible(false);
                title2.setText("UPDATE VOUCHER");
                form2();
            }
        }
        if (ae.getSource() == tambahVoucher) {
            if (panelForm1.isVisible()) {
                panelForm1.setVisible(false);
                title2.setText("TAMBAH VOUCHER");
                form4();
            }
        }

        if (ae.getSource() == backFromUpdate) {
            if (panelForm2.isVisible()) {
                panelForm2.setVisible(false);
                title2.setText("UPDATE / DELETE");
                form1();
            }
        }
        if (ae.getSource() == backFromDelete) {
            if (panelForm3.isVisible()) {
                panelForm3.setVisible(false);
                title2.setText("UPDATE / DELETE");
                form1();
            }
        }
        if (ae.getSource() == backFromTambah) {
            if (panelForm4.isVisible()) {
                panelForm4.setVisible(false);
                title2.setText("UPDATE / DELETE");
                form1();
            }
        }
        if (ae.getSource() == backKeMenu) {
            if (frameUpdateVoucher.isVisible()) {
                frameUpdateVoucher.setVisible(false);
                new MenuH_admin();
            }
        }
        
        if (ae.getSource() == VoucherUpdate) {
            if (frameUpdateVoucher.isVisible()) {
                InputIdVoucherUp.setVisible(true);
                InputBanyakVoucher.setVisible(true);
                InputHargaVoucher.setVisible(true);
                InputIdVoucherUp.setEnabled(true);
                InputBanyakVoucher.setEnabled(true);
                InputHargaVoucher.setEnabled(true);               
//                ArrayList<Voucher>listVoucher = new ArrayList<>();
//                
                listVoucher = ctrl.selectDataVoucher(Integer.parseInt((String) VoucherUpdate.getSelectedItem()));
                InputIdVoucherUp.setText(String.valueOf(listVoucher.get(0).getIdVoucher()));
                InputBanyakVoucher.setText(String.valueOf(listVoucher.get(0).getBanyak()));
                InputHargaVoucher.setText(String.valueOf(listVoucher.get(0).getHarga()));
            }
        }

        if (ae.getSource() == ButtonSubmitTambah) {
            if (InputIdVoucher.getText().equals("") || InputHargaVoucher.getText().equals("") || InputBanyakVoucher.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Isi semua data!!", "TAMBAH VOUCHER", JOptionPane.INFORMATION_MESSAGE);
            } else {
                voucher.setIdVoucher((int) Double.parseDouble(InputIdVoucher.getText()));
                voucher.setHarga(Double.parseDouble(InputHargaVoucher.getText()));
                voucher.setBanyak((int) Double.parseDouble(InputBanyakVoucher.getText()));

                
                if (ctrl.insertNewVoucher(voucher)) {
                    panelForm4.setVisible(false);
                    form1();
                    JOptionPane.showMessageDialog(null, "Tambah Voucher Brhasil!", "TAMBAH VOUCHER", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Tambah Voucher Gagal!", "TAMBAH VOUCHER", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        if (ae.getSource() == ButtonSubmitDelete) {

            voucher.setIdVoucher((int) Double.parseDouble((String) VoucherDel.getSelectedItem()));

            
            if (ctrl.deleteVoucher(voucher)) {
                panelForm3.setVisible(false);
                form1();
                JOptionPane.showMessageDialog(null, "Hapus Voucher Brhasil!", "HAPUS VOUCHER", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Hapus Voucher Gagal!", "HAPUS VOUCHER", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (ae.getSource() == ButtonSubmit) {

            listVoucher = ctrl.selectDataVoucher(Integer.parseInt((String) VoucherUpdate.getSelectedItem()));          
            
  
            Voucher voucher = new Voucher(Integer.parseInt(InputIdVoucherUp.getText()),Double.parseDouble(InputHargaVoucher.getText()),Integer.parseInt(InputBanyakVoucher.getText()));
            if (ctrl.updateVoucher(voucher, listVoucher.get(0).getIdVoucher())) {
                panelForm2.setVisible(false);
                form1();
                JOptionPane.showMessageDialog(null, "Update Voucher Brhasil!", "UPDATE VOUCHER", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Update Voucher Gagal!", "UPDATE VOUCHER", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        
        
        

    }

    static DatabaseHandler conn = new DatabaseHandler();

    public void ambilDataDelete() {
        conn.connect();
        String query = "select * from voucher";
        java.sql.ResultSet rs;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                VoucherDel.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ambilDataUpdate() {
        
        conn.connect();
        String query = "select * from voucher";
        java.sql.ResultSet rs;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                VoucherUpdate.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new MenuA_updateVoucher();
    }
}
