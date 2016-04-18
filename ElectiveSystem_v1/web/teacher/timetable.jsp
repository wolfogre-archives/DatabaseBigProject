<%@ page import="com.wolfogre.domain.Teacher" %><%--
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
	<title>教师页面</title>
</head>
<body>
<%
	Teacher master = (Teacher)session.getAttribute("master");
	if(master == null){
		response.sendRedirect("/index.jsp");
		return;
	}
%>
<div class="page-header" >
	<div>
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;教师:<%=master.getT_name()%><small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号:<%=master.getT_id()%></small></h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="index.jsp">基本信息</a></li>
	<li role="presentation" class="active"><a href="timetable.jsp">课表查询</a></li>
	<li role="presentation"><a href="list.jsp">学生名单查询</a></li>
	<li role="presentation"><a href="score.jsp">成绩登记</a></li>
	<li role="presentation"><a href="logout">退出系统</a></li>
</ul>
</body>
</html>
