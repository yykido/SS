package com.window;

import com.mysqldb.Mysqldb;

import java.util.HashMap;

public class StudentSystem {
    public static LoginStart loginStart;
    public static Register register;
    public static Manage manage;
    public static Student student;
    public static Teacher teacher;
    public static HashMap<String,String> nameIdCon;
    public static void main(String[] args) {
        Mysqldb mysqldb = new Mysqldb("root", "stefano123");
//        loginStart = new LoginStart(); // initialize construction
//        Register register = new Register();
        student = new Student();
//        teacher = new Teacher();
    }
}
