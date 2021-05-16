SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS plane;
DROP TABLE IF EXISTS airport;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS grp_user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS grp_role;
DROP TABLE IF EXISTS permission;
DROP TABLE IF EXISTS role_per;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS per_menu;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE company (
    `name` VARCHAR(64) PRIMARY KEY COMMENT '公司名称',
    location VARCHAR(64) NOT NULL COMMENT '公司地址'
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE plane (
    id INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '飞机id',
    `name` VARCHAR(64) NOT NULL COMMENT '飞机名称',
    capacity INT(5) NOT NULL COMMENT '飞机容量',
    company_name VARCHAR(64) COMMENT '飞机所属公司',
    CONSTRAINT fk_plane_company FOREIGN KEY(company_name) REFERENCES company(NAME) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE airport (
    `name` VARCHAR(64) PRIMARY KEY COMMENT '机场名称',
    location VARCHAR(64) NOT NULL COMMENT '机场所在地'
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE flight (
    id VARCHAR(256) PRIMARY KEY COMMENT '航班id',
    `name` VARCHAR(64) NOT NULL COMMENT '航班名称',
    leave_time DATETIME NOT NULL COMMENT '起飞时间',
    arrive_time DATETIME NOT NULL COMMENT '到达时间',
    leave_airport_name VARCHAR(64) NOT NULL COMMENT '起飞机场',
    arrive_airport_name VARCHAR(64) NOT NULL COMMENT '降落机场',
    stock INT(5) NOT NULL COMMENT '余量',
    price DECIMAL(7,2) NOT NULL,
    plane_id INT(11) COMMENT '飞机id',
    CONSTRAINT fk_leave_airport FOREIGN KEY(leave_airport_name) REFERENCES airport(NAME) ON DELETE CASCADE,
    CONSTRAINT fk_arrive_airport FOREIGN KEY(arrive_airport_name) REFERENCES airport(NAME) ON DELETE CASCADE,
    CONSTRAINT fk_flight_plane FOREIGN KEY(plane_id) REFERENCES plane(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `user` (
    username VARCHAR(64) PRIMARY KEY COMMENT '用户名',
    `password` VARCHAR(300) NOT NULL COMMENT '密码',
    phone VARCHAR(12) NOT NULL COMMENT '手机号码',
    first_name VARCHAR(64) NOT NULL COMMENT 'first_name',
    last_name VARCHAR(64) NOT NULL COMMENT 'last_name',
    gender CHAR(1) DEFAULT 'M' COMMENT '性别',
    age INT(3) NOT NULL COMMENT '年龄',
    email VARCHAR(64) COMMENT '邮箱',
    image VARCHAR(600) COMMENT '头像'
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `order` (
    id VARCHAR(256) PRIMARY KEY,
    order_time DATETIME NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    username VARCHAR(64) NOT NULL,
    flight_id VARCHAR(256) NOT NULL,
    CONSTRAINT fk_oun FOREIGN KEY(username) REFERENCES `user`(username) ON DELETE CASCADE,
    CONSTRAINT fk_ofi FOREIGN KEY(flight_id) REFERENCES `flight`(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `group` (
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    description VARCHAR(64)
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE grp_user (
    group_id INT(11),
    username VARCHAR(64),
    PRIMARY KEY(group_id,username),
    CONSTRAINT fk_grp_user_un FOREIGN KEY(username) REFERENCES `user`(username) ON DELETE CASCADE,
    CONSTRAINT fk_grp_user_gid FOREIGN KEY(group_id) REFERENCES `group`(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE role (
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(64)   
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE grp_role (
    group_id INT(11),
    role_id INT(11),
    PRIMARY KEY(group_id,role_id),
    CONSTRAINT fk_grp_role_gid FOREIGN KEY(group_id) REFERENCES `group`(id) ON DELETE CASCADE,
    CONSTRAINT fk_grp_role_rid FOREIGN KEY(role_id) REFERENCES role(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE permission(
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    `type` VARCHAR(20),
    description VARCHAR(64)
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE role_per (
    role_id INT(11),
    permission_id INT(11),
    PRIMARY KEY(role_id,permission_id),
    CONSTRAINT fk_role_per_rid FOREIGN KEY(role_id) REFERENCES role(id) ON DELETE CASCADE,
    CONSTRAINT fk_role_per_pid FOREIGN KEY(permission_id) REFERENCES permission(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE menu(
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20),
    url VARCHAR(64)
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE per_menu (
    permission_id INT(11),
    menu_id INT(11),
    PRIMARY KEY(permission_id,menu_id),
    CONSTRAINT fk_per_menu_pid FOREIGN KEY(permission_id) REFERENCES permission(id) ON DELETE CASCADE,
    CONSTRAINT fk_per_menu_mid FOREIGN KEY(menu_id) REFERENCES menu(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE INDEX roleName_unique ON role(`name`);
CREATE INDEX order_username ON `order`('username');
#EXPLAIN SELECT group_id FROM grp_role
 #                                LEFT JOIN role r
  #                                         ON role_id = r.id
   #     WHERE r.name IN('ticketManager','commonUser');