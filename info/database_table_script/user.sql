/*
Navicat MySQL Data Transfer

Source Server         : Local MySQL
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : kothamala

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-12-27 21:43:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password_hash` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `activation_key` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reset_key` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
