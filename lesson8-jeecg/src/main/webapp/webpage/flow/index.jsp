<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <title>index.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>  
  <body>
    	<table>
    		<tr>
    			<td></td>
    		</tr>
    		<c:forEach items="${list}" var="item">
    		<tr>
    			<td>${item}</td>
				<td><a href="${webRoot}/flow/deploy.do?processName=${item}">部署</a></td>
    		</tr>
    		</c:forEach>
    	</table>
  </body>
</html>
