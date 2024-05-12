/*
 Navicat Premium Data Transfer

 Source Server         : db_balance
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3308
 Source Schema         : db_balance

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 12/05/2024 15:14:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `processflag` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'en procesos etl indica estados de preprocesamiento',
  `insert_platform` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT 'base_array.type=platform (id_pk) desde que plataforma se ha realizado la creacion, mobile, web, api',
  `insert_user` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'creador (no necesariamente en base_user) puede ser un proceso ETL',
  `insert_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'datetime de la creacion',
  `update_platform` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_user` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_platform` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `delete_user` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `cru_csvnote` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_erpsent` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT 'en procesos etl indica si se ha exportado',
  `is_enabled` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT 'bloquea por completo el archivo',
  `i` int DEFAULT NULL COMMENT 'en procesos de etl con disp offline el autoid en la máquina',
  
  
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code_erp` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `secret` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fullname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL COMMENT 'comprobar mayoria de edad',
  `id_parent` int DEFAULT NULL COMMENT 'base_user.id quien es su superior',
  `id_gender` int DEFAULT NULL COMMENT 'app_array.type=gender',
  `id_nationality` int DEFAULT NULL COMMENT 'app_array.type=nationality',
  `id_country` int DEFAULT NULL COMMENT 'app_array.type=country',
  `id_language` int NOT NULL DEFAULT '1' COMMENT 'app_array.type=language id_pk',
  `id_profile` int NOT NULL DEFAULT '2' COMMENT 'base_array.type=profile perfil 1 root, 2 sys admin, 3 business owner, 4 busines manager',
  `url_picture` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date_validated` datetime DEFAULT NULL,
  `log_attempts` int NOT NULL DEFAULT '0',


  
  PRIMARY KEY (`id`) USING BTREE,
  KEY `delete_date_idx` (`delete_date`) USING BTREE,
  KEY `is_enabled_idx` (`is_enabled`) USING BTREE,
  KEY `uuid_idx` (`uuid`) USING BTREE,
  KEY `email_idx` (`email`) USING BTREE,
  KEY `secret_idx` (`secret`) USING BTREE,
  KEY `description_idx` (`description`) USING BTREE,
  KEY `id__uuid_idx` (`id`,`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
