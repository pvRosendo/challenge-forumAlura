CREATE TABLE IF NOT EXISTS `tb_answers`(
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `author` varchar(255) DEFAULT NULL,
      `message` varchar(255) DEFAULT NULL,
      `topic_id` bigint(20) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB;