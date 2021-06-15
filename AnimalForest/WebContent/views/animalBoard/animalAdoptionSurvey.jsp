<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
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
}

.article {
	color: limegreen;
}

.form-control {
	width: 400px;
}

.form-radio {
	size:
}

table {
	display: table-cell;
	vertical-align: middle;
}

#aaa {
	vertical-align: middle;
}

.form-radio {
	width: 2em;
	height: 15px;
}

</style>
</head>

<body>

<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<h2 class="text-center">입양 설문 작성</h2>
			<p>
				기본적인 조사를 위해 만들어진 설문입니다 성실히 기재 부탁드립니다.<br> 설문작성 이후 입양신청서 작성으로
				넘어갑니다.
			</p>
			<hr>

			<div class="col-md-12">

				<form action="/animalBoardSurvey" method="post">
					<table class="table table-striped">
						<tr>
							<td>1. 예전에 반려동물을 키운적이 있으신가요???</td>
							<td>예<input type="radio" class="form-radio" name="exprienceYn" size="50px" value="O" required> 
							아니요<input type="radio" class="form-radio" name="exprienceYn" value="X" required>
							<input type="hidden" name="boardNo" value = "${boardNo }"/>
							</td>
						</tr>
						
						<tr>
							<td>2. 현재 동거중인 반려동물이 있으신가요??</td>
							<td>예<input type="radio" class="form-radio" name="petYn" value = "O" required>
							아니요<input type="radio" class="form-radio" name="petYn" value = "X" required></td>
						</tr>

						<tr>
							<td>3. 가족구성원의 수를 입력해주세요 (방치 방지)</td>
							<td><input type="text" class="form-control" name="memberFamily" placeholder="구성원수를 입력해주세요" required></td>
						</tr>

						<tr>
							<td>4. 거주하고 계신 주택 종류는??</td>
							<td>빌라<input type="radio" class="form-radio" name="homeType" value="빌라" required>
								아파트<input type="radio" class="form-radio" name="homeType" value="아파트" required>
								단독주택<input type="radio" class="form-radio" name="homeType" value="단독주택" required>
								원룸<input type="radio" class="form-radio" name="homeType" value="원룸" required>
							</td>
						</tr>

						<tr>
							<td style="vertical-align: middle;">5. 입양사유를 적어주세요</td>
							<td><textarea rows="10" cols="50" name="adoptionReason" class="form-control" placeholder="내용을 입력하세요" required></textarea></td>
						</tr>

						<tr>
							<td style="vertical-align: middle;">6. 명절 및 휴가철 집을 비울 경우 입양한 아이를 어떻게 케어하실건지 기재부탁드립니다.</td>
							<td><textarea rows="10" cols="50" name="adoptionCare" class="form-control" placeholder="내용을 입력하세요" required></textarea></td>
						</tr>

						<tr>
							<td>7. 입양 후 대략 어느정도의 비용이 예상되는지 기재 부탁드립니다.</td>
							<td><input type="text" class="form-control" name="expectedCost" placeholder="비용을 입력하세요" required></td>
						</tr>

						<td colspan="2" class="text-center"><input type="submit" value="작성완료" class="btn btn-success"> 
															<input type="reset" value="취소" class="btn btn-warning">
															<button type="button" class="btn btn-primary" onclick ="boardList()">전체 게시글 보기</button></td>
					</table>
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
		
		function boardList(){
			location.href="/boardList";
		}
	</script>
</body>
</html>