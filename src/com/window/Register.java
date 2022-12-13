package com.window;

import com.event.AccountEvent;
import com.event.SubmitRegEvent;
import com.style.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Register extends JFrame {
    // define layout
    FlowLayout flowLayout;
    // define window size
    final int WIDTH = 600;
    final int HEIGHT = 350;

    // 10 labels
    JPanel jpanel_1; // to put image and other panel
    JPanel jPanel_2; // to put labels
    JPanel jPanel_3; // to put text files
    JLabel title;
    JLabel studentID;
    public static JTextField studentIDText;
    JLabel fName;

    public static JTextField firstNameText;
    JLabel lName;
    public static JTextField lastNameText;
    JLabel password;
    public static JPasswordField passwordText;
    JLabel confirmPassword;
    public static JPasswordField confirmPasswordText;
    JButton register;

    SubmitRegEvent submitRegEvent;

    // define listener
    ActionListener listener_1;



    public Register(){
        init();
        setResizable(false); // set not resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // set is visible
        validate(); // let component valid
    }
    void init(){
        // set layout
        flowLayout = new FlowLayout(FlowLayout.CENTER);
        // set window's position
        Toolkit kit = Toolkit.getDefaultToolkit(); // get object's size and set position
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width; // get screen width
        int height = screenSize.height; // get screen height
        int x = (width-WIDTH)/2;
        int y = (height- HEIGHT)/2;
        this.setBounds(x,y,WIDTH,HEIGHT);

        // set bottom layout
//        this.setLayout(null);
        this.setTitle("registration");

        // font style
        Fonts fontStyle = new Fonts();

        // set panel1
        jpanel_1 = new JPanel();
        jpanel_1.setBounds(0,0,WIDTH,HEIGHT);
        jpanel_1.setLayout(null);
        jpanel_1.setOpaque(false);
//        jpanel_1.setBackground(new Color(220,220,220));
//        jpanel_1.setBackground(Color.black);

        JPanel topbackground = new JPanel();
        topbackground.setBounds(0,0,WIDTH,HEIGHT/7);
        topbackground.setBackground(Color.black);
        // add image

        // set title
        title = new JLabel("Register for an account");
        title.setBounds(10,-75,500,200);
        title.setFont(fontStyle.button);
        title.setForeground(Color.white);

        // set panel2
        flowLayout = new FlowLayout(FlowLayout.RIGHT);
        jPanel_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 14));
        jPanel_2.setBounds(90,68,150,300);
        jPanel_2.setOpaque(true);
//        jPanel_2.setLayout(flowLayout);
//        jPanel_2.setBackground(new Color(0,0,0));

        // set panel3 to store the text file;
        flowLayout = new FlowLayout(FlowLayout.LEFT);
        jPanel_3 = new JPanel();
        jPanel_3.setBounds(220,70,250,300);
        jPanel_3.setLayout(flowLayout);
        jPanel_3.setOpaque(true);
//        jPanel_3.setBackground(Color.black);


        //set studentID label and text file
        studentID = new JLabel("Account ID:");
        studentIDText = new JTextField(15);
//        studentIDText.setText(" 9 Digits ID");
//        studentIDText.setForeground(new Color(150,150,150));
        // set first name label and text file
        fName = new JLabel("First Name:");
        firstNameText = new JTextField(15);
//        firstNameText.setText("First Name");
//        firstNameText.setForeground(new Color(150,150,150));
        // set last name label and text file
        lName = new JLabel("Last Name:");
        lastNameText = new JTextField(15);
        // set password and confirm password
        password = new JLabel("Password:");
        passwordText = new JPasswordField(15);
        confirmPassword = new JLabel("Confirm Password:");
        confirmPasswordText = new JPasswordField(15);

        // add to panel 2
        jPanel_2.add(studentID);
        jPanel_2.add(fName);
        jPanel_2.add(lName);
        jPanel_2.add(password);
        jPanel_2.add(confirmPassword);

        // add to panel 3
        jPanel_3.add(studentIDText);
        jPanel_3.add(new JLabel("*"));
        jPanel_3.add(firstNameText);
        jPanel_3.add(new JLabel("*"));
        jPanel_3.add(lastNameText);
        jPanel_3.add(new JLabel("*"));
        jPanel_3.add(passwordText);
        jPanel_3.add(new JLabel("*"));
        jPanel_3.add(confirmPasswordText);
        jPanel_3.add(new JLabel("*"));

        // set register
        register = new JButton("Submit");
        register.setFont(fontStyle.button);
        register.setBounds(200,250,200,40);
        register.setOpaque(true);
        register.setBorderPainted(false);
        register.setBackground(new Color(175,12,12));
        register.setForeground(Color.white);

        // set event
        allEvent();
        setAllTag();
        this.add(register);



        jpanel_1.add(jPanel_2);
        jpanel_1.add(title);
        jpanel_1.add(topbackground);
//        jpanel_1.add(jPanel_2);
        jpanel_1.add(jPanel_3);

        this.add(jpanel_1);


    }

    public void allEvent() {
        submitRegEvent = new SubmitRegEvent();
        register.addMouseListener(submitRegEvent);
        listener_1 = new AccountEvent();
        register.addActionListener(listener_1);
//        listener_1 = new AccountEvent();
//        firstNameText.addFocusListener(listener_1);
//        lastNameText.addFocusListener(listener_1);

    }
    void setAllTag() {
//        firstNameText.setName("firstname");
//        lastNameText.setName("lastname");
//        studentIDText.setName("studentID");
//        passwordText.setName("password");
//        confirmPassword.setName("confirmpassword");
        register.setName("reg");
    }


}
