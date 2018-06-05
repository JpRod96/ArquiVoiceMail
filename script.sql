CREATE TABLE `Mailbox` (
  `id` INTEGER NOT NULL PRIMARY KEY,
  `passcode` TEXT,
  `greeting` TEXT
);

--
-- Dumping data for table `Mailbox`
--

INSERT INTO `Mailbox` (`id`, `passcode`, `greeting`) VALUES
(1, '1', 'You have reached mailbox 1. \Please leave a message now.'),
(2, '2', 'You have reached mailbox 2. \Please leave a message now.'),
(3, '3', 'You have reached mailbox 3. \Please leave a message now.'),
(4, '4', 'You have reached mailbox 4. \Please leave a message now.'),
(5, '5', 'You have reached mailbox 5. \Please leave a message now.'),
(6, '6', 'You have reached mailbox 6. \Please leave a message now.'),
(7, '7', 'You have reached mailbox 7. \Please leave a message now.'),
(8, '8', 'You have reached mailbox 8. \Please leave a message now.'),
(9, '9', 'You have reached mailbox 9. \Please leave a message now.'),
(10, '10', 'You have reached mailbox 10. \Please leave a message now.'),
(11, '11', 'You have reached mailbox 11. \Please leave a message now.'),
(12, '12', 'You have reached mailbox 12. \Please leave a message now.'),
(13, '13', 'You have reached mailbox 13. \Please leave a message now.'),
(14, '14', 'You have reached mailbox 14. \Please leave a message now.'),
(15, '15', 'You have reached mailbox 15. \Please leave a message now.'),
(16, '16', 'You have reached mailbox 16. \Please leave a message now.'),
(17, '17', 'You have reached mailbox 17. \Please leave a message now.'),
(18, '18', 'You have reached mailbox 18. \Please leave a message now.'),
(19, '19', 'You have reached mailbox 19. \Please leave a message now.'),
(20, '20', 'You have reached mailbox 20. \Please leave a message now.');

-- --------------------------------------------------------

--
-- Table structure for table `Message`
--

CREATE TABLE `Message` (
  `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `text` TEXT NOT NULL,
  `MailBoxId` INTEGER NOT NULL,
  FOREIGN KEY(`MailBoxId`) REFERENCES `Mailbox`(`id`)
);

