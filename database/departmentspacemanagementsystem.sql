-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2023 at 04:13 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `departmentspacemanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`) VALUES
(3),
(4);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `end_date_time` datetime(6) DEFAULT NULL,
  `reservation_date_time` datetime(6) DEFAULT NULL,
  `reserved_by_id` bigint(20) NOT NULL,
  `responsible_person_id` bigint(20) NOT NULL,
  `spaceid` int(11) NOT NULL,
  `start_date_time` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation_seq`
--

CREATE TABLE `reservation_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation_seq`
--

INSERT INTO `reservation_seq` (`next_val`) VALUES
(351);

-- --------------------------------------------------------

--
-- Table structure for table `responsible_person`
--

CREATE TABLE `responsible_person` (
  `type` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `responsible_person`
--

INSERT INTO `responsible_person` (`type`, `id`) VALUES
('Instructor', 5),
('Dr.', 6),
('Dr.', 7),
('Mrs.', 8),
('Dr.', 9);

-- --------------------------------------------------------

--
-- Table structure for table `spaces`
--

CREATE TABLE `spaces` (
  `id` bigint(20) NOT NULL,
  `capacity` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `facilities` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `spaces`
--

INSERT INTO `spaces` (`id`, `capacity`, `description`, `facilities`, `image`, `name`) VALUES
(1, 60, 'Fourth Floor Lab', 'Projector,Computers,AC', 'IMAGE', 'Computer Lab 01'),
(2, 45, 'First Floor Lab', 'Projector,Computers,AC', 'IMAGE', 'Computer Lab 02'),
(3, 20, 'Third Floor Lab', 'Computers,Projector', 'IMAGE', 'Networking Lab'),
(4, 30, '2nd floor', 'Projector,AC', 'IMAGE', 'Discussion Room'),
(5, 15, 'Ground Floor Room', 'AC,Smart Board,', 'IMAGE', 'Conference Room');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` tinyint(4) DEFAULT NULL,
  `user` varbinary(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`) VALUES
(1, 'e19129@eng.pdn.ac.lk', 'Kanishka', 'Gunawardana'),
(2, 'e19408@eng.pdn.ac.lk', 'Sanduni', 'Ubayasiri'),
(3, 'e19372@eng.pdn.ac.lk', 'Kaushitha', 'Silva'),
(4, 'e19074@eng.pdn.ac.lk', 'Mansitha', 'Eshwara'),
(5, 'e19009@eng.pdn.ac.lk', 'Chandula', 'Adhikari'),
(6, 'kaushithamsilva@gmail.com', 'Roshan', 'Ragel'),
(7, 'kaushithamsilva@gmail.com', 'Isuru', 'Nawinna'),
(8, 'kaushithamsilva@gmail.com', 'Nadeesha', 'Adikari'),
(9, 'kaushithamsilva@gmail.com', 'Mahanama', 'Wickramasinghe');

-- --------------------------------------------------------

--
-- Table structure for table `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Table structure for table `waiting`
--

CREATE TABLE `waiting` (
  `id` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `end_date_time` datetime(6) DEFAULT NULL,
  `reservation_date_time` datetime(6) DEFAULT NULL,
  `reserved_by_id` bigint(20) NOT NULL,
  `responsible_person_id` bigint(20) NOT NULL,
  `spaceid` int(11) NOT NULL,
  `start_date_time` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `available` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `waiting_seq`
--

CREATE TABLE `waiting_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `waiting_seq`
--

INSERT INTO `waiting_seq` (`next_val`) VALUES
(151);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `responsible_person`
--
ALTER TABLE `responsible_person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `spaces`
--
ALTER TABLE `spaces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `waiting`
--
ALTER TABLE `waiting`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `spaces`
--
ALTER TABLE `spaces`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK1ja8rua032fgnk9jmq7du3b3a` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `responsible_person`
--
ALTER TABLE `responsible_person`
  ADD CONSTRAINT `FKqgxfcov9w7ygi6v1okuyd5uns` FOREIGN KEY (`id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
