<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="LoginServlet" method="post">
		ユーザー名: <input type="text" name="username"><br> パスワード: <input
			type="password" name="password"><br> <input
			type="submit" value="ログイン">
	</form>
	<p style="color: red;">${errorMsg}</p>
</body>
</html>