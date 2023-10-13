CREATE DATABASE KAREEM_ELASSAD_SYSCBOOK;

USE KAREEM_ELASSAD_SYSCBOOK;

CREATE TABLE USERS_INFO (
    STUDENT_ID INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    STUDENT_EMAIL VARCHAR(150),
    FIRST_NAME VARCHAR(150),
    LAST_NAME VARCHAR(150),
    DOB DATE
);

ALTER TABLE USERS_INFO AUTO_INCREMENT = 100100;

CREATE TABLE USERS_PROGRAM (
    STUDENT_ID INT(10) UNSIGNED PRIMARY KEY,
    PROGRAM VARCHAR(50),
    FOREIGN KEY (STUDENT_ID) REFERENCES USERS_INFO(STUDENT_ID)
);

CREATE TABLE USERS_AVATAR (
    STUDENT_ID INT(10) UNSIGNED PRIMARY KEY,
    AVATAR CHAR(1),
    FOREIGN KEY (STUDENT_ID) REFERENCES USERS_INFO(STUDENT_ID)
);

CREATE TABLE USERS_ADDRESS (
    STUDENT_ID INT(10) UNSIGNED PRIMARY KEY,
    STREET_NUMBER INT(5),
    STREET_NAME VARCHAR(150),
    CITY VARCHAR(30),
    PROVENCE CHAR(2),
    POSTAL_CODE CHAR(7),
    FOREIGN KEY (STUDENT_ID) REFERENCES USERS_INFO(STUDENT_ID)
);

CREATE TABLE USERS_POSTS (
    POST_ID INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    STUDENT_ID INT(10) UNSIGNED,
    NEW_POST TEXT(1000),
    POST_DATE TIMESTAMP,
    FOREIGN KEY (STUDENT_ID) REFERENCES USERS_INFO(STUDENT_ID)
);