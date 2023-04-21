CREATE TABLE `Employee`
(
    `employeeId`   int(11)     not null auto_increment COMMENT '员工表id',
    `employeeName` varchar(20) not null COMMENT '员工姓名',
    `gender`       varchar(2)  not null COMMENT '员工性别',
    `age`       int(11)  not null COMMENT '员工年龄',
    `baseSalary`  decimal(10,2) not null COMMENT '员工基本工资',
    `locationId` int(11) not null COMMENT '地点信息id',
    PRIMARY KEY (`employeeId`)
) ENGINE = INNODB
  auto_increment = 21
  DEFAULT CHARSET = utf8
