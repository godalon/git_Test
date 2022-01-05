CREATE TABLE `admin` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pw` varchar(50) DEFAULT NULL,
  `sessionid` varchar(50) DEFAULT NULL,
  `cookieend` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trainer` (
  `T_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_pw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_birth` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `t_zipcode` varchar(50) DEFAULT NULL,
  `t_tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `t_addrdetail` varchar(100) DEFAULT NULL,
  `t_photo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sessionid` varchar(50) DEFAULT NULL,
  `cookieend` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`T_ID`) USING BTREE,
  UNIQUE KEY `tnick` (`t_nick`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `user` (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PW` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `GENDER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `EMAIL` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BIRTH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ADDRESS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `QR` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `SIGNDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STARTDATE` date DEFAULT NULL,
  `ENDDATE` date DEFAULT NULL,
  `PTCOUNT` int DEFAULT NULL,
  `TID` varchar(50) DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `addrdetail` varchar(100) DEFAULT NULL,
  `sessionid` varchar(50) DEFAULT NULL,
  `cookieend` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `nick` (`nick`),
  KEY `FK_user_trainer` (`TID`),
  CONSTRAINT `FK_user_trainer` FOREIGN KEY (`TID`) REFERENCES `trainer` (`T_ID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cboard` (
  `cb_num` int NOT NULL AUTO_INCREMENT,
  `cb_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cb_writer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cb_head` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cb_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `cb_hit` int DEFAULT NULL,
  `cb_like` int DEFAULT NULL,
  `cb_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cb_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pboard` (
  `pb_num` int NOT NULL AUTO_INCREMENT,
  `pb_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pb_writer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pb_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `pb_hit` int DEFAULT NULL,
  `pb_like` int DEFAULT NULL,
  `pb_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `pb_photo` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`pb_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE `qboard` (
  `qb_num` int NOT NULL AUTO_INCREMENT,
  `qb_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `qb_writer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `qb_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `qb_hit` int DEFAULT NULL,
  `qb_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `qb_ref` int DEFAULT NULL,
  `qb_depth` int DEFAULT NULL,
  `qb_pos` int DEFAULT NULL,
  PRIMARY KEY (`qb_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;



CREATE TABLE `cblike` (
  `l_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `boardnum` int NOT NULL,
  PRIMARY KEY (`l_nick`,`boardnum`) USING BTREE,
  KEY `FK__pboard` (`boardnum`) USING BTREE,
  CONSTRAINT `FK_cblike_cboard` FOREIGN KEY (`boardnum`) REFERENCES `cboard` (`cb_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;



CREATE TABLE `ccomment` (
  `c_num` int NOT NULL AUTO_INCREMENT,
  `boardnum` int DEFAULT NULL,
  `c_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `c_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `c_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`c_num`) USING BTREE,
  KEY `FK_ccomment_cboard` (`boardnum`) USING BTREE,
  CONSTRAINT `FK_ccomment_cboard` FOREIGN KEY (`boardnum`) REFERENCES `cboard` (`cb_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `headcount` (
  `h_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OUTTIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`h_ID`,`INTIME`),
  KEY `FK_headcount_user` (`h_ID`) USING BTREE,
  CONSTRAINT `FK_headcount_user` FOREIGN KEY (`h_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `history` (
  `hid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `paydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `price` int DEFAULT NULL,
  `hcount` int DEFAULT NULL,
  `tid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hid`,`paydate`),
  KEY `FK_history_trainer` (`tid`),
  CONSTRAINT `FK_history_trainer` FOREIGN KEY (`tid`) REFERENCES `trainer` (`T_ID`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_history_user` FOREIGN KEY (`hid`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `inbody` (
  `I_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `I_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MUSCLE` float DEFAULT NULL,
  `FAT` float DEFAULT NULL,
  `HEIGHT` float DEFAULT NULL,
  `WEIGHT` float DEFAULT NULL,
  PRIMARY KEY (`I_ID`,`I_DATE`) USING BTREE,
  KEY `FK_inbody_user` (`I_ID`) USING BTREE,
  CONSTRAINT `FK_inbody_user` FOREIGN KEY (`I_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pblike` (
  `l_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `boardnum` int NOT NULL,
  PRIMARY KEY (`l_nick`,`boardnum`) USING BTREE,
  KEY `FK__pboard` (`boardnum`) USING BTREE,
  CONSTRAINT `FK__pboard` FOREIGN KEY (`boardnum`) REFERENCES `pboard` (`pb_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `pcomment` (
  `c_num` int NOT NULL AUTO_INCREMENT,
  `boardnum` int DEFAULT NULL,
  `c_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `c_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `c_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`c_num`) USING BTREE,
  KEY `FK_pcomment_pboard` (`boardnum`) USING BTREE,
  CONSTRAINT `FK_pcomment_pboard` FOREIGN KEY (`boardnum`) REFERENCES `pboard` (`pb_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE `program` (
  `p_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `p_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `p_mention` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `p_part` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`p_id`,`p_date`) USING BTREE,
  KEY `FK_program_user` (`p_id`) USING BTREE,
  CONSTRAINT `FK_program_user` FOREIGN KEY (`p_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `qcomment` (
  `c_num` int NOT NULL AUTO_INCREMENT,
  `boardnum` int DEFAULT NULL,
  `c_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `c_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `c_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`c_num`) USING BTREE,
  KEY `FK_comment_mvideo` (`boardnum`) USING BTREE,
  CONSTRAINT `FK_qcomment_qboard` FOREIGN KEY (`boardnum`) REFERENCES `qboard` (`qb_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE `schedule` (
  `s_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `s_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `s_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `s_finish` int DEFAULT '0',
  PRIMARY KEY (`s_id`,`s_date`) USING BTREE,
  KEY `FK_schedule_user` (`s_id`) USING BTREE,
  CONSTRAINT `FK_schedule_user` FOREIGN KEY (`s_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

