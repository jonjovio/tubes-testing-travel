/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller_tiket;
import Model.Seat;
import Model.Tiket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Gibran<>
 */
public class MenuA_lihatJumlahPenumpang implements ActionListener {

    JFrame frameLiatPenumpang;
    JPanel panelAwal, panelForm1;
    JLabel labelJumlahPenumpang;
    JButton backKeMenu, ButtonSubmit;
    JComboBox lokasi, rute, jam;
    JDatePickerImpl datePicker;
    JDatePanelImpl datePanel;
    ArrayList<Tiket> tiket = new ArrayList<>();
    ArrayList<String> rut = new ArrayList<>();
    ArrayList<String> jamm = new ArrayList<>();
    ArrayList<Integer> seatUdahDipesan = new ArrayList<>();
    Controller_tiket ctrl = new Controller_tiket();
    
    MenuA_lihatJumlahPenumpang() {
        frameLiatPenumpang = new JFrame("MENU ADMIN LIHAT JUMLAH PENUMPANG");
        frameLiatPenumpang.pack();
        frameLiatPenumpang.setSize(1000, 700);
        frameLiatPenumpang.setLocationRelativeTo(null);
        frameLiatPenumpang.getContentPane().setBackground(new Color(51, 153, 255));
        frameLiatPenumpang.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150, 153, 255));
        panelAwal.setBounds(0, 20, 480, 600);

        JLabel title = new JLabel("MENU LIHAT PENUMPANG");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        panelAwal.add(title);

        form1();
        frameLiatPenumpang.add(panelAwal);
        frameLiatPenumpang.setLayout(null);
        frameLiatPenumpang.setVisible(true);

    }

    public void form1() { //MENU AWAL

        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);

        backKeMenu = new JButton("BACK");
        backKeMenu.setBounds(100, 450, 300, 50);
        backKeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backKeMenu.addActionListener(this);

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
        
        
        JLabel labelJam = new JLabel("Jam");
        labelJam.setBounds(50, 310, 200, 30);
        labelJam.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        jam = new JComboBox(new DefaultComboBoxModel<String>(jamm.toArray(new String[0])));
        jam.setBounds(50, 340, 200, 30);
        jam.addActionListener(this);
        jam.setEnabled(false);
        
        labelJumlahPenumpang = new JLabel("");
        labelJumlahPenumpang.setBounds(50, 380, 400, 30);
        labelJumlahPenumpang.setFont(new Font("Helvetica Neue", Font.ITALIC, 24));

        panelForm1.add(labelTanggal);
        panelForm1.add(datePicker);
        panelForm1.add(labelLokasi);
        panelForm1.add(lokasi);
        panelForm1.add(labelRute);
        panelForm1.add(rute);
        panelForm1.add(labelJam);
        panelForm1.add(jam);
        panelForm1.add(labelJumlahPenumpang);
        panelForm1.add(backKeMenu);

        frameLiatPenumpang.add(panelForm1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backKeMenu) {
            if (frameLiatPenumpang.isVisible()) {
                frameLiatPenumpang.setVisible(false);
                new MenuH_admin();
            }
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format((Date) datePicker.getModel().getValue());
            String rutee = (String) rute.getSelectedItem();
            int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
            Pattern patternReverse = Pattern.compile("\\s");
            String[] tempReverse = patternReverse.split(rutee);
            int temp2 = tempReverse[tempReverse.length - 1].length();
            tiket = ctrl.getTiket((String) lokasi.getSelectedItem(), date, Integer.parseInt(rutee.substring(0, temp)), rutee.substring(rutee.length() - temp2), "");
            for (int i = 0; i < tiket.size(); i++) {
                jamm.add(tiket.get(i).getJam());
            }
            jam.setModel(new DefaultComboBoxModel<String>(jamm.toArray(new String[0])));
            jam.setEnabled(true);
        }
        
        if (ae.getSource() == jam) {
            for (int i = 0; i < tiket.size(); i++) {
                if (tiket.get(i).getJam().equals(jam.getSelectedItem())) {
                    seatUdahDipesan = ctrl.getSeatIsi(tiket.get(i).getIdTiket());
                    labelJumlahPenumpang.setText("Jumlah Penumpang: " + Integer.toString(seatUdahDipesan.size()));
                }
            }
            
        }
    }

    public static void main(String[] args) {
        new MenuA_lihatJumlahPenumpang();
    }
}
