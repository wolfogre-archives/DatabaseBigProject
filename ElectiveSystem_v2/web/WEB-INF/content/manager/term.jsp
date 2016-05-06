<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wolfogre.domain.Manager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.wolfogre.Information" %>
<%@ page import="com.wolfogre.domain.Term" %>

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

	<!-- BootStrap CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/BootStrap/css/bootstrap.min.css"/>
	<!--DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/DataTables/css/jquery.dataTables.css"/>
	<!-- jQuery -->
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/DataTables/js/jquery.js"></script>
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/DataTables/js/jquery.dataTables.js"></script>

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
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;管理员:<%=master.getM_name()%>
			<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号:<%=master.getM_id()%></small>
			<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Information.getCopyRight()%></small>
		</h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="index.action">基本信息</a></li>
	<li role="presentation" class="active"><a href="term.action">学期管理</a></li>
	<li role="presentation"><a href="student.action">学生管理</a></li>
	<li role="presentation"><a href="teacher.action">教师管理</a></li>
	<li role="presentation"><a href="manager.action">管理员管理</a></li>
	<li role="presentation"><a href="course.action">课程管理</a></li>
	<li role="presentation"><a href="open-course.action">开课管理</a></li>
	<li role="presentation"><a href="select-course.action">选课管理</a></li>
	<li role="presentation"><a href="${pageContext.request.contextPath}/login.action">退出登录</a></li>
</ul>
<div class="jumbotron" style="height: 100%">
	<%
		if(request.getAttribute("error") != null){
	%>
	<div class="alert alert-danger center-block" role="alert" style="width: 500px;" align="center">
		<strong>错误：</strong><%=request.getAttribute("error")%>
	</div>
	<%
		}
		Term nowTerm = (Term)request.getAttribute("term");
	%>
	<div class="panel panel-info center-block" style="width: 50%">
		<div class="panel-body text-center">
			<h2>当前学期：<%=Information.getTermName()%></h2>
			<form class="form-inline" action="update-term.action" method="get">
				<div class="form-group">
					<label for="sel_begin">选课时间：</label>
					<input type="text" class="form-control" id="sel_begin" name="sel_begin" value="<%=Information.getDateFormat().format(nowTerm.getD_sel_begin().getTime())%>">
				</div>
				<div class="form-group">
					<label for="sel_end">至</label>
					<input type="text" class="form-control" id="sel_end" name="sel_end" value="<%=Information.getDateFormat().format(nowTerm.getD_sel_end().getTime())%>">
				</div>
				<button type="submit" class="btn btn-default">修改</button>
			</form>
			<form class="form-inline" action="update-term.action" method="get">
				<div class="form-group">
					<label for="reg_begin">登分时间：</label>
					<input type="text" class="form-control" id="reg_begin" name="reg_begin" value="<%=Information.getDateFormat().format(nowTerm.getD_reg_begin().getTime())%>">
				</div>
				<div class="form-group">
					<label for="reg_end">至</label>
					<input type="text" class="form-control" id="reg_end" name="reg_end" value="<%=Information.getDateFormat().format(nowTerm.getD_reg_end().getTime())%>">
				</div>
				<button type="submit" class="btn btn-default">修改</button>
			</form>
			<form class="form-inline" action="update-term.action" method="get">
				<div class="form-group">
					<label for="inq_begin">查分时间：</label>
					<input type="text" class="form-control" id="inq_begin" name="inq_begin" value="<%=Information.getDateFormat().format(nowTerm.getD_inq_begin().getTime())%>">
				</div>
				<div class="form-group">
					<label for="inq_end">至</label>
					<input type="text" class="form-control" id="inq_end" name="inq_end" value="<%=Information.getDateFormat().format(nowTerm.getD_inq_end().getTime())%>">
				</div>
				<button type="submit" class="btn btn-default">修改</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>