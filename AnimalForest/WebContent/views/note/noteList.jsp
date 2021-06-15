<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>쪽지함</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
		window.onload = function () {
			parent.changeNavi(${type});
		}
		function showContents(num, type) {
			location.href = "/noteContent?noteNo="+num+"&type="+type+"&currentPage="+${currentPage};
		}
	</script>
<style>
table {
	width: 100%;
	text-align: center;
	table-layout: fixed;
}

tr:not (.navi ):hover {
	cursor: pointer;
}

td {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.pagination {
	margin-top: 0px;
	margin-bottom: 0px;
}
</style>
</head>
<body>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>제목</th>
				<th>내용</th>
				<th>보낸이</th>
				<th>보낸날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pd.getPageList()}" var="note" varStatus="status">
				<c:set var="idx" value="${status.index}" />
				<tr onclick="showContents(${note.getNoteNo()}, ${type})">
					<td>${note.getNoteTitle()}</td>
					<td>${note.getNoteContents()}</td>
					<td>${note.getSenderId()}</td>
					<td>${note.getNoteDate()}</td>
				</tr>
				<%-- <c:choose>
					<c:when test="${idx%5 eq 0}">
						<tr class="success"
							onclick="showContents(${note.getNoteNo()}, ${type})">
							<td>${note.getNoteTitle()}</td>
							<td>${note.getNoteContents()}</td>
							<td>${note.getSenderId()}</td>
							<td>${note.getNoteDate()}</td>
						</tr>
					</c:when>
					<c:when test="${idx%5 eq 1}">
						<tr class="danger"
							onclick="showContents(${note.getNoteNo()}, ${type})">
							<td>${note.getNoteTitle()}</td>
							<td>${note.getNoteContents()}</td>
							<td>${note.getSenderId()}</td>
							<td>${note.getNoteDate()}</td>
						</tr>
					</c:when>
					<c:when test="${idx%5 eq 2}">
						<tr class="info"
							onclick="showContents(${note.getNoteNo()}, ${type})">
							<td>${note.getNoteTitle()}</td>
							<td>${note.getNoteContents()}</td>
							<td>${note.getSenderId()}</td>
							<td>${note.getNoteDate()}</td>
						</tr>
					</c:when>
					<c:when test="${idx%5 eq 3}">
						<tr class="warning"
							onclick="showContents(${note.getNoteNo()}, ${type})">
							<td>${note.getNoteTitle()}</td>
							<td>${note.getNoteContents()}</td>
							<td>${note.getSenderId()}</td>
							<td>${note.getNoteDate()}</td>
						</tr>
					</c:when>
					<c:when test="${idx%5 eq 4}">
						<tr class="active"
							onclick="showContents(${note.getNoteNo()}, ${type})">
							<td>${note.getNoteTitle()}</td>
							<td>${note.getNoteContents()}</td>
							<td>${note.getSenderId()}</td>
							<td>${note.getNoteDate()}</td>
						</tr>
					</c:when>
				</c:choose> --%>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr class="navi">
				<td colspan="4" align="center">
					<nav aria-label="pageNavi">
						<ul class="pagination pagination-lg">
							<li class="page-item">${pd.getPageNavi()}</li>
						</ul>
					</nav>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>