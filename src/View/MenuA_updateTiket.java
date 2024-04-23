/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerA_updateTiket;
import Controller.Controller_tiket;
import Controller.DatabaseHandler;
import Model.Mobil;
import Model.Rute;
import Model.Tiket;
import Model.Voucher;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jdatepicker.impl.*;

/**
 *
 * @author Gibran<>
 */
public class MenuA_updateTiket implements ActionListener {

    JFrame frameUpdateTiket;
    JLabel title2;
    JPanel panelAwal, panelForm1, panelForm3, panelForm4, panelForm2;
    JButton backKeMenu, ButtonSubmitUpdate, backFromUpdate, backFromTambah, ButtonSubmitTambah, tambahTiket, updateTiket, deleteTiket, ButtonSubmit, backFromDelete;
    JComboBox Tiket, TiketUpdate, inputRute, inputMobil;
    JTextField inputJamTiket, InputHargaTiket, InputIdTiket, InputIdRute, InputIdMobil, InputJam, InputTglTiket;
    JDatePickerImpl datePicker, datePicker2;
    JDatePanelImpl datePanel, datePanel2;
    ArrayList<String> rute = new ArrayList<>();
    ArrayList<String> mobil = new ArrayList<>();
    ArrayList<String> ruteTiket = new ArrayList<>();
    ArrayList<Tiket> listTiket = new ArrayList<>();
    Tiket tiket = new Tiket();
    ControllerA_updateTiket ctrl = new ControllerA_updateTiket();
    Controller_tiket ctrlT = new Controller_tiket();

    MenuA_updateTiket() {
        frameUpdateTiket = new JFrame("MENU ADMIN UPDATE TIKET");
        frameUpdateTiket.pack();
        frameUpdateTiket.setSize(1000, 700);
        frameUpdateTiket.setLocationRelativeTo(null);
        frameUpdateTiket.getContentPane().setBackground(new Color(51, 153, 255));
        frameUpdateTiket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150, 153, 255));
        panelAwal.setBounds(0, 20, 480, 600);

        JLabel title = new JLabel("MENU UPDATE TIKET");
        title.setBounds(30, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title2 = new JLabel("UPDATE / DELETE");
        title2.setBounds(30, 210, 400, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        panelAwal.add(title2);
        panelAwal.add(title);

        form1();
        frameUpdateTiket.add(panelAwal);
        frameUpdateTiket.setLayout(null);
        frameUpdateTiket.setVisible(true);

    }

    public void form1() { //MENU AWAL

        panelForm1 = new JPanel();
        panelForm1.setLayout(null);
        panelForm1.setBackground(Color.white);
        panelForm1.setBounds(480, 20, 500, 600);

        tambahTiket = new JButton("Tambah Tiket");
        tambahTiket.setBounds(130, 100, 250, 100);
        tambahTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        tambahTiket.addActionListener(this);

        updateTiket = new JButton("Update Tiket");
        updateTiket.setBounds(130, 220, 250, 100);
        updateTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        updateTiket.addActionListener(this);

        deleteTiket = new JButton("Delete Tiket");
        deleteTiket.setBounds(130, 340, 250, 100);
        deleteTiket.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        deleteTiket.addActionListener(this);

        backKeMenu = new JButton("Back Ke Menu");
        backKeMenu.setBounds(130, 460, 250, 100);
        backKeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backKeMenu.addActionListener(this);

        panelForm1.add(tambahTiket);
        panelForm1.add(updateTiket);
        panelForm1.add(deleteTiket);
        panelForm1.add(backKeMenu);

        frameUpdateTiket.add(panelForm1);
    }

    public void form3() {  //DELETE
        panelForm3 = new JPanel();
        panelForm3.setLayout(null);
        panelForm3.setBackground(Color.white);
        panelForm3.setBounds(480, 20, 500, 600);

        JLabel labelTiket = new JLabel("Pilih Tiket");
        labelTiket.setBounds(50, 170, 200, 30);
        labelTiket.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        ruteTiket = ctrl.getTiket();
        Tiket = new JComboBox(new DefaultComboBoxModel<String>(ruteTiket.toArray(new String[0])));
        Tiket.setBounds(50, 200, 200, 30);
        Tiket.addActionListener(this);

        ButtonSubmit = new JButton("HAPUS");
        ButtonSubmit.setBounds(300, 450, 150, 50);
        ButtonSubmit.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmit.addActionListener(this);

        backFromDelete = new JButton("BACK");
        backFromDelete.setBounds(80, 450, 150, 50);
        backFromDelete.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backFromDelete.addActionListener(this);

        panelForm3.add(backFromDelete);
        panelForm3.add(ButtonSubmit);
        panelForm3.add(labelTiket);
        panelForm3.add(Tiket);

        frameUpdateTiket.add(panelForm3);
    }

    public void form4() {  //TAMBAH
        panelForm4 = new JPanel();
        panelForm4.setLayout(null);
        panelForm4.setBackground(Color.white);
        panelForm4.setBounds(480, 20, 500, 600);

        JLabel labelTanggal = new JLabel("Tanggal Tiket :");
        labelTanggal.setBounds(50, 40, 200, 30);
        labelTanggal.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(50, 70, 200, 30);
        datePanel.addActionListener(this);

        JLabel labelRute = new JLabel("Rute:");
        labelRute.setBounds(50, 110, 200, 30);
        labelRute.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        rute = ctrl.getRute();
        inputRute = new JComboBox(new DefaultComboBoxModel<String>(rute.toArray(new String[0])));
        inputRute.setBounds(50, 140, 200, 30);
        inputRute.addActionListener(this);
        inputRute.setEnabled(false);

        JLabel labelMobil = new JLabel("MOBIL :");
        labelMobil.setBounds(50, 180, 200, 30);
        labelMobil.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        mobil = ctrl.getMobil();
        inputMobil = new JComboBox(new DefaultComboBoxModel<String>(mobil.toArray(new String[0])));
        inputMobil.setBounds(50, 210, 200, 30);
        inputMobil.addActionListener(this);
        inputMobil.setEnabled(false);

        JLabel labelJam = new JLabel("JAM :");
        labelJam.setBounds(50, 250, 200, 30);
        labelJam.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        inputJamTiket = new JTextField("");
        inputJamTiket.setBounds(50, 280, 200, 30);

        JLabel labelHargaTiket = new JLabel("HARGA TIKET :");
        labelHargaTiket.setBounds(50, 320, 200, 30);
        labelHargaTiket.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputHargaTiket = new JTextField("");
        InputHargaTiket.setBounds(50, 350, 200, 30);

        backFromTambah = new JButton("BACK");
        backFromTambah.setBounds(80, 450, 150, 50);
        backFromTambah.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backFromTambah.addActionListener(this);

        ButtonSubmitTambah = new JButton("Submit");
        ButtonSubmitTambah.setBounds(260, 450, 150, 50);
        ButtonSubmitTambah.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmitTambah.addActionListener(this);

        panelForm4.add(labelRute);
        panelForm4.add(labelMobil);
        panelForm4.add(labelJam);
        panelForm4.add(backFromTambah);
        panelForm4.add(datePicker);
        panelForm4.add(inputRute);
        panelForm4.add(inputMobil);
        panelForm4.add(labelTanggal);
        panelForm4.add(labelHargaTiket);
        panelForm4.add(inputJamTiket);
        panelForm4.add(InputHargaTiket);
        panelForm4.add(ButtonSubmitTambah);

        frameUpdateTiket.add(panelForm4);
    }

    public void form2() { //UPDATE
        panelForm2 = new JPanel();
        panelForm2.setLayout(null);
        panelForm2.setBackground(Color.white);
        panelForm2.setBounds(480, 20, 500, 600);

        JLabel labelTiket = new JLabel("Pilih Tiket Yang Ingin Diubah");
        labelTiket.setBounds(50, 10, 400, 30);
        labelTiket.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        ruteTiket = ctrl.getTiket();
        TiketUpdate = new JComboBox(new DefaultComboBoxModel<String>(ruteTiket.toArray(new String[0])));
        TiketUpdate.setBounds(50, 40, 200, 30);
        TiketUpdate.addActionListener(this);

        JLabel labelJam = new JLabel("JAM :");
        labelJam.setBounds(50, 70, 200, 30);
        labelJam.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputJam = new JTextField("");
        InputJam.setBounds(50, 100, 200, 30);
        InputJam.setEnabled(true);

        JLabel labelTglTiket = new JLabel("TANGGAL BARU :");
        labelTglTiket.setBounds(50, 130, 200, 30);
        labelTglTiket.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel2 = new JDatePanelImpl(model, p);
        datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        datePicker2.setBounds(50, 160, 200, 30);
        datePanel2.addActionListener(this);

        JLabel labelHargaTiket = new JLabel("HARGA TIKET BARU :");
        labelHargaTiket.setBounds(50, 190, 200, 30);
        labelHargaTiket.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        InputHargaTiket = new JTextField("");
        InputHargaTiket.setBounds(50, 220, 200, 30);
        InputHargaTiket.setEnabled(true);

        backFromUpdate = new JButton("BACK");
        backFromUpdate.setBounds(80, 460, 150, 50);
        backFromUpdate.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        backFromUpdate.addActionListener(this);

        ButtonSubmitUpdate = new JButton("Submit");
        ButtonSubmitUpdate.setBounds(260, 460, 150, 50);
        ButtonSubmitUpdate.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        ButtonSubmitUpdate.addActionListener(this);

        panelForm2.add(ButtonSubmitUpdate);
        panelForm2.add(backFromUpdate);
        panelForm2.add(InputHargaTiket);
        panelForm2.add(labelHargaTiket);
        panelForm2.add(labelTglTiket);
        panelForm2.add(labelJam);

        panelForm2.add(TiketUpdate);
        panelForm2.add(labelTiket);
        panelForm2.add(InputHargaTiket);
        panelForm2.add(datePicker2);
        panelForm2.add(InputJam);
        frameUpdateTiket.add(panelForm2);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == TiketUpdate) {
            if (TiketUpdate.getSelectedItem().equals("Tiket")) {
                InputIdTiket.setEnabled(false);
                InputIdRute.setEnabled(false);
                InputHargaTiket.setEnabled(false);
            }
        }
        if (ae.getSource() == backKeMenu) {
            if (frameUpdateTiket.isVisible()) {
                frameUpdateTiket.setVisible(false);
                new MenuH_admin();
            }
        }
        if (ae.getSource() == backFromTambah) {
            if (panelForm4.isVisible()) {
                panelForm4.setVisible(false);
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
        if (ae.getSource() == backFromUpdate) {
            if (panelForm2.isVisible()) {
                panelForm2.setVisible(false);
                title2.setText("UPDATE / DELETE");
                form1();
            }
        }

        if (ae.getSource() == deleteTiket) {
            if (panelForm1.isVisible()) {
                panelForm1.setVisible(false);
                title2.setText("DELETE TIKET");
                form3();
            }

        }
        if (ae.getSource() == updateTiket) {
            if (panelForm1.isVisible()) {
                panelForm1.setVisible(false);
                title2.setText("UPDATE TIKET");
                form2();

            }
        }

        if (ae.getSource() == TiketUpdate) {
            if (frameUpdateTiket.isVisible()) {

                String rutee = (String) TiketUpdate.getSelectedItem();
                System.out.println("rutee " + rutee);
                int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
                System.out.println("temp " + temp);

                listTiket = ctrl.selectDataTiket(Integer.parseInt(rutee.substring(0, temp)));

                InputJam.setText(String.valueOf(listTiket.get(0).getJam()));
                InputHargaTiket.setText(String.valueOf(listTiket.get(0).getHarga()));
            }
        }

        if (ae.getSource() == tambahTiket) {
            if (panelForm1.isVisible()) {
                panelForm1.setVisible(false);
                title2.setText("TAMBAH TIKET");
                form4();
            }
        }
        if (ae.getSource() == ButtonSubmit) {
            
            String rutee = (String) Tiket.getSelectedItem();
            int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
            tiket.setIdTiket(Integer.parseInt(rutee.substring(0, temp)));
            //10 angka keramat

            if (ctrl.deleteTiket(tiket)) {
                panelForm3.setVisible(false);
                form1();
                JOptionPane.showMessageDialog(null, "Hapus Tiket Brhasil!", "HAPUS TIKET", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Hapus Tiket Gagal!", "HAPUS TIKET", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (ae.getSource() == ButtonSubmitUpdate) {
            if (InputJam.getText().equals("") || InputHargaTiket.getText().equals("")) {
                JOptionPane.showMessageDialog (null, "ISI SEMUA DATA!", "UPDATE TIKET", JOptionPane.INFORMATION_MESSAGE);
            } else {
            String rutee = (String) TiketUpdate.getSelectedItem();
            int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
            listTiket = ctrl.selectDataTiket(Integer.parseInt(rutee.substring(0, temp)));
            Tiket tiket = new Tiket();
            tiket.setHarga(Double.parseDouble(InputHargaTiket.getText()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tiket.setDate(java.sql.Date.valueOf(simpleDateFormat.format((Date) datePicker2.getModel().getValue())));
            tiket.setJam(InputJam.getText());
            if (ctrl.updateTiket(tiket, listTiket.get(0).getIdTiket())) {
                panelForm2.setVisible(false);
                form1();
                JOptionPane.showMessageDialog(null, "Update Tiket Brhasil!", "UPDATE TIKET", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Update Tiket Gagal!", "UPDATE TIKET", JOptionPane.INFORMATION_MESSAGE);
            }
            }
        }

        // TAMBAH TIKET
        if (ae.getSource() == datePanel) {
            inputRute.setEnabled(true);
        }
        if (ae.getSource() == inputRute) {
            inputMobil.setEnabled(true);
        }
        if (ae.getSource() == inputMobil) {
            ButtonSubmitTambah.setEnabled(true);
        }
        if (ae.getSource() == ButtonSubmitTambah) {
            if (inputJamTiket.getText().equals("") || InputHargaTiket.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ISI SEMUA DATA!", "TAMBAH TIKET", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Tiket tiket = new Tiket();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tiket.setDate(java.sql.Date.valueOf(simpleDateFormat.format((Date) datePicker.getModel().getValue())));
                tiket.setJam(inputJamTiket.getText());
                tiket.setHarga(Double.parseDouble(InputHargaTiket.getText()));
                String rutee = (String) inputRute.getSelectedItem();
                int temp = rutee.indexOf(" ");//buat ambil brpa char sebelum space
                tiket.getRute().setIdRute(Integer.parseInt(rutee.substring(0, temp)));
                String mobb = (String) inputMobil.getSelectedItem();
                int temp2 = rutee.indexOf(" ");//buat ambil brpa char sebelum space
                tiket.getMobil().setIdMobil(Integer.parseInt(mobb.substring(0, temp2)));
                if (ctrl.addTiket(tiket)) {
                    JOptionPane.showMessageDialog(null, "Tiket berhasil ditambah!", "TAMBAH TIKET", JOptionPane.INFORMATION_MESSAGE);
                    panelForm4.setVisible(false);
                    form1();
                } else {
                    JOptionPane.showMessageDialog(null, "Tiket gagal ditambah!", "TAMBAH TIKET", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }

    }

    public static void main(String[] args) {
        new MenuA_updateTiket();
    }

    static DatabaseHandler conn = new DatabaseHandler();

    public void ambilDataDelete() {
        conn.connect();
        String query = "select * from tiket";
        java.sql.ResultSet rs;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tiket.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
