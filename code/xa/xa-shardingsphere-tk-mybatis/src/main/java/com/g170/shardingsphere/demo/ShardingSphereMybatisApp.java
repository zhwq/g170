package com.g170.shardingsphere.demo;

// import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// import org.mybatis.spring.annotation.MapperScan; ==>
// java.lang.NoSuchMethodException:tk.mybatis.mapper.provider.base.BaseSelectProvider.<init>()
// 采用通用Mapper启动器 tk.mybatis后，springboot启动类上的 MapperScan 要引入 tk.mybatis包下的
//平常默认导入 org.mybatis.spring包下
@MapperScan("com.g170.shardingsphere.demo.dao")
public class ShardingSphereMybatisApp {
  public static void main(String[] args) {
    SpringApplication.run(ShardingSphereMybatisApp.class, args);
  }
}
