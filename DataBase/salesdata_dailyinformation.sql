-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: salesdata
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `dailyinformation`
--

DROP TABLE IF EXISTS `dailyinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailyinformation` (
  `Date` varchar(45) NOT NULL,
  `DayOfWeek` varchar(45) DEFAULT NULL,
  `DayOfYearByWeek` varchar(45) DEFAULT NULL,
  `DayOfMonth` varchar(45) DEFAULT NULL,
  `GrossSales` double DEFAULT NULL,
  PRIMARY KEY (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyinformation`
--

LOCK TABLES `dailyinformation` WRITE;
/*!40000 ALTER TABLE `dailyinformation` DISABLE KEYS */;
INSERT INTO `dailyinformation` VALUES ('01/25/2018','Thu','4','4thu',2145.5),('01/26/2018','Fri','4','4fri',6007),('01/27/2018','Sat','4','4sat',450),('01/28/2018','Sun','4','4sun',690),('01/29/2018','Mon','5','5mon',850),('05/01/2017','Mon','5','1mon',917.5),('05/02/2017','Tue','5','1tue',1374.25),('05/03/2017','Wed','5','1wed',1011.75),('05/04/2017','Thu','5','1thu',2780),('05/05/2017','Fri','5','1fri',11965),('05/06/2017','Sat','5','1sat',5986.5),('05/07/2017','Sun','10','2sun',505.75),('05/08/2017','Mon','10','2mon',735.25),('05/09/2017','Tue','10','2tue',2379.75),('05/10/2017','Wed','10','2wed',1533.25),('05/11/2017','Thu','10','2thu',2654.5),('05/12/2017','Fri','10','2fri',8025.5),('05/13/2017','Sat','10','2sat',9439.5),('05/14/2017','Sun','15','3sun',425.75),('05/15/2017','Mon','15','3mon',1067.25),('05/16/2017','Tue','15','3tue',2213.5),('05/17/2017','Wed','15','3wed',1441.75),('05/18/2017','Thu','15','3thu',3823.5),('05/19/2017','Fri','15','3fri',9084.25),('05/20/2017','Sat','15','3sat',12304.5),('05/21/2017','Sun','15','3sun',974.25),('05/22/2017','Mon','20','4mon',783.25),('05/23/2017','Tue','20','4tue',1608.5),('05/24/2017','Wed','20','4wed',1551.25),('05/25/2017','Thu','20','4thu',3863),('05/26/2017','Fri','20','4fri',5633.25),('05/27/2017','Sat','20','4sat',8130.25),('05/28/2017','Sun','20','4sun',1990),('05/29/2017','Mon','25','5mon',0),('05/30/2017','Tue','25','5tue',1281),('05/31/2017','Wed','25','5wed',859.5),('06/01/2017','Thu','6','1thu',2861),('06/02/2017','Fri','6','1fri',6723.25),('06/03/2017','Sat','6','1sat',6140.5),('06/04/2017','Sun','6','1sun',794.25),('06/05/2017','Mon','6','1mon',828.5),('06/06/2017','Tue','6','1tue',1447.75),('06/07/2017','Wed','12','2wed',1306),('06/08/2017','Thu','12','2thu',3809.75),('06/09/2017','Fri','12','2fri',5613),('06/10/2017','Sat','12','2sat',7295.25),('06/11/2017','Sun','12','2sun',611.75),('06/12/2017','Mon','12','2mon',908.75),('06/13/2017','Tue','12','2tue',2057.25),('06/14/2017','Wed','18','3wed',1362),('06/15/2017','Thu','18','3thu',2784.75),('06/16/2017','Fri','18','3fri',6652),('06/17/2017','Sat','18','3sat',5674.5),('06/18/2017','Sun','18','3sun',339.25),('06/19/2017','Mon','18','3mon',1382),('06/20/2017','Tue','18','3tue',1728.75),('06/21/2017','Wed','18','3wed',1629.25),('06/22/2017','Thu','24','4thu',3350.75),('06/23/2017','Fri','24','4fri',6688.25),('06/24/2017','Sat','24','4sat',7584.75),('06/24/2018','Wed','24','4wed',1197.5),('06/25/2017','Sun','24','4sun',1045.5),('06/26/2017','Mon','24','4mon',586.25),('06/27/2017','Tue','24','4tue',1591),('06/28/2017','Wed','24','4wed',1333.5),('06/29/2017','Thu','30','5thu',5101.75),('06/30/2017','Fri','30','5fri',4714.5),('07/01/2017','Sat','7','1sat',5737),('07/02/2017','Sun','7','1sun',1486.25),('07/03/2017','Mon','7','1mon',3851),('07/04/2017','Tue','7','1tue',1963.25),('07/05/2017','Wed','7','1wed',1194),('07/06/2017','Thu','7','1thu',2435),('07/07/2017','Fri','14','2fri',5361.25),('07/08/2017','Sat','14','2sat',7388),('07/09/2017','Sun','14','2sun',548.75),('07/10/2017','Mon','14','2mon',1203.75),('07/11/2017','Tue','14','2tue',2206.25),('07/12/2017','Wed','14','2wed',1174.75),('07/13/2017','Thu','14','2thu',2836.25),('07/14/2017','Fri','21','3fri',5639.25),('07/15/2017','Sat','21','3sat',7289.25),('07/16/2017','Sun','21','3sun',788.75),('07/17/2017','Mon','21','3mon',688.25),('07/18/2017','Tue','21','3tue',1279),('07/19/2017','Wed','21','3wed',1508.25),('07/20/2017','Thu','21','3thu',2660),('07/21/2017','Fri','21','3fri',5609),('07/22/2017','Sat','28','4sat',7011.25),('07/23/2017','Sun','28','4sun',886),('07/24/2017','Mon','28','4mon',1207.5),('07/25/2017','Tue','28','4tue',1478),('07/26/2017','Wed','28','4wed',1460.25),('07/27/2017','Thu','28','4thu',2342),('07/28/2017','Fri','28','4fri',4506.25),('07/29/2017','Sat','35','5sat',7488),('07/30/2017','Sun','35','5sun',1199.5),('07/31/2017','Mon','35','5mon',1111.25),('08/01/2017','Tue','8','1tue',1147.25),('08/02/2017','Wed','8','1wed',2309.25),('08/03/2017','Thu','8','1thu',2757.75),('08/04/2017','Fri','8','1fri',7305),('08/05/2017','Sat','8','1sat',6779),('08/06/2017','Sun','8','1sun',494.5),('08/07/2017','Mon','16','2mon',889.75),('08/08/2017','Tue','16','2tue',1605.25),('08/09/2017','Wed','16','2wed',1014.25),('08/10/2017','Thu','16','2thu',2424.5),('08/11/2017','Fri','16','2fri',5061.25),('08/12/2017','Sat','16','2sat',11531.5),('08/13/2017','Sun','16','2sun',1421.75),('08/14/2017','Mon','24','3mon',728.5),('08/15/2017','Tue','24','3tue',1313.5),('08/16/2017','Wed','24','3wed',1392.5),('08/17/2017','Thu','24','3thu',2543),('08/18/2017','Fri','24','3fri',4899),('08/19/2017','Sat','24','3sat',6758.75),('08/20/2017','Sun','24','3sun',1225.75);
/*!40000 ALTER TABLE `dailyinformation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

<<<<<<< HEAD
-- Dump completed on 2018-03-30 18:21:28
=======
-- Dump completed on 2018-03-30 18:08:30
>>>>>>> a99e72ab668a1dee9cf1d11ab5e1e30283730bc5
