/*
 Navicat Premium Data Transfer

 Source Server         : maria-univ
 Source Server Type    : MySQL
 Source Server Version : 110002 (11.0.2-MariaDB-1:11.0.2+maria~ubu2204)
 Source Host           : localhost:3306
 Source Schema         : db_mypromos

 Target Server Type    : MySQL
 Target Server Version : 110002 (11.0.2-MariaDB-1:11.0.2+maria~ubu2204)
 File Encoding         : 65001

 Date: 04/05/2024 23:27:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_array
-- ----------------------------
DROP TABLE IF EXISTS `app_array`;
CREATE TABLE `app_array` (
  `processflag` varchar(5) DEFAULT NULL COMMENT 'en procesos etl indica estados de preprocesamiento',
  `insert_platform` varchar(3) DEFAULT '1' COMMENT 'base_array.type=platform (id_pk) desde que plataforma se ha realizado la creacion, mobile, web, api',
  `insert_user` varchar(15) DEFAULT NULL COMMENT 'creador (no necesariamente en base_user) puede ser un proceso ETL',
  `insert_date` datetime DEFAULT current_timestamp() COMMENT 'datetime de la creacion',
  `update_platform` varchar(3) DEFAULT NULL,
  `update_user` varchar(15) DEFAULT NULL,
  `update_date` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_platform` varchar(3) DEFAULT NULL,
  `delete_user` varchar(15) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `cru_csvnote` varchar(500) DEFAULT NULL,
  `is_erpsent` varchar(3) DEFAULT '0' COMMENT 'en procesos etl indica si se ha exportado',
  `is_enabled` varchar(3) DEFAULT '1' COMMENT 'bloquea por completo el archivo',
  `i` int(11) DEFAULT NULL COMMENT 'en procesos de etl con disp offline el autoid en la máquina',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) DEFAULT NULL,
  `code_erp` varchar(25) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `id_pk` varchar(25) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `id_owner` int(11) NOT NULL DEFAULT -1,
  `order_by` int(5) DEFAULT 100,
  PRIMARY KEY (`id`),
  KEY `delete_date_idx` (`delete_date`),
  KEY `is_enabled_idx` (`is_enabled`),
  KEY `uuid_idx` (`uuid`),
  KEY `type_idx` (`type`),
  KEY `id_pk_idx` (`id_pk`),
  KEY `description_idx` (`description`),
  KEY `id_owner` (`id_owner`),
  KEY `order_by_idx` (`order_by`),
  KEY `id__type_idx` (`id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=889 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
