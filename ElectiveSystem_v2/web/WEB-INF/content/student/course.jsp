<%@ page import="com.wolfogre.domain.Student" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.wolfogre.Information" %>
<%@ page import="java.util.HashMap" %><%--
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

	<title>学生页面</title>
</head>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		$('#dataTable1').DataTable({
			language: {
				"sProcessing": "处理中...",
				"sLengthMenu": "显示 _MENU_ 项结果",
				"sZeroRecords": "没有匹配结果",
				"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix": "",
				"sSearch": "搜索:",
				"sUrl": "",
				"sEmptyTable": "表中数据为空",
				"sLoadingRecords": "载入中...",
				"sInfoThousands": ",",
				"oPaginate": {
					"sFirst": "首页",
					"sPrevious": "上页",
					"sNext": "下页",
					"sLast": "末页"
				},
				"oAria": {
					"sSortAscending": ": 以升序排列此列",
					"sSortDescending": ": 以降序排列此列"
				}
			}
		});
		$('#dataTable2').DataTable({
			language: {
				"sProcessing": "处理中...",
				"sLengthMenu": "显示 _MENU_ 项结果",
				"sZeroRecords": "没有匹配结果",
				"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix": "",
				"sSearch": "搜索:",
				"sUrl": "",
				"sEmptyTable": "表中数据为空",
				"sLoadingRecords": "载入中...",
				"sInfoThousands": ",",
				"oPaginate": {
					"sFirst": "首页",
					"sPrevious": "上页",
					"sNext": "下页",
					"sLast": "末页"
				},
				"oAria": {
					"sSortAscending": ": 以升序排列此列",
					"sSortDescending": ": 以降序排列此列"
				}
			}
		});
	});
</script>
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
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;学生:<%=master.getS_name()%>
			<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:<%=master.getS_id()%></small>
			<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Information.getCopyRight()%></small>
		</h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="index.action">基本信息</a></li>
	<li role="presentation"><a href="timetable.action">课表查询</a></li>
	<li role="presentation"><a href="score.action">成绩查询</a></li>
	<li role="presentation" class="active"><a href="course.action">选课退课</a></li>
	<li role="presentation"><a href="${pageContext.request.contextPath}/login.action">退出登录</a></li>
</ul>
<%
	if(!Information.isInInquireTime()){
%>
<div class="jumbotron" style="height: 100%">
	<div class="panel panel-info center-block" style="width: 50%">
		<div class="panel-body">
			<h1>选课时间未到</h1>
			<h2>当前学期：<%=Information.getTermName()%></h2>
			<h2>当前时间：<%=new SimpleDateFormat("yyyy年M月dd日 HH时mm分").format(new java.util.Date())%></h2>
			<h2>选课开始时间：<%=new SimpleDateFormat("yyyy年M月dd日 HH时mm分").format(Information.getSelectBeginTime())%></h2>
			<h2>选课结束时间：<%=new SimpleDateFormat("yyyy年M月dd日 HH时mm分").format(Information.getSelectEndTime())%></h2>
		</div>
	</div>
</div>
<%
		return;
	}
%>
<%
	if(request.getAttribute("error") != null){
%>
<div class="alert alert-danger center-block" role="alert" style="width: 500px;" align="center">
	<strong>选课失败：</strong><%=request.getAttribute("error")%>
</div>
<%
	}
%>
<table id="dataTable1" class="display" cellspacing="0" width="100%">
	<h2 align="center">可选课程</h2>
	<thead>
	<tr>
		<th>课程号</th>
		<th>课程名</th>
		<th>教师号</th>
		<th>教师名</th>
		<th>学分</th>
		<th>上课时间</th>
		<th>上课地点</th>
		<th>选课人数</th>
		<th></th>
	</tr>
	</thead>

	<tbody>
	<%
		HashMap<Integer, HashMap<String, Object>> 可选课程 = (HashMap<Integer, HashMap<String, Object>>)request.getAttribute("可选课程");
		for(HashMap.Entry<Integer, HashMap<String, Object>> data : 可选课程.entrySet())
		{
	%>
	<tr>
		<td><%=data.getValue().get("课程号")%></td>
		<td><%=data.getValue().get("课程名")%></td>
		<td><%=data.getValue().get("教师号")%></td>
		<td><%=data.getValue().get("教师名")%></td>
		<td><%=data.getValue().get("学分")%></td>
		<td><%=Information.getCourseTimeString(data.getValue().get("上课时间").toString())%></td>
		<td><%=data.getValue().get("上课地点")%></td>
		<td><%=data.getValue().get("选课人数")%></td>
		<td><a href="update-course?new_data=new_data&o_id=<%=data.getKey()%>">选课</a></td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>

<table id="dataTable2" class="display" cellspacing="0" width="100%">
	<h2 align="center">已选课程</h2>
	<thead>
	<tr>
		<th>课程号</th>
		<th>课程名</th>
		<th>教师号</th>
		<th>教师名</th>
		<th>学分</th>
		<th>上课时间</th>
		<th>上课地点</th>
		<th>选课人数</th>
		<th></th>
	</tr>
	</thead>

	<tbody>
	<%
		HashMap<Integer, HashMap<String, Object>> 已选课程 = (HashMap<Integer, HashMap<String, Object>>)request.getAttribute("已选课程");
		for(HashMap.Entry<Integer, HashMap<String, Object>> data : 已选课程.entrySet())
		{
	%>
	<tr>
		<td><%=data.getValue().get("课程号")%></td>
		<td><%=data.getValue().get("课程名")%></td>
		<td><%=data.getValue().get("教师号")%></td>
		<td><%=data.getValue().get("教师名")%></td>
		<td><%=data.getValue().get("学分")%></td>
		<td><%=Information.getCourseTimeString(data.getValue().get("上课时间").toString())%></td>
		<td><%=data.getValue().get("上课地点")%></td>
		<td><%=data.getValue().get("选课人数")%></td>
		<td><a href="update-course?delete_data=delete_data&o_id=<%=data.getKey()%>">退课</a></td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>

</body>
</html>