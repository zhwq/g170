```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.4.8</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
```

## 此参数必须配置

spring.shardingsphere.rules.sharding.key-generators.snowflake.type=SNOWFLAKE
spring.shardingsphere.rules.sharding.key-generators.snowflake.props.worker-id=123

 ```log
Caused by: java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.apache.shardingsphere.spring.boot.util.PropertyUtil.v2(PropertyUtil.java:111)
        at org.apache.shardingsphere.spring.boot.util.PropertyUtil.handle(PropertyUtil.java:75)
        at org.apache.shardingsphere.spring.boot.registry.AbstractAlgorithmProvidedBeanRegistry.registerBean(AbstractAlgorithmProvidedBeanRegistry.java:50)
        at org.apache.shardingsphere.sharding.spring.boot.algorithm.KeyGenerateAlgorithmProvidedBeanRegistry.postProcessBeanDefinitionRegistry(KeyGenerateAlgorithmProvidedBeanRegistry.java:38)
        at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:311)
        at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:142)
        at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:746)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:564)
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:144)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:782)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:774)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:339)
        ... 3 more
```

- 注意 `actual-data-nodes` 与 `algorithm-expression` 的格式

```java`
java.lang.IllegalStateException: no database route info
``