-- MySQL dump 10.14  Distrib 5.5.68-MariaDB, for Linux (x86_64)
--
-- Host: medicmagic-server.cdjty7ay8ee6.ap-northeast-2.rds.amazonaws.com    Database: medicmagic_server
-- ------------------------------------------------------
-- Server version	10.2.21-MariaDB-log

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aelim','aelim','aelim','2000-04-09',22),('mg','mg','mg','2000-06-17',200),('sl','sl','sl','1999-05-29',23),('sr','sr','sr','2000-05-21',22);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userBirthControlPills`
--

DROP TABLE IF EXISTS `userBirthControlPills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userBirthControlPills` (
  `id` varchar(20) NOT NULL,
  `pillsTime` time DEFAULT NULL,
  `pillsDate` date DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userBirthControlPills`
--

LOCK TABLES `userBirthControlPills` WRITE;
/*!40000 ALTER TABLE `userBirthControlPills` DISABLE KEYS */;
INSERT INTO `userBirthControlPills` VALUES ('aelim','14:59:00','2021-03-25',21),('mg','23:47:00','2021-05-27',21),('sl','08:00:00','2021-05-27',21),('sr','19:07:00','2021-05-30',21);
/*!40000 ALTER TABLE `userBirthControlPills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userCalender`
--

DROP TABLE IF EXISTS `userCalender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userCalender` (
  `id` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `sleepTime` time DEFAULT NULL,
  `exerciseTime` time DEFAULT NULL,
  `waterIntake` double DEFAULT NULL,
  `startDay` date DEFAULT NULL,
  `endDay` date DEFAULT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userCalender`
--

LOCK TABLES `userCalender` WRITE;
/*!40000 ALTER TABLE `userCalender` DISABLE KEYS */;
INSERT INTO `userCalender` VALUES ('aelim','2021-05-28','00:00:00','00:00:00',0,NULL,NULL),('aelim','2021-05-29','00:00:00','00:00:00',3,NULL,NULL),('aelim','2021-05-30','00:00:00','00:00:00',1,NULL,NULL),('aelim','2021-05-31','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-01','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-03','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-04','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-05','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-06','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-07','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-09','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-11','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-13','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-15','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-17','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-19','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-20','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-21','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-23','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-24','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-25','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-26','00:00:00','00:00:00',0,NULL,NULL),('mg','2021-05-30','00:00:00','00:00:00',0,NULL,NULL),('null','2021-05-30','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-03-10','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-03-15','00:00:00','00:00:00',0,'2021-03-15',NULL),('sl','2021-03-17','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-03-21','00:00:00','00:00:00',0,NULL,'2021-03-21'),('sl','2021-03-23','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-03-25','00:00:00','00:00:00',6,NULL,NULL),('sl','2021-03-30','00:00:00','00:00:00',2,NULL,NULL),('sl','2021-03-31','00:00:00','00:10:00',1,NULL,NULL),('sl','2021-04-03','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-04-12','00:00:00','00:00:00',0,'2021-04-12',NULL),('sl','2021-04-19','00:00:00','00:00:00',0,NULL,'2021-04-19'),('sl','2021-05-03','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-04','00:10:00','00:10:00',3,NULL,NULL),('sl','2021-05-05','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-06','00:00:00','00:10:00',1,NULL,NULL),('sl','2021-05-07','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-08','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-09','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-10','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-11','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-12','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-14','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-15','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-16','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-17','00:00:00','00:00:00',0,'2021-05-17',NULL),('sl','2021-05-18','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-19','00:00:00','00:00:00',2,NULL,NULL),('sl','2021-05-20','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-21','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-23','00:00:00','00:00:00',0,NULL,'2021-05-23'),('sl','2021-05-25','00:00:00','03:40:00',2,NULL,NULL),('sl','2021-05-26','00:20:00','00:00:00',0,NULL,NULL),('sl','2021-05-27','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-28','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-29','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-05-30','00:00:00','00:40:00',0,NULL,NULL),('sl','2021-05-31','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-06-01','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-06-17','00:00:00','00:00:00',0,'2021-06-17',NULL),('sl','2021-06-22','00:00:00','00:00:00',0,NULL,'2021-06-22'),('sl','2021-07-07','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-07-15','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-07-16','00:00:00','00:00:00',0,'2021-07-16',NULL),('sl','2021-07-21','00:00:00','00:00:00',0,NULL,'2021-07-21'),('sl','2021-07-23','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-07-26','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-07-28','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-07-29','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-08-15','00:00:00','00:00:00',0,'2021-08-15',NULL),('sl','2021-08-22','00:00:00','00:00:00',0,NULL,'2021-08-22'),('sl','2021-09-01','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-09-12','00:00:00','00:00:00',0,'2021-09-12',NULL),('sl','2021-09-17','00:00:00','00:00:00',0,NULL,'2021-09-17'),('sl','2021-09-22','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-10-10','00:00:00','00:00:00',0,'2021-10-10',NULL),('sl','2021-10-15','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-10-16','00:00:00','00:00:00',0,NULL,'2021-10-16'),('sl','2021-10-17','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-10-18','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-11-01','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-11-13','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-11-14','00:00:00','00:00:00',0,'2021-11-14',NULL),('sl','2021-11-15','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-11-19','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-11-20','00:00:00','00:00:00',0,NULL,'2021-11-20'),('sl','2021-11-21','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-01','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-06','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-07','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-08','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-09','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-10','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-11','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-12','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-13','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-14','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-15','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-16','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-17','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-18','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-19','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-20','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-21','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-22','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-23','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-24','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-26','00:00:00','00:00:00',0,NULL,NULL),('sl','2021-12-30','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-04-07','00:00:00','00:00:00',0,'2021-04-07',NULL),('sr','2021-04-11','00:00:00','00:00:00',0,NULL,'2021-04-11'),('sr','2021-04-20','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-04-30','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-01','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-03','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-04','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-05','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-06','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-07','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-08','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-09','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-10','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-11','00:00:00','00:00:00',0,'2021-05-11',NULL),('sr','2021-05-12','00:00:00','00:30:00',3,NULL,NULL),('sr','2021-05-13','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-14','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-16','00:00:00','00:00:00',0,NULL,'2021-05-16'),('sr','2021-05-17','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-18','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-21','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-22','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-24','00:30:00','00:50:00',3,NULL,NULL),('sr','2021-05-25','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-26','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-27','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-28','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-29','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-05-30','00:50:00','01:10:00',10,NULL,NULL),('sr','2021-05-31','01:20:00','00:00:00',12,NULL,NULL),('sr','2021-06-03','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-08','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-09','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-10','00:00:00','00:00:00',0,'2021-06-10',NULL),('sr','2021-06-12','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-13','00:00:00','00:00:00',0,NULL,'2021-06-13'),('sr','2021-06-14','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-15','00:40:00','00:20:00',2,NULL,NULL),('sr','2021-06-16','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-17','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-18','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-21','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-23','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-24','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-06-30','00:00:00','00:00:00',0,'2021-06-30',NULL),('sr','2021-07-02','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-05','01:10:00','00:30:00',1,NULL,'2021-07-05'),('sr','2021-07-10','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-13','00:00:00','00:00:00',0,'2021-07-13',NULL),('sr','2021-07-14','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-15','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-16','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-17','00:00:00','00:00:00',0,NULL,'2021-07-17'),('sr','2021-07-20','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-21','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-22','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-23','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-24','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-26','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-30','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-07-31','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-08-11','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-08-16','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-08-21','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-08-27','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-08-29','00:00:00','00:00:00',0,'2021-08-29',NULL),('sr','2021-09-02','00:00:00','00:00:00',0,NULL,'2021-09-02'),('sr','2021-09-03','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-09-14','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-09-15','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-09-17','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-09-18','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-09-28','00:00:00','00:00:00',0,'2021-09-28',NULL),('sr','2021-10-02','00:00:00','00:00:00',0,NULL,'2021-10-02'),('sr','2021-10-13','00:00:00','00:00:00',0,NULL,NULL),('sr','2021-10-18','00:00:00','00:00:00',0,'2021-10-18',NULL),('sr','2021-10-22','00:00:00','00:00:00',0,NULL,'2021-10-22'),('sr','2021-11-09','00:00:00','00:00:00',0,'2021-11-09',NULL),('sr','2021-11-14','00:00:00','00:00:00',0,NULL,'2021-11-14');
/*!40000 ALTER TABLE `userCalender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userExercise`
--

DROP TABLE IF EXISTS `userExercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userExercise` (
  `id` varchar(20) NOT NULL,
  `exerciseHour` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userExercise`
--

LOCK TABLES `userExercise` WRITE;
/*!40000 ALTER TABLE `userExercise` DISABLE KEYS */;
INSERT INTO `userExercise` VALUES ('aelim',0.5),('mg',0),('sl',0),('sr',3);
/*!40000 ALTER TABLE `userExercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userHospital`
--

DROP TABLE IF EXISTS `userHospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userHospital` (
  `id` varchar(20) NOT NULL,
  `hospitalDate` date DEFAULT NULL,
  `hospitalTime` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userHospital`
--

LOCK TABLES `userHospital` WRITE;
/*!40000 ALTER TABLE `userHospital` DISABLE KEYS */;
INSERT INTO `userHospital` VALUES ('aelim','2021-03-16','18:00:00'),('mg','2021-05-19','18:00:00'),('sl',NULL,'18:00:00'),('sr','2021-05-19','18:00:00');
/*!40000 ALTER TABLE `userHospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userMucus`
--

DROP TABLE IF EXISTS `userMucus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userMucus` (
  `id` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `none` tinyint(1) DEFAULT NULL,
  `mottled` tinyint(1) DEFAULT NULL,
  `sticky` tinyint(1) DEFAULT NULL,
  `creamy` tinyint(1) DEFAULT NULL,
  `likeEggWhite` tinyint(1) DEFAULT NULL,
  `watery` tinyint(1) DEFAULT NULL,
  `abnormal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userMucus`
--

LOCK TABLES `userMucus` WRITE;
/*!40000 ALTER TABLE `userMucus` DISABLE KEYS */;
INSERT INTO `userMucus` VALUES ('aelim','2021-05-28',1,1,0,0,0,1,0),('aelim','2021-05-29',0,0,0,0,0,0,0),('sl','2021-05-29',0,0,0,0,0,0,0),('sl','2021-07-29',0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `userMucus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userPhysiology`
--

DROP TABLE IF EXISTS `userPhysiology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userPhysiology` (
  `id` varchar(20) DEFAULT NULL,
  `startPhysiology` date DEFAULT NULL,
  `endPhysiology` date DEFAULT NULL,
  `expectedOvulationDate` date DEFAULT NULL,
  `expectedPhysiologyDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userPhysiology`
--

LOCK TABLES `userPhysiology` WRITE;
/*!40000 ALTER TABLE `userPhysiology` DISABLE KEYS */;
INSERT INTO `userPhysiology` VALUES ('sl','2021-03-15','2021-03-21',NULL,NULL),('sl','2021-04-12','2021-04-19',NULL,NULL),('sl','2021-05-17','2021-05-23',NULL,NULL),('sl','2021-06-17','2021-06-22',NULL,NULL),('sl','2021-07-16','2021-07-21',NULL,NULL),('sl','2021-08-15','2021-08-22',NULL,NULL),('sr','2021-06-10','2021-06-13',NULL,NULL),('sr','2021-06-30','2021-07-05',NULL,NULL),('sr','2021-07-13','2021-07-17',NULL,NULL),('sr','2021-08-29','2021-09-02',NULL,NULL),('sl','2021-09-12','2021-09-17',NULL,NULL),('sr','2021-09-28','2021-10-02',NULL,NULL),('sl','2021-10-10','2021-10-16',NULL,NULL),('sr','2021-10-18','2021-10-22',NULL,NULL),('sr','2021-11-09','2021-11-14',NULL,NULL),('sr','2021-05-11','2021-05-16',NULL,NULL),('sl','2021-11-14','2021-11-20',NULL,NULL),('sr','2021-04-07','2021-04-11',NULL,NULL);
/*!40000 ALTER TABLE `userPhysiology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userReminderList`
--

DROP TABLE IF EXISTS `userReminderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userReminderList` (
  `id` varchar(20) NOT NULL,
  `birthControlPills` tinyint(1) DEFAULT NULL,
  `physiology` tinyint(1) DEFAULT NULL,
  `hospital` tinyint(1) DEFAULT NULL,
  `waterIntake` tinyint(1) DEFAULT NULL,
  `exerciseTimeGoal` tinyint(1) DEFAULT NULL,
  `sleepTimeGoal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userReminderList`
--

LOCK TABLES `userReminderList` WRITE;
/*!40000 ALTER TABLE `userReminderList` DISABLE KEYS */;
INSERT INTO `userReminderList` VALUES ('aelim',1,1,0,0,0,0),('mg',1,0,0,0,0,0),('sl',0,0,0,0,0,0),('sr',1,0,0,0,0,0);
/*!40000 ALTER TABLE `userReminderList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userReminderPhysiology`
--

DROP TABLE IF EXISTS `userReminderPhysiology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userReminderPhysiology` (
  `id` varchar(20) NOT NULL,
  `physiologyTime` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userReminderPhysiology`
--

LOCK TABLES `userReminderPhysiology` WRITE;
/*!40000 ALTER TABLE `userReminderPhysiology` DISABLE KEYS */;
INSERT INTO `userReminderPhysiology` VALUES ('aelim','15:32:00'),('mg','18:00:00'),('sl','18:00:00'),('sr','18:00:00');
/*!40000 ALTER TABLE `userReminderPhysiology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userSleep`
--

DROP TABLE IF EXISTS `userSleep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userSleep` (
  `id` varchar(20) NOT NULL,
  `sleepGoal` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userSleep`
--

LOCK TABLES `userSleep` WRITE;
/*!40000 ALTER TABLE `userSleep` DISABLE KEYS */;
INSERT INTO `userSleep` VALUES ('aelim',8),('mg',0),('sl',0),('sr',2);
/*!40000 ALTER TABLE `userSleep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userSymptom`
--

DROP TABLE IF EXISTS `userSymptom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userSymptom` (
  `id` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `none` tinyint(1) DEFAULT NULL,
  `cramps` tinyint(1) DEFAULT NULL,
  `breastTenderness` tinyint(1) DEFAULT NULL,
  `headache` tinyint(1) DEFAULT NULL,
  `acne` tinyint(1) DEFAULT NULL,
  `lumbago` tinyint(1) DEFAULT NULL,
  `nausea` tinyint(1) DEFAULT NULL,
  `fatigue` tinyint(1) DEFAULT NULL,
  `abdominalBloating` tinyint(1) DEFAULT NULL,
  `desires` tinyint(1) DEFAULT NULL,
  `insomnia` tinyint(1) DEFAULT NULL,
  `constipation` tinyint(1) DEFAULT NULL,
  `diarrhea` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userSymptom`
--

LOCK TABLES `userSymptom` WRITE;
/*!40000 ALTER TABLE `userSymptom` DISABLE KEYS */;
INSERT INTO `userSymptom` VALUES ('aelim','2021-05-28',1,1,0,1,1,0,0,0,0,0,0,0,0),('aelim','2021-05-29',0,0,0,0,0,0,0,0,0,0,0,0,0),('sl','2021-05-29',0,0,0,0,0,0,0,0,0,0,0,0,0),('sl','2021-07-29',0,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `userSymptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userWaterIntake`
--

DROP TABLE IF EXISTS `userWaterIntake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userWaterIntake` (
  `id` varchar(20) NOT NULL,
  `cups` double DEFAULT NULL,
  `waterTime` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userWaterIntake`
--

LOCK TABLES `userWaterIntake` WRITE;
/*!40000 ALTER TABLE `userWaterIntake` DISABLE KEYS */;
INSERT INTO `userWaterIntake` VALUES ('aelim',0,NULL),('mg',0,NULL),('sl',0,NULL),('sr',0,NULL);
/*!40000 ALTER TABLE `userWaterIntake` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-31 16:56:55
