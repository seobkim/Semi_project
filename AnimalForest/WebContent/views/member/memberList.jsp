<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,member.model.vo.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일반회원전체리스트</title>
</head>
<body>

	<h2 align="center">관리자 : 일반회원전체리스트</h2>

	<table border="1">
		<tr>
			<th>NO</th>
			<th>아이디</th>
			<th>프로필</th>
			<th>비밀번호</th>
			<th>이름</th>
			<!--  <th>TYPE </th> -->
			<th>닉네임</th>
			<th>EMAIL</th>
			<th>우편번호</th>
			<th>주소1</th>
			<th>주소2</th>
			<th>연락처</th>
			<th>관리번호</th>
			<th>가입날짜</th>
		</tr>
		<c:forEach items="${list}" var="mOne">
			<tr>
				<td>${mOne.memberNum}</td>
				<td>${mOne.memberId}</td>
				<td>${mOne.memberPhoto}</td>
				<td>${mOne.memberPwd}</td>
				<td>${mOne.memberName}</td>
				<%--   <td> ${mOne.memberType}</td> --%>
				<td>${mOne.memberNick}</td>
				<td>${mOne.memberEmail}</td>
				<td>${mOne.memberPost}</td>
				<td>${mOne.memberAddress}</td>
				<td>${mOne.memberAddressDetail}</td>
				<td>${mOne.memberPhone}</td>
				<td>${mOne.memberRegNum}</td>
				<td>${mOne.memberRegDate}</td>

				<%--   <td> ${mOne.getmemberNum() %></td>
   <td> <%=mOne.getmemberId()%></td>
   <td> <%=mOne.getmemberPhoto() %></td>
   <td> <%=mOne.getmemberPwd()%></td>
   <td> <%=mOne.getmemberName() %></td>
   <td> <%=mOne.getmemberType()%></td>
   <td> <%=mOne.getmemberNick()%> </td>
   <td> <%=mOne.getmemberEmail() %></td>
   <td> <%=mOne.getmemberPost()%></td>
   <td> <%=mOne.getmemberAddress() %></td>
   <td> <%=mOne.getmemberAddressDetail() %></td>
   <td> <%=mOne.getmemberPhone()%></td>
   <td><%=mOne.getmemberRegNum() %></td>
   <td> <%=mOne.getmemberRegDate() %></td>
    --%>
			</tr>
		</c:forEach>

	</table>
</body>
</html>