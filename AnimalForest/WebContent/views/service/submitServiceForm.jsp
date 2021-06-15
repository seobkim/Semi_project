<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

th {
	font-size: 1.1em;
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

#footerDiv {
	padding-left: 0px;
	padding-right: 0px;
}
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="text-center col-lg-6">
				<h2>
					<b>봉사신청서</b>
				</h2>
				<h2>───</h2>
				<div></div>
				<div>
					<form action="/submitFormCommit" method="post">
						<div class="panel-body">
							<div class="col-lg-2"></div>
							<div class="col-lg-6"></div>
							<div class="col-lg-3"></div>
							<br>
							<table class="table table-hover text-center">
								<thead>
									<tr>
										<th scope="col" class="article"><h4>
												<b>봉사공고</b>
											</h4></th>
										<th scope="col" colspan="2">
											<h4 class="text-center">
												<b> ${serviceContent.title } </b>
											</h4>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row" class="article">직업</th>
										<td colspan="2"><input type="text"
											placeholder="직업을 입력해주세요" size="40px;" name="job"
											class="form-control"></td>
									</tr>
									<tr>
										<th scope="row" class="article">연락처</th>
										<td colspan="2"><input type="text"
											placeholder="'-'를 제외한 연락처를 입력해주세요" size="40px;" name="phone"
											class="form-control"></td>
									</tr>
									<tr>
										<th scope="row" class="article">이메일</th>
										<td colspan="3"><input type="text"
											placeholder="이메일을 입력해주세요" size="40px;" name="email"
											class="form-control"></td>
									</tr>
									<!-- <tr>
								<th scope="row" class="article">주소</th>
								<td colspan="2">
									<input type="text" placeholder="주소를 입력해주세요"
										size="40px" name="address">
									<input type="text" placeholder="자세한 주소를 입력해주세요" name="detail_address">
								</td>
							</tr> -->
									<tr>
										<th scope="row" class="article" rowspan="2">주소</th>
										<td colspan="3">
											<div class="col-lg-8">
												<input type="text" id="sample6_postcode" name="address"
													placeholder="우편번호" class="form-control">
											</div>
											<div class="col-lg-4">
												<input type="button" onclick="sample6_execDaumPostcode()"
													value="우편번호 찾기" class="btn btn-success btn-sm">
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<div class="col-lg-8">
												<input type="text" id="sample6_address"
													name="detail_address" placeholder="주소" class="form-control">
											</div>
											<div class="col-lg-4">
												<input type="text" id="sample6_extraAddress" name="addr2"
													placeholder="상세주소" class="form-control">
											</div>
										</td>
									</tr>
									<script
										src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
									<script>
										function sample6_execDaumPostcode() {
											new daum.Postcode(
													{
														oncomplete : function(
																data) {
															// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

															// 각 주소의 노출 규칙에 따라 주소를 조합한다.
															// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
															var addr = ''; // 주소 변수
															var extraAddr = ''; // 참고항목 변수

															//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
															if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
																addr = data.roadAddress;
															} else { // 사용자가 지번 주소를 선택했을 경우(J)
																addr = data.jibunAddress;
															}

															// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
															if (data.userSelectedType === 'R') {
																// 법정동명이 있을 경우 추가한다. (법정리는 제외)
																// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
																if (data.bname !== ''
																		&& /[동|로|가]$/g
																				.test(data.bname)) {
																	extraAddr += data.bname;
																}
																// 건물명이 있고, 공동주택일 경우 추가한다.
																if (data.buildingName !== ''
																		&& data.apartment === 'Y') {
																	extraAddr += (extraAddr !== '' ? ', '
																			+ data.buildingName
																			: data.buildingName);
																}
																// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
																if (extraAddr !== '') {
																	extraAddr = ' ('
																			+ extraAddr
																			+ ')';
																}
																// 조합된 참고항목을 해당 필드에 넣는다.
																document
																		.getElementById("sample6_extraAddress").value = extraAddr;

															} else {
																document
																		.getElementById("sample6_extraAddress").value = '';
															}

															// 우편번호와 주소 정보를 해당 필드에 넣는다.
															document
																	.getElementById('sample6_postcode').value = data.zonecode;
															document
																	.getElementById("sample6_address").value = addr;
															// 커서를 상세주소 필드로 이동한다.
															document
																	.getElementById(
																			"sample6_extraAddress")
																	.focus();
														}
													}).open();
										}
									</script>
									<tr>
										<th scope="row" class="article">각오</th>
										<td colspan="2"><input type="text" placeholder="각오 한마디"
											size="40px" name="content" class="form-control"> <input
											type="hidden" value="${serviceContent.board_No }"
											name="board_No"></td>
									</tr>

								</tbody>
							</table>

						</div>
						<div>
							<input type="submit" value="제출하기" class="btn btn-success btn-lg">
							<a href="/serviceBoard">
								<button type="button" class="btn btn-primary btn-lg">목록보기</button>
							</a> <br>
						</div>
					</form>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	<br>
	<br>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	<!-- <footer class="container-fluid text-center col-lg-12">
		<p>Copyright(C)2020.어디개어디냥.All right reserved</p>
		<p>
			<a href="#">회사소개</a> | <a href="#">개인정보처리방침</a> | <a href="#">청소년보호정책</a>
		</p>

	</footer> -->
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