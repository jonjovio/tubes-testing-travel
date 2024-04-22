/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller_user;
import Model.Member;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Gibran<>
 */
public class Menu_home implements ActionListener {

    JFrame frameHome;
    JPanel panelAwal, panelLogin, panelRegister;
    JTextField email, firstname, lastname, phonenumber;
    JPasswordField password;
    JLabel title, title2, labelNanya;
    JButton buttonLogin, buttonRegister, buttonSubmitLogin, buttonSubmitRegister;

    Member member = new Member();
    
    public Menu_home() {

        frameHome = new JFrame("TRAVEL GAJELAS");
        frameHome.pack();
        frameHome.setSize(1000, 700);
        frameHome.setLocationRelativeTo(null);
        frameHome.getContentPane().setBackground(new Color(51,153, 255));
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAwal = new JPanel();
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(150,153, 255));
        panelAwal.setBounds(500, 20, 500, 600);

        title = new JLabel("SELAMAT DATANG DI");
        title.setBounds(50, 180, 400, 50);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title2 = new JLabel("TRAVEL GAJELAS");
        title2.setBounds(50, 210, 400, 50);
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        labelNanya = new JLabel("Not a member? Sign up now!");
        labelNanya.setBounds(125, 260, 250, 30);
        labelNanya.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        labelNanya.setHorizontalAlignment(SwingConstants.CENTER);
        
        buttonLogin = new JButton("LOGIN");
        buttonLogin.setBounds(175, 290, 150, 50);
        buttonLogin.addActionListener(this);
        buttonLogin.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        buttonLogin.setVisible(false);
        
        buttonRegister = new JButton("REGISTER");
        buttonRegister.setBounds(175, 290, 150, 50);
        buttonRegister.addActionListener(this);
        buttonRegister.setFont(new Font("Helvetica Neue", Font.BOLD, 18));


        panelAwal.add(buttonLogin);
        panelAwal.add(buttonRegister);
        panelAwal.add(labelNanya);
        panelAwal.add(title);
        panelAwal.add(title2);

        login();
        frameHome.add(panelAwal);
        frameHome.setLayout(null);
        frameHome.setVisible(true);
        
    }

    public void login() {
        panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBackground(Color.white);
        panelLogin.setBounds(0, 20, 500, 600);
        
        JLabel titleLogin = new JLabel("LOGIN");
        titleLogin.setBounds(50, 50, 400, 50);
        titleLogin.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(130, 145, 100, 30);
        labelEmail.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        email = new JTextField();
        email.setBounds(130, 170, 250, 40);

        JLabel labelpass = new JLabel("Password");
        labelpass.setBounds(130, 215, 100, 30);
        labelpass.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        password = new JPasswordField();
        password.setBounds(130, 240, 250, 40);

        buttonSubmitLogin = new JButton("LOG IN");
        buttonSubmitLogin.setBounds(130, 300, 250, 50);
        buttonSubmitLogin.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        buttonSubmitLogin.addActionListener(this);
        
        
        panelLogin.add(labelEmail);
        panelLogin.add(labelpass);
        panelLogin.add(email);
        panelLogin.add(password);
        panelLogin.add(titleLogin);
        panelLogin.add(buttonSubmitLogin);
        
        frameHome.add(panelLogin);
    }

    public void register(){
        
        panelRegister = new JPanel();
        panelRegister.setLayout(null);
        panelRegister.setBackground(Color.white);
        panelRegister.setBounds(0, 20, 500, 600);
        
        JLabel titleRegister = new JLabel("REGISTER");
        titleRegister.setBounds(50, 50, 400, 50);
        titleRegister.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        titleRegister.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel labelFirstName = new JLabel("First Name");
        labelFirstName.setBounds(130, 145, 100, 30);
        labelFirstName.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        firstname = new JTextField();
        firstname.setBounds(130, 170, 250, 40);

        JLabel labelLastName = new JLabel("Last Name");
        labelLastName.setBounds(130, 215, 100, 30);
        labelLastName.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        lastname = new JTextField();
        lastname.setBounds(130, 240, 250, 40);
        
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(130, 285, 100, 30);
        labelEmail.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        email = new JTextField();
        email.setBounds(130, 310, 250, 40);
        
        JLabel labelPhoneNumber = new JLabel("Phone Number");
        labelPhoneNumber.setBounds(130, 355, 200, 30);
        labelPhoneNumber.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        phonenumber = new JTextField();
        phonenumber.setBounds(130, 380, 250, 40);
        
        JLabel labelpass = new JLabel("Password");
        labelpass.setBounds(130, 425, 100, 30);
        labelpass.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        password = new JPasswordField();
        password.setBounds(130, 450, 250, 40);

        buttonSubmitRegister = new JButton("REGISTER");
        buttonSubmitRegister.setBounds(130, 520, 250, 50);
        buttonSubmitRegister.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        buttonSubmitRegister.addActionListener(this);
        
        panelRegister.add(labelFirstName);
        panelRegister.add(labelLastName);
        panelRegister.add(labelEmail);
        panelRegister.add(labelPhoneNumber);
        panelRegister.add(labelpass);
        panelRegister.add(firstname);
        panelRegister.add(lastname);
        panelRegister.add(email);
        panelRegister.add(phonenumber);
        panelRegister.add(password);
        panelRegister.add(titleRegister);
        panelRegister.add(buttonSubmitRegister);
        
        frameHome.add(panelLogin);
        
        frameHome.add(panelRegister);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonLogin) {
            panelRegister.setVisible(false);
            buttonLogin.setVisible(false);
            buttonRegister.setVisible(true);
            labelNanya.setText("Not a member? Sign up now!");
            login();
        }
        if (ae.getSource() == buttonRegister) {
            panelLogin.setVisible(false);
            buttonLogin.setVisible(true);
            buttonRegister.setVisible(false);
            labelNanya.setText("Already have an account?");
            register();
        }
        
        if (ae.getSource() == buttonSubmitRegister) {
            if (firstname.getText().equals("") || lastname.getText().equals("") || email.getText().equals("") || password.getText().equals("") || phonenumber.getText().equals("")) {
                JOptionPane.showMessageDialog (null, "Isi semua data!!", "REGISTER", JOptionPane.INFORMATION_MESSAGE);
            }else {
                member.setFirstName(firstname.getText());
                member.setLastName(lastname.getText());
                member.setEmail(email.getText());
                member.setPassword(password.getText());
                member.setPhoneNumber(phonenumber.getText());

                Controller_user ctrl = new Controller_user();
                if (ctrl.checkEmail(member)) {
                    panelRegister.setVisible(false);
                    buttonLogin.setVisible(false);
                    buttonRegister.setVisible(true);
                    labelNanya.setText("Not a member? Sign up now!");
                    login();
                }else{
                    JOptionPane.showMessageDialog (null, "Register yang dilakukan gagal!", "REGISTER", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        if (ae.getSource() == buttonSubmitLogin) {
            Controller_user ctrl = new Controller_user();
            if (ctrl.checkLogin(email.getText(), password.getText()).equals("member")) {
                frameHome.dispose();
                new Menu_member();
            }else if (ctrl.checkLogin(email.getText(), password.getText()).equals("admin")) {
                frameHome.dispose();
                new MenuH_admin();
            }else{
                JOptionPane.showMessageDialog (null, "Email dan Password yang dimasukkan salah/belum berdaftar!", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        
        
        
    }

    public static void main(String[] args) {
        new Menu_home();
    }
}
