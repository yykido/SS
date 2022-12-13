package com.event;

import com.mysqldb.Mysqldb;
import com.window.Register;
import com.window.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

public class AccountEvent implements ActionListener {


    JTextField alluse;
    JButton allJbutton;
    Statement sql;
    ResultSet rs;
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            alluse = (JTextField) e.getSource();
            if(alluse != null) {
                allUse();
            }
        }
        catch (Exception e1) {
            allJbutton = (JButton) e.getSource();
            if(allJbutton.getName() == "reg") {
                allButton();
                System.out.println("register listened");
            }
        }


    }

    void allUse() {

    }

    void allButton() {
        // check if password and confirm password are the same
        // First Name and Last Name are not null
        // account is not null
        // password is not null
        if(Register.studentIDText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter Account ID","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(Register.firstNameText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter First Name","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(Register.lastNameText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter Last Name","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(Register.passwordText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter Password","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(Register.confirmPasswordText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please confirm your Password","error",JOptionPane.WARNING_MESSAGE);
        }
//        else if(Register.studentIDText.getText().length() != 9) {
//            JOptionPane.showMessageDialog(null,"Please enter a valid Student ID (9 digits)","error",JOptionPane.WARNING_MESSAGE);
//        }
        else if(false) {
            JOptionPane.showMessageDialog(null,"Please enter a valid First Name","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(false) {
            JOptionPane.showMessageDialog(null,"Please enter a valid Last Name","error",JOptionPane.WARNING_MESSAGE);
        }
        else if(false) {
            JOptionPane.showMessageDialog(null,"Please enter a valid Password","error",JOptionPane.WARNING_MESSAGE);
        }

        else {
            char[] password = Register.passwordText.getPassword();
            char[] conpass = Register.confirmPasswordText.getPassword();
            boolean isMatch = false;
            if(password.length == conpass.length) {
                for(int i=0; i<password.length; i++) {
                    if(password[i] != conpass[i]) {
                        break;
                    }
                    if(i == password.length-1) {
                        isMatch = true;
                    }
                }
            }

            if(!isMatch) {
                JOptionPane.showMessageDialog(null,"Password did not match","error",JOptionPane.WARNING_MESSAGE);
            }
            else {
                String firstname = Register.firstNameText.getText();
                String lastname = Register.lastNameText.getText();
                String account = Register.studentIDText.getText();
                String strpassword = String.valueOf(password);
                Connection con = Mysqldb.con;

                PreparedStatement preSql;
                String url = "insert into user values(?,?,?,?)";
                try {
                    preSql = con.prepareStatement(url);
                    preSql.setString(1,firstname);
                    preSql.setString(2,lastname);
                    preSql.setString(3, account);
                    preSql.setString(4, strpassword);
                    System.out.println("insert to database");
                    int ok = preSql.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Congratulation your account has been successfully created","success",JOptionPane.WARNING_MESSAGE);
                    StudentSystem.register.dispose();


//                    sql = con.createStatement();
//                    rs = sql.execute(strpassword);

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Registration Failed - Account already exist","error",JOptionPane.WARNING_MESSAGE);


                }
            }
        }
//        else if(Register.studentIDText.getText().length() != 9) {
//            JOptionPane.showMessageDialog(null,"Please enter a valid Student ID (9 digits)","error",JOptionPane.WARNING_MESSAGE);
//        }
    }
}

