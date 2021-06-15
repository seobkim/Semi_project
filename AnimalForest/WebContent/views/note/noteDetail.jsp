<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>쪽지상세</title>
	<style>
		textarea {
			resize: none;
		}
		div:hover{
			cursor: pointer
		}
		table{
		/* 	table-layout: fixed; */
		}
		
	</style>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<table class = "table table-striped">
		<tr>
			<td colspan = "3">받는사람 : ${note.getReceiverId()}</td>
		</tr>
		<tr>
			<td colspan = "3">보낸사람 : ${note.getSenderId()}</td>
		</tr>
		<tr>
			<td colspan = "3">제목 : ${note.getNoteTitle()}</td>
		</tr>
		<tr>
			<td colspan = "3"><textarea rows = "10" style = "width:100%;" readonly>${note.getNoteContents()}</textarea></td>
		</tr>
		<tr align = "center">
			<td class = "btn" onclick = "backList()"><div>목록으로</div></td>
			<c:if test="${type eq 1}">
				<td class = "btn" onclick = "reply()"><div>답장하기</div></td>
				<td class = "btn" onclick = "deleteNote(${note.getNoteNo()})"><div>삭제</div></td>
			</c:if>
		</tr>
	</table>
	<script type="text/javascript">
		window.onload = function() {
			if(${type eq 1}){
				$(".btn").css("width","33%");
			}else{
				$(".btn").css("width","100%");
			}
		}
		function backList() {
			if(${type eq 1}){
				location.href = "/receiveNoteList?currentPage="+${param.currentPage};
			}else if(${type eq 2}){
				location.href = "/sendNoteList?currentPage="+${param.currentPage};
			}
			
		}
		function deleteNote(num) {
			location.href = "/deleteNote?currentPage="+${param.currentPage}+"&noteNo="+num;
		}
		function reply() {
			location.href = "/views/note/replyNote.jsp?receiverId=${note.getSenderId()}&senderId=${note.getReceiverId()}";	
		}
	</script>
</body>
</html>