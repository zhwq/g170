create database `g170_mall` default character set utf8mb4 collate utf8mb4_general_ci;
grant all on g170_mall.* to g170_mall@'%' identified by 'g170_mall@123';

drop table if exists user_t;
create table user_t(
    revision int    comment '乐观锁' ,
    created_by varchar(32)    comment '创建人' ,
    created_time datetime    comment '创建时间' ,
    updated_by varchar(32)    comment '更新人' ,
    updated_time datetime    comment '更新时间' ,
    id bigint    comment '主键 11' ,
    login_name varchar(32)    comment '登录名' ,
    pass varchar(32)    comment '密码' 
) comment = '用户 ';

alter table user_t comment '用户';
drop table if exists product_t;
create table product_t(
    revision int    comment '乐观锁' ,
    created_by varchar(32)    comment '创建人' ,
    created_time datetime    comment '创建时间' ,
    updated_by varchar(32)    comment '更新人' ,
    updated_time datetime    comment '更新时间' ,
    id bigint    comment '主键' ,
    product_name varchar(32)    comment '商品名称' ,
    barcode varchar(32)    comment '商品条码' 
) comment = '商品 ';

alter table product_t comment '商品';
drop table if exists order_t;
create table order_t(
    revision int    comment '乐观锁' ,
    created_by varchar(32)    comment '创建人' ,
    created_time datetime    comment '创建时间' ,
    updated_by varchar(32)    comment '更新人' ,
    updated_time datetime    comment '更新时间' ,
    id varchar(32)    comment '主键' ,
    u_id varchar(32)    comment '用户' 
) comment = '订单 ';

alter table order_t comment '订单';
drop table if exists order_detail_t;
create table order_detail_t(
    revision int    comment '乐观锁' ,
    created_by varchar(32)    comment '创建人' ,
    created_time datetime    comment '创建时间' ,
    updated_by varchar(32)    comment '更新人' ,
    updated_time datetime    comment '更新时间' ,
    id bigint    comment '主键' ,
    o_id bigint    comment '订单id' ,
    p_id bigint    comment '商品id' ,
    num bigint    comment '数量' ,
    sale_price decimal(32,8)    comment '售价' 
) comment = '订单详情 ';

alter table order_detail_t comment '订单详情';
drop table if exists inventory_t;
create table inventory_t(
    revision int    comment '乐观锁' ,
    created_by varchar(32)    comment '创建人' ,
    created_time datetime    comment '创建时间' ,
    updated_by varchar(32)    comment '更新人' ,
    updated_time datetime    comment '更新时间' ,
    id bigint not null   comment '主键' ,
    product_id bigint    comment '商品' ,
    sku bigint    comment '库存' ,
    primary key (id)
) comment = '库存 库存';

alter table inventory_t comment '库存';
