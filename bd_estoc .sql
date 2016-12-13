-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-11-2016 a las 17:47:05
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `bd_estoc`
--
CREATE DATABASE IF NOT EXISTS `bd_estoc` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `bd_estoc`;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_lloc`
--
CREATE TABLE IF NOT EXISTS `tbl_lloc` (
  `lloc_id` int(11) NOT NULL AUTO_INCREMENT,
  `num_bloc` enum('Bloc 1','Bloc 2','Bloc3','Bloc 4','Bloc 5','Bloc 6','Bloc 7','Bloc 8') COLLATE utf8_unicode_ci NOT NULL,
  `num_passadis` enum('passadis 1','passadis 2','passadis 3','passadis 4','passadis 5','passadis 6','passadis 7','passadis 8','passadis 9','passadis 10','passadis 11','passadis 12') COLLATE utf8_unicode_ci NOT NULL,
  `num_lleixa` enum('lleixa 1','lleixa 2','lleixa 3','lleixa 4','lleixa 5','lleixa 6','lleixa 7','lleixa 8') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`lloc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_estoc`
--
CREATE TABLE IF NOT EXISTS `tbl_estoc` (
  `estoc_id` int(11) NOT NULL AUTO_INCREMENT,
  `estoc_q_max` int(5) NOT NULL,
  `estoc_q_min` int(5) NOT NULL,
  `prod_id` int(11)  NULL,
  `lloc_id` int(11)  NULL,
  PRIMARY KEY (`estoc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_proveidor`
--
CREATE TABLE IF NOT EXISTS `tbl_proveidor` (
  `prov_id` int(11) NOT NULL AUTO_INCREMENT,
  `prov_nom` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `prov_adre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prov_email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`prov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_categoria`
--
CREATE TABLE IF NOT EXISTS `tbl_categoria` (
  `categoria_id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria_nom` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`categoria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_serie`
--
CREATE TABLE IF NOT EXISTS `tbl_serie` (
  `serie_id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_nom` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `categoria_id` int(11) NULL,
  PRIMARY KEY (`serie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_producte`
--
CREATE TABLE IF NOT EXISTS `tbl_producte` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prod_foto` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `serie_id` int(11) NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_comanda`
--
CREATE TABLE IF NOT EXISTS `tbl_comanda` (
  `comanda_id` int(11) NOT NULL AUTO_INCREMENT,
  `comanda_quantitat` int(5) NOT NULL,
  `comanda_data` date NOT NULL,
  `prov_id` int(11)  NULL,
  PRIMARY KEY (`comanda_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tbl_detall_comanda`
--
CREATE TABLE IF NOT EXISTS `tbl_detall_com` (
  `detall_com_id` int(11) NOT NULL AUTO_INCREMENT,
  `comanda_id` int(11) NOT NULL,
  `prod_id` int(11)  NULL,
  PRIMARY KEY (`detall_com_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



-- -------------------------------------------------------
-- RELACIONES PARA LAS TABLAS 
-- ---------------------------------------------------

-- `tbl_serie` `tbl_categoria`:
ALTER TABLE `tbl_serie` ADD CONSTRAINT `FK_serie_categoria` FOREIGN KEY (categoria_id)
REFERENCES `tbl_categoria`(categoria_id);
-- ---------------------------------------------------

-- `tbl_producte`  `tbl_serie`:
ALTER TABLE `tbl_producte` ADD CONSTRAINT `FK_prod_serie` FOREIGN KEY (serie_id)
REFERENCES `tbl_serie`(serie_id);
-- ---------------------------------------------------

-- `tbl_detall_comanda` `tbl_producte`:
ALTER TABLE `tbl_detall_com` ADD CONSTRAINT `FK_detall_com_prod` FOREIGN KEY (prod_id)
REFERENCES `tbl_producte`(prod_id);
-- ---------------------------------------------------

-- `tbl_detall_comanda` `tbl_comanda`:
ALTER TABLE `tbl_detall_com` ADD CONSTRAINT `FK_detall_com_comanda` FOREIGN KEY (comanda_id)
REFERENCES `tbl_comanda`(comanda_id);
-- ---------------------------------------------------

-- `tbl_comanda` `tbl_proveidor`:
ALTER TABLE `tbl_comanda` ADD CONSTRAINT `FK_comanda_prov` FOREIGN KEY (prov_id)
REFERENCES `tbl_proveidor`(prov_id);
-- ---------------------------------------------------

-- `tbl_estoc` `tbl_producte`:
ALTER TABLE `tbl_estoc` ADD CONSTRAINT `FK_estoc_prod` FOREIGN KEY (prod_id)
REFERENCES `tbl_producte`(prod_id);
-- ---------------------------------------------------

-- `tbl_estoc `tbl_lloc`:
ALTER TABLE `tbl_estoc` ADD CONSTRAINT `FK_estoc_lloc` FOREIGN KEY (lloc_id)
REFERENCES `tbl_lloc`(lloc_id);
-- ---------------------------------------------------


