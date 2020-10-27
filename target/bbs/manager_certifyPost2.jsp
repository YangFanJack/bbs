<%--
  Created by IntelliJ IDEA.
  User: 11445
  Date: 2020/10/19
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一人贴吧</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_manager_certifyPost2.css" type="text/css">
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
            <a href="showNotice">网站通知</a>
            <p>THE NOTIFICATION</p>
        </li>
        <li>
            <a href="manager_login.jsp">管理员登录</a>
            <p>ADMINISTRATOR LOGIN</p>
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
    <div class="mainBody clearfix">
        <div class="userBar clearfix">
            <div class="bar">
                <a href="certifyPost1">审核贴子</a>
            </div>
            <div class="bar">
                <a href="showAllUser">用户管理</a>
            </div>
            <div class="bar">
                <a href="showAllPost">贴子管理</a>
            </div>
            <div class="bar">
                <a href="manager_changePwd.jsp">修改密码</a>
            </div>
            <div class="bar">
                <a href="manager_doNotice.jsp">发布通知</a>
            </div>
        </div>
        <div class="postList clearfix">
            <span class="T">审核贴子</span>
            <div class="detailPost clearfix">
                <div class="postTitle">
                    ${postAndUser.postTitle}
                </div>
                <div class="cookie clearfix">
                    <div class="postMan">
                        发贴用户:<span>${postAndUser.userId.username}</span>
                    </div>
                    <div class="postCategory">
                        贴子分类:
                        <span>
                                <c:if test="${postAndUser.postCategory==1}">
                                    数码科技
                                </c:if>
                                <c:if test="${postAndUser.postCategory==2}">
                                    历史军事
                                </c:if>
                                <c:if test="${postAndUser.postCategory==3}">
                                    娱乐音乐
                                </c:if>
                                <c:if test="${postAndUser.postCategory==4}">
                                    政治经济
                                </c:if>
                            </span>
                    </div>
                    <div class="postTime">
                        发帖时间:<span>${postAndUser.postTime}</span>
                    </div>
                </div>
                <div class="postContent">
                    ${postAndUser.postContent}
                </div>
                <div class="certifyButton clearfix">
                    <input onclick="window.location='outPost?postId=${postAndUser.id}'" type="button" value="审核失败">
                    <input onclick="window.location='passPost?postId=${postAndUser.id}'" type="button" value="审核通过">
                </div>
            </div>
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
            <a href="showNotice">网站通知</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
        </p>
        <br/>
        <p>版权所有 YangfanJack1024</p>
        <br/>
        <p><a href="Mailto:yangfanjack1024@qq.com">如果您对该BBS论坛网站有任何意见，点击这里发送邮件给我们。</a></p>
    </div>
</div>
</body>
</html>
