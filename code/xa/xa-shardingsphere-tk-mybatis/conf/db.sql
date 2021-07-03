create table t_order(
  order_id bigint not null,
  user_id bigint,
  order_no varchar(100),
  create_time date,
  primary key(order_id)
);
