CREATE DATABASE IF NOT EXISTS KAREEM_ELASSAD_LAB05;

CREATE TABLE IF NOT EXISTS STUDENT_INFO (
    ID INT(10) PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    DOB DATE NOT NULL,
    INCOME DECIMAL(10, 2) NOT NULL,
    COURSE_ID INT(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS COURSE_INFO (
    ID INT(10) PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    LOCATION VARCHAR(200) NOT NULL,
    STARTDATE DATE NOT NULL,
    TYPE VARCHAR(100) NOT NULL
);

ALTER TABLE STUDENT_INFO ADD ADDRESS VARCHAR(200) DEFAULT NULL;

ALTER TABLE COURSE_INFO MODIFY NAME VARCHAR(200);

RENAME TABLE STUDENT_INFO TO STUDENT;

RENAME TABLE COURSE_INFO TO COURSE;

-- -- Q7 insert 3 rows into STUDENT
INSERT INTO STUDENT VALUES (
    1,
    'JOHN',
    '1998-05-01',
    1200,
    100,
    "NORTH"
);

INSERT INTO STUDENT VALUES (
    2,
    'MIKE',
    '2000-08-15',
    1100.15,
    200,
    "WEST"
);

INSERT INTO STUDENT VALUES (
    3,
    'SAM',
    '1997-11-01',
    500,
    100,
    "SOUTH"
);

-- Q8 insert 4 rows into COURSE
INSERT INTO COURSE VALUES (
    100,
    'Fundamentals of Web Development',
    'Azrieli Pavilion',
    '2023-09-10',
    'Mandatory'
);

INSERT INTO COURSE VALUES (
    300,
    'Analytical Methods',
    'Tory Building',
    '2023-09-17',
    'Elective'
);

INSERT INTO COURSE VALUES (
    500,
    'Java Programming',
    'Tory Building',
    '2023-09-17',
    'Elective'
);

INSERT INTO COURSE VALUES (
    700,
    'C++ Programming',
    'Patterson Hall',
    '2023-09-10',
    'Elective'
);

-- Q9 = Update income col for Student_number 2 in STUDENT table so his income will be 1000 instead of 1100.15
UPDATE STUDENT
SET
    INCOME = 1000
WHERE
    ID = 2;

-- Q10 = Update location col for course number 100 in Course tbale so the location will be Patterson Hall instead of Azreili
UPDATE COURSE
SET
    LOCATION = 'Patterson Hall'
WHERE
    ID = 100;

-- Q11 = Delete student num 2 from STUDENT table
DELETE FROM STUDENT
WHERE
    ID = 2;

-- Q12 = Delete course num 300 from COURSE table
DELETE FROM COURSE
WHERE
    ID = 300;

-- Q13 = Select all rows from STUDENT table & Q14 seleect all from COURSE
SELECT
    *
FROM
    STUDENT;

SELECT
    *
FROM
    COURSE;

-- Q15 = Select student name, dob, income from student
SELECT
    NAME,
    DOB,
    INCOME
FROM
    STUDENT;

-- Q16 = Select courseid, name, location from course
SELECT
    ID,
    NAME,
    LOCATION
FROM
    COURSE;

-- Q17 = select studentid, name, dob, income from students where inome > 600
SELECT
    ID,
    NAME,
    DOB,
    INCOME
FROM
    STUDENT
WHERE
    INCOME > 600;

-- Q18 = select all colmns from COURSE where location = ‘Patterson Hall’ and TYPE = ‘Elective’
SELECT
    *
FROM
    COURSE
WHERE
    LOCATION = 'Patterson Hall'
    AND TYPE = 'Elective';

-- Q19 = select studnetid, name, dob, coursename, from STUDENT, Course (joined on course_id)
SELECT
    STUDENT.ID,
    STUDENT.NAME,
    STUDENT.DOB,
    COURSE.NAME
FROM
    STUDENT
    INNER JOIN COURSE
    ON STUDENT.COURSE_ID = COURSE.ID;

-- Q20 = select student id, name, course name, location, starddate from STUDENT, COURSE where income >1000 (joined on course_id)
SELECT
    STUDENT.ID,
    STUDENT.NAME,
    COURSE.NAME,
    COURSE.LOCATION,
    COURSE.STARTDATE
FROM
    STUDENT
    INNER JOIN COURSE
    ON STUDENT.COURSE_ID = COURSE.ID
WHERE
    STUDENT.INCOME > 1000;

-- Q21 = truncate all data from Student then select all from student
TRUNCATE TABLE STUDENT;

SELECT
    *
FROM
    STUDENT;

-- 22
TRUNCATE TABLE COURSE;

SELECT
    *
FROM
    COURSE;

-- Q23 = drop student table
DROP TABLE STUDENT;

-- Q24 = drop course table
DROP TABLE COURSE;