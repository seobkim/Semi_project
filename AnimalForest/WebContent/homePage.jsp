<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <%
        Member member=(Member)session.getAttribute("member");
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인화면</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        a:hover {
          	text-decoration: none;
          	color: gray;
          	cursor: pointer;
        }
        a {
            color:black;
        }
        .row .a {
            float: right;
        }
    </style>
</head>



<body>

    <div id="warpper">
        <jsp:include page="/views/common/header.jsp"></jsp:include>
        <br><br>
        <div class="container">

            <div class="row">
                <div class="col-sm-5">
                    <div class="panel panel-default">
                        <div class="panel-heading"><a id = "animalBoard" onclick="link(this)">입양공고 <div class="a"> 더보기 <span class="glyphicon glyphicon-arrow-right"></span>  </div></a>   </div>
                        <div class="panel-body"><img src="img/${animal.fileName }" class="img-responsive" style="width:100%; height:250px" alt="Image"></div>
                        <div class="panel-footer">${animal.title }</div>
                    </div>

                </div>
                <div class="col-sm-2"></div>
                <div class="col-sm-5">
                    <div class="panel panel-default">
                        <div class="panel-heading"><a id = "reviewBoard" onclick="link(this)">입양후기 <div class="a"> 더보기 <span class="glyphicon glyphicon-arrow-right"></span>  </div></a></div>
                        <div class="panel-body"><img src="img/${review.fileName }" class="img-responsive" style="width:100%; height:250px" alt="Image"></div>
                        <div class="panel-footer">${review.title }</div>
                    </div>

                </div>

            </div>


        </div><br>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><a id = "donationBoard" onclick="link(this)">후원 <div class="a"> 더보기 <span class="glyphicon glyphicon-arrow-right"></span>  </div></a></div>
                        <div class="panel-body"><img src="img/${donation.fileName }" class="img-responsive" style="width:100%; height:250px" alt="Image"></div>
                        <div class="panel-footer">${donation.title }</div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><a id = "serviceBoard" onclick="link(this)">자원봉사 <div class="a"> 더보기 <span class="glyphicon glyphicon-arrow-right"></span>  </div></a></div>
                        <div class="panel-body"><img src="img/${service.fileName }" class="img-responsive" style="width:100%; height:250px" alt="Image"></div>
                        <div class="panel-footer">${service.title }</div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><a id = "eventBoard" onclick="link(this)">혜택 <div class="a"> 더보기 <span class="glyphicon glyphicon-arrow-right"></span>  </div></a></div>
                        <div class="panel-body"><img src="img/${benefit.fileName }" class="img-responsive" style="width:100%; height:250px" alt="Image"></div>
                        <div class="panel-footer">${benefit.title }</div>
                    </div>
                </div>
            </div>
        </div><br>
       <br>
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
    </div>
	
</body>

</html>
