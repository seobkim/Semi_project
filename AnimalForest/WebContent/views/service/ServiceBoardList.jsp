<%@page import="serviceBoard.model.vo.PageData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="serviceBoard.model.vo.ServiceBoard"
	import="member.model.vo.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<%
	PageData pd = (PageData) request.getAttribute("pageData");
	Member member = (Member) session.getAttribute("member");
%>

<html lang="en">
<head>
<title>ServiceBoard</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default rounded borders and increase the bottom margin */
.container {
	padding-top: 40px;
}

/* Remove the jumbotron's default bottom margin */

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
	color: white;
}

img {
	display: block;
	margin: 0px auto;
}

/* .panel, .panel:befor, .panel:after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all .3s;
	transition: all .10s;
}

.overEffact {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all .3s;
	transition: all .15s;
	border: 2px solid #4CAF50;
	box-shadow: 6px 6px lightgray;
} */
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<c:forEach items="${pageData.pageList}" var="bOne" varStatus="i">
				<div class="col-sm-4">
					<div class="panel panel-default" id="listBox">
						<div class="panel-heading">
							<h4><b>${bOne.title }</b></h4>
						</div>
						<div class="panel-body">
							<img src="/img/${bOne.fileName }"
								style="width: 300px; height: 300px;">
						</div>
						<div class="panel-footer">
							<p>
								글번호 : ${bOne.board_No }<br> 일시 : ${bOne.service_Fr} -
								${bOne.service_To} <br> 모집인원 : ${bOne.volunteer}명<br>
								<%--  모집날짜 : ${bOne.deadLine }<br> --%>
								신청자격 :
								<c:if test="${bOne.qualfication eq '0'}">제한없음<br>
								</c:if>
								<c:if test="${bOne.qualfication ne '0'}">${bOne.qualfication}살 이상<br>
								</c:if>
							</p>

							<a href="/selectOneContent?board_No=${bOne.board_No }&type=0"><button
									type="button" class="btn btn-success" id="form-btn">신청하기</button> <input
								type="hidden" name="boardNo" value="${bOne.board_No}" /> </a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="text-center">
		<nav aria-label="pageNavi">
			<ul class="pagination pagination-lg">
				<li class="page-item">${pageData.pageNavi }</li>
			</ul>
		</nav>
		<c:if
			test="${member.getMemberType().toString() eq '2' || member.getMemberType().toString() eq '0'}">
			<form action="/insertContentForm">
				<input type="submit" class="btn btn-info btn-lg" value="글쓰기">
			</form>
		</c:if>
		<br> <br>
		<form action="/searchContent" method="get">
			<input type="text" name="search"> <input type="submit"
				value="검색" class="btn btn-success">
		</form>
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
		/* $("#listBox").hover(function() {
			$("#listBox").addClass("overEffact");
		}, function() {
			$("#listBox").removeClass("overEffact");
		}); */
		
		/* $("#form-btn").hover(function() {
			console.log("TEST");
			$("#listBox").addClass("overEffact");
		}, function() {
			$("#listBox").removeClass("overEffact");
		}); */
		

		$(".btn btn-success").click(function() {
			document.id.action = "/view/service/selectOne.jsp";
		})
	</script>
</body>
</html>