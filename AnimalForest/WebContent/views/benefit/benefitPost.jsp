<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">

<head>
<title>혜택 게시물</title>
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
</style>
</head>

<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div>
		<div class="container">
			<div class="col-lg-3"></div>
			<div class="panel panel-default col-lg-6">
				<div class="panel-heading">
					<h3>
						<b>${benefit.title }</b>
					</h3>
					<img src="img/${benefit.fileName}" class="img-responsive" style="width: 100%; height:100%;"  alt="Image">
				</div>
				<div class="panel-body">
					<img src="img/3.png"><br>
					<br>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">항목</th>
								<th scope="col" colspan="3">상세내용 <input type="hidden"
									name="board_No" value="${benefit.boardNo}"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row" class="article">글쓴이</th>
								<td colspan="3">관리자</td>
							</tr>
							<tr>
								<th scope="row" class="article">종료기간</th>
								<td colspan="3">${benefit.endDate }</td>
							</tr>
							<tr>
								<th scope="row" class="article">내용</th>
								<td colspan="3">${benefit.contents }</td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="panel-footer text-center">
					<a href="/benefitList?categoryNo=${benefit.categoryNo }">
						<button type="button" class="btn btn-primary btn-lg">목록보기</button>
					</a>
					<c:if test="${sessionScope.member.memberId=='admin'}">
						<a
							href="/benefitDelete?boardNo=${benefit.boardNo }&categoryNo=${benefit.categoryNo}"
							onclick="return question()">
							<button type="button" class="btn btn-primary btn-lg">삭제하기</button>
						</a>
						<a href="/benefitModify?boardNo=${benefit.boardNo }"> 
							<button type="button" class="btn btn-primary btn-lg">수정하기
							</button>
						</a>
					</c:if>
				</div>
				<%-- <table class="table table-striped text-center">
					<tr class="text-center">
						<th colspan="2">댓글</th>
						<th>아이디</th>
						<th>날짜</th>
					</tr>
					<c:forEach items="${benefitCommentWrite.benefit }" var="comment">
						<tr>
							<td>${comment.contents }</td>
							<td>${comment.memberNo}</td>
							<td>${comment.MDT }</td>
							<td><a href="javascript:void(0)"
								onclick="showModifyComment(this,'${comment.contents}','${comment.memberNo }','${comment.MDT}')">
									<input type="button" value="수정" class="btn btn-success">
							</a> <a
								href="/deleteComment?commentNo=${comment.commentNo }&boardNo=${benefit.boardNo }">
									<input type="button" value="삭제" class="btn btn-success">
							</a></td>
						</tr>
						<tr style="display: none;">
							<td><input type="text" size="40" id="modifyMent"
								value="${comment.contents}"></td>
							<td><a href="javascript:void(0)"
								onclick="modifyComment(this,'${comment.commentNo}');"><button
										class="btn btn-success">수정완료</button></a> <a href='#'
								onclick='modifyCancel(this)'><button class="btn btn-default">취소</button></a></td>
						</tr>
					</c:forEach>
				</table>
				<form action="/modifyComment" id="modifyForm" method="post">
					<input type="hidden" id="modContent" name="modContent"> <input
						type="hidden" id="modBoardNo" name="modBoardNo"
						value="${boardNo}"> <input type="hidden"
						id="modCommentNo" name="modCommentNo">
				</form> --%>
				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>댓글</th>
								<th>아이디</th>
								<th>날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${comment }" var="comment">
								<tr>
									<td>${comment.commentNo }</td>
									<td>${comment.contents }</td>
									<td>${comment.memberId }</td>
									<td>${comment.CDT }</td>
									<c:if
										test="${sessionScope.member.memberId==comment.memberId || sessionScope.member.memberId=='admin'}">
										<td><a href="javascript:void(0)"
											onclick="showModifyComment(this,'${comment.contents }',
								'${comment.memberId }','${comment.CDT }');">수정</a>
										<td>
										<td><a
											href="/deleteComment?boardNo=${comment.boardNo }&commentNo=${comment.commentNo}">삭제</a></td>
									</c:if>
									<%-- <td><a href="/modifyComment?boardNo=${comment.boardNo }&commentNo=${comment.commentNo}">
										<input 
									</a></td> --%>
								</tr>
								<tr style="display: none;">
									<td><input type="text" size="40" id="modifyMent"
										value="${comment.contents}"></td>
									<td><a href="javascript:void(0)"
										onclick="modifyComment(this);">
											<button class="btn btn-success">수정완료</button>
									</a> <a href='#' onclick='modifyCancel(this)'>
											<button class="btn btn-default">취소</button>
									</a></td>
								</tr>
							</c:forEach>
							<%-- <form action="/modifyComment" id="modifyForm" method="post">
								<input type="hidden" id="contents" name="contents"> 
								<input type="hidden" id="boardNo" name="boardNo" value="${benefit.boardNo}">
								<input type="hidden" id="commentNo" name="commentNo">
							</form>
 --%>
							<c:if test="${sessionScope.member.memberId!=null}">
								<form action="/commentWrite" method="post">
									<input type="hidden" name="boardNo" value="${benefit.boardNo }">
									<input type="text" name="contents" style = "width:100%;'"
										placeholder="댓글을 작성해 보세요.">
									<input type="submit" value="작성">
								</form>
							</c:if>
							<form action="/modifyComment" id="modifyForm" method="post">
								<input type="hidden" id="comment" name="comment"> <input
									type="hidden" id="boardNo" value="${benefit.boardNo }"
									name="boardNo"> <input type="hidden" id="commentNo"
									name="commentNo">
							</form>


							<%-- <c:forEach items="${commentWrite.comment }" var="comment">
								<tr>
									<td>${comment.commentNo }</td>
									<td>${comment.contents }</td>
									<td>${comment.memberId }</td>
									<td>${comment.CDT }</td>
									<td><a href="javascript:void(0)"
										onclick="showModifyComment(this,'${comment.contents }',
								'${comment.memberId }','${comment.CDT }');">수정</a>
									<td><a
										href="/commentDelete?commentNo=${comment.commentNo }&benefitNo=${comment.benefitNo
								}">삭제</a></td>
								</tr>
							</c:forEach> --%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	<script>
		function question() {
			var noticeNo = '${post.boardNo}'; //변수에다가 가져온 값을 넣는 방법 
			var result = window.confirm("게시글을 삭제 하시겠습니까?");
			if (result) {
				return true;
			} else {
				return false;
			}
		}
		function showModifyComment(obj, comment, memberId, CDT) {
			console.log(obj);
			$(obj).parents("tr").next().show();
			$(obj).parents("tr").hide();
		}

		function modifiyCancel(obj) {
			$(obj).parents("tr").prev().show();
			$(obj).parents("tr").hide();
		}

		function modifyComment(obj, commentNo) {
			var comment = $(obj).parent().prev().find("input").val();
			$("#commentNo").val(commentNo);
			$("#comment").val(comment);
			$("#boardeNo").val($("#boardNo").val());
			$("#modifyForm").submit();
		}
	</script>
</body>

</html>
