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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_manager_managePost.css"text/css">
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
                <div class="bar">
                    <a href="showNotice?fromUrl=manager_manageNotice1">通知管理</a>
                </div>
            </div>
            <div class="postList clearfix">
                <span class="T">贴子管理</span>
                <div class="posts clearfix">
                    <c:forEach items="${postPageBean.list}" var="keyword" varStatus="id">
                        <div class="post clearfix">
                            <div class="postName">
                                <a href="detailPost?postId=${keyword.id}">${keyword.postTitle}</a>
                            </div>
                            <div class="manButton">
                                <input onclick="window.location='deletePost?postId=${keyword.id}&fromUrl=manager'" type="button" class="deleteButton" value="删除">
                                <input onclick="window.location='detailPost?postId=${keyword.id}'" type="button" class="deleteButton" value="查看">
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${postPageBean.currentPage <=1 }">
                        <li class="disabled">
                            </c:if>
                            <c:if test="${postPageBean.currentPage >1 }">
                        <li>
                            </c:if>
                            <a href="showAllPost?currentPage=${postPageBean.currentPage-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach  var="i" begin="1" end="${postPageBean.totalPage}">
                            <c:if test="${postPageBean.currentPage == i }">
                                <li class="active">
                            </c:if>
                            <c:if test="${postPageBean.currentPage != i }">
                                <li>
                            </c:if>
                            <a href="showAllPost?currentPage=${i}">${i}</a></li>
                        </c:forEach>
                        <c:if test="${postPageBean.currentPage >=postPageBean.totalPage }">
                        <li class="disabled">
                            </c:if>
                            <c:if test="${postPageBean.currentPage <postPageBean.totalPage }">
                        <li>
                            </c:if>
                            <a href="showAllPost?currentPage=${postPageBean.currentPage+1}&totalPage=${postPageBean.totalPage}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
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