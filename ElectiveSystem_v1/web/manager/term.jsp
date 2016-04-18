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
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;管理员:<%=master.getM_name()%><small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号:<%=master.getM_id()%></small></h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="index.jsp">基本信息</a></li>
	<li role="presentation" class="active"><a href="term.jsp">学期管理</a></li>
	<li role="presentation"><a href="student.jsp">学生管理</a></li>
	<li role="presentation"><a href="teacher.jsp">教师管理</a></li>
	<li role="presentation"><a href="manager.jsp">管理员管理</a></li>
	<li role="presentation"><a href="course.jsp">课程管理</a></li>
	<li role="presentation"><a href="class.jsp">开课管理</a></li>
	<li role="presentation"><a href="selection.jsp">选课管理</a></li>
	<li role="presentation"><a href="logout">退出系统</a></li>
</ul>
</body>
</html>