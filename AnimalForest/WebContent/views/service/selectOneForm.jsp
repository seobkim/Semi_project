<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>SelectOneForm</title>
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
	color: white;
}

th {
	font-size: 1.1em;
	text-align: center;
}

.article {
	color: limegreen;
}

.text-left {
	line-height: 1.4em;
}

h4 {
	line-height: 1.4em;
}
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<br>
			<br>
			<div class="col-lg-3"></div>
			<div class="text-center col-lg-6">
				<h2>
					<b>봉사신청서</b>
				</h2>
				<h2>───</h2>
				<div>
					<div class="panel-body">
						<div class="col-lg-2"></div>
						<div class="col-lg-6"></div>
						<div class="col-lg-3"></div>
						<br>
						<table class="table table-hover text-center">
							<thead>
								<tr>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row" class="article">이름</th>
									<td colspan="2">${member.memberName}</td>
								</tr>
								<tr>
									<th scope="row" class="article">직업</th>
									<td colspan="2">${member.job}</td>
								</tr>
								<tr>
									<th scope="row" class="article">연락처</th>
									<td colspan="2">${member.memberPhone }</td>
								</tr>
								<tr>
									<th scope="row" class="article">이메일</th>
									<td colspan="2">${member.email }</td>
								</tr>
								<tr>
									<th scope="row" class="article">주소</th>
									<td colspan="2">${member.memberAddress }
										${member.detail_Address }</td>
								</tr>
								<tr>
									<th scope="row" class="article">각오</th>
									<td colspan="2">${member.content }</td>
								</tr>
							</tbody>
						</table>
						<form action="/deleteServiceForm" method="get">
							<input type="hidden" value="${member.board_No }" name="board_No">
							<input type="hidden" value="${member.memberNum}"
								name="member_Num"> <input type="submit" value="삭제하기"
								class="btn btn-primary btn-lg" onclick="return question()">
							<a href="/serviceBoard">
								<button type="button" class="btn btn-primary btn-lg">목록보기</button>
							</a>
						</form>
					</div>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	<br>
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

		function question() {
			var noticeNo = '${content.board_No}'; //변수에다가 가져온 값을 넣는 방법 
			var result = window.confirm("게시글을 삭제 하시겠습니까?");
			if (result) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>