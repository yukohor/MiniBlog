<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記事一覧</title>
</head>
<body>
<h1>記事一覧</h1>
<a href="post.jsp">新規投稿</a>
<ul>
<%
List<Atricle> articleList= (List<Aticle>) request.getAttribute("articleList");
for (Article article : articleList) {
%>
<li>
<a href="ShowArticleServlet?id=<%= article.getId() %>">
<%= article.getTitle() %> (<%= article.getUsername() %>)
</a>
</ul>

::contentReference[oaicite:0]{index=0}



</body>
</html>
 
