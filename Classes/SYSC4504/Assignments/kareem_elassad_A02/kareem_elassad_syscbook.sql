-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2023 at 02:31 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

START TRANSACTION;

SET TIME_ZONE = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kareem_elassad_syscbook`
--

-- --------------------------------------------------------

--
-- Table structure for table `users_address`
--

CREATE DATABASE IF NOT EXISTS KAREEM_ELASSAD_SYSCBOOK;

CREATE TABLE `USERS_ADDRESS` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL,
  `STREET_NUMBER` INT(5) DEFAULT NULL,
  `STREET_NAME` VARCHAR(150) DEFAULT NULL,
  `CITY` VARCHAR(30) DEFAULT NULL,
  `PROVENCE` CHAR(2) DEFAULT NULL,
  `POSTAL_CODE` CHAR(7) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_address`
--

INSERT INTO `USERS_ADDRESS` (
  `STUDENT_ID`,
  `STREET_NUMBER`,
  `STREET_NAME`,
  `CITY`,
  `PROVENCE`,
  `POSTAL_CODE`
) VALUES (
  100111,
  0,
  'NULL',
  'NULL',
  'NU',
  'NULL'
),
(
  100112,
  0,
  'NULL',
  'NULL',
  'NU',
  'NULL'
),
(
  100113,
  373,
  'Brettonwood Ridge',
  'Ottawa',
  'On',
  'K2T0H8'
),
(
  100114,
  315,
  'Oxer ',
  'Ottawa',
  'On',
  'K2T0H8'
);

-- --------------------------------------------------------

--
-- Table structure for table `users_avatar`
--

CREATE TABLE `USERS_AVATAR` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL,
  `AVATAR` INT(1) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_avatar`
--

INSERT INTO `USERS_AVATAR` (
  `STUDENT_ID`,
  `AVATAR`
) VALUES (
  100111,
  '0'
),
(
  100112,
  '0'
),
(
  100113,
  '1'
),
(
  100114,
  '1'
);

-- --------------------------------------------------------

--
-- Table structure for table `users_info`
--

CREATE TABLE `USERS_INFO` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL,
  `STUDENT_EMAIL` VARCHAR(150) DEFAULT NULL,
  `FIRST_NAME` VARCHAR(150) DEFAULT NULL,
  `LAST_NAME` VARCHAR(150) DEFAULT NULL,
  `DOB` DATE DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_info`
--

INSERT INTO `USERS_INFO` (
  `STUDENT_ID`,
  `STUDENT_EMAIL`,
  `FIRST_NAME`,
  `LAST_NAME`,
  `DOB`
) VALUES (
  100100,
  '',
  '',
  '',
  '0000-00-00'
),
(
  100101,
  '',
  '',
  '',
  '0000-00-00'
),
(
  100102,
  'kareemassad5@gmail.com',
  'ka',
  'js',
  '2222-02-22'
),
(
  100103,
  'kareemassad5@gmail.com',
  'ka',
  'js',
  '2222-02-22'
),
(
  100104,
  'kareemassad5@gmail.com',
  'ka',
  'js',
  '2222-02-22'
),
(
  100105,
  '',
  '',
  '',
  '0000-00-00'
),
(
  100106,
  'small_benis@gmail.com',
  'Andrej',
  'Zoranovic',
  '2002-08-27'
),
(
  100107,
  'small_benis@gmail.com',
  'Andrej',
  'Zoranovic',
  '2002-08-27'
),
(
  100108,
  'small_benis@gmail.com',
  'Andrej',
  'Zoranovic',
  '2002-08-27'
),
(
  100109,
  'small_benis@gmail.com',
  'Andrej',
  'Zoranovic',
  '2002-08-27'
),
(
  100110,
  'kareemsinchin@gmail.com',
  'Kareemson',
  'Chin',
  '2002-01-08'
),
(
  100111,
  'kareemsinchin@gmail.com',
  'Kareemson',
  'Chin',
  '2002-01-08'
),
(
  100112,
  'karerermerem@gmail.com',
  'Karreeeem',
  'sinninin',
  '2020-02-20'
),
(
  100113,
  'kareemassad5@gmail.com',
  'Kareem',
  'El Assad',
  '2000-07-15'
),
(
  100114,
  'kareemassad5@gmail.com',
  'Ahmed',
  'Allam',
  '2000-08-12'
);

-- --------------------------------------------------------

--
-- Table structure for table `users_posts`
--

CREATE TABLE `USERS_POSTS` (
  `POST_ID` INT(10) UNSIGNED NOT NULL,
  `STUDENT_ID` INT(10) UNSIGNED DEFAULT NULL,
  `NEW_POST` TEXT DEFAULT NULL,
  `POST_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_posts`
--

INSERT INTO `USERS_POSTS` (
  `POST_ID`,
  `STUDENT_ID`,
  `NEW_POST`,
  `POST_DATE`
) VALUES (
  4,
  NULL,
  'west virgina',
  '2023-03-24 04:00:00'
),
(
  5,
  NULL,
  'blue ridge mountain',
  '2023-03-24 04:00:00'
);

-- --------------------------------------------------------

--
-- Table structure for table `users_program`
--

CREATE TABLE `USERS_PROGRAM` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL,
  `PROGRAM` VARCHAR(50) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_program`
--

INSERT INTO `USERS_PROGRAM` (
  `STUDENT_ID`,
  `PROGRAM`
) VALUES (
  100111,
  'COMPENG'
),
(
  100112,
  'Comms'
),
(
  100113,
  'Software'
),
(
  100114,
  'Software'
);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users_address`
--
ALTER TABLE `USERS_ADDRESS` ADD PRIMARY KEY (`STUDENT_ID`);

--
-- Indexes for table `users_avatar`
--
ALTER TABLE `USERS_AVATAR` ADD PRIMARY KEY (`STUDENT_ID`);

--
-- Indexes for table `users_info`
--
ALTER TABLE `USERS_INFO` ADD PRIMARY KEY (`STUDENT_ID`);

--
-- Indexes for table `users_posts`
--
ALTER TABLE `USERS_POSTS` ADD PRIMARY KEY (`POST_ID`), ADD KEY `STUDENT_ID` (`STUDENT_ID`);

--
-- Indexes for table `users_program`
--
ALTER TABLE `USERS_PROGRAM` ADD PRIMARY KEY (`STUDENT_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users_info`
--
ALTER TABLE `USERS_INFO` MODIFY `STUDENT_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100115;

--
-- AUTO_INCREMENT for table `users_posts`
--
ALTER TABLE `USERS_POSTS` MODIFY `POST_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_address`
--
ALTER TABLE `USERS_ADDRESS` ADD CONSTRAINT `USERS_ADDRESS_IBFK_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `USERS_INFO` (`STUDENT_ID`);

--
-- Constraints for table `users_avatar`
--
ALTER TABLE `USERS_AVATAR` ADD CONSTRAINT `USERS_AVATAR_IBFK_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `USERS_INFO` (`STUDENT_ID`);

--
-- Constraints for table `users_posts`
--
ALTER TABLE `USERS_POSTS` ADD CONSTRAINT `USERS_POSTS_IBFK_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `USERS_INFO` (`STUDENT_ID`);

--
-- Constraints for table `users_program`
--
ALTER TABLE `USERS_PROGRAM` ADD CONSTRAINT `USERS_PROGRAM_IBFK_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `USERS_INFO` (`STUDENT_ID`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;