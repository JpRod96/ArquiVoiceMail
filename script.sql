-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 08, 2018 at 12:30 AM
-- Server version: 5.6.37
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `VoiceMailDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Mailbox`
--

CREATE TABLE IF NOT EXISTS `Mailbox` (
  `id` int(2) NOT NULL,
  `passcode` varchar(20) COLLATE utf8_bin NOT NULL,
  `greeting` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `Mailbox`
--

INSERT INTO `Mailbox` (`id`, `passcode`, `greeting`) VALUES
(1, '2', 'Passcode es 2'),
(2, '2', 'You have reached mailbox 2. \nPlease leave a message now.'),
(3, '3', 'You have reached mailbox 3. \nPlease leave a message now.'),
(4, '4', 'You have reached mailbox 4. \nPlease leave a message now.'),
(5, '5', 'You have reached mailbox 5. \nPlease leave a message now.'),
(6, '6', 'You have reached mailbox 6. \nPlease leave a message now.'),
(7, '7', 'You have reached mailbox 7. \nPlease leave a message now.'),
(8, '8', 'You have reached mailbox 8. \nPlease leave a message now.'),
(9, '9', 'You have reached mailbox 9. \nPlease leave a message now.'),
(10, '10', 'You have reached mailbox 10. \nPlease leave a message now.'),
(11, '11', 'You have reached mailbox 11. \nPlease leave a message now.'),
(12, '12', 'You have reached mailbox 12. \nPlease leave a message now.'),
(13, '13', 'You have reached mailbox 13. \nPlease leave a message now.'),
(14, '14', 'You have reached mailbox 14. \nPlease leave a message now.'),
(15, '15', 'You have reached mailbox 15. \nPlease leave a message now.'),
(16, '16', 'You have reached mailbox 16. \nPlease leave a message now.'),
(17, '17', 'You have reached mailbox 17. \nPlease leave a message now.'),
(18, '18', 'You have reached mailbox 18. \nPlease leave a message now.'),
(19, '19', 'You have reached mailbox 19. \nPlease leave a message now.'),
(20, '20', 'You have reached mailbox 20. \nPlease leave a message now.');

-- --------------------------------------------------------

--
-- Table structure for table `Message`
--

CREATE TABLE IF NOT EXISTS `Message` (
  `Id` int(2) NOT NULL,
  `text` varchar(500) COLLATE utf8_bin NOT NULL,
  `MailBoxId` int(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Mailbox`
--
ALTER TABLE `Mailbox`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Message`
--
ALTER TABLE `Message`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `MessageQueueId` (`MailBoxId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Mailbox`
--
ALTER TABLE `Mailbox`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `Message`
--
ALTER TABLE `Message`
  MODIFY `Id` int(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Message`
--
ALTER TABLE `Message`
  ADD CONSTRAINT `Mailbox_Message_foreign` FOREIGN KEY (`MailBoxId`) REFERENCES `Mailbox` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
