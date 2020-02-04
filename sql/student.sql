/*
Navicat MySQL Data Transfer

Source Server         : MySpringDemo
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : springboot5

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-02-04 23:15:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `score` double(11,0) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '张三', '99', '2020-02-02');
INSERT INTO `student` VALUES ('2', '李四', '88', '1999-02-04');
INSERT INTO `student` VALUES ('3', '王五', '100', '1999-02-03');
