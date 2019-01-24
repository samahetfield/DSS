-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-01-2019 a las 10:28:11
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `consorcio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `farmacia`
--

CREATE TABLE `farmacia` (
  `ID` int(5) NOT NULL,
  `NOMBRE` varchar(30) NOT NULL,
  `LATITUD` float NOT NULL,
  `LONGITUD` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `farmacia`
--

INSERT INTO `farmacia` (`ID`, `NOMBRE`, `LATITUD`, `LONGITUD`) VALUES
(16, 'Farmacia Juani', 37.422, -122.084),
(17, 'Farmacia Amphiteatre', 37.423, -122.085),
(18, 'Farnacia Charleston', 37.421, -122.083),
(19, 'Farmacia del Parque', 37.422, -122.082),
(20, 'Farmacia Paco', 37.421, -122.084);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `orders` (
  `ID` int(5) NOT NULL,
  `PRODUCTO` int(5) NOT NULL,
  `USUARIO` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`ID`, `PRODUCTO`, `USUARIO`) VALUES
(14, 20, 'adritake'),
(15, 21, 'adritake'),
(16, 20, 'samaLastHope'),
(17, 27, 'samaLastHope'),
(18, 28, 'freesoftwareftw'),
(19, 24, 'segisking'),
(20, 25, 'segisking'),
(21, 26, 'segisking');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID` int(5) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`ID`, `NOMBRE`) VALUES
(20, 'Paracetamol'),
(21, 'Eferalgan'),
(22, 'Vaporub'),
(23, 'Aspirina'),
(24, 'Filbit'),
(25, 'Ibuprofeno'),
(26, 'Frenadol'),
(27, 'Vendas'),
(28, 'Tiritas'),
(29, 'Antibiótico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `USERNAME` varchar(20) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `CORREO` varchar(30) NOT NULL,
  `CONTRASENA` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`USERNAME`, `NOMBRE`, `CORREO`, `CONTRASENA`) VALUES
('adritake', 'Adrian de la Torre Rodriguez', 'atr96@correo.ugr.es', 'contrasenaAdri'),
('DimitriKnows', 'Dimitri Johnson', 'dimitri@hotmail.ru', 'contrasenaDimitri'),
('DonFarmacio', 'Emeterio Santos Sanchez', 'emeterio@farmacia.es', 'contrasenaEmeterio'),
('freesoftwareftw', 'Julian Juan Cubero Cuervos', 'jj@linux.com', 'contrasenajj'),
('samaLastHope', 'Sergio Samaniego Martinez', 'sama@correo.ugr.es', 'contrasenaSergio'),
('segisking', 'Segismundo Roldan de Borbón', 'segi@yahoo.com', 'contrasenaSegis');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `farmacia`
--
ALTER TABLE `farmacia`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`USERNAME`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `farmacia`
--
ALTER TABLE `farmacia`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `orders`
--
ALTER TABLE `orders`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
