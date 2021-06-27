## shardingspere-alpha 5.0.0

### ext-lib

- 驱动包

### conf

- server.yaml

```yaml
authentication:
 users:
   root:
     password: root
   sharding:
     password: sharding 
     authorizedSchemas: sharding_db

props:
 max-connections-size-per-query: 1
 acceptor-size: 16  # The default value is available processors count * 2.
 executor-size: 16  # Infinite by default.
 proxy-frontend-flush-threshold: 128  # The default value is 128.
   # LOCAL: Proxy will run with LOCAL transaction.
   # XA: Proxy will run with XA transaction.
   # BASE: Proxy will run with B.A.S.E transaction.
 proxy-transaction-type: LOCAL
 proxy-opentracing-enabled: false
 proxy-hint-enabled: false
 query-with-cipher-column: true
 sql-show: true
 check-table-metadata-enabled: false
```

- config-sharding.yml

- `shardingColumn` 配置错误导致 `Cannot invoke method mod() on null object`

```yaml
schemaName: sharding_db

dataSourceCommon:
 username: g170_mall
 password: g170_mall@123
 connectionTimeoutMilliseconds: 30000
 idleTimeoutMilliseconds: 60000
 maxLifetimeMilliseconds: 1800000
 maxPoolSize: 50
 minPoolSize: 1
 maintenanceIntervalMilliseconds: 30000

dataSources:
 ds_0:
   url: jdbc:mysql://127.0.0.1:8266/g170_mall?serverTimezone=UTC&useSSL=false
#  ds_1:
#    url: jdbc:mysql://127.0.0.1:8266/g170_mall_1?serverTimezone=UTC&useSSL=false

rules:
- !SHARDING
 tables:
   t_order:
    #  actualDataNodes: ds_${0..1}.t_order_${0..1}
     actualDataNodes: ds_0.t_order_${0..15}
     tableStrategy:
       standard:
         shardingColumn: user_id
         shardingAlgorithmName: t_order_inline
     keyGenerateStrategy:
       column: order_id
       keyGeneratorName: snowflake
 bindingTables:
   - t_order #,t_order_item
 defaultDatabaseStrategy:
   standard:
     shardingColumn: user_id
     shardingAlgorithmName: database_inline
 defaultTableStrategy:
   none:
 
 shardingAlgorithms:
   database_inline:
     type: INLINE
     props:
       algorithm-expression: ds_0 #ds_${user_id % 15}
   t_order_inline:
     type: INLINE
     props:
       algorithm-expression: t_order_${user_id % 15}
 
 keyGenerators:
   snowflake:
     type: SNOWFLAKE
     props:
       worker-id: 123
```


### 启动

- `bin\start.bat 3317 conf -A`

- **-A** 预取元数据


### sql

```sql
drop table t_order;

-- 测试订单
create table t_order(
    order_id bigint not null auto_increment primary key,
    user_id int not null
);

insert into t_order(user_id) values(1),(2);
```
