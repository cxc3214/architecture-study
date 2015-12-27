<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<title>task.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

</head>

<body>
	<h2>任务列表</h2>
	<table width="400px;">
		<thead>
			<tr>
				<th>任务ID</th>
				<th>任务名称</th>
				<th>任务执行人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="temp">
			<tr>
				<td>${temp.id}</td>
				<td>${temp.name}</td>
				<td>${temp.assignee}</td>
				<td>
					<!-- <a href="../process/complete?id=${temp.id}">完成</a> -->
					<a href="${webRoot}/flow/complete.do?id=${temp.id}">完成</a>
					<a href="${webRoot}/flow/graphics.do?taskId=${temp.id}">图形</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
