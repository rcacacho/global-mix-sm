-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-03-2024 a las 21:21:32
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `global_mix_sm`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asesor`
--

CREATE TABLE `asesor` (
  `idasesor` int(11) NOT NULL,
  `nombres` varchar(250) NOT NULL,
  `apellidos` varchar(250) NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asesor`
--

INSERT INTO `asesor` (`idasesor`, `nombres`, `apellidos`, `usuariocreacion`, `fechacreacion`, `usuariomodificacion`, `fechamodificacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 'Jorge Anibal', 'perezs', 'admin', '2024-01-26 14:24:12', 'admin', '2024-02-23 06:07:51', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion`
--

CREATE TABLE `camion` (
  `idcamion` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `encargado` varchar(200) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `camion`
--

INSERT INTO `camion` (`idcamion`, `numero`, `encargado`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuariomodificacion`, `fechamodificacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 1, 'Jose Perez', 'a', 'admin', '2024-01-29 07:27:33', 'admin', '2024-02-23 06:14:55', NULL, NULL, 1),
(2, 2, 'aaaa', 'a', 'admin', '2024-01-29 07:27:45', 'admin', '2024-02-09 20:28:42', 'admin', '2024-02-23 06:14:47', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL,
  `nombres` varchar(500) NOT NULL,
  `apellidos` varchar(500) NOT NULL,
  `nit` varchar(50) DEFAULT NULL,
  `direccion` varchar(1000) DEFAULT NULL,
  `correoelectronico` varchar(500) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nombres`, `apellidos`, `nit`, `direccion`, `correoelectronico`, `telefono`, `usuariocreacion`, `fechacreacion`, `usuariomodificacion`, `fechamodificacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 'Otto a', 'Castillo', '40494555', 'ciudad', 'no tiene', 'no tienes', 'admin', '2024-01-24 14:49:39', 'admin', '2024-01-29 10:16:39', NULL, NULL, 1),
(2, 'Rolando ', 'miranda', '556ds5', 'no tiene', 'a@gmail.com', '123222', 'admin', '2024-01-24 14:51:12', NULL, NULL, NULL, NULL, 1),
(3, 'Adolfo', 'Lopez', '123132', '33', 'a@gmail.com', '3233331', 'admin', '2024-01-24 14:52:59', NULL, NULL, NULL, NULL, 1),
(4, 'Juan', 'Perez', 'no tiene', 'no tiene', 'no tiene', 'no tiene', 'admin', '2024-01-25 10:21:33', NULL, NULL, 'admin', '2024-02-23 06:07:24', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `confirmacionpago`
--

CREATE TABLE `confirmacionpago` (
  `idconfirmacionpago` int(11) NOT NULL,
  `idpedido` int(11) NOT NULL,
  `nombreconfirmo` varchar(250) NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `despachos`
--

CREATE TABLE `despachos` (
  `iddespacho` int(11) NOT NULL,
  `idpedido` int(11) NOT NULL,
  `idestadodespacho` int(11) DEFAULT NULL,
  `metroscubicossolicitados` double NOT NULL,
  `metroscubicosvendidos` double DEFAULT NULL,
  `costototalcemento` double DEFAULT NULL,
  `costototaldinerocemento` double DEFAULT NULL,
  `costototalarena` double DEFAULT NULL,
  `costototalarenadinero` double DEFAULT NULL,
  `costototalpiedrin` double DEFAULT NULL,
  `costototalpiedrindinero` double DEFAULT NULL,
  `costototaladitivo` double DEFAULT NULL,
  `costototaladitivodinero` double DEFAULT NULL,
  `costotalagua` double DEFAULT NULL,
  `costototalaguadinero` double DEFAULT NULL,
  `costototaldiesel` double DEFAULT NULL,
  `costototaldieseldinero` double DEFAULT NULL,
  `costototalotro` double DEFAULT NULL,
  `costototalotrodinero` double DEFAULT NULL,
  `costototalbombeo` double DEFAULT NULL,
  `costototalcolocado` double DEFAULT NULL,
  `segundopago` double DEFAULT NULL,
  `idasesor` int(11) DEFAULT NULL,
  `comision` double DEFAULT NULL,
  `beneficioprimo` double DEFAULT NULL,
  `beneficiometrocubico` double DEFAULT NULL,
  `pagototal` double DEFAULT NULL,
  `pagototalsiniva` double DEFAULT NULL,
  `precioventametrocubico` double DEFAULT NULL,
  `precioventametrocubicosiniva` double DEFAULT NULL,
  `costoobra` double DEFAULT NULL,
  `costometrocubicovendido` double DEFAULT NULL,
  `totalmateriaprima` double DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioelimiancion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `despachos`
--

INSERT INTO `despachos` (`iddespacho`, `idpedido`, `idestadodespacho`, `metroscubicossolicitados`, `metroscubicosvendidos`, `costototalcemento`, `costototaldinerocemento`, `costototalarena`, `costototalarenadinero`, `costototalpiedrin`, `costototalpiedrindinero`, `costototaladitivo`, `costototaladitivodinero`, `costotalagua`, `costototalaguadinero`, `costototaldiesel`, `costototaldieseldinero`, `costototalotro`, `costototalotrodinero`, `costototalbombeo`, `costototalcolocado`, `segundopago`, `idasesor`, `comision`, `beneficioprimo`, `beneficiometrocubico`, `pagototal`, `pagototalsiniva`, `precioventametrocubico`, `precioventametrocubicosiniva`, `costoobra`, `costometrocubicovendido`, `totalmateriaprima`, `usuariocreacion`, `fechacreacion`, `usuariomodificacion`, `fechamodificacion`, `usuarioelimiancion`, `fechaeliminacion`, `activo`) VALUES
(1, 2, 2, 0, 50, 4310, 8167.103660714285, 12785, 973.0394990366086, 14200, 0, 21.6, 308.7417969821725, 2810, 231.9761928034215, 1000, 22350, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-02-16 15:56:21', 'admin', '2024-02-27 14:09:49', NULL, NULL, 1),
(2, 3, 2, 53, 50, 4310, 8167.103660714285, 12785, 973.0394990366086, 14200, 1688.7539184952977, 21.6, 601.6898716764781, 2810, 231.9761928034215, 1000, 22350, NULL, NULL, NULL, NULL, 5800, NULL, NULL, 44935, 748.9246059385084, 62300, 55624.99999999999, 1038.3333333333333, 927.0833333333331, 10689.523643689483, 178.15872739482472, 10689.523643689483, 'admin', '2024-02-16 08:01:38', 'admin', '2024-02-27 14:09:47', NULL, NULL, 1),
(3, 1, 1, 50, 50, 3080, 5836.352499999999, 1615, 122.91425818882463, 1615, 192.06602664576803, 565.9, 15763.717517672174, 2115, 174.60129814207704, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-02-27 12:54:31', NULL, NULL, NULL, NULL, 1),
(4, 4, 1, 50, NULL, 4147, 7858.23175892857, 2501, 190.3458574181117, 2838, 337.5129310344828, 247.9, 3362.1216535517756, 3796, 313.37424479778934, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-02-29 06:49:28', NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallematerial`
--

CREATE TABLE `detallematerial` (
  `iddetallematerial` int(11) NOT NULL,
  `idmaterial` int(11) NOT NULL,
  `existencia_actual` double NOT NULL,
  `ingreso` double DEFAULT NULL,
  `egreso` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detallematerial`
--

INSERT INTO `detallematerial` (`iddetallematerial`, `idmaterial`, `existencia_actual`, `ingreso`, `egreso`, `total`, `fechacreacion`, `usuariocreacion`, `fechaeliminacion`, `usuarioeliminacion`, `activo`) VALUES
(1, 5, 5, 5, NULL, NULL, '2024-01-24 13:56:42', 'admin', NULL, NULL, 1),
(2, 6, 10, 10, NULL, 541.0714285714286, '2024-01-31 15:41:21', 'admin', NULL, NULL, 1),
(3, 7, 10, 10, NULL, 513.3928571428571, '2024-01-31 15:43:36', 'admin', NULL, NULL, 1),
(4, 8, 10, 10, NULL, 949.0178571428571, '2024-01-31 15:56:06', 'admin', NULL, NULL, 1),
(5, 9, 100, 100, NULL, 15175, '2024-01-31 15:58:48', 'admin', NULL, NULL, 1),
(6, 10, 100, 100, NULL, 15175, '2024-01-31 15:59:13', 'admin', NULL, NULL, 1),
(7, 11, 100, 100, NULL, 31.249999999999993, '2024-02-01 10:39:29', 'admin', NULL, NULL, 1),
(8, 12, 100, 100, NULL, 11849.999999999998, '2024-02-01 11:04:00', 'admin', NULL, NULL, 1),
(9, 13, 100, 100, NULL, 1199.9999999999998, '2024-02-01 11:06:37', 'admin', NULL, NULL, 1),
(10, 14, 1500, 1500, NULL, 2008928.5714285714, '2024-02-02 11:43:30', 'admin', NULL, NULL, 1),
(11, 1, 200, 200, NULL, NULL, '2024-02-09 07:28:07', 'admin', NULL, NULL, 1),
(12, 1, 300, 300, NULL, NULL, '2024-02-09 09:33:16', 'admin', NULL, NULL, 1),
(13, 1, 300, 300, NULL, NULL, '2024-02-09 09:41:23', 'admin', NULL, NULL, 1),
(14, 1, 400, 400, NULL, NULL, '2024-02-09 11:46:53', 'admin', NULL, NULL, 1),
(15, 6, 10, NULL, 0, 541.0714285714286, '2024-02-14 09:17:41', 'admin', NULL, NULL, 1),
(16, 11, 100, NULL, 0, 31.249999999999993, '2024-02-14 09:17:49', 'admin', NULL, NULL, 1),
(17, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:17:53', 'admin', NULL, NULL, 1),
(18, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:17:53', 'admin', NULL, NULL, 1),
(19, 6, 10, NULL, 0, 541.0714285714286, '2024-02-14 09:22:33', 'admin', NULL, NULL, 1),
(20, 11, 100, NULL, 0, 31.249999999999993, '2024-02-14 09:22:34', 'admin', NULL, NULL, 1),
(21, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:22:37', 'admin', NULL, NULL, 1),
(22, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:22:39', 'admin', NULL, NULL, 1),
(23, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:22:46', 'admin', NULL, NULL, 1),
(24, 6, 10, NULL, 0, 541.0714285714286, '2024-02-14 09:23:07', 'admin', NULL, NULL, 1),
(25, 11, 100, NULL, 0, 31.249999999999993, '2024-02-14 09:23:07', 'admin', NULL, NULL, 1),
(26, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:23:07', 'admin', NULL, NULL, 1),
(27, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:23:07', 'admin', NULL, NULL, 1),
(28, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 09:23:07', 'admin', NULL, NULL, 1),
(29, 6, 10, NULL, 0, 541.0714285714286, '2024-02-14 10:06:26', 'admin', NULL, NULL, 1),
(30, 11, 100, NULL, 0, 31.249999999999993, '2024-02-14 10:06:26', 'admin', NULL, NULL, 1),
(31, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 10:06:26', 'admin', NULL, NULL, 1),
(32, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 10:06:26', 'admin', NULL, NULL, 1),
(33, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 10:06:26', 'admin', NULL, NULL, 1),
(34, 6, 10, NULL, 0, 541.0714285714286, '2024-02-14 10:06:32', 'admin', NULL, NULL, 1),
(35, 11, 100, NULL, 0, 31.249999999999993, '2024-02-14 10:06:34', 'admin', NULL, NULL, 1),
(36, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 10:06:35', 'admin', NULL, NULL, 1),
(37, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 10:06:37', 'admin', NULL, NULL, 1),
(38, 12, 100, NULL, 0, 11849.999999999998, '2024-02-14 10:06:39', 'admin', NULL, NULL, 1),
(39, 11, 100, 100, NULL, NULL, '2024-02-14 10:38:14', 'admin', NULL, NULL, 1),
(40, 11, 200, 100, NULL, NULL, '2024-02-14 10:44:21', 'admin', NULL, NULL, 1),
(41, 11, 300, 100, NULL, NULL, '2024-02-14 10:47:23', 'admin', NULL, NULL, 1),
(42, 9, 100, 100, NULL, NULL, '2024-02-14 10:51:01', 'admin', NULL, NULL, 1),
(43, 12, 100, 150, NULL, NULL, '2024-02-14 10:52:03', 'admin', NULL, NULL, 1),
(44, 6, 10, 100, NULL, NULL, '2024-02-14 10:56:26', 'admin', NULL, NULL, 1),
(45, 7, 10, 50, NULL, NULL, '2024-02-14 10:56:41', 'admin', NULL, NULL, 1),
(46, 15, 150, 150, NULL, 22762.5, '2024-02-14 11:04:48', 'admin', NULL, NULL, 1),
(47, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(48, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(49, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(50, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(51, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(52, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(53, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(54, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(55, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(56, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:29:28', 'admin', NULL, NULL, 1),
(57, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(58, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(59, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(60, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(61, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(62, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(63, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(64, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(65, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(66, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:30:37', 'admin', NULL, NULL, 1),
(67, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(68, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(69, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(70, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(71, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(72, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(73, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(74, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(75, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(76, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:41:02', 'admin', NULL, NULL, 1),
(77, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(78, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(79, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(80, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(81, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(82, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(83, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(84, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(85, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(86, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:42:47', 'admin', NULL, NULL, 1),
(87, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(88, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(89, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(90, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(91, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(92, 6, 110, NULL, 0, 5951.785714285714, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(93, 11, 400, NULL, 0, 124.99999999999997, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(94, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(95, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(96, 12, 250, NULL, 0, 29624.999999999996, '2024-02-15 15:56:08', 'admin', NULL, NULL, 1),
(97, 7, 60, NULL, 0, 3080.3571428571427, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(98, 11, 400, NULL, 0, 124.99999999999997, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(99, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(100, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(101, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(102, 6, 110, NULL, 0, 5951.785714285714, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(103, 11, 400, NULL, 0, 124.99999999999997, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(104, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(105, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(106, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:45:01', 'admin', NULL, NULL, 1),
(107, 7, 60, NULL, 0, 3080.3571428571427, '2024-02-16 07:46:59', 'admin', NULL, NULL, 1),
(108, 11, 400, NULL, 0, 124.99999999999997, '2024-02-16 07:47:01', 'admin', NULL, NULL, 1),
(109, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:47:03', 'admin', NULL, NULL, 1),
(110, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:47:05', 'admin', NULL, NULL, 1),
(111, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:47:11', 'admin', NULL, NULL, 1),
(112, 6, 110, NULL, 0, 5951.785714285714, '2024-02-16 07:47:26', 'admin', NULL, NULL, 1),
(113, 11, 400, NULL, 0, 124.99999999999997, '2024-02-16 07:47:26', 'admin', NULL, NULL, 1),
(114, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:47:26', 'admin', NULL, NULL, 1),
(115, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:47:26', 'admin', NULL, NULL, 1),
(116, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 07:47:26', 'admin', NULL, NULL, 1),
(117, 7, 49.2, NULL, 10.8, 2525.892857142857, '2024-02-16 08:00:08', 'admin', NULL, NULL, 1),
(118, 11, 400, NULL, 0, 124.99999999999997, '2024-02-16 08:00:12', 'admin', NULL, NULL, 1),
(119, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 08:00:13', 'admin', NULL, NULL, 1),
(120, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 08:00:13', 'admin', NULL, NULL, 1),
(121, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 08:00:13', 'admin', NULL, NULL, 1),
(122, 6, 99.2, NULL, 10.8, 5367.428571428572, '2024-02-16 08:00:24', 'admin', NULL, NULL, 1),
(123, 11, 400, NULL, 0, 124.99999999999997, '2024-02-16 08:00:24', 'admin', NULL, NULL, 1),
(124, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 08:00:24', 'admin', NULL, NULL, 1),
(125, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 08:00:24', 'admin', NULL, NULL, 1),
(126, 12, 250, NULL, 0, 29624.999999999996, '2024-02-16 08:00:24', 'admin', NULL, NULL, 1),
(127, 1, 500, 58, NULL, NULL, '2024-02-23 06:04:40', 'admin', NULL, NULL, 1),
(128, 16, 100, 100, NULL, 8928.571428571428, '2024-02-23 06:05:01', 'admin', NULL, NULL, 1),
(129, 6, -455.8, NULL, 555, -24662.035714285714, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(130, 11, -155, NULL, 555, -48.43749999999999, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(131, 12, 195, NULL, 55, 23107.499999999996, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(132, 12, -1305, NULL, 1500, -154642.49999999997, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(133, 12, -1370, NULL, 65, -162344.99999999997, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(134, 7, 38.300000000000004, NULL, 10.9, 1966.294642857143, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(135, 11, -1715, NULL, 1560, -535.9374999999999, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(136, 12, -2930, NULL, 1560, -347204.99999999994, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(137, 12, -4510, NULL, 1580, -534434.9999999999, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(138, 12, -6060, NULL, 1550, -718109.9999999999, '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(139, 8, -140, NULL, 150, -13286.25, '2024-02-29 06:46:57', 'admin', NULL, NULL, 1),
(140, 11, -3220, NULL, 1505, -1006.2499999999998, '2024-02-29 06:48:30', 'admin', NULL, NULL, 1),
(141, 12, -6215, NULL, 155, -736477.4999999999, '2024-02-29 06:48:33', 'admin', NULL, NULL, 1),
(142, 12, -7795, NULL, 1580, -923707.4999999999, '2024-02-29 06:49:24', 'admin', NULL, NULL, 1),
(143, 12, -8306, NULL, 511, -984260.9999999999, '2024-02-29 06:49:24', 'admin', NULL, NULL, 1),
(144, 7, -48.699999999999996, NULL, 87, -2500.2232142857138, '2024-02-29 06:49:25', 'admin', NULL, NULL, 1),
(145, 11, -4006, NULL, 786, -1251.8749999999998, '2024-02-29 06:49:26', 'admin', NULL, NULL, 1),
(146, 12, -9092, NULL, 786, -1077401.9999999998, '2024-02-29 06:49:26', 'admin', NULL, NULL, 1),
(147, 12, -10079, NULL, 987, -1194361.4999999998, '2024-02-29 06:49:26', 'admin', NULL, NULL, 1),
(148, 12, -10846, NULL, 767, -1285250.9999999998, '2024-02-29 06:49:27', 'admin', NULL, NULL, 1),
(149, 8, -150.9, NULL, 10.9, -14320.679464285713, '2024-02-29 06:49:27', 'admin', NULL, NULL, 1),
(150, 11, -5511, NULL, 1505, -1722.1874999999998, '2024-02-29 06:49:27', 'admin', NULL, NULL, 1),
(151, 12, -12406, NULL, 1560, -1470110.9999999998, '2024-02-29 06:49:27', 'admin', NULL, NULL, 1),
(152, 12, -13986, NULL, 1580, -1657340.9999999998, '2024-02-29 06:49:28', 'admin', NULL, NULL, 1),
(153, 12, -15546, NULL, 1560, -1842200.9999999998, '2024-02-29 06:49:28', 'admin', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedido`
--

CREATE TABLE `detallepedido` (
  `iddetallepedido` int(11) NOT NULL,
  `iddetallepedidonormal` int(11) NOT NULL,
  `idmaterial` int(11) NOT NULL,
  `cantidad` double NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedidonormal`
--

CREATE TABLE `detallepedidonormal` (
  `iddetallepedidonormal` int(11) NOT NULL,
  `idpedido` int(11) NOT NULL,
  `idcamion` int(11) NOT NULL,
  `idcemento` int(11) DEFAULT NULL,
  `cantidadcemento` double DEFAULT NULL,
  `idarena` int(11) DEFAULT NULL,
  `cantidadarena` double DEFAULT NULL,
  `idpiedrin` int(11) DEFAULT NULL,
  `cantidadpiedrin` double DEFAULT NULL,
  `idaditivo` int(11) DEFAULT NULL,
  `cantidadaditivo` double DEFAULT NULL,
  `idagua` int(11) DEFAULT NULL,
  `cantidadagua` double DEFAULT NULL,
  `idotromaterial` int(11) DEFAULT NULL,
  `cantidadotromaterial` double DEFAULT NULL,
  `cantidaddespachada` double DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomoficacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detallepedidonormal`
--

INSERT INTO `detallepedidonormal` (`iddetallepedidonormal`, `idpedido`, `idcamion`, `idcemento`, `cantidadcemento`, `idarena`, `cantidadarena`, `idpiedrin`, `cantidadpiedrin`, `idaditivo`, `cantidadaditivo`, `idagua`, `cantidadagua`, `idotromaterial`, `cantidadotromaterial`, `cantidaddespachada`, `usuariocreacion`, `fechacreacion`, `usuariomoficacion`, `fechamodificacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 2, 1, 1, 2155, 12, 6405, 9, 7100, 6, 10.8, 11, 1380, NULL, NULL, NULL, 'admin', '2024-02-05 11:12:58', NULL, NULL, NULL, NULL, 1),
(2, 2, 2, 1, 2155, 12, 6380, 9, 7100, 6, 10.8, 11, 1430, NULL, NULL, NULL, 'admin', '2024-02-12 08:34:56', NULL, NULL, NULL, NULL, 1),
(3, 3, 1, 1, 2155, 12, 6405, 15, 7100, 7, 10.8, 11, 1380, NULL, NULL, NULL, 'admin', '2024-02-16 07:35:21', NULL, NULL, NULL, NULL, 1),
(4, 3, 2, 1, 2155, 12, 6380, 15, 7100, 6, 10.8, 11, 1430, NULL, NULL, NULL, 'admin', '2024-02-16 07:40:18', NULL, NULL, NULL, NULL, 1),
(5, 3, 1, 1, 1580, 12, 1560, 15, 1560, 8, 10.9, 11, 1560, NULL, NULL, 1560, 'admin', '2024-02-19 12:53:37', NULL, NULL, NULL, NULL, 1),
(6, 1, 1, 1, 1500, 12, 55, 15, 65, 6, 555, 11, 555, NULL, NULL, 55, 'admin', '2024-02-20 07:47:19', 'admin', '2024-02-22 15:56:52', NULL, NULL, 1),
(7, 1, 1, 1, 1580, 12, 1560, 15, 1550, 7, 10.9, 11, 1560, NULL, NULL, 20, 'admin', '2024-02-22 14:17:54', 'admin', '2024-02-22 15:56:24', NULL, NULL, 1),
(8, 4, 1, 1, 1580, 12, 155, 15, 511, 8, 150, 11, 1505, NULL, NULL, 15, 'admin', '2024-02-28 09:32:21', NULL, NULL, NULL, NULL, 1),
(9, 4, 1, 1, 987, 12, 786, 15, 767, 7, 87, 11, 786, NULL, NULL, 78, 'admin', '2024-02-29 06:40:40', NULL, NULL, NULL, NULL, 1),
(10, 4, 1, 1, 1580, 12, 1560, 15, 1560, 8, 10.9, 11, 1505, NULL, NULL, 85, 'admin', '2024-02-29 06:43:04', NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diesel`
--

CREATE TABLE `diesel` (
  `iddiesel` int(11) NOT NULL,
  `fechaconsumo` datetime DEFAULT NULL,
  `cantidadconsumida` double NOT NULL,
  `precio` double DEFAULT NULL,
  `cantidadtotalmaterial` double DEFAULT NULL,
  `cantidadtotaldinero` double DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `diesel`
--

INSERT INTO `diesel` (`iddiesel`, `fechaconsumo`, `cantidadconsumida`, `precio`, `cantidadtotalmaterial`, `cantidadtotaldinero`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuariomodificacion`, `fechamodificacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, NULL, 4.5, NULL, 0, 0, 'Para el calculo de horaw', 'admin', '2024-01-16 10:38:15', NULL, NULL, 'admin', '2024-01-29 09:49:53', 1),
(2, '2024-02-16 00:00:00', 2000, 22.35, 20, 44700, '222', 'admin', '2024-02-27 13:52:39', NULL, NULL, NULL, NULL, 1),
(3, '2024-02-16 00:00:00', 2000, 22.35, 20, 44700, '222', 'admin', '2024-02-27 14:09:43', NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodespacho`
--

CREATE TABLE `estadodespacho` (
  `idestadodespacho` int(11) NOT NULL,
  `estado` varchar(250) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estadodespacho`
--

INSERT INTO `estadodespacho` (`idestadodespacho`, `estado`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 'En proceso', NULL, 'admin', '2024-02-23 00:00:00', NULL, NULL, 1),
(2, 'Finalizado', NULL, 'admin', '2024-02-23 00:00:00', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadopedido`
--

CREATE TABLE `estadopedido` (
  `idestadopedido` int(11) NOT NULL,
  `estado` varchar(250) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estadopedido`
--

INSERT INTO `estadopedido` (`idestadopedido`, `estado`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 'En proceso', 'Pendiente de finalizar', 'admin', '2024-01-29 00:00:00', NULL, NULL, 1),
(2, 'Finalizado', 'Pedido enviado a despacho', 'admin', '2024-01-29 00:00:00', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos`
--

CREATE TABLE `gastos` (
  `idgasto` int(11) NOT NULL,
  `tipogasto` varchar(1000) NOT NULL,
  `total` double NOT NULL,
  `pordia` double NOT NULL,
  `sietedias` double NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kilogramo`
--

CREATE TABLE `kilogramo` (
  `idkilogramo` int(11) NOT NULL,
  `valor` double NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `kilogramo`
--

INSERT INTO `kilogramo` (`idkilogramo`, `valor`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 1000, 'Para una tonelada de cemento', 'admin', '2024-01-12 00:00:00', NULL, NULL, 1),
(2, 1276, 'Para M|3 de piedrin de 3/8\"', 'admin', '2024-01-12 00:00:00', NULL, NULL, 1),
(3, 1557, 'Para M|3 de arena de 1/4\" - 0\"', 'admin', '2024-01-12 00:00:00', NULL, NULL, 1),
(4, 1369, 'Para M|3 para piedrin de 1\"', 'admin', '2024-01-12 00:00:00', NULL, NULL, 1),
(5, 3.78541, 'Galón', 'admin', '2024-01-12 00:00:00', 'admin', '2024-01-29 09:50:13', 1),
(6, 0.453592, 'Para una libra', 'admin', '2024-01-12 15:45:20', 'admin', '2024-01-16 07:17:02', 0),
(7, 0.453592, 'Libra', 'admin', '2024-02-10 14:36:21', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material`
--

CREATE TABLE `material` (
  `idmaterial` int(11) NOT NULL,
  `idunidadmedida` int(11) NOT NULL,
  `material` varchar(1000) NOT NULL,
  `existenciainicial` double DEFAULT NULL,
  `valor` double NOT NULL,
  `valorneto` double NOT NULL,
  `costo` double DEFAULT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `material`
--

INSERT INTO `material` (`idmaterial`, `idunidadmedida`, `material`, `existenciainicial`, `valor`, `valorneto`, `costo`, `fechacreacion`, `usuariocreacion`, `fechamodificacion`, `usuariomodificacion`, `fechaeliminacion`, `usuarioeliminacion`, `activo`) VALUES
(1, 1, 'Cemento', 558, 2122.31, 1894.9196428571427, 1.8949196428571426, '2024-01-23 14:37:01', 'admin', '2024-02-23 06:04:40', 'admin', NULL, NULL, 1),
(2, 9, 'ACEITE DE MOTOR PHILIPS 66', 5, 6750, 6026.785714285714, NULL, '2024-01-23 14:42:22', 'admin', NULL, NULL, NULL, NULL, 1),
(3, 11, 'CUBETA DE PUMPAK', 4, 1450, 1294.642857142857, NULL, '2024-01-23 14:43:59', 'admin', NULL, NULL, NULL, NULL, 1),
(4, 10, 'GRASA EN TUBO MARCA CHEVRON DELO STAR', 8, 43.92, 39.214285714285715, NULL, '2024-01-23 14:53:24', 'admin', NULL, NULL, NULL, NULL, 1),
(5, 12, 'MICROSILISICA MARCA EUCLIDE', 5, 13.44, 11.999999999999998, NULL, '2024-01-24 13:56:42', 'admin', NULL, NULL, NULL, NULL, 1),
(6, 7, 'ADITIVO ISOFLUID DE CEMEX AGRECA', -455.8, 60.6, 54.107142857142854, 14.293601712137615, '2024-01-31 15:41:21', 'admin', '2024-02-27 12:54:31', 'admin', NULL, NULL, 1),
(7, 7, 'ADITIVO EUCON RETARDER DE EUCLID', -48.699999999999996, 57.5, 51.33928571428571, 13.56241086547711, '2024-01-31 15:43:36', 'admin', '2024-02-29 06:49:24', 'admin', NULL, NULL, 1),
(8, 7, 'ADITIVO DELVO STABLISHER BASF', -150.9, 106.29, 94.90178571428571, NULL, '2024-01-31 15:56:06', 'admin', '2024-02-29 06:49:27', 'admin', NULL, NULL, 1),
(9, 2, 'PIEDRIN DE 3/4\"', 200, 169.96, 151.75, NULL, '2024-01-31 15:58:48', 'admin', '2024-02-14 10:51:01', 'admin', NULL, NULL, 1),
(10, 3, 'PIEDRIN DE 1 1/2\"', 100, 169.96, 151.75, NULL, '2024-01-31 15:59:13', 'admin', NULL, NULL, NULL, NULL, 1),
(11, 7, 'Agua', -5511, 0.35, 0.31249999999999994, 0.08255380526812153, '2024-02-01 10:39:29', 'admin', '2024-02-29 06:49:27', 'admin', NULL, NULL, 1),
(12, 5, 'ARENA DE 1/4\" - 0\"', -15546, 132.72, 118.49999999999999, 0.07610789980732176, '2024-02-01 11:04:00', 'admin', '2024-02-29 06:49:28', 'admin', NULL, NULL, 1),
(13, 12, 'FIBRA DE POLIPROPILENO MARCA EUCLID', 100, 13.44, 11.999999999999998, NULL, '2024-02-01 11:06:37', 'admin', NULL, NULL, NULL, NULL, 1),
(14, 7, 'Diésel', 1500, 1500, 1339.2857142857142, NULL, '2024-02-02 11:43:30', 'admin', NULL, NULL, NULL, NULL, 1),
(15, 4, 'Piedrín de 3/8\"', 150, 169.96, 151.75, 0.11892633228840126, '2024-02-14 11:04:46', 'admin', NULL, NULL, NULL, NULL, 1),
(16, 9, 'prueba', 100, 100, 89.28571428571428, NULL, '2024-02-23 06:05:01', 'admin', NULL, NULL, '2024-02-23 06:05:08', 'admin', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materialcamion`
--

CREATE TABLE `materialcamion` (
  `idmaterialcamion` int(11) NOT NULL,
  `idcamion` int(11) NOT NULL,
  `idmaterial` int(11) NOT NULL,
  `idpedido` int(11) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `idpedido` int(11) NOT NULL,
  `idasesor` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `idtipopago` int(11) NOT NULL,
  `idestadopedido` int(11) NOT NULL,
  `tipopedido` int(11) DEFAULT NULL,
  `obra` varchar(500) NOT NULL,
  `elemento` varchar(100) NOT NULL,
  `volumen` double NOT NULL,
  `fraguado` int(11) DEFAULT NULL,
  `cantidadpagada` double DEFAULT NULL,
  `kgcm3` int(11) DEFAULT NULL,
  `agregado` varchar(100) DEFAULT NULL,
  `revpulg` int(11) DEFAULT NULL,
  `frec` int(11) DEFAULT NULL,
  `bombeo` varchar(25) NOT NULL,
  `dirbom` varchar(50) DEFAULT NULL,
  `colocado` varchar(25) DEFAULT NULL,
  `tipocolocado` varchar(75) DEFAULT NULL,
  `cantidadcobradacolocado` double DEFAULT NULL,
  `colocador` varchar(50) DEFAULT NULL,
  `tuberia` int(11) DEFAULT NULL,
  `laboratorio` varchar(50) DEFAULT NULL,
  `confirmado` tinyint(1) DEFAULT NULL,
  `fechapedido` datetime DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`idpedido`, `idasesor`, `idcliente`, `idtipopago`, `idestadopedido`, `tipopedido`, `obra`, `elemento`, `volumen`, `fraguado`, `cantidadpagada`, `kgcm3`, `agregado`, `revpulg`, `frec`, `bombeo`, `dirbom`, `colocado`, `tipocolocado`, `cantidadcobradacolocado`, `colocador`, `tuberia`, `laboratorio`, `confirmado`, `fechapedido`, `usuariocreacion`, `fechacreacion`, `usuariomodificacion`, `fechamodificacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 1, 1, 1, 2, 2, 'San juanes', 'losa', 50, 28, NULL, 4000, '1.5', 6, 15, '', 'no', NULL, NULL, NULL, 'be-02salva', 0, 'no', 1, NULL, 'admin', '2024-01-31 07:00:59', 'admin', '2024-02-27 12:54:31', NULL, NULL, 1),
(2, 1, 1, 2, 2, 1, 'SAN CRISTOBAL TOTO', 'pavimento', 50, 28, NULL, 3000, '1', 6, 15, '', 'no', 'No', NULL, NULL, 'no', 0, 'no', 1, NULL, 'admin', '2024-01-31 08:36:46', 'admin', '2024-02-15 15:56:10', NULL, NULL, 1),
(3, 1, 2, 1, 2, 1, 'San Rafel pie de la Cuesta', 'Losa', 53, 28, 56500, 3000, '1', 6, 15, 'No', 'no', 'No', NULL, NULL, NULL, 0, 'No', NULL, NULL, 'admin', '2024-02-16 07:26:09', 'admin', '2024-02-16 08:01:09', NULL, NULL, 1),
(4, 1, 1, 1, 2, NULL, 'prueba', 'losa', 50, 111, 1000, 11, '11', 11, 11, 'Si', 'a', 'No', NULL, NULL, NULL, 1, 'o', NULL, '2024-02-27 17:00:00', 'admin', '2024-02-28 09:31:45', 'admin', '2024-02-29 06:49:28', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resumendiario`
--

CREATE TABLE `resumendiario` (
  `idresumendiario` int(11) NOT NULL,
  `materiaprima` double DEFAULT NULL,
  `diesel` double DEFAULT NULL,
  `bombeo` double DEFAULT NULL,
  `colocado` double DEFAULT NULL,
  `comision` double DEFAULT NULL,
  `totalcosto` double DEFAULT NULL,
  `ingresoneto` int(11) DEFAULT NULL,
  `utilidadbruta` int(11) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resumeobras`
--

CREATE TABLE `resumeobras` (
  `idresumen` int(11) NOT NULL,
  `cliente` varchar(250) DEFAULT NULL,
  `obra` varchar(1000) DEFAULT NULL,
  `metrosencargados` double DEFAULT NULL,
  `metrosvendidos` double DEFAULT NULL,
  `totalmateriaprima` double DEFAULT NULL,
  `totaldiesel` double DEFAULT NULL,
  `totalbombeo` double DEFAULT NULL,
  `totalcolocado` double DEFAULT NULL,
  `totalcomision` double DEFAULT NULL,
  `totalingresoneto` double DEFAULT NULL,
  `beneficioprimo` double DEFAULT NULL,
  `compras` double DEFAULT NULL,
  `beneficio` decimal(10,0) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secuenciapedido`
--

CREATE TABLE `secuenciapedido` (
  `idsecuenciapedido` int(11) NOT NULL,
  `iddetallepedidonormal` int(11) NOT NULL,
  `secuencia` int(11) NOT NULL,
  `correlativo` varchar(250) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `secuenciapedido`
--

INSERT INTO `secuenciapedido` (`idsecuenciapedido`, `iddetallepedidonormal`, `secuencia`, `correlativo`, `fechacreacion`, `usuariocreacion`, `fechaeliminacion`, `usuarioeliminacion`, `activo`) VALUES
(1, 2, 1, '2024-1', '2024-02-22 14:59:25', 'admin', NULL, NULL, 1),
(2, 8, 2, '2024-2', '2024-02-28 09:40:21', 'admin', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipopago`
--

CREATE TABLE `tipopago` (
  `idtipopago` int(11) NOT NULL,
  `tipopago` varchar(250) NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipopago`
--

INSERT INTO `tipopago` (`idtipopago`, `tipopago`, `usuariocreacion`, `fechacreacion`, `activo`) VALUES
(1, 'Contado', 'admin', '2024-01-30 00:00:00', 1),
(2, 'Credito', 'admin', '2024-01-30 00:00:00', 1),
(3, 'Mixto', 'admin', '2024-01-30 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `idtipousuario` int(11) NOT NULL,
  `tipousuario` varchar(250) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`idtipousuario`, `tipousuario`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 'administrador', 'Administrar todas las funciones del sistema', 'admin', '2024-01-10 00:00:00', NULL, NULL, 1),
(2, 'supervisor', 'ingrese a las funcionalidades de inventario y pedidos', 'admin', '2024-01-10 00:00:00', NULL, NULL, 1),
(3, 'asesor', 'ingrese a las funcionalidades de pedidos', 'admin', '2024-01-10 00:00:00', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidadmedida`
--

CREATE TABLE `unidadmedida` (
  `idunidadmedida` int(11) NOT NULL,
  `idkilogramo` int(11) DEFAULT NULL,
  `unidadmedida` varchar(250) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `unidadmedida`
--

INSERT INTO `unidadmedida` (`idunidadmedida`, `idkilogramo`, `unidadmedida`, `descripcion`, `usuariocreacion`, `fechacreacion`, `usuarioeliminacion`, `fechaeliminacion`, `activo`) VALUES
(1, 1, 'Tonelada', 'Para el cemento', 'admin', '2024-01-18 06:49:30', NULL, NULL, 1),
(2, NULL, 'M|3', 'PIEDRIN DE 3/4\"', 'admin', '2024-01-22 07:43:40', NULL, NULL, 1),
(3, NULL, 'M|3', 'Piedrin de 1 1/2\"', 'admin', '2024-01-22 07:47:27', NULL, NULL, 1),
(4, 2, 'M|3', 'PIEDRIN DE 3/8\"', 'admin', '2024-01-22 07:48:01', NULL, NULL, 1),
(5, 3, 'M|3', 'ARENA DE 1/4\" - 0\"', 'admin', '2024-01-22 08:49:00', NULL, NULL, 1),
(6, 4, 'M|3', 'PIEDRIN DE 1\"', 'admin', '2024-01-22 09:11:57', NULL, NULL, 1),
(7, 5, 'Galón', '', 'admin', '2024-01-22 09:12:42', NULL, NULL, 1),
(8, NULL, 'Libra', 'Para libras', 'admin', '2024-01-22 09:24:01', 'admin', '2024-01-29 08:59:07', 1),
(9, NULL, 'Tonel', '', 'admin', '2024-01-22 09:25:30', NULL, NULL, 1),
(10, NULL, 'Tubo', '', 'admin', '2024-01-22 09:25:45', NULL, NULL, 1),
(11, NULL, 'Cubeta', '', 'admin', '2024-01-22 09:25:52', NULL, NULL, 1),
(12, NULL, 'Kilogramo', '', 'admin', '2024-01-22 09:26:34', NULL, NULL, 1),
(13, 5, '11', '11', 'admin', '2024-01-25 15:19:47', 'admin', '2024-01-25 15:19:51', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `idtipousuario` int(11) NOT NULL,
  `nombres` varchar(500) NOT NULL,
  `apellidos` varchar(500) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fechacreacion` datetime NOT NULL,
  `usuariocreacion` varchar(50) NOT NULL,
  `fechamodificacion` datetime DEFAULT NULL,
  `usuariomodificacion` varchar(50) DEFAULT NULL,
  `fechaeliminacion` datetime DEFAULT NULL,
  `usuarioeliminacion` varchar(50) DEFAULT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `idtipousuario`, `nombres`, `apellidos`, `usuario`, `password`, `fechacreacion`, `usuariocreacion`, `fechamodificacion`, `usuariomodificacion`, `fechaeliminacion`, `usuarioeliminacion`, `activo`) VALUES
(1, 1, 'Raul', 'Cacacho', 'admin', '0e7517141fb53f21ee439b355b5a1d0a', '2024-01-10 00:00:00', 'admin', '2024-01-29 08:21:13', 'admin', NULL, NULL, 1),
(2, 2, 'prueba', 'prueba', 'prueba', 'prueba', '2024-01-23 14:47:49', 'admin', NULL, NULL, '2024-01-25 15:13:16', 'admin', 0),
(3, 1, 'Carlos', 'Tzic', 'carlostzic', '00b237ef918f650645e3748f9b66d797', '2024-01-25 10:13:26', 'admin', '2024-02-10 14:34:31', 'admin', NULL, NULL, 1),
(4, 2, '11', '11', '11', '11', '2024-01-25 15:19:26', 'admin', NULL, NULL, '2024-01-25 15:19:29', 'admin', 0),
(5, 1, 'Diego', 'Barreno', 'diegobarreno', 'a27f4b6bbe0b6f0b9c3dc60394d1e793', '2024-02-10 13:54:14', 'admin', NULL, NULL, NULL, NULL, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asesor`
--
ALTER TABLE `asesor`
  ADD PRIMARY KEY (`idasesor`);

--
-- Indices de la tabla `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`idcamion`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `confirmacionpago`
--
ALTER TABLE `confirmacionpago`
  ADD PRIMARY KEY (`idconfirmacionpago`),
  ADD KEY `FK_ConfirmacionPedido` (`idpedido`);

--
-- Indices de la tabla `despachos`
--
ALTER TABLE `despachos`
  ADD PRIMARY KEY (`iddespacho`),
  ADD KEY `FK_DespachoPedido` (`idpedido`),
  ADD KEY `FK_EstadoDespacho` (`idestadodespacho`),
  ADD KEY `FK_DespachoAsesor` (`idasesor`);

--
-- Indices de la tabla `detallematerial`
--
ALTER TABLE `detallematerial`
  ADD PRIMARY KEY (`iddetallematerial`),
  ADD KEY `FK_DetalleMaterial` (`idmaterial`);

--
-- Indices de la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  ADD PRIMARY KEY (`iddetallepedido`),
  ADD KEY `FK_DetalleNormal` (`iddetallepedidonormal`),
  ADD KEY `FK_DetallePedMaterial` (`idmaterial`);

--
-- Indices de la tabla `detallepedidonormal`
--
ALTER TABLE `detallepedidonormal`
  ADD PRIMARY KEY (`iddetallepedidonormal`),
  ADD KEY `FK_PedidoNormal` (`idpedido`),
  ADD KEY `FK_PedidoNormalCemento` (`idcamion`);

--
-- Indices de la tabla `diesel`
--
ALTER TABLE `diesel`
  ADD PRIMARY KEY (`iddiesel`);

--
-- Indices de la tabla `estadodespacho`
--
ALTER TABLE `estadodespacho`
  ADD PRIMARY KEY (`idestadodespacho`);

--
-- Indices de la tabla `estadopedido`
--
ALTER TABLE `estadopedido`
  ADD PRIMARY KEY (`idestadopedido`);

--
-- Indices de la tabla `gastos`
--
ALTER TABLE `gastos`
  ADD PRIMARY KEY (`idgasto`);

--
-- Indices de la tabla `kilogramo`
--
ALTER TABLE `kilogramo`
  ADD PRIMARY KEY (`idkilogramo`);

--
-- Indices de la tabla `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`idmaterial`),
  ADD KEY `FK_UnidadMedida` (`idunidadmedida`);

--
-- Indices de la tabla `materialcamion`
--
ALTER TABLE `materialcamion`
  ADD PRIMARY KEY (`idmaterialcamion`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`idpedido`),
  ADD KEY `FK_PedidoAsesor` (`idasesor`),
  ADD KEY `FK_PedidoCliente` (`idcliente`),
  ADD KEY `FK_PedidoTipoPago` (`idtipopago`),
  ADD KEY `FK_EstadoPedido` (`idestadopedido`);

--
-- Indices de la tabla `resumendiario`
--
ALTER TABLE `resumendiario`
  ADD PRIMARY KEY (`idresumendiario`);

--
-- Indices de la tabla `resumeobras`
--
ALTER TABLE `resumeobras`
  ADD PRIMARY KEY (`idresumen`);

--
-- Indices de la tabla `secuenciapedido`
--
ALTER TABLE `secuenciapedido`
  ADD PRIMARY KEY (`idsecuenciapedido`),
  ADD KEY `FK_SecuenciaDetallePedido` (`iddetallepedidonormal`);

--
-- Indices de la tabla `tipopago`
--
ALTER TABLE `tipopago`
  ADD PRIMARY KEY (`idtipopago`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`idtipousuario`);

--
-- Indices de la tabla `unidadmedida`
--
ALTER TABLE `unidadmedida`
  ADD PRIMARY KEY (`idunidadmedida`),
  ADD KEY `FK_UnidadMedidaKilogramo` (`idkilogramo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `FK_TipoUsuario` (`idtipousuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asesor`
--
ALTER TABLE `asesor`
  MODIFY `idasesor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `camion`
--
ALTER TABLE `camion`
  MODIFY `idcamion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `confirmacionpago`
--
ALTER TABLE `confirmacionpago`
  MODIFY `idconfirmacionpago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `despachos`
--
ALTER TABLE `despachos`
  MODIFY `iddespacho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detallematerial`
--
ALTER TABLE `detallematerial`
  MODIFY `iddetallematerial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=154;

--
-- AUTO_INCREMENT de la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  MODIFY `iddetallepedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detallepedidonormal`
--
ALTER TABLE `detallepedidonormal`
  MODIFY `iddetallepedidonormal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `diesel`
--
ALTER TABLE `diesel`
  MODIFY `iddiesel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `estadodespacho`
--
ALTER TABLE `estadodespacho`
  MODIFY `idestadodespacho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estadopedido`
--
ALTER TABLE `estadopedido`
  MODIFY `idestadopedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `gastos`
--
ALTER TABLE `gastos`
  MODIFY `idgasto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `kilogramo`
--
ALTER TABLE `kilogramo`
  MODIFY `idkilogramo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `material`
--
ALTER TABLE `material`
  MODIFY `idmaterial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `materialcamion`
--
ALTER TABLE `materialcamion`
  MODIFY `idmaterialcamion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `idpedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `resumendiario`
--
ALTER TABLE `resumendiario`
  MODIFY `idresumendiario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `resumeobras`
--
ALTER TABLE `resumeobras`
  MODIFY `idresumen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `secuenciapedido`
--
ALTER TABLE `secuenciapedido`
  MODIFY `idsecuenciapedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipopago`
--
ALTER TABLE `tipopago`
  MODIFY `idtipopago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `idtipousuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `unidadmedida`
--
ALTER TABLE `unidadmedida`
  MODIFY `idunidadmedida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `confirmacionpago`
--
ALTER TABLE `confirmacionpago`
  ADD CONSTRAINT `FK_ConfirmacionPedido` FOREIGN KEY (`idpedido`) REFERENCES `pedidos` (`idpedido`);

--
-- Filtros para la tabla `despachos`
--
ALTER TABLE `despachos`
  ADD CONSTRAINT `FK_DespachoAsesor` FOREIGN KEY (`idasesor`) REFERENCES `asesor` (`idasesor`),
  ADD CONSTRAINT `FK_DespachoPedido` FOREIGN KEY (`idpedido`) REFERENCES `pedidos` (`idpedido`),
  ADD CONSTRAINT `FK_EstadoDespacho` FOREIGN KEY (`idestadodespacho`) REFERENCES `estadodespacho` (`idestadodespacho`);

--
-- Filtros para la tabla `detallematerial`
--
ALTER TABLE `detallematerial`
  ADD CONSTRAINT `FK_DetalleMaterial` FOREIGN KEY (`idmaterial`) REFERENCES `material` (`idmaterial`);

--
-- Filtros para la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  ADD CONSTRAINT `FK_DetalleNormal` FOREIGN KEY (`iddetallepedidonormal`) REFERENCES `detallepedidonormal` (`iddetallepedidonormal`),
  ADD CONSTRAINT `FK_DetallePedMaterial` FOREIGN KEY (`idmaterial`) REFERENCES `material` (`idmaterial`);

--
-- Filtros para la tabla `detallepedidonormal`
--
ALTER TABLE `detallepedidonormal`
  ADD CONSTRAINT `FK_PedidoNormal` FOREIGN KEY (`idpedido`) REFERENCES `pedidos` (`idpedido`),
  ADD CONSTRAINT `FK_PedidoNormalCamion` FOREIGN KEY (`idcamion`) REFERENCES `camion` (`idcamion`),
  ADD CONSTRAINT `FK_PedidoNormalCemento` FOREIGN KEY (`idcamion`) REFERENCES `material` (`idmaterial`);

--
-- Filtros para la tabla `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `FK_UnidadMedida` FOREIGN KEY (`idunidadmedida`) REFERENCES `unidadmedida` (`idunidadmedida`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `FK_EstadoPedido` FOREIGN KEY (`idestadopedido`) REFERENCES `estadopedido` (`idestadopedido`),
  ADD CONSTRAINT `FK_PedidoAsesor` FOREIGN KEY (`idasesor`) REFERENCES `asesor` (`idasesor`),
  ADD CONSTRAINT `FK_PedidoCliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  ADD CONSTRAINT `FK_PedidoTipoPago` FOREIGN KEY (`idtipopago`) REFERENCES `tipopago` (`idtipopago`);

--
-- Filtros para la tabla `secuenciapedido`
--
ALTER TABLE `secuenciapedido`
  ADD CONSTRAINT `FK_SecuenciaDetallePedido` FOREIGN KEY (`iddetallepedidonormal`) REFERENCES `detallepedidonormal` (`iddetallepedidonormal`);

--
-- Filtros para la tabla `unidadmedida`
--
ALTER TABLE `unidadmedida`
  ADD CONSTRAINT `FK_UnidadMedidaKilogramo` FOREIGN KEY (`idkilogramo`) REFERENCES `kilogramo` (`idkilogramo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_TipoUsuario` FOREIGN KEY (`idtipousuario`) REFERENCES `tipousuario` (`idtipousuario`);

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `ResumenGeneral` ON SCHEDULE EVERY 1 DAY STARTS '2024-03-05 23:00:00' ON COMPLETION PRESERVE ENABLE DO INSERT INTO resumeobras (activo, beneficio, beneficioprimo, cliente, fechacreacion, metrosencargados, metrosvendidos, obra, totalbombeo, totalcolocado,
                        totalcomision, totaldiesel, totalingresoneto, totalmateriaprima, usuariocreacion)
select 1, sum(d.beneficiometrocubico) beneficio, sum(d.beneficioprimo) beneficioprimo, concat(c.nombres, ' ', c.apellidos) cliente, 
CURDATE() fechacreacion, sum(d.metroscubicossolicitados) metrosencargado, sum(d.metroscubicosvendidos) metrosvendidos, p.obra,
sum(d.costototalbombeo) totalbombeo, sum(d.costototalcolocado) totalcolocado, sum(d.comision) totalcomision, sum(d.costototaldieseldinero) totaldiesel,
sum(d.pagototalsiniva) totalingresoneto, sum(d.totalmateriaprima) totalmateriaprima, 'sistema' usuariocreacion
from despachos d
join pedidos p on d.idpedido = d.idpedido and p.activo = 1 
left join cliente c on p.idcliente = c.idcliente and c.activo = 1
where d.fechacreacion BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-%d 00:00:00') and DATE_FORMAT(CURDATE(), '%Y-%m-%d 23:59:00')$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
