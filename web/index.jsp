<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>主页</title>
    <meta charset='UTF-8'/>
    <meta name="viewport" content="width=device-width,
                                   initial-scale=1.0,
                                   maximum-scale=1.0,
                                   user-scalable=no" />
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="resources/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="resources/bootstrap/js/popper.js"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
  </head>
  <body style="background-image: url('/resources/assets/bgPicture.jpeg');background-size: 100%">
  <div class="container-fluid">

    <%--Header--%>
    <div class="row" style="height:8%;border-bottom: 1px solid">
      <h1>海客邮箱</h1>
    </div>

    <%--Main--%>
    <div class="row" style="height:80%">

      <div class="col-6">
        <div style="padding: 15% 30%">
          2500年前，人们飞鸽传书<br>
          182年前，莫尔斯发明了电报<br>
          49年前，第一封电子邮件发出<br>
          今天，海客邮箱联系你、我、他<br>
        </div>
      </div>

      <div class="col-6">
        <div class="panel panel-default" style="padding: 10% 25%">
          <div class="panel-heading" style="padding: 6% 6%;border: 1px solid;border-bottom-width: 0px">
            登录
          </div>
          <div class="panel-body" style="padding: 6% 6%;border: 1px solid">
            <form method="POST" action="/login">
              <div class="form-group">
                <div class="input-group">
                  <input type="text" class="form-control" id="emailAddress" name="emailAddress" placeholder="邮箱地址">
                </div>
              </div>

              <div class="form-group">
                <div class="input-group">
                  <input type="password" class="form-control" id="inputPassword" name="password" placeholder="密码">
                </div>
              </div>

              <div class="form-group">
                <input type="submit" class="form-control btn btn-primary" value="登 录"/>
              </div>
            </form>

            <div style="margin-top: 20%;text-align: right">
              <a href="/register">注册新邮箱</a>
              &ensp;&ensp;|&ensp;&ensp;
              <a href="/">忘了密码?</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <%--footer--%>
    <div class="row" style="height:6%;">
      <div class="col-12" style="padding-top: 4%;padding-bottom:0;text-align: center">版权所有© Copyright 2006-2018 LM</div>
    </div>

  </div>

  </body>
</html>
