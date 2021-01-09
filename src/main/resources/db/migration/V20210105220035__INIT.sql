CREATE TABLE `user`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `prev_password` varchar(20) NOT NULL,
  `address` varchar(240) NOT NULL ,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `removed_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) comment = '사용자 테이블' ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `post`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `content` varchar(4000) NOT NULL,
  `price` int NOT NULL,
  `user_id` int NULL,
  `like_cnt` int NOT NULL DEFAULT '0',
  `view_cnt` int NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `removed_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) comment = '게시글 테이블' ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `image`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `imgname` varchar(20) NOT NULL,
  `imgpath` varchar(100) NOT NULL,
  `imgtype` varchar(10) NOT NULL,
  `post_id` int NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `removed_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) comment = '이미지 테이블' ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;