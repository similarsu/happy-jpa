# 概述
```
该工程有jpa-base模块构成
```

# jpa-base
```
jpa基础相关示例代码，基于hibernate jpa provider
```

# 疑问
## 1、final
```
The class must not be declared final.
but when i declared final, success, why?
```

## 2、 a public or protected, no-argument constructor
```
The class must have a public or protected, no-argument constructor.
it see no affect for use.
```

## 3、启动验证
```
1、在persistence.xml 使用validation-mode，可不配置，使用默认值AUTO
        <!--
         * AUTO 当验证提供程序在类路径上存在时,启用验证(默认).
         * CALLBACK 如果没有可用的验证提供程序,那么启用验证并抛出一个错误.
         * NONE 禁用验证
         -->
        <validation-mode>AUTO</validation-mode>
2、同时引入validation provider
compile group: 'org.glassfish', name: 'javax.el', version: '3.0.1-b10'
```