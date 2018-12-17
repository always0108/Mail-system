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
    <style>
        button {
            width: 5rem;
            margin: 0 10px 5px 0;
        }

        /* 展示邮件基本且固定信息 */
        .showInfo {
            background: #f0ecec;
            padding: 12px 10px 1px 10px;
            color: rgb(102, 101, 101);
            border-radius: 5px;
        }

        .instruct {
            color: rgb(102, 101, 101);
            font-size: 13px;
        }

        .center a{
            color:black;
        }
        .center a:hover{
            color:rgb(109, 107, 107);
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
        <div class="amail">

            <div class="showInfo">
                <h4 style="color:black;font-wight:500;">${emailItem.subject}</h4>
                <p> <span></span>
                    发件人：${emailItem.sender} ( ${emailItem.email_address} )
                    </appleid><br>
                    时间：${emailItem.time}<br>
                </p>
            </div>
            <hr>
            <!-- 邮件正文 -->
            <div style="padding:70px 70px 70px 120px;">
                <p>
                    ${emailItem.content}
                </p>
            </div>
            <hr>
            <!-- 附件部分 -->
            <div style="padding:5px 10px;background:#f0ecec">
                <c:if test="${not empty emailItem.enclosures}">
                    <h4>附件<span>( ${emailItem.enclosures.size()} 个)</span></h4>
                    <div style="background: white;padding:5px 10px;text-align: left">
                        <%--<table>--%>
                            <%--<c:forEach var="enclosure" items="${emailItem.enclosures}">--%>
                                <%--<tr>--%>
                                    <%--<td><img src="../../resources/assets/file.png" alt="文件图标" style="width:20px;height:20px"></td>--%>
                                    <%--<td>${enclosure.name}</td>--%>
                                    <%--<td><a href="/file/download?id=${enclosure.id}">下载</a></td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                        <%--</table>--%>
                        <ul>
                            <c:forEach var="enclosure" items="${emailItem.enclosures}">
                                <li style="list-style: none">
                                    <img src="../../resources/assets/file.png" alt="文件图标" style="width:20px;height:20px">
                                    <span style="display: inline-block;min-width: 150px">${enclosure.name}</span>
                                    <a href="/file/download?id=${enclosure.id}">下载</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>

        <div style="margin-top: 20px">
            <a href="/letter/reply?receiver=${emailItem.email_address}">
                <button type="button" class="col-1 btn btn-primary">回复</button>
            </a>

            <c:choose>
                <c:when test="${type == 1}">
                    <a href="/letter/inbox">
                        <button type="button" class="col-1 btn btn-primary">返回</button>
                    </a>
                </c:when>

                <c:when test="${type == 2}">
                    <a href="/letter/star">
                        <button type="button" class="col-1 btn btn-primary">返回</button>
                    </a>
                </c:when>

                <c:when test="${type == 3}">
                    <a href="/letter/garbage">
                        <button type="button" class="col-1 btn btn-primary">返回</button>
                    </a>
                </c:when>
            </c:choose>
        </div>

        <!-- <%--footer--%> -->
        <div class="foot">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>
</body>
</html>
