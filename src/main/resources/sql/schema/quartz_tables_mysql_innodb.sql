CREATE TABLE `posts` (
  `postsid` int(11) NOT NULL,
  `inserttime` datetime DEFAULT NULL,
  `poststitle` varchar(255) DEFAULT NULL,
  `poststype` varchar(255) NOT NULL,
  `postsurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`postsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of posts
-- ----------------------------
INSERT INTO `posts` VALUES ('3989339', '2019-06-22 10:32:36', '11', '11', '1');
INSERT INTO `posts` VALUES ('4157523', '2019-09-18 11:34:17', '22', '22', '22');
