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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_user_reg.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script>
        function checkUsername() {
            var username = $("#username").val();
            var reg_username = /^\w{8,20}$/;
            var flag = reg_username.test(username);
            if(flag){
                $("#username").css("border","");
            }
            else{
                $("#username").css("border","2px solid red");
            }
            return flag;
        }
        function checkPassword() {
            var password = $("#password").val();
            var reg_password = /^\w{8,20}$/;
            var flag = reg_password.test(password);
            if(flag){
                $("#password").css("border","");
            }
            else{
                $("#password").css("border","2px solid red");
            }
            return flag;
        }
        function checkNewPassword() {
            var password = $("#newPassword").val();
            var reg_password = /^\w{8,20}$/;
            var flag = reg_password.test(password);
            if(flag){
                $("#newPassword").css("border","");
            }
            else{
                $("#newPassword").css("border","2px solid red");
            }
            return flag;
        }
        function checkEmail() {
            var email = $("#email").val();
            var reg_email =  /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;
            var flag = reg_email.test(email);
            if(flag){
                $("#email").css("border","");
            }
            else{
                $("#email").css("border","2px solid red");
            }
            return flag;
        }

        function isSubmit(){
            if(checkUsername() && checkPassword() && checkNewPassword() && checkEmail()){
                return true;
            }else{
                return false;
            }
        }

        $(function (){
            //验证码
            $("#checkCode").click(function (){
                var date = new Date().getTime();
                this.src = "checkCode?signal="+date;
            })

            //正则检查字符串合法性
            $("#username").blur(checkUsername);
            $("#password").blur(checkPassword);
            $("#newPassword").blur(checkNewPassword);
            $("#email").blur(checkEmail);
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
        <div class="login clearfix">
            <span>用户注册</span>
            <form class=" clearfix" action="userRegister" onsubmit="return isSubmit();">
                <input id="username" class="kuang" type="text" placeholder="填写用户名" name="username">
                <input id="password" class="kuang" type="password" placeholder="填写密码" name="password">
                <input id="newPassword" class="kuang" type="password" placeholder="确认密码" name="newPassword">
                <input id="email" class="kuang" type="text" placeholder="填写邮箱" name="email">
                <input class="cerCode1" type="text" placeholder="填写验证码" name="checkCode">
                <img class="cerCode2" id="checkCode" src="checkCode">
                <input class="kuang wide" type="submit" value="注册">
            </form>
            <span>${msg}</span>
            <p><a href="user_login.jsp">前往登录</a></p>
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