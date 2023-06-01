<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会</title>
<link rel="stylesheet" href="font.css">
<link rel="stylesheet" href="button.css">
<style>
body {
	background: #9fa09e;
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<br>
		<div class="font_test size_test">本当に退会しますか</div>
		<form action="/Ensyu/LoginServlet" method="post">
			<p>
				<input type="submit" value="Sign out" class="button">
			</p>
			<input type="hidden" name="action" value="delete" />
		</form>
		<img src=jpg/ガーン.png width="200" height="300" alt="ちいかわ"><br>
		<a href="/Ensyu/mypage.jsp"><font face="Comic Sans MS">
		My Page</font></a><br> <a href="/Ensyu/toppage.jsp">
		<font face="Comic Sans MS">Top page</font></a>
	</div>
</body>
</html>