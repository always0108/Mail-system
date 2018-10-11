<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <meta charset='UTF-8'/>
    <meta name="viewport" content="width=device-width,
                                   initial-scale=1.0,
                                   maximum-scale=1.0,
                                   user-scalable=no" />

    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../resources/js/jquery-3.3.1.min.js"></script>
    <script src="../../resources/js/popper.js"></script>
    <script src="../../resources/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container-fluid">

    <%--Header--%>
    <div class="row" style="height:8%;border-bottom: 2px solid">
        <div class="col-2">
            <h1>海客邮箱</h1>
        </div>

        <div class="col-8">
            <div>用户名 &lt;邮箱地址&gt;</div>
            <div><a href="">邮件首页</a> | <a href="">设置</a></div>
        </div>

        <div class="col-2" style="text-align: right">
            <a href="">退出</a>
        </div>

    </div>

    <%--Main--%>
    <div class="row" style="height:80%">

        <div class="col-2" style="padding-left: 0;border-right: 1px solid">
            <div style="padding: 10% 15%">
                <div><a href="">写信</a></div>
                <div><a href="">收信</a></div>
                <div><a href="">通讯录</a></div>
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
        </div>

    </div>

    <%--footer--%>
    <div class="row" style="height:6%;">
        <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>

</div>
</body>
</html>
