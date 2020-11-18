## BBS论坛

### 使用技术

* 后端：SSM+MySQL
* 前端：HTML+CSS+JavaScript+JQuery+Bootstrap+JSP
* 管理：Maven
* 开发工具：VSCode+IDEA+Sqlyog

### 功能描述

* 公共模块
  * 用户登录
  * 用户注册
  * 首页显示
  * 贴子详情
  * 关于网站
  * 网站通知
  * 发帖须知
* 用户模块
  * 查看消息
  * 修改密码
  * 评论管理
  * 贴子管理
  * 发布帖子
* 管理员模块
  * 审查贴子
  * 修改密码
  * 发布通知
  * 管理帖子
  * 管理员登录
  * 管理用户
  * 管理通知

### 数据库

user，post，comment，manager，notice，message

![](https://gitee.com/yangfanjack/blogimage/raw/master/img/20201118200610.png)


## bug解决&功能扩展
第一次大更新（11月18日）
1. √ 管理员帖子管理只能审核通过的帖子，not all
2. √ 注册添加javascript正则验证
3. √ 注册添加邮箱验证确认
4. √ 用户表增加一个删除帖子数
5. √ 审核不通过时发邮件通知原因
6. √ 贴子管理(用户) 删除功能
7. √ 增加一个用户通知信箱之类的东西（用来接收审核不通过通知，帖子被评论通知，被删贴通知）
    * 需要添加一个消息表id，msgCategory，msgContent，msgTime，userId，isRead，和用户一对多
    * 1您有新的评论
    * 2贴子审核通过
    * 3贴子审核失败
    * 4贴子删除通知
    * 5贴子删除成功
8. √ 用户管理(管理员) 显示用户的激活状态
9. √ 审查列表中的排序应该按照事件顺序从远到近
10. √ 管理员的通知管理
11. √ 只有审查合格再能算作发帖数