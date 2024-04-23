/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerA_LihatUser;
import Model.Transaksi;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gibran<>
 */
public class MenuA_lihatRiwayatUser implements ActionListener {

    JFrame frameLihatRiwayatUser;
    JPanel panelAwal, panelForm1;
    JLabel title2, text;
    JButton backKeMenu;
    ControllerA_LihatUser ctrl = new ControllerA_LihatUser();
     MenuA_lihatRiwayatUser() {

        frameLihatRiwayatUser = new JFrame("MENU ADMIN LIHAT PENDAPATAN");
        frameLihatRiwayatUser.pack();
        frameLihatRiwayatUser.setSize(1000, 700);
        frameLihatRiwayatUser.setLocationRelativeTo(null);
        frameLihatRiwayatUser.getContentPane().setBackground(new Color(51, 153, 255));
        frameLihatRiwayatUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150, 153, 255));
        panelAwal.setBounds(0, 20, 480, 600);

        JLabel title = new JLabel("MENU LIHAT");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title2 = new JLabel("RIWAYAT USER");
        title2.setBounds(30, 210, 400, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        panelAwal.add(title);
        panelAwal.add(title2);

        form1();
        frameLihatRiwayatUser.add(panelAwal);
        frameLihatRiwayatUser.setLayout(null);
        frameLihatRiwayatUser.setVisible(true);

    }

    public void form1() { //MENU AWAL

        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("IdTransaksi");
        tableModel.addColumn("Member");
        tableModel.addColumn("totalBayar");
        tableModel.addColumn("date");
        tableModel.addColumn("caraBayar");
        tableModel.addColumn("refund");
        
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        ArrayList<Transaksi> listUser = new ArrayList<>(ctrl.getAllTransaksi());
        for (int i = 0; i < listUser.size(); i++) {
            tableModel.addRow(ctrl.buatTabel(listUser.get(i)));
        }

        table.setBounds(0, 0, 500, 400);
        scrollPane.setBounds(0, 0, 500, 400);
        panelForm1.add(scrollPane);

        backKeMenu = new JButton("BACK");
        backKeMenu.setBounds(80, 450, 150, 50);
        backKeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backKeMenu.addActionListener(this);

        panelForm1.add(backKeMenu);

        frameLihatRiwayatUser.add(panelForm1);
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backKeMenu) {
            if (frameLihatRiwayatUser.isVisible()) {
                frameLihatRiwayatUser.setVisible(false);
                new MenuH_admin();
            }
        }
    }

    public static void main(String[] args) {
        new MenuA_lihatRiwayatUser();
    }
}
