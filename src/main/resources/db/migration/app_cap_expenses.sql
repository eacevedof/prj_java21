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

 Date: 05/05/2024 00:45:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_cap_expenses
-- ----------------------------
DROP TABLE IF EXISTS `app_cap_expenses`;
CREATE TABLE `app_cap_expenses` (
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
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'titulo',
  `payment_for` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'en concepto de que',
  `payed_to` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'quien ha realizado el pago',
  `expense_date` datetime DEFAULT NULL COMMENT 'cuando se efectuo',
  `amount` decimal(10,3) NOT NULL,
  `notes` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_owner` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `delete_date_idx` (`delete_date`) USING BTREE,
  KEY `is_enabled_idx` (`is_enabled`) USING BTREE,
  KEY `uuid_idx` (`uuid`) USING BTREE,
  KEY `type_idx` (`amount`) USING BTREE,
  KEY `id_pk_idx` (`notes`) USING BTREE,
  KEY `description_idx` (`description`) USING BTREE,
  KEY `id_owner` (`id_owner`) USING BTREE,
  KEY `id__type_idx` (`id`,`amount`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
