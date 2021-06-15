<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="serviceBoard.model.vo.*"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	Member member = (Member) session.getAttribute("member");
%>
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
	text-align: center;
}

.article {
	color: limegreen;
}

td {
	text-align: center;
}

img {
	display: block;
	margin: 0px auto;
}
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
		<br><br>
			<div class="col-lg-2"></div>
			<div class="panel panel-default col-lg-8">
				<div class="panel-heading text-center">
					<h3>
						<b>${serviceBoard.title }</b>
					</h3>
				</div>
				<div class="panel-body">
					<img src="/img/${serviceBoard.fileName }"
						style="width: 600px; height: 600px;"> <br>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">항목</th>
								<th scope="col" colspan="3">상세내용 <input type="hidden"
									name="board_No" value="${serviceBoard.board_No}"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row" class="article">신청현황</th>
								<td colspan="3"><b> ${serviceBoard.count } /
										${serviceBoard.volunteer }</b> <c:if
										test="${serviceBoard.volunteer eq serviceBoard.count }">
										<input type="button" class="btn btn-primary btn-sm"
											value="모집마감" readonly>
									</c:if></td>
							</tr>
							<tr>
								<th scope="row" class="article">글쓴이</th>
								<td colspan="3">${member.memberName }</td>
							</tr>
							<tr>
								<th scope="row" class="article">주소</th>
								<td colspan="3">${member.memberAddress }</td>
							</tr>
							<tr>
								<th scope="row" class="article">연락처</th>
								<td colspan="3">${member.memberPhone }</td>
							</tr>
							<tr>
								<th scope="row" class="article">신청기한</th>
								<td colspan="3">${serviceBoard.deadLine }</td>
							</tr>
							<tr>
								<th scope="row" class="article">봉사일정</th>
								<td colspan="3">${serviceBoard.service_Fr}-
									${serviceBoard.service_To}</td>
							</tr>
							<tr>
								<th scope="row" class="article">모집인원</th>
								<td colspan="3">${serviceBoard.volunteer }명</td>
							</tr>
							<tr>
								<th scope="row" class="article">자격요건</th>
								<td colspan="3"><c:if
										test="${serviceBoard.qualfication eq '0'}">
                        제한없음</c:if> <c:if
										test="${serviceBoard.qualfication ne '0'}">
                        ${serviceBoard.qualfication }살 이상</c:if></td>
							</tr>
							<tr>
								<th scope="row" class="article">봉사내용</th>
								<td colspan="3">${serviceBoard.content}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel-footer text-center">
					<a href="/submitForm?board_No=${serviceBoard.board_No }"> <c:if
							test="${serviceBoard.volunteer ne serviceBoard.count }">
							<button type="button" class="btn btn-success btn-lg">봉사신청</button>
						</c:if>
					</a> <a href="javascript:callBack()">
						<button type="button" class="btn btn-primary btn-lg">목록보기</button>
					</a>
					<script>
						function callBack() {
							window.history.back();
						}
					</script>
					<c:if test="${member.getMemberType().toString() eq '2' or '0'}">
						<!-- 관리자 또는 보호소 걸러내는 if문 -->
						<c:if test="${logInId eq 'admin' || logInId eq member.memberId}">
							<!-- 로그인 아이디가 admin이거나 게시글 작성자를 걸러내는 if문 -->
							<a href="/deleteContent?board_No=${serviceBoard.board_No }"
								onclick="return question()">
								<button type="button" class="btn btn-primary btn-lg">삭제하기</button>
							</a>
							<a href="/modifyContent?board_No=${serviceBoard.board_No }">
								<button type="button" class="btn btn-primary btn-lg">수정하기
								</button>
							</a>
							<a href="/formList?board_No=${serviceBoard.board_No}">
								<button type="button" class="btn btn-primary btn-lg">신청현황</button>
							</a>
						</c:if>
					</c:if>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<div class="commentDiv">
			<hr>
			<div class="col-sm-12 col-md-12 col-lg-12 text-center">
				<form action="/insertServiceComment?board_No=${serviceBoard.board_No }"
					method="post">
					<input type="text" name="content"
						placeholder="깨끗한 인터넷 문화를 위해 바른말을 사용해주세요." size=50 required>
					<input type="submit" value="작성" class="btn btn-success"> <input
						type="hidden" name="board_No" value="${serviceBoard.board_No }">
				</form>
				<br>
			</div>
			<div class="col-lg-12 text-center">
				<table class="table table-striped text-center">
					<tr class="text-center">
						<th colspan="2">댓글</th>
						<th>아이디</th>
						<th>날짜</th>
						<th>수정 / 삭제</th>
					</tr>
					<c:forEach items="${serviceBoard.clist }" var="comment">
						<tr>
							<td colspan="2">${comment.content }</td>
							<td>${comment.member_Name}</td>
							<td>${comment.mdt }</td>
							<td><c:if test="${logInNum eq comment.member_Num || logInType.toString() eq '0'}">
									<a href="#"
										onclick="showModifyComment(this,'${comment.content}','${comment.member_Num }','${comment.mdt}')">
										<input type="button" value="수정" class="btn btn-success">
									</a>
									<a
										href="/deleteServiceComment?comment_No=${comment.comment_No }&board_No=${serviceBoard.board_No }">
										<input type="button" value="삭제" class="btn btn-success">
									</a>
								</c:if></td>
						</tr>
						<tr style="display: none;">
							<td><input type="text" size="40" id="modifyMent"
								value="${comment.content}"></td>
							<td><a href="javascript:void(0)"
								onclick="modifyComment(this,'${comment.comment_No}');"><button
										class="btn btn-success">수정완료</button></a> <a href='#'
								onclick='modifyCancel(this)'><button class="btn btn-default">취소</button></a></td>
						</tr>
					</c:forEach>
				</table>
				<form action="/modifyServiceComment" id="modifyForm" method="post">
					<input type="hidden" id="modContent" name="modContent"> <input
						type="hidden" id="modBoardNo" name="modBoardNo"
						value="${serviceBoard.board_No }"> <input type="hidden"
						id="modCommentNo" name="modCommentNo">
				</form>
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
