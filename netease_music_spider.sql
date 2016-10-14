/*
Navicat MySQL Data Transfer

Source Server         : baiye.website
Source Server Version : 50714
Source Host           : 182.92.198.241:3306
Source Database       : netease_music_spider

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-10-14 15:17:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for DuplicateRemoval
-- ----------------------------
DROP TABLE IF EXISTS `DuplicateRemoval`;
CREATE TABLE `DuplicateRemoval` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `md5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `md5` (`md5`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=827660 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for MusicInfo
-- ----------------------------
DROP TABLE IF EXISTS `MusicInfo`;
CREATE TABLE `MusicInfo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(1000) NOT NULL,
  `Artist` varchar(1000) NOT NULL,
  `Album` varchar(1000) NOT NULL,
  `CommentCount` bigint(255) NOT NULL,
  `URL` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE,
  KEY `CommentCount` (`CommentCount`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=827090 DEFAULT CHARSET=utf8mb4;
