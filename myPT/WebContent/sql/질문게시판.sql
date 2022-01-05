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

-- 테이블 데이터 mypt.qboard:~53 rows (대략적) 내보내기
/*!40000 ALTER TABLE `qboard` DISABLE KEYS */;
INSERT INTO `qboard` (`qb_num`, `qb_title`, `qb_writer`, `qb_content`, `qb_hit`, `qb_date`, `qb_ref`, `qb_depth`, `qb_pos`) VALUES
	(4, 'ssss', '길동이', '<p>ssss</p>', 3, '2020-09-16 15:49:21', 1, 0, 0),
	(5, 'ssss', '길동이', '<p>ssss</p>', 0, '2020-09-16 15:49:30', 1, 1, 2),
	(6, 'ㄴㅁㅇㄴ', '길동이', '<p>ㄴㄴ</p>', 1, '2020-09-16 16:01:24', 6, 0, 0),
	(7, 'ㄴㅁㅇㄴ', '길동이', '<p>ㅁㄴㅇㄴㅇ</p>', 0, '2020-09-16 16:01:31', 6, 1, 2),
	(8, '질문입니다', '기안84', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:17', 8, 0, 0),
	(9, '질문입니다.', '기안84', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 9, 0, 0),
	(10, '질문입니다.', '기안84', '<p>질문입니다.</p>', 4, '2020-09-17 09:17:34', 10, 0, 0),
	(11, '질문입니다.', '호동이', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 11, 0, 0),
	(12, '질문입니다.', '두환이주먹', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 12, 0, 0),
	(13, '질문입니다.', '미나다', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 13, 0, 0),
	(14, '질문입니다.', '서장훈쓰', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 14, 0, 0),
	(15, '질문입니다.', '킹범수', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 15, 0, 0),
	(16, '질문입니다.', '대장', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 16, 0, 0),
	(17, '질문입니다.', '나얼', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 17, 0, 0),
	(18, '질문입니다.', '성훈', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 18, 0, 0),
	(19, '질문입니다.', '킹기훈', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 19, 0, 0),
	(20, '질문입니다.', '감스트', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 20, 0, 0),
	(21, '질문입니다.', '센규', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 21, 0, 0),
	(22, '질문입니다.', '탁태민', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 22, 0, 0),
	(23, '질문입니다.', '이유', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 23, 0, 0),
	(24, '질문입니다.', '비욘세', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 24, 0, 0),
	(25, '질문입니다.', '나연이', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 25, 0, 0),
	(26, '질문입니다.', '사나마나', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 26, 0, 0),
	(27, '질문입니다.', '정연이', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 27, 0, 0),
	(28, '질문입니다.', '두환이주먹', '<p>질문입니다.</p>', 0, '2020-09-17 09:17:34', 28, 0, 0),
	(29, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 29, 0, 0),
	(33, '질문입니다.', '관리자', '<p>답변</p>', 0, '2020-09-17 09:25:46', 10, 1, 2),
	(34, '질문입니다.', '관리자', '<p>답변</p>', 0, '2020-09-17 11:25:04', 29, 1, 2),
	(35, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 30, 0, 0),
	(36, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 31, 0, 0),
	(37, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 32, 0, 0),
	(38, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 33, 0, 0),
	(39, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 34, 0, 0),
	(40, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 35, 0, 0),
	(41, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 36, 0, 0),
	(42, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 37, 0, 0),
	(43, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 38, 0, 0),
	(44, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 39, 0, 0),
	(45, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 40, 0, 0),
	(46, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 41, 0, 0),
	(47, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 42, 0, 0),
	(48, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 43, 0, 0),
	(49, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 44, 0, 0),
	(50, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 45, 0, 0),
	(51, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 46, 0, 0),
	(52, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 47, 0, 0),
	(53, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 48, 0, 0),
	(54, '질문입니다.', '호동이', '<p>질문입니다.</p>', 3, '2020-09-17 09:17:34', 49, 0, 0),
	(55, '질문입니다.', '호동이', '<p>질문입니다.</p>', 4, '2020-09-17 09:17:34', 50, 0, 0),
	(56, '질문입니다.', '호동이', '<p>질문입니다.</p>', 4, '2020-09-17 09:17:34', 51, 0, 0),
	(57, '질문입니다.', '호동이', '<p>질문입니다.</p>', 4, '2020-09-17 09:17:34', 52, 0, 0),
	(58, '질문입니다.', '호동이', '<p>질문입니다.</p>', 4, '2020-09-17 09:17:34', 53, 0, 0),
	(59, '질문입니다.', '호동이', '<p>질문입니다.</p>', 4, '2020-09-17 09:17:34', 54, 0, 0),
	(61, '질문입니다.', '관리자', '<p>답변</p>', 0, '2020-09-17 13:17:51', 53, 1, 2),
	(62, '질문입니다.', '관리자', '<p>답변</p>', 0, '2020-09-17 13:18:02', 52, 1, 2),
	(63, '질문입니다.', '관리자', '<p>답변</p>', 0, '2020-09-17 13:18:07', 51, 1, 2),
	(64, '질문입니다.', '관리자', '<p>답변</p>', 0, '2020-09-17 13:18:13', 50, 1, 2);
/*!40000 ALTER TABLE `qboard` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
