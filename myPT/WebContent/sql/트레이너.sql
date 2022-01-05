-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.21 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 데이터 mypt.trainer:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` (`T_ID`, `t_pw`, `t_name`, `t_gender`, `t_email`, `t_birth`, `t_address`, `t_nick`, `t_zipcode`, `t_tel`, `t_addrdetail`, `t_photo`, `sessionid`, `cookieend`) VALUES
	('T0001', '1111', '황철순', '남성', 'hwang@gmail.com', '1985-11-11', '서울 강남구 가로수길 5  (신사동)', '철순씨', '06035', '010-1111-1111', '111', 'T0001.jpg', '', '2020-09-17 11:55:38.84'),
	('T0002', '1111', '양치승', '남성', 'yang@gmail.com', '1972-11-11', '서울 강남구 가로수길 5  (신사동)', '양치승', '06035', '010-1111-1111', '111', 'T0002.jpg', NULL, NULL),
	('T0003', '1111', '김레반', '남성', 'revi@gmail.com', '1985-11-11', '부산 강서구 가덕해안로 3  (성북동)', '레반', '46769', '010-1111-1111', '111', 'T0003.jpg', '', '2020-09-16 15:15:49.691'),
	('T0004', '1111', '유태양', '남성', 'mal@gmail.com', '1985-11-11', '서울 강남구 신사동 536-9 ', '말왕', '06035', '010-1111-1111', '111', 'T0004.jpg', '', '2020-09-16 15:13:04.119'),
	('T0005', '1111', '심으뜸', '여성', 'sim@gmail.com', '1988-11-11', '서울 강남구 신사동 537-5 ', '심으뜸', '06035', '010-1111-1111', '111', 'T0005.jpg', '', '2020-09-16 15:13:59.255');
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
