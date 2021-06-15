
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>동물의숲</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
#page>li {
	float: left;
}
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
</style>
</head>
<body>

	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<br><br>
	<div class="container">
		<div class="row">
			<c:forEach items="${pageData.pageList}" var="board" varStatus="i">

				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">${board.board_No}.${board.title}</div>
						<div class="panel-body">
							<a href="/donationSelect?board_No=${board.board_No}&type=0"> <img
								src="img/${board.fileName}" class="img-responsive"
								style="width: 100%; height: 200px;" alt="Image"></a>
						</div>
						<div class="panel-footer">종료날짜:${board.endDate}</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<center>
		<a href="donationWritePage"><button type="button"
				class="btn btn-success">글쓰기</button></a><br>
				
		<br> 
		<form action="donationSelectName">
		<input type="text" name ="searchval"placeholder="검색어를 입력하세요" size="100">
		<input type="submit" value ="검색">
		</form>
		<br>
		<nav aria-label="pageNavi">
			<ul class="pagination pagination-lg">
				<li class="page-item">${pageData.pageNavi }</li>
			</ul>
		</nav>
		<br>


	</center>
	<br>
	<br>
	
	
	<footer class="container-fluid text-center">
		<jsp:include page="/views/common/footer.jsp"></jsp:include>

	</footer>

</body>
</html>
