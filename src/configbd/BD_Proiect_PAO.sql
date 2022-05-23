-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: proiect_pao_apl_bancara
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `adrese`
--

DROP TABLE IF EXISTS `adrese`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adrese` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stradă` varchar(100) NOT NULL,
  `număr` int NOT NULL,
  `oraș` varchar(20) NOT NULL,
  `cod_poștal` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adrese`
--

LOCK TABLES `adrese` WRITE;
/*!40000 ALTER TABLE `adrese` DISABLE KEYS */;
INSERT INTO `adrese` VALUES (1,'Luică',16,'București','040547'),(2,'Luică',17,'București','040547'),(3,'Luică',18,'București','040547');
/*!40000 ALTER TABLE `adrese` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carduri`
--

DROP TABLE IF EXISTS `carduri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carduri` (
  `număr` varchar(16) NOT NULL,
  `nume` varchar(100) NOT NULL,
  `CVV` varchar(3) NOT NULL,
  `dată_expirare` varchar(5) NOT NULL,
  `PIN` varchar(4) NOT NULL,
  `IBAN` varchar(24) NOT NULL,
  `nivel` varchar(8) DEFAULT NULL,
  `asigurarecălătorie` tinyint DEFAULT NULL,
  PRIMARY KEY (`număr`),
  KEY `fk_iban_idx` (`IBAN`),
  CONSTRAINT `fk_iban` FOREIGN KEY (`IBAN`) REFERENCES `conturi` (`IBAN`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carduri`
--

LOCK TABLES `carduri` WRITE;
/*!40000 ALTER TABLE `carduri` DISABLE KEYS */;
INSERT INTO `carduri` VALUES ('0610627605240214','Andrei Roman','654','05/27','6486','RO54RNCB7239889702487302','World',NULL),('1502570110054089','Andrei Roman','316','05/27','2035','RO54RNCB7239889702487302','Gold',NULL),('4794727679074721','Andrei Roman','789','05/27','1996','RO54RNCB7239889702487302',NULL,NULL),('6610286879653317','Andrei Roman','424','05/27','7684','RO54RNCB7239889702487302',NULL,NULL),('9428066299591072','Andrei Roman','913','05/27','3602','RO54RNCB7239889702487302',NULL,0);
/*!40000 ALTER TABLE `carduri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienți`
--

DROP TABLE IF EXISTS `clienți`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienți` (
  `CNP` varchar(13) NOT NULL,
  `nume` varchar(50) NOT NULL,
  `prenume` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nr_tel` varchar(10) NOT NULL,
  `id_adresă` int NOT NULL,
  PRIMARY KEY (`CNP`),
  KEY `fk_adresă_idx` (`id_adresă`),
  CONSTRAINT `fk_adresă` FOREIGN KEY (`id_adresă`) REFERENCES `adrese` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienți`
--

LOCK TABLES `clienți` WRITE;
/*!40000 ALTER TABLE `clienți` DISABLE KEYS */;
INSERT INTO `clienți` VALUES ('1257214891325','Avram','Ioana','ioana.avram@gmail.com','0771257346',3),('1268475128534','Roman','Andrei','andrei.roman@gmail.com','0785328794',1),('1278544247851','Solomon','Flavius','flavius.solomon@yahoo.com','0781235251',2);
/*!40000 ALTER TABLE `clienți` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conturi`
--

DROP TABLE IF EXISTS `conturi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conturi` (
  `IBAN` varchar(24) NOT NULL,
  `CNP_client` varchar(13) NOT NULL,
  `BIC` varchar(7) NOT NULL DEFAULT 'RNCROBU',
  `balanță` double NOT NULL DEFAULT '0',
  `dobândă` double DEFAULT NULL,
  `dată_început` varchar(10) DEFAULT NULL,
  `dată_final` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`IBAN`),
  KEY `fk_client_idx` (`CNP_client`),
  CONSTRAINT `fk_client` FOREIGN KEY (`CNP_client`) REFERENCES `clienți` (`CNP`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conturi`
--

LOCK TABLES `conturi` WRITE;
/*!40000 ALTER TABLE `conturi` DISABLE KEYS */;
INSERT INTO `conturi` VALUES ('RO04RNCB0241596372732941','1278544247851','RNCROBU',0,2,'19/05/2022','19/05/2023'),('RO09RNCB5505589091976400','1257214891325','RNCROBU',100,NULL,NULL,NULL),('RO26RNCB9193446398654008','1278544247851','RNCROBU',150,NULL,NULL,NULL),('RO54RNCB7239889702487302','1268475128534','RNCROBU',150,NULL,NULL,NULL),('RO86RNCB3994123639951674','1278544247851','RNCROBU',0,2,NULL,NULL);
/*!40000 ALTER TABLE `conturi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tranzacții`
--

DROP TABLE IF EXISTS `tranzacții`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tranzacții` (
  `tip` varchar(12) NOT NULL,
  `cont_sursă` varchar(24) NOT NULL,
  `cont_beneficiar` varchar(24) NOT NULL,
  `nume_beneficiar` varchar(100) NOT NULL,
  `detalii` varchar(200) DEFAULT NULL,
  `sumă` double NOT NULL,
  `dată` varchar(19) NOT NULL,
  KEY `fk_sursă_idx` (`cont_sursă`),
  KEY `fk_beneficiar_idx` (`cont_beneficiar`),
  CONSTRAINT `fk_beneficiar` FOREIGN KEY (`cont_beneficiar`) REFERENCES `conturi` (`IBAN`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_sursă` FOREIGN KEY (`cont_sursă`) REFERENCES `conturi` (`IBAN`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tranzacții`
--

LOCK TABLES `tranzacții` WRITE;
/*!40000 ALTER TABLE `tranzacții` DISABLE KEYS */;
INSERT INTO `tranzacții` VALUES ('interbanking','RO54RNCB7239889702487302','RO26RNCB9193446398654008','Solomon Flavius','',50,'23/05/2022 10:46:07'),('interbanking','RO54RNCB7239889702487302','RO09RNCB5505589091976400','Avram Ioana','',25,'23/05/2022 10:51:52'),('interbanking','RO54RNCB7239889702487302','RO26RNCB9193446398654008','Solomon Flavius','',50,'23/05/2022 17:47:19');
/*!40000 ALTER TABLE `tranzacții` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23 18:09:09
