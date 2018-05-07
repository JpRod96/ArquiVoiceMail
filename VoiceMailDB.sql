-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 05-05-2018 a las 18:10:30
-- Versión del servidor: 5.6.37
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `VoiceMailDB`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Mailbox`
--

CREATE TABLE IF NOT EXISTS `Mailbox` (
  `id` int(2) NOT NULL,
  `passcode` varchar(20) COLLATE utf8_bin NOT NULL,
  `greeting` varchar(100) COLLATE utf8_bin NOT NULL,
  `keptMessageQueueId` int(2) NOT NULL,
  `newMessageQueueId` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Message`
--

CREATE TABLE IF NOT EXISTS `Message` (
  `Id` int(2) NOT NULL,
  `text` varchar(500) COLLATE utf8_bin NOT NULL,
  `MessageQueueId` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MessageQueue`
--

CREATE TABLE IF NOT EXISTS `MessageQueue` (
  `Id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Mailbox`
--
ALTER TABLE `Mailbox`
  ADD PRIMARY KEY (`id`),
  ADD KEY `keptMessageQueueId` (`keptMessageQueueId`),
  ADD KEY `newMessageQueue` (`newMessageQueueId`),
  ADD KEY `newMessageQueueId` (`newMessageQueueId`);

--
-- Indices de la tabla `Message`
--
ALTER TABLE `Message`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `MessageQueueId` (`MessageQueueId`);

--
-- Indices de la tabla `MessageQueue`
--
ALTER TABLE `MessageQueue`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Mailbox`
--
ALTER TABLE `Mailbox`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Message`
--
ALTER TABLE `Message`
  MODIFY `Id` int(2) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `MessageQueue`
--
ALTER TABLE `MessageQueue`
  MODIFY `Id` int(2) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Mailbox`
--
ALTER TABLE `Mailbox`
  ADD CONSTRAINT `keptQueueForeign` FOREIGN KEY (`keptMessageQueueId`) REFERENCES `MessageQueue` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `newQueueForeign` FOREIGN KEY (`newMessageQueueId`) REFERENCES `MessageQueue` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Message`
--
ALTER TABLE `Message`
  ADD CONSTRAINT `MessageQueue_Message_Foreign` FOREIGN KEY (`MessageQueueId`) REFERENCES `MessageQueue` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
