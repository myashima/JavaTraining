<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Let's Go!</title>
<link rel="stylesheet" href="button.css">
<link rel="stylesheet" href="background.css">
<link rel="stylesheet" href="font.css">
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div style="text-align: center;">
		<div class="font_test size_test">選ばれたいきさきは...</div>
		<br>
		<table align="center">
			<c:forEach items="${show}" var="list">
				<tr>
					<td>${list.pref}</td>
					<td>${list.spot }</td>
				</tr>
			</c:forEach>
		</table>

		<br>
		<form action="/Ensyu/LoginServlet" method="post">
		<input type="submit" value="再検索" class="button">
		<input type="hidden" name="action" value="select" />
		</form>
		<br>
		<div class="font_test size_test">
			<a href="/Ensyu/login-result.jsp">もどる</a>
		</div>
	</div>
</body>
</html>