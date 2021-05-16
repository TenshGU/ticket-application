INSERT INTO `user` VALUES
('admin','$2a$10$g5K1kF0Y7M/jfRn3d22jl.WmAAPzgwMxi3B4M4gfWgMlGQgt7tAl2','17925678901','Hill','Jacker','M',17,'123456@fox.com','http://121.5.237.69/group1/M00/00/00/eQXtRWCXOLuAXoD5AAAMz79eafk023.jpg'), #admin
('zs233','$2a$10$VAjoZ6ztVO7Bl2SjEw/SEub9aYcb6C2jr2rwgHqTrXXAzH/RdvAWG','15103265410','张','三','M',18,'zhangsan@fox.com','http://121.5.237.69/group1/M00/00/00/eQXtRWCXOLuAXoD5AAAMz79eafk023.jpg'); #123456

INSERT INTO `group` VALUES
(1,'ticketGroup','管理票务组'),
(2,'commonGroup','普通用户组');

INSERT INTO grp_user VALUES
(1,'admin'),
(2,'zs233');

INSERT INTO role VALUES
(1,'ROLE_ticketManager','负责票务的CRUD'),
(2,'ROLE_commonUser','普通用户买票');

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

INSERT INTO company VALUES
('南方航空','广东广州'),
('北方航空','北京');

INSERT INTO plane VALUES
(111,'南航K10',200,'南方航空'),
(222,'北航L20',100,'北方航空');

INSERT INTO airport VALUES
('北京首都机场','北京'),
('广州白云机场','广州');

INSERT INTO flight VALUES
(1,'航班H123','2021-5-15 10:10:10.63','2021-5-15 23:10:10.63','广州白云机场','北京首都机场',200,999.99,111);
INSERT INTO flight VALUES
(2,'航班H222','2021-5-15 10:10:10.63','2021-5-15 23:10:10.63','北京首都机场','广州白云机场',200,1999.99,111);