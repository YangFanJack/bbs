<%--
  Created by IntelliJ IDEA.
  User: 11445
  Date: 2020/10/19
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一人贴吧</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_understand.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <!--头部-->
    <div class="header w">
        <!--创建导航条-->
        <ul class="nav">
            <li>
                <a href="understand.jsp">发帖须知</a>
                <p>ABOUT POST</p>
            </li>
            <li>
                <a href="showNotice?fromUrl=notice">网站通知</a>
                <p>THE NOTIFICATION</p>
            </li>
            <li>
                <a href="user_login.jsp">用户登录</a>
                <p>USER LOGIN</p>
            </li>
            <li>
                <a href="about.jsp">关于我们</a>
                <p>ABOUT US</p>
            </li>
        </ul>
        
        <div class="logo">
            <a href="initIndex" title="一个牛逼的网站">
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="网站的logo">
            </a>
        </div>  
    </div>

    <!--网站主内容-->
    <div class="content w clearfix">
        <div class="understandList clearfix">
            <span>发贴须知</span>
            <p>为维护贴吧秩序，确保本贴吧健康、和谐、稳定发展，特制定本吧管理制度：</p>
            <div class="understand">
                1、违反国家相关法律及社区规则的帖子（包括破坏社会公共安全的帖子及社区认定部分涉及敏感内容的帖子）。
            </div>
            <div class="understand">
                2、与贴吧主题无关，1楼和2楼加起来字数少于20字的水帖子。
            </div>
            <div class="understand">
                3、无实质性内容的帖子（包括灌水和与威信帖吧的广告性质的帖子）。
            </div>
            <div class="understand">
                5、使用煽动性语言故意夸大事实、误导网友、挑起争端的帖子。
            </div>
            <div class="understand">
                6、有关政治、和国家领导人有关、严重偏激的帖子、还有影响论坛稳定以及引起社会恐慌等类型的帖子；
            </div>
            <div class="understand">
                7、未经过本人同意发表其照片、隐私等个人信息的。
            </div>
            <div class="understand">
                8、帐号：注册帐号使用违法、不雅、不当的帐号。
            </div>
            <div class="understand">
                9、利用帖子恶意中伤、辱骂、恐吓、伤害以及进行污辱、漫骂及人身攻击的 。
            </div>
            <div class="understand">
                10、带有种族、性别歧视的贴子。
            </div>
            <div class="understand">
                11、任何带有虚假信息的商业性广告，以及在签名或者帖子中发布恶意弹出链接广告，发布赚钱、电影下线帖子、传销类广告帖子。
            </div>
            <div class="understand">
                12、对于违反以上规则的帖子及回复，吧主有权做修改及删除处理，原则上无需另作说明，吧主有权在不通知发帖者本人的情况下，将其帖删除及封本吧ID发言权。
            </div>
            <div class="understand">
                13、网友有任何问题可以发消息给贴吧管理员咨询。请各位吧友自行约束好自己的言行举止，做一个合格的，优秀的吧友，分享健康，新颖但不违法吧规的内容！谢谢支持！
            </div>
        </div>
    </div>

    <!--网站底部-->
    <div class="footer">
        <div class="w">
            <p>
                <a href="initIndex">网站主页</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="about.jsp">关于我们</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="understand.jsp">发帖须知</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="showNotice?fromUrl=notice">网站通知</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
            <br/>
            <p>版权所有 YangfanJack1024</p>
            <br/>
            <p><a href="Mailto:yangfanjack1024@qq.com">如果您对该BBS论坛网站有任何意见，点击这里发送邮件给我们。</a></p> 
        </div>
    </div>
</body>
</html>