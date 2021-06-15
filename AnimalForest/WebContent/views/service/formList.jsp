<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="serviceBoard.model.vo.*"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>SelectOne</title>
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
}

.article {
	color: limegreen;
}
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<br><br>
				<h3 class="text-center"><b>봉사신청 현황</b></h3>
				<br>
				<table class="table table-sm">
					<tr>
						<th>작성자</th>
						<th>작성내용</th>
						<th>작성시간</th>
						<th>상세보기</th>
					</tr>
					<c:forEach items="${formList}" var="list">
						<tr>
							<td>${list.memberName }</td>
							<td>${list.content}</td>
							<td>${list.mdt }</td>
							<td>
								<form action="/selectOneForm">
									<input type="hidden" value="${list.memberNum }"
										name="memberNum"> <input type="hidden"
										value="${list.board_No }" name="board_No"> <input
										type="submit" class="btn btn-default" value="상세보기">
								</form>
							</td>
						</tr>
					</c:forEach>
					<tr>
					</tr>
				</table>

			</div>
			<div class="col-lg-3"></div>
			<div class="panel-footer text-center col-lg-12">
				<a href="/serviceBoard">
					<button type="button" class="btn btn-primary btn-lg">목록보기</button>
				</a>
			</div>
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

		function showModifyComment(obj, content, member_Num, mdt) {
			$(obj).parents("tr").next().show();
			$(obj).parents("tr").hide();
		}

		function modifyCancel(obj) {
			$(obj).parents("tr").prev().show();
			$(obj).parents("tr").hide();
		}

		function modifyComment(obj, comment_No) {
			var content = $(obj).parent().prev().find("input").val();
			$("#modCommentNo").val(comment_No);
			$("#modContent").val(content);
			$("#modBoardNo").val($("#modBoardNo").val());
			$("#modifyForm").submit();
		}
	</script>
</body>
</html>