CREATE TABLE `User`(
                       `userId`          int(11)      not null  auto_increment COMMENT '用户表id',
                       `userName`    varchar(20)  not null                 COMMENT '用户名',
                       `password`    varchar(50)  not null                 COMMENT '用户密码',
                       PRIMARY KEY (`userId`),
                       UNIQUE KEY `user_name_unique` (`username`) USING BTREE
)ENGINE=INNODB auto_increment=21 DEFAULT CHARSET=utf8
