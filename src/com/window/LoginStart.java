package com.window;

import com.event.LoginEvent;
import com.event.RegEvent;
import com.style.Fonts;
import com.sun.prism.Image;


import javax.swing.*;
import java.awt.*;

public class LoginStart extends JFrame{

    FlowLayout flowLayout;

    // need 5 labels, 1 text file, 1 password text file, 1 login button
    JLabel background; // define background label
    JButton register; // define register label
    JLabel account; //define account label
    JLabel password; // define password label
    JLabel title; // define title label
    public static JTextField accountText;
    public static JPasswordField passwordtext;
    JButton login;


    final int WIDTH = 600;
    final int HEIGHT = 350;
    // define panel
    JPanel jpanel_1; //  to put picture
    JPanel jpanel_2; // to put title
    JPanel jpanel_3; // to put the text file of the account and password

    // define RegEvent;
    RegEvent regEvent;
    LoginEvent loginEvent;

    public LoginStart(){
        init();
        setResizable(false); // set not resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // set is visible
    }
    void init() {
        // create an panel1 object
        jpanel_1 = new JPanel();
        jpanel_1.setBounds(0,0,WIDTH,HEIGHT);
        jpanel_1.setLayout(null);

        // create a panel2 object and put to the center
        jpanel_2 = new JPanel();
        jpanel_2.setBounds(0,0,WIDTH,HEIGHT/5);
        jpanel_2.setBackground(Color.black);
//        jpanel_2.setOpaque(false); // set background invisible
//        flowLayout = new FlowLayout(FlowLayout.CENTER);
//        jpanel_2.setLayout(flowLayout);
//        jpanel_2.setLayout(null);

        //create a panel3 object to store account file and password file
        jpanel_3 = new JPanel();
        jpanel_3.setBounds(250,130,300,100);
//
        flowLayout = new FlowLayout(FlowLayout.RIGHT);
        jpanel_3.setLayout(null);
        jpanel_3.setOpaque(false); // make it invisible




        // set font
        Fonts fontStyle = new Fonts();
        // add title
        this.setTitle("Student Management System");


        // set window's size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width-WIDTH)/2;
        int y = (height- HEIGHT)/2;
        this.setBounds(x,y,WIDTH,HEIGHT);

        // background setting
        ImageIcon img = new ImageIcon("src/img/loginimg.jpg"); // put the picture in to img variable
        JLabel bgimg = new JLabel(img);
        bgimg.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
//        jpanel_1.add(bgimg); // put the picture to the panel


        // add title
        title = new JLabel("Student Management System");
//        title.setVerticalAlignment(JLabel.CENTER);
        title.setBounds(100,-65,500,200);
        title.setFont(fontStyle.title_font);
        title.setForeground(Color.white);

        // add account, password and editor ------------------------------------------------------------------------
        account = new JLabel("account");
        account.setForeground(Color.white);
        account.setFont(fontStyle.content);
        account.setBounds(320,85,200,30);

        // add account text file
        accountText = new JTextField();
        accountText.setBounds(320,110,200,30);
//        accountText.setBounds(300,50,50,15);

        // add password label
        password = new JLabel("password");
        password.setBounds(320,140,200,30);
        password.setForeground(Color.white);
        password.setFont(fontStyle.content);
        // add password text file
        passwordtext = new JPasswordField();
        passwordtext.setBounds(320,165,200,30);

        // add login button
        login = new JButton("Log in");
        login.setBounds(320,210,200,35);
        login.setBorderPainted(false);
        login.setFont(fontStyle.button);
        login.setOpaque(true);
        login.setBackground(Color.black);
        login.setForeground(Color.white);


        // add register
        register = new JButton("Sign up");
//        register.setPreferredSize(new Dimension(80,35));
        register.setBounds(320,260,200,35);
        register.setBorderPainted(false);
        register.setFont(fontStyle.button);
        register.setOpaque(true);
        register.setBackground(Color.black);
        register.setForeground(Color.white);

//        register.setBounds();
        allEvent();


        // add operation

        jpanel_1.add(account);
        jpanel_1.add(accountText);
        jpanel_1.add(password);
        jpanel_1.add(passwordtext);
//        jpanel_3.add(login);
//        jpanel_3.add(register);


        jpanel_1.add(title);
        jpanel_1.add(jpanel_3);
        jpanel_1.add(jpanel_2);
        jpanel_1.add(login);
        jpanel_1.add(register);

//        jpanel_1.add(jpanel_3);
        jpanel_1.add(bgimg); // put the picture to the panel
        this.add(jpanel_1);

        // add account text file
//        accountText = new JTextField(15);
//        accountText.setFont(fontStyle.content);

    }

    // set all event
    void allEvent() {
        regEvent = new RegEvent();
        register.addMouseListener(regEvent);
        loginEvent = new LoginEvent();
        login.addMouseListener(loginEvent);

    }

}
