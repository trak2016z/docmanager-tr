-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: sql7.freesqldatabase.com    Database: sql7142486
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `com_date` datetime NOT NULL,
  `content` varchar(600) NOT NULL,
  `user_fk` int(11) DEFAULT NULL,
  `document_fk` int(11) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_user_idx` (`user_fk`),
  KEY `fk_document_idx` (`document_fk`),
  CONSTRAINT `fk_document` FOREIGN KEY (`document_fk`) REFERENCES `document` (`document_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_fk`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `extension` varchar(5) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `note` varchar(600) DEFAULT NULL,
  `keywords` varchar(300) DEFAULT NULL,
  `approved` bit(1) DEFAULT NULL,
  `fk_user` int(11) DEFAULT NULL,
  `public` bit(1) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `uploaded` datetime DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  UNIQUE KEY `doument_id_UNIQUE` (`document_id`),
  KEY `user_fk_idx` (`fk_user`),
  CONSTRAINT `user_document_fk` FOREIGN KEY (`fk_user`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rate`
--

DROP TABLE IF EXISTS `rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rate` (
  `rate_id` int(11) NOT NULL AUTO_INCREMENT,
  `rate_value` float NOT NULL,
  `user_fk` int(11) DEFAULT NULL,
  `document_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`rate_id`),
  UNIQUE KEY `owner_id_UNIQUE` (`rate_id`),
  KEY `user_fk_idx` (`user_fk`),
  KEY `document_fk_idx` (`document_fk`),
  CONSTRAINT `fkdoc` FOREIGN KEY (`document_fk`) REFERENCES `document` (`document_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkuser` FOREIGN KEY (`user_fk`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `search_browser`
--

DROP TABLE IF EXISTS `search_browser`;
/*!50001 DROP VIEW IF EXISTS `search_browser`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `search_browser` AS SELECT 
 1 AS `avatar`,
 1 AS `keywords`,
 1 AS `approved`,
 1 AS `name`,
 1 AS `uploaded`,
 1 AS `fname`,
 1 AS `lname`,
 1 AS `public`,
 1 AS `document_id`,
 1 AS `average_rate`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `mname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) NOT NULL,
  `email` varchar(300) NOT NULL,
  `permission` char(1) NOT NULL DEFAULT 'A',
  `dob` datetime NOT NULL,
  `description` varchar(600) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL,
  `trusted` bit(1) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `search_browser`
--

/*!50001 DROP VIEW IF EXISTS `search_browser`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sql7142486`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `search_browser` AS (select `d`.`avatar` AS `avatar`,`d`.`keywords` AS `keywords`,`d`.`approved` AS `approved`,`d`.`name` AS `name`,`d`.`uploaded` AS `uploaded`,`u`.`fname` AS `fname`,`u`.`lname` AS `lname`,`d`.`public` AS `public`,`d`.`document_id` AS `document_id`,avg(`r`.`rate_value`) AS `average_rate` from ((`document` `d` left join `user` `u` on((`u`.`user_id` = `d`.`fk_user`))) left join `rate` `r` on((`r`.`document_fk` = `d`.`document_id`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-07 20:52:33
