<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
</head>

<body class="container">

<div id="login" class="span4 offset3">

<form class="form-horizontal" id="login" action="login"  method="post">
  <div class="control-group">
    <label class="control-label" for="inputUser">用户名</label>
    <div class="controls">
      <input type="text" name="username" id="inputUser" placeholder="用户名" required autofocus="autofocus">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">密码</label>
    <div class="controls">
      <input type="password" name="password" id="inputPassword" required placeholder="密码">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">登录</button>
    </div>
  </div>
</form>
</div>
</body>

</html>