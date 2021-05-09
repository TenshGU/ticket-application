INSERT INTO `user` VALUES
('admin','$2a$10$g5K1kF0Y7M/jfRn3d22jl.WmAAPzgwMxi3B4M4gfWgMlGQgt7tAl2','17925678901','Hill','Jacker','M',17,'123456@fox.com','group1/M00/00/00/eQXtRWCXOLuAXoD5AAAMz79eafk023.jpg'), #admin
('zs233','$2a$10$VAjoZ6ztVO7Bl2SjEw/SEub9aYcb6C2jr2rwgHqTrXXAzH/RdvAWG','15103265410','张','三','M',18,'zhangsan@fox.com','group1/M00/00/00/eQXtRWCXOLuAXoD5AAAMz79eafk023.jpg'); #123456

INSERT INTO `group` VALUES
(1,'ticketGroup','管理票务组'),
(2,'commonGroup','普通用户组');

INSERT INTO grp_user VALUES
(1,'admin'),
(2,'zs233');

INSERT INTO role VALUES
(1,'ticketManager','负责票务的CRUD'),
(2,'commonUser','普通用户买票');

INSERT INTO grp_role VALUES
(1,1),
(2,2);

INSERT INTO permission VALUES
(1,'tmPermission','票务管理员许可'),
(2,'cuPermission','普通用户许可');

INSERT INTO role_per VALUES
(1,1),
(2,2);

INSERT INTO menu VALUES
(1,'首页','/index'),
(2,'个人订单','/order/'),#restful
(3,'个人中心','/myself/'),#restful
(4,'飞机票','/flightBook'),
(5,'票务管理','/ticketManager');#管理员专属

INSERT INTO per_menu VALUES
(1,1),(1,5),
(2,1),(2,2),(2,3),(2,4);