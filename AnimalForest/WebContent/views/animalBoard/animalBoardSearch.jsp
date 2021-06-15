<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}
.jumbotron {
	margin-bottom: 0;
}
footer {
	background-color: #f2f2f2;
	padding: 25px;
}
.panel-body {
	width: 100%;
	height: 100%;
}
.reverse {
	background: #4CAF50;
	color: black;
}
#write {
	position: absolute;
	right: 380px;
	color: white;
}
 a:link { color: black; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: black; text-decoration: none;}
</style></head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<c:forEach items="${pageData.pageList}" var="board">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>${board.title}</h4>
						</div>
						<div class="panel-body">
							<img src="/img/${board.fileName }" style="width: 100%; height: 100%;">
						</div>
						<div class="panel-footer">
							<p>
								 글쓴이 : ${board.memberName} <br>
								 주소 : ${board.memberAddress }<br>
								 연락처: ${board.memberPhone } <br>
						    </p>
							<button type="button" class="btn btn-success" ><a href="/animalBoardDetail?boardNo=${board.boardNo}&fileName=${board.fileName}&type=0">상세보기</a></button>
							<input type="hidden" name="boardNo" value="${board.boardNo}" />
							<input type="hidden" name="fileName" value="${board.fileName }" />
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<br><br><br>
	<div class="text-center">
		<form action="/boardSearch" method="get">
			<input type="text" name="search">
			<input type="submit" value="검색">
		</form>
	</div>
	<div class="text-center">
			<nav aria-label="pageNavi">
  				<ul class="pagination pagination-lg">
    					<li class="page-item">${pageData.pageNavi }</li>
    					<li> <button type="button" class="btn btn-success" id="write" onclick="boardWrite()">공고 올리기</button></li>	
  				</ul>
			</nav>
	</div><br>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$("li").hover(function() {
				$(this).addClass("reverse");
			}, function() {
				$(this).removeClass("reverse");
			});
		});
		function boardWrite(){
			location.href="/AnimalBoardWirtePageServlet"
		}
	</script>
</body></html>

