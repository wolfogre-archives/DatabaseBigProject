<%@ page import="com.wolfogre.domain.Teacher" %>
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

	<title>教师页面</title>
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
			},
			paging: false,//禁用分页
			searching: false,//禁用搜索
			ordering:  false,//禁用排序
			info: false//禁用最下角信息
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
			},
			paging: false,//禁用分页
			searching: false,//禁用搜索
			ordering:  false,//禁用排序
			info: false//禁用最下角信息
		});
	});
</script>
<body>
<%
	Teacher master = (Teacher)session.getAttribute("master");
	if(master == null){
		response.sendRedirect("/index.action");
		return;
	}
%>
<div class="page-header" >
	<div>
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;教师:<%=master.getT_name()%>
			<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号:<%=master.getT_id()%></small>
			<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Information.getCopyRight()%></small>
		</h2>
	</div>
</div>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="index.action">基本信息</a></li>
	<li role="presentation" class="active"><a href="timetable.action">课表查询</a></li>
	<li role="presentation"><a href="list.action">学生名单查询</a></li>
	<li role="presentation"><a href="score.action">成绩登记</a></li>
	<li role="presentation"><a href="${pageContext.request.contextPath}/login.action">退出登录</a></li>
</ul>
<table id="dataTable1" class="display" cellspacing="0" width="100%">
	<thead>
	<tr>
		<th>序号</th>
		<th>课程号</th>
		<th>课程名</th>
		<th>学分</th>
		<th>上课时间</th>
		<th>上课地点</th>
	</tr>
	</thead>

	<tbody>

	<%
		HashMap<Character, HashMap<String, Object>> selectData = (HashMap<Character, HashMap<String, Object>>)request.getAttribute("selectData");
		Information.initTimeTable();
		for(char index = 'A'; selectData.get(index) != null; ++index )
		{
	%>
	<tr>
		<td><%=index%></td>
		<td><%=selectData.get(index).get("课程号")%></td>
		<td><%=selectData.get(index).get("课程名")%></td>
		<td><%=selectData.get(index).get("学分")%></td>
		<td><%=Information.getCourseTimeString(index, selectData.get(index).get("上课时间").toString())%></td>
		<td><%=selectData.get(index).get("上课地点")%></td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>
<table id="dataTable2" class="cell-border" style="text-align:center" cellspacing="0" width="100%">
	<thead>
	<tr>
		<th  style="text-align:center">时间</th>
		<th  style="text-align:center">星期一</th>
		<th  style="text-align:center">星期二</th>
		<th  style="text-align:center">星期三</th>
		<th  style="text-align:center">星期四</th>
		<th  style="text-align:center">星期五</th>
		<th  style="text-align:center">星期六</th>
		<th  style="text-align:center">星期日</th>
	</tr>
	</thead>

	<tbody>

	<%
		char[][] timeTable = Information.getTimeTable();
		for(int raw = 0; raw < 13; ++raw)
		{
	%>
	<tr>
		<td><%=raw + 1%></td>
		<%
			for(int col = 0; col < 7; ++col) {
				if(timeTable[raw][col] != Character.MIN_VALUE){
		%><td><%=timeTable[raw][col]%></td><%
	} else {
	%><td></td><%
			}
		}
	%>
	</tr>
	<%
		}
	%>
	</tbody>
</table>
</body>
</html>