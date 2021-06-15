<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, member.model.service.MemberService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>찾기</title>
</head>
<body>
<center>
   <h1>찾은 아이디</h1>
   <%-- <p>${member.memberId}</p> --%>
   <p>당신의 아이디 : ${memberId} 입니다.</p> <br>
   
        <a href="/views/member/login.html">로그인하기  </a> / <a href="/views/member/searchPwd.jsp">비밀번호찾기</a>


</center>
</body>
</html>