package com.event;

import com.mysqldb.Mysqldb;
import com.window.LoginStart;
//import com.window.Manage;
import com.window.Student;
import com.window.Teacher;
import com.window.StudentSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TeacherEvent implements ActionListener {

    Map<String,String[]> map = new HashMap<>();
    JMenuItem menuItem;
    JButton allJbutton;
    @Override
    public void actionPerformed(ActionEvent e) {
        String course_id = Teacher.course_id_text.getText();
        String course_name = Teacher.course_nametext.getText();
        String instructor_id = Teacher.login_account;
        String student_id = Teacher.student_name_grading.getText();
        String grade = Teacher.student_score.getText();
        String course_id2 = Teacher.course_id_for_search.getText();

        try {
            menuItem = (JMenuItem) e.getSource();
            if(menuItem.getName().equals("Refresh")) {
                System.out.println("refresh");
//                Mysqldb.showStudentRegisteredCourse(Teacher.login_account);
                showMyCourses();
            }
            if(menuItem.getName().equals("Log out")) {
                System.out.println("'Log out' picked");
//                System.out.println(Teacher.login_account);
//                Teacher.showLoginAccount.setText(Teacher.login_account);
                StudentSystem.teacher.dispose();
                StudentSystem.loginStart = new LoginStart();
            }
        }
        catch (Exception e1) {
            allJbutton = (JButton) e.getSource();
            if(allJbutton.getName().equals("add")) {
//                allButton();
                // --------------------------------------------------------

                takeDatafromTable();

                System.out.println("add listened");
                if(course_id.equals("")) {
                    JOptionPane.showMessageDialog(null,"Course ID can not be empty","error",JOptionPane.WARNING_MESSAGE);
                }
                else if(course_name.equals("")) {
                    JOptionPane.showMessageDialog(null,"Course Name can not be empty","error",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Mysqldb.addToTeacherTable(course_id,course_name,instructor_id);
                    showMyCourses();

                }
//                else {
//                    Mysqldb.addStudent(first_name,last_name,age,grade,student_id);
//
//                    String[] data = new String[6];
//
//                    data[0] = first_name;
//                    data[1] = last_name;
//                    data[2] = sex;
//                    data[3] = age;
//                    data[4] = grade;
//                    data[5] = student_id;
//                    Student.model.addRow(data);
//                }

            }
            if(allJbutton.getName().equals("reset")) {
                System.out.println("reset listened");
                Teacher.model = new DefaultTableModel();
            }

            if(allJbutton.getName().equals("delete")) {
                if(Teacher.course_id_text.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Course ID can not be empty","error",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Mysqldb.deleteFromTeacher(course_id);
                }
            }
            if(allJbutton.getName().equals("modify")) {
                if(Teacher.course_id_text.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Student ID can not be empty","error",JOptionPane.WARNING_MESSAGE);

                }
                else {
                    if (course_name.equals("")) {
                        JOptionPane.showMessageDialog(null, "Full Name can not be empty", "error", JOptionPane.WARNING_MESSAGE);
//                    } else if (grade.equals("")) {
//                        JOptionPane.showMessageDialog(null, "Grade can not be empty", "error", JOptionPane.WARNING_MESSAGE);
                    } else if (course_id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Student ID can not be empty", "error", JOptionPane.WARNING_MESSAGE);
                    } else {
//                        Mysqldb.modifyStudent(first_name, last_name, age, sex, grade, student_id);

                    }
                }

            }

            if(allJbutton.getName().equals("search")) {
                System.out.println("search picked");
//                Teacher.model = (DefaultTableModel) Teacher.table.getModel();
//                Teacher.model.setRowCount(0);

                if(course_id2.equals("")) {
                    Mysqldb.showMyStudent(instructor_id);

                }
                else {
                    Mysqldb.showMyStudent(instructor_id,course_id2);
                }
            }
            if(allJbutton.getName().equals("set grade")) {
                System.out.println("set grade picked");
//                Teacher.model = (DefaultTableModel) Teacher.table.getModel();
//                Teacher.model.setRowCount(0);
                if(student_id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Student ID can not be empty", "error", JOptionPane.WARNING_MESSAGE);
                }
                else if( course_id2.equals("")) {
                    JOptionPane.showMessageDialog(null, "Course ID can not be empty", "error", JOptionPane.WARNING_MESSAGE);

                }
                else if(grade.equals("")) {
                    JOptionPane.showMessageDialog(null, "Grade can not be empty", "error", JOptionPane.WARNING_MESSAGE);

                }

                else {
                    Mysqldb.modifyStudent(student_id,course_id2,grade);
                }
            }


        }


    }
    public void takeDatafromTable() {
        map = new HashMap<>();
        try {
            Connection con = Mysqldb.con;
            String query = "SELECT * FROM teacher";
            // create statement
            Statement st = con.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                String dbCourseid = rs.getString("course_id");
                String dbCoursename = rs.getString("course_name");
                String dbIstructor = rs.getString("instructor_id");
                String[] strings = new String[2];

                strings[0] = dbCoursename;
                strings[1] = dbIstructor;
                map.put(dbCourseid,strings);

            }

        }
        catch (Exception e1) {
            System.err.println("Got an exception! ");
            System.err.println(e1.getMessage());
        }
    }

    public static void showMyCourses() {
        Teacher.model = (DefaultTableModel) Teacher.table.getModel();
        Teacher.model.setRowCount(0);

        try {
            Connection con = Mysqldb.con;

            String query = "SELECT * FROM teacher where instructor_id="+Teacher.login_account;
            // create statement
            Statement st = con.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            int i=1;

            // iterate through the java resultset
            while (rs.next()) {
                String dbCourseid = rs.getString("course_id");
                String dbCoursename = rs.getString("course_name");
                String dbIstructor = rs.getString("instructor_id");
//                            String dbCourseid = rs.getString("account");
//                            String dbCoursename = rs.getString("first_name");
//                            String dbIstructor = rs.getString("last_name");
                System.out.println(dbCourseid);
                System.out.format(" %s, %s, %s\n", dbCourseid, dbCoursename, dbIstructor);
                String[] data = new String[3];
                System.out.println("im here");
                data[0] = String.valueOf(i);

                data[1] = dbCourseid;
                data[2] = dbCoursename;
                i++;

                Teacher.model.addRow(data);

            }
        }
        catch (Exception e2) {
            System.err.println("Got an exception! ");
            System.err.println(e2.getMessage());
        }
    }

}