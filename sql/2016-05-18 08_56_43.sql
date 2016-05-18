-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.13-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 elective_system_v1 的数据库结构
CREATE DATABASE IF NOT EXISTS `elective_system_v1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `elective_system_v1`;


-- 导出  表 elective_system_v1.c 结构
CREATE TABLE IF NOT EXISTS `c` (
  `c_id` varchar(50) NOT NULL,
  `c_name` varchar(50) NOT NULL,
  `c_credit` int(11) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

-- 正在导出表  elective_system_v1.c 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `c` DISABLE KEYS */;
INSERT INTO `c` (`c_id`, `c_name`, `c_credit`) VALUES
	('3001', '大学物理1', 4),
	('3002', '大学物理2', 4),
	('3003', '高级办公自动化', 2),
	('3004', '高等数学1', 6),
	('3005', '高等数学2', 6),
	('3006', '体育', 1),
	('3007', '数据结构', 4),
	('3008', '大学英语', 4),
	('3009', '面向对象程序设计', 4),
	('3010', '数据库原理', 4),
	('3011', '魔鬼三人特训', 2),
	('3012', '离散数学', 4);
/*!40000 ALTER TABLE `c` ENABLE KEYS */;


-- 导出  过程 elective_system_v1.create_term 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_term`(IN term_year int, IN term_summer char(1))
BEGIN
	SET @month_start = 0;
	SET @month_end = 0;
	IF(term_summer = 'C') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 3, '_', 1)), DATE(CONCAT(term_year, '_', 6, '_', 30))); 
	END IF;
	IF(term_summer = 'X') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 7, '_', 1)), DATE(CONCAT(term_year, '_', 7, '_', 31))); 
	END IF;
	IF(term_summer = 'Q') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 9, '_', 1)), DATE(CONCAT(term_year, '_', 11, '_', 30))); 
	END IF;
	IF(term_summer = 'D') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 12, '_', 1)), DATE(CONCAT(term_year + 1, '_', 2, '_', 28))); 
	END IF;
END//
DELIMITER ;


-- 导出  表 elective_system_v1.d 结构
CREATE TABLE IF NOT EXISTS `d` (
  `d_term` varchar(50) NOT NULL,
  `d_begin` date NOT NULL,
  `d_end` date NOT NULL,
  `d_sel_begin` timestamp NULL DEFAULT NULL,
  `d_sel_end` timestamp NULL DEFAULT NULL,
  `d_reg_begin` timestamp NULL DEFAULT NULL,
  `d_reg_end` timestamp NULL DEFAULT NULL,
  `d_inq_begin` timestamp NULL DEFAULT NULL,
  `d_inq_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`d_term`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日程';

-- 正在导出表  elective_system_v1.d 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `d` DISABLE KEYS */;
INSERT INTO `d` (`d_term`, `d_begin`, `d_end`, `d_sel_begin`, `d_sel_end`, `d_reg_begin`, `d_reg_end`, `d_inq_begin`, `d_inq_end`) VALUES
	('2016-C', '2016-03-01', '2016-05-31', '2016-03-01 08:10:00', '2016-05-31 08:00:00', '2016-03-01 08:00:00', '2016-05-31 08:00:00', '2016-03-01 08:00:00', '2016-05-31 08:00:00'),
	('2016-D', '2016-12-01', '2017-02-28', NULL, NULL, NULL, NULL, NULL, NULL),
	('2016-Q', '2016-09-01', '2016-11-30', NULL, NULL, NULL, NULL, NULL, NULL),
	('2016-X', '2016-07-01', '2016-07-31', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `d` ENABLE KEYS */;


-- 导出  表 elective_system_v1.m 结构
CREATE TABLE IF NOT EXISTS `m` (
  `m_id` varchar(50) NOT NULL,
  `m_name` varchar(50) NOT NULL,
  `m_pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';

-- 正在导出表  elective_system_v1.m 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `m` DISABLE KEYS */;
INSERT INTO `m` (`m_id`, `m_name`, `m_pwd`) VALUES
	('4001', '诸葛鑫鑫', '4001');
/*!40000 ALTER TABLE `m` ENABLE KEYS */;


-- 导出  表 elective_system_v1.o 结构
CREATE TABLE IF NOT EXISTS `o` (
  `o_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `c_id` varchar(50) NOT NULL,
  `t_id` varchar(50) NOT NULL,
  `d_term` varchar(50) NOT NULL,
  `o_room` varchar(50) NOT NULL,
  `o_time` varchar(50) NOT NULL,
  `o_cap` int(11) NOT NULL,
  PRIMARY KEY (`o_id`),
  KEY `c_id` (`c_id`),
  KEY `t_id` (`t_id`),
  KEY `d_term` (`d_term`),
  CONSTRAINT `c_id` FOREIGN KEY (`c_id`) REFERENCES `c` (`c_id`),
  CONSTRAINT `d_term` FOREIGN KEY (`d_term`) REFERENCES `d` (`d_term`),
  CONSTRAINT `t_id` FOREIGN KEY (`t_id`) REFERENCES `t` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='开课';

-- 正在导出表  elective_system_v1.o 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `o` DISABLE KEYS */;
INSERT INTO `o` (`o_id`, `c_id`, `t_id`, `d_term`, `o_room`, `o_time`, `o_cap`) VALUES
	(1, '3006', '2002', '2016-C', '体育馆', '3#3#4$2#8#9', 30),
	(2, '3001', '2001', '2016-C', 'AJ101', '1#3#4$3#8#9', 30),
	(3, '3003', '2003', '2016-C', '计101', '2#1#4', 60),
	(4, '3011', '2007', '2016-C', '操场', '5#3#4', 3),
	(5, '3010', '2010', '2016-C', '计202', '2#3#4$4#11#12', 30),
	(6, '3007', '2010', '2016-C', '计508', '2#10#13', 30);
/*!40000 ALTER TABLE `o` ENABLE KEYS */;


-- 导出  表 elective_system_v1.s 结构
CREATE TABLE IF NOT EXISTS `s` (
  `s_id` varchar(50) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `s_pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生';

-- 正在导出表  elective_system_v1.s 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `s` DISABLE KEYS */;
INSERT INTO `s` (`s_id`, `s_name`, `s_pwd`) VALUES
	('1001', '赵一', '1001'),
	('1002', '钱二', '1002'),
	('1003', '孙三', '1003'),
	('1004', '李四', '1004'),
	('1005', '周五', '1005'),
	('1006', '吴六', '1006'),
	('1007', '郑七', '1007'),
	('1008', '王八', '1008'),
	('1009', '冯九', '1009'),
	('1010', '陈十', '1010'),
	('1011', 'Jason Song', '1011'),
	('1012', 'Vivia Song', '1012');
/*!40000 ALTER TABLE `s` ENABLE KEYS */;


-- 导出  表 elective_system_v1.so 结构
CREATE TABLE IF NOT EXISTS `so` (
  `s_id` varchar(50) NOT NULL,
  `o_id` int(11) unsigned NOT NULL,
  `so_ps_score` double DEFAULT NULL,
  `so_ks_score` double DEFAULT NULL,
  PRIMARY KEY (`s_id`,`o_id`),
  KEY `o_id` (`o_id`),
  CONSTRAINT `o_id` FOREIGN KEY (`o_id`) REFERENCES `o` (`o_id`),
  CONSTRAINT `s_id` FOREIGN KEY (`s_id`) REFERENCES `s` (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选课';

-- 正在导出表  elective_system_v1.so 的数据：~25 rows (大约)
/*!40000 ALTER TABLE `so` DISABLE KEYS */;
INSERT INTO `so` (`s_id`, `o_id`, `so_ps_score`, `so_ks_score`) VALUES
	('1001', 1, 0, 100),
	('1001', 2, 12, 12),
	('1001', 4, NULL, NULL),
	('1001', 5, 12, 11),
	('1001', 6, 12, 12),
	('1002', 1, NULL, NULL),
	('1002', 4, NULL, NULL),
	('1002', 5, 100, NULL),
	('1002', 6, 12, 11),
	('1003', 4, NULL, NULL),
	('1003', 5, NULL, NULL),
	('1003', 6, 12, 11),
	('1004', 5, NULL, NULL),
	('1004', 6, NULL, NULL),
	('1005', 6, NULL, NULL),
	('1006', 6, NULL, NULL),
	('1007', 6, NULL, NULL),
	('1008', 6, NULL, NULL),
	('1009', 6, NULL, NULL),
	('1010', 6, NULL, NULL),
	('1011', 1, NULL, NULL),
	('1011', 2, 12, 12),
	('1011', 5, 98, 78),
	('1011', 6, NULL, NULL),
	('1012', 6, NULL, NULL);
/*!40000 ALTER TABLE `so` ENABLE KEYS */;


-- 导出  表 elective_system_v1.t 结构
CREATE TABLE IF NOT EXISTS `t` (
  `t_id` varchar(50) NOT NULL,
  `t_name` varchar(50) NOT NULL,
  `t_pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师';

-- 正在导出表  elective_system_v1.t 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `t` DISABLE KEYS */;
INSERT INTO `t` (`t_id`, `t_name`, `t_pwd`) VALUES
	('2001', '褚甲', '2001'),
	('2002', '卫乙', '2002'),
	('2003', '蒋丙', '2003'),
	('2004', '沈丁', '2004'),
	('2005', '韩戊', '2005'),
	('2006', '杨己', '2006'),
	('2007', '朱庚', '2007'),
	('2008', '秦辛', '2008'),
	('2009', '尤壬', '2009'),
	('2010', '许癸', '2010'),
	('2011', 'Bill Gates', '2011');
/*!40000 ALTER TABLE `t` ENABLE KEYS */;


-- 导出  触发器 elective_system_v1.so_before_update 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `so_before_update` BEFORE UPDATE ON `so` FOR EACH ROW BEGIN
     IF NEW.so_ps_score < 0 THEN
         SET NEW.so_ps_score = 0;
     ELSEIF NEW.so_ps_score > 100 THEN
         SET NEW.so_ps_score = 100;
     END IF;
     IF NEW.so_ks_score < 0 THEN
         SET NEW.so_ks_score = 0;
     ELSEIF NEW.so_ks_score > 100 THEN
         SET NEW.so_ks_score = 100;
     END IF;
end//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
