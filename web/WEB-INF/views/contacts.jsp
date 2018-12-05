<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>邮箱系统</title>
    <meta charset='UTF-8'/>
    <meta name="viewport" content="width=device-width,
                                   initial-scale=1.0,
                                   maximum-scale=1.0,
                                   user-scalable=no" />
    <link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../resources/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../../resources/bootstrap/js/popper.js"></script>
    <script src="../../resources/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<div>
    <%--Header--%>
    <div class="row" style="height:8%;border-bottom: 2px solid">
        <div class="col-2">
            <h1>海客邮箱</h1>
        </div>

        <div class="col-8">
            <div>${user.name} &lt;${user.email_address}&gt;</div>
            <div><a href="/main">邮件首页</a> | <a href="">设置</a></div>
        </div>

        <div class="col-2" style="text-align: right">
            <a href="/logout">退出</a>
        </div>
    </div>

    <%--Main--%>
    <div class="row" style="height:80%">

        <div class="col-2" style="padding-left: 0;border-right: 1px solid">
            <div style="padding: 10% 15%">
                <div><a href="/letter/write">写信</a></div>
                <div><a href="">收信</a></div>
                <div><a href="/contact/getContacts">通讯录</a></div>
            </div>

            <hr>

            <div style="padding: 10% 15%">
                <div><a href="">收件箱</a></div>
                <div><a href="">星标邮件</a></div>
                <div><a href="">草稿箱</a></div>
                <div><a href="">垃圾箱</a></div>
            </div>

        </div>

        <div class="col-10">
            <div style="margin: 30px 20px">
                <form class="form-inline" method="post" action="/contact/searchContacts">
                    <a href="/contact/contactAdd">
                        <button type="button" class="btn btn-primary">添加</button>
                    </a>
                    <div class="form-group mx-sm-3" style="padding-left: 60px">
                        <input type="text" class="form-control" id="key" name="key" placeholder="请输入关键字">
                    </div>
                    <button type="submit" class="btn btn-primary">搜索</button>
                </form>
            </div>

            <div style="margin: 20px 20px">${note}</div>

            <div style="margin: 20px 20px">
                <c:if test="${empty contacts}">
                    暂时没有找到联系人
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

    </div>

    <%--footer--%>
    <div class="row" style="height:6%;">
        <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>

<script type="application/javascript">
    function contactDelete(email) {
        alert("email");
    }
</script>
</body>
</html>
