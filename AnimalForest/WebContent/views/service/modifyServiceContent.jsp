<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="serviceBoard.model.vo.*"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>게시글 수정하기</title>
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
.form-control {
	align:center;
}
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
		<br><h2 class="text-center"><b>봉사공고 수정</b></h2><br>
			<form action="/modifyContentCommit" method="post">
				<div class="col-lg-2"></div>
				<div class="panel panel-default col-lg-8 text-center">
					<div class="panel-heading">
						<h3>
							<b><input type="text" value="${serviceContent.title }"
								name="title" class="form-control"></b>
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col">항목</th>
									<th scope="col" colspan="3">상세내용 <input type="hidden"
										name="board_No" value="${serviceContent.board_No}"></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${loginId eq 'admin'}">
									<tr>
										<th scope="row" class="article">공개여부</th>
										<td>
											<input type="text" value="${serviceContent.flag }"
											name="flag" style="width: 40%; float:left;" class="form-control">
											<br><b>0 : 비공개 / 1 : 공개</b>
										</td>
										
									</tr>
								</c:if>
								<tr>
									
									<th scope="row" class="article">글쓴이</th>
									<td colspan="3"><input type="text"
										value="${serviceBoardAndMember.memberName }"
										style="width: 60%;" class="form-control" readonly></td>
									<td>
											<input type="hidden" value="${serviceContent.flag }" name="flag">
									</td>	
								</tr>
								<tr>
									<th scope="row" class="article">주소</th>
									<td colspan="3"><input type="text"
										value="${serviceBoardAndMember.memberAddress }"
										style="width: 60%;" class="form-control" readonly></td>
								</tr>
								<tr>
									<th scope="row" class="article">연락처</th>
									<td colspan="3"><input type="text"
										value="${serviceBoardAndMember.memberPhone }"
										style="width: 60%;" class="form-control" readonly></td>
								</tr>
								<tr>
									<th scope="row" class="article">신청기한</th>
									<td colspan="3"><input type="date" name="deadLine"
										required></td>
								</tr>
								<tr>
									<th scope="row" class="article" rowspan="2">봉사일정</th>
									<td colspan="3">시작일 : <input type="date" name="service_Fr"
										required>
									</td>
								</tr>
								<tr>
									<td colspan="3">종료일 : <input type="date" name="service_To"
										required>
									</td>
								</tr>

								<tr>
									<th scope="row" class="article">모집인원</th>
									<td colspan="3"><input type="text"
										value="${serviceContent.volunteer }" name="volunteer"
										style="width: 10%;" class="form-control"></td>
								</tr>
								<tr>
									<th scope="row" class="article">자격요건</th>
									<td colspan="3"><input type="text" name="qualfication"
										value="${serviceContent.qualfication }" name="qualfication"
										style="width: 10%;" class="form-control"></td>
								</tr>
								<tr>
									<th scope="row" class="article">봉사내용</th>
									<td colspan="3"><textarea name="content" cols="50"
											rows="10" style="resize: none;">${serviceContent.content}</textarea></td>
								</tr>
								<tr>
									<th scope="row" class="article">파일</th>
									<td colspan="3"><input type="file" name="files"
										value="${serviceContent.fileName }" disabled></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="panel-footer text-center">
						<input type="submit" class="btn btn-primary btn-lg" value="수정하기">
						<input type="reset" class="btn btn-primary btn-lg" value="취소하기">
						<a href="/serviceBoard"><input type="button"
							class="btn btn-primary btn-lg" value="목록보기"></a>
					</div>
				</div>
				<div class="col-lg-2"></div>
			</form>
		</div>
	</div>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
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