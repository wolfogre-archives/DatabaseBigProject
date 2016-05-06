<%--
  Created by IntelliJ IDEA.
  User: Jason Song(wolfogre.com)
  Date: 2016/4/13
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- BootStrap CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/BootStrap/css/bootstrap.min.css"/>
  <!--DataTables CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/DataTables/css/jquery.dataTables.css"/>
  <!-- jQuery -->
  <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/DataTables/js/jquery.js"></script>
  <!-- DataTables -->
  <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/DataTables/js/jquery.dataTables.js"></script>

  <title>选课系统</title>
</head>
<body>
<div class="page-header">
  <h1 class="text-center">欢迎来到课程管理系统</h1>
</div>
<%
  if(request.getAttribute("error") != null){
%>
<div class="alert alert-danger center-block" role="alert" style="width: 500px;" align="center">
  <strong>登录失败！</strong><%=request.getAttribute("error")%>
</div>
<%
  }
%>
<div class="panel panel-default center-block" style="width:400px;padding:30px">
  <form action="check-login.action">
    <div class="form-group">
      <label for="id">学号/工号</label>
      <input type="text" class="form-control" name="id" id="id" placeholder="请输入学号/工号" required>
    </div>
    <div class="form-group">
      <label for="password">密码</label>
      <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码" required>
    </div>
    <div class="form-group">
      <label>登录类型</label>
    </div>
    <div class="checkbox text-center">
      <label>
        <input type="radio" id="login-type1" name="loginType" value="student" checked>学生
      </label>
      <label>
        <input type="radio" id="login-type2" name="loginType" value="teacher">教师
      </label>
      <label>
        <input type="radio" id="login-type3" name="loginType" value="manager">管理员
      </label>
    </div>
    <br/>
    <div class="text-center">
      <button type="submit" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;</button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="reset" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;重&nbsp;&nbsp;&nbsp;置&nbsp;&nbsp;&nbsp;&nbsp;</button>
    </div>
  </form>
</div>
</body>
</html>