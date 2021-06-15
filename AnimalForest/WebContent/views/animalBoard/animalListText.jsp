<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        /* Remove the navbar's default rounded borders and increase the bottom margin */
        .navbar {
            margin-bottom: 50px;
            border-radius: 0;
        }

        /* Remove the jumbotron's default bottom margin */
        .jumbotron {
            margin-bottom: 0;
        }

        /* Add a gray background color and some padding to the footer */
        footer {
            background-color: #f2f2f2;
            padding: 25px;
        }

        .panel-body {
            width: 100%;
            height: 100%;
        }

        .reverse {
            background: #4CAF50;
            color: black;
        }

        #write {
            position: absolute;
            right: 600px;
            color: white;
            
        }
        .test1{
        }

    </style>
</head>

<body>

    <div class="jumbotron">
        <div class="container text-center">
            <img src="img/logo.png" style="height: 30%; width: 25%;">
            <h5>유기동물과 더불어 사는 우리들의 숲</h5>
        </div>
    </div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">누구개어디냥</a>
            </div>

            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="#">봉사</a></li>
                    <li><a href="#">입양</a></li>
                    <li><a href="#">혜택</a></li>
                    <li><a href="#">후원</a></li>
                    <li><a href="#">후기</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span>
                            마이 페이지</a></li>
                    <li><a href="#"> <span class="glyphicon glyphicon-envelope"></span> 쪽지
                        </a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="text-center" style="float: left">
        <select name="job">
            <option value="">분류</option>
            <option value="고양이">고양이</option>
            <option value="개">개</option>
        </select>
        <input type="search"><input type="submit" value="검색">
    </div>

    <div class="container">
        <div class="row" id="test1">
        
        </div> 
    </div>
    <br>

    <br>
    <br>
    <div class="text-center">
        <button type="button" class="btn btn-secondary" class="writea">이전페이지</button>
        <button type="button" class="btn btn-secondary" class="writea">다음페이지</button>
        <button type="button" class="btn btn-success" id="write"><a href="/views/animalBoard/animalBoardWriteForm.html">공고 올리기</a></button>
    </div>

    <br>
    <footer class="container-fluid text-center">
        <p>Copyright(C)2020.어디개어디냥.All right reserved</p>
        <p>
            <a href="#">회사소개</a> | <a href="#">개인정보처리방침</a> | <a href="#">청소년보호정책</a>
        </p>

    </footer>
    <script>
        $(document).ready(function() {
            $("li").hover(function() {
                $(this).addClass("reverse");
            }, function() {
                $(this).removeClass("reverse");
            });
        });
        
        
        window.onload = function () {
        	
            	var html = "";
                for (var i = 0; i < 6; i++) {
                	html += "<div class='col-sm-4'>";
                	html += "<div class='panel panel-default'>";
                	html += "<div class='panel-heading'>";
                	html += "<h4>홍길동이</h4>";
                	html += "</div>";
                	html += "<div class='panel-body'>";
                	html += "<img src='img/a.PNG' style='width: 100%; height: 100%;'>";
					html += "</div>";
					html += "<div class='panel-footer'>";
					html += "<p> 나이 : "13"<br> 이름 : 다솔<br> 장소 : 창원 유기견보호센터<br> 신청자격: 제한없음 <br></p>";
					html += "<button type='button' class='btn btn-success'><a href=/animalBoardDetail>상세보기</a></button>";
					html += "</div>";
					html += "</div>"; 
					html += "</div>";
            }
            $("#test1").prepend(html);
        }
        

        
        
    </script>
</body></html>

