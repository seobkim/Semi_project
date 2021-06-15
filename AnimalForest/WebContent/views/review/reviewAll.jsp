<%@page import="review.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
	Review review = (Review)request.getAttribute("content");
%>  
<!DOCTYPE html>
<html lang="en">

<head>
<title>입양후기 목록</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<style type="text/css">

.container {
	padding-top: 40px;
}
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
	right: 600px;
	color: white;
}

.bottomnav div{
	display: flex;
}

a h4 { text-decoration:none 
} 


</style>
</head>

<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<c:forEach items="${review.pageList}" var="board">
				<div class="col-sm-4">
				
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>${board.board_No}.  ${board.title}</h4>
						</div>
						<a style="text-decoration:non" href="/reviewSelect?board_No=${board.board_No}&type=0">
						<div class="panel-body">  
						<img src="/img/${board.fileName} " style="width: 100% ;height:200px"><br>
					 
						</div></a>
						<div class="panel-footer">
							<p>
								<b> 유저 아이디 : ${board.member_Num}</b> <br>
								<b> 입양 시점 : ${board.adoptionTime}</b> <br> 
							</p>							
							<input type="hidden" name="board_No" value="${board.board_No}" />
						</div>
					</div>
					
				</div>
			</c:forEach>
		</div>
	</div>
	<br>
	<br>
	
	
	<nav aria-label="pageNavi">
	<div class="text-center" id="bottomnav">
	
  				
  					<div>
  					<button type="button" class="btn btn" id="write">	<a href="/views/review/reviewWrite.jsp"><b>입양 후기 올리기</b></a>
  					</button></div><br><br><br><br>
  					<div><form action="/reviewSearch" method="get">
						<input type="text" name="search">
						<input type="submit" value="검색"></form>
					</div>
					<div>
					<ul class="pagination pagination-lg">
    				<li class="page-item">${review.pageNavi }</li>	
  					</ul></div>
  				
  					
  					<br><br><br><br>
	
			</div>
			</nav>

	<br>
<footer class="container-fluid text-center">
<jsp:include page="/views/common/footer.jsp"></jsp:include>
</footer>
	<script>
		$(document).ready(function() {
			$("li").hover(function() {
				$(this).addClass("reverse");
			}, function() {
				$(this).removeClass("reverse");
			});
		});
		
	</script>
</body>
</html>

