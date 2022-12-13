

CREATE TABLE `mystudent`.`course` (
  `course_id` VARCHAR(20) NOT NULL,
  `course_name` VARCHAR(20) NOT NULL,
  `instructor_id` VARCHAR(20) NOT NULL,
  `student_id` VARCHAR(20) NULL,
  `grade` VARCHAR(20) NULL);

CREATE TABLE `mystudent`.`teacher` (
  `course_id` VARCHAR(20) NOT NULL,
  `course_name` VARCHAR(20) NOT NULL,
  `instructor_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`course_id`));

CREATE TABLE `mystudent`.`user` (
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `account` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`account`));


INSERT INTO user(first_name, last_name, account, password)
VALUES 
("Stephen", "Curry", "0001234", "123"),
("Maomao", "Liu", "000123456", "123456"),
("Moyi", "Liu", "000123457", "123456"),
("Elong", "Ma", "0001236", "123"),
("Yao", "Ye", "123456789", "123");

INSERT INTO teacher(course_id, course_name, instructor_id)
VALUES
("5101","lab","0001234"),
("5102","python","0001234"),
("6000","Career","0001234"),
("6205","Algo","0001234"),
("7500","sc","0001236"),
("8000","Basketball","0001234");

INSERT INTO course(course_id, course_name, instructor_id, student_id, grade)
VALUES
("5101","lab","0001234","000123456","80"),
("6205","Algo","0001234","000654321",""),
("6000","Career","0001234","000654321",""),
("7500","sc","0001236","000123456","99"),
("5102","python","0001234","000123456","93"),
("8000","Basketball","0001234","000123456","97");



