<%@ page import="com.wolfogre.domain.Student" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
	<title>学生页面</title>
</head>
<body>
<%
	Student master = (Student)session.getAttribute("master");
	if(master == null){
		response.sendRedirect("/index.action");
		return;
	}
%>
<div class="page-header" >
	<div>
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;学生:<%=master.getS_name()%><small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:<%=master.getS_id()%></small></h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation" class="active"><a href="studentIndex.action">基本信息</a></li>
	<li role="presentation"><a href="studentTimetable.action">课表查询</a></li>
	<li role="presentation"><a href="studentScore.action">成绩查询</a></li>
	<li role="presentation"><a href="studentCourse.action">选课退课</a></li>
	<li role="presentation"><a href="logout.action">退出系统</a></li>
</ul>
<div class="jumbotron" style="height: 100%">
	<div class="panel panel-info center-block" style="width: 50%">
		<div class="panel-body">
			<h2>姓名：<%=master.getS_name()%></h2>
			<h2>学号：<%=master.getS_id()%></h2>
			<h2>身份：学生</h2>
			<h2>当前时间：<%=new SimpleDateFormat("yyyy年M月dd日 HH时mm分").format(new java.util.Date())%></h2>
		</div>
	</div>
</div>
</body>
</html>