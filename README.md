# Student Management System

<img width="595" alt="Screenshot 2022-12-11 at 12 35 38" src="https://user-images.githubusercontent.com/97153671/206927431-c62d56a0-7b25-449c-ac2d-3d65e227874b.png">

This student information management system involves students, teachers, classes, student grades, and courses. 
All users need to enter their account and password to log in to the system.
Students can register the course and delete the course they have already registered.
Teachers can add the course for student to register and set the grade for students who have taken teachers' course.


## Getting Started

Click the 'Sign up' button to create your new account, the 9-digit account will default to the student account, and the 7-digit account will default to the teacher account.  
<img width="594" alt="Screenshot 2022-12-12 at 16 28 48" src="https://user-images.githubusercontent.com/97153671/207203978-67b26711-7e4d-41f0-bf57-3d387a0ef397.png">

Click the 'Log in' button to get into the platform. 

* If you have a 7-digit account, you will enter the teacher platform. On this platform, you can add or delete your courses. On the right half, you can check the students who have chosen your courses, and give the students their grades.
<img width="834" alt="Screenshot 2022-12-12 at 16 30 10" src="https://user-images.githubusercontent.com/97153671/207205628-6b060eec-6de6-416d-bd06-ad210d22cf00.png">

* If you have a 9-digit account, you will enter the student platform. On this platform, you can search for all courses, enter the Course ID to register courses, and the right half is the courses you registered and your grades. You can press 'show' or 'hide' to show or hide your grades.   
<img width="834" alt="Screenshot 2022-12-12 at 16 29 41" src="https://user-images.githubusercontent.com/97153671/207205537-acec0962-ebcb-4daa-902c-614b7f22fec6.png">


## Prerequisites
Before you begin, you will need to have following software installed on your computer.
1. Java.
2. IntelliJ or other IDEA.
3. MySQL.


* Run the query statement in Mysql to create database named 'mystudent' with 3 tables named 'user', 'course', 'teacher'. You can find the query statement in the depository named 'CreateTableQuery.sql'.

OR

* Create database named 'mystudent' with 3 tables named 'user', 'course', 'teacher'.   
  'user' table contains four columns 'first_name', 'last_name', 'account' with primary key, 'password' all with varchar(20) Datatype.     
  'course' table contians five columns 'course_id', 'course_name', 'instructor_id', 'student_id', 'grade' all with varchar(20) Datatype.    
  'teacher' table contains three colums 'course_id' with primary key, 'course_name', 'instructor_id' all with varchar(20) Datatype.  

<img width="584" alt="Screenshot 2022-12-11 at 12 04 55" src="https://user-images.githubusercontent.com/97153671/206926861-ee1eb984-2630-4e73-96ca-07344c82c828.png">
<img width="556" alt="Screenshot 2022-12-11 at 12 04 46" src="https://user-images.githubusercontent.com/97153671/206926825-fc8a82b9-0853-4953-bb67-207f77c2297b.png">
<img width="588" alt="Screenshot 2022-12-11 at 12 05 03" src="https://user-images.githubusercontent.com/97153671/206926872-b62ab4c8-b13b-4ed9-8b89-53dfc4928fe6.png">



* Change url variable (in src/com/mysqldb/Mysqldb.java line 24) localhost number to your localhost number.   

* Change two variables (in src/com/window/StudentSystem.java) "root" and "password" to your MySQL account number and password.

## Run the StudentSystem.java file to open the project.

If you have successfully connected to the database the information shown in the figure below will be displayed, after starting the project, register an account first, the 9-digit account will default to the student account, and the 7-digit account will default to the teacher account. 
<img width="505" alt="Screenshot 2022-12-11 at 12 28 00" src="https://user-images.githubusercontent.com/97153671/206927166-f9dfa22c-fff7-4b33-a894-b2db04e95c38.png">

If not, don't worry. Just open the src/com/window/StudentSystem.java file. 
And Uncomment the main function the corresponding window. 
In this case, most functions (like adding, deleting, modyfing and searching) will not work due to the disconnection to the MySQL database.
