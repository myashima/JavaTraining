<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報の変更</title>
<link rel="stylesheet" href="button.css">
<link rel="stylesheet" href="font.css">
<style>
body {
	background: #eaf4ff;
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<div class="font_test size_test">登録情報の変更</div>
		<form action="/Ensyu/LoginServlet" method="post">
			<p>
				<font face="Comic Sans MS">Name：</font>
				<input type="text" name="name" size="20" placeholder="名前">
			</p>
			<p>
				<font face="Comic Sans MS">LoginID：</font>
				<input type="text" name="id" size="20" placeholder="ログインID(半角英数字)">
			</p>
			<p>
				<font face="Comic Sans MS">Password：</font>
				<input type="password" name="pass" size="20" placeholder="パスワード(半角英数字)">
			</p>
			<input type="hidden" name="action" value="update" />
			<p>
				<input type="submit" value="変更" class="button">
			</p>
			<a href="/Ensyu/mypage.jsp"><font face="Comic Sans MS">
			 My Pgae</font></a>
			<p>
				<a><img src=jpg/全然わかんない.png width="211" height="170"
					alt="全然わかんない"></a>
			</p>

		</form>
	</div>
</body>
</html>