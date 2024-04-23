/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author jonjovio
 */
public class Menu_riwayatTransaksi implements ActionListener{
    JFrame frameRiwayatTransaksi;
    JPanel panelAwal, panelRiwayatTiket, panelRiwayatSewa, panelRiwayatVoucher;
    JButton home, butTiket, butSewa, butVoucher;
    JScrollPane scrollPaneForm1, scrollPaneForm2, scrollPaneForm3;
    Controller_riwayatTransaksi ctrl = new Controller_riwayatTransaksi();
    Controller_tiket ctrlTiket = new Controller_tiket();
    
    public Menu_riwayatTransaksi(){
        
        frameRiwayatTransaksi = new JFrame("MENU RIWAYAT TRANSAKSI");
        frameRiwayatTransaksi.pack();
        frameRiwayatTransaksi.setSize(1000, 700);
        frameRiwayatTransaksi.setLocationRelativeTo(null);
        frameRiwayatTransaksi.getContentPane().setBackground(new Color(51, 153, 255));
        frameRiwayatTransaksi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150,153, 255));
        panelAwal.setBounds(0, 20, 480, 600);
        
        butTiket = new JButton("Tiket");
        butTiket.setBounds(115, 50, 250, 70);
        butTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        butTiket.addActionListener(this);
        butTiket.setBackground(Color.yellow);
        butTiket.setEnabled(false);
        
        butSewa = new JButton("Sewa");
        butSewa.setBounds(115, 150, 250, 70);
        butSewa.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        butSewa.addActionListener(this);
        butSewa.setBackground(Color.LIGHT_GRAY);
        
        butVoucher = new JButton("Voucher");
        butVoucher.setBounds(115, 250, 250, 70);
        butVoucher.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        butVoucher.addActionListener(this);
        butVoucher.setBackground(Color.LIGHT_GRAY);
        
        home = new JButton("BACK TO HOME");
        home.setBounds(90, 450, 300, 50);
        home.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        home.addActionListener(this);
        
        panelAwal.add(home);
        panelAwal.add(butTiket);
        panelAwal.add(butSewa);
        panelAwal.add(butVoucher);
        
        formRiwayatTiket();
        frameRiwayatTransaksi.add(panelAwal);
        frameRiwayatTransaksi.setLayout(null);
        frameRiwayatTransaksi.setVisible(true);
        
        
    }

    public void formRiwayatTiket(){
        panelRiwayatTiket = new JPanel();
        panelRiwayatTiket.setLayout(null);
        panelRiwayatTiket.setBackground(Color.white);
        panelRiwayatTiket.setBounds(480, 20, 500, 600);
        
        ArrayList<TransaksiTiket> transTik = ctrl.getTransaksiTiket();
        
        JLabel rute[], tanggal[], jam[], banyakTiket[], caraPembayaran[], totalBayar[], title[], refund[];
        title = new JLabel[transTik.size()];
        rute = new JLabel[transTik.size()];
        tanggal = new JLabel[transTik.size()];
        jam = new JLabel[transTik.size()];
        banyakTiket = new JLabel[transTik.size()];
        caraPembayaran = new JLabel[transTik.size()];
        totalBayar = new JLabel[transTik.size()];
        refund = new JLabel[transTik.size()];
        
        int xbut = 20;
        int ybut = 100;
        
        if (transTik.size() < 1) {
            JLabel titlee = new JLabel("<html>BELUM ADA<br>TRANSASKSI TIKET</html>");
            titlee.setBounds(50, xbut, 400, 100);
            titlee.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
            xbut += 30;
            panelRiwayatTiket.add(titlee);
        } else {
            for (int i = 0; i < transTik.size(); i++) {
                int temp = xbut;

                title[i] = new JLabel("TRANSAKSI " + (i+1));
                title[i].setBounds(ybut, xbut, 300, 50);
                title[i].setFont(new Font("Helvetica Neue", Font.BOLD, 30));
                xbut += 30;

                rute[i] = new JLabel("Rute: " + ctrlTiket.printRute(transTik.get(i).getTiket().getRute().getKeberangkatan(), transTik.get(i).getTiket().getRute().getTujuan()));
                rute[i].setBounds(ybut, xbut, 300, 50);
                rute[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                tanggal[i] = new JLabel("Tanggal: " + transTik.get(i).getTiket().getDate());
                tanggal[i].setBounds(ybut, xbut, 300, 50);
                tanggal[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                jam[i] = new JLabel("Jam: " + transTik.get(i).getTiket().getJam());
                jam[i].setBounds(ybut, xbut, 300, 50);
                jam[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                banyakTiket[i] = new JLabel("Tiket: " + ctrl.banyakTiket(transTik.get(i).getIdTransaksi()));
                banyakTiket[i].setBounds(ybut, xbut, 300, 50);
                banyakTiket[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                caraPembayaran[i] = new JLabel("Cara Pembayaran: " + transTik.get(i).getCaraPembayaran());
                caraPembayaran[i].setBounds(ybut, xbut, 300, 50);
                caraPembayaran[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                totalBayar[i] = new JLabel("Total Bayar: " + transTik.get(i).getTotalPembayaran());
                totalBayar[i].setBounds(ybut, xbut, 300, 50);
                totalBayar[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;
                
                refund[i] = new JLabel("Refund: " + transTik.get(i).isRefund());
                refund[i].setBounds(ybut, xbut, 300, 50);
                refund[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 100;
            }

            for (int i = 0; i < transTik.size(); i++) {
                panelRiwayatTiket.add(title[i]);
                panelRiwayatTiket.add(rute[i]);
                panelRiwayatTiket.add(tanggal[i]);
                panelRiwayatTiket.add(jam[i]);
                panelRiwayatTiket.add(banyakTiket[i]);
                panelRiwayatTiket.add(caraPembayaran[i]);
                panelRiwayatTiket.add(totalBayar[i]);
                panelRiwayatTiket.add(refund[i]);
            }
        }
        scrollPaneForm1 = new JScrollPane(panelRiwayatTiket, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneForm1.setBounds(480, 20, 500, 600);
        panelRiwayatTiket.setPreferredSize(new Dimension(0, xbut+100));
        frameRiwayatTransaksi.add(scrollPaneForm1);
    }
    
    public void formRiwayatSewa(){
        panelRiwayatSewa = new JPanel();
        panelRiwayatSewa.setLayout(null);
        panelRiwayatSewa.setBackground(Color.white);
        panelRiwayatSewa.setBounds(480, 20, 500, 600);
        
        ArrayList<TransaksiSewa> transSewa = ctrl.getTransaksiSewa();
        
        JLabel jenisMobil[], tanggal[], lama[], caraPembayaran[], totalBayar[], title[];
        title = new JLabel[transSewa.size()];
        jenisMobil = new JLabel[transSewa.size()];
        tanggal = new JLabel[transSewa.size()];
        lama = new JLabel[transSewa.size()];
        caraPembayaran = new JLabel[transSewa.size()];
        totalBayar = new JLabel[transSewa.size()];
        
        int xbut = 20;
        int ybut = 100;
        
        if (transSewa.size() < 1) {
            JLabel titlee = new JLabel("<html>BELUM ADA<br>TRANSASKSI SEWA</html>");
            titlee.setBounds(50, xbut, 400, 100);
            titlee.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
            xbut += 30;
            panelRiwayatSewa.add(titlee);
        } else {
            for (int i = 0; i < transSewa.size(); i++) {
                int temp = xbut;

                title[i] = new JLabel("TRANSAKSI " + (i+1));
                title[i].setBounds(ybut, xbut, 300, 50);
                title[i].setFont(new Font("Helvetica Neue", Font.BOLD, 30));
                xbut += 30;

                jenisMobil[i] = new JLabel("Jenis Mobil: " + transSewa.get(i).getSewa().getMobil().getJenisMobil());
                jenisMobil[i].setBounds(ybut, xbut, 300, 50);
                jenisMobil[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                tanggal[i] = new JLabel("Tanggal sewa: " + transSewa.get(i).getSewa().getDate());
                tanggal[i].setBounds(ybut, xbut, 300, 50);
                tanggal[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                lama[i] = new JLabel("Lama sewa: " + transSewa.get(i).getSewa().getHari());
                lama[i].setBounds(ybut, xbut, 300, 50);
                lama[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                caraPembayaran[i] = new JLabel("Cara Pembayaran: " + transSewa.get(i).getCaraPembayaran());
                caraPembayaran[i].setBounds(ybut, xbut, 300, 50);
                caraPembayaran[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                totalBayar[i] = new JLabel("Total Bayar: " + transSewa.get(i).getTotalPembayaran());
                totalBayar[i].setBounds(ybut, xbut, 300, 50);
                totalBayar[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 100;
            }

            for (int i = 0; i < transSewa.size(); i++) {
                panelRiwayatSewa.add(title[i]);
                panelRiwayatSewa.add(jenisMobil[i]);
                panelRiwayatSewa.add(tanggal[i]);
                panelRiwayatSewa.add(lama[i]);
                panelRiwayatSewa.add(caraPembayaran[i]);
                panelRiwayatSewa.add(totalBayar[i]);
            }
        }
        
        scrollPaneForm2 = new JScrollPane(panelRiwayatSewa, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneForm2.setBounds(480, 20, 500, 600);
        panelRiwayatSewa.setPreferredSize(new Dimension(0, xbut+100));
        frameRiwayatTransaksi.add(scrollPaneForm2);
    }
    
    
    public void formRiwayatVoucher(){
        panelRiwayatVoucher = new JPanel();
        panelRiwayatVoucher.setLayout(null);
        panelRiwayatVoucher.setBackground(Color.white);
        panelRiwayatVoucher.setBounds(480, 20, 500, 600);
        
        ArrayList<TransaksiVoucher> transVoucher = ctrl.getTransaksiVoucher();
        
        JLabel idVoucher[], banyakTiket[], caraPembayaran[], totalBayar[], title[];
        title = new JLabel[transVoucher.size()];
        idVoucher = new JLabel[transVoucher.size()];
        banyakTiket = new JLabel[transVoucher.size()];
        caraPembayaran = new JLabel[transVoucher.size()];
        totalBayar = new JLabel[transVoucher.size()];
        
        int xbut = 20;
        int ybut = 100;
        
        if (transVoucher.size() < 1) {
            JLabel titlee = new JLabel("<html>BELUM ADA<br>TRANSASKSI Voucher</html>");
            titlee.setBounds(50, xbut, 400, 100);
            titlee.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
            xbut += 30;
            panelRiwayatSewa.add(titlee);
        } else {
            for (int i = 0; i < transVoucher.size(); i++) {
                int temp = xbut;

                title[i] = new JLabel("TRANSAKSI " + (i+1));
                title[i].setBounds(ybut, xbut, 300, 50);
                title[i].setFont(new Font("Helvetica Neue", Font.BOLD, 30));
                xbut += 30;

                idVoucher[i] = new JLabel("Id Voucher: " + transVoucher.get(i).getVoucher().getIdVoucher());
                idVoucher[i].setBounds(ybut, xbut, 300, 50);
                idVoucher[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                caraPembayaran[i] = new JLabel("Cara Pembayaran: " + transVoucher.get(i).getCaraPembayaran());
                caraPembayaran[i].setBounds(ybut, xbut, 300, 50);
                caraPembayaran[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 30;

                totalBayar[i] = new JLabel("Total Bayar: " + transVoucher.get(i).getTotalPembayaran());
                totalBayar[i].setBounds(ybut, xbut, 300, 50);
                totalBayar[i].setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
                xbut += 100;
            }

            for (int i = 0; i < transVoucher.size(); i++) {
                panelRiwayatVoucher.add(title[i]);
                panelRiwayatVoucher.add(idVoucher[i]);
                panelRiwayatVoucher.add(banyakTiket[i]);
                panelRiwayatVoucher.add(caraPembayaran[i]);
                panelRiwayatVoucher.add(totalBayar[i]);
            }
        }
        
        scrollPaneForm3 = new JScrollPane(panelRiwayatVoucher, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneForm3.setBounds(480, 20, 500, 600);
        panelRiwayatVoucher.setPreferredSize(new Dimension(0, xbut+100));
        frameRiwayatTransaksi.add(scrollPaneForm3);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            frameRiwayatTransaksi.dispose();
            new Menu_member();
        }
        
        if (ae.getSource() == butTiket) {
            if (scrollPaneForm2.isVisible()) {
                scrollPaneForm2.setVisible(false);
            }else if(scrollPaneForm3.isVisible()){
                scrollPaneForm3.setVisible(false);
            }
            scrollPaneForm2.setVisible(false);
            
            butTiket.setBackground(Color.yellow);
            butSewa.setBackground(Color.LIGHT_GRAY);
            butVoucher.setBackground(Color.LIGHT_GRAY);
            butTiket.setEnabled(false);
            butSewa.setEnabled(true);
            butVoucher.setEnabled(true);
            formRiwayatTiket();
        }
        
        if (ae.getSource() == butSewa) {
            if (scrollPaneForm1.isVisible()) {
                scrollPaneForm1.setVisible(false);
            }else if(scrollPaneForm3.isVisible()){
                scrollPaneForm3.setVisible(false);
            }
            butSewa.setBackground(Color.yellow);
            butTiket.setBackground(Color.LIGHT_GRAY);
            butVoucher.setBackground(Color.LIGHT_GRAY);
            butSewa.setEnabled(false);
            butTiket.setEnabled(true);
            butVoucher.setEnabled(true);
            formRiwayatSewa();
        }
        
        if (ae.getSource() == butVoucher) {
            if (scrollPaneForm1.isVisible()) {
                scrollPaneForm1.setVisible(false);
            }else if(scrollPaneForm2.isVisible()){
                scrollPaneForm2.setVisible(false);
            }
            butSewa.setBackground(Color.LIGHT_GRAY);
            butTiket.setBackground(Color.LIGHT_GRAY);
            butVoucher.setBackground(Color.yellow);
            butSewa.setEnabled(true);
            butTiket.setEnabled(true);
            butVoucher.setEnabled(false);
            formRiwayatVoucher();
        }
    }
    
    public static void main(String[] args) {
        new Menu_riwayatTransaksi();
    }
    
}
