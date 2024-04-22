/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller_riwayatTransaksi;
import Controller.Controller_tiket;
import Model.TransaksiTiket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 *
 * @author jonjovio
 */
public class Menu_pembatalanTiket implements ActionListener {

    JFrame framePembatalanTiket;
    JPanel panelTiket;
    JButton home, next, pesanan[];
    ArrayList<TransaksiTiket> transTik = new ArrayList<>();
    Controller_riwayatTransaksi ctrlRT = new Controller_riwayatTransaksi();
    Controller_tiket ctrl = new Controller_tiket();
    int yangDipilih;

    public Menu_pembatalanTiket() {
        framePembatalanTiket = new JFrame("MENU PEMBATALAN TIKET");
        framePembatalanTiket.pack();
        framePembatalanTiket.setSize(1000, 700);
        framePembatalanTiket.setLocationRelativeTo(null);
        framePembatalanTiket.getContentPane().setBackground(new Color(51,153, 255));
        framePembatalanTiket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelTiket = new JPanel();
        panelTiket.setLayout(null);
        panelTiket.setBackground(Color.white);
        panelTiket.setBounds(480, 20, 500, 600);
        
        JLabel title = new JLabel("SILAHKAN PILIH");
        title.setBounds(200, 20, 600, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title2 = new JLabel("PESANAN UNTUK DIBATALKAN");
        title2.setBounds(200, 50, 600, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        int xbut = 20;
        int ybut = 150;
        int banyakTransaksi = 0;
        transTik = ctrlRT.getTransaksiTiket();
        pesanan = new JButton[transTik.size()];
        for (int i = 0; i < transTik.size(); i++) {
            if (!transTik.get(i).isRefund()) {
                long DAY_IN_MS = 1000 * 60 * 60 * 24;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = new Date();
                Date hariSebelum = new Date(transTik.get(i).getTiket().getDate().getTime() + 1 * DAY_IN_MS);
                if (hariSebelum.compareTo(currentDate) > 0) {
                    int temp = xbut;
                    pesanan[i] = new JButton("<html>" + ctrl.printRute(transTik.get(i).getTiket().getRute().getKeberangkatan(), transTik.get(i).getTiket().getRute().getTujuan()) + "<br>" + transTik.get(i).getTiket().getDate() + "<br>" + transTik.get(i).getTiket().getJam() + "</html>");
                    pesanan[i].setBounds(ybut, xbut, 300, 90);
                    pesanan[i].setFont(new Font("Helvetica Neue", Font.BOLD, 18));
                    pesanan[i].setBackground(Color.lightGray);
                    pesanan[i].addActionListener(this);

                    if (banyakTransaksi % 2 == 0) {
                        xbut = temp;
                        ybut = 550;
                    } else {
                        ybut = 150;
                        xbut += 120;
                    }
                    banyakTransaksi++;
                }
            }
        }

        next = new JButton("BATALKAN TIKET");
        next.setBounds(525, 540, 250, 60);
        next.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        next.addActionListener(this);
        next.setEnabled(false);

        home = new JButton("BACK TO HOME");
        home.setBounds(225, 540, 250, 60);
        home.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        home.addActionListener(this);

        framePembatalanTiket.add(home);
        framePembatalanTiket.add(next);
        framePembatalanTiket.add(title);
        framePembatalanTiket.add(title2);
        for (int i = 0; i < pesanan.length; i++) {
            if (!transTik.get(i).isRefund()) {
                long DAY_IN_MS = 1000 * 60 * 60 * 24;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = new Date();
                Date hariSebelum = new Date(transTik.get(i).getTiket().getDate().getTime() + 1 * DAY_IN_MS);
                if (hariSebelum.compareTo(currentDate) > 0) {
                    panelTiket.add(pesanan[i]);
                }
            }
        }

        panelTiket.setBackground(new Color(252, 251,244));
        JScrollPane scrollPaneForm2 = new JScrollPane(panelTiket, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneForm2.setBounds(0, 100, 980, 400);
        
        panelTiket.setPreferredSize(new Dimension(0, xbut + 100));
        framePembatalanTiket.add(scrollPaneForm2);
        framePembatalanTiket.setLayout(null);
        framePembatalanTiket.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == home) {
            framePembatalanTiket.dispose();
            new Menu_member();
        }

        if (ae.getSource() == next) {
            long DAY_IN_MS = 1000 * 60 * 60 * 24;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String d = simpleDateFormat.format(new Date(transTik.get(yangDipilih).getTiket().getDate().getTime() - 2 * DAY_IN_MS));
            Date currentDate = new Date();
            Date hariSebelum = new Date(transTik.get(yangDipilih).getTiket().getDate().getTime() - 2 * DAY_IN_MS);
//            System.out.println(currentDate);
//            System.out.println(hariSebelum);
//            System.out.println("brpa " + hariSebelum.compareTo(currentDate));
            if (hariSebelum.compareTo(currentDate) < 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pembatalan hanya bisa dilakukan 2 hari sebelumnya!", "PEMBATALAN TIKET", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Anda yakin ingin membatalkan tiket ini?\nRefund akan dipotong 20% dari harga beli!", "PEMBATALAN TIKET", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (ctrl.setPembatalanTiket(transTik.get(yangDipilih).getIdTransaksi())) {
                        JOptionPane.showMessageDialog(null, "Pesanan anda akan segera di batalkan!\nRefund akan dikirimkan ke rekening " + transTik.get(yangDipilih).getCaraPembayaran() + " anda dalam waktu 7 hari paling lama!", "PEMBATALAN TIKET", JOptionPane.INFORMATION_MESSAGE);
                        framePembatalanTiket.dispose();
                        new Menu_member();
                    }else {
                        JOptionPane.showMessageDialog(null, "Maaf, Terjadi kesalahan saat membatalkan pesanan anda!\nSilahkan coba lagi!", "PEMBATALAN TIKET", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        for (int i = 0; i < transTik.size(); i++) {
            if (ae.getSource() == pesanan[i]) {
                //buat ganti warna baackground yg dipilih dan ga dipilih
                pesanan[i].setBackground(Color.yellow);
                yangDipilih = i;
                for (int j = 0; j < transTik.size(); j++) {
                    if (!transTik.get(j).isRefund()) {
                        long DAY_IN_MS = 1000 * 60 * 60 * 24;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date currentDate = new Date();
                        Date hariSebelum = new Date(transTik.get(j).getTiket().getDate().getTime() + 1 * DAY_IN_MS);
                        if (hariSebelum.compareTo(currentDate) > 0) {
                            if (pesanan[j] != pesanan[i]) {
                                pesanan[j].setBackground(Color.lightGray);
                            }
                        }
                    }
                }
                next.setEnabled(true);
            }
        }

    }

    public static void main(String[] args) {
        new Menu_pembatalanTiket();
    }

}
