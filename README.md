# Plum


## 介绍

.  
├── LICENSE    

├── README.md  

├── database.sql     # 数据库结构  

├── nginx.conf       # nginx 配置文件

├── plum             # 对外展示部分  

├── plum-admin       # 后台管理  

└── plum-api         # API  

## 部署

plum-api：

1.修改`application.yml`

2.通过`mvn package`命令得到`plum-api.jar`

3.将文件上传到服务器，输入`java -jar plum-api.jar`即可启动api服务


plum-admin:

1.修改`/Plum/plum-admin/src/config/app.js`

2.输入`npm i`安装依赖，输入`npm run build`打包

3.将 dist 目录下的文件放到 nginx 所指定的项目目录


plum

1.修改`/Plum/plum/src/api/index.js`

2.输入`npm i`安装依赖，输入`npm run build`打包

3.将 dist 目录下的文件放到 nginx 所指定的项目目录



截图：

![3.png](https://i.loli.net/2017/07/26/59783851ce005.png)

![1.png](https://ooo.0o0.ooo/2017/07/26/597837f62ef35.png)

![2.png](https://i.loli.net/2017/07/26/597837f62efe9.png)
