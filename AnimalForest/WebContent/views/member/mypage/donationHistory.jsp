<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>후원내역</title>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css">
	<script src="https://d3js.org/d3.v3.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>		
	<style>
		th {
			text-align: center;
		}
		
		table * {
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		tr:hover {
			cursor: pointer;
		}

		td{
			text-align: center;
		}
	</style>
</head>
<body>
	<table class="table table-striped">
		<thead>
			<tr>
				<th width="15%">제목</th>
				<th width="35%">내용</th>
				<th width="10%">목표금액</th>
				<th width="10%">후원금액(전체)</th>
				<th width="10%">후원금액(본인)</th>
				<th width="10%">달성율</th>
				<th width="10%">후원일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pd.getPageList()}" var="board" varStatus="i">
				<tr	class = "donationData"
					onclick="showContent(${board.getBoardNo()})">
					<td class="title">${board.getTitle()}</td>
					<td class="content">${board.getContent()}</td>
					<td id = "fullAmount${i.index}" class="fullAmount">${board.getFullAmount()}</td>
					<td id = "sumAmount${i.index}" class="sumAmount">${board.getSumAmount()}</td>
					<td id = "myAmount${i.index}" class="myAmount">${board.getDonationAmount()}</td>
					<td class="rt"><div id="barchart${i.index}"></div></td>
					<td class="donationDate">${board.getDonationDate()}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr class="navi">
				<td colspan="4" align="center">
					<nav aria-label="pageNavi">
						<ul class="pagination pagination-lg">
							<li class="page-item">${pd.getPageNavi()}</li>
						</ul>
					</nav>
				</td>
			</tr>
		</tfoot>
	</table>

	<script type="text/javascript">
		function showContent(boardNo) {
			/* console.log("카테고리 번호 : " + categoryNo);
			console.log("게시판 번호 : " + boardNo); */
			console.log(boardNo);
			location.href = "/donationSelect?board_No=" + boardNo+"&type=1";
		}
	
		
		window.onload = function () {
			for(var i = 0 ; i < $(".donationData").length; i ++){
				
				
				var chartbar = c3.generate({
					bindto: "#barchart"+i,
					data: {
						columns : [
							['목표금액', parseInt($("#fullAmount"+i).text())/1000],
							['총모금액', parseInt($("#sumAmount"+i).text())/1000],
							['후원금액(본인)', parseInt($("#myAmount"+i).text())/1000]
						],
						type: "bar",
					},
					title: {
						  text: '단위(천원)'
					},
					axis : {
						rotated: true,
						y: {
				            max: parseInt($("#fullAmount"+i).text())/1000 * 1.1,
				            min: parseInt($("#myAmount"+i).text())/1000 * 0.8,
				            tick : {
				            	format : d3.format(",원")
				            }
				            // Range includes padding, set 0 if no padding needed
				            // padding: {top:0, bottom:0}
				        }
				    },
					bar: {
						width: {
				            ratio: 0.5 // this makes bar width 50% of length between ticks
				        }
					},
				});
				chartbar.resize({height:100, width:350});
			}
		}
	</script>
</body>
</html>