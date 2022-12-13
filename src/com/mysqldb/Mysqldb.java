package com.mysqldb;

import com.event.TeacherEvent;
import com.window.Manage;
import com.window.Student;
import com.window.StudentSystem;
import com.window.Teacher;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;

public class Mysqldb {

    public static Connection con = null;
    public Mysqldb(String account, String password) {
        // defined database's account and password
        // create our mysql database connection
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("connection success");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("connection failed");
        }
        //
        String url = "jdbc:mysql://localhost:3306/mystudent?characterEncoding=utf-8&&useSSL=false";
//        String url ="";
        try {
            con = DriverManager.getConnection(url,account,password);
            System.out.println("database connected");
        }
        catch (SQLException e) {
            System.out.println("database not connected");

        }

    }

    public static void deleteFromTeacher(String course_id) {
        PreparedStatement preSql;
        String sqlStr = "delete from teacher where course_id=?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,course_id);
            int ok = preSql.executeUpdate();
            if( ok == 0) {
                JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"Successfully Deleted","",JOptionPane.WARNING_MESSAGE);
                TeacherEvent.showMyCourses();
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);

        }

        sqlStr = "delete from course where course_id=?";
        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,course_id);
            int ok = preSql.executeUpdate();

        }
        catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);

        }


    }

    public static void addToTeacherTable(String course_id, String course_name, String instructor_id) {
        PreparedStatement preSql;
        String sqlStr = "insert into teacher(course_id,course_name,instructor_id) values(?,?,?)";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,course_id);
            preSql.setString(2,course_name);
            preSql.setString(3,instructor_id);
            int ok = preSql.executeUpdate();
            JOptionPane.showMessageDialog(null,"Successfully Added","",JOptionPane.WARNING_MESSAGE);

        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Course is already exist","error",JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addStudent(String first_name, String last_name,String sex, String age, String grade, String student_id) {
        PreparedStatement preSql;
        String sqlStr = "insert into student(student_id,first_name,last_name,age,sex,grade) values(?,?,?,?,?,?)";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,student_id);
            preSql.setString(2,first_name);
            preSql.setString(3,last_name);
            preSql.setString(4,age);
            preSql.setString(5,sex);
            preSql.setString(6,grade);
            int ok = preSql.executeUpdate();
            JOptionPane.showMessageDialog(null,"Successfully Added","",JOptionPane.WARNING_MESSAGE);

        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Student ID is already exist","error",JOptionPane.WARNING_MESSAGE);
        }

    }
    public static void deleteCourseFromStudent(String course_id, String student_id) {
        PreparedStatement preSql;
        String sqlStr = "delete from course where course_id=? and student_id=?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,course_id);
            preSql.setString(2,student_id);
            int ok = preSql.executeUpdate();
            if( ok == 0) {
                JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"Successfully Deleted","",JOptionPane.WARNING_MESSAGE);
                Mysqldb.showStudentRegisteredCourse(Student.login_account);
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);

        }
    }
    public static void addCourseToStudent(String course_id, String course_name, String instructor) {
        PreparedStatement preSql;
        String sqlStr = "insert into course(course_id,course_name,instructor_id,student_id,grade) values(?,?,?,?,?)";
        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,course_id);
            preSql.setString(2,course_name);
            preSql.setString(3,instructor);
            preSql.setString(4, Student.login_account);
            preSql.setString(5,"");
            int ok = preSql.executeUpdate();
            if(ok == 0) {
                JOptionPane.showMessageDialog(null,"Failed to Add new Course","",JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"Successfully Added","",JOptionPane.WARNING_MESSAGE);

            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);
        }
        showStudentRegisteredCourse(Student.login_account);
//        PreparedStatement preSql;
//        String sqlStr = "insert into student(student_id,first_name,last_name,age,sex,grade) values(?,?,?,?,?,?)";
//
//        try {
//            preSql = con.prepareStatement(sqlStr);
//            preSql.setString(1,course_id);
//            preSql.setString(2,course_name);
//            preSql.setString(3,instructor);
//            preSql.setString(4,"age");
//            preSql.setString(5,"sex");
//            preSql.setString(6,"grade");
//            int ok = preSql.executeUpdate();
//            JOptionPane.showMessageDialog(null,"Successfully Added","",JOptionPane.WARNING_MESSAGE);
//
//        }
//        catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,"Student ID is already exist","error",JOptionPane.WARNING_MESSAGE);
//        }

    }

    public static void deleteStudent(String number) {
        PreparedStatement preSql;
        String sqlStr = "delete from student where student_id=?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,number);
            int ok = preSql.executeUpdate();
            if( ok == 0) {
                JOptionPane.showMessageDialog(null,"Can not find Student ID","error",JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"Successfully Deleted","",JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);

        }

    }

    public static void modifyStudent(String student_id, String course_id, String grade) {
        PreparedStatement preSql;
        String sqlStr = "update course set grade=? where student_id=? and course_id=?";
        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1,grade);
            preSql.setString(2,student_id);
            preSql.setString(3,course_id);
            int ok = preSql.executeUpdate();
            if( ok == 0) {
                JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"Successfully set the grade","",JOptionPane.WARNING_MESSAGE);
                Teacher.search.doClick();
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error","error",JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void showGrade(String studentID) {
        Statement sql;
        ResultSet rs;
        String url = "select * from course";
        try {
            sql = con.createStatement();
            rs = sql.executeQuery(url);
//            System.out.println("2 step");
//            String content = "course_id"+"\t"+"course_name"+"\t   "+"instructor_name"+"\t"+"grade"+"\n";
            String content = "course_id"+"    "+"course_name"+"      "+"instructor_name"+"    "+"grade"+"\n";
            while(rs.next()) {
                String course_id = rs.getString("course_id");
                String course_name = rs.getString("course_name");
                String instructor_id = rs.getString("instructor_id");
                String student_id = rs.getString("student_id");
                String grade = rs.getString("grade");
                if(studentID.equals(student_id)) {
                    content = content+course_id+"\t"+course_name+"\t"+ StudentSystem.nameIdCon.get(instructor_id)+ "\t    "+grade+"\n";
                }
                Student.resultText.setText(content);

            }
        }
        catch (SQLException e) {

        }
    }

    public static void showStudentRegisteredCourse(String studentID) {
        Statement sql;
        ResultSet rs;
        String url = "select * from course";
        try {
            sql = con.createStatement();
            rs = sql.executeQuery(url);
//            System.out.println("2 step");
//            String content = "course_id"+"\t"+"course_name"+"\t   "+"instructor_name"+"\t"+"grade"+"\n";
            String content = "course_id"+"    "+"course_name"+"      "+"instructor_name"+"    "+"grade"+"\n";
            while(rs.next()) {
                String course_id = rs.getString("course_id");
                String course_name = rs.getString("course_name");
                String instructor_id = rs.getString("instructor_id");
                String student_id = rs.getString("student_id");
                if(studentID.equals(student_id)) {
                    content = content+course_id+"\t"+course_name+"\t"+ StudentSystem.nameIdCon.get(instructor_id)+ "\t"+" "+"\n";
                }
                Student.resultText.setText(content);

            }
        }
        catch (SQLException e) {

        }

    }

    public static void showMyStudent(String teacherid) {
        Statement sql;
        ResultSet rs;
        String url = "select * from course where instructor_id="+teacherid;

        try {
            sql = con.createStatement();
            rs = sql.executeQuery(url);
            String content = "course_id"+"\t"+"course_name"+"\t "+"student_id"+"\t       "+"grade"+"\n";
            while(rs.next()) {
                String course_id = rs.getString("course_id");
                String course_name = rs.getString("course_name");
                String grade = rs.getString("grade");
                String student_id = rs.getString("student_id");
                content = content+course_id+"\t"+course_name+"\t "+student_id+"\t       "+grade+"\n";
                Teacher.resultText.setText(content);
            }
        }
        catch (SQLException e) {

        }
    }
    public static void showMyStudent(String teacherid, String courseid) {
        Statement sql;
        ResultSet rs;
        String url = "select * from course where instructor_id="+teacherid+" and course_id="+courseid;

        try {
            sql = con.createStatement();
            rs = sql.executeQuery(url);
            String content = "course_id"+"\t"+"course_name"+"\t "+"student_id"+"\t       "+"grade"+"\n";
            while(rs.next()) {
                String course_id = rs.getString("course_id");
                String course_name = rs.getString("course_name");
                String grade = rs.getString("grade");
                String student_id = rs.getString("student_id");
                content = content+course_id+"\t"+course_name+"\t "+student_id+"\t       "+grade+"\n";
                Teacher.resultText.setText(content);
            }
        }
        catch (SQLException e) {

        }
    }
}
