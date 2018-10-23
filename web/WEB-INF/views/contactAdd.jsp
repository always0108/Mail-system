<%--
  Created by IntelliJ IDEA.
  User: limeng
  Date: 18-10-22
  Time: 下午10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮箱系统</title>
    <meta charset='UTF-8'/>
    <meta name="viewport" content="width=device-width,
                                   initial-scale=1.0,
                                   maximum-scale=1.0,
                                   user-scalable=no" />
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/popper.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>

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
                <div><a href="/contacts">通讯录</a></div>
            </div>

            <hr>

            <div style="padding: 10% 15%">
                <div><a href="">收件箱</a></div>
                <div><a href="">星标邮件</a></div>
                <div><a href="">草稿箱</a></div>
                <div><a href="">垃圾箱</a></div>
            </div>

        </div>

        <div class="col-2" style="margin: 30px 20px">
            <div>
                <h3 style="margin-bottom: 40px">添加联系人</h3>
                <div style="margin-bottom: 20px">${note}</div>
                <form method="post" action="/contactAdd">
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="姓名">
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="邮箱地址">
                    </div>

                    <div style="margin-top: 20px">
                        <button type="submit" class="btn btn-primary">确认</button>
                        <a href="/contacts">
                            <button type="button" class="btn btn-primary" style="margin-left: 50px">返回</button>
                        </a>
                    </div>
                </form>
            </div>

        </div>

    </div>

    <%--footer--%>
    <div class="row" style="height:6%;">
        <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>
</body>
</html>
