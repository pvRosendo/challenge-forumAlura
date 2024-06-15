CREATE TABLE IF NOT EXISTS `tb_topics`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `author` varchar(255) DEFAULT NULL,
    `course` varchar(255) DEFAULT NULL,
    `date_creation` datetime(6) DEFAULT NULL,
    `message` varchar(255) DEFAULT NULL,
    `title` varchar(255) DEFAULT NULL,
    `topic_state` enum('inProgress', 'resolved'),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;