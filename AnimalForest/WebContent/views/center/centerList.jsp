<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="member.model.vo.Member"%>
<% ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>보호소 리스트</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- 지도 API -->
	<script type="text/javascript"	
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6aef0f8cb3601f757909683f15e28741&libraries=services"></script>	
  	<style>
	    .wrap {position: absolute;left: 0;bottom: 40px;width: 206px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
	    .wrap * {padding: 0;margin: 0;}
	    .wrap .info {width: 206px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
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
	    #back{
	    	z-index: 0;
	    	/* position: absolute; */
	    }
	    #map{
	    	/* position: fixed; */
	    }
  	</style>
</head>
<body>
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	
	<button id = "back" type="button" class="btn btn-info" onclick="back()">뒤로가기</button>
	<div id="map" style="width: 100%; height: 40vh;">
		
	</div>
	
	<jsp:include page="/views/common/footer.jsp"></jsp:include>
	
	<script>
		function back() {
			window.history.back();
		}
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(37.8459543, 127.4991358), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);
		var overlay;
		var titles = [];	//제목 베열
		var overlays = [];	//오버레이 배열
		
		//팝업 Open 기능
		function open_pop(i) {
			
	        var lat = overlays[i-1].getPosition().Ga;
	        var lng = overlays[i-1].getPosition().Ha;
	        var width = 650, height = 650;
	        var popupX = (document.body.offsetWidth / 2) - (width / 2);
	        //&nbsp;만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

	        var popupY= (window.screen.height / 2) - (height / 2);
	        //&nbsp;만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	           
	        var url = "https://map.kakao.com/link/to/"+titles[i-1]+"," + lng + ","+ lat;
	        var option    = "width = " + width + ", height = " + height +", "
	                    + "top = "+ popupY + ", left = " + popupX + ", location = no";
	        var title = "길찾기";
        	console.log(url);
        	window.open(url, title, option);
		}
		
		// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay(i) {
			overlays[i-1].setMap(null);
		}
		var i = 0;
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		<%for(Member m: list){ %>
			geocoder.addressSearch("<%=m.getMemberAddress()%>",function(result, status) {
				// 정상적으로 검색이 완료됐으면 
				if (status === kakao.maps.services.Status.OK) {
					var coords = new kakao.maps.LatLng(result[0].y,	result[0].x);

					// 결과값으로 받은 위치를 마커로 표시합니다
					var marker = new kakao.maps.Marker({
						map : map,
						position : coords
					});
					i++;
					marker.setTitle(i);
					titles.push('<%=m.getMemberName()%>');
					var memberName = '<%=m.getMemberName()%>';
					// 인포윈도우로 장소에 대한 설명을 표시합니다
					var content = '<div class="wrap">' + 
					            '    <div class="info">' + 
					            '        <div class="title">' + '<%=m.getMemberName()%>'+  
					            '            <div class="close" onclick="closeOverlay('+i+')" title="닫기"></div>' + 
					            '        </div>' + 
					            '        <div class="body">' + 
					            '           <div class="desc">' + 
					            '               <div class="ellipsis">'+ '<%=m.getMemberAddress()%>' + '</div>' + 
					            '               <div class="jibun ellipsis">(우) ' +'<%=m.getMemberPost()%>' + ' (Tel) '+ '<%=m.getMemberPhone()%>' +'</div>' +
					            '               <div class="jibun ellipsis">(현황) 분양 : ' +'<%=m.getAdoption_success()%>' + ' 미분양 : '+ '<%=m.getAdoption_yet()%>' +'</div>' +
					            '			 	<div class="link" onclick = "open_pop('+i+')"> 길찾기 </div>' +
					            '           </div>' +
					            '        </div>' + 
					            '    </div>' +    
					            '</div>';
					         // 마커 위에 커스텀오버레이를 표시합니다
					         // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	 				overlay = new kakao.maps.CustomOverlay({
					    content: content,
					    map: map,
						position: marker.getPosition()       
					});
	 				overlays.push(overlay);
					// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
					kakao.maps.event.addListener(marker, 'click', function() {
						/* overlay.setMap(map); */
						overlays[parseInt(marker.getTitle())-1].setMap(map);
					});
					overlay.setMap(null);
					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				}
			});
		<%} %>
		if (navigator.geolocation) {
		    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
		        
		        var lat = position.coords.latitude, // 위도
		            lon = position.coords.longitude; // 경도
		        
		        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
		        	message = '<div style="padding:5px;">내 위치</div>'; // 인포윈도우에 표시될 내용입니다
		        	displayMarker(locPosition, message);
		        map.setCenter(locPosition);
		    });
		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
		    
		    var locPosition = new kakao.maps.LatLng(37.8459543, 127.4991358),
		    	message = 'geolocation을 사용할수 없어요..'
		    	displayMarker(locPosition, message);
		    map.setCenter(locPosition);
		}
		function displayMarker(locPosition, message) {

		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({  
		        map: map, 
		        position: locPosition
		    }); 
		    
		    var iwContent = message, // 인포윈도우에 표시할 내용
		        iwRemoveable = true;

		    // 인포윈도우를 생성합니다
		    var infowindow = new kakao.maps.InfoWindow({
		        content : iwContent,
		        removable : iwRemoveable
		    });
		    
		    // 인포윈도우를 마커위에 표시합니다 
		    infowindow.open(map, marker);
		    
		    // 지도 중심좌표를 접속위치로 변경합니다
		    map.setCenter(locPosition);      
		}
	</script>	
</body>
</html>