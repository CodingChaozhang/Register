create database regist_web;
use regist_web;
create table `user`(
    id int(11) primary key auto_increment comment '用户id',
    username varchar(255) not null comment '用户名',
    email varchar(255) not null comment '用户邮箱',
    password varchar(255) not null comment '用户密码',
    state int(1) not null default 0 comment '用户激活状态：0表示未激活，1表示激活',
    code varchar(255) null comment '激活码'
)engine=InnoDB default charset=utf8;