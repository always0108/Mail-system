<%--
  Created by IntelliJ IDEA.
  User: limeng
  Date: 18-11-8
  Time: 上午11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <script src="../../resources/js/jquery-3.3.1.min.js"></script>
</head>
<body>

    <br />
    <br />
    <form id= "uploadForm">
        <input type="file" id="file" name="file"/>
        <input type="button" onclick="UploadFile()" value="上传"/>
    </form>

    <script type="text/javascript">
        function UploadFile() {
            var ajaxUrl = "http://localhost:8080/upload";
            var form = new FormData($("#uploadForm")[0]);
            $.ajax({
                type: "POST",
                //dataType: "text",
                url: ajaxUrl,
                data: form,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    alert("上传成功");
                },
                error: function(data) {
                    alert("上传失败");
                }
            });
        }
    </script>
</body>
</html>
