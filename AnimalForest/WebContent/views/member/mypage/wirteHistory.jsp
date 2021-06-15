<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>내가 쓴 게시물</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
		/* Remove the navbar's default margin-bottom and rounded borders */
		.navbar {
			margin-bottom: 0;
			border-radius: 0;
		}
		
		/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
		.row.content {
			height: 450px
		}
		
		/* Set gray background color and 100% height */
		.sidenav {
			padding-top: 20px;
			background-color: #f1f1f1;
			height: 100%;
		}
		
		/* Set black background color, white text and some padding */
		footer {
			background-color: #555;
			color: white;
			padding: 15px;
		}
		
		th {
			text-align: center;
		}
		
		table * {
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		/* On small screens, set height to 'auto' for sidenav and grid */
		@media screen and (max-width: 767px) {
			.sidenav {
				height: auto;
				padding: 15px;
			}
			.row.content {
				height: auto;
			}
		}
		.category{
			text-align: center;
		}
		.cdt{
			text-align: center;
		}
	</style>
</head>
<body>
	<table class="table table-striped">
		<thead>
			<tr>
				<th width="15%">카테고리</th>
				<th width="20%">제목</th>
				<th width="50%">내용</th>
				<th width="15%">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pd.getPageList()}" var="board" varStatus="i">
				<tr>
					<td class="category"><c:choose>
							<c:when test="${board.getCategoryNum() eq 1}">
									입양
								</c:when>
							<c:when test="${board.getCategoryNum() eq 2}">
									봉사
								</c:when>
							<c:when test="${board.getCategoryNum() eq 3}">
									후원
								</c:when>
							<c:when test="${board.getCategoryNum() eq 4}">
									혜택
								</c:when>
							<c:when test="${board.getCategoryNum() eq 5}">
									후기
								</c:when>
						</c:choose></td>
					<td class="title">${board.getTitle()}</td>
					<td class="content">${board.getContent()}</td>
					<td class="cdt">${board.getCdt()}</td>
				</tr>
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