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
<meta http-equiv="refresh" content="1 url=/">
<title>회원가입성공</title>
</head>
<body>
	<h1>회원가입을 축하드립니다~~</h1>
	
	<br>
	<h2>메인화면으로  이동합니다.</h2>

</body>
</html>