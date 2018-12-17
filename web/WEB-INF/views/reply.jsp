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
    <link type="text/css" rel="stylesheet" href="../../resources/bootstrap-fileinput/css/fileinput.css" />
    <script type="text/javascript" src="../../resources/bootstrap-fileinput/js/fileinput.js"></script>
    <script type="text/javascript" src="../../resources/bootstrap-fileinput/js/locales/zh.js"></script>
    <style>
        .file-drop-zone{
            height: 300px;
        }

        .file-drop-zone-title{
            color: #6c757d;
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
        <div class="col-10">
            <form method="post" action="/letter/write" id="letterForm">
                <div style="margin: 30px 20px">
                    <div class="form-group row">
                        <label for="receiver" class="col-1 col-form-label">收件人</label>
                        <div class="col-10">
                            <input type="text" class="form-control" id="receiver" name="receiver" readonly value="${receiver}">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="subject" class="col-1 col-form-label">主题</label>
                        <div class="col-10">
                            <input type="text" class="form-control" id="subject" name="subject">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="body" class="col-1 col-form-label">正文</label>
                        <div class="col-10">
                            <textarea name="content" class="form-control" rows="10" form="letterForm" id="body" placeholder="在此输入文本"></textarea>
                        </div>
                    </div>

                    <input type="hidden" id="enclosure" name="enclosure">
                    <input type="hidden" id="type" name="type">
                </div>
            </form>

            <form class="form" action="#" method="post" enctype="multipart/form-data"  id="pollutionForm">
                <div style="margin: 30px 20px">
                    <div class="form-group row">
                        <label class="col-1 col-form-label">附件</label>
                        <!-- 注意事项：Input type类型为file class为样式 id随意 name随意
                         multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
                        <div class="col-10">
                            <input type="file" class="col-10 file" id="img" multiple name="images"> <br>
                        </div>
                    </div>
                </div>
            </form>

            <div style="margin: 30px 20px">
                <div class="form-group row">
                    <label class="col-1 col-form-label"></label>

                    <div class="col-8">
                        <button type="button" class="col-2 btn btn-primary" style="margin-right: 40px" onclick="send(0)">发送</button>

                        <button type="button" class="col-2 btn btn-primary" style="margin-right: 40px" onclick="send(1)">存草稿</button>

                        <a href="/main">
                            <button type="button" class="col-2 btn btn-primary">关闭</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- <%--footer--%> -->
        <div class="foot">版权所有© Copyright 2006-2018 LM</div>
    </div>
</div>

<script type="text/javascript">
    var fileData = new Array(); //多图上传返回的图片属性接受数组

    $("#img").fileinput({
        language : 'zh',
        uploadUrl : "/file/upload",
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        autoReplace : true,
        minFileCount: 0,
        uploadAsync: true,
        maxFileCount: 8,//最大上传数量
        browseOnZoneClick: true,
        msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
        enctype: 'multipart/form-data',
        // overwriteInitial: false,//不覆盖已上传的图片
        allowedFileExtensions : [ "rar", "zip", "pdf", "rar", "zip", "rar", "jpg", "jpeg", "png", "gif" ],
        browseClass : "btn btn-primary", //按钮样式
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
    }).on("fileuploaded", function(e, data) {//文件上传成功的回调函数，还有其他的一些回调函数，比如上传之前...
        var res = data.response;
        fileData.push(res.id);
    });

    function send(type) {
        $("#type").val(type);
        if(fileData.length > 0){
            var result = "";
            result = result + fileData[0];
            for(var i = 1; i < fileData.length;i++)
                result = result + "+" + fileData[i];
            $("#enclosure").val(result);
        }else{
            $("#enclosure").val("null");
        }
        for(var i = 0; i < fileData.length;i++) console.log(fileData[i]);
        $("#letterForm").submit();
    }
</script>
</body>
</html>
