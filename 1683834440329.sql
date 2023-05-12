-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: repair_app_db
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.22.04.1

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
-- Current Database: `repair_app_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `repair_app_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `repair_app_db`;

--
-- Table structure for table `_user`
--

DROP TABLE IF EXISTS `_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user`
--

LOCK TABLES `_user` WRITE;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
INSERT INTO `_user` VALUES (1,'2023-05-10 17:58:17.984928','admin@mail.com','admin','admin','$2a$10$e3mgpU/tvgJJI3HjbGOg8OnOitTtr0QVJiE78l57TCjFOvdWsGpN.','2023-05-10 17:58:17.996846'),(2,'2023-05-10 17:58:18.196705','user@mail.com','user','user','$2a$10$Tj1/uFIkLsHl0mrW1hY25u5tmCGDE1h7ok9rrerHGWUwHGnQJlz6i','2023-05-10 17:58:18.199747'),(52,'2023-05-11 11:32:23.182585','Zechariah_Sauer@hotmail.com','Johnnie','McLaughlin','$2a$10$nKlayatE4eJ88L9.k.FOu.ZVOfvEQtOUlvEs6.C9dhEqLgamedarO','2023-05-11 11:32:23.182684');
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_user_seq`
--

DROP TABLE IF EXISTS `_user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user_seq`
--

LOCK TABLES `_user_seq` WRITE;
/*!40000 ALTER TABLE `_user_seq` DISABLE KEYS */;
INSERT INTO `_user_seq` VALUES (151);
/*!40000 ALTER TABLE `_user_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'2023-05-10 17:58:18.269519','hp','2023-05-10 17:58:18.269579'),(2,'2023-05-10 17:58:18.276960','DELL','2023-05-10 17:58:18.277015'),(3,'2023-05-10 17:58:18.284285','Acer','2023-05-10 17:58:18.284356'),(4,'2023-05-11 16:16:00.421431','Apple','2023-05-11 16:16:00.421475');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defect`
--

DROP TABLE IF EXISTS `defect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defect` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost` double NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `required_time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgbwq3li6f49u8i2ndh5hm8f6b` (`device_id`),
  CONSTRAINT `FKgbwq3li6f49u8i2ndh5hm8f6b` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defect`
--

LOCK TABLES `defect` WRITE;
/*!40000 ALTER TABLE `defect` DISABLE KEYS */;
INSERT INTO `defect` VALUES (1,1,'2023-05-10 17:58:18.301691',50,'3','broken screen','2023-05-10 17:58:18.301768',1);
/*!40000 ALTER TABLE `defect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `brand_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc30uehhf9cfjxhugdu6cqcaw` (`brand_id`),
  CONSTRAINT `FKc30uehhf9cfjxhugdu6cqcaw` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,NULL,'2023-05-10 17:58:18.291453','3000 series','inspiron 15','2023-05-10 17:58:18.291509',2);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quick_reply`
--

DROP TABLE IF EXISTS `quick_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quick_reply` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `body` tinytext,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quick_reply`
--

LOCK TABLES `quick_reply` WRITE;
/*!40000 ALTER TABLE `quick_reply` DISABLE KEYS */;
INSERT INTO `quick_reply` VALUES (1,'body3','2023-05-10 17:58:18.214359','name3','2023-05-10 17:58:18.214403'),(2,'body2','2023-05-10 17:58:18.229060','name2','2023-05-10 17:58:18.229123'),(3,'body1','2023-05-10 17:58:18.238211','name1','2023-05-10 17:58:18.238279');
/*!40000 ALTER TABLE `quick_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_order`
--

DROP TABLE IF EXISTS `repair_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `completed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `diagnostic` tinytext,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment_status` bit(1) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pre_paid` double NOT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `total_charge` double NOT NULL,
  `total_cost` double NOT NULL,
  `tracking` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `uuid` binary(16) DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `repair_priority_id` bigint DEFAULT NULL,
  `repair_status_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXsag8gboqmbbw51vtncunt6y5` (`uuid`),
  KEY `FKumn1gw3qy18t9t7je963jmb4` (`device_id`),
  KEY `FKshqtrxuvdpweaanw7pyu0s6h9` (`repair_priority_id`),
  KEY `FK8b7bvglqvwggbtyhcs0tsoqgu` (`repair_status_id`),
  KEY `FKseg7uqs215mcyjgphbfernhud` (`user_id`),
  CONSTRAINT `FK8b7bvglqvwggbtyhcs0tsoqgu` FOREIGN KEY (`repair_status_id`) REFERENCES `repair_status` (`id`),
  CONSTRAINT `FKseg7uqs215mcyjgphbfernhud` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `FKshqtrxuvdpweaanw7pyu0s6h9` FOREIGN KEY (`repair_priority_id`) REFERENCES `repair_priority` (`id`),
  CONSTRAINT `FKumn1gw3qy18t9t7je963jmb4` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_order`
--

LOCK TABLES `repair_order` WRITE;
/*!40000 ALTER TABLE `repair_order` DISABLE KEYS */;
INSERT INTO `repair_order` VALUES (3,'19417 Harris Field',NULL,'2023-05-10 18:04:32.733052',NULL,'Annie.Keebler@hotmail.com','Alicia',_binary '\0','626-282-2524',0,'q',30,10,'1683738272729','2023-05-10 18:04:32.733114',_binary 'øÑ|Ãù?@±í\«\\\Ô\ÒPP',1,1,1,NULL),(4,'049 Upton Centers',NULL,'2023-05-10 18:11:36.945349',NULL,'Linwood89@yahoo.com','Sofia',_binary '','264-488-3990',0,'w',30,10,'1683738696944','2023-05-10 18:11:36.945401',_binary '≤\Îj¥©KJ¿óP\ÂW97\Ú]',1,1,1,NULL);
/*!40000 ALTER TABLE `repair_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_order_defect`
--

DROP TABLE IF EXISTS `repair_order_defect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_order_defect` (
  `repair_order_id` bigint NOT NULL,
  `defect_id` bigint NOT NULL,
  KEY `FKocyjjvo9cpphl5v33ojlx794y` (`defect_id`),
  KEY `FKkxk80q2nvh66lku811smnv9t8` (`repair_order_id`),
  CONSTRAINT `FKkxk80q2nvh66lku811smnv9t8` FOREIGN KEY (`repair_order_id`) REFERENCES `repair_order` (`id`),
  CONSTRAINT `FKocyjjvo9cpphl5v33ojlx794y` FOREIGN KEY (`defect_id`) REFERENCES `defect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_order_defect`
--

LOCK TABLES `repair_order_defect` WRITE;
/*!40000 ALTER TABLE `repair_order_defect` DISABLE KEYS */;
INSERT INTO `repair_order_defect` VALUES (3,1),(4,1);
/*!40000 ALTER TABLE `repair_order_defect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_priority`
--

DROP TABLE IF EXISTS `repair_priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_priority` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `extra_charge` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority_value` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_priority`
--

LOCK TABLES `repair_priority` WRITE;
/*!40000 ALTER TABLE `repair_priority` DISABLE KEYS */;
INSERT INTO `repair_priority` VALUES (1,'2023-05-10 17:58:18.311699',0,'normal',0,'2023-05-10 17:58:18.311781');
/*!40000 ALTER TABLE `repair_priority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_status`
--

DROP TABLE IF EXISTS `repair_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_status`
--

LOCK TABLES `repair_status` WRITE;
/*!40000 ALTER TABLE `repair_status` DISABLE KEYS */;
INSERT INTO `repair_status` VALUES (1,'2023-05-10 17:58:18.247470','pending','2023-05-10 17:58:18.247529'),(2,'2023-05-10 17:58:18.255129','opening','2023-05-10 17:58:18.255198'),(3,'2023-05-10 17:58:18.261562','closed','2023-05-10 17:58:18.261603');
/*!40000 ALTER TABLE `repair_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_address` varchar(255) DEFAULT NULL,
  `app_name` varchar(255) DEFAULT NULL,
  `app_phone` varchar(255) DEFAULT NULL,
  `currency_symbol` varchar(255) DEFAULT 'DA',
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKiblu4cjwvyntq3ugo31klp1c6` (`user_id`),
  CONSTRAINT `FKiblu4cjwvyntq3ugo31klp1c6` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzczNzk1OCwiZXhwIjoxNjgzNzM5Mzk4fQ.0tieP2I7FIcrPvbeJgCRpipkut9kbUawOfTl0_52m1A','BEARER',1),(2,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzczODA2NSwiZXhwIjoxNjgzNzM5NTA1fQ.qB0cEmkI54ccgPrEqdkTvSDFERw8z5ah4W4HYym9ZIg','BEARER',1),(52,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzczODI2NywiZXhwIjoxNjgzNzM5NzA3fQ.SKvrbztzMlNG22xEJztI1BhYG4bHbxjX5IK9abEbQ7w','BEARER',1),(102,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzgwMTEzNSwiZXhwIjoxNjgzODAyNTc1fQ.oosaytmp0y5akPXvuisPBH_Q9SX9lIO16CALQV_tkYM','BEARER',1),(103,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJaZWNoYXJpYWhfU2F1ZXJAaG90bWFpbC5jb20iLCJpYXQiOjE2ODM4MDExNTgsImV4cCI6MTY4MzgwMjU5OH0.WGINBxvKbyxVWX0vOhwfMDLNfXlah20KPJqwSOpc18M','BEARER',52),(104,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzgxNTQzOCwiZXhwIjoxNjgzODE2ODc4fQ.3VGmdRA2NHNq6K4EdKew-TJL7xBhc87pXQ2Z-23SI1U','BEARER',1),(105,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzgxNzgyNSwiZXhwIjoxNjgzODE5MjY1fQ.jFTDbuY9LJtEXzxVpI3GVJ5qejPgsrNfbybobc6X6N0','BEARER',1),(152,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY4MzgzNDQzNCwiZXhwIjoxNjgzODM1ODc0fQ.xLnIBdWrzCky3RNtnKnZxJ6Z9qQjCfM4dgWuK6c9R74','BEARER',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_seq`
--

DROP TABLE IF EXISTS `token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_seq`
--

LOCK TABLES `token_seq` WRITE;
/*!40000 ALTER TABLE `token_seq` DISABLE KEYS */;
INSERT INTO `token_seq` VALUES (251);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_authority` (
  `user_id` bigint NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FKqqm2hu93y192x5qbey5vg8hxj` (`user_id`),
  CONSTRAINT `FKqqm2hu93y192x5qbey5vg8hxj` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,'ACCESS_DASHBOARD'),(1,'MANAGE_REPAIR_ORDER'),(1,'EDIT_REPAIR_ORDER'),(1,'REMOVE_REPAIR_ORDER'),(1,'MANAGE_DEVICE'),(1,'MANAGE_DEFECT'),(1,'MANAGE_BRAND'),(1,'MANAGE_QUICK_REPLY'),(1,'MANAGE_REPAIR_STATUS'),(1,'MANAGE_REPAIR_PRIORITY'),(1,'ACCESS_REPORT'),(1,'MANAGE_USERS'),(1,'GENERAL_SETTING'),(1,'OUTGOING_MAIL'),(1,'SMS_GATEWAY'),(1,'CURRENCY_CONFIGURATION'),(1,'AUTHENTICATION'),(1,'LOCALIZATION'),(1,'DATABASE_BACKUP'),(2,'MANAGE_DEVICE'),(2,'MANAGE_BRAND'),(2,'MANAGE_DEFECT'),(52,'ACCESS_DASHBOARD');
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-11 20:47:20
