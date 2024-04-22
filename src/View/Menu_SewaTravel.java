/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller_Travel;
import Model.Sewa;
import Model.Mobil;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/**
 *
 * @author peter
 */
public class Menu_SewaTravel implements ActionListener{
    JPanel panelAwal,panelForm1, panelTransaksi, panelDetail;
    JLabel title,title2,hari, labelQrGopay, labelBca;
    JButton home,next,back,gopay,bca;
    JComboBox mobil, lamaHari,lokasi;
    JDatePickerImpl datePicker;
    JDatePanelImpl datePanel;
    JFrame frameSewaTravel;
    Controller_Travel ctrl = new Controller_Travel();
    String jamDipilih, caraBayar;
    ArrayList<Sewa> sewa = new ArrayList<>();
    ArrayList<Mobil> car = new ArrayList<>();
    Menu_SewaTravel(){
        frameSewaTravel = new JFrame("MENU SEWA TRAVEL");
        frameSewaTravel.pack();
        frameSewaTravel.setSize(1000, 700);
        frameSewaTravel.setLocationRelativeTo(null);
        frameSewaTravel.getContentPane().setBackground(new Color(51, 153, 255));
        frameSewaTravel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150,153, 255));
        panelAwal.setBounds(0, 20, 480, 600);
        
        title = new JLabel("HALO, SILAHKAN PILIH");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        title2 = new JLabel("SEWA MOBILNYA");
        title2.setBounds(30, 210, 400, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        
        home = new JButton("BACK TO HOME");
        home.setBounds(100, 450, 300, 50);
        home.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        home.addActionListener(this);
        
        panelAwal.add(home);
        panelAwal.add(title);
        panelAwal.add(title2);
        
        form1();
        frameSewaTravel.add(panelAwal);
        frameSewaTravel.setLayout(null);
        frameSewaTravel.setVisible(true);
    }
public void form1() {
        //isi dengan docs sewa travel nomor 2
        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);
        
        JLabel labelTanggal = new JLabel("Tanggal Sewa");
        labelTanggal.setBounds(50, 100, 200, 30);
        labelTanggal.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(50, 130, 200, 30);
        datePanel.addActionListener(this);
        
        JLabel labelHari = new JLabel("Berapa Hari");
        labelHari.setBounds(50, 170, 200, 30);
        labelHari.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        String day[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        lamaHari = new JComboBox(day);
        lamaHari.setBounds(50, 200, 200, 30);
        lamaHari.addActionListener(this);
        lamaHari.setEnabled(false);
        
        JLabel labelLokasi = new JLabel("Lokasi Penyewaan");
        labelLokasi.setBounds(50, 240, 200, 30);
        labelLokasi.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        ArrayList<String>lok= ctrl.getLokasi();
        lokasi = new JComboBox(new DefaultComboBoxModel<String>(lok.toArray(new String[0])));
        lokasi.setBounds(50, 270, 200, 30);
        lokasi.addActionListener(this);
        lokasi.setEnabled(false);
        
        JLabel labelMobil = new JLabel("Jenis Kendaraan");
        labelMobil.setBounds(50, 300, 200, 30);
        labelMobil.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        ArrayList<String>mbl= ctrl.getMobil();
        mobil = new JComboBox(new DefaultComboBoxModel<String>(mbl.toArray(new String[0])));
        mobil.setBounds(50, 330, 200, 30);
        mobil.addActionListener(this);
        mobil.setEnabled(false);
        
        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);
        frameSewaTravel.add(panelForm1);
        
        panelForm1.add(labelTanggal);
        panelForm1.add(datePicker);
        panelForm1.add(labelHari);
        panelForm1.add(lamaHari);
        panelForm1.add(labelLokasi);
        panelForm1.add(lokasi);
        panelForm1.add(labelMobil);
        panelForm1.add(mobil);
        
        next = new JButton("NEXT");
        next.setBounds(100, 450, 300, 50);
        next.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        next.addActionListener(this);
        next.setEnabled(false);
        
        panelForm1.add(next);
        panelForm1.setEnabled(false);
    }
public void formTransaksi(){
        panelDetail = new JPanel();
        panelDetail.setLayout(null);
        panelDetail.setBackground(new Color(150,153, 255));
        panelDetail.setBounds(0, 20, 480, 600);
        
        title.setText("Booking Detail");
        title2.setText("Payment Detail");
        title.setBounds(30, 20, 400, 50);
        title2.setBounds(30, 230, 400, 50);
        
        JLabel labelLokasi = new JLabel("LOKASI: " + sewa.get(0).getLokasi());
        labelLokasi.setBounds(30, 60, 400, 50);
        labelLokasi.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelTanggalSewa = new JLabel("TANGGAL: " + sewa.get(0).getDate());
        labelTanggalSewa.setBounds(30, 90, 400, 50);
        labelTanggalSewa.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelLamaSewa = new JLabel("HARI: " + sewa.get(0).getHari());
        labelLamaSewa.setBounds(30, 120, 400, 50);
        labelLamaSewa.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelJenisMobil = new JLabel("Mobil: " + car.get(0).getJenisMobil());
        labelJenisMobil.setBounds(30, 150, 400, 50);
        labelJenisMobil.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelHargaSewa = new JLabel("HARGA SEWA: " + sewa.get(0).getHarga());
        labelHargaSewa.setBounds(30, 280, 400, 50);
        labelHargaSewa.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelTotalBayarTrans = new JLabel("TOTAL: " + sewa.get(0).getHarga() * sewa.get(0).getHari());
        labelTotalBayarTrans.setBounds(30, 310, 400, 50);
        labelTotalBayarTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        panelDetail.add(labelLokasi);
        panelDetail.add(labelTanggalSewa);
        panelDetail.add(labelLamaSewa);
        panelDetail.add(labelJenisMobil);
        panelDetail.add(labelJenisMobil);
        panelDetail.add(labelHargaSewa);
        panelDetail.add(labelTotalBayarTrans);
        panelDetail.add(title);
        panelDetail.add(title2);
        
        
        panelTransaksi = new JPanel();
        panelTransaksi.setLayout(null);
        panelTransaksi.setBackground(Color.white);
        panelTransaksi.setBounds(480, 20, 500, 600);
        
        
        gopay = new JButton("GO-PAY");
        gopay.setBackground(Color.yellow);
        gopay.setBounds(100, 50, 300, 50);
        gopay.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        gopay.addActionListener(this);
        
        bca = new JButton("BCA Virtual Account");
        bca.setBackground(Color.lightGray);
        bca.setBounds(100, 110, 300, 50);
        bca.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        bca.addActionListener(this);
        
        labelQrGopay = new JLabel();
        ImageIcon qr = new ImageIcon(getClass().getResource("qrGopay.jpg"));
        labelQrGopay.setIcon(qr);
        labelQrGopay.setBounds(100, 200, 300, 200);
        labelQrGopay.setHorizontalAlignment(SwingConstants.CENTER);
        
        labelBca = new JLabel();
        ImageIcon bcaa = new ImageIcon(getClass().getResource("bca.png"));
        labelBca.setIcon(bcaa);
        labelBca.setBounds(100, 200, 300, 200);
        labelBca.setHorizontalAlignment(SwingConstants.CENTER);
        labelBca.setVisible(false);
        
        back = new JButton("BACK");
        back.setBounds(80, 450, 150, 50);
        back.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        back.addActionListener(this);
        next.setText("SELESAI");
        next.setBounds(270, 450, 150, 50);
        
        panelTransaksi.add(gopay);
        panelTransaksi.add(bca);
        panelTransaksi.add(labelQrGopay);
        panelTransaksi.add(labelBca);
        panelTransaksi.add(next);
        panelTransaksi.add(back);
        
        frameSewaTravel.add(panelDetail);
        frameSewaTravel.add(panelTransaksi);
    }
// isi dengan form pembayaran yag mirip ada di Menu_pesanTiket tapi tampilan booking detail dan payment detailnya ubah
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            frameSewaTravel.dispose();
            new Menu_member();
        }
        if (ae.getSource() == datePanel) {
            lamaHari.setEnabled(true);
        }
        if (ae.getSource() == lamaHari) {
            lokasi.setEnabled(true);
        }
        if (ae.getSource() == lokasi) {
            mobil.setEnabled(true);
        }
        if (ae.getSource() == mobil) {
            panelForm1.setEnabled(true);
        }
        if (ctrl.setTransaksi(sewa.get(0).getIdSewa(), ctrl.transaksi(caraBayar,sewa.get(0).getHari(), sewa.get(0).getHarga()))!=null) {
                    JOptionPane.showMessageDialog (null, "Terima Kasih Telah Melakukan Pembayaran.\nPesanan anda akan segera di konfirmasi!", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                    frameSewaTravel.dispose();
                    new Menu_member();
                }else{
                    JOptionPane.showMessageDialog (null, "Maaf, Transaksi gagal dilakukan!", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                }
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    public static void main(String[] args) {
        new Menu_SewaTravel();
    }
}
