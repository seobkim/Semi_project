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
  

      <h1>찾은 비밀번호 </h1>

        <p>당신의 비밀번호는 : ${memberPwd }입니다 </p> 

 <a href="/views/member/login.html">로그인하기  </a> 
</center>
</body>
</html>