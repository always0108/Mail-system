<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div><a href="/letter/inbox">收信</a></div>
                <div><a href="/contact/getContacts">通讯录</a></div>
            </div>

            <hr>

            <div style="padding: 10% 15%">
                <div><a href="/letter/inbox">收件箱</a></div>
                <div><a href="/letter/star">星标邮件</a></div>
                <div><a href="">草稿箱</a></div>
                <div><a href="/letter/garbage">垃圾箱</a></div>
            </div>
        </div>

        <div class="col-10">
            <div style="margin: 30px 20px">
                    <div class="form-group row">
                        <label class="col-1 col-form-label">发件人</label>
                        <div class="col-10">
                            <input type="text" class="form-control" readonly value="${emailItem.sender}(${emailItem.email_address})">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-1 col-form-label">主题</label>
                        <div class="col-10">
                            <input type="text" class="form-control" readonly value="${emailItem.subject}">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-1 col-form-label">正文</label>
                        <div class="col-10">
                            <textarea class="form-control" readonly>${emailItem.content}</textarea>
                        </div>
                    </div>

                    <c:if test="${not empty emailItem.enclosures}">
                    <div class="form-group row">
                        <label class="col-1 col-form-label">附件</label>
                        <div class="col-10">
                            <ul>

                                <c:forEach var="enclosure" items="${emailItem.enclosures}">
                                    <li><a href="/file/download?id=${enclosure.id}">${enclosure.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    </c:if>
            </div>

            <div style="margin: 30px 20px">
                <div class="form-group row">
                    <label class="col-1 col-form-label"></label>

                    <div class="col-8">
                        <a href="/letter/reply?receiver=${emailItem.email_address}">
                            <button type="button" class="col-2 btn btn-primary">回复</button>
                        </a>

                        <%--<a href="/letter/inbox">--%>
                            <%--<button type="button" class="col-2 btn btn-primary">返回</button>--%>
                        <%--</a>--%>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <%--footer--%>
    <div class="row" style="height:6%">
        <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>
</body>
</html>
