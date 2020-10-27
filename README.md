## BBS论坛

### 使用技术

* 后端：SSM+MySQL
* 前端：HTML+CSS+JavaScript+JQuery+Bootstrap+JSP
* 管理：Maven
* 开发工具：VSCode+IDEA+Sqlyog

### 功能描述

* 公共模块
  * 访问搜索贴吧：所有进入论坛的访客都可以再网站首页访问帖子，搜索帖子

* 用户功能说明
  * 用户注册：游客要想实现发帖，评论等用户功能，需要先注册，填入用户名，用户密码，邮箱等信息完成注册，即从游客变为用户
  * 用户登录：用户需要登录后才能以该身份完成用户级别的操作
  * 用户发帖：用户填写帖子标题和内容，然后提交帖子到管理员审核
  * 用户评贴：用户可以再所有用户发布的贴子下评论
  * 用户修改密码：用户可以修改自己的密码
* 管理员功能模块
  * 管理员登录：管理员需要输入管理员用户名和密码才能进入管理员管理界面
  * 管理员发布网站通知：管理员在后台管理界面的网站通知模块中完成发布网站通知的功能，会再网站首页更新
  * 管理员管理帖子：管理员在后台管理员界面的帖子管理模块中完成帖子审核和删除帖子的工作，其中，删除帖子需要同时发送一封邮件给相应的用户告知删帖原因
  * 管理员管理用户：管理员在后台管理员界面的用户管理模块中完成用户删除的工作，同时需要发送一封邮件给相应的用户告知删号的原因
  * 管理员修改密码

### 数据库

user，post，comment，manager，notice

* **user**

\1.   id int(20)

\2.   username varcher(30)

\3.   password varcher(30)

\4.   email varcher(30)

\5.   postNum int(20)

\6.   commentNum int(20)



* **post**

\1.   id int(20)

\2.   postTitle varcher(50)

\3.   postContent varcher(200)

\4.   userId int(20)

\5.   postTime datetime

\6.   postCategory varcher(30)



* **comment**

\1.   id int(20)

\2.   commentContent varcher(100)

\3.   postId int(20)

\4.   userId int(20)

\5.   commentTime datetime



* **manager**

\1.   id int(20)

\2.   username varcher(30)

\3.   password varcher(30)

\4.   noticeNum int(20)



* **notice**

\1.   id int(20)

\2.   noticeTitle varcher(50)

\3.   noticeContent varcher(200)

\4.   managerId int(20)

\5.   noticeTime datetime