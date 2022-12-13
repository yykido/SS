package com.event;

import com.mysqldb.Mysqldb;
import com.window.LoginStart;
import com.window.Manage;
import com.window.StudentSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagementEvent implements ActionListener {

    JMenuItem menuItem;
    JButton allJbutton;
    @Override
    public void actionPerformed(ActionEvent e) {
        String student_id = Manage.student_id_text.getText();
        String name = Manage.nametext.getText();
        String age = Manage.agetext.getText();
        String grade = Manage.gradetext.getText();
        String[] str;
        String first_name = "";
        String last_name = "";
        if(!name.equals("")) {
            str = name.split(" ");
            first_name = str[0];
            last_name = str[1];
        }
        String sex = "";
        boolean isMan = Manage.radioM.isSelected();
        boolean isWoman = Manage.radioF.isSelected();
        if(isMan) {
            sex = "male";
        }
        if(isWoman) {
            sex = "female";
        }

        try {
            menuItem = (JMenuItem) e.getSource();
            if(menuItem.getName().equals("See all Account")) {
                System.out.println("'See all Account' picked");
            }
            if(menuItem.getName().equals("Log out")) {
                System.out.println("'Log out' picked");
                StudentSystem.manage.dispose();
                StudentSystem.loginStart = new LoginStart();
            }
        }
        catch (Exception e1) {
            allJbutton = (JButton) e.getSource();
            if(allJbutton.getName().equals("add")) {
//                allButton();
                System.out.println("add listened");
                if(name.equals("")) {
                    JOptionPane.showMessageDialog(null,"Full Name can not be empty","error",JOptionPane.WARNING_MESSAGE);
                }
                else if(grade.equals("")) {
                    JOptionPane.showMessageDialog(null,"Grade can not be empty","error",JOptionPane.WARNING_MESSAGE);
                }
                else if(student_id.equals("")) {
                    JOptionPane.showMessageDialog(null,"Student ID can not be empty","error",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Mysqldb.addStudent(first_name,last_name,sex,age,grade,student_id);

                    String[] data = new String[6];

                    data[0] = first_name;
                    data[1] = last_name;
                    data[2] = sex;
                    data[3] = age;
                    data[4] = grade;
                    data[5] = student_id;
                    Manage.model.addRow(data);
                }

            }
            if(allJbutton.getName().equals("reset")) {
                System.out.println("reset listened");
                Manage.model = (DefaultTableModel) Manage.table.getModel();
                Manage.model.setRowCount(0);
            }

            if(allJbutton.getName().equals("delete")) {
                if(Manage.student_id_text.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Student ID can not be empty","error",JOptionPane.WARNING_MESSAGE);

                }
                else {
                    Mysqldb.deleteStudent(student_id);
                }
//                Mysqldb.deleteStudent(student_id);

            }
            if(allJbutton.getName().equals("modify")) {
                if(Manage.student_id_text.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Student ID can not be empty","error",JOptionPane.WARNING_MESSAGE);

                }
                else {
                    if (name.equals("")) {
                        JOptionPane.showMessageDialog(null, "Full Name can not be empty", "error", JOptionPane.WARNING_MESSAGE);
                    } else if (grade.equals("")) {
                        JOptionPane.showMessageDialog(null, "Grade can not be empty", "error", JOptionPane.WARNING_MESSAGE);
                    } else if (student_id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Student ID can not be empty", "error", JOptionPane.WARNING_MESSAGE);
                    } else {
//                        Mysqldb.modifyStudent(first_name, last_name, age, sex, grade, student_id);

                    }
                }

            }

        }


    }

}
