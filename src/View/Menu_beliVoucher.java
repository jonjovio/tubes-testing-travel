/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerA_voucher;
import Controller.DatabaseHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Arvin Situmorang
 */
public class Menu_beliVoucher implements ActionListener {

    JFrame frameBeliVoucher;
    JPanel panelAwal, panelForm;
    JButton buttonSubmit, backKeMenu;
    JComboBox pilihVoucher;
    JLabel tipeVoucher1, tipeVoucher2, tipeVoucher3, tipeVoucher4, tipeVoucher5, hargaVoucher1, hargaVoucher2, hargaVoucher3, hargaVoucher4, hargaVoucher5;

    static DatabaseHandler conn = new DatabaseHandler();

    Menu_beliVoucher() {
        frameBeliVoucher = new JFrame("MENU ADMIN BELI VOUCHER");
        frameBeliVoucher.pack();
        frameBeliVoucher.setSize(1000, 700);
        frameBeliVoucher.setLocationRelativeTo(null);
        frameBeliVoucher.getContentPane().setBackground(new Color(51, 153, 255));
        //frameBeliVoucher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150, 153, 255));
        panelAwal.setBounds(0, 20, 480, 600);

        JLabel title = new JLabel("MENU BELI VOUCHER");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        panelAwal.add(title);

        formPanel();
        frameBeliVoucher.add(panelAwal);
        frameBeliVoucher.setLayout(null);
        frameBeliVoucher.setVisible(true);
    }

    public void formPanel() {

        panelForm = new JPanel();
        panelForm.setLayout(null);
        panelForm.setBackground(Color.white);
        panelForm.setBounds(480, 20, 500, 600);

        JLabel labelBeliVoucher = new JLabel("PILIH VOUCHER:");
        labelBeliVoucher.setBounds(50, 100, 200, 30);
        labelBeliVoucher.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        //String[] voucherBeli = {"Voucher 1", "Voucher 2", "Voucher 3", "Voucher 4", "Voucher 5"};
        pilihVoucher = new JComboBox();
        pilihVoucher.removeAllItems();
        ambilDataBeli();
        pilihVoucher.setBounds(50, 130, 200, 30);
        pilihVoucher.addActionListener(this);

        tipeVoucher1 = new JLabel("HARGA VOUCHER 1:");
        tipeVoucher1.setBounds(50, 180, 200, 30);
        tipeVoucher1.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        tipeVoucher1.setVisible(false);
        hargaVoucher1 = new JLabel("Rp25.000");
        hargaVoucher1.setBounds(50, 210, 200, 30);
        hargaVoucher1.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 20));
        hargaVoucher1.setVisible(false);
        tipeVoucher2 = new JLabel("HARGA VOUCHER 2:");
        tipeVoucher2.setBounds(50, 180, 200, 30);
        tipeVoucher2.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        tipeVoucher2.setVisible(false);

        hargaVoucher2 = new JLabel("Rp50.000");
        hargaVoucher2.setBounds(50, 210, 200, 30);
        hargaVoucher2.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 20));
        hargaVoucher2.setVisible(false);
        tipeVoucher3 = new JLabel("HARGA VOUCHER 3:");
        tipeVoucher3.setBounds(50, 180, 200, 30);
        tipeVoucher3.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        tipeVoucher3.setVisible(false);

        hargaVoucher3 = new JLabel("Rp75.000");
        hargaVoucher3.setBounds(50, 210, 200, 30);
        hargaVoucher3.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 20));
        hargaVoucher3.setVisible(false);

        tipeVoucher4 = new JLabel("HARGA VOUCHER 4:");
        tipeVoucher4.setBounds(50, 180, 200, 30);
        tipeVoucher4.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        tipeVoucher4.setVisible(false);

        hargaVoucher4 = new JLabel("Rp100.000");
        hargaVoucher4.setBounds(50, 210, 200, 30);
        hargaVoucher4.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 20));
        hargaVoucher4.setVisible(false);

        tipeVoucher5 = new JLabel("HARGA VOUCHER 5:");
        tipeVoucher5.setBounds(50, 180, 200, 30);
        tipeVoucher5.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        tipeVoucher5.setVisible(false);

        hargaVoucher5 = new JLabel("Rp150.000");
        hargaVoucher5.setBounds(50, 210, 200, 30);
        hargaVoucher5.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 20));
        hargaVoucher5.setVisible(false);

        buttonSubmit = new JButton("BELI");
        buttonSubmit.setBounds(300, 450, 150, 50);
        buttonSubmit.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        buttonSubmit.addActionListener(this);

        backKeMenu = new JButton("Back Ke Menu");
        backKeMenu.setBounds(50, 450, 180, 50);
        backKeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backKeMenu.addActionListener(this);

        panelForm.add(pilihVoucher);
        panelForm.add(labelBeliVoucher);
        panelForm.add(tipeVoucher1);
        panelForm.add(tipeVoucher2);
        panelForm.add(tipeVoucher3);
        panelForm.add(tipeVoucher4);
        panelForm.add(tipeVoucher5);
        panelForm.add(hargaVoucher1);
        panelForm.add(hargaVoucher2);
        panelForm.add(hargaVoucher3);
        panelForm.add(hargaVoucher4);
        panelForm.add(hargaVoucher5);
        panelForm.add(backKeMenu);
        panelForm.add(buttonSubmit);

        frameBeliVoucher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameBeliVoucher.add(panelForm);
    }

    public static void main(String[] args) {
        new Menu_beliVoucher();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pilihVoucher) {
            if (pilihVoucher.getSelectedItem().equals("Voucher 1")) {
                tipeVoucher1.setVisible(true);
                hargaVoucher1.setVisible(true);
                tipeVoucher2.setVisible(false);
                hargaVoucher2.setVisible(false);
                tipeVoucher3.setVisible(false);
                hargaVoucher3.setVisible(false);
                tipeVoucher4.setVisible(false);
                hargaVoucher4.setVisible(false);
                tipeVoucher5.setVisible(false);
                hargaVoucher5.setVisible(false);
            } else if (pilihVoucher.getSelectedItem().equals("Voucher 2")) {
                tipeVoucher1.setVisible(false);
                hargaVoucher1.setVisible(false);
                tipeVoucher2.setVisible(true);
                hargaVoucher2.setVisible(true);
                tipeVoucher3.setVisible(false);
                hargaVoucher3.setVisible(false);
                tipeVoucher4.setVisible(false);
                hargaVoucher4.setVisible(false);
                tipeVoucher5.setVisible(false);
                hargaVoucher5.setVisible(false);
            } else if (pilihVoucher.getSelectedItem().equals("Voucher 3")) {
                tipeVoucher1.setVisible(false);
                hargaVoucher1.setVisible(false);
                tipeVoucher2.setVisible(false);
                hargaVoucher2.setVisible(false);
                tipeVoucher3.setVisible(true);
                hargaVoucher3.setVisible(true);
                tipeVoucher4.setVisible(false);
                hargaVoucher4.setVisible(false);
                tipeVoucher5.setVisible(false);
                hargaVoucher5.setVisible(false);
            } else if (pilihVoucher.getSelectedItem().equals("Voucher 4")) {
                tipeVoucher1.setVisible(false);
                hargaVoucher1.setVisible(false);
                tipeVoucher2.setVisible(false);
                hargaVoucher2.setVisible(false);
                tipeVoucher3.setVisible(false);
                hargaVoucher3.setVisible(false);
                tipeVoucher4.setVisible(true);
                hargaVoucher4.setVisible(true);
                tipeVoucher5.setVisible(false);
                hargaVoucher5.setVisible(false);
            } else {
//                tipeVoucher1.setVisible(false);
//                hargaVoucher1.setVisible(false);
//                tipeVoucher2.setVisible(false);
//                hargaVoucher2.setVisible(false);
//                tipeVoucher3.setVisible(false);
//                hargaVoucher3.setVisible(false);
//                tipeVoucher4.setVisible(false);
//                hargaVoucher4.setVisible(false);
//                tipeVoucher5.setVisible(true);
//                hargaVoucher5.setVisible(true);
            }
        }
        if (ae.getSource() == backKeMenu) {
            if (frameBeliVoucher.isVisible()) {
                frameBeliVoucher.setVisible(false);
                new Menu_member();
            }
        }
        if (ae.getSource() == buttonSubmit) {
            JOptionPane.showMessageDialog(null, "Voucher berhasil dibeli!", "Beli Voucher", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void ambilDataBeli() {

        conn.connect();
        String query = "select * from voucher";
        java.sql.ResultSet rs;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                pilihVoucher.addItem("Voucher " + rs.getString("idVoucher"));
                tipeVoucher1 = new JLabel();
                tipeVoucher1.setBounds(50, 180, 200, 30);
                tipeVoucher1.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
                tipeVoucher1.setText("Harga: " + rs.getDouble("hargaVoucher"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
