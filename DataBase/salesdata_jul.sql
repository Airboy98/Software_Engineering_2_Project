-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: salesdata
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `jul`
--

DROP TABLE IF EXISTS `jul`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jul` (
  `DayOfMonth` varchar(45) NOT NULL,
  `AvgGrossSales` double NOT NULL,
  PRIMARY KEY (`DayOfMonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jul`
--

LOCK TABLES `jul` WRITE;
/*!40000 ALTER TABLE `jul` DISABLE KEYS */;
INSERT INTO `jul` VALUES ('1fri',608.75),('1mon',320.91666),('1sat',423.6875),('1sun',41.208332),('1thu',172.35938),('1tue',71.703125),('1wed',192.4375),('2fri',63.390625),('2mon',71.703125),('2sat',316.32812),('2sun',88.859375),('2thu',55.609375),('2tue',172.35938),('2wed',423.6875),('3fri',45.53125),('3mon',192.4375),('3sat',158.9375),('3sun',61.2875),('3thu',316.32812),('3tue',423.6875),('3wed',100.328125),('4fri',422.42188),('4mon',172.35938),('4sat',91.265625),('4sun',55.375),('4thu',82.09375),('4tue',55.609375),('4wed',316.32812),('5fri',146.375),('5mon',608.75),('5sat',320.25),('5sun',299.875),('5thu',422.42188),('5tue',63.390625),('5wed',45.53125);
/*!40000 ALTER TABLE `jul` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-30 18:21:28
