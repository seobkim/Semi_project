<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
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
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}
.jumbotron {
	margin-bottom: 0;
}
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
	width: 250px;
}
.form-radio {
	size:
}
#concon {
	width: 100px;
}
</style>
</head>

<body>
<script>
function method1() {
	var animal = $("#grp1").select().val();

	if (animal == 2) {

		$("#cat").css("display", "none");
		$("#dog").css("display", "");
		/* 	
			document.getElementById("cat").style.display="none";
			document.getElementById("dog").style.display=""; 
		 */
	} else if (animal == 1) {
		$("#dog").css("display", "none");
		$("#cat").css("display", "");

	} else if (animal == 3) {
		$("#dog").css("display", "none");
		$("#cat").css("display", "none");
	}
}
</script>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-5">
				<h2 class="text-center">입양 글 등록</h2>
				<form action="/modifyAnimalBoardCommit" method="post"
					name="productWrite">
					<table class="table table-striped">

						<tr>
							<td>공고 제목</td>
							<td><input type="text" class="form-control" name="title"
								placeholder="공고제목을 입력해주세요" value="${animalboard.title }"></td>
							<td><input type="hidden" class="form-control" name="boardNo" value="${animalboard.boardNo }"></td>
						</tr>

						<tr>
							<td>분류</td>
							<td>대분류:<select id="grp1" name="grp1" onchange="method1()">
								<c:forEach items="${list}" var="i">
									<option value="${i.grp1No}">${i.grp1Name}</option>
        						</c:forEach>
        						
        						   </select> 
        						   
        					소분류:<select id="dog" name="grp1-1" style="display: none">
								<c:forEach items="${list2}" var="dog">
									<c:if test="${dog.grp1No eq 2}">
										<option value="${dog.grp2No}">${dog.grp2name}</option>
									</c:if>
								</c:forEach>
								   </select> 	
								   
								  <select id="cat" name="grp2-1" style="display: none">
								<c:forEach items="${list2}" var="cat">
									<c:if test="${cat.grp1No eq 1}">
										<option value="${cat.grp2No}">${cat.grp2name}</option>
									</c:if>
								</c:forEach>
							</select> <br></td>
						</tr>

						<tr>
							<td>습득장소</td>
							<td><input type="text" class="form-control" name="findplace" value="${animalboard.findPlace }" ></td>
						</tr>

						<tr>
							<td>색깔</td>
							<td><input type="text" class="form-control" name="color" value="${animalboard.color }"></td>
						</tr>

						<tr>
							<td>중성화 여부</td>
							<td>
								O<input type="radio" class="form-radio" name="neutralizationyn" value="O"><br> 
								X<input type="radio" class="form-radio" name="neutralizationyn" value="X">
							</td>
						</tr>

						<tr>
							<td>무게</td>
							<td><input type="text" class="form-control" name="weight" value="${animalboard.weight }"></td>
						</tr>

						<tr>
							<td>출생</td>
							<td><input type="date" class="form-control" name="birthdate" value="${animalboard.birthDate }"></td>
						</tr>


						<tr>
							<td>성별</td>
							<td>
							미확인<input type="radio" class="form-radio" name="gender" value="X"> 
							암컷<input type="radio" class="form-radio" name="gender" value="F"> 
							수컷<input type="radio" class="form-radio" name="gender" value="M">
							</td>
						</tr>

						<tr>
							<td>특징</td>
							<td>
							<input type="text" class="form-control" name="feature" placeholder="특징을 입력하세요" value="${animalboard.feature }"></td>
						</tr>

						<tr>
							<td>유의사항</td>
							<td><input type="text" class="form-control" name="content" placeholder="유의사항을 입력하세요" value="${animalboard.content }"></td>
						</tr>

						<tr>
							<td>첨부파일</td>
							<td><input type="file" class="form-control" name="file"
								placeholder="직업을 입력하세요"></td>
						</tr>
						<tr>
							<td colspan="2" class="text-center"><input type="submit"
								value="작성완료" class="btn btn-success"> <input
								type="reset" value="다시작성" class="btn btn-warning">
								<button type="button" class="btn btn-primary">전체 게시글보기</button>
							</td>
						</tr>

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
	</script>
</body>
</html>