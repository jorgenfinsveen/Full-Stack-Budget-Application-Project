-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: s681.loopia.se
-- Generation Time: 19. Apr, 2023 14:38 PM
-- Tjener-versjon: 10.6.12-MariaDB-log
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finsveen_dev_db_2`
--

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `budget`
--

CREATE TABLE `budget` (
  `bid` bigint(20) NOT NULL,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `boundary` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dataark for tabell `budget`
--

INSERT INTO `budget` (`bid`, `end_date`, `start_date`, `boundary`) VALUES
(2, '2023-04-18', '2023-04-18', 10000),
(52, '2023-04-18', '2023-04-18', 10000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `budget`
--
ALTER TABLE `budget`
  ADD PRIMARY KEY (`bid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
