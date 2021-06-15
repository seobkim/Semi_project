<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>아이디찾기</title>
	<style type="text/css">
		a:hover {
			text-decoration: none;
			color: gray;
			cursor: pointer;
		}	
	</style>
</head>
<body>
	
	<div class="jumbotron text-center">
		<div class="container">

			<c:if test="${member!=null}">
				<h2>아이디확인</h2>
            
            검색한 아이디는 : <c:out value="${memberId}" />


			</c:if>

			<h2>아이디찾기</h2>
			<legend>
				<form action="/memberSearchId" method="post">


					이름 : <input type="text" id="userName" name="userName"><br>
					<br> 연락처 <input type="text" id="phone" name="phone"> <br>
					<br> email <input type="email" id="email" name="email"><br>
					<br> <input type="submit" value="아이디찾기 " id="searchId"
						style="width: 250px"><br> <a
						href="/views/member/login.html">로그인하기 </a> / <a
						href="/views/member/searchPwd.jsp">비밀번호찾기</a>
				</form>
			</legend>
		</div>
	</div>
</body>
</html>