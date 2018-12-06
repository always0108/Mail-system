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

    <link href="../../resources/index.css" rel="stylesheet">
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
                <div><a href="">收件箱</a></div>
                <div><a href="">星标邮件</a></div>
                <div><a href="">草稿箱</a></div>
                <div><a href="">垃圾箱</a></div>
            </div>
        </div>

        <div class="col-10">
            <div style="margin: 20px 20px">
                <c:if test="${empty emailItems}">
                    暂时没有邮件
                </c:if>

                <c:if test="${not empty emailItems}">
                <div class="center">
                    <span>
                        收件箱 (共 311 封，其中 未读邮件 9 封)
                    </span>
                    <!-- 功能按钮 -->
                    <div class="">
                        <form class="functionButton">
                            <button type="button" class="btn btn-sm">删除</button>
                            <button type="button" class="btn btn-sm">转发</button>
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
                                    移动到...
                                    <span class="caret"></span>
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="amail">
                        <table class="table">
                            <tr>
                                <th width="5%">
                                    <div class="checkbox disabled">
                                        <label><input type="checkbox" value="" onclick="swapCheck()"></label>
                                    </div>
                                </th>
                                <th width="12%">
                                    <img src="../../resources/assets/email.png" style="display:inline-block;width:20%">
                                </th>
                                <th width="25%">发件人</th>
                                <th width="30%">主题</th>
                                <th width="28%">时间</th>
                            </tr>
                            <c:forEach items="${emailItems}" var="emailItem">
                            <tr>
                                <td>
                                    <div class="checkbox disabled">
                                        <label><input type="checkbox" value="${emailItem.id}"></label>
                                    </div>
                                </td>
                                <td>
                                    <img src="../../resources/assets/email.png" style="display:inline-block;width:20%">
                                </td>
                                <td>${emailItem.sender} (${emailItem.email_address})</td>
                                <td>${emailItem.subject}</td>
                                <td>${emailItem.time}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
    </div>

    <%--footer--%>
    <div class="row" style="height:6%;">
        <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>

<script type="text/javascript">
    //checkbox 全选/取消全选
    var isCheckAll = false;
    function swapCheck() {
        if (isCheckAll) {
            $("input[type='checkbox']").each(function() {
                this.checked = false;
            });
            isCheckAll = false;
        } else {
            $("input[type='checkbox']").each(function() {
                this.checked = true;
            });
            isCheckAll = true;
        }
    }
</script>
</body>
</html>
