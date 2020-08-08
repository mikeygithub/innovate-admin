# innovate-admin

#### 项目介绍
学校创新创业信息管理系统

#### 软件架构
软件架构说明  
技术要求：
>spring boot，spring mvc，mybatis，mybatis plus


#### 安装教程

1. idea自带数据库管理工具导入方式 创建innovate_admin数据库命令：
```sql
CREATE SCHEMA innovate_admin DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```
2. maven引入jar包
选择maven插件中的install 或 `mvn install`
3. 运行InnovateApplication

#### 使用说明

1. 构建镜像： `mvn clean package docker:build`
2. 部署应用： `docker run --name innovate-admin --privileged=true -d -p 8080:8080 -v /root/mikey/MIKEY:/home/mikey/MIKEY 90fbb84d9eb1`
3. 下载依赖： `mvn clean install`
4. 构建镜像： `docker build -t mikeyboom/innovate-admin:lastst .`
5. 推到仓库： `docker push mikeyboom/innovate-admin`
6. 拉取镜像： `docker pull mikeyboom/innovate-admin:latest`
6. 启动服务： `docker-compose -f docker-compose.yml -d`

### 修改数据库密码

修改普通用户只改一个  
`SET PASSWORD FOR 'username' = PASSWORD('xxxxxxxx');`

修改root用户改两个  
`SET PASSWORD FOR 'root' = PASSWORD('xxxxxxxxx');`  
`SET PASSWORD FOR 'root'@'localhost'=PASSWORD('xxxxxxxxx');`

#### 持续集成平台

>Travis CI

#### TODO

