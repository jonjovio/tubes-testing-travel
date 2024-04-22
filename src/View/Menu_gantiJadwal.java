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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import org.jdatepicker.impl.*;


/**
 *
 * @author jonjovio
 */
public class Menu_gantiJadwal implements ActionListener{
    JFrame frameGantiJadwal;
    JPanel panelAwal, panelTiket2, panelTiket1, panelTiket3, panelSewa1, panelSewa2, panelDetail, panelTransaksi;
    JLabel title, title2, labelQrGopay, labelBca;
    JButton butTiket, butSewa, home, next, back, jam[], seatBut[], pesanan[], bca, gopay;
    JComboBox lokasi, rute, jumlahPenumpang;
    JScrollPane scrollPaneTiket1, scrollPaneTiket2, scrollPaneSewa1, scrollPaneSewa2;
    JDatePickerImpl datePicker;
    JDatePanelImpl datePanel;
    int banyakSeatDipilih, transaksiDipilih, tiketKe;
    String jamDipilih, caraBayar;
    ArrayList<TransaksiTiket> transTik = new ArrayList<>();//buat isi transaksi tiket untuk di update
    ArrayList<Tiket> tiket = new ArrayList<>();//buat isi tiket dengan rute tertentu
    ArrayList<String> rut = new ArrayList<>();//buat isi rute dari lokasi tertentu
    ArrayList<Integer> seatUdahDipesan = new ArrayList<>();//buat isi seat yang sudah dipesan orang
    Seat seatDipilih = new Seat();//buat masukin seat yang dipilih
    Controller_tiket ctrl = new Controller_tiket();
    Controller_riwayatTransaksi ctrlRT = new Controller_riwayatTransaksi();
    
    public Menu_gantiJadwal() {
        frameGantiJadwal = new JFrame("MENU GANTI JADWAL");
        frameGantiJadwal.pack();
        frameGantiJadwal.setSize(1000, 700);
        frameGantiJadwal.setLocationRelativeTo(null);
        frameGantiJadwal.getContentPane().setBackground(new Color(51, 153, 255));
        frameGantiJadwal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150,153, 255));
        panelAwal.setBounds(0, 20, 480, 600);
        
        title = new JLabel("HALO, SILAHKAN PILIH");
        title.setBounds(30, 20, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        title2 = new JLabel("PESANAN UNTUK DIUBAH");
        title2.setBounds(30, 50, 400, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        
        butTiket = new JButton("Tiket");
        butTiket.setBounds(115, 100, 250, 70);
        butTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        butTiket.addActionListener(this);
        butTiket.setBackground(Color.yellow);
        butTiket.setEnabled(false);
        
        butSewa = new JButton("Sewa");
        butSewa.setBounds(115, 200, 250, 70);
        butSewa.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        butSewa.addActionListener(this);
        butSewa.setBackground(Color.LIGHT_GRAY);
        
        home = new JButton("BACK TO HOME");
        home.setBounds(100, 450, 300, 50);
        home.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        home.addActionListener(this);
        
        panelAwal.add(home);
        panelAwal.add(title);
        panelAwal.add(title2);
        panelAwal.add(butTiket);
        panelAwal.add(butSewa);
        
        formTiket1();
        frameGantiJadwal.add(panelAwal);
        frameGantiJadwal.setLayout(null);
        frameGantiJadwal.setVisible(true);
    }

    
    public void formTiket1(){
        panelTiket1 = new JPanel();
        panelTiket1.setLayout(null);
        panelTiket1.setBackground(Color.white);
        panelTiket1.setBounds(480, 20, 500, 600);
        
        int xbut = 100;
        transTik = ctrlRT.getTransaksiTiket();
        pesanan = new JButton[transTik.size()];
        for (int i = 0; i < transTik.size(); i++) {
            if (!transTik.get(i).isRefund()) {
                int temp = xbut;
                pesanan[i] = new JButton("<html>" + ctrl.printRute(transTik.get(i).getTiket().getRute().getKeberangkatan(), transTik.get(i).getTiket().getRute().getTujuan()) + "<br>" + transTik.get(i).getTiket().getDate() + "<br>" + transTik.get(i).getTiket().getJam() + "</html>");
                pesanan[i].setBounds(100, xbut, 300, 70);
                pesanan[i].setFont(new Font("Helvetica Neue", Font.BOLD, 18));
                pesanan[i].setBackground(Color.lightGray);
                pesanan[i].addActionListener(this);
                xbut += 100;
            }
        }
        
        next = new JButton("NEXT");
        next.setBounds(100, xbut + 50, 300, 50);
        next.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        next.addActionListener(this);
        
        for (int i = 0; i < pesanan.length; i++) {
            if (!transTik.get(i).isRefund()) {
                panelTiket1.add(pesanan[i]);
            }
        }
        panelTiket1.add(next);
        
        scrollPaneTiket1 = new JScrollPane(panelTiket1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTiket1.setBounds(480, 20, 500, 600);
        panelTiket1.setPreferredSize(new Dimension(0, xbut+200));
        frameGantiJadwal.add(scrollPaneTiket1);
        
    }
    
    public void formTiket2(){
        panelTiket2 = new JPanel();
        panelTiket2.setLayout(null);
        panelTiket2.setBackground(Color.white);
        panelTiket2.setBounds(480, 20, 500, 600);
        panelTiket2.setEnabled(true);
        
        JLabel labelTanggal = new JLabel("Tanggal Keberangkatan");
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
        
        JLabel labelLokasi = new JLabel("Lokasi Keberangkatan");
        labelLokasi.setBounds(50, 170, 200, 30);
        labelLokasi.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        ArrayList<String> lok = ctrl.getKota();
        lokasi = new JComboBox(new DefaultComboBoxModel<String>(lok.toArray(new String[0])));
        lokasi.setBounds(50, 200, 200, 30);
        lokasi.addActionListener(this);
        lokasi.setEnabled(false);
        
        JLabel labelRute = new JLabel("Rute");
        labelRute.setBounds(50, 240, 200, 30);
        labelRute.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        rut = ctrl.getRute((String) lokasi.getSelectedItem(), "");
        rute = new JComboBox(new DefaultComboBoxModel<String>(rut.toArray(new String[0])));
        rute.setBounds(50, 270, 200, 30);
        rute.addActionListener(this);
        rute.setEnabled(false);
        
        JLabel labelPenumpang = new JLabel("Total Penumpang");
        labelPenumpang.setBounds(50, 310, 200, 30);
        labelPenumpang.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        String pen[]={"1","2","3","4"};
        jumlahPenumpang = new JComboBox(pen);
        jumlahPenumpang.setBounds(50, 340, 200, 30);
        jumlahPenumpang.addActionListener(this);
        jumlahPenumpang.setEnabled(false);
        
        back = new JButton("BACK");
        back.setBounds(80, 450, 150, 50);
        back.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        back.addActionListener(this);
        next.setBounds(270, 450, 150, 50);
        
        
        panelTiket2.add(labelTanggal);
        panelTiket2.add(datePicker);
        panelTiket2.add(labelLokasi);
        panelTiket2.add(lokasi);
        panelTiket2.add(labelRute);
        panelTiket2.add(rute);
        panelTiket2.add(labelPenumpang);
        panelTiket2.add(jumlahPenumpang);
        panelTiket2.add(next);
        panelTiket2.add(back);
        frameGantiJadwal.add(panelTiket2);
        
    }
    
    public void formTiket3(){
        panelTiket3 = new JPanel();
        panelTiket3.setLayout(null);
        panelTiket3.setBackground(Color.white);
        panelTiket3.setBounds(480, 20, 500, 600);

        int xBut = 50;
        int yBut = 100;
        jam = new JButton[tiket.size()];
        
        
        for (int i = 0; i < jam.length; i++) {
            jam[i] = new JButton(tiket.get(i).getJam());
            if (i == 0) {
                jam[i].setBounds(xBut, yBut, 175, 50);
            }else if (i % 2 == 0) {
                yBut += 70;
                jam[i].setBounds(xBut, yBut, 175, 50);
            }else{
                jam[i].setBounds(xBut + 225, yBut, 175, 50);
            }
            jam[i].setBackground(Color.lightGray);
            jam[i].setFont(new Font("Helvetica Neue", Font.BOLD, 20));
            jam[i].addActionListener(this);
            
        }
        
        ImageIcon image1;
        JLabel img = new JLabel();
        if (tiket.get(tiketKe).getMobil().getJenisMobil().equals("van")) {
            image1 = new ImageIcon(getClass().getResource("smallvan.png"));
        }else {
            image1 = new ImageIcon(getClass().getResource("bus1.png"));
        }
        
        img.setIcon(image1);
        yBut += 150;
        img.setBounds(50, yBut, 400, 500);
        img.setHorizontalAlignment(SwingConstants.CENTER);
        
        seatBut = new JButton[tiket.get(tiketKe).getMobil().getBanyakKursi()];
        yBut += 500;
        xBut = (500 - 66*(seatBut.length))/2;
        if (tiket.get(tiketKe).getMobil().getJenisMobil().equals("bus")) {
            xBut = (500 - 66*(seatBut.length/2))/2;
        }
        
        for (int i = 0; i < seatBut.length; i++) {
            
            seatBut[i] = new JButton(i+1 + "");
            seatBut[i].setBounds(xBut, yBut, 60, 50);
            xBut += 66;
            seatBut[i].setBackground(Color.lightGray);
            seatBut[i].setFont(new Font("Helvetica Neue", Font.BOLD, 20));
            seatBut[i].addActionListener(this);
            seatBut[i].setEnabled(false);
            if (i == 6) {
                yBut += 70;
                xBut = (500 - 66*(seatBut.length/2))/2;
            }
        }
        
        back = new JButton("BACK");
        back.setBounds(80, yBut + 100, 150, 50);
        back.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        back.addActionListener(this);
        next.setBounds(270, yBut + 100, 150, 50);
        next.setEnabled(false);
        
        for (int i = 0; i < jam.length; i++) {
            panelTiket3.add(jam[i]);
        }
        panelTiket3.add(img);
        for (int i = 0; i < seatBut.length; i++) {
            panelTiket3.add(seatBut[i]);
        }
        
        panelTiket3.add(next);
        panelTiket3.add(back);
        scrollPaneTiket2 = new JScrollPane(panelTiket3, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTiket2.setBounds(480, 20, 500, 600);
        panelTiket3.setPreferredSize(new Dimension(0, 1500));
        frameGantiJadwal.add(scrollPaneTiket2);
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
        
        JLabel labelRuteTrans = new JLabel("RUTE: " + tiket.get(0).getRute().getKeberangkatan() + "-" + tiket.get(0).getRute().getTujuan());
        labelRuteTrans.setBounds(30, 60, 400, 50);
        labelRuteTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelTanggalBerangkatTrans = new JLabel("TANGGAL: " + tiket.get(0).getDate());
        labelTanggalBerangkatTrans.setBounds(30, 90, 400, 50);
        labelTanggalBerangkatTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelJamBerangkatTrans = new JLabel("JAM: " + tiket.get(0).getJam());
        labelJamBerangkatTrans.setBounds(30, 120, 400, 50);
        labelJamBerangkatTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelJenisMobil = new JLabel("Mobil: " + tiket.get(0).getMobil().getJenisMobil());
        labelJenisMobil.setBounds(30, 150, 400, 50);
        labelJenisMobil.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelJumlahTiketTrans = new JLabel("JUMLAH TIKET: " + Integer.parseInt((String) jumlahPenumpang.getSelectedItem()));
        labelJumlahTiketTrans.setBounds(30, 180, 400, 50);
        labelJumlahTiketTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelHargaTiketTrans = new JLabel("HARGA TIKET: " + tiket.get(0).getHarga());
        labelHargaTiketTrans.setBounds(30, 280, 400, 50);
        labelHargaTiketTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        JLabel labelTotalBayarTrans = new JLabel("TOTAL: " + tiket.get(0).getHarga() * Integer.parseInt((String) jumlahPenumpang.getSelectedItem()));
        labelTotalBayarTrans.setBounds(30, 310, 400, 50);
        labelTotalBayarTrans.setFont(new Font("Helvetica Neue", Font.ROMAN_BASELINE, 18));
        
        panelDetail.add(labelRuteTrans);
        panelDetail.add(labelTanggalBerangkatTrans);
        panelDetail.add(labelJamBerangkatTrans);
        panelDetail.add(labelJenisMobil);
        panelDetail.add(labelJumlahTiketTrans);
        panelDetail.add(labelHargaTiketTrans);
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
        
        frameGantiJadwal.add(panelDetail);
        frameGantiJadwal.add(panelTransaksi);
    }
    
//    public void formSewa1(){
//        panelSewa1 = new JPanel();
//        panelSewa1.setLayout(null);
//        panelSewa1.setBackground(Color.white);
//        panelSewa1.setBounds(480, 20, 500, 600);
//        
//        
//        frameGantiJadwal.add(panelSewa1);
//    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            frameGantiJadwal.dispose();
            new Menu_member();
        }
        
        if (ae.getSource() == datePanel) {
            lokasi.setEnabled(true);
        }
        
        if (ae.getSource() == lokasi) {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format((Date) datePicker.getModel().getValue());
            
            rut = ctrl.getRute((String) lokasi.getSelectedItem(), date);
            rute.setModel(new DefaultComboBoxModel<String>(rut.toArray(new String[0])));
            rute.setEnabled(true);
        }
        
        if (ae.getSource() == rute) {
            jumlahPenumpang.setEnabled(true);
        }
        
        if (ae.getSource() == jumlahPenumpang) {
            next.setEnabled(true);
        }
        
        if (ae.getSource() == next) {
            if (scrollPaneTiket1.isVisible()) {
                scrollPaneTiket1.setVisible(false);
                formTiket2();
            }else if (panelTiket2.isVisible()) {
                panelTiket2.setVisible(false);
                title2.setText("JAM & TEMPAT DUDUK");
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format((Date) datePicker.getModel().getValue());
                String rutee = (String) rute.getSelectedItem();
                int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
                Pattern patternReverse = Pattern.compile("\\s");
                String[] tempReverse = patternReverse.split(rutee);
                int temp2 = tempReverse[tempReverse.length - 1].length();
                
                tiket = ctrl.getTiket((String) lokasi.getSelectedItem(), date, Integer.parseInt(rutee.substring(0, temp)), rutee.substring(rutee.length() - temp2), "");
                formTiket3();
            }else if (scrollPaneTiket2.isVisible()) {
                scrollPaneTiket2.setVisible(false);
                panelAwal.setVisible(false);
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format((Date) datePicker.getModel().getValue());
                String rutee = (String) rute.getSelectedItem();
                int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
                Pattern patternReverse = Pattern.compile("\\s");
                String[] tempReverse = patternReverse.split(rutee);
                int temp2 = tempReverse[tempReverse.length - 1].length();
                
                tiket = ctrl.getTiket((String) lokasi.getSelectedItem(), date, Integer.parseInt(rutee.substring(0, temp)), rutee.substring(rutee.length() - temp2), jamDipilih);
                tiketKe = 0;
                formTransaksi();
            }else if (panelTransaksi.isVisible()) {
//                if (ctrl.setTransaksi(tiket.get(0).getIdTiket(), ctrl.transaksi(caraBayar, Integer.parseInt((String) jumlahPenumpang.getSelectedItem()), tiket.get(0).getHarga()), seatDipilih)) {
                    JOptionPane.showMessageDialog (null, "Pesanan anda akan segera di update!", "GANTI JADWAL", JOptionPane.INFORMATION_MESSAGE);
                    frameGantiJadwal.dispose();
                    new Menu_member();
//                }else{
//                    JOptionPane.showMessageDialog (null, "Maaf, Transaksi gagal dilakukan!", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
//                }
            }
        }
        
        if (ae.getSource() == back) {
            if (panelTiket2.isVisible()) {
                panelTiket2.setVisible(false);
                formTiket1();
            }else if (scrollPaneTiket2.isVisible()) {
                scrollPaneTiket2.setVisible(false);
                formTiket2();
            }else if (panelTransaksi.isVisible()) {
                panelTransaksi.setVisible(false);
                title.setBounds(30, 20, 400, 50);
                title2.setBounds(30, 50, 400, 50);
                panelAwal.setVisible(true);
                panelDetail.setVisible(false);
                panelAwal.add(title);
                panelAwal.add(title2);
                panelAwal.setVisible(true);
                panelDetail.setVisible(false);
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format((Date) datePicker.getModel().getValue());
                String idRute = (String) rute.getSelectedItem();
                
                tiket = ctrl.getTiket((String) lokasi.getSelectedItem(), date, Integer.parseInt(idRute.substring(0, 1)), idRute.substring(idRute.length() - 3), "");
                formTiket3();
            }
            
        }
        
        if (ae.getSource() == butTiket) {
            scrollPaneSewa1.setVisible(false);
            butTiket.setBackground(Color.yellow);
            butSewa.setBackground(Color.LIGHT_GRAY);
            butTiket.setEnabled(false);
            butSewa.setEnabled(true);
            formTiket1();
        }
        
//        if (ae.getSource() == butSewa) {
//            scrollPaneTiket1.setVisible(false);
//            butSewa.setBackground(Color.yellow);
//            butTiket.setBackground(Color.LIGHT_GRAY);
//            butSewa.setEnabled(false);
//            butTiket.setEnabled(true);
//            formSewa1();
//        }
        
        for (int i = 0; i < transTik.size(); i++) {
            if (ae.getSource() == pesanan[i]) {
                //buat ganti warna baackground yg dipilih dan ga dipilih
                pesanan[i].setBackground(Color.yellow);
                transaksiDipilih = i;
                for (int j = 0; j < transTik.size(); j++) {
                    if (!transTik.get(j).isRefund()) {
                        if (pesanan[j] != pesanan[i]) {
                            pesanan[j].setBackground(Color.lightGray);
                        }
                    }
                }
            }
        }
        
        if (!scrollPaneTiket1.isVisible() && !panelTiket2.isVisible()) {
            //INI FORNYA BUAT CEK ARRAY JAM KE BRPA YG DICLICK
            for (int i = 0; i < tiket.size(); i++) {
                if (ae.getSource() == jam[i]) {
                    //buat ganti warna baackground yg dipilih dan ga dipilih
                    jam[i].setBackground(Color.yellow);
                    tiketKe = i;
                    System.out.println(i);
                    jamDipilih = tiket.get(i).getJam();
                    for (int j = 0; j < tiket.size(); j++) {
                        if (jam[j] != jam[i]) {
                            jam[j].setBackground(Color.lightGray);
                        }
                    }
                    
                    next.setEnabled(false);
                    seatUdahDipesan = ctrl.getSeatIsi(tiket.get(i).getIdTiket());
                    for (int j = 0; j < tiket.get(tiketKe).getMobil().getBanyakKursi(); j++) {
                        seatBut[j].setEnabled(true);
                        banyakSeatDipilih = 0;
                        seatDipilih.getSeat().clear();
                        if (seatBut[j].getBackground() == Color.YELLOW) {
                            seatBut[j].setBackground(Color.lightGray);
                        }
                        
                    }
                    for (int k = 0; k < seatUdahDipesan.size(); k++) {
                        seatBut[seatUdahDipesan.get(k)-1].setEnabled(false);
                    }
                }
            }

            //INI FORNYA BUAT CEK ARRAY SEAT KE BRPA YG DICLICK
            for (int i = 0; i < tiket.get(tiketKe).getMobil().getBanyakKursi(); i++) {
                if (ae.getSource() == seatBut[i]) {
                    //buat ganti warna baackground yg diclick
                    if (seatBut[i].getBackground() == Color.YELLOW) {
                        seatBut[i].setBackground(Color.lightGray);
                        banyakSeatDipilih--;
                        seatDipilih.getSeat().remove(Integer.valueOf(i+1));
                    }else{
                        seatBut[i].setBackground(Color.YELLOW);
                        banyakSeatDipilih++;
                        seatDipilih.getSeat().add(i+1);
                    }

                    if (banyakSeatDipilih == Integer.parseInt((String) jumlahPenumpang.getSelectedItem())) {
                        next.setEnabled(true);
                        for (int j = 0; j < seatBut.length; j++) {
                            if (seatBut[j].getBackground() != Color.YELLOW) {
                                seatBut[j].setEnabled(false);
                            }
                        }
                    }else{
                        for (int j = 0; j < seatBut.length; j++) {
                            seatBut[j].setEnabled(true);
                        }
                        for (int k = 0; k < seatUdahDipesan.size(); k++) {
                            seatBut[seatUdahDipesan.get(k)-1].setEnabled(false);
                        }
                        next.setEnabled(false);
                    }
                }
            }
        }
        
        if (ae.getSource() == gopay) {
            caraBayar = "GoPay";
            gopay.setBackground(Color.yellow);
            bca.setBackground(Color.lightGray);
            labelQrGopay.setVisible(true);
            labelBca.setVisible(false);
        }
        
        if (ae.getSource() == bca) {
            caraBayar = "BCA";
            bca.setBackground(Color.yellow);
            gopay.setBackground(Color.lightGray);
            labelBca.setVisible(true);
            labelQrGopay.setVisible(false);
        }
        
    }
    
    public static void main(String[] args) {
        new Menu_gantiJadwal();
    }
}
