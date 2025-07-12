-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mobile_1
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','Create and Insert','SQL','V1__Create_and_Insert.sql',1019055349,'root','2026-07-10 13:36:18',125,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_admin_user`
--

DROP TABLE IF EXISTS `tb_carb_mall_admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_admin_user` (
  `admin_user_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_admin_user`
--

LOCK TABLES `tb_carb_mall_admin_user` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_admin_user` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_admin_user` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','LK',0),(2,'carb-admin1','e10adc3949ba59abbe56e057f20f883e','01',0),(3,'carb-admin2','e10adc3949ba59abbe56e057f20f883e','02',0);
/*!40000 ALTER TABLE `tb_carb_mall_admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_carousel`
--

DROP TABLE IF EXISTS `tb_carb_mall_carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_carousel` (
  `carousel_id` int NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id',
  `carousel_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '轮播图',
  `redirect_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '''##''' COMMENT '点击后的跳转地址(默认不跳转)',
  `carousel_rank` int NOT NULL DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` int NOT NULL DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_carousel`
--

LOCK TABLES `tb_carb_mall_carousel` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_carousel` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_carousel` VALUES (1,'videos/Black_myth_video.mp4','https://store.steampowered.com',100,0,'2024-06-29 00:00:00',0,'2024-06-29 00:00:00',0),
                                           (2,'videos/Dont_Starve_Together_video.mp4','https://store.steampowered.com',80,0,'2024-06-29 00:00:00',0,'2024-06-29 00:00:00',0),
                                           (3,'videos/Hollow knight_video.mp4','https://store.steampowered.com',70,0,'2024-06-29 00:00:00',0,'2024-06-29 00:00:00',0),
                                           (4,'videos/Mayhemers_video.mp4','https://store.steampowered.com',60,0,'2024-06-29 00:00:00',0,'2024-06-29 00:00:00',0),
                                           (5,'videos/Stardew_Valley_video.mp4','https://store.steampowered.com',50,0,'2024-06-29 00:00:00',0,'2024-06-29 00:00:00',0);
/*!40000 ALTER TABLE `tb_carb_mall_carousel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_cars_category`
--

DROP TABLE IF EXISTS `tb_carb_mall_cars_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_cars_category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_level` tinyint NOT NULL DEFAULT '0' COMMENT '分类级别(1-一级分类 2-二级分类 3-三级分类)',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父分类id',
  `category_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_rank` int NOT NULL DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` int NOT NULL DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_cars_category`
--

LOCK TABLES `tb_carb_mall_cars_category` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_cars_category` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_cars_category` VALUES (1,1,0,'Action Games',0,0,'2026-07-08 18:47:38',0,'2026-07-08 18:47:38',0),(2,1,0,'Role-Playing Games',0,0,'2026-07-08 18:47:38',0,'2026-07-08 18:47:38',0),(3,1,0,'Shooter Games',0,0,'2026-07-08 18:47:49',0,'2026-07-08 18:47:49',0),(4,1,0,'Strategy Games',0,0,'2026-07-08 18:47:49',0,'2026-07-08 18:47:49',0),(5,1,0,'Simulation Games',0,0,'2026-07-08 18:47:58',0,'2026-07-08 18:47:58',0),(6,1,0,'Sports & Racing',0,0,'2026-07-08 18:47:58',0,'2026-07-08 18:47:58',0),(7,1,0,'Adventure Games',0,0,'2026-07-08 18:48:00',0,'2026-07-08 18:48:00',0),(8,1,0,'Puzzle & Casual',0,0,'2026-07-08 18:48:00',0,'2026-07-08 18:48:00',0),(9,1,0,'Horror & Survival',0,0,'2026-07-08 18:48:00',0,'2026-07-08 18:48:00',0),(10,2,1,'Platformer',0,0,'2026-07-08 18:48:38',0,'2026-07-08 18:48:38',0),(11,2,1,'Fighting',0,0,'2026-07-08 18:48:49',0,'2026-07-08 18:48:49',0),(12,2,1,'Beat them up',0,0,'2026-07-08 18:48:58',0,'2026-07-08 18:48:58',0),(13,2,2,'Adventure role-playing game',0,0,'2026-07-08 18:48:38',0,'2026-07-08 18:48:38',0),(14,2,2,'Japanese role-playing game',0,0,'2026-07-08 18:48:49',0,'2026-07-08 18:48:49',0),(15,2,2,'Team role-playing game',0,0,'2026-07-08 18:48:58',0,'2026-07-08 18:48:58',0),(16,2,3,'First-Person Shooter',0,0,'2026-07-08 18:49:06',0,'2026-07-08 18:49:06',0),(17,2,3,'Third-Person Shooter',0,0,'2026-07-08 18:49:12',0,'2026-07-08 18:49:12',0),(18,2,3,'Hero Shooter',0,0,'2026-07-08 18:49:26',0,'2026-07-08 18:49:26',0),(19,2,4,'Real-time strategy',0,0,'2026-07-08 18:49:06',0,'2026-07-08 18:49:06',0),(20,2,4,'Turn-based',0,0,'2026-07-08 18:49:12',0,'2026-07-08 18:49:12',0),(21,2,4,'Survival Strategy',0,0,'2026-07-08 18:49:26',0,'2026-07-08 18:49:26',0),(22,2,5,'Space and flight',0,0,'2026-07-08 18:49:40',0,'2026-07-08 18:49:40',0),(23,2,5,'Farm management',0,0,'2026-07-08 18:49:50',0,'2026-07-08 18:49:50',0),(24,2,5,'Lifestyle and interior',0,0,'2026-07-08 18:49:58',0,'2026-07-08 18:49:58',0),(25,2,6,'Football / Soccer',0,0,'2026-07-08 18:49:40',0,'2026-07-08 18:49:40',0),(26,2,6,'Basketball',0,0,'2026-07-08 18:49:50',0,'2026-07-08 18:49:50',0),(27,2,6,'Racing Simulation',0,0,'2026-07-08 18:49:58',0,'2026-07-08 18:49:58',0),(28,2,7,'Point & Click',0,0,'2026-07-08 18:50:06',0,'2026-07-08 18:50:06',0),(29,2,7,'Puzzle Adventure',0,0,'2026-07-08 18:50:14',0,'2026-07-08 18:50:14',0),(30,2,7,'Metroidvania',0,0,'2026-07-08 18:50:22',0,'2026-07-08 18:50:22',0),(31,2,8,'Narrative Adventure',0,0,'2026-07-08 18:50:06',0,'2026-07-08 18:50:06',0),(32,2,8,'Brain Training',0,0,'2026-07-08 18:50:14',0,'2026-07-08 18:50:14',0),(33,2,8,'Match-3',0,0,'2026-07-08 18:50:22',0,'2026-07-08 18:50:22',0),(34,2,9,'Survival Horror',0,0,'2026-07-08 18:50:06',0,'2026-07-08 18:50:06',0),(35,2,9,'Zombie Survival',0,0,'2026-07-08 18:50:14',0,'2026-07-08 18:50:14',0),(36,2,9,'Crafting Survival',0,0,'2026-07-08 18:50:22',0,'2026-07-08 18:50:22',0),(37,3,10,'Noita',0,0,'2026-07-08 18:51:49',0,'2026-07-08 18:51:49',0),(38,3,11,'Mayhemers',0,0,'2026-07-08 18:52:14',0,'2026-07-08 18:52:14',0),(39,3,12,'Double Dragon Trilogy',0,0,'2026-07-08 18:52:38',0,'2026-07-08 18:52:38',0),(40,3,13,'Black Myth: Wukong',0,0,'2026-07-08 18:51:58',0,'2026-07-08 18:51:58',0),(41,3,14,'Persona',0,0,'2026-07-08 18:52:22',0,'2026-07-08 18:52:22',0),(42,3,15,'Baldur\'s Gate',0,0,'2026-07-08 18:52:46',0,'2026-07-08 18:52:46',0),(43,3,16,'Call of Duty',0,0,'2026-07-08 18:52:54',0,'2026-07-08 18:52:54',0),(44,3,17,'Gears 5',0,0,'2026-07-08 18:53:18',0,'2026-07-08 18:53:18',0),(45,3,18,'Overwatch 2',0,0,'2026-07-08 18:53:42',0,'2026-07-08 18:53:42',0),(46,3,19,'Age of Empires IV',0,0,'2026-07-08 18:53:10',0,'2026-07-08 18:53:10',0),(47,3,20,'XCOM 2',0,0,'2026-07-08 18:53:34',0,'2026-07-08 18:53:34',0),(48,3,21,'Don\'t Starve Together',0,0,'2026-07-08 18:53:58',0,'2026-07-08 18:53:58',0),(49,3,22,'Elite Dangerous',0,0,'2026-07-08 18:54:06',0,'2026-07-08 18:54:06',0),(50,3,23,'Stardew Valley',0,0,'2026-07-08 18:54:22',0,'2026-07-08 18:54:22',0),(51,3,24,'House Flipper',0,0,'2026-07-08 18:54:46',0,'2026-07-08 18:54:46',0),(52,3,25,'EA Sports FC 24',0,0,'2026-07-08 18:54:14',0,'2026-07-08 18:54:14',0),(53,3,26,'NBA 2K24',0,0,'2026-07-08 18:54:30',0,'2026-07-08 18:54:30',0),(54,3,27,'Forza Horizon 5',0,0,'2026-07-08 18:54:38',0,'2026-07-08 18:54:38',0),(55,3,28,'Life is Strange',0,0,'2026-07-08 18:55:30',0,'2026-07-08 18:55:30',0),(56,3,29,'The Witness',0,0,'2026-07-08 18:55:38',0,'2026-07-08 18:55:38',0),(57,3,30,'Hollow Knight',0,0,'2026-07-08 18:55:46',0,'2026-07-08 18:55:46',0),(58,3,31,'Detroit: Become Human',0,0,'2026-07-08 18:55:54',0,'2026-07-08 18:55:54',0),(59,3,32,'Human Resource Machine',0,0,'2026-07-08 18:56:02',0,'2026-07-08 18:56:02',0),(60,3,33,'Puzzle Quest 3',0,0,'2026-07-08 18:56:10',0,'2026-07-08 18:56:10',0),(61,3,34,'Dead by Daylight',0,0,'2026-07-08 18:56:18',0,'2026-07-08 18:56:18',0),(62,3,35,'Dying Light 2',0,0,'2026-07-08 18:56:26',0,'2026-07-08 18:56:26',0),(63,3,36,'Green Hell',0,0,'2026-07-08 18:56:34',0,'2026-07-08 18:56:34',0);
/*!40000 ALTER TABLE `tb_carb_mall_cars_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_cars_info`
--

DROP TABLE IF EXISTS `tb_carb_mall_cars_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_cars_info` (
  `cars_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '商品表主键id',
  `cars_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品名',
  `cars_intro` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品简介',
  `cars_category_id` bigint NOT NULL DEFAULT '0' COMMENT '关联分类id',
  `cars_cover_img` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品主图',
  `cars_carousel` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品轮播图',
  `cars_detail_content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品详情',
  `original_price` decimal(10,4) NOT NULL DEFAULT '1.0000' COMMENT '商品价格',
  `selling_price` decimal(10,4) NOT NULL DEFAULT '1.0000' COMMENT '商品实际售价',
  `stock_num` int NOT NULL DEFAULT '0' COMMENT '商品库存数量',
  `tag` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品标签',
  `cars_sell_status` tinyint NOT NULL DEFAULT '0' COMMENT '商品上架状态 0-上架 1-下架',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '添加者主键id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品添加时间',
  `update_user` int NOT NULL DEFAULT '0' COMMENT '修改者主键id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品修改时间',
  PRIMARY KEY (`cars_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10027 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_cars_info`
--

LOCK TABLES `tb_carb_mall_cars_info` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_cars_info` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_cars_info` VALUES (10000,'Noita','Noita is a magical action roguelite set in a world where every pixel is physically simulated. Fight, explore, melt, burn, freeze and evaporate your way through the procedurally generated world using spells you\'ve created yourself.',37,'/images/Noita1.jpg','/images/Noita1.jpg,/images/Noita2.jpg','Noita is a magical action roguelite set in a world where every pixel is physically simulated. Fight, explore, melt, burn, freeze and evaporate your way through the procedurally generated world using spells you\'ve created yourself.',19.9900,7.9900,5000,'Platformer',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10001,'Mayhemers','\"Mayhemers\" is a multiplayer chaos creation game for up to 8 players with a procedurally generated map. Choose your side: Will you be a Mayhemer or a Guardian? Mayhemers\' task is to create chaos. Guardians\' task is to stop them.',38,'/images/Mayhemers1.jpg','/images/Mayhemers1.jpg,/images/Mayhemers2.jpg','\"Mayhemers\" is a multiplayer chaos creation game for up to 8 players with a procedurally generated map. Choose your side: Will you be a Mayhemer or a Guardian? Mayhemers\' task is to create chaos. Guardians\' task is to stop them.',3.9900,1.9900,10000,'Compact',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10002,'Double Dragon Trilogy','A groundbreaking, uber-popular game upon its arcade debut in 1987. Double Dragon is the undisputed godfather of co-op beat ‘em up.',39,'/images/Double_Dragon_Trilogy1.jpg','/images/Double_Dragon_Trilogy1.jpg,/images/Double_Dragon_Trilogy2.jpg','A groundbreaking, uber-popular game upon its arcade debut in 1987. Double Dragon is the undisputed godfather of co-op beat ‘em up..',3.5900,0.7100,10000,'Beat them up',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10003,'Black Myth: Wukong',' Wukong is an action RPG rooted in Chinese mythology. You shall set out as the Destined One to venture into the challenges and marvels ahead, to uncover the obscured truth beneath the veil of a glorious legend from the past.',40,'/images/BlackMyth1.jpg','/images/BlackMyth1.jpg,/images/BlackMyth2.jpg','Wukong is an action RPG rooted in Chinese mythology. You shall set out as the Destined One to venture into the challenges and marvels ahead, to uncover the obscured truth beneath the veil of a glorious legend from the past.',59.9900,47.9900,2452,'Adventure role-playing game',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10004,'Persona','Dive into the Dark Hour and awaken the depths of your heart. Persona Reload is a captivating reimagining of the genre-defining RPG, reborn for the modern era with cutting-edge graphics and gameplay.',41,'/images/Persona1.jpg','/images/Persona1.jpg,/images/Persona2.jpg','Dive into the Dark Hour and awaken the depths of your heart. Persona Reload is a captivating reimagining of the genre-defining RPG, reborn for the modern era with cutting-edge graphics and gameplay.',20.9900,8.9900,12402,'Japanese role-playing game',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10005,'Baldur\'s Gate','The classic adventure returns! Baldur’s Gate: Enhanced Edition includes the original Baldur’s Gate adventure, the Tales of the Sword Coast expansion, and all-new content including three new party members.',42,'/images/BaldursGate1.jpg','/images/BaldursGate1.jpg,/images/BaldursGate2.jpg','The classic adventure returns! Baldur’s Gate: Enhanced Edition includes the original Baldur’s Gate adventure, the Tales of the Sword Coast expansion, and all-new content including three new party members.',10.4900,5.2400,690,'Team role-playing game',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10006,'Call of Duty','Outnumbered and outgunned, but not outmatched.Call of Duty®: Ghosts is an extraordinary step forward for one of the largest entertainment franchises of all-time. This new chapter in the Call of Duty® franchise features a new dynamic where players are on the side of a crippled nation fighting',43,'/images/Call_of_Duty1.jpg','/images/Call_of_Duty1.jpg,/images/Call_of_Duty2.jpg','Outnumbered and outgunned, but not outmatched.Call of Duty®: Ghosts is an extraordinary step forward for one of the largest entertainment franchises of all-time. This new chapter in the Call of Duty® franchise features a new dynamic where players are on the side of a crippled nation fighting',59.9900,19.7900,12405,'First-Person Shooter',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10007,'Gears 5','From one of gaming’s most acclaimed sagas, Gears is bigger than ever. With all-out war descending, Kait Diaz breaks away to uncover her connection to the enemy and discovers the true danger to Sera – herself.',44,'/images/Gear_5_1.jpg','/images/Gear_5_1.jpg,/images/Gear_5_2.jpg','From one of gaming’s most acclaimed sagas, Gears is bigger than ever. With all-out war descending, Kait Diaz breaks away to uncover her connection to the enemy and discovers the true danger to Sera – herself.',49.9900,29.9900,9240,'Third-Person Shooter',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10008,'Overwatch 2','Overwatch 2 is a critically acclaimed, team-based shooter game set in an optimistic future with an evolving roster of heroes. Team up with friends and jump in today.',45,'/images/overwatch_2.jpg','/images/overwatch_2.jpg,/images/overwatch_1.jpg','Overwatch 2 is a critically acclaimed, team-based shooter game set in an optimistic future with an evolving roster of heroes. Team up with friends and jump in today.',14.9900,0.0000,9024,'Hero Shooter',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10009,'Age of Empires IV','Celebrating its first year of delighting millions of global players, the award-winning and best-selling strategy franchise continues with Age of Empires IV: Anniversary Edition, putting you at the center of even more epic historical battles that shaped the world.',46,'/images/Age of Empires IV_1.jpg','/images/Age of Empires IV_1.jpg,/images/Age of Empires IV_2.jpg','Celebrating its first year of delighting millions of global players, the award-winning and best-selling strategy franchise continues with Age of Empires IV: Anniversary Edition, putting you at the center of even more epic historical battles that shaped the world.',10.9900,7.9900,12402,'Real-time strategy',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10010,'XCOM 2','XCOM 2 is the sequel to XCOM: Enemy Unknown, the 2012 award-winning strategy game of the year. Earth has changed and is now under alien rule. Facing impossible odds you must rebuild XCOM, and ignite a global resistance to reclaim our world and save humanity.',47,'/images/xcom_image_1.jpg','/images/xcom_image_1.jpg,/images/xcom_image_2.jpg','XCOM 2 is the sequel to XCOM: Enemy Unknown, the 2012 award-winning strategy game of the year. Earth has changed and is now under alien rule. Facing impossible odds you must rebuild XCOM, and ignite a global resistance to reclaim our world and save humanity.',59.9900,2.9900,8024,'Turn-based',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10011,'Don\'t Starve Together','Fight, Farm, Build and Explore Together in the standalone multiplayer expansion to the uncompromising wilderness survival game, Don\'t Starve.',48,'/images/dont_starve_image_1.jpg','/images/dont_starve_image_1.jpg,/images/dont_starve_image_2.jpg','Fight, Farm, Build and Explore Together in the standalone multiplayer expansion to the uncompromising wilderness survival game, Don\'t Starve.',6.5900,2.2400,10224,'Survival Strategy',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10012,'Elite Dangerous','Take control of your own starship in a cutthroat galaxy. Elite Dangerous is the definitive massively multiplayer space epic.',49,'/images/elite_image_1.jpg','/images/elite_image_1.jpg,/images/elite_image_2.jpg','Take control of your own starship in a cutthroat galaxy. Elite Dangerous is the definitive massively multiplayer space epic.',10.4900,3.1400,12230,'Space and flight',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10013,'Stardew Valley',' You\'ve inherited your grandfather\'s old farm plot in Stardew Valley. Armed with hand-me-down tools and a few coins, you set out to begin your new life. Can you learn to live off the land and turn these overgrown fields into a thriving home?',50,'/images/stardew_image_1.jpg','/images/stardew_image_1.jpg,/images/stardew_image_2.jpg','You\'ve inherited your grandfather\'s old farm plot in Stardew Valley. Armed with hand-me-down tools and a few coins, you set out to begin your new life. Can you learn to live off the land and turn these overgrown fields into a thriving home?',5.9900,2.9900,9214,'Farm management',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10014,'House Flipper','House Flipper is a unique chance to become a one-man renovation crew. Buy, repair and remodel devastated houses. Give them a second life and sell them at a profit!',51,'/images/house_flipper_image_1.jpg','/images/house_flipper_image_1.jpg,/images/house_flipper_image_2.jpg','House Flipper is a unique chance to become a one-man renovation crew. Buy, repair and remodel devastated houses. Give them a second life and sell them at a profit!',14.9900,1.4900,12000,'Lifestyle and interior',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10015,'EA Sports FC 24','EA SPORTS FC™ 24 welcomes you to The World’s Game: the most true-to-football experience ever with HyperMotionV, PlayStyles optimised by Opta, and an enhanced Frostbite™ Engine.',52,'/images/fc24_image_1.jpg','/images/fc24_image_1.jpg,/images/fc24_image_2.jpg','EA SPORTS FC™ 24 welcomes you to The World’s Game: the most true-to-football experience ever with HyperMotionV, PlayStyles optimised by Opta, and an enhanced Frostbite™ Engine.',9.9900,5.9900,15600,'Football / Soccer',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10016,'NBA 2K24','Experience hoops culture in NBA 2K24. Enjoy loads of action and limitless personalized MyPLAYER options in MyCAREER. Build your perfect lineup in MyTEAM. Feel more responsive gameplay and polished visuals while playing with your favorite NBA and WNBA teams in PLAY NOW.',53,'/images/nba2k_image_1.jpg','/images/nba2k_image_1.jpg,/images/nba2k_image_2.jpg','Experience hoops culture in NBA 2K24. Enjoy loads of action and limitless personalized MyPLAYER options in MyCAREER. Build your perfect lineup in MyTEAM. Feel more responsive gameplay and polished visuals while playing with your favorite NBA and WNBA teams in PLAY NOW.',99.9900,59.9900,12002,'Basketball',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10017,'Forza Horizon 5','Explore the vibrant open world landscapes of Mexico with limitless, fun driving action in the world’s greatest cars.',54,'/images/Forza_Horizon_image_1.jpg','/images/Forza_Horizon_image_1.jpg,/images/Forza_Horizon_image_2.jpg','Explore the vibrant open world landscapes of Mexico with limitless, fun driving action in the world’s greatest cars.',32.7800,16.3900,12402,'Racing Simulation',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10018,'Life is Strange','Episode 1 now FREE! Life is Strange is an award-winning and critically acclaimed episodic adventure game that allows the player to rewind time and affect the past, present and future.',55,'/images/lis_image_1.jpg','/images/lis_image_1.jpg,/images/lis_image_2.jpg','Episode 1 now FREE! Life is Strange is an award-winning and critically acclaimed episodic adventure game that allows the player to rewind time and affect the past, present and future.',15.9900,0.0000,10924,'Point & Click',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10019,'The Witness','You wake up, alone, on a strange island full of puzzles that will challenge and surprise you.',56,'/images/witness_image_1.jpg','/images/witness_image_1.jpg,/images/witness_image_2.jpg','You wake up, alone, on a strange island full of puzzles that will challenge and surprise you.',10.9900,4.7400,10242,'Puzzle Adventure',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10020,'Hollow Knight','Forge your own path in Hollow Knight! An epic action adventure through a vast ruined kingdom of insects and heroes. Explore twisting caverns, battle tainted creatures and befriend bizarre bugs, all in a classic, hand-drawn 2D style.',57,'/images/hollow_knight_image_1.jpg','/images/hollow_knight_image_1.jpg,/images/hollow_knight_image_2.jpg','Forge your own path in Hollow Knight! An epic action adventure through a vast ruined kingdom of insects and heroes. Explore twisting caverns, battle tainted creatures and befriend bizarre bugs, all in a classic, hand-drawn 2D style.',5.9900,2.9900,10129,'Metroidvania',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10021,'Detroit: Become Human','Detroit: Become Human puts the destiny of both mankind and androids in your hands, taking you to a near future where machines have become more intelligent than humans. Every choice you make affects the outcome of the game, with one of the most intricately branching narratives ever created.',58,'/images/Detroit1.jpg','/images/Detroit1.jpg,/images/Detroit2.jpg','Detroit: Become Human puts the destiny of both mankind and androids in your hands, taking you to a near future where machines have become more intelligent than humans. Every choice you make affects the outcome of the game, with one of the most intricately branching narratives ever created.',31.9900,9.5900,10234,'Narrative Adventure',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10022,'Human Resource Machine','Program little office workers to solve puzzles. Be a good employee! The machines are coming... for your job. From the creators of World of Goo and Little Inferno.',59,'/images/Human_Resource_Machine_1.jpg','/images/Human_Resource_Machine_1jpg,/images/Human_Resource_Machine_2.jpg','Program little office workers to solve puzzles. Be a good employee! The machines are coming... for your job. From the creators of World of Goo and Little Inferno.',7.9900,2.6300,10241,'Brain Training',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10023,'Puzzle Quest 3','The original Match-3 RPG returns with an all-new adventure, bringing the classic combination of strategic puzzle battles with epic monsters, unique hero classes, and rich fantasy stories.',60,'/images/Puzzle Quest 3_1.jpg','/images/Puzzle Quest 3_1.jpg,/images/Puzzle Quest 3_2.jpg','The original Match-3 RPG returns with an all-new adventure, bringing the classic combination of strategic puzzle battles with epic monsters, unique hero classes, and rich fantasy stories.',29.9800,9.9800,10243,'Match-3',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10024,'Dead by Daylight','Trapped forever in a realm of eldritch evil where even death is not an escape, four determined Survivors face a bloodthirsty Killer in a vicious game of nerve and wits. Pick a side and step into a world of tension and terror with horror gaming\'s best asymmetrical multiplayer.',61,'/images/Dead by daylight_1.jpg','/images/Dead by daylight_1,/images/Dead by daylight_2.jpg','Trapped forever in a realm of eldritch evil where even death is not an escape, four determined Survivors face a bloodthirsty Killer in a vicious game of nerve and wits. Pick a side and step into a world of tension and terror with horror gaming\'s best asymmetrical multiplayer.',14.9900,5.9900,21241,'Survival Horror',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10025,'Dying Light 2','Humanity is fighting a losing battle against the virus. Experience a post-apocalyptic open world overrun by hordes of zombies, where your parkour and combat skills are key to survival. Traverse the City freely during the day, but watch the monsters take over during the night.',62,'/images/Dying Light 2_1.jpg','/images/Dying Light 2_1.jpg,/images/Dying Light 2_2.jpg','Humanity is fighting a losing battle against the virus. Experience a post-apocalyptic open world overrun by hordes of zombies, where your parkour and combat skills are key to survival. Traverse the City freely during the day, but watch the monsters take over during the night.',39.9900,13.1900,5325,'Zombie Survival',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18'),
                                            (10026,'Green Hell','Plunge into the open-world survival simulation set in the extreme conditions of the uncharted Amazon jungle. Use real-life survival techniques to craft, hunt, fight, and gather resources, set a makeshift shelter, or raise a fortress. Survive alone or team up with your friends and challenge the jungle together.',63,'/images/Green hell_1.jpg','/images/Green hell_1.jpg,/images/Green hell_2.jpg','Plunge into the open-world survival simulation set in the extreme conditions of the uncharted Amazon jungle. Use real-life survival techniques to craft, hunt, fight, and gather resources, set a makeshift shelter, or raise a fortress. Survive alone or team up with your friends and challenge the jungle together.',12.4900,3.1200,12415,'Crafting Survival',0,1,'2026-07-10 21:36:18',0,'2026-07-10 21:36:18');
/*!40000 ALTER TABLE `tb_carb_mall_cars_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_index_config`
--

DROP TABLE IF EXISTS `tb_carb_mall_index_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_index_config` (
  `config_id` bigint NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键id',
  `config_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '显示字符(配置搜索时不可为空，其他可为空)',
  `config_type` tinyint NOT NULL DEFAULT '0' COMMENT '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
  `cars_id` bigint NOT NULL DEFAULT '0' COMMENT '商品id 默认为0',
  `redirect_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
  `config_rank` int NOT NULL DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_index_config`
--

LOCK TABLES `tb_carb_mall_index_config` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_index_config` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_index_config` VALUES
                                               (32,'NEW Stardew Valley',4,10013,'/images/stardew_image_1.jpg',40,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (33,'NEW Don\'t Starve Together',4,10011,'/images/',40,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (34,'NEW Mayhemers',4,10001,'/images/',40,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (35,'NEW Call of Duty',4,10006,'/images/',40,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (36,'NEW Black Myth: Wukong',4,10003,'/images/',40,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (37,'RECOMMEND Hollow Knight',5,10020,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (38,'RECOMMEND Stardew Valley',5,10013,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (39,'RECOMMEND Dead by Daylight',5,10024,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (40,'RECOMMEND Persona',5,10004,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (41,'RECOMMEND Baldur\'s Gate',5,10005,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (42,'RECOMMEND Noita',5,10000,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (43,'RECOMMEND The Witness',5,10019,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (44,'RECOMMEND Puzzle Quest 3',5,10023,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (45,'RECOMMEND Dying Light 2',5,10025,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1),
                                               (46,'RECOMMEND Dead by Daylight',5,10024,'/images/',70,0,'2026-07-08 18:57:00',1,'2026-07-08 18:57:00',1);
/*!40000 ALTER TABLE `tb_carb_mall_index_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_order`
--

DROP TABLE IF EXISTS `tb_carb_mall_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单表主键id',
  `order_no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户主键id',
  `total_price` int NOT NULL DEFAULT '1' COMMENT '订单总价',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
  `pay_type` tinyint NOT NULL DEFAULT '0' COMMENT '0.无 1.支付宝支付 2.微信支付',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '订单body',
  `user_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `user_address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '收货人收货地址',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_order`
--

LOCK TABLES `tb_carb_mall_order` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_carb_mall_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_order_item`
--

DROP TABLE IF EXISTS `tb_carb_mall_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_order_item` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单主键id',
  `cars_id` bigint NOT NULL DEFAULT '0' COMMENT '关联商品id',
  `cars_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
  `cars_cover_img` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
  `selling_price` int NOT NULL DEFAULT '1' COMMENT '下单时商品的价格(订单快照)',
  `cars_count` int NOT NULL DEFAULT '1' COMMENT '数量(订单快照)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_order_item`
--

LOCK TABLES `tb_carb_mall_order_item` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_order_item` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_order_item` VALUES (1,1,10905,'Ferrari 488','/cars-img/64768a8d-0664-4b29-88c9-2626578ffbd1.jpg',240000,2,'2024-06-24 22:53:07');
/*!40000 ALTER TABLE `tb_carb_mall_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_shopping_cart_item`
--

DROP TABLE IF EXISTS `tb_carb_mall_shopping_cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_shopping_cart_item` (
  `cart_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物项主键id',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `cars_id` bigint NOT NULL DEFAULT '0' COMMENT '关联商品id',
  `cars_count` int NOT NULL DEFAULT '1' COMMENT '数量(最大为5)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`cart_item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_shopping_cart_item`
--

LOCK TABLES `tb_carb_mall_shopping_cart_item` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_shopping_cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_carb_mall_shopping_cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_carb_mall_user`
--

DROP TABLE IF EXISTS `tb_carb_mall_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carb_mall_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `nick_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `login_name` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
  `password_md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `introduce_sign` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '个性签名',
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '收货地址',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '注销标识字段(0-正常 1-已注销)',
  `locked_flag` tinyint NOT NULL DEFAULT '0' COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carb_mall_user`
--

LOCK TABLES `tb_carb_mall_user` WRITE;
/*!40000 ALTER TABLE `tb_carb_mall_user` DISABLE KEYS */;
INSERT INTO `tb_carb_mall_user` VALUES (1,'LK','13700002703','e10adc3949ba59abbe56e057f20f883e','ababababab','cd',0,0,'2024-06-22 08:44:57'),(2,'Lin','0172565469','e10adc3949ba59abbe56e057f20f883e','hello world','cd',0,0,'2024-07-07 11:57:36');
/*!40000 ALTER TABLE `tb_carb_mall_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-10 22:02:57
