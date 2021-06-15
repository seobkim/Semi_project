<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>입양 설문 조회</title>
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
			<h2 class="text-center">입양 설문 조회</h2>

			<hr>

			<div class="col-md-12">

				<form action="/animalBoardSurvey" method="post">
					<table class="table table-striped">
						<tr>
							<td>1. 예전에 반려동물을 키운적이 있으신가요???</td>
							<td>
							<input type="text" class="form-control" name="exprienceYn" size="50px" value = ${adoptionSurvey.exprienceYn } readonly/> 
							</td>
						</tr>
						
						<tr>
							<td>2. 현재 동거중인 반려동물이 있으신가요??</td>
							<td><input type="text" class="form-control" name="petYn" value = ${adoptionSurvey.petYn } readonly>
							</td>
						</tr>

						<tr>
							<td>3. 가족구성원의 수를 입력해주세요 (방치 방지)</td>
							<td><input type="text" class="form-control" name="memberFamily" value = ${adoptionSurvey.memberFamily } readonly></td>
						</tr>

						<tr>
							<td>4. 거주하고 계신 주택 종류는??</td>
							<td><input type="text" class="form-control" name="homeType" value = ${adoptionSurvey.homeType } readonly>
							</td>
						</tr>

						<tr>
							<td style="vertical-align: middle;">5. 입양사유를 적어주세요</td>
							<td><textarea rows="10" cols="50" name="adoptionReason" class="form-control" readonly>${adoptionSurvey.adoptionReason }</textarea></td>
						</tr>

						<tr>
							<td style="vertical-align: middle;">6. 명절 및 휴가철 집을 비울 경우 입양한 아이를 어떻게 케어하실건지 기재부탁드립니다.</td>
							<td><textarea rows="10" cols="50" name="adoptionCare" class="form-control" readonly>${adoptionSurvey.adoptionCare }</textarea></td>
						</tr>

						<tr>
							<td>7. 입양 후 대략 어느정도의 비용이 예상되는지 기재 부탁드립니다.</td>
							<td><input type="text" class="form-control" name="expectedCost" value = ${adoptionSurvey.expectedCost } readonly></td>
						</tr>

						<td colspan="2" class="text-center">
							<button type="button" class="btn btn-primary" onclick="selectForm()">채택하기</button>
                            <button type="button" class="btn btn-primary" onclick="back()">뒤로가기</button>
                            <button type="button" class="btn btn-primary" onclick="home()">홈으로</button>
                        </td>

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
		function back() {
			window.history.back();
		}
		function home(){
			location.href="/boardList"
		}
		function selectForm(){
			location.href="/selectForm?memberNum="+${adoptionSurvey.memberNum}+"&boardNo="+${adoptionSurvey.boardNo};
		}
	</script>
</body>
</html>