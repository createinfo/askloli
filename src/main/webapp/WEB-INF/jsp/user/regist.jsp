<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>

<body class="container">

<div id="regist" class="span8 offset3">

<form class="form-horizontal" id="regist" action="regist"  method="post">
  <div class="control-group">
    <label class="control-label" for="inputUser">用户名</label>
    <div class="controls">
      <input type="text" name="username" id="inputUser" placeholder="用户名" required autofocus="autofocus">
      <span hidden="hidden" class="">用户名已被占用</span>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">密码</label>
    <div class="controls">
      <input name="password" type="password" id="inputPassword" required placeholder="密码">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="repeat">重复密码</label>
    <div class="controls">
      <input type="password" id="repeat" required placeholder="重复密码">
      <span hidden="hidden" class="">密码不一致</span>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">邮箱</label>
    <div class="controls">
      <input name="email" type="email" id="inputPassword" required placeholder="邮箱">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">注册</button>
    </div>
  </div>
</form>


</div>

</body>

</html>