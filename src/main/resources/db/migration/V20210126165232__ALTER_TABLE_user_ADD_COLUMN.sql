ALTER TABLE user ADD role varchar(10) NOT NULL;

CREATE TABLE `user_roles`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL ,
  `roles` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) comment = '사용자 권한 테이블' ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;