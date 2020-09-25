CREATE DATABASE  IF NOT EXISTS `testspring5` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `testspring5`;
-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: testspring5
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `arbitro`
--

DROP TABLE IF EXISTS `arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arbitro` (
  `ar_id` int(11) NOT NULL AUTO_INCREMENT,
  `ar_nombre` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `ar_fecnac` date NOT NULL,
  PRIMARY KEY (`ar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbitro`
--

LOCK TABLES `arbitro` WRITE;
/*!40000 ALTER TABLE `arbitro` DISABLE KEYS */;
INSERT INTO `arbitro` VALUES (1,'José Miguez Blanco','1990-02-11'),(2,'Juan Manuel  De la Barca Torrecilla','1991-07-22'),(3,'Martín Méndez Castañeda','1990-06-06'),(4,'Pedro Romo Morelos','1993-03-21');
/*!40000 ALTER TABLE `arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `eq_id` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `eq_nombre` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `eq_apodo` varchar(45) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `eq_patrocinador` varchar(45) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `eq_jj` int(10) unsigned DEFAULT NULL,
  `eq_jg` int(10) unsigned DEFAULT NULL,
  `eq_je` int(10) unsigned DEFAULT NULL,
  `eq_jp` int(10) unsigned DEFAULT NULL,
  `eq_puntos` int(10) unsigned DEFAULT NULL,
  `eq_entrenador` varchar(45) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`eq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES ('Chihuahua','Atlético Chihuahua','Alces',NULL,3,1,0,2,1,'Javier Bernal Montesinos'),('Monterrey','Monterrey AC','Toros Regios',NULL,3,1,1,1,4,'Norma De la Barrera García'),('PoliGuinda','Politécnico Guinda','Burros blancos',NULL,2,2,0,0,6,'Ezequiel Montes Avelar'),('UnamOro','UNAM ','Pumas','Carso',2,1,1,0,4,'Martín Aburto Jara');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador` (
  `jug_id` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `jug_nombre` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `jug_numero` int(11) NOT NULL,
  `jug_posicion` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `jug_fecnac` date NOT NULL,
  `jug_lesionado` tinyint(1) NOT NULL,
  `jug_suspendido` tinyint(1) NOT NULL,
  `jug_titular` tinyint(1) NOT NULL,
  `jug_id_eq` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`jug_id`),
  KEY `idx` (`jug_id_eq`),
  CONSTRAINT `fk_equipo` FOREIGN KEY (`jug_id_eq`) REFERENCES `equipo` (`eq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES ('9709091','José Luis Chavero Díaz',2,'Defensa','1999-07-12',1,0,1,'Monterrey'),('9800001','Javier Loza Santero',4,'Defensa','2001-01-22',0,0,1,'Monterrey'),('9902301','Alfonso Marini Aquelarre',4,'Defensa','1999-02-04',0,0,1,'Chihuahua'),('9902305','Manuel De la Cruz Mora',6,'Medio','2000-12-12',0,0,1,'Chihuahua'),('9902349','Martín Abarca Lima',17,'Delantero','1997-05-06',0,0,0,'Monterrey'),('9906201','Jaime Espinoza Espinoza',1,'Portero','2001-11-11',0,0,0,'Chihuahua');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-25 14:51:04
