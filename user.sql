create database regist_web;
use regist_web;
create table `user`(
    id int(11) primary key auto_increment comment '�û�id',
    username varchar(255) not null comment '�û���',
    email varchar(255) not null comment '�û�����',
    password varchar(255) not null comment '�û�����',
    state int(1) not null default 0 comment '�û�����״̬��0��ʾδ���1��ʾ����',
    code varchar(255) null comment '������'
)engine=InnoDB default charset=utf8;