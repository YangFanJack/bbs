<%--
  Created by IntelliJ IDEA.
  User: 11445
  Date: 2020/10/19
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一人贴吧</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_index.css" type="text/css">

<%--    <script type="text/javascript" src="js/tools.js"></script>--%>
<%--    <script type="text/javascript" src="js/banner.js"></script>--%>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script>
        $(function (){
            //登录用户信息显示
            var currentUser = "<%=session.getAttribute("currentUser")%>";
            if (currentUser != "null") {
                $("#afterLogin").removeAttr("hidden");
                $("#beforeLogin").attr("hidden","hidden");
            }
            else{
                $("#beforeLogin").removeAttr("hidden");
                $("#afterLogin").attr("hidden","hidden");
            }
        })
    </script>
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
        <h1>
            一人一贴一故事，贴吧因你而不同！
        </h1>
        <div class="webPic">
            <img src="img/banner.png" alt="">
        </div>
        <div class="choose clearfix">
            <div class="chooseBar">
                <div>
                    <c:if test="${sessionScope.currentCategory == 1}">
                        <a class="act" href="initIndex?postCategory=1">数码科技</a>
                    </c:if>
                    <c:if test="${sessionScope.currentCategory != 1}">
                        <a href="initIndex?postCategory=1">数码科技</a>
                    </c:if>
                </div>
                <div>
                    <c:if test="${sessionScope.currentCategory == 2}">
                        <a class="act" href="initIndex?postCategory=2">历史军事</a>
                    </c:if>
                    <c:if test="${sessionScope.currentCategory != 2}">
                        <a href="initIndex?postCategory=2">历史军事</a>
                    </c:if>
                </div>
                <div>
                    <c:if test="${sessionScope.currentCategory == 3}">
                        <a class="act" href="initIndex?postCategory=3">娱乐音乐</a>
                    </c:if>
                    <c:if test="${sessionScope.currentCategory != 3}">
                        <a href="initIndex?postCategory=3">娱乐音乐</a>
                    </c:if>
                </div>
                <div>
                    <c:if test="${sessionScope.currentCategory == 4}">
                        <a class="act" href="initIndex?postCategory=4">政治经济</a>
                    </c:if>
                    <c:if test="${sessionScope.currentCategory != 4}">
                        <a href="initIndex?postCategory=4">政治经济</a>
                    </c:if>
                </div>
            </div>
        </div>
        
        <div class="search clearfix">
            <form action="initIndex?postCategory=${sessionScope.currentCategory}">
                <input id="search-input" name="searchString" type="text" placeholder="关键词" class="search_input" autocomplete="off">
                <input type="submit" id="search-button" class="search-button" value="搜索">
            </form>
        </div>
        <div class="notify">
            <h2>网站通知</h2>
            <p class="p1">Notificaton</p>
            <div class="noticeList">
                <c:forEach items="${someNotice}" var="keyword" varStatus="id">
                    <div class="item">
                        <hr>
                        <p class="p2"><a href="showNotice?fromUrl=notice">${keyword.noticeTitle}</a></p>
                        <hr>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="posts">
            <h2>浏览帖子</h2>
            <p class="p1">All Posts</p>
            <div class="postList">
                <c:forEach items="${postPageBean.list}" var="keyword" varStatus="id">
                    <div class="post">
                        <p class="postHead"><a href="detailPost?postId=${keyword.id}">${keyword.postTitle}</a></p>
                        <div class="postContent">${fn:substring(keyword.postContent, 0, 80)}. . . . . .</div>
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
                        <a href="initIndex?currentPage=${postPageBean.currentPage-1}&postCategory=${sessionScope.currentCategory}" aria-label="Previous">
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
                        <a href="initIndex?currentPage=${i}&postCategory=${sessionScope.currentCategory}">${i}</a></li>
                    </c:forEach>
                    <c:if test="${postPageBean.currentPage >=postPageBean.totalPage }">
                        <li class="disabled">
                    </c:if>
                    <c:if test="${postPageBean.currentPage <postPageBean.totalPage }">
                        <li>
                    </c:if>
                        <a href="initIndex?currentPage=${postPageBean.currentPage+1}&postCategory=${sessionScope.currentCategory}&totalPage=${postPageBean.totalPage}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="userInfo">
            <h2>我的信息</h2>
            <p class="p1">My Information</p>
            <div id="afterLogin">
                <hr>
                <p><span>欢迎你,${sessionScope.currentUser}</span></p>
                <hr>
                <p><a href="showPost1">我的主页</a></p>
                <p><a href="userExit">退出登录</a></p>
                <p><a href="user_doPost.jsp">我要发帖</a></p>
                <hr>
                <p>发帖数：<span>${userInfo.postNum}</span></p>
                <p>评论数：<span>${userInfo.commentNum}</span></p>
                <p>删帖数：<span>${userInfo.delPostNum}</span></p>
                <hr>
            </div>
            <div id="beforeLogin">
                <hr>
                <p><a href="user_login.jsp">前往登录</a></p>
                <p><a href="user_reg.jsp">前往注册</a></p>
                <hr>
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
