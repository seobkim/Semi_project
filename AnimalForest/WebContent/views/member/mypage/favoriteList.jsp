<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>관심 입양글</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
		img {
			top: 6px;
			left: 5px;
			width: 33px;
			height: 33px;
			border: 0px solid #ddd;
			color: #888;
			overflow: hidden;
		}
		/* Remove the navbar's default margin-bottom and rounded borders */
		.navbar {
			margin-bottom: 0;
			border-radius: 0;
		}
		
		/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
		.row.content {
			height: 450px
		}
		
		/* Set gray background color and 100% height */
		.sidenav {
			padding-top: 20px;
			background-color: #f1f1f1;
			height: 100%;
		}
		
		/* Set black background color, white text and some padding */
		footer {
			background-color: #555;
			color: white;
			padding: 15px;
		}
		
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
		.category{
			text-align: center;
		}
		.cdt{
			text-align: center;
		}
	</style>
</head>
<body>
	<table class="table table-striped">
		<thead>
			<tr>
				<th width="10%"></th>
				<th width="20%">제목</th>
				<th width="50%">내용</th>
				<th width="10%">작성일</th>
				<th width="10%">분양여부<th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pd.getPageList()}" var="board" varStatus="i">
				<tr>
					<td id="favorite${board.getBoardNo()}" onclick="chg(${board.getBoardNo()})">
						<img src="/img/like.png">
					</td>
					<td class="title" onclick="showContent(${board.getBoardNo()})">${board.getTitle()}</td>
					<td class="content">${board.getContent()}</td>
					<td class="cdt">${board.getCdt()}</td>
					<td class="yn">
						<c:choose>
							<c:when test="${board.getNet_yn() eq null}">Y</c:when>
							<c:otherwise>N</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr class="navi">
				<td colspan="5" align="center">
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
			parent.location.href = "/animalBoardDetail?boardNo=" + boardNo;
		}
		function chg(num) {
			var date = new Date();
			var name = "#favorite"+num;
			var src = $($(name).children()[0]).attr('src');
			var url = "";
			if(src == "/img/like.png"){
				url = "/deleteFavorite?currentPage=${param.currentPage}&boardNo="+num;
			}else if(src == "/img/unlike.png"){
				url = "/insertFavorite?currentPage=${param.currentPage}&boardNo="+num;
			}
			
			$.ajax({
				url : url,
				type : "get",
				success : function(data) {
					$($(name).children()[0]).removeAttr("src").attr("src",data.src);
				},
				error : function() {
					console.log("실패");
				}
			});
		}
		
	</script>
</body>
</html>