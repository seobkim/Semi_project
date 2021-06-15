<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>입양신청서 조회</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
            color: white;
        }

        th {
            font-size: 1.1em;
        }

        .article {
            color: limegreen;
        }

        .form-control {
            width: 250px;
        }

        .form-radio {
            size:
        }
    </style>
</head>

<body>
    <jsp:include page="/views/common/header.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <img src="/img/a.PNG" style="margin-top : 110px;">
            </div>
            <div class="col-md-5">
                <h2 class="text-center">입양 신청서 조회</h2>
                <form action="#" method="post">
                    <table class="table table-striped">
                    
                        <tr>
                            <td>성함</td>
                            <td><input type="text" class="form-control" name="name" value="${adoptionForm.memberName }" readonly></td>
            
                        </tr>

                        <tr>
                            <td>연락처</td>
                            <td><input type="text" class="form-control" name="phone" placeholder="연락처를 입력해주세요" value="${adoptionForm.phone }" readonly></td>
                        </tr>

                        <tr>
                            <td>주소</td>
                            <td><input type="text" class="form-control" name="address" placeholder="주소를 입력해주세요" value="${adoptionForm.address  }" readonly></td>
                        </tr>

                        <tr>
                            <td>직업</td>
                            <td><input type="text" class="form-control" name="job" placeholder="직업을 입력해주세요" value="${adoptionForm.job }" readonly></td>
                        </tr>

                        <tr>
                            <td>메일</td>
                            <td><input type="text" class="form-control" name="email" placeholder="메일을 입력해주세요~" value="${adoptionForm.email }" readonly></td>
                        </tr>
                        
                        <tr>
							<td style="vertical-align: middle;">각오</td>
							<td><textarea rows="10" cols="50" name="content" class="form-control" placeholder="내용을 입력하세요" readonly>${adoptionForm.content }</textarea></td>
						</tr>

                        <tr>

                            <td colspan="2" class="text-center">
                            <button type="button" class="btn btn-primary" onclick="ShowSurvey()">설문지 조회</button>
                            <button type="button" class="btn btn-primary">뒤로가기</button>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
        </div>



    </div>
    <br>
    <br>
    <jsp:include page="/views/common/footer.jsp"></jsp:include>
    <script>
        $(document).ready(function() {
            $("li").hover(function() {
                $(this).addClass("reverse");
            }, function() {
                $(this).removeClass("reverse");
            });
        });
        function ShowSurvey(){
        	location.href="/AnimalBoardSelectOneServeyServlet?boardNo="+${adoptionForm.boardNo }+"&memberNum="+${adoptionForm.memberNum}
        }
    </script>
</body></html>