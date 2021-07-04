
## 

- 依赖问题
```log
[ERROR] [ERROR] Some problems were encountered while processing the POMs:
[FATAL] Non-resolvable parent POM for com.g170:xa-atomikos-starter:0.0.1-SNAPSHOT: Could not transfer artifact org.springframework.boot:spring-boot-starter-parent:pom:2.4.8 from/to central (https://repo.maven.apache.org/maven2): Connect to repo.maven.apache.org:443 [repo.maven.apache.org/127.0.0.1] failed: Connection refused: connect and 'parent.relativePath' points at no local POM @ line 5, column 10

[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:2.4.8:repackage (repackage) on project xa-atomikos-starter: Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:2.4.8:repackage failed: Plugin org.springframework.boot:spring-boot-maven-plugin:2.4.8 or one of its dependencies could not be resolved: Failed to collect dependencies at org.springframework.boot:spring-boot-maven-plugin:jar:2.4.8 -> org.apache.maven.shared:maven-common-artifact-filters:jar:3.1.1: Failed to read artifact descriptor for org.apache.maven.shared:maven-common-artifact-filters:jar:3.1.1: Could not transfer artifact org.apache.maven.shared:maven-common-artifact-filters:pom:3.1.1 from/to spring-releases (https://repo.spring.io/libs-release): Not authorized -> [Help 1]
```

使用maven的镜像代理处理

- springframework `_` vs `-`

```xml
<mirror>
  <id>alimaven</id>
  <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  <mirrorOf>*,!com.e-iceblue</mirrorOf>
</mirror>
```

- `- $`

```java
Description:

Configuration property name 'spring.shardingsphere.rules.sharding.sharding-algorithms.database_inline.props' is not valid:

    Invalid characters: '_'
    Reason: Canonical names should be kebab-case ('-' separated), lowercase alpha-numeric characters and must start with a letter

Action:
Modify 'spring.shardingsphere.rules.sharding.sharding-algorithms.database_inline.props' so that it conforms to the canonical names requirements.
```

```log
org.springframework.beans.factory.BeanDefinitionStoreException: Invalid bean definition with name 'database-inline' defined in null: Could not resolve placeholder 'user_id % 2' in value "ds_${user_id % 2}"; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'user_id % 2' in value "ds_${user_id % 2}"
```

`${xx}` --> `$->{xx}`


