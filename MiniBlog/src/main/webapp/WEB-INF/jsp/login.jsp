<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini Blog</title>
</head>
<body>
<h1>Mini Blog</h1>
<p>
<c:out value="${loginUser.username}" /> Login
<a href="Logout"> LOGOUT</a>
<form action="LoginServlet" method="post">
    ユーザー名: <input type="text" name="username"><br>
    パスワード: <input type="password" name="password"><br>
    <input type="submit" value="ログイン">
</form>

</p>
<c:if test="${not empty errorMsg}">
	<p><c:out value="${errorMsg}" /></p>
	</c:if>
</body>
</html>