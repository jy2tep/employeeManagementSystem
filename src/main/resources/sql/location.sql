CREATE TABLE `Location`
(
    `locationId`   int(11)     not null auto_increment COMMENT '地点信息id',
    `locationName` varchar(20) not null COMMENT '地点名称',
    `latitude`       decimal(3,2)  not null COMMENT '地点精度',
    `longitude`       decimal(3,2)  not null COMMENT '地点纬度',
    PRIMARY KEY (`locationId`)
) ENGINE = INNODB
  auto_increment = 21
  DEFAULT CHARSET = utf8
