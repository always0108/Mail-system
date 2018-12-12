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
            <a href="/letter/inbox">
                <li>收信</li>
            </a>
            <a href="/contact/getContacts">
                <li>通讯录</li>
            </a>
        </ul>

        <hr style="background:rgb(202, 201, 201)">

        <ul>
            <a href="/letter/inbox">
                <li>收件箱</li>
            </a>
            <a href="/letter/star">
                <li>星标邮件</li>
            </a>
            <a href="">
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
        <div style="float: left">
            <div style="margin: 20px">
                <form class="form-inline" method="post" action="/contact/searchContacts">
                    <a href="/contact/contactAdd">
                        <button type="button" class="btn btn-primary" style="margin-right: 50px">添加</button>
                    </a>

                    <input type="text" class="col-6 form-control" style="margin-right: 10px" id="key" name="key" placeholder="请输入关键字">
                    <button type="submit" class="btn btn-primary">搜索</button>
                </form>
            </div>

            <div style="margin: 20px">${note}</div>

            <div style="margin: 20px">
                <c:if test="${empty contacts}">
                    暂时没有相应联系人
                </c:if>

                <c:if test="${not empty contacts}">
                    <table>
                        <tr>
                            <th style="font-size: larger">姓名</th>
                            <th style="font-size: larger">邮箱地址</th>
                            <th style="font-size: larger">操作</th>
                        </tr>
                        <c:forEach items="${contacts}" var="contact">
                            <tr>
                                <td width="200px" height="50px">${contact.name}</td>
                                <td width="200px" height="50px">${contact.email_address}</td>
                                <td width="200px" height="50px">
                                    <a href="/contact/contactDelete?email=${contact.email_address}">
                                        <button type="button" class="btn btn-danger">删除</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
        <!-- <%--footer--%> -->
        <div class="foot">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>

<script type="application/javascript">
    function contactDelete(email) {
        alert("email");
    }
</script>
</body>
</html>