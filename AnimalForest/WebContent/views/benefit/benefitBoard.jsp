<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>혜택 게시판</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default rounded borders and increase the bottom margin */
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}

/* Remove the jumbotron's default bottom margin */
.jumbotron {
	margin-bottom: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}

#null {
	text-align: center;
}

#menu {
	align-content: center;
	text-align: center;
}

input[name=menu] {
	align-content: center;
	text-align: center;
}
</style>
</head>

<body>

	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<br>
	<br>
	<h1 id="null">아직 준비되지 않았습니다. 업데이트를 기다려주세요.</h1>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			
		</div>
		<hr>
		<c:if test="${sessionScope.member.memberId=='admin' }">
			<a class="btn btn-default pull-right"
				href="views/benefit/benefitInsert.jsp?categoryNo=1">이벤트 글쓰기</a>
		</c:if>
		<c:if test="${sessionScope.member.memberId=='admin' }">
			<a class="btn btn-default pull-right"
				href="views/benefit/benefitInsert.jsp?categoryNo=2">할인행사 글쓰기</a>
		</c:if>
		<c:if test="${sessionScope.member.memberId=='admin' }">
			<a class="btn btn-default pull-right"
				href="views/benefit/benefitInsert.jsp?categoryNo=3">서비스 글쓰기</a>
		</c:if>

		<nav aria-label="pageNavi" class="text-center">
			<ul class="pagination pagination-sm">
				<li class="page-item"><a class="page-link"
					href="pageData.naviData" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li>${pageData.pageNavi }</li>
				<li class="page-item"><a class="page-link"
					href="pageData.naviData" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		<center>
			<form action="/searchTitle" method="get">
				<input type="hidden" value="1" name="categoryNo"> <input
					type="text" name="search"> <input type="submit" value="검색"
					class="btn btn-success btn-sm">
			</form>
		</center>
	</div>
	<!-- <nav aria-label="Page navigation example" class="text-center">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="pageData.naviData"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav> -->
	</div>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	<script src="js/jquery-3.1.1.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>
