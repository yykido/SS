package com.event;

import com.mysqldb.Mysqldb;
import com.window.LoginStart;
//import com.window.Manage;
import com.window.Student;
import com.window.StudentSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class StudentEvent implements ActionListener {

    Map<String,String[]> map = new HashMap<>();
    JMenuItem menuItem;
    JButton allJbutton;
    @Override
    public void actionPerformed(ActionEvent e) {
        String course_id = Student.course_id_text.getText();
        String course_name = Student.course_nametext.getText();
        String instructor = Student.instructortext.getText();
        String delete_course_id = Student.insert_delete_course.getText();
//        String grade = Student.gradetext.getText();
        String[] str;
//        String first_name = "";
//        String last_name = "";
//        if(!course_name.equals("")) {
//            str = course_name.split(" ");
//            first_name = str[0];
//            last_name = str[1];
//        }

        try {
            menuItem = (JMenuItem) e.getSource();
            if(menuItem.getName().equals("Refresh")) {
                System.out.println("refresh");
                Mysqldb.showStudentRegisteredCourse(Student.login_account);
            }
            if(menuItem.getName().equals("Log out")) {
                System.out.println("'Log out' picked");
//                System.out.println(Student.login_account);
//                Student.showLoginAccount.setText(Student.login_account);
                Student.model = (DefaultTableModel) Student.table.getModel();
                Student.model.setRowCount(0);
                StudentSystem.student.dispose();
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
                else {
                    String value2 = map.get(course_id)[0];
                    String value3 = map.get(course_id)[1];
//
                    Mysqldb.addCourseToStudent(course_id,value2,value3);

                }


            }
            if(allJbutton.getName().equals("reset")) {
                System.out.println("reset listened");
                Student.model = new DefaultTableModel();
            }
            if(allJbutton.getName().equals("show")) {
                System.out.println("show grade clicked");
                Mysqldb.showGrade(Student.login_account);
            }
            if(allJbutton.getName().equals("hide")) {
                System.out.println("show grade clicked");
                Mysqldb.showStudentRegisteredCourse(Student.login_account);
            }

            if(allJbutton.getName().equals("delete")) {
                if(Student.insert_delete_course.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Student ID can not be empty","error",JOptionPane.WARNING_MESSAGE);

                }
                else {
                    Mysqldb.deleteCourseFromStudent(delete_course_id, Student.login_account);
                    Student.insert_delete_course.setText("");
                }
//                Mysqldb.deleteStudent(student_id);

            }
            if(allJbutton.getName().equals("modify")) {
                if(Student.course_id_text.getText().equals("")) {
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
                Student.model = (DefaultTableModel) Student.table.getModel();
                Student.model.setRowCount(0);

                if(course_id.equals("") && course_name.equals("") && instructor.equals("")) {

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
//                            String dbCourseid = rs.getString("account");
//                            String dbCoursename = rs.getString("first_name");
//                            String dbIstructor = rs.getString("last_name");
                            System.out.println(dbCourseid);
                            System.out.format(" %s, %s, %s\n", dbCourseid, dbCoursename, dbIstructor);
                            String[] data = new String[3];
                            System.out.println("im here");

                            data[0] = dbCourseid;
                            data[1] = dbCoursename;
                            data[2] = StudentSystem.nameIdCon.get(dbIstructor);

                            Student.model.addRow(data);

                        }
                    }
                    catch (Exception e2) {
                        System.err.println("Got an exception! ");
                        System.err.println(e2.getMessage());
                    }
                }
                else if(course_id.equals("") && course_name.equals("")) {
                    try {
                        Connection con = Mysqldb.con;

                        String query = "SELECT * FROM teacher where instructor_id="+instructor;
                        // create statement
                        Statement st = con.createStatement();

                        // execute the query, and get a java resultset
                        ResultSet rs = st.executeQuery(query);

                        // iterate through the java resultset
                        while (rs.next()) {
                            String dbCourseid = rs.getString("course_id");
                            String dbCoursename = rs.getString("course_name");
                            String dbIstructor = rs.getString("instructor_id");

                            System.out.println(dbCourseid);
                            System.out.format(" %s, %s, %s\n", dbCourseid, dbCoursename, dbIstructor);
                            String[] data = new String[3];
                            System.out.println("im here");

                            data[0] = dbCourseid;
                            data[1] = dbCoursename;
                            data[2] = dbIstructor;

                            Student.model.addRow(data);

                        }
                    }
                    catch (Exception e2) {
                        System.err.println("Got an exception! ");
                        System.err.println(e2.getMessage());
                    }

                }
                else if(course_name.equals("") && instructor.equals("")) {
                    try {
                        Connection con = Mysqldb.con;


                        String query = "SELECT * FROM teacher where course_id="+course_id;
                        // create statement
                        Statement st = con.createStatement();
//                        PreparedStatement preSql;
//                        preSql = con.prepareStatement(query);
//                        preSql.setString(1,course_id);

                        // execute the query, and get a java resultset
                        ResultSet rs = st.executeQuery(query);

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

                            data[0] = dbCourseid;
                            data[1] = dbCoursename;
                            data[2] = dbIstructor;

                            Student.model.addRow(data);

                        }
                    }
                    catch (Exception e2) {
                        System.err.println("Got an exception! ");
                        System.err.println(e2.getMessage());
                    }

                }
                else if(course_id.equals("") && instructor.equals("")) {
                    try {
                        Connection con = Mysqldb.con;

                        String query = "SELECT * FROM teacher where course_name="+course_name;
                        System.out.println(course_name);
                        // create statement
                        Statement st = con.createStatement();

                        // execute the query, and get a java resultset
                        ResultSet rs = st.executeQuery(query);

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

                            data[0] = dbCourseid;
                            data[1] = dbCoursename;
                            data[2] = dbIstructor;

                            Student.model.addRow(data);

                        }
                    }
                    catch (Exception e2) {
                        System.err.println("Got an exception! ");
                        System.err.println(e2.getMessage());
                    }

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

}