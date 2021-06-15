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
<title>고객정보수정완료</title>
</head>
<body>
	
	<h1>[${sessionScope.member.getMemberId()}]고객정보수정이 완료되었습니다 </h1>
	<br>
	<h2>3초 후 메인페이지로 이동합니다.</h2>
<script type="text/javascript">
	window.onload = function () {
		setTimeout("main()", 3000);
	}
	function main() {
		parent.location.href = "/";
	}
</script>
</body>
</html>