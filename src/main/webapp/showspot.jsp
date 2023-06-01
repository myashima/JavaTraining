<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Let's Go!</title>
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
		<br> <a href="/Ensyu/login-result.jsp"><font
			face="Comic Sans MS">ちいきときせつをえらびなおす</font></a> <br>
		<div class="content">
			<div class="bg"></div>
		</div>
	</div>
</body>
</html>