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
    <script src="../../resources/anime.min.js"></script>
    <style>
        /* .table th,
        .table td {
            padding: 5px;
        }

        .functionButton button {
            background: #84a2d4;
            color: white;
        } */
        .ml3 {
            font-weight: 800;
            font-size: 3.5em;
        }
    </style>
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
        <div style="text-align:center;margin-top:100px;">
            <span class="ml3">欢迎使用海客邮箱系统！！！</span>
        </div>
        <!-- <%--footer--%> -->
        <div class="foot">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>
</body>
<script>
    // Wrap every letter in a span
    $('.ml3').each(function () {
        $(this).html($(this).text().replace(/([^\x00-\x80]|\w)/g, "<span class='letter' style='font-size:25px'>$&</span>"));
    });

    anime.timeline({
        loop: true
    })
        .add({
            targets: '.ml3 .letter',
            opacity: [0, 1],
            easing: "easeInOutQuad",
            duration: 2250,
            delay: function (el, i) {
                return 150 * (i + 1)
            }
        }).add({
        targets: '.ml3',
        opacity: 0,
        duration: 900,
        easing: "easeOutExpo",
        delay: 900
    });
</script>

</html>
