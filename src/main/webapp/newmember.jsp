<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="button.css">
<link rel="stylesheet" href="font.css">
<style>
body {
	background: #fdede4;
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<h2>
			<font face="Comic Sans MS">Sign up</font>
		</h2>
		<div class="font_test size_test3">IDとPasswordは４文字以上で入力してください</div>
		<form action="/Ensyu/LoginServlet" method="post">
			<label for="memberName" class="form-label"><font face="Comic Sans MS">name</font></label><br>
			<input type="text" class="form-control" id="memberName" name="name" required><br>
			<label for="id" class="form-label"><font face="Comic Sans MS">LoginID</font></label><br>
			<input type="text" class="form-control" id="loginid" name="id" required><br>
			<label for="password" class="form-label"><font face="Comic Sans MS">Password</font></label><br>
			<input type="text" class="form-control" id="password" name="pass" required><br><br>
			<input type="hidden" name="action" value="add" />
			<button type="submit" class="button btn-primary"><font face="Comic Sans MS">Sign up</font>
			</button>
			<br><br>
			<a href="/Ensyu/toppage.jsp?action=logout"><font face="Comic Sans MS">Top page</font></a>
		</form>
		<p>
			<a href="/Ensyu/secret2.jsp"><img src=jpg/おうえん.png width="300" height="240" alt="応援"></a>
		</p>
	</div>
</body>
</html>