<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>혜택 수정</title>
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
</style>
<!-- <script language="javascript">
	function check() {
		for (var i = 0; i < document.input.elements.length; i++) {
			if (document.input.elements[i].value == "") {
				alert("모든 값을 입력 하셔야 합니다. ");
				return false;
			}
		}
		document.input.submit();
	}
</script> -->
</head>
<body>

	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<!-- <tr>
				<td align="center">게시판</td>
				<td><select id="sel" name="categoryNo"
					onchanege="changeBoard();">
						<option value="1">이벤트</option>
						<option value="2">할인행사</option>
						<option value="3">서비스</option>
				</select> <script>
					function changeBoard() {
						var num = document.getElementById("num");
						var sel = $("option").filter("option:selected");
						/* 	for (var index = 0; index > sel.length; index++) {
								sel = document.getElementById("sel");
							} */
					}
				</script></td>
			</tr> -->
	<div class="col-lg-3"></div>
	<div class="text-center col-lg-6">
		<form name="input" method="post" action="/benefitModifyCommit">
			<input type="hidden" name="categoryNo" value="${benefit.categoryNo }">
			<input type="hidden" name="boardNo" value="${benefit.boardNo }">
			<form name="textarea" method="post"
				action="/benefitModifyCommit?boardNo=${benefit.boardNo }&categoryNo=${benefit.categoryNo }">
				<h2>
					<b>수정</b>
				</h2>
				<h2>───</h2>
				<div>
					<h4>
						<br> <b>유기동물과 더불어 사는 우리들의 숲</b>을 만들기 위해 노력중인 누구개 어디냥입니다.<br>
						방문자는 이벤트 / 할인행사 / 서비스를 통해 다양하고 유익한 정보를 제공받을 수 있습니다.<br> 내용,
						신청방법, 종료날짜, 주최 등을 상세히 기입해주시길 바랍니다.<br>
					</h4>
				</div>
				<div>
					<div class="panel-body" style="text-align: left;">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col" class="article">게시판</th>
									<td><select id="sel" name="categoryNo"
										onchanege="changeBoard();">
											<option value="1">이벤트</option>
											<option value="2">할인행사</option>
											<option value="3">서비스</option>
									</select> <script>
										function changeBoard() {
											var num = document
													.getElementById("num");
											var sel = $("option").filter(
													"option:selected");
											/* 	for (var index = 0; index > sel.length; index++) {
													sel = document.getElementById("sel");
												} */
										}
									</script></td>
								</tr>
								<tr>
									<th scope="col" class="article">제목</th>
									<th scope="col" colspan="2"><input type="text"
										size="80px;" placeholder="제목을 입력하세요" name="title"
										value="${benefit.title }"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row" class="article">작성자</th>
									<td colspan="2">관리자</td>

								</tr>
								<tr>
									<th scope="col" class="article">종료날짜</th>
									<td><input type='date' name='endDate'
										value="${benefit.endDate }" /> <script>
											document.getElementById('endDate').value = new Date()
													.toISOString().substring(0,
															10);
										</script></td>
								</tr>
								<th scope="row" class="article">내용</th>
								<td colspan="2"><textarea cols="100" rows="30"
										placeholder="상세 내용을 입력해주세요" name="contents">
                            </textarea></td>
								<tr>
									<td>첨부파일</td>
									<td><input type="file" class="form-control" name="file"
										accept=".jpg,.jpeg,.png,.gif"></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="수정완료"> <input type="reset" value="다시 입력"></td>
								</tr>
							</tbody>
						</table>
						<%-- <div class="panel-footer text-center">
						<a href="/benefitList?categoryNo=${benefit.categoryNo }">
							<button type="button" class="btn btn-primary btn-lg">목록보기</button>
						</a> <a
							href="/benefitDelete?boardNo=${benefit.boardNo }&categoryNo=${benefit.categoryNo}"
							onclick="return question()">
							<button type="button" class="btn btn-primary btn-lg">삭제하기</button>
						</a> <a href="/benefitModifyCommit?boardNo=${benefit.boardNo }">
							<button type="button" class="btn btn-primary btn-lg">수정완료
							</button>
						</a>
					</div> --%>
					</div>
				</div>
				</form>
				</form>
	</div>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
</body>
</html>