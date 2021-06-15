<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>보호소 위치</title>	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript"	
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6aef0f8cb3601f757909683f15e28741&libraries=services"></script>
	<style type="text/css">
	    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
	    .wrap * {padding: 0;margin: 0;}
	    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
	    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
	    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
	    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
	    .info .close:hover {cursor: pointer;}
	    .info .body {position: relative;overflow: hidden;}
	    .info .desc {position: relative;margin: 13px 0 0 10px;height: 75px;}
	    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
	    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
	    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
	    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
	    .info .link {color: #5085BB;}
	</style>
</head>
<body>
	<div id="map" style="width:100%;height:700px;"></div>
	<script type="text/javascript">
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		var flag = false;
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch("${param.address}", function(result, status) {
			console.log(result);
			console.log(status);
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });	        
		        var content = 	'<div class="wrap">' + 
					            '    <div class="info">' + 
					            '        <div class="title">' + '${param.name}'+   
					            '        </div>' + 
					            '        <div class="body">' + 
					            '           <div class="desc">' + 
					            '               <div class="ellipsis">'+ '${param.address}' + '</div>' + 
					            '               <div class="jibun ellipsis">(우) ' +'${param.post}' + ' (Tel) '+ '${param.phone}' +'</div>' +
					            '               <div class="jibun ellipsis">(현황) 분양 : ' +'${param.adopSuccess}' + ' 미분양 : '+ '${param.adopYet}' +'</div>' +
					            '           </div>' +
					            '        </div>' + 
					            '    </div>' +    
					            '</div>';
				overlay = new kakao.maps.CustomOverlay({
				    content: content,
				    map: map,
					position: marker.getPosition()       
				});
				
				function closeOverlay(obj) {
					/* overlay.setMap(null); */
					console.log($(obj));
				}
				
				kakao.maps.event.addListener(marker, 'click', function() {
					if(flag == true){
						flag = false;
						overlay.setMap(null);	
					}else{
						flag = true;
						overlay.setMap(map);
					}
				});				
		        overlay.setMap(null);
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});     
	</script>
</body>
</html>