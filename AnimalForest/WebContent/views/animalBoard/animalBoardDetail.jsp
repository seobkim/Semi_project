<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="board.model.vo.*, java.util.*"%>

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
     a:link { color: white; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: white; text-decoration: none;}
 
</style>
</head>

<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div>
		<div>
			<div class="panel panel-default ">
				<div class="panel-heading">
					<h3>${animalBoard.title }</h3>
						${member.memberName}
				</div>
				<div class="panel-body">
					<img src="img/${animalBoard.fileName }" width="400px" height="400px"><br> <br>

					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">항목</th>
								<th scope="col" colspan="3">상세내용</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row" class="article">글쓴이</th>
								<td colspan="3">${member.memberName}</td>
							</tr>
							<tr>
								<th scope="row" class="article">주소</th>
								<td colspan="3">${member.memberAddress}</td>
							</tr>
							<tr>
								<th scope="row" class="article">연락처</th>
								<td colspan="3">${member.memberPhone}</td>
							</tr>

							<tr>
								<th scope="row" class="article">발견 장소</th>
								<td colspan="3">${animalBoard.findPlace }</td>
							</tr>

							<tr>
								<th scope="row" class="article">생일(발견날짜)</th>
								<td colspan="3">${animalBoard.birthDate }</td>
							</tr>

							<tr>
								<th scope="row" class="article">성별</th>
								<td colspan="3">${animalBoard.gender }</td>
							</tr>

							<tr>
								<th scope="row" class="article">색</th>
								<td colspan="3">${animalBoard.color }</td>
							</tr>

							<tr>
								<th scope="row" class="article">중성화 여부</th>
								<td colspan="3">${animalBoard.neutralizationyn }</td>
							</tr>

							<tr>
								<th scope="row" class="article">무게</th>
								<td colspan="3">${animalBoard.weight }</td>
							</tr>

							<tr>
								<th scope="row" class="article">특징</th>
								<td colspan="3">${animalBoard.feature }</td>
							</tr>
							<tr>
								<th scope="row" class="article">유의사항</th>
								<td colspan="3">${animalBoard.content }</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-success btn-lg" onclick ="WriteAdoption()">입양신청</button>
					<button type="button" class="btn btn-primary btn-lg" onclick ="back()">취소하기(목록으로돌아가기)</button>
					<button type="button" class="btn btn-primary btn-lg" onclick ="modify()">게시글수정</button>
					<button type="button" class="btn btn-primary btn-lg" onclick ="del()">게시글삭제</button>
					<button type="button" class="btn btn-primary btn-lg" onclick ="ShowMember()">신청자 조회</button>
					<br>
					<br>
					<br>
						<script>
							function del(){
								location.href="/deleteAnimalBoard?boardNo="+${animalBoard.boardNo }+"&memberNum="+${animalBoard.memberNum};
							}
							function modify(){
								location.href="/modifyAnimalBoard?boardNo="+${animalBoard.boardNo };
							}
							function WriteAdoption(){
								location.href="/AnimalBoardDetailToForm?boardNo="+${animalBoard.boardNo}+"&memberNum="+${animalBoard.memberNum};
							}
							function ShowMember(){
								location.href="/AnimalBoardAdoptionList?boardNo="+${animalBoard.boardNo }+"&memberNum="+${animalBoard.memberNum};
							}
							function back() {
								window.history.back();
							}
							
						</script>
					
					<hr>
					<div class="col-md-12 col-lg-12 text-center">

					<form action="/AnimalBoardCommentWriteServlet" method="post">
						 <input type="text" name="comment" placeholder="깨끗한 인터넷 문화를 위해 바른말을 사용해주세요." size=50>
								  <input type="hidden" name="boardNo" value="${animalBoard.boardNo}" /> 
  					<input type="submit" value="댓글달기" class="btn btn-success">
					</form>
					</div>
					
					<br>
					<div class="col-lg-3"></div>
					<div class="col-md-12 col-lg-6"></div>	

					 <table class="table table-striped text-center">
      				<tr class="text-center">
      					<td>작성자</td>
      					<td>댓글</td>
      					<td>날짜</td>
      				</tr>

      
      <c:forEach items="${animalBoard.comments }" var="comment">
      <tr>
            <td>${comment.memberName }</td> 
            <td>${comment.content }</td> 
            <td>${comment.cdt }</td> 
            <td>
               <a href="#" onclick="showModifyComment(this,
               '${comment.memberName }',
               '${comment.content }',
               '${comment.cdt }');"><input type="button" value="수정" class="btn btn-success"></a>
               <a href="/deleteAnimalBoardComment?commentNo=${comment.commentNo }&boardNo=${comment.boardNo }">
               <input type="button" value="삭제" class="btn btn-success"></a>
            </td>
      </tr>
						<tr style="display: none;">
							<td><input type="text" size="40" id="modifyMent"
								value="${comment.content}"></td>
							<td><a href="javascript:void(0)"
								onclick="modifyComment(this,'${comment.commentNo}');"><button
										class="btn btn-success">수정완료</button></a> <a href='#'
								onclick='modifyCancel(this)'><button class="btn btn-default">취소</button></a></td>
						</tr>
               
      </c:forEach>
      </table>
     
      <form action="/modifyAnimalBoardComment" id="modifyForm" method="post">
   	  <input type="hidden"  id="modComment" name="modComment">
      <input type="hidden" id="modBoardNo" name="modBoardNo" value="${animalBoard.boardNo }"/>
      <input type="hidden" id="modCommentNo" name="modCommentNo">
      </form>
					<input type="hidden" name="boardNo" value="${animalBoard.boardNo}" />
				</div>
			</div>
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
		function showModifyComment(obj, comment, memberName, cdt) {
	         console.log(obj);
	         $(obj).parents("tr").next().show();
	         $(obj).parents("tr").hide();
	      }
	      function modifyCancel(obj) {
	         $(obj).parents("tr").prev().show();
	         $(obj).parents("tr").hide();
	      }
	      function  modifyComment(obj, commentNo) {
	         var comment = $(obj).parent().prev().find("input").val();
	         $("#modCommentNo").val(commentNo);
	         $("#modComment").val(comment);
	         $("#modBoardNo").val($("#modBoardNo").val());
	         $("#modifyForm").submit();
	      }
		
	</script>
</body>
</html>