<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% Member member = (Member)session.getAttribute("member"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>동물의숲</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
#page>li {
	float: left;
}
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
</style>
</head>
<body>
<c:if test="${param.type eq 0}">
	<jsp:include page="/views/common/header.jsp"></jsp:include>
	
	<br><br>
	</c:if>
	

	<div class="container">
		
    <div class="col-lg-12">   
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3><b>${board.title}</b></h3>
            	
            </div>
            <div class="panel-body">
            	<center>
                <img src="img/${board.fileName}" style="width: 80%; height: 600px"><br>
                </center>
                <br>
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">항목</th>
                      <th scope="col" colspan="3">상세내용
                      <input type="hidden" name="board_No" value="${board.board_No}"></th>
                    </tr>
                  </thead>
                  <tbody>
  					<tr>
                      <th scope="row" class="article">공개여부</th>
                      <td colspan="3">
                      
                    
                        <c:if test="${mem.memberType.toString() eq '0'}">
            		 <c:if test="${board.flag.toString() eq '0'}">
										<button id="btn" onclick="chbtn()" value="0">비공개</button>
									</c:if>
									
						<c:if test="${board.flag.toString() eq '1'}">
										<button id="btn" onclick="chbtn()" value="1">공개</button>
										
										
									</c:if>	
            		</c:if>
            		 <c:if test="${mem.memberType.toString() eq '1'}">
            		 <c:if test="${board.flag.toString() eq '0'}">
										비공개
									</c:if>
									
						<c:if test="${board.flag.toString() eq '1'}">
										공개
										
										
									</c:if>	
            		</c:if>
            		 <c:if test="${mem.memberType.toString() eq '2'}">
            		 <c:if test="${board.flag.toString() eq '0'}">
										비공개
									</c:if>
									
						<c:if test="${board.flag.toString() eq '1'}">
										공개
										
										
									</c:if>	
            		</c:if>
                      </td>
                      
                      
                    </tr>
                    <tr>
                      <th scope="row" class="article">후원 목표 금액</th>
                      <td colspan="3">
                      ${board.full_Amount }
                      </td>
                    </tr>
                    <tr>
                      <th scope="row" class="article">후원 마감 기간</th>
                      <td colspan="3">
                      ${board.endDate }
                      
                      </td>
                    </tr>
                  
                    <tr>
                        <th scope="row" class="article">후원인원</th>
                        <td colspan="3">${result.count }명  </td>
                    </tr>
           			<tr>
                        <th scope="row" class="article">후원금액</th>
                        <td colspan="3">
                        
                        <c:if test ="${num < 10}">
                        <div class="progress progress-bar" style="width:10%; height: 20px;">${num}%</div>
                    	</c:if>
                    	<c:if test ="${num > 10}">
                        <div class="progress progress-bar" style="width:${num/2}%; height: 20px;">${num}%</div>
                    	</c:if>
                    	</td>
                    </tr>
                    	
                    <tr>
                        <th scope="row" class="article">내용</th>
                        <td colspan="3">${board.content}</td>
                    </tr>
                  </tbody>
                </table>
            </div>
            <div class="panel-footer text-center">
                <input type="button" onclick="del()" value="삭제">
						
						<input type="button" onclick="modify()" value="수정"> <input
							type="button" onclick="backPage()" value="목록"> <input
							type="button" style="float: right;" class="btn btn-success"
							onclick="donation('${board.title}','<%=member.getMemberEmail()%>','<%=member.getMemberName()%>','<%=member.getMemberPhone()%>','<%=member.getMemberAddress()%>')"
							value="후원하기">
            </div>
        </div>
        <div class="col-md-12 col-lg-3"></div>
    </div>
</div>
	
	<br>
	<script>
                     function del(){
                        if(<%=member.getMemberNum()%> == ${board.member_Num}){
                        location.href="donationDelete?board_No="+${board.board_No}
                        }   else {
                           alert("글 삭제 불가");
                        }
                        }
                     
                     function modify(){
                        if(<%=member.getMemberNum()%> == ${board.member_Num}){
                        location.href="donationModify?board_No="+${board.board_No};
                        }else{
                        alert("글 수정 불가");
                        }
                           
                     }
                     
                     function backPage(){
                        window.history.back();
                        
                     }
                     
                     function donation(orderName,email,name,tel,addr){
                    	var money=window.prompt("결제할 금액을 입력하세요");
                        var IMP = window.IMP;
                        IMP.init('imp37897171');
                        IMP.request_pay({
                            pg : 'html5_inicis',
                            pay_method : 'vbank',
                            merchant_uid : 'merchant_' + new Date().getTime(),
                            name : orderName,
                            amount : money,
                            buyer_email : email,
                            buyer_name : name,
                            buyer_tel : tel,
                            buyer_addr : addr
                        
                           
                        }, function(rsp) {
                            if ( rsp.success ) {
                                var msg = '결제가 완료되었습니다.';
                                msg += '고유ID : ' + rsp.imp_uid;
                                msg += '상점 거래ID : ' + rsp.merchant_uid;
                                msg += '결제 금액 : ' + rsp.paid_amount;
                                msg += '카드 승인번호 : ' + rsp.apply_num;
                                location.href = "donationHistory?boardNo="+${board.board_No}+"&amount="+money   ;
                                
                                
                                
                            } else {
                                var msg = '결제에 실패하였습니다.';
                                msg += '에러내용 : ' + rsp.error_msg;
                            }

                            alert(msg);
                        });
                     
                     }
                    
                     function chbtn() {
             			
             			var number = $("#btn").val();
             			console.log(number);
             			
             		
             			
             			var	url = "/flagModi?flag="+number+"&boardNo="+${board.board_No};
             			
             			
             			$.ajax({
             				url : url,
             				type : "get",
             				success : function(data) {
             					
             					
             					$("#btn").val(data.val);
             					$("#btn").text(decodeURIComponent(data.text));
             				},
             				error : function() {
             					console.log("실패");
             				}
             			});
             		}
                  </script>

<c:if test="${param.type eq 0}">
	<footer class="container-fluid text-center">
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</footer>
</c:if>
</body>
</html>