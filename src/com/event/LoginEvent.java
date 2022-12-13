package com.event;

import com.mysqldb.Mysqldb;
import com.window.LoginStart;
import com.window.Student;
import com.window.StudentSystem;
import com.window.Teacher;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class LoginEvent implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            HashMap<String,String> map = new HashMap<>();
            System.out.println("log in clicked");
            String accountcheck = LoginStart.accountText.getText();
            String passwordcheck = String.valueOf(LoginStart.passwordtext.getPassword());
            String take_first_name="";
            String take_last_name="";
            boolean found = false;
            boolean successLogin = false;
            try {
                Connection con = Mysqldb.con;
                String query = "SELECT * FROM user";
                // create statement
                Statement st = con.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                // iterate through the java resultset
                while (rs.next()) {
                    String account = rs.getString("account");
                    String firstname = rs.getString("first_name");
                    String lastname = rs.getString("last_name");
                    String password = rs.getString("password");
                    map.put(account,firstname+" "+lastname);
//                    System.out.format(" %s, %s, %s, %s\n", account, firstname, lastname, password);
                    if(account.equals(accountcheck)) {
                        found = true;
                    }
                    if(account.equals(accountcheck) && password.equals(passwordcheck)) {
                        JOptionPane.showMessageDialog(null,"Login Success!\n "+firstname+" "+lastname,"success",JOptionPane.WARNING_MESSAGE);
                        successLogin = true;
                        take_first_name = firstname;
                        take_last_name = lastname;
                    }

                }
                StudentSystem.nameIdCon = map;
                if(!found) {
                    JOptionPane.showMessageDialog(null,"Couldn't find your Account","not found",JOptionPane.WARNING_MESSAGE);
                }
                if(found && !successLogin) {
                    JOptionPane.showMessageDialog(null,"The password you entered is incorrect","",JOptionPane.WARNING_MESSAGE);
                }
                st.close();
                if(successLogin) {
//                    StudentSystem.loginStart.dispatchEvent(new WindowEvent(StudentSystem.loginStart, WindowEvent.WINDOW_CLOSING));
//                    StudentSystem.manage = new Manage();

                    if(accountcheck.length() == 7) {
                        StudentSystem.teacher = new Teacher();
                        Teacher.login_account = accountcheck;
                        Teacher.showLoginAccount.setText(Teacher.login_account);
                        Teacher.login_name = take_first_name+ " "+ take_last_name;
                        Teacher.showName.setText(Teacher.login_name);
                        TeacherEvent.showMyCourses();
                        StudentSystem.loginStart.dispose();
                    }
                    else {
                        StudentSystem.student = new Student();
                        System.out.println(accountcheck);
                        Student.login_account = accountcheck;
                        Student.showLoginAccount.setText(Student.login_account);
                        Student.login_name = take_first_name + " "+ take_last_name;
                        Student.showName.setText(Student.login_name);
                        Mysqldb.showStudentRegisteredCourse(Student.login_account);
                        StudentSystem.loginStart.dispose();
                    }
                }
            }
            catch (Exception e1) {
                System.err.println("Got an exception! ");
                System.err.println(e1.getMessage());
            }


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
