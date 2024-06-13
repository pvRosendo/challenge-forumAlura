CREATE TABLE IF NOT EXISTS `tb_user_permission` (
    `id_user` bigint(20) NOT NULL,
    `id_permission` bigint(20) NOT NULL,
    PRIMARY KEY (`id_user`,`id_permission`),
    KEY `fk_user_permission_permission` (`id_permission`),
    CONSTRAINT `fk_user_permission` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`),
    CONSTRAINT `fk_user_permission_permission` FOREIGN KEY (`id_permission`) REFERENCES `tb_permission` (`id`)
) ENGINE=InnoDB;