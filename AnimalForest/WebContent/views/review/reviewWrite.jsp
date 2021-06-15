<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  </style>
</head>
<body>

<jsp:include page="/views/common/header.jsp"></jsp:include>


<form action="/reviewWrite" enctype="multipart/form-data" method="post">
<div class="container">    
  <div class="row">
    <div class="">
      <div class="panel panel-default">
      
        <div class="panel-heading">제목:<input id="title" name="title" type="text" size="100"></div>
        <div class="panel-body">
        
        
        	첨부파일<input type="file" name="file" id="imgView"
								accept=".jpg,.jpeg,.png,.gif">
								<img id="viewer" width="150px" height="100px" style="display:none">
							<br>
							<script>
								function readURL(input) {
									if (input.files && input.files[0]) {
										var reader = new FileReader();

										reader.onload = function(e) {
											$('#imgView').attr('src',e.target.result);
											$('#imgView').css("display","");
										}

										reader.readAsDataURL(input.files[0]); 
									}
									else{
										$('#imgView').css("display","none");
									}
								}

								$("#imgView").change(function() {
									readURL(this);
								});
							</script>
        	
 
        	
        	입양 시점 : <input type="date" id="adoptionTime" name="adoptionTime"><br>
   
        	<br>
        	내용:<br><textarea id="content" name="content" style="width:100%; height:600px; resize:none"></textarea><br>
        	<a href="/reviewWrite"><input type="submit" value="작성"></a>
        	<input type="button" onclick="back()" value="취소">
  				<script>
  					function back(){
  						window.history.back();
  					}
  				</script>      	
  		</div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
</div>
</div><br>
</form>

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