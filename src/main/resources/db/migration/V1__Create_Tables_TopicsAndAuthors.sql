CREATE TABLE IF NOT EXISTS `tb_authors` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tb_topics` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(100) NOT NULL,
    `message` TEXT NOT NULL,
    `dateCreation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `topicState` smallint NOT NULL,
    `course` smallint NOT NULL,
    `author` bigint(20),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author`) REFERENCES `tb_authors`(`id`)
);