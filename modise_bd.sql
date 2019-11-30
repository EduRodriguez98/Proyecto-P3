-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: modise_schema
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `camisetas`
--

DROP TABLE IF EXISTS `camisetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camisetas` (
  `idcamisetas` int(11) NOT NULL,
  `idprendas` int(11) NOT NULL,
  `logotipo` varchar(45) DEFAULT NULL,
  `rayas` tinyint(4) DEFAULT NULL,
  `cuadros` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idcamisetas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camisetas`
--

LOCK TABLES `camisetas` WRITE;
/*!40000 ALTER TABLE `camisetas` DISABLE KEYS */;
INSERT INTO `camisetas` VALUES (1,1,'lacoste',0,0),(2,1,'lacoste',1,0),(3,1,'zara',0,1),(4,1,'h&m',0,0),(5,1,'vitorioylucino',0,1);
/*!40000 ALTER TABLE `camisetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chaquetas`
--

DROP TABLE IF EXISTS `chaquetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chaquetas` (
  `idchaquetas` int(11) NOT NULL,
  `idprendas` int(11) NOT NULL,
  `larga` tinyint(4) DEFAULT NULL,
  `lisa` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idchaquetas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chaquetas`
--

LOCK TABLES `chaquetas` WRITE;
/*!40000 ALTER TABLE `chaquetas` DISABLE KEYS */;
INSERT INTO `chaquetas` VALUES (1,2,1,1),(2,2,0,1),(3,2,0,0),(4,2,1,0),(5,2,1,1);
/*!40000 ALTER TABLE `chaquetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `idcolor` int(11) NOT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcolor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'rojo'),(2,'azul'),(3,'amarillo'),(4,'verde'),(5,'negro');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gorros`
--

DROP TABLE IF EXISTS `gorros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gorros` (
  `idgorros` int(11) NOT NULL,
  `idprendas` int(11) NOT NULL,
  `verano` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idgorros`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gorros`
--

LOCK TABLES `gorros` WRITE;
/*!40000 ALTER TABLE `gorros` DISABLE KEYS */;
INSERT INTO `gorros` VALUES (1,3,1),(2,3,0),(3,3,1),(4,3,0),(5,3,0);
/*!40000 ALTER TABLE `gorros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outfit`
--

DROP TABLE IF EXISTS `outfit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outfit` (
  `idoutfit` int(11) NOT NULL AUTO_INCREMENT,
  `imagen_OF` longblob,
  PRIMARY KEY (`idoutfit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outfit`
--

LOCK TABLES `outfit` WRITE;
/*!40000 ALTER TABLE `outfit` DISABLE KEYS */;
/*!40000 ALTER TABLE `outfit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pantalones`
--

DROP TABLE IF EXISTS `pantalones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pantalones` (
  `idpantalones` int(11) NOT NULL,
  `idprendas` int(11) NOT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `corto` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idpantalones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantalones`
--

LOCK TABLES `pantalones` WRITE;
/*!40000 ALTER TABLE `pantalones` DISABLE KEYS */;
INSERT INTO `pantalones` VALUES (1,4,'zara',0),(2,4,'berskha',0),(3,4,'zara',1),(4,4,'bersache',0),(5,4,'h&m',1);
/*!40000 ALTER TABLE `pantalones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prendas`
--

DROP TABLE IF EXISTS `prendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prendas` (
  `idprendas` int(11) NOT NULL,
  `id_color` int(11) NOT NULL,
  PRIMARY KEY (`idprendas`),
  KEY `idcolor_idx` (`id_color`),
  CONSTRAINT `idcolor` FOREIGN KEY (`id_color`) REFERENCES `color` (`idcolor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prendas`
--

LOCK TABLES `prendas` WRITE;
/*!40000 ALTER TABLE `prendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `prendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usu_cami`
--

DROP TABLE IF EXISTS `usu_cami`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usu_cami` (
  `idusuario` int(11) NOT NULL,
  `idcamisetas` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  KEY `idcamisetas_idx` (`idcamisetas`),
  KEY `idusuario_idx` (`idusuario`),
  CONSTRAINT `idcamisetas` FOREIGN KEY (`idcamisetas`) REFERENCES `camisetas` (`idcamisetas`) ON DELETE CASCADE,
  CONSTRAINT `idusuario_cami` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usu_cami`
--

LOCK TABLES `usu_cami` WRITE;
/*!40000 ALTER TABLE `usu_cami` DISABLE KEYS */;
INSERT INTO `usu_cami` VALUES (1,1,'2007-05-20'),(1,2,'2022-05-20'),(2,4,'2007-07-20'),(3,5,'2007-11-20'),(4,2,'2007-05-20');
/*!40000 ALTER TABLE `usu_cami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usu_cha`
--

DROP TABLE IF EXISTS `usu_cha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usu_cha` (
  `idusuario` int(11) NOT NULL,
  `idchaquetas` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  KEY `idusuario_idx` (`idusuario`),
  KEY `idchaquetas_idx` (`idchaquetas`),
  CONSTRAINT `idchaquetas` FOREIGN KEY (`idchaquetas`) REFERENCES `chaquetas` (`idchaquetas`) ON DELETE CASCADE,
  CONSTRAINT `idusuario_cha` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usu_cha`
--

LOCK TABLES `usu_cha` WRITE;
/*!40000 ALTER TABLE `usu_cha` DISABLE KEYS */;
INSERT INTO `usu_cha` VALUES (10,1,'2014-06-20'),(8,2,'2014-06-20'),(1,3,'2015-06-20'),(5,4,'2017-06-20'),(7,5,'2014-06-20');
/*!40000 ALTER TABLE `usu_cha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usu_gor`
--

DROP TABLE IF EXISTS `usu_gor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usu_gor` (
  `idusuario` int(11) NOT NULL,
  `idgorros` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  KEY `idusuario_gor_idx` (`idusuario`),
  KEY `idgorros_idx` (`idgorros`),
  CONSTRAINT `idgorros` FOREIGN KEY (`idgorros`) REFERENCES `gorros` (`idgorros`) ON DELETE CASCADE,
  CONSTRAINT `idusuario_gor` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usu_gor`
--

LOCK TABLES `usu_gor` WRITE;
/*!40000 ALTER TABLE `usu_gor` DISABLE KEYS */;
INSERT INTO `usu_gor` VALUES (2,1,'2014-06-20'),(3,1,'2014-06-20'),(4,2,'2014-04-20'),(10,3,'2014-12-20'),(9,3,'2014-11-20');
/*!40000 ALTER TABLE `usu_gor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usu_pan`
--

DROP TABLE IF EXISTS `usu_pan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usu_pan` (
  `idusuario` int(11) NOT NULL,
  `idpantalones` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  KEY `idusuario_pan_idx` (`idusuario`),
  KEY `idpantalones_idx` (`idpantalones`),
  CONSTRAINT `idpantalones` FOREIGN KEY (`idpantalones`) REFERENCES `pantalones` (`idpantalones`) ON DELETE CASCADE,
  CONSTRAINT `idusuario_pan` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usu_pan`
--

LOCK TABLES `usu_pan` WRITE;
/*!40000 ALTER TABLE `usu_pan` DISABLE KEYS */;
INSERT INTO `usu_pan` VALUES (1,1,'2014-06-20'),(2,3,'2014-06-20'),(6,4,'2014-06-20'),(9,5,'2014-06-20'),(4,1,'2014-06-20');
/*!40000 ALTER TABLE `usu_pan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usu_zap`
--

DROP TABLE IF EXISTS `usu_zap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usu_zap` (
  `idusuario` int(11) NOT NULL,
  `idzapatos` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  KEY `idusuario_idx` (`idusuario`),
  KEY `idzapatos_idx` (`idzapatos`),
  CONSTRAINT `idusuario_zap` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE,
  CONSTRAINT `idzapatos` FOREIGN KEY (`idzapatos`) REFERENCES `zapatos` (`idzapatos`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usu_zap`
--

LOCK TABLES `usu_zap` WRITE;
/*!40000 ALTER TABLE `usu_zap` DISABLE KEYS */;
INSERT INTO `usu_zap` VALUES (1,1,'2014-06-20'),(3,2,'2014-07-20'),(2,3,'2014-08-20'),(4,4,'2014-09-20'),(8,5,'2014-01-20');
/*!40000 ALTER TABLE `usu_zap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nom_usuario` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `administrador` tinyint(4) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `contrasena` varchar(45) NOT NULL,
  `genero` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'eneko','enekop@gmail.com',1,20,'prog',1),(2,'eduj','edudor@gmail.com',1,20,'clash',1),(3,'acvelap','acvelap@gmail.com',1,28,'lol',1),(4,'laura','lauram@gmail.com',0,21,'123',0),(5,'barbara','barbarae@gmail.com',0,19,'456',0),(6,'francisco','francis@gmail.com',0,24,'789',1),(7,'paula','paulaf@gmail.com',0,45,'987',0),(8,'ernesto','ernestos@gmail.com',0,18,'654',1),(9,'marta','martasa@gmail.com',0,24,'321',0),(10,'maripili','mpili@gmail.com',0,20,'abc',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zapatos`
--

DROP TABLE IF EXISTS `zapatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zapatos` (
  `idzapatos` int(11) NOT NULL,
  `idprendas` int(11) NOT NULL,
  `deportivos` tinyint(4) DEFAULT NULL,
  `deVestir` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idzapatos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zapatos`
--

LOCK TABLES `zapatos` WRITE;
/*!40000 ALTER TABLE `zapatos` DISABLE KEYS */;
INSERT INTO `zapatos` VALUES (1,5,1,1),(2,5,1,0),(3,5,1,0),(4,5,0,1),(5,5,0,1);
/*!40000 ALTER TABLE `zapatos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-25 11:14:29