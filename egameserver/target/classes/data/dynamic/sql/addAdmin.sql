# SQL Manager 2005 Lite for MySQL 3.6.0.3
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : reunion


DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(28) NOT NULL,
  `password` varchar(28) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

COMMIT;
