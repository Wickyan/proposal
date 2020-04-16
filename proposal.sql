/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : proposal

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/04/2020 17:40:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` bigint(0) NULL DEFAULT NULL COMMENT '管理员名',
  `admin_psw` bigint(0) NULL DEFAULT NULL COMMENT '密码',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `管理员名`(`admin_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `dept_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '主管ID',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pubinfos
-- ----------------------------
DROP TABLE IF EXISTS `pubinfos`;
CREATE TABLE `pubinfos`  (
  `pub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '公告表ID',
  `pub_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `pub_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告内容',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `read_count` int(0) NULL DEFAULT NULL COMMENT '点击数',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`pub_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `reply_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '回复ID',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '提案ID',
  `dept_id` bigint(0) NULL DEFAULT -1 COMMENT '回复部门',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '回复人ID',
  `reply_time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `reply_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  `reply_score` int(0) NULL DEFAULT NULL COMMENT '提案人打分',
  `reply_evaluation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提案人评价',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resend
-- ----------------------------
DROP TABLE IF EXISTS `resend`;
CREATE TABLE `resend`  (
  `resend_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '交送ID',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '提案ID',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '交送部门ID',
  `resend_count` int(0) NULL DEFAULT 0 COMMENT '交送次数',
  `user_id` bigint(0) NULL DEFAULT 0 COMMENT '交送人ID',
  `resend_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '系统移交' COMMENT '交送原因',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '交送时间',
  `back_user_id` bigint(0) NULL DEFAULT 0 COMMENT '退回人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '退回时间',
  `back_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退回原因',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`resend_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交送' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `topic_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '提案ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '提案人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '接受部门ID',
  `topic_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提案标题',
  `topic_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提案内容',
  `read_count` int(0) NULL DEFAULT 0 COMMENT '点击数',
  `reply_id` bigint(0) NULL DEFAULT -1 COMMENT '回复ID',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门ID',
  `idcard_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `user_psw` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `psw_changed` tinyint(1) NULL DEFAULT 0 COMMENT '是否修改密码',
  `mobil` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role` int(0) NULL DEFAULT NULL COMMENT '角色',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT '账户不可用',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
