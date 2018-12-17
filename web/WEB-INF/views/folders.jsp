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

        <div style="float: left">
            <div style="margin: 20px">
                <form class="form-inline" method="post" action="/folder/foldAdd">
                    <input type="text" class="col-6 form-control" style="margin-right: 20px" id="name" name="name" placeholder="输入新文件名">
                    <button type="submit" class="btn btn-primary">添加</button>
                </form>
            </div>


            <div style="margin: 20px">
                <c:if test="${not note.equals('')}">
                    ${note} <br> <br>
                </c:if>

                <c:if test="${empty folders}">
                    暂时没有文件
                </c:if>

                <c:if test="${not empty folders}">
                    <table>
                        <tr>
                            <th style="font-size: larger">文件名</th>
                            <th style="font-size: larger">操作</th>
                        </tr>
                        <c:forEach items="${folders}" var="folder">
                            <tr>
                                <td width="200px" height="50px">${folder.name}</td>

                                <td width="400px" height="50px">
                                    <a href="/folder/FolderDetail?dir_id=${folder.id}">
                                        <button type="button" class="btn btn-primary">查看</button>
                                    </a>

                                    <button type="button" class="btn btn-primary"
                                            onclick="updateID(${folder.id})"
                                            data-toggle="modal" data-target="#myModal">修改</button>

                                    <a href="/folder/delete?id=${folder.id}">
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

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">修改文件名</h4>
            </div>
            <div class="modal-body">
                <form id="modifyForm" action="/folder/modify" method="get">
                    <input name="name" type="text" placeholder="新文件名">
                    <input name="id" id="folderId" type="hidden">
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">放弃</button>
                <button type="button" class="btn btn-primary" onclick="submit()" >提交</button>
            </div>
        </div>
    </div>
</div>

<%--<!-- 模态框（Modal） -->--%>
<%--<div class="modal fade" id="noteModal" tabindex="-1" role="dialog" aria-labelledby="noteModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <%--<h4 class="modal-title" id="noteModalLabel">提示</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">${note}</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<script type="text/javascript">
    function updateID(id) {
        $("#folderId").val(id);
    }

    function submit() {
        $("#modifyForm").submit();
    }
</script>

</body>
</html>