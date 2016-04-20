<%@ page import="com.wolfogre.domain.Manager" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%--
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
	<link rel="stylesheet" href="/css/bootstrap.css">
	<title>管理员页面</title>
</head>
<body>
<%
	Manager master = (Manager)session.getAttribute("master");
	if(master == null){
		response.sendRedirect("/index.action");
		return;
	}
%>
<div class="page-header" >
	<div>
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;管理员:<%=master.getM_name()%><small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号:<%=master.getM_id()%></small></h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="managerIndex.action">基本信息</a></li>
	<li role="presentation" class="active"><a href="managerTerm.action">学期管理</a></li>
	<li role="presentation"><a href="managerStudent.action">学生管理</a></li>
	<li role="presentation"><a href="managerTeacher.action">教师管理</a></li>
	<li role="presentation"><a href="managerManager.action">管理员管理</a></li>
	<li role="presentation"><a href="managerCourse.action">课程管理</a></li>
	<li role="presentation"><a href="managerClass.action">开课管理</a></li>
	<li role="presentation"><a href="managerSelection.action">选课管理</a></li>
	<li role="presentation"><a href="logout.action">退出系统</a></li>
</ul>
<div class="jumbotron" style="height: 100%">
	
</div>
</body>
</html>
