/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : zxhtrecord

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2017-12-12 23:40:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for day
-- ----------------------------
DROP TABLE IF EXISTS `day`;
CREATE TABLE `day` (
  `did` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `desc` text COMMENT '当日备注',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=20171212 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of day
-- ----------------------------
INSERT INTO `day` VALUES ('20171210', '11');
INSERT INTO `day` VALUES ('20171211', '12');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `rid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `did` int(11) unsigned NOT NULL COMMENT '记录所属的日期编号',
  `starttime` time NOT NULL,
  `endtime` time NOT NULL,
  `explain` text NOT NULL COMMENT '记录说明',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '20171208', '09:30:00', '12:40:00', '完成我的订单的页面');
INSERT INTO `record` VALUES ('2', '20171208', '13:10:00', '17:10:00', '更改订单详情页');
INSERT INTO `record` VALUES ('3', '20171206', '11:10:00', '12:50:00', '添加块状商品列表组件');
INSERT INTO `record` VALUES ('4', '20171206', '13:30:00', '16:30:00', '修改块状商品列表样式');
