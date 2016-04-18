<%@ page import="com.wolfogre.domain.Manager" %><%--
  Created by IntelliJ IDEA.
  User: Jason Song(wolfogre.com)
  Date: 2016/4/13
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<title>管理员页面</title>
</head>
<body>
<%
	Manager master = (Manager)session.getAttribute("master");
	if(master == null){
		response.sendRedirect("/index.jsp");
		return;
	}
%>
<div class="page-header" >
	<div>
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;姓名:<%=master.getM_name()%><small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号:<%=master.getM_id()%></small></h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation" class="active"><a href="#">基本信息</a></li>
	<li role="presentation"><a href="#">学期管理</a></li>
	<li role="presentation"><a href="#">学生管理</a></li>
	<li role="presentation"><a href="#">教师管理</a></li>
	<li role="presentation"><a href="#">管理员管理</a></li>
	<li role="presentation"><a href="#">课程管理</a></li>
	<li role="presentation"><a href="#">开课管理</a></li>
	<li role="presentation"><a href="#">选课管理</a></li>
	<li role="presentation"><a href="logout">退出系统</a></li>
</ul>
</body>
</html>
