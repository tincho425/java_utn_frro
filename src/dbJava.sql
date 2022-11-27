-- MySQL dump 10.15  Distrib 10.0.38-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: martin-java_callcenter
-- ------------------------------------------------------
-- Server version       10.0.38-MariaDB-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS `martin-java_callcenter`;

--
-- Current Database: `martin-java_callcenter`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `martin-java_callcenter` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `martin-java_callcenter`;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_doc` varchar(10) NOT NULL,
  `nro_doc` varchar(45) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `habilitado` tinyint(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'dni','10101010','Juan','Perez','jp@gmail.com','4101010',1,'jperez',DEFAULT),(2,'dni','12121212','John','Doe','contacto@jd','4121212',0,'jdoe',DEFAULT),(3,'dni','13131313','Nadie','Sabe','ns@ns.com','4131313',1,'nsabe',DEFAULT),(4,'cuit','14141414141','Identidad','Desconocida','unknown@gmail.com','4141414',0,'idesconocida',DEFAULT),(5,'cuit','15151515151','Alguien','Más','am@gmail.com','4151515',1,'amas',DEFAULT),(6,'dni','16161616','Otra','Persona','op@gmail.com','4161616',0,'opersona',DEFAULT);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `cliente`
--
DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
	`dni` INT(8) NOT NULL,
	`apellido` varchar(255) NOT NULL,
	`nombre` varchar(255) NOT NULL,
	`CUIT` INT(11) NOT NULL,
	`telefono` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	PRIMARY KEY (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (39120610,'ballestero','martin',20391206105,'+543416295949','martin.ballestero@hotmail.com'),(39120611,'perez','ricardo',20391206115,'+543416295959','perez.ricardo@hotmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin'),(2,'agent');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `rol_usuario_rol_fk` (`id_rol`),
  CONSTRAINT `rol_usuario_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `rol_usuario_rol_fk` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_usuario`
--

LOCK TABLES `rol_usuario` WRITE;
/*!40000 ALTER TABLE `rol_usuario` DISABLE KEYS */;
INSERT INTO `rol_usuario` VALUES (1,1),(2,2),(3,1),(3,2),(4,2),(5,2),(6,2);
/*!40000 ALTER TABLE `rol_usuario` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `llamada`
--
DROP TABLE IF EXISTS `llamada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `llamada` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`timestamp_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`timestamp_ended` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`remitente` varchar(255) DEFAULT NULL,
	`nota` varchar(255) DEFAULT NULL,
	`id_usuario` int(11) NOT NULL,
	`id_cliente` INT(8) DEFAULT NULL,
	`eliminado` BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (`id`),
	CONSTRAINT `llamada_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
	CONSTRAINT `llamada_cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `llamada`
--

LOCK TABLES `llamada` WRITE;
/*!40000 ALTER TABLE `llamada` DISABLE KEYS */;
INSERT INTO `llamada` (id, id_usuario, id_cliente, remitente) VALUES (1, 1, 39120610, NULL), (2, 1, 39120610, NULL), (3, 2, 39120611, NULL),
(4, 2, 39120610, NULL), (5, 3, 39120611, NULL), (6, 3, 39120611, NULL), (7, 3, 39120610, NULL), (8, 3, 39120611, NULL), (9, 3, NULL, '+543416295949'), (10, 1, NULL, '+543415203923');
/*!40000 ALTER TABLE `llamada` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `servicio`
--
DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicio` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nombre` varchar(255) NOT NULL,
	`descripcion` varchar(255) DEFAULT NULL,
	`eliminado` BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` (id, nombre, descripcion, eliminado) VALUES (1, "Fibra óptica - 100MB","El servicio brinda acceso a internet con velocidades de subida y bajada paralelas de 100MB", 0),
(2, "Fibra óptica - 200MB", "El servicio brinda acceso a internet con velocidades de subida y bajada paralelas de 200MB", 0);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `llamada_servicio`
--

DROP TABLE IF EXISTS `llamada_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `llamada_servicio` (
  `id_llamada` INT NOT NULL,
  `id_servicio` INT NOT NULL,
  PRIMARY KEY (`id_llamada`,`id_servicio`),
  KEY `llamada_servicio_llamada_fk` (`id_llamada`),
  CONSTRAINT `llamada_servicio_llamada_fk` FOREIGN KEY (`id_llamada`) REFERENCES `llamada` (`id`),
  CONSTRAINT `llamada_servicio_servicio_fk` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `llamada_servicio`
--

LOCK TABLES `llamada_servicio` WRITE;
/*!40000 ALTER TABLE `llamada_servicio` DISABLE KEYS */;
INSERT INTO `llamada_servicio` VALUES (1,1),(2,2),(3,1),(3,2),(4,2),(5,2),(6,2);
/*!40000 ALTER TABLE `llamada_servicio` ENABLE KEYS */;
UNLOCK TABLES;



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;