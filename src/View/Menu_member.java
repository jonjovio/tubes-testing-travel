/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Member;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jonjovio
 */
public class Menu_member implements ActionListener{

    JFrame frameMember;
    JButton pesanTiket, gantiJadwal, pembatalanTiket, sewaMobil, beliVoucher, riwayatTransaksi, logout;
    
    
    
    public Menu_member(){
        Member member = Member.getMemberInst();
        
        frameMember = new JFrame("MENU MEMBER");
        frameMember.pack();
        frameMember.setSize(1000, 700);
        frameMember.setLocationRelativeTo(null);
        frameMember.getContentPane().setBackground(new Color(51, 153, 255));
        frameMember.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel title = new JLabel(member.welcomeText());
        title.setBounds(90, 50, 800, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        pesanTiket = new JButton("Pesan Tiket");
        pesanTiket.setBounds(190, 120, 250, 100);
        pesanTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        pesanTiket.addActionListener(this);
        
        gantiJadwal = new JButton("Ganti Jadwal");
        gantiJadwal.setBounds(550, 120, 250, 100);
        gantiJadwal.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        gantiJadwal.addActionListener(this);
        
        pembatalanTiket = new JButton("Pembatalan Tiket");
        pembatalanTiket.setBounds(190, 270, 250, 100);
        pembatalanTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        pembatalanTiket.addActionListener(this);
        
        sewaMobil = new JButton("Sewa Mobil");
        sewaMobil.setBounds(550, 270, 250, 100);
        sewaMobil.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        sewaMobil.addActionListener(this);
        
        beliVoucher = new JButton("Beli Voucher");
        beliVoucher.setBounds(190, 420, 250, 100);
        beliVoucher.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        beliVoucher.addActionListener(this);
        
        riwayatTransaksi = new JButton("Riwayar Transaksi");
        riwayatTransaksi.setBounds(550, 420, 250, 100);
        riwayatTransaksi.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        riwayatTransaksi.addActionListener(this);
        
        logout = new JButton("LOGOUT");
        logout.setBounds(390, 550, 200, 50);
        logout.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        logout.addActionListener(this);
        
        frameMember.add(title);
        frameMember.add(pesanTiket);
        frameMember.add(gantiJadwal);
        frameMember.add(pembatalanTiket);
        frameMember.add(sewaMobil);
        frameMember.add(beliVoucher);
        frameMember.add(riwayatTransaksi);
        frameMember.add(logout);
        frameMember.setLayout(null);
        frameMember.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pesanTiket) {
            frameMember.dispose();
            new Menu_pesanTiket();
        }
        
        if (ae.getSource() == gantiJadwal) {
            frameMember.dispose();
            new Menu_gantiJadwal();
        }
        if (ae.getSource() == sewaMobil) {
            frameMember.dispose();
            new Menu_SewaTravel();
        }
        if (ae.getSource() == riwayatTransaksi) {
            frameMember.dispose();
            new Menu_riwayatTransaksi();
        }
        
        if (ae.getSource() == pembatalanTiket) {
            frameMember.dispose();
            new Menu_pembatalanTiket();
        }
         if (ae.getSource() == beliVoucher) {
            frameMember.dispose();
            new Menu_beliVoucher();
        }
        
        if (ae.getSource() == logout) {
            Member member = Member.getMemberInst();
            member.deleteMemberInstance();
            frameMember.dispose();
            new Menu_home();
        }
    }
    
//    public static void main(String[] args) {
//        new Menu_member();
//    }
    
    
}
