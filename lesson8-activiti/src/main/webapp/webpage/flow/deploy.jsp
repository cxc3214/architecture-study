<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <title>deploy.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    

  </head>
  
  <body>
    <table width="400px;">
		<thead>
			<tr>
				<th>流程ID</th>
				<th>流程名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="temp">
			<tr>
				<td>${temp.id}</td>
				<td>${temp.name}</td>
				<td>
					<a href="${webRoot}/flow/start.do?id=${temp.id}">启动流程</a>
					<a href="${webRoot}/flow/graphics.do?definitionId=${temp.id}">图形</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>
