<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>主页</title>
    <meta charset='UTF-8'/>
    <meta name="viewport" content="width=device-width,
                                   initial-scale=1.0,
                                   maximum-scale=1.0,
                                   user-scalable=no" />
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/popper.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
  </head>
  <body>
  <div class="container-fluid">

    <%--Header--%>
    <div class="row">
      <h2>海客邮箱</h2>
    </div>

    <%--Main--%>
    <div class="row">
      <div class="col-8">
        此处有图
      </div>

      <div class="col-3">
        <div class="panel panel-default">
          <div class="panel-heading">
            用户登录
          </div>
          <div class="panel-body">
            <form method="POST" action="/login">
              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-user"></span>
                  </div>
                  <input type="text" class="form-control" id="stuNum" name="stuNum" placeholder="学号">
                </div>
              </div>

              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-lock"></span>
                  </div>
                  <input type="password" class="form-control" id="inputPassword" name="password" placeholder="教务系统密码">
                </div>
              </div>

              <div class="form-group">
                <input type="submit" class="form-control btn btn-primary" value="报 名"/>
              </div>
            </form>
          </div>
        </div>

      </div>

    </div>

    <%--footer--%>
    <div class="row">
      <div class="col-12 text-center">版权所有© Copyright 2006-2018 LM</div>
    </div>

  </div>

  </body>
</html>
