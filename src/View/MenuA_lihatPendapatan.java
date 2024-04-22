/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerA_Pendapatan;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Gibran<>
 */
public class MenuA_lihatPendapatan implements ActionListener {

    JFrame frameLihatPendapatan;
    JPanel panelAwal, panelForm1;
    JLabel title2;
    JButton backKeMenu, ButtonSubmit;
    JComboBox Bulan, Tahun;
    JLabel hasilPendapatan;
    
    ControllerA_Pendapatan ctrl = new ControllerA_Pendapatan();
    MenuA_lihatPendapatan() {
        frameLihatPendapatan = new JFrame("MENU ADMIN LIHAT PENDAPATAN");
        frameLihatPendapatan.pack();
        frameLihatPendapatan.setSize(1000, 700);
        frameLihatPendapatan.setLocationRelativeTo(null);
        frameLihatPendapatan.getContentPane().setBackground(new Color(51, 153, 255));
        frameLihatPendapatan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150, 153, 255));
        panelAwal.setBounds(0, 20, 480, 600);

        JLabel title = new JLabel("MENU LIHAT PENDAPATAN");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        panelAwal.add(title);

        form1();
        frameLihatPendapatan.add(panelAwal);
        frameLihatPendapatan.setLayout(null);
        frameLihatPendapatan.setVisible(true);

    }

    public void form1() { //MENU AWAL

        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);

        backKeMenu = new JButton("BACK");
        backKeMenu.setBounds(80, 450, 150, 50);
        backKeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backKeMenu.addActionListener(this);

        ButtonSubmit = new JButton("Submit");
        ButtonSubmit.setBounds(260, 450, 150, 50);
        ButtonSubmit.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmit.addActionListener(this);

        JLabel labelBulan = new JLabel("Masukkan Bulan :");
        labelBulan.setBounds(50, 170, 270, 30);
        labelBulan.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        String bulan[] = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        Bulan = new JComboBox(bulan);
        Bulan.setBounds(50, 200, 240, 30);
        Bulan.addActionListener(this);

        JLabel labelTahun = new JLabel("Masukkan Tahun :");
        labelTahun.setBounds(50, 240, 270, 30);
        labelTahun.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        String tahun[] = {"2023", "2022", "2021", "2020", "2019"};
        Tahun = new JComboBox(tahun);
        Tahun.setBounds(50, 270, 240, 30);
        Tahun.addActionListener(this);
    
        JLabel labelPendapatan = new JLabel("Total Pendapatan :");
        labelPendapatan.setBounds(50, 300, 270, 30);
        labelPendapatan.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        hasilPendapatan = new JLabel("");
        hasilPendapatan.setBounds(50, 340, 400, 30);
        hasilPendapatan.setFont(new Font("Helvetica Neue", Font.ITALIC, 24));
        hasilPendapatan.setEnabled(false);
        
        panelForm1.add(labelPendapatan);
        panelForm1.add(hasilPendapatan);
        
        panelForm1.add(labelBulan);
        panelForm1.add(labelTahun);
        panelForm1.add(Bulan);
        panelForm1.add(Tahun);
        panelForm1.add(backKeMenu);
        panelForm1.add(ButtonSubmit);

        frameLihatPendapatan.add(panelForm1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backKeMenu) {
            if (frameLihatPendapatan.isVisible()) {
                frameLihatPendapatan.setVisible(false);
                new MenuH_admin();
            }
        }
        
        if (ae.getSource() == ButtonSubmit) {
            int n = Bulan.getSelectedIndex();
            String m = Tahun.getSelectedItem().toString();
            double a = ctrl.hitungPendapatan(ctrl.cariPendapatan(n + 1,m));
            if (a == 0) {
                hasilPendapatan.setText("Belum ada transaksi di bulan " + n);
            }else{
                hasilPendapatan.setText(String.valueOf(a));
            }
            
        }
    }

    public static void main(String[] args) {
        new MenuA_lihatPendapatan();
    }
}
