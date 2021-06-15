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
<title>탈퇴성공 </title>
</head>
<body>
	<h1>회원탈퇴되었습니다 </h1>
	
	<br>
	<h2>그동안 감사했습니다</h2>
	<h2>메인페이지로 이동합니다.</h2>
	<script type="text/javascript">
		window.onload = function () {
			setTimeout("main()", 1000);
		}
		function main() {
			parent.location.href = "/";
		}
	</script>
</body>
</html>