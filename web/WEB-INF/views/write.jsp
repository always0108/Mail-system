<%--
  Created by IntelliJ IDEA.
  User: limeng
  Date: 18-10-23
  Time: 下午7:39
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

    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../resources/js/jquery-3.3.1.min.js"></script>
    <script src="../../resources/js/popper.js"></script>
    <script src="../../resources/js/bootstrap.min.js"></script>
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


        <div class="col-10">
            <form action="" id="letterForm">
                <div style="margin: 30px 20px">
                    <div class="form-group row">
                        <label for="receiver" class="col-1 col-form-label">收件人</label>
                        <div class="col-6">
                            <input type="text" class="form-control" id="receiver">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="subject" class="col-1 col-form-label">主题</label>
                        <div class="col-6">
                            <input type="text" class="form-control" id="subject">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="body" class="col-1 col-form-label">正文</label>
                        <div class="col-6">
                            <textarea name="comment" class="form-control" rows="10" form="letterForm" id="body">请在此处输入文本...</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="enclosure" class="col-1 col-form-label">附件</label>
                        <div class="col-6">
                            <input type="file" class="form-control-file" id="enclosure">
                        </div>
                    </div>
                </div>

                <div style="margin: 0px 95px">
                    <a href="">
                        <button type="submit" class="btn btn-primary" style="margin: 30px">发送</button>
                    </a>
                    <a>
                        <button type="button" class="btn btn-primary" style="margin: 30px">存草稿</button>
                    </a>
                    <a href="/main">
                        <button type="button" class="btn btn-primary" style="margin: 30px">关闭</button>

                    </a>
                </div>
            </form>


        </div>

    </div>

    <%--footer--%>
    <div class="row" style="height:6%;">
        <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>

</div>
</body>
</html>
