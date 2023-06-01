<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>show destination</title>
<link rel="stylesheet" href="button.css">
<link rel="stylesheet" href="secret.css">
<link rel="stylesheet" href="font.css">
<style>
body {
	background: #eaf4ff;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div style="text-align: center">

		<div class="font_test size_test">旅行先きめます</div>

		<form action="/Ensyu/LoginServlet" method="post">
			<input type="submit" value="表示" class="button">
			<input type="hidden" name="action" value="select" />
		</form>
		<br>
		<div class="font_test size_test">条件を追加する</div>
		<font size="4" color="#d3381c">${ message }</font>
		<div class="selectItem">
			<form action="/Ensyu/LoginServlet" method="post">
				<div>
					<label><input type="checkbox" name="spring" value="春">春</label>
					<label><input type="checkbox" name="summer" value="夏">夏</label>
					<label><input type="checkbox" name="autumn" value="秋">秋</label>
					<label><input type="checkbox" name="winter" value="冬">冬</label>
				</div>
				<div>
					<label><input type="radio" name="area" value="北海道">北海道</label>
					<label><input type="radio" name="area" value="東北">東北</label>
					<label><input type="radio" name="area" value="関東">関東</label>
					<label><input type="radio" name="area" value="甲信越">甲信越</label>
				</div>
				<div>
					<label><input type="radio" name="area" value="北陸">北陸</label>
					<label><input type="radio" name="area" value="東海">東海</label>
					<label><input type="radio" name="area" value="関西">関西</label>
					<label><input type="radio" name="area" value="中国">中国</label>
				</div>
				<div>
					<label><input type="radio" name="area" value="四国">四国</label>
					<label><input type="radio" name="area" value="九州">九州</label>
					<label><input type="radio" name="area" value="沖縄">沖縄</label>
				</div>
				<p>
					<input type="submit" value="表示" class="button"> <input
						type="hidden" name="action" value="random" />
				</p>
			</form>
			<a href="/Ensyu/mypage.jsp"><font face="Comic Sans MS">
			My Page</font></a><br> <br> <a href="/Ensyu/toppage.jsp">
			<font 	face="Comic Sans MS"> Log Out</font></a>
		</div>
	</div>
	<div style="position: absolute; top: 360px; left: 900px">
		<p>
			<a href="/Ensyu/secret3.jsp"><img src=jpg/ハチワレアップ.png width="180"
				height="130" alt="ハチワレ"> </a>
		</p>
	</div>
</body>
</html>