<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <%@ page import="model.User" %>
    <%
    User loginUser = (User)session.getAttribute("loginUser");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini Blog</title>
</head>
<body>
<h1>Mini Blog</h1>
<% if(loginUser != null) { %>
<p> Success Login! </p>
<p> Wellcome<%= loginUser.getUserame() %></p>
<% } else { %>
<p> Unsuccess login please try it again</p>
<% } %>
</body>
</html>