<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>쪽지함</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<style>
		/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
		.row.content {
			height: 1500px
		}
		
		/* Set gray background color and 100% height */
		.sidenav {
			background-color: #f1f1f1;
			height: 100%;
		}
		
		/* Set black background color, white text and some padding */
		footer {
			background-color: #555;
			color: white;
			padding: 15px;
		}
		
		/* On small screens, set height to 'auto' for sidenav and grid */
		@media screen and (max-width: 767px) {
			.sidenav {
				height: auto;
				padding: 15px;
			}
			.row.content {
				height: auto;
			}
	
		}
	    table{
	    	width : 100%;
	    	text-align: center;
	    }
		</style>
	</head>
<body>
	<script type="text/javascript">
		window.onload = function () {
			if(${param.type} == 1){
				receiveNoteBox();
			}else if(${param.type}==2){
				sendNoteBox();
			}else if(${param.type}==3){		
				writeNote();
			}		
		}
		function changeNavi(num) {
			if(num == 1){
			    $("#li1").addClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").removeClass("active");
			}else if(num == 2){
			    $("#li1").removeClass("active");
			    $("#li2").addClass("active");
			    $("#li3").removeClass("active");
			}else if(num == 3){
			    $("#li1").removeClass("active");
			    $("#li2").removeClass("active");
			    $("#li3").addClass("active");
			}
		}
		function receiveNoteBox() {
		    var width = 650, height = 650;
		    var popupX = (document.body.offsetWidth / 2) - (width / 2);
		    //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
		    var popupY= (window.screen.height / 2) - (height / 2);
		    //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
		       
		    var url = "/receiveNoteList";
		    var option    = "width = " + width + ", height = " + height +", "
		                + "top = "+ popupY + ", left = " + popupX + ", resizable=no";
		    var title = "쪽지함";

		    changeNavi(1);
			document.getElementById("iframeA").src = url;
			
			/* window.open(url, title, option); */
		}
		function sendNoteBox() {
		    var width = 650, height = 650;
		    var popupX = (document.body.offsetWidth / 2) - (width / 2);
		    //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
		    var popupY= (window.screen.height / 2) - (height / 2);
		    //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
		       
		    var url = "/sendNoteList";
		    var option    = "width = " + width + ", height = " + height +", "
		                + "top = "+ popupY + ", left = " + popupX + ", resizable=no";
		    var title = "쪽지함";
		    changeNavi(2);

		    document.getElementById("iframeA").src = url;
		    /* window.open(url, title, option);	 */
		}
		function writeNote() {
		    var width = 650, height = 650;
		    var popupX = (document.body.offsetWidth / 2) - (width / 2);
		    //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
		    var popupY= (window.screen.height / 2) - (height / 2);
		    //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
		    var url = "/views/note/writeNote.jsp?senderId=${member.getMemberId()}";
		    var option    = "width = " + width + ", height = " + height +", "
		                + "top = "+ popupY + ", left = " + popupX + ", resizable=no";
		    var title = "쪽지함";
		    changeNavi(3);

		    document.getElementById("iframeA").src = url;
		    /* window.open(url, title, option);	 */
		}
	</script>
	
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h4>쪽지함</h4>
				<ul class="nav nav-pills nav-stacked">
					<li	id = "li1"
						<c:choose> 
							<c:when test = "${param.type eq 1}">
								class="active"
							</c:when>
							<c:otherwise>
								class=""
							</c:otherwise>
						</c:choose>>
						<a href="#" onclick = "receiveNoteBox()">받은쪽지</a>
					</li>
			        <li id= "li2"
			        	<c:choose> 
							<c:when test = "${param.type eq 2}">
								class="active"
							</c:when>
							<c:otherwise>
								class=""
							</c:otherwise>
						</c:choose>>
			        	<a href="#" onclick = "sendNoteBox()">보낸쪽지</a>
			        </li>
		        	<li id = "li3">
		        		<a href="#" onclick = "writeNote()">쪽지작성</a>
		        	</li>
				</ul>
			</div>
			<iframe id = "iframeA" src="" style = "width : 100%; height : 470px">
			</iframe>
		</div>
	</div>
	<footer class="container-fluid">
		<!-- <p>Footer Text</p> -->
	</footer>
</body>
</html>