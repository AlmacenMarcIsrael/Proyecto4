-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-12-2016 a las 18:40:18
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_estoc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_categoria`
--

CREATE TABLE `tbl_categoria` (
  `categoria_id` int(11) NOT NULL,
  `categoria_nom` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tbl_categoria`
--

INSERT INTO `tbl_categoria` (`categoria_id`, `categoria_nom`) VALUES
(1, 'Dormitorio'),
(2, 'Comedor'),
(3, 'Salón'),
(4, 'Cocina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_comanda`
--

CREATE TABLE `tbl_comanda` (
  `comanda_id` int(11) NOT NULL,
  `comanda_quantitat` int(5) NOT NULL,
  `comanda_data` date NOT NULL,
  `prov_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tbl_comanda`
--

INSERT INTO `tbl_comanda` (`comanda_id`, `comanda_quantitat`, `comanda_data`, `prov_id`) VALUES
(1, 5, '2016-12-13', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_detall_com`
--

CREATE TABLE `tbl_detall_com` (
  `detall_com_id` int(11) NOT NULL,
  `comanda_id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tbl_detall_com`
--

INSERT INTO `tbl_detall_com` (`detall_com_id`, `comanda_id`, `prod_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_estoc`
--

CREATE TABLE `tbl_estoc` (
  `estoc_id` int(11) NOT NULL,
  `estoc_q_max` int(5) NOT NULL,
  `estoc_q_min` int(5) NOT NULL,
  `prod_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tbl_estoc`
--

INSERT INTO `tbl_estoc` (`estoc_id`, `estoc_q_max`, `estoc_q_min`, `prod_id`) VALUES
(1, 10, 3, 1),
(2, 20, 10, 2),
(3, 10, 2, 3),
(4, 15, 3, 4),
(5, 30, 5, 5),
(6, 12, 2, 6),
(7, 10, 2, 8),
(8, 25, 2, 9),
(9, 10, 2, 7),
(10, 50, 10, 10),
(11, 10, 2, 11),
(12, 20, 10, 12),
(13, 20, 10, 13),
(14, 20, 10, 14),
(15, 20, 5, 15),
(16, 20, 3, 16),
(17, 12, 12, 17),
(18, 12, 32, 18),
(23, 10, 5, 23),
(24, 10, 7, 24),
(25, 10, 7, 25),
(26, 10, 7, 26),
(27, 10, 7, 27),
(28, 10, 7, 28),
(29, 10, 7, 29),
(30, 20, 10, 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_lloc`
--

CREATE TABLE `tbl_lloc` (
  `lloc_id` int(11) NOT NULL,
  `num_bloc` enum('Bloc 1','Bloc 2','Bloc3','Bloc 4','Bloc 5','Bloc 6','Bloc 7','Bloc 8') COLLATE utf8_unicode_ci NOT NULL,
  `num_passadis` enum('passadis 1','passadis 2','passadis 3','passadis 4','passadis 5','passadis 6','passadis 7','passadis 8','passadis 9','passadis 10','passadis 11','passadis 12') COLLATE utf8_unicode_ci NOT NULL,
  `num_lleixa` enum('lleixa 1','lleixa 2','lleixa 3','lleixa 4','lleixa 5','lleixa 6','lleixa 7','lleixa 8') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_producte`
--

CREATE TABLE `tbl_producte` (
  `prod_id` int(11) NOT NULL,
  `prod_nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prod_foto` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0.jpg',
  `prod_preu` decimal(6,2) NOT NULL,
  `categoria_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tbl_producte`
--

INSERT INTO `tbl_producte` (`prod_id`, `prod_nom`, `prod_foto`, `prod_preu`, `categoria_id`) VALUES
(1, 'Armario Blanco', 'Armario_Pax_Blanco.JPG', '128.00', 1),
(2, 'Armario Negro', 'Armario_Pax_Negro.jpg', '196.00', 1),
(3, 'Armario Rosa', 'Armario_Pax_Rosa.jpg', '340.00', 1),
(4, 'Armario con 2 cajones', 'Armario_2_cajones_STOCKHOLM.jpg', '349.00', 2),
(5, 'Mesa de centro', 'Mesa_Centro_STOCKHOLM.jpg', '199.00', 2),
(6, 'Mueble TV', 'Mueble_TV_STOCKHOLM.jpg', '349.00', 1),
(7, 'Mesa extensible blanco', 'Mesa_extensible_blanco_BJURSTA', '199.00', 1),
(8, 'Mesa extensible marron', 'Mesa_extensible_marron_BJURSTA', '199.00', 1),
(9, 'Aparador', 'Aparador_BJURSTA', '199.00', 3),
(10, 'Cama', '0.jpg', '100.00', 1),
(16, 'Nevera', '0.jpg', '350.00', 4),
(23, 'Estanteria', '0.jpg', '15.00', 1),
(24, 'Estanteria Gris', '0.jpg', '17.00', 1),
(25, 'Estanteria Azul', '0.jpg', '17.00', 1),
(26, 'Estanteria Verde', '0.jpg', '17.00', 1),
(27, 'Estanteria Amarilla', '0.jpg', '17.00', 1),
(28, 'Estanteria Rosa', '0.jpg', '17.00', 1),
(29, 'Estanteria Beige', '0.jpg', '17.00', 1),
(30, 'Mesita de noche', '0.jpg', '30.00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_proveidor`
--

CREATE TABLE `tbl_proveidor` (
  `prov_id` int(11) NOT NULL,
  `prov_nom` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `prov_adre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prov_email` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuari`
--

CREATE TABLE `tbl_usuari` (
  `usuari_id` int(11) NOT NULL,
  `login_usuari` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `pass_usuari` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nom_usuari` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `cognom_usuari` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tbl_usuari`
--

INSERT INTO `tbl_usuari` (`usuari_id`, `login_usuari`, `pass_usuari`, `nom_usuari`, `cognom_usuari`) VALUES
(1, 'mpetit', '12345', 'marc', 'petit');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_categoria`
--
ALTER TABLE `tbl_categoria`
  ADD PRIMARY KEY (`categoria_id`);

--
-- Indices de la tabla `tbl_comanda`
--
ALTER TABLE `tbl_comanda`
  ADD PRIMARY KEY (`comanda_id`);

--
-- Indices de la tabla `tbl_detall_com`
--
ALTER TABLE `tbl_detall_com`
  ADD PRIMARY KEY (`detall_com_id`);

--
-- Indices de la tabla `tbl_estoc`
--
ALTER TABLE `tbl_estoc`
  ADD PRIMARY KEY (`estoc_id`);

--
-- Indices de la tabla `tbl_lloc`
--
ALTER TABLE `tbl_lloc`
  ADD PRIMARY KEY (`lloc_id`);

--
-- Indices de la tabla `tbl_producte`
--
ALTER TABLE `tbl_producte`
  ADD PRIMARY KEY (`prod_id`);

--
-- Indices de la tabla `tbl_proveidor`
--
ALTER TABLE `tbl_proveidor`
  ADD PRIMARY KEY (`prov_id`);

--
-- Indices de la tabla `tbl_usuari`
--
ALTER TABLE `tbl_usuari`
  ADD PRIMARY KEY (`usuari_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_categoria`
--
ALTER TABLE `tbl_categoria`
  MODIFY `categoria_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tbl_comanda`
--
ALTER TABLE `tbl_comanda`
  MODIFY `comanda_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tbl_detall_com`
--
ALTER TABLE `tbl_detall_com`
  MODIFY `detall_com_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tbl_estoc`
--
ALTER TABLE `tbl_estoc`
  MODIFY `estoc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `tbl_lloc`
--
ALTER TABLE `tbl_lloc`
  MODIFY `lloc_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tbl_producte`
--
ALTER TABLE `tbl_producte`
  MODIFY `prod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `tbl_proveidor`
--
ALTER TABLE `tbl_proveidor`
  MODIFY `prov_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tbl_usuari`
--
ALTER TABLE `tbl_usuari`
  MODIFY `usuari_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
