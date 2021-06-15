<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>편지쓰기</title>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<form action = "/writeNote" method = "post">
		<table class = "table table-striped">
			<tr>
				<td colspan = "3">받는사람 : <input type = "text" name = "receiverId" value = "${param.receiverId}"></td>
			</tr>
			<tr>
				<td colspan = "3">보낸사람 : <input type = "text" name = "senderId" value = "${param.senderId}" 
													readonly onfocus="this.blur();" style = "color:#808080; background-color:#ffffff;"></td>
			</tr>
			<tr>
				<td colspan = "3">제    목 : <input type = "text" name = "title" style = "width:90%"></td>
			</tr>
			<tr>
				<td colspan = "3"><textarea rows = "10" style = "width:100%;" name = "contents"></textarea></td>
			</tr>
			<tr align = "center">
				<td><input type = "submit" value = "전송"></td>
				<td><input type = "reset" value = "취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>