<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top Page</title>
<link rel="stylesheet" href="button.css">
<style type="text/css">
body {
	background-image: url("jpg/富士山.jpg"); /* 画像 */
	background-size: cover; /*  全画面 */
}
</style>

</head>
<body>
	<div style="text-align: center;">
		<br>
		<h2>
			<font face="Comic Sans MS">Log in</font>
		</h2>

		<form action="/Ensyu/LoginServlet" method="post">
			<p>
				<font face="Comic Sans MS">LoginID：</font>
				<input type="text" name="id" size="20" placeholder="ログインID(半角英数字)">
			</p>
			<p>
				<font face="Comic Sans MS">Password：</font>
				<input type="password" name="pass" size="20" placeholder="パスワード(半角英数字)">
				<input type="hidden" name="action" value="login" />
			</p>
			<button type="submit" value="Log in" class="button">
				<font face="Comic Sans MS">Log in</font>
			</button>
			<p>
				<a href="/Ensyu/newmember.jsp"> <font face="Comic Sans MS">
				Sign up</font></a>
		</form>
	</div>
</body>
</html>