<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.jumbotron{
			margin-bottom: 0px;
		}
		.navbar {
      		border-radius: 0;
			margin-bottom: 0;
    	}	
    	a:hover {
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div class="jumbotron">
		<div class="container text-center">
			<img
				src="/img/logo.png"
				style="height: 30%; width: 25%;">
			<h5>유기동물과 더불어 사는 우리들의 숲</h5>
		</div>
	</div>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">누구개어디냥</a>
		</div>

		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href = "/AboutCompany.jsp">회사소개</a></li>
				<li><a id = "centerBoard" onclick="link(this)">보호소정보</a></li>
			    <li><a id = "animalBoard" onclick="link(this)">입양공고</a></li>
                <li><a id = "serviceBoard" onclick="link(this)">자원봉사</a></li>
				<li><a id = "donationBoard" onclick="link(this)">후원</a></li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">커뮤니티 </a>
                	<ul class="dropdown-menu">
                    	<li><a id = "reviewBoard" onclick="link(this)">입양후기</a></li>
                        	<li><a id = "eventBoard" onclick="link(this)">이벤트</a></li>
                            <li><a id = "saleBoard" onclick="link(this)">할인행사</a></li>
                            <li><a id = "serveBoard" onclick="link(this)">서비스</a></li>
						</ul>
				</li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<c:if test="${member!=null}"> 
					<li><a href="/logout">로그아웃</a></li>
					<li><a href="/views/member/mypage/Mypage.jsp?type=1"><span class="glyphicon glyphicon-user"></span>
						마이 페이지</a></li>
					<li><a href="javascript:receiveNoteBox()"> <span class="glyphicon glyphicon-envelope"></span>
						쪽지</a></li>
				</c:if>
				<c:if test="${member==null}">
					<li><a onclick="login()"><span class=""></span> LOGIN</a></li>
                   	<li><a href="/views/member/join.jsp">회원가입 </a></li>
          		</c:if>
			</ul>
		</div>
	</div>
	</nav>
	<script type="text/javascript">
		function receiveNoteBox() {
		    var width = 650, height = 700;
		    var popupX = (document.body.offsetWidth / 2) - (width / 2);
		    //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
		    var popupY= (window.screen.height / 2) - (height / 2);
		    //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
		       
		    var url = "/views/note/noteBox.jsp?type=1";
		    var option    = "width = " + width + ", height = " + height +", "
		    			+ "top = "+ popupY + ", left = " + popupX + ", resizable=no, status=no, location=no";
		    var title = "쪽지함";
			window.open(url, title, option);	
		}
		function login() {
			var width = 650, height = 400;
		    var popupX = (document.body.offsetWidth / 2) - (width / 2);
		    //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
		    var popupY= (window.screen.height / 2) - (height / 2);
		    //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
		    var url = "/views/member/login.html";
	    	var option    = "width = " + width + ", height = " + height +", "
	    				+ "top = "+ popupY + ", left = " + popupX + ","
	    				+ " location = no, resizable=no, toolbar = yes";
	    	var title = "로그인";
			window.open(url, title, option);
		}
		
		function link(obj) {
			console.log(obj.id)
			if(${empty member}){
				login();
			}else{
				switch (obj.id) {
				
				case "myPage" :
					location.href = "/views/member/mypage/Mypage.jsp?type=1";
					break;	
				case "animalBoard" :
					location.href = "/boardList";
					break;
				case "centerBoard" :
					location.href = "/centerList?type=1";
					break;
				case "serviceBoard":
					location.href = "/serviceBoard";
					break;
				case "donationBoard":
					location.href = "/donationBoard";
					break;
				case "reviewBoard":
					location.href = "/review";
					break;
				case "eventBoard":
					location.href = "/benefitList?categoryNo=1";
					break;
				case "saleBoard":
					location.href = "/benefitList?categoryNo=2";
					break;
				case "serveBoard":
					location.href = "/benefitList?categoryNo=3";
					break;
				default:
					break;
				}			
			}	
		}
	</script>
</body>
</html>