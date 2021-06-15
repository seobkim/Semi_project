<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="member.model.vo.Member"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>insertService</title>
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

footer {
   background-color: #f2f2f2;
   padding: 25px;
}


/* Add a gray background color and some padding to the footer */

.panel-body {
	width: 100%;
	height: 100%;
}

.reverse {
	background: #4CAF50;
	color: white;
}

tr {
	vertical-align: middle;
}

td {
	font-size: 1.1em;
	vertical-align: middle;
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
			<div class="col-lg-3"></div>
			<div class="text-center col-lg-6">
				<br>
				<br>
				<h2>
					<b>봉사공고 작성</b>
				</h2>
				<h2>───</h2>
				<div>
					<h4>
						<br> <b>유기동물과 더불어 사는 우리들의 숲</b>을 만들기 위해 노력중인 누구개 어디냥입니다.<br>
						봉사게시판을 통해 일손이 부족한 동물 보호소는 자원봉사를 지원받을 수 있습니다.<br> 공고 제목, 봉사 상세
						내용, 모집기한, 봉사날짜(시작/종료), 모집인원, 자격요건을 상세히 기입하여 주시기 바랍니다.<br>
					</h4>
				</div>
				<div>
					<form action="/insertContent" method="post" name="insertBoard"
						enctype="multipart/form-data">
						<div class="panel-body">
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col"
											class="article text-center col-xs-4 col-sm-4 col-md-3">제목</th>
										<th scope="col" colspan="2"><input type="text"
											size="50px;" placeholder="제목을 입력하세요" name="title"
											class="form-control" required></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row" class="article text-center"
											style="width: 20%;">아이디</th>
										<td colspan="2"><input type="text"
											placeholder="아이디를 입력해주세요" size="40px;"
											value="${member.memberName}" class="form-control" readonly></td>

									</tr>
									<tr>
										<th scope="row" class="article text-center">주소</th>
										<td colspan="2"><input type="text"
											placeholder="주소를 입력해주세요" size="40px;" name="adress"
											value="${member.memberAddress}" class="form-control" readonly></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center">연락처</th>
										<td colspan="3"><input type="text"
											placeholder="회원가입시 입력된 번호" size="40px;"
											value="${member.memberPhone }" class="form-control" readonly></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center">신청기한</th>
										<td colspan="3"><input type="date" name="deadLine"
											required></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center" rowspan="2">봉사날짜</th>
										<td colspan="3">시작일 : <input type="date"
											name="service_Fr" required></td>
									</tr>
									<tr>
										<td colspan="3">종료일 : <input type="date"
											name="service_To" required></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center">모집인원</th>
										<td colspan="2"><input type="text" size="1px"
											name="volunteer" class="form-control"
											placeholder="인원수를 입력하세요" required></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center">자격요건</th>
										<td colspan="2"><input type="text" size="1px;"
											name="qualfication" class="form-control"
											placeholder="나이 제한을 입력하세요" required></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center">봉사내용</th>
										<td colspan="2" style="text-align: left;"><textarea
												cols="50" rows="7" placeholder="공고의 상세 내용을 입력해주세요"
												name="content" style="resize: none;" required></textarea></td>
									</tr>
									<tr>
										<th scope="row" class="article text-center">첨부파일</th>
										<td colspan="2"><input type="file" name="files" required></td>
									</tr>
								</tbody>
							</table>
						</div>

						<div>
							<input type="submit" class="btn btn-success btn-lg" value="작성하기">
							<input type="reset" class="btn btn-primary btn-lg" value="취소하기">
							<input type="button" class="btn btn-primary btn-lg" value="목록보기"
								id="list"> <br> <br>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-3"></div>
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
		$("#list").click(function() {
			location.replace("/serviceBoard");
		})
	</script>
</body>
</html>