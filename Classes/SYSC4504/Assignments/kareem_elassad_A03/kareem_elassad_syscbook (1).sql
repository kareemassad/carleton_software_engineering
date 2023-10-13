-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2023 at 05:43 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

CREATE DATABASE IF NOT EXISTS KAREEM_ELASSAD_SYSCBOOK;

USE KAREEM_ELASSAD_SYSCBOOK;

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
  100100,
  373,
  'champapgne',
  'toronto',
  'On',
  'ven12'
),
(
  100101,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100102,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100103,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100104,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100105,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100106,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100109,
  0,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100110,
  0,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100111,
  1,
  '11',
  '11',
  'On',
  '111111'
),
(
  100112,
  0,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100113,
  0,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100114,
  0,
  NULL,
  NULL,
  NULL,
  NULL
),
(
  100115,
  1,
  '111',
  '1',
  '1',
  '1'
),
(
  100116,
  0,
  NULL,
  NULL,
  NULL,
  NULL
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
  100100,
  3
),
(
  100101,
  NULL
),
(
  100102,
  NULL
),
(
  100103,
  NULL
),
(
  100104,
  NULL
),
(
  100105,
  NULL
),
(
  100106,
  NULL
),
(
  100109,
  0
),
(
  100110,
  0
),
(
  100111,
  5
),
(
  100112,
  0
),
(
  100113,
  0
),
(
  100114,
  0
),
(
  100115,
  2
),
(
  100116,
  0
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
  'baba@gmail.com',
  'baba',
  'kareem',
  '0001-01-01'
),
(
  100101,
  'kareemassad5@gmail.com',
  'Kareem',
  'El Assad',
  '2000-07-15'
),
(
  100102,
  'kareemassad5@gmail.com',
  'Kareem',
  'El Assad',
  '2000-07-15'
),
(
  100103,
  'kareemassad5@gmail.com',
  'Kareem',
  'El Assad',
  '2000-07-15'
),
(
  100104,
  'kareemsin@gmail.com',
  'Kareemsin',
  'Chin',
  '2999-09-09'
),
(
  100105,
  'baba@gmail.com',
  'baba',
  'kareem',
  '0001-01-01'
),
(
  100106,
  '1@1.com',
  'Kareemsin',
  'Chin',
  '0020-01-28'
),
(
  100107,
  'clutch@man.ca',
  'clutch',
  'man',
  '0001-01-01'
),
(
  100108,
  '2@2.ca',
  'super',
  'man',
  '0002-02-02'
),
(
  100109,
  '3@3.ca',
  '3',
  '3',
  '0003-03-03'
),
(
  100110,
  '3@3.ca',
  '3',
  '3',
  '0003-03-03'
),
(
  100111,
  '1@1.ca',
  '1',
  '1',
  '0001-01-01'
),
(
  100112,
  '1@1.ca',
  '1',
  '1',
  '0001-01-01'
),
(
  100113,
  '9@9.ca',
  '9',
  '9',
  '0009-09-09'
),
(
  100114,
  '1@cmail.ca',
  '1',
  '1',
  '0011-01-01'
),
(
  100115,
  '1@1.ca',
  '1111',
  '1',
  '0001-01-01'
),
(
  100116,
  '1@1.ca',
  '1',
  '1',
  '0001-01-01'
),
(
  100117,
  'u@u.ca',
  'Kareem',
  'ElA',
  '0001-01-01'
);

-- --------------------------------------------------------

--
-- Table structure for table `users_passwords`
--

CREATE TABLE `USERS_PASSWORDS` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL,
  `PASSWORD_HASH` VARCHAR(255) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_passwords`
--

INSERT INTO `USERS_PASSWORDS` (
  `STUDENT_ID`,
  `PASSWORD_HASH`
) VALUES (
  100100,
  '$2y$10$n0jqLZXBprY0AWGeuSibWufdzbqC1VSLOu93pSWYTfIwrszYexm6e'
),
(
  100101,
  '$2y$10$omhvnd9CAL73hguNF2tFneCj9y/SGXfV99Z3Mnm.saxkcEOhq/yFS'
),
(
  100102,
  '$2y$10$jFSlmSTbbIC75.zz0meUke56DnMtjqGNULSqds1f2AWnJzznGza/u'
),
(
  100103,
  '$2y$10$Spz3IIbr4wSPkWTzIL5DD.U2jzdemr1WkkoFeqzi.KMFoVC.w5ohW'
),
(
  100104,
  '$2y$10$m8hlEHFpOc7t10Wkj82vV.QRV7Eeei9k7KUZEgln8w9tRS3x1zuce'
),
(
  100105,
  '$2y$10$L4N.uYnjXxU6TLT/SXK9L.UDKRPnlCvIdCdOUO4Z6/XGKKSpVfL0y'
),
(
  100106,
  '$2y$10$kj1hzlsIx16ETQVbcFDRu.AKooBBkFOhIisLC64UgR51CtiClFarG'
),
(
  100109,
  '$2y$10$bz0nCnUAat1KWipcmrG1YOJy7vbZDuI2VhGIAoAuJsbmtSUcbzlNK'
),
(
  100110,
  '$2y$10$SO.Ok5gqUntG34n591fUUe7kWRUVX4VkfFL7dUhOcY59jePQG2cj2'
),
(
  100111,
  '$2y$10$yyJM8nqD4jinwqm4rS3Kl.BVcIbTxHvJJ8A6im4BjwO2tZHwLTZ1K'
),
(
  100112,
  '$2y$10$WxrAGjHTatyRwuGM5LxMiufVrPtTkoTQyJiSpwFrBKvhBtlFjYdHW'
),
(
  100113,
  '$2y$10$8QM/Y331g7Jh.b/Ar8TtX.Of/Xp/pFRv5f4qOKvwtz3DrsjAI2aAa'
),
(
  100114,
  '$2y$10$kVJENksHSAA2j8NYlG4ipOxPuP53hczQL3DtdHyMrlynEsufYPh9W'
),
(
  100115,
  '$2y$10$pqLLOFrbqVowEYrgZ79uT.JoNI4KqguKCk302ljvBZWAdWL3CX45K'
),
(
  100116,
  '$2y$10$oDfP83tU9Rnx39g/x89Tf.3tCYe3tcS4w/ZxFPEDM0K2sOPvy2mAi'
);

-- --------------------------------------------------------

--
-- Table structure for table `users_permissions`
--

CREATE TABLE `USERS_PERMISSIONS` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL,
  `ACCOUNT_TYPE` INT(1) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

--
-- Dumping data for table `users_permissions`
--

INSERT INTO `USERS_PERMISSIONS` (
  `STUDENT_ID`,
  `ACCOUNT_TYPE`
) VALUES (
  100100,
  1
),
(
  100101,
  1
),
(
  100102,
  1
),
(
  100103,
  1
),
(
  100104,
  1
),
(
  100105,
  1
),
(
  100106,
  1
),
(
  100109,
  1
),
(
  100110,
  1
),
(
  100111,
  1
),
(
  100112,
  1
),
(
  100113,
  1
),
(
  100114,
  1
),
(
  100115,
  1
),
(
  100116,
  1
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
  1,
  100100,
  'post1',
  '2023-04-13 04:00:00'
),
(
  2,
  100100,
  'post 2',
  '2023-04-13 04:00:00'
),
(
  3,
  100100,
  'post 3',
  '2023-04-13 04:00:00'
),
(
  4,
  100100,
  'post 4',
  '2023-04-13 04:00:00'
),
(
  5,
  100100,
  'post5',
  '2023-04-13 04:00:00'
),
(
  6,
  100100,
  'post6',
  '2023-04-13 04:00:00'
),
(
  7,
  100100,
  'post 10',
  '2023-04-13 04:00:00'
),
(
  8,
  100100,
  'hello',
  '2023-04-13 04:00:00'
),
(
  9,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  10,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  11,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  12,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  13,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  14,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  15,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  16,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  17,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  18,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  19,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  20,
  100115,
  '1',
  '2023-04-13 04:00:00'
),
(
  21,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  22,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  23,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  24,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  25,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  26,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  27,
  100117,
  'gwgw',
  '2023-04-13 04:00:00'
),
(
  28,
  100117,
  '1',
  '2023-04-13 04:00:00'
),
(
  29,
  100117,
  '12weq',
  '2023-04-13 04:00:00'
),
(
  30,
  100117,
  '1234124',
  '2023-04-13 04:00:00'
),
(
  31,
  100117,
  '12312',
  '2023-04-13 04:00:00'
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
  100100,
  'Communications Engineering'
),
(
  100101,
  'Software'
),
(
  100102,
  'Software'
),
(
  100103,
  'COMPENG'
),
(
  100104,
  'COMPENG'
),
(
  100105,
  'COMPENG'
),
(
  100106,
  'COMPENG'
),
(
  100109,
  'BioElec'
),
(
  100110,
  'BioElec'
),
(
  100111,
  'BLANK'
),
(
  100112,
  'Software'
),
(
  100113,
  'Software'
),
(
  100114,
  'Comms'
),
(
  100115,
  'Software Engineering'
),
(
  100116,
  'Special'
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
-- Indexes for table `users_passwords`
--
ALTER TABLE `USERS_PASSWORDS` ADD PRIMARY KEY (`STUDENT_ID`);

--
-- Indexes for table `users_permissions`
--
ALTER TABLE `USERS_PERMISSIONS` ADD PRIMARY KEY (`STUDENT_ID`);

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
ALTER TABLE `USERS_INFO` MODIFY `STUDENT_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100118;

--
-- AUTO_INCREMENT for table `users_posts`
--
ALTER TABLE `USERS_POSTS` MODIFY `POST_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

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
-- Constraints for table `users_passwords`
--
ALTER TABLE `USERS_PASSWORDS` ADD CONSTRAINT `USERS_PASSWORDS_IBFK_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `USERS_INFO` (`STUDENT_ID`);

--
-- Constraints for table `users_permissions`
--
ALTER TABLE `USERS_PERMISSIONS` ADD CONSTRAINT `USERS_PERMISSIONS_IBFK_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `USERS_INFO` (`STUDENT_ID`);

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