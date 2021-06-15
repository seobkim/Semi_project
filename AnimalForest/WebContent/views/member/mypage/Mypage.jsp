<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	  <title>마이페이지</title>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	  <style>
	  	.link:hover {
    		cursor: pointer;
    	}
    	.link {
    		color: #5085BB;
    	}
	    /* Remove the navbar's default margin-bottom and rounded borders */ 
	    .navbar {
	      margin-bottom: 0;
	      border-radius: 0;
	    }
	    
	    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
	    .row.content {
	    	height: 90vh;
	    }
	    div[class^="col"]{
	    	padding-left: 0px;
	    	padding-right: 0px;
	    }
	    /* Set gray background color and 100% height */
	    .sidenav {
			/* padding-left: 0px;
	    	padding-right: 0px; */
			background-color: #f1f1f1;
			height: 100%;
	    }
	    .nav-pills{
	    	padding-top:20px; 
	    }
	  </style>
	</head>
	<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<div class="container-fluid text-center" style = "height:90vh;">    
	  <div class="row content">
	    <div class="col-sm-2 sidenav">
	    	<ul class="nav nav-pills nav-stacked">
				<li	id = "li1">
					<a href="/views/member/mypage/Mypage.jsp?type=1">내가 쓴 글</a>
				</li>
			    <li id= "li2">
			       	<a href="/views/member/mypage/Mypage.jsp?type=2">후원내역</a>
			    </li>
			    <li id= "li3">
			       	<a href="/views/member/mypage/Mypage.jsp?type=3" >관심 분양글 확인</a>
			    </li>
			    <li id= "li4">
			       	<a href="/views/member/mypage/Mypage.jsp?type=4" >회원정보수정</a>
			    </li>
			    <c:if test="${member.getMemberType().toString() eq '0' }">
			    	<li id = "li5">
			    		<a href="/views/member/mypage/Mypage.jsp?type=5" >전체회원조회 </a>
			    	</li>
			    	<li id = "li6">
			    		<a href="/views/member/mypage/Mypage.jsp?type=6" >전체보호소조회</a>
			    	</li>
			    </c:if>
			</ul>
	    </div>
	    <div class="col-sm-10 text-left">
	    	<iframe id = "iframeA" src=""  style = "width : 100%; height : 90vh">
			</iframe> 
	    </div>
	  </div>
	</div>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		window.onload = function () {
			if(${param.type} == 1){
				writeHistory();
			}else if(${param.type}==2){
				donationHistory();
			}else if(${param.type}==3){
				loadFavorite();
			}else if(${param.type}==4){
				memberDataChg();
			}else if(${param.type}==5){
				allMemberList();
			}else if(${param.type}==6){
				allCenterList();
			}
		}
		
		function changeNavi(num) {
			if(num == 1){
				$("#li1").addClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").removeClass("active");
			    $("#li4").removeClass("active");
			    $("#li5").removeClass("active");
		    	$("#li6").removeClass("active");
			}else if(num == 2){
			    $("#li1").removeClass("active");
			    $("#li2").addClass("active");
			    $("#li3").removeClass("active");
			    $("#li4").removeClass("active");
			    $("#li5").removeClass("active");
		    	$("#li6").removeClass("active");
			}else if(num ==3){
				$("#li1").removeClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").addClass("active");
			    $("#li4").removeClass("active");
			    $("#li5").removeClass("active");
		    	$("#li6").removeClass("active");
			}else if(num ==4){
				$("#li1").removeClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").removeClass("active");
			    $("#li4").addClass("active");
			    $("#li5").removeClass("active");
		    	$("#li6").removeClass("active");
			}else if(num ==5){
				$("#li1").removeClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").removeClass("active");
			    $("#li4").removeClass("active");
			    $("#li5").addClass("active");
		    	$("#li6").removeClass("active");
			}else if(num ==6){
				$("#li1").removeClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").removeClass("active");
			    $("#li4").removeClass("active");
			    $("#li5").removeClass("active");
		    	$("#li6").addClass("active");
			}
		}
		
		function writeHistory() {
			document.getElementById("iframeA").src = "/writeList";
			changeNavi(1);
		}
		function donationHistory() {
			document.getElementById("iframeA").src = "/myDonationList";
			changeNavi(2);
		}
		function loadFavorite() {
			document.getElementById("iframeA").src = "/favoriteList";
			changeNavi(3);
		}
		function memberDataChg(){
			document.getElementById("iframeA").src = "/views/member/memberModify.jsp";
			changeNavi(4);
		}
		function allMemberList() {
			document.getElementById("iframeA").src = "/memberlist";
			changeNavi(5);
		}
		function allCenterList() {
			document.getElementById("iframeA").src = "/membercenterlist";
			changeNavi(6);
		}

	</script>
	
	</body>
</html>
