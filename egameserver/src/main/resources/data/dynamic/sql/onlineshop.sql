# SQL Manager 2005 Lite for MySQL 3.6.0.3
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : reunion


DROP TABLE IF EXISTS `onlineshop`;

CREATE TABLE `onlineshop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopitemid` int(11) DEFAULT NULL,
  `itemid` int(11) DEFAULT NULL,
  `lime` bigint(20) DEFAULT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

COMMIT;
