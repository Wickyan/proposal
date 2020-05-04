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

 Date: 05/05/2020 02:42:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员名',
  `admin_psw` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `管理员名`(`admin_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123', 0, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '经济与管理系', NULL, 0, 1);
INSERT INTO `dept` VALUES (2, '机电与材料工程系', NULL, 0, 1);
INSERT INTO `dept` VALUES (3, '信息与电气工程系', NULL, 0, 1);
INSERT INTO `dept` VALUES (4, '建筑工程系', NULL, 0, 1);
INSERT INTO `dept` VALUES (5, '计算机系', NULL, 0, 1);
INSERT INTO `dept` VALUES (6, '外国语言文化系', NULL, 0, 1);
INSERT INTO `dept` VALUES (7, '文学与艺术系', NULL, 0, 1);
INSERT INTO `dept` VALUES (8, '基础教学部', NULL, 0, 1);
INSERT INTO `dept` VALUES (9, '公共体育部', NULL, 0, 1);
INSERT INTO `dept` VALUES (10, '党政办公室', NULL, 0, 1);
INSERT INTO `dept` VALUES (11, '图档网信办公室', NULL, 0, 1);
INSERT INTO `dept` VALUES (12, '教务处', NULL, 0, 1);
INSERT INTO `dept` VALUES (13, '学生工作处', NULL, 0, 1);
INSERT INTO `dept` VALUES (14, '招生就业处', NULL, 0, 1);
INSERT INTO `dept` VALUES (15, '总务处', NULL, 0, 1);
INSERT INTO `dept` VALUES (101, '经济与管理系2', NULL, 0, 1);
INSERT INTO `dept` VALUES (102, '撒擦三次', NULL, 1, 1);
INSERT INTO `dept` VALUES (103, '机电与材料工程系', NULL, 1, 1);
INSERT INTO `dept` VALUES (104, 'xinzeng', NULL, 1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES (1, 1, 10, 22000020, '2020-04-24 22:17:34', '党政办公室回复', NULL, NULL, 0, 1);
INSERT INTO `reply` VALUES (2, 16, 12, 22000023, '2020-04-25 16:34:36', '很好的建议', NULL, NULL, 0, 1);
INSERT INTO `reply` VALUES (3, 3, 10, 22000020, '2020-04-29 14:53:20', '同意', NULL, NULL, 0, 1);
INSERT INTO `reply` VALUES (4, 22, 15, 22000027, '2020-04-30 00:36:08', '同学：您好！\r\n\r\n    感谢您对饮食工作的关注与支持。关于“文昌食堂卫生和价格”的问题饮食中心非常重视。针对同学提出的问题，饮食服务中心逐一分析解决，对于“一份米饭两个青菜9块钱”的情况，饮食中心已严格要求文昌校区所有餐厅售卖的蔬菜类菜品价格均不得高于3元/份；针对“吃出创可贴和铁丝”、“尝了一口酸菜又倒了回去”、“炸过的油又倒回油桶”的情况，饮食服务中心已经规范文昌所有餐厅的操作流程严格按照《饮食服务中心制度》执行，如再发生类似事件，严惩不贷；“喝汤的碗总是洗不干净”已让文昌校区各餐厅严格按照《总务部餐（饮）具清洗消毒办法》洗消，总务部质量监管部会不定期对各餐厅的餐具进行抽检，抽检分为测餐具微生物标准和餐具食物残留度；“餐厅苍蝇乱飞”已经采取了具体措施，如更换门帘和纱窗，及时打药。再次欢迎同学提出宝贵意见并予以监督。联系电话：0516-83592306。\r\n\r\n    谢谢您的问题反馈，祝生活愉快！\r\n\r\n总务部饮食服务中心', NULL, NULL, 0, 1);
INSERT INTO `reply` VALUES (5, 24, 15, 22000027, '2020-05-02 18:50:10', '同学：\r\n\r\n    你好！关于你建议的公教区“友情提醒”清理和部分同学挪用座椅的问题，该友情提醒是各学生组织根据不同需要进行的宣传，近期我们将安排物业管理公司进行分期、分批做清除工作，并严格控制乱张贴问题；对座椅搬动问题，要求物业对公教区座椅加强管理，在此也呼吁同学们不要随意移动桌椅，以免影响正常教学。\r\n\r\n    感谢你的关注及建议，联系电话：83590328。\r\n总务部公房与物业管理办公室', NULL, NULL, 0, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交送' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resend
-- ----------------------------
INSERT INTO `resend` VALUES (1, 1, 5, 0, 0, '系统移交', '2020-04-24 19:00:33', 0, '2020-04-24 19:00:33', NULL, 0, 1);
INSERT INTO `resend` VALUES (2, 1, 12, 1, 22160425, '移交给教务处', '2020-04-24 22:11:10', 22000023, '2020-04-24 22:12:46', '教务处回答不了', 0, 1);
INSERT INTO `resend` VALUES (3, 1, 10, 2, 22160425, '给党政办公室回复', '2020-04-24 22:13:16', 0, '2020-04-24 22:13:16', NULL, 0, 1);
INSERT INTO `resend` VALUES (4, 2, 12, 0, 0, '系统移交', '2020-04-25 14:59:01', 0, '2020-04-25 14:59:01', NULL, 0, 1);
INSERT INTO `resend` VALUES (5, 3, 10, 0, 0, '系统移交', '2020-04-25 15:02:26', 0, '2020-04-25 15:02:26', NULL, 0, 1);
INSERT INTO `resend` VALUES (6, 4, 12, 0, 0, '系统移交', '2020-04-25 15:02:43', 0, '2020-04-25 15:02:43', NULL, 0, 1);
INSERT INTO `resend` VALUES (7, 5, 12, 0, 0, '系统移交', '2020-04-25 15:02:45', 0, '2020-04-25 15:02:45', NULL, 0, 1);
INSERT INTO `resend` VALUES (8, 6, 12, 0, 0, '系统移交', '2020-04-25 15:02:52', 0, '2020-04-25 15:02:52', NULL, 0, 1);
INSERT INTO `resend` VALUES (9, 7, 9, 0, 0, '系统移交', '2020-04-25 15:09:53', 0, '2020-04-25 15:09:53', NULL, 0, 1);
INSERT INTO `resend` VALUES (10, 8, 9, 0, 0, '系统移交', '2020-04-25 15:10:42', 0, '2020-04-25 15:10:42', NULL, 0, 1);
INSERT INTO `resend` VALUES (11, 9, 9, 0, 0, '系统移交', '2020-04-25 15:10:50', 0, '2020-04-25 15:10:50', NULL, 0, 1);
INSERT INTO `resend` VALUES (12, 10, 9, 0, 0, '系统移交', '2020-04-25 15:11:10', 0, '2020-04-25 15:11:10', NULL, 0, 1);
INSERT INTO `resend` VALUES (13, 11, 9, 0, 0, '系统移交', '2020-04-25 15:11:17', 0, '2020-04-25 15:11:17', NULL, 0, 1);
INSERT INTO `resend` VALUES (14, 12, 15, 0, 0, '系统移交', '2020-04-25 15:13:21', 0, '2020-04-25 15:13:21', NULL, 0, 1);
INSERT INTO `resend` VALUES (15, 13, 15, 0, 0, '系统移交', '2020-04-25 15:14:25', 0, '2020-04-25 15:14:25', NULL, 0, 1);
INSERT INTO `resend` VALUES (16, 14, 15, 0, 0, '系统移交', '2020-04-25 15:14:35', 0, '2020-04-25 15:14:35', NULL, 0, 1);
INSERT INTO `resend` VALUES (17, 15, 2, 0, 0, '系统移交', '2020-04-25 15:19:02', 0, '2020-04-25 15:19:02', NULL, 0, 1);
INSERT INTO `resend` VALUES (18, 16, 12, 0, 0, '系统移交', '2020-04-25 16:29:53', 0, '2020-04-25 16:29:53', NULL, 0, 1);
INSERT INTO `resend` VALUES (19, 16, 10, 1, 22000023, '不能回答', '2020-04-25 16:32:55', 22000020, '2020-04-25 16:34:10', '我也不能回答', 0, 1);
INSERT INTO `resend` VALUES (20, 17, 9, 0, 0, '系统移交', '2020-04-25 20:53:37', 0, '2020-04-25 20:53:37', NULL, 0, 1);
INSERT INTO `resend` VALUES (21, 18, 11, 0, 0, '系统移交', '2020-04-25 20:55:28', 0, '2020-04-25 20:55:28', NULL, 0, 1);
INSERT INTO `resend` VALUES (22, 19, 15, 0, 0, '系统移交', '2020-04-29 18:12:55', 0, '2020-04-29 18:12:55', NULL, 0, 1);
INSERT INTO `resend` VALUES (23, 20, 13, 0, 0, '系统移交', '2020-04-29 18:15:46', 0, '2020-04-29 18:15:46', NULL, 0, 1);
INSERT INTO `resend` VALUES (24, 21, 12, 0, 0, '系统移交', '2020-04-29 23:51:12', 0, '2020-04-29 23:51:12', NULL, 0, 1);
INSERT INTO `resend` VALUES (25, 22, 15, 0, 0, '系统移交', '2020-04-29 23:57:14', 0, '2020-04-29 23:57:14', NULL, 0, 1);
INSERT INTO `resend` VALUES (26, 23, 12, 0, 0, '系统移交', '2020-04-30 00:01:21', 0, '2020-04-30 00:01:21', NULL, 0, 1);
INSERT INTO `resend` VALUES (27, 20, 15, 1, 22000026, '总务处的事', '2020-04-30 00:25:35', 0, '2020-04-30 00:25:35', NULL, 0, 1);
INSERT INTO `resend` VALUES (28, 12, 2, 1, 22000027, '...', '2020-04-30 00:46:22', 0, '2020-04-30 00:46:22', NULL, 0, 1);
INSERT INTO `resend` VALUES (29, 24, 12, 0, 0, '系统移交', '2020-05-02 18:47:29', 0, '2020-05-02 18:47:29', NULL, 0, 1);
INSERT INTO `resend` VALUES (30, 24, 15, 1, 22000023, '归总务处处理', '2020-05-02 18:49:05', 0, '2020-05-02 18:49:05', NULL, 0, 1);
INSERT INTO `resend` VALUES (31, 23, 15, 1, 22000023, '教材事宜归总务处管理', '2020-05-05 01:11:39', 0, '2020-05-05 01:11:39', NULL, 0, 1);

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
  `resend_dept` bigint(0) NULL DEFAULT NULL COMMENT '移交部门ID',
  `read_count` int(0) NULL DEFAULT 0 COMMENT '点击数',
  `reply_id` bigint(0) NULL DEFAULT -1 COMMENT '回复ID',
  `audited` tinyint(1) NULL DEFAULT 0 COMMENT '被审核过',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT '冻结提案',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, 22160424, '2020-04-24 19:00:33', 5, '综合测试：计算机系', '发给计算机系的测试', 10, 22, 1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (2, 221604241, '2020-04-25 14:59:01', 12, '给教务处的建议', '材料学生给教务处的建议', 12, 1, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (3, 221604241, '2020-04-25 15:02:26', 10, '给党政的建议', '材料学生给党政的建议', 10, 21, 3, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (4, 221604241, '2020-04-25 15:02:43', 12, '给教务处的建议', '材料学生给教务处的建议', 12, 0, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (5, 221604241, '2020-04-25 15:02:45', 12, '给教务处的建议', '材料学生给教务处的建议', 12, 1, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (6, 221604241, '2020-04-25 15:02:52', 12, '给教务处的建议2', '材料学生给教务处的建议2', 12, 2, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (7, 221604241, '2020-04-25 15:09:53', 9, '给体育的提案', '材料学生给体育的提案', 9, 0, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (8, 221604241, '2020-04-25 15:10:42', 9, '给体育的提案', '材料学生给体育的提案', 9, 1, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (9, 221604241, '2020-04-25 15:10:50', 9, '给体育的提案2', '材料学生给体育的提案2', 9, 0, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (10, 221604241, '2020-04-25 15:11:10', 9, '给体育的提案2', '材料学生给体育的提案2', 9, 1, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (11, 221604241, '2020-04-25 15:11:17', 9, '给体育的提案3', '材料学生给体育的提案3', 9, 0, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (12, 221604241, '2020-04-25 15:13:21', 15, '材料学生提问总务处', '材料学生提问总务处', 2, 4, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (13, 221604241, '2020-04-25 15:14:25', 15, '材料学生提问总务处', '材料学生提问总务处', 15, 0, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (14, 221604241, '2020-04-25 15:14:35', 15, '材料学生提问总务处2', '材料学生提问总务处2', 15, 4, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (15, 221604241, '2020-04-25 15:19:02', 2, '材料学生提问材料', '材料学生提问材料', 2, 7, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (16, 22160424, '2020-04-25 16:29:53', 12, '给教务处的建议', '给教务处的建议', 12, 11, 2, 1, 1, 0, 1);
INSERT INTO `topic` VALUES (17, 22160424, '2020-04-25 20:53:37', 9, 'zxc', 'sdfg', 9, 3, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (18, 22160424, '2020-04-25 20:55:28', 11, '非常的吧', 'sdad', 11, 2, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (19, 22160424, '2020-04-29 18:12:55', 15, '关于物流园需要使用“身份码”取件的疑问', '     老师：\r\n    你好，作为一名学生我还是挺纳闷的，学校的物流园是以菜鸟驿站的形式合作没有错，寄快递多几块钱我也无话可说，但现在为什么连取个快递都需要用所谓的“身份码”来取件了？\r\n    虽然现在还保留有使用校园卡拍照取件的方式，但是工作人员会优先让取件人使用所谓的“身份码”来收快递，非常不方便。我觉得这样可能让物流园服务于学校师生的本质有些颠倒了。如果我没有拿手机或者我不愿意使用身份码，我就不能够取件吗？难道每一次我不愿意使用身份码来取件的时候，都需要听取工作人员的一番劝阻之后才能够以校园卡的形式取件吗？\r\n    为了拿个快递使用所谓的“身份码”，便捷程度甚至比不上丰巢快递柜，最后还是帮支付宝和手机淘宝app刷每日使用量，那为什么又要设置专门的物流园呢？', 15, 0, -1, 0, 1, 0, 1);
INSERT INTO `topic` VALUES (20, 22160424, '2020-04-29 18:15:46', 13, '关于物流园需要使用“身份码”取件的疑问', '     老师：\r\n    你好，作为一名学生我还是挺纳闷的，学校的物流园是以菜鸟驿站的形式合作没有错，寄快递多几块钱我也无话可说，但现在为什么连取个快递都需要用所谓的“身份码”来取件了？\r\n    虽然现在还保留有使用校园卡拍照取件的方式，但是工作人员会优先让取件人使用所谓的“身份码”来收快递，非常不方便。我觉得这样可能让物流园服务于学校师生的本质有些颠倒了。如果我没有拿手机或者我不愿意使用身份码，我就不能够取件吗？难道每一次我不愿意使用身份码来取件的时候，都需要听取工作人员的一番劝阻之后才能够以校园卡的形式取件吗？\r\n    为了拿个快递使用所谓的“身份码”，便捷程度甚至比不上丰巢快递柜，最后还是帮支付宝和手机淘宝app刷每日使用量，那为什么又要设置专门的物流园呢？', 15, 13, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (21, 22160424, '2020-04-29 23:51:12', 12, '教室何时开放供自主学习', '老师，想请问下教室何时开放，宿舍过于闭塞，学习效率也不高，而且宿舍是人挨着人，传播风险也不小，开放部分教室供自主学习一定程度上可以增大人与人之间的距离，某种意义上说应该是可行的。', 12, 7, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (22, 22160424, '2020-04-29 23:57:14', 15, '关于文昌食堂卫生和价格问题', '老师你好：\r\n\r\n    这学期以来文昌这边的食堂普遍涨价，甚至一份米饭两个青菜9块钱。但是价格上去了，但是卫生和服务似乎还是老样子。上学期有同学吃出创可贴，我之前亲眼看见学苑食堂大妈尝了一口酸菜又倒了回去，吃到刷过铁丝也是常有的。清真食堂喝汤的碗总是洗不干净，沾有米粒。风味餐厅苍蝇乱飞，落在饼上大妈们也视而不见，我赶走苍蝇，建议大妈把那个饼拿走，可是她却装作没听见，任由同学们和苍蝇分享食物。有厨师把炸过的油又倒回油桶！试问这样的情况下还涨价，是不是说不过去了呀？希望总务这边能重视一下，要不搞得文昌校区这边不像是亲儿子！谢谢！\r\n', 15, 20, 4, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (23, 221604241, '2020-04-30 00:01:21', 12, '关于教材退订的问题', '老师：\r\n\r\n您好！\r\n\r\n疫情期间上半学期的课程基本已经上完，想麻烦问下上学期订的教材是否可以退？', 15, 23, -1, 1, 0, 0, 1);
INSERT INTO `topic` VALUES (24, 22160425, '2020-05-02 18:47:29', 12, '关于清理公教区杂乱的“友情提醒”的建议', '老师：\r\n    您好！公教区教室内外贴有多种多样的“友情提醒”，涉及关灯、关门、静音乃至穿秋裤等内容。大部分由各班级同学张贴，虽用心良好，但张贴随意、内容不规范、甚至重复，且贴纸品质很差，与学校的高水平管理有较大差距。公教区不是各班级刷存在感的理想场所，教室的墙也不是公告牌。\r\n    另外，部分同学把教室内座椅搬到走廊、楼梯拐角等处私用，也需及时清理归位，对于学生的不当行为也应予以教育。\r\n    一点不成熟的建议，希望学校越来越好！', 15, 54, 5, 1, 0, 0, 1);

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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (22000015, '员工1', 15, NULL, '321', 0, '123123', '22000015@cumt.edu.cm', 2, NULL, '2020-04-14 18:01:42', 0, 1, 1);
INSERT INTO `user` VALUES (22000016, '员工2', 15, NULL, '321', 0, '123123234', '12323@12344', 3, NULL, '2020-04-19 13:00:36', 0, 1, 1);
INSERT INTO `user` VALUES (22000017, '员工3', 15, NULL, '321', 0, '', '温热去', 2, NULL, '2020-04-19 13:01:31', 0, 1, 1);
INSERT INTO `user` VALUES (22000018, '教务处回复人2', 15, NULL, '321', 0, '123123', '', 3, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000019, '教务处回复人', 12, NULL, '321', 0, '123123213434', '', 3, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000020, '党政回复人', 10, NULL, '321', 0, '1231232312', '121233@cumt', 3, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000021, '教务 回复人', 12, NULL, '321', 0, '', '', 3, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000022, '教务处回复人4', 12, NULL, '321', 0, '', '', 2, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000023, '教务处回复人3', 12, NULL, '321', 0, '', '', 3, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000025, '材料回复人', 2, '12312312321321', '321321', 0, '', '', 3, '2020-04-25 15:31:02', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000026, '学生工作处回复人', 13, '123123123123', '123123', 0, '', '', 3, '2020-04-30 00:11:08', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22000027, '总务处回复人', 15, '123123123123', '123123', 0, '', '', 3, '2020-04-30 00:27:02', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22121267, '基础教学教师', 8, '1234554563252354', '123', 0, '12314543', 'ashd@ahsui.com', 2, '2020-04-02 02:04:20', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22160424, '严伟奇', 5, NULL, '123', 0, '12312314', '213@12312.com', 1, NULL, '2020-05-04 23:54:22', 0, 0, 1);
INSERT INTO `user` VALUES (22160425, '计算机教师', 5, NULL, '123', 0, '123123', 'ashd@ahhd.com', 2, NULL, '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (221604241, '材料学生', 2, '12312312312', '123', 0, '123123123', '1231231@hd.com', 1, '2020-04-14 00:11:48', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (221604243, '材料 教师', 2, '12312312312', '123', 0, '123123', 'Test@asd.com', 2, '2020-04-14 00:13:19', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (2200002122, '经管系学生', 1, '12312312312', '123', 0, '123123123', 'ashd@hd.com', 1, '2020-04-14 00:10:46', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (2200002123, '经管系教师', 1, '12312312312', '123', 0, '123123123', 'ashd@hd.com', 2, '2020-04-14 00:08:38', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22160424123, '建筑系教师', 4, '12312312312', '123', 0, '123123213434', 'ashd@hd.com', 2, '2020-04-14 01:47:59', '2020-04-30 22:49:45', 0, 0, 1);
INSERT INTO `user` VALUES (22160424123123, 'wickyan', 14, '12312312312', '123', 0, '123123', '测试第', 3, '2020-04-14 01:48:15', '2020-04-16 17:15:08', 1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
