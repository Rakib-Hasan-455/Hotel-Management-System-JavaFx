-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: HMS0
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admininfo`
--

DROP TABLE IF EXISTS `admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admininfo` (
  `NID` varchar(25) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admininfo`
--

LOCK TABLES `admininfo` WRITE;
/*!40000 ALTER TABLE `admininfo` DISABLE KEYS */;
INSERT INTO `admininfo` VALUES ('123','admin','admin'),('admin','admin','admin'),('root','admin','admin');
/*!40000 ALTER TABLE `admininfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkinoutinfo`
--

DROP TABLE IF EXISTS `checkinoutinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkinoutinfo` (
  `SI_NO` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(30) DEFAULT NULL,
  `NID` varchar(15) DEFAULT NULL,
  `ROOMNO` varchar(15) DEFAULT NULL,
  `ROOMTYPE` varchar(15) DEFAULT NULL,
  `CAPACITY` varchar(15) DEFAULT NULL,
  `CHECKEDIN` varchar(20) DEFAULT NULL,
  `CHECKEDOUT` varchar(20) DEFAULT NULL,
  `PRICEDAY` varchar(30) DEFAULT NULL,
  `TOTALDAYS` varchar(30) DEFAULT NULL,
  `TOTALPRICE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`SI_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkinoutinfo`
--

LOCK TABLES `checkinoutinfo` WRITE;
/*!40000 ALTER TABLE `checkinoutinfo` DISABLE KEYS */;
INSERT INTO `checkinoutinfo` VALUES (16,'3','3','3','3','3','12','12','12','2021-12-06','2021-12-06','12','1','12'),(17,'2','2','2','2','2','9','9','9','2021-12-06','2021-12-06','9','1','9'),(18,'4','4','4','4','4','11','Non-Ac','Double','2021-12-06','2021-12-16','500','11','5500'),(19,'8','8','8','8','8','11','Non-Ac','Double','2021-12-06','2021-12-07','500','2','1000'),(20,'3','3','3','3','3','11','Non-Ac','Double','2021-12-06','2021-12-06','500','1','500'),(21,'2','3','2','2','2','13','Ac','12','2020-12-01','2020-12-31','12','31','372'),(22,'2','3','2','2','2','13','Ac','12','2020-09-01','2020-11-30','12','91','1092'),(23,'2','3','2','2','2','13','Ac','12','2013-07-01','2021-11-30','12','155','1860'),(24,'23','3','2','2','2','13','Ac','12','2021-12-06','2021-12-19','12','22','4884'),(25,'Md. Mursalin','mursa@gamil.com','015555','Dhaka, Bangladesh','mursalin','1','AC','Single','2021-12-01','2021-12-10','1500','10','15000'),(26,'Md. Mursalin','mursa@gamil.com','015555','Dhaka, Bangladesh','mursalin','11','Non-Ac','Double','2021-12-02','2021-12-19','500','22','4884'),(27,'mursalin','mursalin@gmail.com','mursalin','mursalin','mursalin','111','AC','Double','2021-11-30','2021-12-18','1000','19','19000'),(28,'mursalin','mursalin@gmail.com','mursalin','mursalin','mursalin','2','AC-Room','Double','2021-11-28','2021-12-08','2000','11','22000'),(29,'1','1','1','1','1','1','AC','Single','2021-11-29','2021-12-17','1500','19','28500'),(30,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','1','AC','Single','2021-12-17','2021-12-19','1500','22','4884'),(31,'1','1','1','1','1','111','AC','Double','2021-11-28','2021-12-19','1000','22','4884'),(32,'4','4','4','4','4','12','12','12','2021-12-18','2021-12-19','12','22','4884'),(33,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','123','1222','222','2021-11-30','2021-12-25','222','26','5772'),(34,'1','1','1111','1','1','123','1222','222','2021-11-28','2021-12-19','222','22','4884'),(35,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','1','AC','Single','2021-11-29',NULL,'1500',NULL,NULL),(36,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','11','Non-Ac','Double','2021-11-29',NULL,'500',NULL,NULL),(37,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','12','12','12','2021-11-29',NULL,'12',NULL,NULL),(38,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','111','AC','Double','2021-12-19',NULL,'1000',NULL,NULL),(39,'1','1','1111','1','1','123','1222','222','2021-12-19',NULL,'222',NULL,NULL);
/*!40000 ALTER TABLE `checkinoutinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerinfo`
--

DROP TABLE IF EXISTS `customerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerinfo` (
  `NAME` varchar(30) DEFAULT NULL,
  `NID` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerinfo`
--

LOCK TABLES `customerinfo` WRITE;
/*!40000 ALTER TABLE `customerinfo` DISABLE KEYS */;
INSERT INTO `customerinfo` VALUES ('123','1','1','1','1111','1'),('4','4','4','4','4','4'),('a','a','a','a','a','a'),('mursalin','mursalin','mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh');
/*!40000 ALTER TABLE `customerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeinfo`
--

DROP TABLE IF EXISTS `employeeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeeinfo` (
  `NAME` varchar(30) DEFAULT NULL,
  `NID` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeinfo`
--

LOCK TABLES `employeeinfo` WRITE;
/*!40000 ALTER TABLE `employeeinfo` DISABLE KEYS */;
INSERT INTO `employeeinfo` VALUES ('1','1','1','1','1','1'),('123','111','1111','111','111','111'),('2','2','2','2','2','2'),('3','3','3','3','3','3'),('Md. Mursalin','mursalin','mursalin','mur@gmail.com','dhaka, bangla','01222222'),('rakib','rakib','rakib','hasan@gmail.com','dhaka','012323');
/*!40000 ALTER TABLE `employeeinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roominfo`
--

DROP TABLE IF EXISTS `roominfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roominfo` (
  `ROOM_NO` varchar(30) NOT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  `CAPACITY` varchar(10) DEFAULT NULL,
  `PRICE_DAY` varchar(30) DEFAULT NULL,
  `STATUS` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`ROOM_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roominfo`
--

LOCK TABLES `roominfo` WRITE;
/*!40000 ALTER TABLE `roominfo` DISABLE KEYS */;
INSERT INTO `roominfo` VALUES ('1','AC','Single','1500','Booked'),('11','Non-Ac','Double','500','Booked'),('111','AC','Double','1000','Booked'),('12','12','12','12','Booked'),('123','1222','222','222','Booked'),('13','Ac','12','12','Available'),('2','AC-Room','Double','2000','Available'),('3','AC','Double','600','Available'),('9','9','9','9','Available');
/*!40000 ALTER TABLE `roominfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-26 14:14:02
