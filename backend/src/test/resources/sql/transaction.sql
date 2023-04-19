-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: s681.loopia.se
-- Generation Time: 19. Apr, 2023 14:31 PM
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
-- Tabellstruktur for tabell `transaction`
--

CREATE TABLE `transaction` (
  `tid` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(255) NOT NULL,
  `tname` varchar(255) NOT NULL,
  `value` int(11) NOT NULL,
  `budget_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dataark for tabell `transaction`
--

INSERT INTO `transaction` (`tid`, `date`, `description`, `tname`, `value`, `budget_id`) VALUES
(5, '2023-04-18', 'Transportation', 'Bus ride', -150, 2),
(6, '2023-05-12', 'Payday', 'Salary income', 350, 2),
(15, '2023-06-08', 'Company dinner', 'Food', -200, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `FK7ul8m5q12we515aa7b7ao0p44` (`budget_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `tid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Begrensninger for dumpede tabeller
--

--
-- Begrensninger for tabell `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK7ul8m5q12we515aa7b7ao0p44` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`bid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
