<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>mvc-主页</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <% 
    	String message = (String)request.getAttribute("hehe");     
    %>
    客户端的消息是：
    <%=message %>
    <form action="<%=basePath %>dologin" method="post"><br>
    姓名： <input name="name" /><br>

    性别： <input name="sex" /><br>
   描述： <textarea name="descript" rows="4" cols="50"> 
   
   </textarea><br>
    
    <input type="submit" value="go">
    </form>
  </body>
</html>
