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

    <link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../resources/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../../resources/bootstrap/js/popper.js"></script>
    <script src="../../resources/bootstrap/js/bootstrap.min.js"></script>

    <link type="text/css" rel="stylesheet" href="../../resources/bootstrap-fileinput/css/fileinput.css" />
    <script type="text/javascript" src="../../resources/bootstrap-fileinput/js/fileinput.js"></script>
    <script type="text/javascript" src="../../resources/bootstrap-fileinput/js/locales/zh.js"></script>
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
            <form method="post" action="/letter/write" id="letterForm">
                <div style="margin: 30px 20px">
                    <div class="form-group row">
                        <label for="receiver" class="col-1 col-form-label">收件人</label>
                        <div class="col-10">
                            <input type="text" class="form-control" id="receiver" name="receiver">
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

                    <input type="hidden" id="enclosure" name="enclosure" value="">
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
                        <button type="button" class="col-2 btn btn-primary" style="margin-right: 40px" onclick="sendLetter()">发送</button>
                        <a>
                            <button type="button" class="col-2 btn btn-primary" style="margin-right: 40px">存草稿</button>
                        </a>

                        <a href="/main">
                            <button type="button" class="col-2 btn btn-primary">关闭</button>
                        </a>

                    </div>
                </div>
            </div>
        </div>

    <%--&lt;%&ndash;footer&ndash;%&gt;--%>
    <%--<div class="row" style="height:6%;float: bottom" >--%>
        <%--<div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>--%>
    <%--</div>--%>

</div>

<script type="text/javascript">
    var fileData = new Array(); //多图上传返回的图片属性接受数组

    $("#img").fileinput({
        language : 'zh',
        uploadUrl : "/upload",
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

    function sendLetter() {
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
        console.log(fileData.length);
        console.log($("#enclosure").val());
        $("#letterForm").submit();
    }
</script>
</body>
</html>