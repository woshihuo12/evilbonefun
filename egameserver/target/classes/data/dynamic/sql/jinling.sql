DROP TABLE IF EXISTS `jingling`;

CREATE TABLE `jingling` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `charid` bigint(20) NOT NULL,
  `jinglingid` int(11) NOT NULL,
  `jinglingname` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

COMMIT;