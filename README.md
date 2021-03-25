# 1、描述
> 启动类有两个
>
> `yx-community-admin`中的启动类为后台管理员模块的启动类，可以部署在后台并通过修改端口防止，使其只能在内网访问。
>
> `yx-community-web`中的启动类为前台启动类，启动这个只能访问到前台页面相关接口，不能访问后台相关接口。

# 2、配置运行
## 2.1、搭配前端运行
> 前端代码地址
> 
> [https://github.com/XieZhenyu98/yxk-community-vue](https://github.com/XieZhenyu98/yxk-community-vue)

## 2.2、配置相关地址
在yx-community-web的`application.properties`中配置相关信息
> 1.数据库配置
>> `spring.datasource.url=`配置成自己的数据库地址
>
>> `spring.datasource.username=`配置数据库用户名
>
>> `spring.datasource.password=`配置数据库密码

> 2.redis地址配置
>
>> `spring.redis.host=`配置redis的ip地址
>
>> `spring.redis.port=`配置redis的端口号
>
>> `spring.redis.password=`配置redis的密码 
>

在`yx-community-timer`中配置quartz的相关配置,配置文件名为`quartz.properties`