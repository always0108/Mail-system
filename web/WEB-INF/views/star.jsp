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
        <span>
                        所有星标邮件 (共 ${emailItems.size()} 封)
                    </span>

        <c:if test="${not empty emailItems}">
            <!-- 功能按钮 -->
            <div style="margin-top: 20px">
                <form id="funForm" class="functionButton" action="/letter/cancelStarCheckedEmail" method="post">
                    <button type="button" class="btn btn-sm" onclick="cancelStar()">取消星标</button>
                    <input type="hidden" id="checkedList" name="checkedList">
                    <input type="hidden" id="type" name="type">
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
                        <th width="10%"></th>
                        <th width="20%">发件人</th>
                        <th width="20%">主题</th>
                        <th width="20%">时间</th>
                        <th width="10%"></th>
                    </tr>
                    <c:forEach items="${emailItems}" var="emailItem">
                        <tr>
                            <td>
                                <div class="checkbox disabled">
                                    <label><input type="checkbox" value="${emailItem.id}" name="chk_list"></label>
                                </div>
                            </td>
                            <td>
                                <a href="/letter/getEmailById?id=${emailItem.id}">
                                    <img src="../../resources/assets/email.png" style="display:inline-block;width:20%">
                                </a>
                            </td>
                            <c:choose>
                                <c:when test="${emailItem.is_read == false}">
                                    <td style="font-weight: bold">${emailItem.sender} (${emailItem.email_address})</td>
                                    <td style="font-weight: bold">${emailItem.subject}</td>
                                    <td style="font-weight: bold">${emailItem.time}</td>
                                </c:when>

                                <c:when test="${emailItem.is_read == true}">
                                    <td style="color: #4e555b">${emailItem.sender} (${emailItem.email_address})</td>
                                    <td style="color: #4e555b">${emailItem.subject}</td>
                                    <td style="color: #4e555b">${emailItem.time}</td>
                                </c:when>
                            </c:choose>

                            <c:choose>
                                <c:when test="${emailItem.star == false}">
                                    <td>
                                        <img src="../../resources/assets/unstar.png" style="display:inline-block;width:20%">
                                    </td>
                                </c:when>

                                <c:when test="${emailItem.star == true}">
                                    <td>
                                        <img src="../../resources/assets/star.png" style="display:inline-block;width:20%">
                                    </td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>

        <!-- <%--footer--%> -->
        <div class="foot">版权所有© Copyright 2006-2018 LM</div>
    </div>




</div>

<script type="text/javascript">

    var checked_list = "";

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

    function findChecked() {
        var arrChk = $("input[name='chk_list']:checked");
        if(arrChk.length > 0){
            //遍历得到每个checkbox的value值
            for (var i=0;i<arrChk.length;i++) {
                if(i == 0){
                    checked_list = String(arrChk[i].value);
                } else {
                    checked_list = checked_list + "+" + arrChk[i].value;
                }
            }
            $("#checkedList").val(checked_list);
        }
    }

    function cancelStar() {
        checked_list = "";
        findChecked();
        if(checked_list != ""){
            $("#funForm").submit();
        }
    }
</script>
</body>
</html>
