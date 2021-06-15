<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>입양신청폼</title>
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
    <br><br><br>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
            </div>
            <div class="col-md-5">
                <h2 class="text-center">입양 신청서 작성</h2>
                <form action="/AnimalBoardFormToSurveyServlet" method="post">
                    <table class="table table-striped">
                    
                        <tr>
                            <td>성함</td>
                            <td><input type="text" class="form-control" name="name" value="${memberName }" readonly></td>
                            <input type="hidden" name="boardNo" value = "${boardNo }"/>
                        </tr>

                        <tr>
                            <td>연락처</td>
                            <td><input type="text" class="form-control" name="phone" placeholder="연락처를 입력해주세요" required></td>
                        </tr>

                        <tr>
                            <td>주소</td>
                            <td><input type="text" class="form-control" name="address" placeholder="주소를 입력해주세요" required></td>
                        </tr>

                        <tr>
                            <td>직업</td>
                            <td><input type="text" class="form-control" name="job" placeholder="직업을 입력해주세요" required></td>
                        </tr>

                        <tr>
                            <td>메일</td>
                            <td><input type="text" class="form-control" name="email" placeholder="메일을 입력해주세요~" required></td>
                        </tr>
                        
                        <tr>
							<td style="vertical-align: middle;">각오</td>
							<td><textarea rows="10" cols="50" name="content" class="form-control" placeholder="내용을 입력하세요" required></textarea></td>
						</tr>

                        <tr>

                            <td colspan="2" class="text-center"><input type="submit" value="작성완료(다음단계)" class="btn btn-success"> <input type="reset" value="다시작성" class="btn btn-warning">
                                <button type="button" class="btn btn-primary" onclick="boardList()">전체게시글보기</button>
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
        
        function boardList(){
			location.href="/boardList"
		}
    </script>
</body></html>