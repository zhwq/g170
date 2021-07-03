## demo

- https://linzhefeng23.blog.csdn.net/article/details/111990803

### 问题1 MapperScan

curl localhost:8888/xa/getOrder/616615685783007233

```log
.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseSelectProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseSelectProvider] with root cause
java.lang.NoSuchMethodException: tk.mybatis.mapper.provider.base.BaseSelectProvider.<init>()
```

需要导入**tk.mybatis.spring.annotation.MapperScan** 
// 采用通用Mapper启动器 tk.mybatis后，springboot启动类上的 MapperScan 要引入 tk.mybatis包下的
//平常默认导入 org.mybatis.spring包下

### 问题2- swagger2

http://localhost:8888/swagger-ui.html