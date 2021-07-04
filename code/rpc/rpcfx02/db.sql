-- tcc hmily
create database `g170_hmily_0` default character set utf8mb4 collate utf8mb4_general_ci;
create database `g170_hmily_1` default character set utf8mb4 collate utf8mb4_general_ci;
grant all on g170_hmily_0.* to g170@'%' identified by 'g170@123';
grant all on g170_hmily_1.* to g170@'%' identified by 'g170@123';
-- data
create table g170_hmily_0.t_account(
id bigint auto_increment primary key,
account_no bigint,
account_name varchar(32),
account_type varchar(32) comment '账户类型 CNY 人名币 USD 美元',
amount double comment '余额'
);
create table g170_hmily_1.t_account(
id bigint auto_increment primary key,
account_no bigint,
account_name varchar(32),
account_type varchar(32) comment '账户类型 CNY 人名币 USD 美元',
amount double comment '余额'
);
insert into t_account(account_no, account_name, account_type, amount)
values (2, 'A', 'CNY', 1000),(2, 'A', 'USD', 300),(1, 'B', 'CNY', 1000),(1, 'B', 'USD', 300);
