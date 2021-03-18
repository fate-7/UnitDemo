drop table if exists user;

create table user(
    id          int auto_increment                 comment '用户表id' primary key,
    username    varchar(50)                        not null comment '用户名',
    password    varchar(50)                        not null comment '用户密码'
) comment = '管理员操作原因';