<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>입양공고 게시판</title>
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
/* Remove the navbar's default rounded borders and increase the bottom margin */
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}

/* Remove the jumbotron's default bottom margin */
.jumbotron {
	margin-bottom: 0;
}
.container {
	padding-top: 40px;
}
/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}
.rower{
	position: relative;
	width: 300px;
}
.panel-default{
	position: relative;
}
.panel-heading{
	position: relative;
	flex-direction:row;
	align-items:center;
	height: 50px;
	display: flex;
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
.title{
 	position : absolute;
 	width : 70%;
 	margin: 0px 0px;
 }
.icon {
 	position : absolute;
	left: 75%;
	width: 20px;
	height: 20px;
	border: 0px solid #ddd;
	color: #888;
	overflow: hidden;
}
.icon:hover{
	cursor: pointer;
}
.f_cnt{
	position : absolute;
	left : 90%;
	margin: 0px 0px;
}
</style>
</head>

<body>

	<jsp:include page="/views/common/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">

			<c:forEach items="${pageData.pageList}" var="board">
				<div class="col-sm-4 rower">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class = "title">${board.title}</h4>			
							<img id="favorite${board.getBoardNo()}" 
								 src=
								 <c:choose>
								 	<c:when test="${board.isfFlag() eq true}">"/img/like.png"</c:when>
								 	<c:otherwise>"/img/unlike.png"</c:otherwise>
								 </c:choose>
								 onclick="chg(${board.getBoardNo()})"
								 class = "icon">
							<h4 class = "f_cnt" 
								id = "f_cnt${board.getBoardNo()}">${board.getfCnt()}
							</h4>
						</div>
						<div class="panel-body" style = "text-align: center;">
							<img 	src="/img/${board.fileName}" 
									style="width: 200px; height: 200px;">
						</div>
						<div class="panel-footer">
							<p>
								 글쓴이 : ${board.memberName} <br>
								 주소 : ${board.memberAddress }<br>
								 연락처: ${board.memberPhone } <br>
						    </p>
							<button type="button" class="btn btn-success" >
							<a href="/animalBoardDetail?boardNo=${board.boardNo}">상세보기</a>
							
						</button>

							<input type="hidden" name="boardNo" value="${board.boardNo}" />
							<input type="hidden" name="fileName" value="${board.fileName }" />
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
	<br>

	<br>
	<br>

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
	</div>

	<br>
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
		
		function chg(num) {
			
			var date = new Date();
			var name = "#favorite"+num;
			var name2 = "#f_cnt"+num;
			var src = $(name).attr('src');
			var url = "";
			console.log(src);
			if(src == "/img/like.png"){
				url = "/deleteFavorite?boardNo="+num;
			}else if(src == "/img/unlike.png"){
				url = "/insertFavorite?boardNo="+num;
			}
			console.log(url);
			$.ajax({
				url : url,
				type : "get",
				success : function(data) {
					$(name).removeAttr("src").attr("src",data.src);
					$(name2).text(data.cnt);
				},
				error : function() {
					console.log("실패");
				}
			});
		}
	</script>
</body>
</html>

