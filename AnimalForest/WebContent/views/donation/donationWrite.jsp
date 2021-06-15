<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>동물의숲</title>
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
</head>
<body>
	<script>
		
		window.onload=function(){
			var animal = $("#grp1").select().val();
			if (animal == 1) {
				$("#dog").css("display", "none");
				$("#cat").css("display", "");

			}
			
		}
	
	
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
<br><br>
	<form action="donationWrite" method="post"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="">
					<div class="panel panel-default">

						<div class="panel-heading">
							제목:<input name="title" type="text" size="100" required>
						</div>
						<div class="panel-body">

							첨부파일<input type="file" name="file" id="imgView"
								accept=".jpg,.jpeg,.png,.gif">
								<img id="viewer" width="150px" height="100px" style="display:none" required>
							<br>
							<script>
							
								function readURL(input) {
									if (input.files && input.files[0]) {
										var reader = new FileReader();

										reader.onload = function(e) {
											$('#viewer').attr('src',e.target.result);
											$('#viewer').css("display","");
										}

										reader.readAsDataURL(input.files[0]); 
									}
									else{
										$('#viewer').css("display","none");
									}
								}

								$("#imgView").change(function() {
									readURL(this);
								});
							
							</script>


							후원 마감 날짜 : <input type=date name="endDate" required><br> 총 후원
							모집금액:<input type=text name="fullAccount" required><br> 대분류:<select
								id="grp1" name="grp1" onchange="method1()">
								<c:forEach items="${list}" var="i">
								<c:if test="${i.grp1No eq 1}">
									<option selected="selected" value="${i.grp1No}">${i.grp1Name} </option>
    							</c:if>
    							<c:if test="${i.grp1No ne 1}">
									<option value="${i.grp1No}">${i.grp1Name} </option>
    							</c:if>
        				</c:forEach>
							</select> 소분류:<select id="dog" name="grp1-1" style="display: none">

								<c:forEach items="${list2}" var="dog">
									<c:if test="${dog.grp1No eq 2}">
										<option value="${dog.grp2No}">${dog.grp2Name}</option>
									</c:if>
								</c:forEach>

							</select> <select id="cat" name="grp2-1" style="display: none">
								<c:forEach items="${list2}" var="cat">
									<c:if test="${cat.grp1No eq 1}">
										<option value="${cat.grp2No}">${cat.grp2Name}</option>
									</c:if>
								</c:forEach>
							</select> <br> 내용:<br>
							<textarea name=content
								style="width: 100%; height: 600px; resize: none" required></textarea>
							<br> <input type="submit" value="작성"> <input
								type="button" onclick="back()" value="취소">
							<script>
								function back() {
									window.history.back();
								}
							</script>
						</div>

						<div class="panel-footer"></div>

					</div>
				</div>
			</div>
		</div>
		<br>
	</form>


	<footer class="container-fluid text-center">
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</footer>

</body>
</html>