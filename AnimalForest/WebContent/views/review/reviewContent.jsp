<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="review.model.vo.*"%>
 <%
	Review review = (Review)request.getAttribute("content");
%>     
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>

<div class="container">
		<div class="row">
			<div class="">
				<div class="panel panel-default">
					<div class="panel-heading">${review.title}
						</div>
						<div class="panel-heading">
						입양 시점: ${review.adoptionTime}</div>
					<div class="panel-body">  
						<img src="/img/${review.reviewFiles[0].fileName }" style="width: 60%"><br>
					${review.reviewFiles[0].fileName }<br><br><br>
					${review.content}
					</div>
					<div class="panel-footer">
						<a href="/reviewModify?board_No=${review.board_No}">
					<button type="button" class="btn btn-primary btn-lg">
						게시글수정

					</button></a><a href="/reviewDelete?board_No=${review.board_No}">
					<button type="button" class="btn btn-primary btn-lg">
						게시글삭제
					</button></a>
					<a href="javascript:back()">
					<button type="button" class="btn btn-primary btn-lg">
						뒤로가기
					</button></a>
					<br>
					<br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>

				
					<hr>
					<div class="col-md-12 col-lg-12 text-center">

					<form action="/reviewCommentWrite" method="post">
						 <input type="text" name="comment" placeholder="깨끗한 인터넷 문화를 위해 바른말을 사용해주세요." size=50>
								  <input type="hidden" name="board_No" value="${review.board_No}" /> 
  					<input type="submit" value="댓글달기" class="btn btn-success">
					</form>
					</div>
					<br>
					<div class="col-lg-3"></div>
					<div class="col-md-12 col-lg-6"></div>	

					 <table class="table table-striped text-center">
      				<tr class="text-center"><th colspan="2">댓글</th><th>아이디</th><th>날짜</th><th>수정/삭제</th></tr>
				
      			<c:forEach items="${review.comments}" var="comment">
      				<tr>
      
            	<td>${comment.content }</td> 
            	<td>${comment.memberid }</td> 
            	<td>${comment.cdt }</td> 
            	<td>
               	<a href="#" onclick="showModifyComment(this,
               	'${comment.content }',
               	'${comment.memberName }',
               	'${comment.cdt }');">
               	<input type="button" value="수정" class="btn btn-success"></a>
               	
               	
               	<a href="/reviewCommentDelete?comment_No=${comment.comment_No }&board_No=${comment.board_No }">
               	<input type="button" value="삭제" class="btn btn-success"></a>
            </td>
      		</tr>
      		<tr style="display:none;">
            <td>
               <input type="text" size="40" id="modifyMent" value="${comment.content }" />
            </td>            
            <td>
               <a href="javascript:void(0)" onclick="modifyComment(this, '${comment.comment_No }');">수정완료</a>
               <a href='javascript:void(0)' onclick='modifyCancel(this)'>취소</a>
            </td>
      		</tr>  
      </c:forEach>
      </table>
     
      <form action="/reviewCommentModify" id="modifyForm" method="post">
   	  <input type="hidden"  id="modComment" name="modComment">
      <input type="hidden" id="modBoardNo" name="modBoardNo" value="${review.board_No }"/>
      <input type="hidden" id="modCommentNo" name="modCommentNo">
      </form>
					<input type="hidden" name="board_No" value="${review.board_No}" />
				</div>
			</div>
		</div>
	</div>
	

	
	
	
	
	
	
	
<footer class="container-fluid text-center col-lg-12">
<jsp:include page="/views/common/footer.jsp"></jsp:include>
</footer>
	
	<script>
		function back() {
			window.history.back();
		}
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
	      function  modifyComment(obj, comment_No) {
	         var comment = $(obj).parent().prev().find("input").val();
	         $("#modCommentNo").val(comment_No);
	         $("#modComment").val(comment);
	         $("#modBoardNo").val($("#modBoardNo").val());
	         $("#modifyForm").submit();
	      }
		
	</script>
	
	
</body>
</html>