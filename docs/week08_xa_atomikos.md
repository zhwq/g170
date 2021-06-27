## xa 事务

### atomikos

- db `innodb引擎支持XA事务`
```sql
create database xa_user default character set utf8mb4 collate utf8mb4_general_ci;
create table xa_user.user(id int AUTO_INCREMENT PRIMARY KEY,name varchar(50)) engine=innodb;
create database xa_account default character set utf8mb4 collate utf8mb4_general_ci;
create table xa_account.account(user_id int,money double) engine=innodb;

grant all on xa_user.* to xa@'%' identified by 'xa';
grant all on xa_account.* to xa@'%' identified by 'xa';
```