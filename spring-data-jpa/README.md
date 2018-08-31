# SPRING DATA JPA

## 配置(build).gradle)

```
compile("org.springframework.boot:spring-boot-starter-data-jpa")
```

## 小知识

### 修改MyIsam to Innodb
```
在application.yml中加入如下配置
spring:
  jpa:
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect  #不加这句则默认为myisam引擎
```