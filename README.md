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

1. 构建镜像：`mvn clean package docker:build`
2. 部署：`docker run --name innovate-admin --privileged=true -d -p 8080:8080 -v /root/mikey/MIKEY:/home/mikey/MIKEY 90fbb84d9eb1`
3. mvn clean 在 mvn install
4. docker打包： docker build -t mikeyboom/innovate-admin:v1.3.1 .
5. docker login 登入后推到仓库： docker push mikeyboom/innovate-admin
6. 服务器拉取镜像： docker pull mikeyboom/innovate-admin:v1.3.1
#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)