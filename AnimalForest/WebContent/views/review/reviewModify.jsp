<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="review.model.vo.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
  <title>동물의숲</title>
  <meta charset="utf-8">
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
  </style>
</head>
<body>
	
   <jsp:include page="/views/common/header.jsp"></jsp:include>

<div class="container">    
  <div class="row">
    <div class="">
      <div class="panel panel-default">
      <form action="/reviewModifyCommit">
        <div class="panel-heading">제목:<input name="title" value="${review.title}" type="text" size="100"></div>
        <div class="panel-body">
        
        	첨부파일<input type="file" name="file"><br>
        	입양 시점: <input type=date name="adoptionTime" value="${review.adoptionTime}"><br>
        	<input type="hidden" name="board_No" value="${review.board_No}"><br>
        	내용:<br><textarea name="content"  style="width:100%; height:600px; resize:none">
        	<img src="/img/${review2.reviewFiles[0].fileName }" style="width: 100%"><br>
					${review2.reviewFiles[0].fileName }
        	${review.content}</textarea><br>
        	
        	<input type="submit" value="작성">
        	<input type="button" onclick="back()" value="취소">
  				<script>
  					function back(){
  						window.history.back();
  					}
  				</script>      	
  </div>
  
        <div class="panel-footer">
        
        </div>
      		</form>
      </div>
    </div>
</div>
</div><br>



<footer class="container-fluid text-center">
<jsp:include page="/views/common/footer.jsp"></jsp:include>
</footer>



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