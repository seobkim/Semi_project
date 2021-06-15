<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>보호소 리스트</title>
	<style type="text/css">
		.category{
			text-align: center;
		}
		.cdt{
			text-align: center;
		}
		div{
			overflow: visible;
		}
		#piechart{
			width: 100px;
			height: 100px;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript"	
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6aef0f8cb3601f757909683f15e28741&libraries=services"></script>
	
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css">
	<script src="https://d3js.org/d3.v3.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	<table class="table table-striped">
		<thead>
			<tr>
				<th width="20%">보호소명</th>
				<th width="20%">주소</th>
				<th width="15%">우편번호</th>
				<th width="15%">연락처</th>
				<th width="10%">분양현황</th>
				<th width="10%">현황그래프</th>
				<th width="10%"><a href ="/centerList?type=2">전체 지도</a><br>링크</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="center" varStatus="i">
				<tr class = "centerData">
					<td class="">${center.getMemberName()}</td>
					<td class="">${center.getMemberAddress()}</td>
					<td class="">${center.getMemberPost()}</td>
					<td class="">${center.getMemberPhone()}</td>
					<td class="">
						분양 : <label id = "success${i.index}">${center.getAdoption_success()}</label><br>
						미분양 : <label id = "yet${i.index}">${center.getAdoption_yet()}</label>
					</td>
					<td>
						<div id="piechart${i.index}"></div>
					</td>
					<td class="">
						<form 	name = "form${i.index}" method="post"
								accept-charset="utf-8">
							<input type = "hidden" name = "name" value = "${center.getMemberName()}">
							<input type = "hidden" name = "address" value = "${center.getMemberAddress()}">
							<input type = "hidden" name = "post" value = "${center.getMemberPost()}">
							<input type = "hidden" name = "phone" value = "${center.getMemberPhone()}">
							<input type = "hidden" name = "adopSuccess" value = "${center.getAdoption_success()}">
							<input type = "hidden" name = "adopYet" value = "${center.getAdoption_yet()}">
						</form>
						<a href ="javascript:showLocation(${i.index}, '${center.getMemberName()}')">위치확인</a><br>
						<a href ="javascript:findRoad('${center.getMemberName()}','${center.getMemberAddress()}')">길찾기</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	<script type="text/javascript">
		// 주소-좌표 변환 객체를 생성합니다
		window.onload = function () {
			for(var i = 0 ; i < $(".centerData").length; i ++){
				
				
				var chartDonut = c3.generate({
					bindto: "#piechart"+i,
					data: {
						columns : [
							['분양', parseInt($("#success"+i).text())],
							['미분양', parseInt($("#yet"+i).text())]
						],
						type: "donut",
					},
				});
				chartDonut.resize({height:100, width:300});
			}
		}
		var geocoder = new kakao.maps.services.Geocoder();
		
		function showLocation(num, title){
			var name = "form"+num;
			$("form[name='"+name+"']").one("submit", function() {
				var width = 650, height = 650;
		        var popupX = (document.body.offsetWidth / 2) - (width / 2);
		        //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
		        var popupY= (window.screen.height / 2) - (height / 2);
		        //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
		           
		        var url = ""
		        var option  = "width = " + width + ", height = " + height +", "
		                    + "top = "+ popupY + ", left = " + popupX + ", location = no";
		        
		    	window.open(url, title, option);
		    	this.action = "/views/center/centerLocation.jsp";
		    	this.method = 'POST';
		    	this.target = title;
			}).trigger("submit");
		}
		
		
		function open_pop(title, lng, lat) {
	        var width = 650, height = 650;
	        var popupX = (document.body.offsetWidth / 2) - (width / 2);
	        //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
	
	        var popupY= (window.screen.height / 2) - (height / 2);
	        //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	           
	        var url = "https://map.kakao.com/link/to/"+title+"," + lng + ","+ lat;
	        var option    = "width = " + width + ", height = " + height +", "
	                    + "top = "+ popupY + ", left = " + popupX + ", location = no";
	        var title = "길찾기";
	    	window.open(url, title, option);
		}
		
		function findRoad(name, address) {
			geocoder.addressSearch(address,function(result, status) {
				// 정상적으로 검색이 완료됐으면 
				if (status === kakao.maps.services.Status.OK) {				
					open_pop(name, result[0].y,	result[0].x);
				}
			});
		}
		
	</script>
</body>
</html>