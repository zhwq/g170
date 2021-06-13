DROP TABLE user;;/*SkipError*/
CREATE TABLE user(
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    id BIGINT    COMMENT '主键 11' ,
    login_name VARCHAR(32)    COMMENT '登录名' ,
    pass VARCHAR(32)    COMMENT '密码' 
) COMMENT = '用户 ';;

ALTER TABLE user COMMENT '用户';;
DROP TABLE product;;/*SkipError*/
CREATE TABLE product(
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    id BIGINT    COMMENT '主键' ,
    product_name VARCHAR(32)    COMMENT '商品名称' ,
    barcode VARCHAR(32)    COMMENT '商品条码' 
) COMMENT = '商品 ';;

ALTER TABLE product COMMENT '商品';;
DROP TABLE order;;/*SkipError*/
CREATE TABLE order(
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    id VARCHAR(32)    COMMENT '主键' ,
    u_id VARCHAR(32)    COMMENT '用户' 
) COMMENT = '订单 ';;

ALTER TABLE order COMMENT '订单';;
DROP TABLE order_detail;;/*SkipError*/
CREATE TABLE order_detail(
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    id BIGINT    COMMENT '主键' ,
    o_id BIGINT    COMMENT '订单id' ,
    p_id BIGINT    COMMENT '商品id' ,
    num BIGINT    COMMENT '数量' ,
    sale_price DECIMAL(32,8)    COMMENT '售价' 
) COMMENT = '订单详情 ';;

ALTER TABLE order_detail COMMENT '订单详情';;
