<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Member member = (Member)session.getAttribute("member");

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<h1>로그인을 성공하였습니다.</h1>
	[${sessionScope.member.getMemberId()}]님 환영합니다.
	<br>
	<h2>1초 후 메인페이지로 이동합니다.</h2>
	<script type="text/javascript">
		window.onload = function () {
			setTimeout("main()", 1000);
		}
		function main() {
			opener.location.href = "/";
			window.close();
		}
	</script>
</body>
</html>