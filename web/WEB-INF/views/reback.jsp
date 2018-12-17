<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>海客邮箱系统</title>

    <link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../resources/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../../resources/bootstrap/js/popper.js"></script>
    <script src="../../resources/bootstrap/js/bootstrap.min.js"></script>

    <link href="../../resources/hisindex.css" rel="stylesheet">
</head>

<body>
<!-- <%--Header--%> -->
<div class="row top clearfloat">
    <!-- 系统名字或Logo -->
    <div class="col-3">
        <a href="/main">
            <h4>海客邮箱系统</h4>
        </a>
    </div>
    <div class="col-8">
        <div style="color:white">${user.name} &lt;${user.email_address}&gt;
            <br>
            <a href="/main">邮件首页</a> | <a href="">设置</a>
        </div>
    </div>

    <div class="col-1" style="padding-top:19px">
        <a href="/logout">退出</a>
    </div>
</div>
<div class="none"></div>
<!-- <%--Main--%> -->
<div class="content clearfloat">
    <div class="leftMenu">
        <ul>
            <a href="/letter/write">
                <li>写信</li>
            </a>
            <a href="/folder/FolderDetail?dir_id=1">
                <li>收信</li>
            </a>
            <a href="/contact/getContacts">
                <li>通讯录</li>
            </a>
        </ul>

        <hr style="background:rgb(202, 201, 201)">

        <ul>
            <a href="/letter/sent">
                <li>已发送</li>
            </a>
            <a href="/folder/FolderDetail?dir_id=1">
                <li>收件箱</li>
            </a>
            <a href="/letter/star">
                <li>星标邮件</li>
            </a>
            <a href="/letter/draft">
                <li>草稿箱</li>
            </a>
            <a href="/letter/garbage">
                <li>垃圾箱</li>
            </a>
            <a href="/folder/getFolders">
                <li>我的文件</li>
            </a>
        </ul>
    </div>
    <div class="center">
        <c:choose>
            <c:when test="${flag == 0}">
                <div style="margin: 20px 80px">
                    <img src="../../resources/assets/error.png" style="width: 64px;height: 64px">
                </div>

                <div class="information" style="margin: 20px 80px">
                    <span style="color:green;font:15px bold;">您的邮件发送失败</span><br>
                    <p>
                        <span style="text-decoration: underline;color:black">联系人不存在</span>
                    </p>
                    <p>
                        <a href="/main"><button class="btn btn-sm btn-primary">返回首页</button></a>
                        <a href="/letter/write"><button class="btn btn-sm btn-primary">再写一封</button></a>
                    </p>
                </div>
            </c:when>

            <c:when test="${flag == 1}">
                <div style="margin: 20px 80px">
                    <img src="../../resources/assets/success.png" style="width: 64px;height: 64px">
                </div>

                <div class="information" style="margin: 20px 80px">
                    <span style="color:green;font:15px bold;">您的邮件已发送</span><br>
                    <p>
                        <a href="/main"><button class="btn btn-sm btn-primary">返回首页</button></a>
                        <a href="/letter/write"><button class="btn btn-sm btn-primary">再写一封</button></a>
                    </p>
                </div>
            </c:when>

            <c:when test="${flag == 2}">
                <div style="margin: 20px 80px">
                    <img src="../../resources/assets/success.png" style="width: 64px;height: 64px">
                </div>

                <div class="information" style="margin: 20px 80px">
                    <span style="color:green;font:15px bold;">您的草稿保存成功</span><br>
                    <p>
                        <a href="/main"><button class="btn btn-sm btn-primary">返回首页</button></a>
                        <a href="/letter/write"><button class="btn btn-sm btn-primary">再写一封</button></a>
                    </p>
                </div>
            </c:when>



        </c:choose>



            <!-- <div class="grayBlock">
                    <span class="aspan">邮件已发送给以下收件人：</span><br>
                    <span>用户名&lt;邮箱地址@qq.com&gt;&nbsp;修改联系人信息</span>
                </div> -->
            <div class="foot"> 2018 company FOLLOWYOU. All Rights Reserved</div>
        </div>
    </div>
</div>
</body>
</html>
