<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지 & 관리자</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('postcode').value = data.zonecode;
						document.getElementById("address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_extraAddress").focus();
					}
				}).open();
	}

	function question() {
		var result = window.confirm("정말로 회원 탈퇴를 하시겠습니까?");
		if (result) {
			return true;
		} else {
			return false;
		}
	}

	function loadImg(obj) {
		console.log(obj.files);
		if (obj.files.length != 0 && obj.files[0] != 0) {
			var reader = new FileReader();
			reader.readAsDataURL(obj.files[0]);
			reader.onload = function(e) {
				$("#img-view").attr("src", e.target.result);
			}

		} else {
			$("#img-view").attr("src", "");
		}

	}

	function validate() {
		var re = /^[a-zA-Z0-9]{3,10}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
		var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 이메일이 적합한지 검사할 정규식

		//이름 닉네임 정규식 
		var re3 = /^[가-힣a-zA-Z0-9]{1,}$/;

		//  var file = document.getElementById("img-viewer");
		var type = document.getElementById("type");
		var id = document.getElementById("userId");
		var nickName = document.getElementById("nickName");
		var pw = document.getElementById("userPwd");
		var checkpw = document.getElementById("checkpw");
		var userName = document.getElementById("uerName");
		var regNum = document.getElementById("regNum");
		var email = document.getElementById("email");
		var phone = document.getElementById("phone");
		var postcode = document.getElementById("postcode");
		var addrButton = document.getElementById("addrButton");

		var imgFile = $('#fileName').val();
		var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
		var maxSize = 5 * 1024 * 1024;
		var fileSize;

		if ($('#fileName').val() == "") {
			alert("프로필 사진을 확인해주세요 ");
			$("#fileName").focus();
			return false;

			/*       if (imgFile != "" && imageFile != null) {
			    fileSize = document.getElementById("fileName").files[0].size;

			    if (!imgFile.match(fileForm)) {
			        alert("이미지 파일만 업로드 가능");
			        return;
			    } else if (fileSize = maxSize) {
			        alert("파일 사이즈는 5MB까지 가능");
			        return;
			    }
			}*/

			//ID 
		} else if (!check(re, id, "아이디는 3~10자의 영문 대소문자와 숫자로만 입력")) {
			return false;
			//닉네임 
		} else if (join.nickName.value == "") {
			alert("닉네임을 입력해주세요 ");
			join.nickName.focus();
			return false;

		} else if (!check(re, nickName, "적합하지 않은 닉네임 형식입니다.")) {
			return false;

			/*else if (!regname.test(join.nickName.value == "")){
			    alert("닉네임을 입력해주세요");
			        nickName.innerHTML="닉네임을 입력해 주세요";
			          nickName.style.backgroundColor="green";
			    join.nickName.focus();
			    return false; }*/

			//패스워드 
		} else if (!check(re, pw, "패스워드는 3~10자의 영문 대소문자와 숫자로만 입력")) {
			return false;

		} else if (pw.value != join.checkpw.value) {
			alert("비밀번호가 서로다릅니다 ");
			join.checkpw.focus();
			return false;

			/*            
			            if(join.pw.value != join.checkpw.value) {
			       alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
			       join.checkpw.value = "";
			       join.checkpw.focus();
			       return false;
			   }
			 */

		} else if (join.userName.value == "") {
			alert("이름을 입력해주세요 ");
			join.userName.focus();
			return false;

		} else if (!check(re3, join.userName, "적합하지 않은 이름 형식입니다.")) {
			return false;

			//email    
		} else if (email.value == "") {
			alert("이메일을 입력해 주세요");
			email.focus();
			return false;
		} else if (!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
			return false;

			//연락처         
		} else if (join.phone.value == "") {
			alert("연락처를 입력해주세요");
			join.phone.focus();
			return false;

			//주소        
		} else if (join.postcode.value == "") {
			alert("주소를 입력해주세요");
			join.addrButton.focus();
			return false;
		}

		alert("회원가입이 완료되었습니다.");
	} //

	function check(re, what, message) {
		if (re.test(what.value)) {
			return true;
		}
		alert(message);
		what.value = "";
		what.focus();
		//return false;
	}
</script>

<style>
/* Remove the navbar's default rounded borders and increase the bottom margin */
div {
	/* border: 1px solid black; */
	box-sizing: border-box;
}

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
	background-color: grey;
	padding: 25px;
}

.box {
	display: none;
}
</style>

<title>회원정보수정 & 탈퇴</title>

</head>

<body>


	<div class="container">
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="col-sm-7">		
					<!--일반 고객 로그인시   -->
					<c:if test="${sessionScope.member.memberId !=null}">
						<h2>회원정보수정 & 탈퇴</h2>
						<form action="/mupdate" method="post"
							enctype="multipart/form-data" onsubmit="return validate();">
						<table class="table table-boardered">
							<tr>
								<th>구분</th>
								<c:if test="${member.memberType.toString() eq '1'}">
										<td><input type="radio" value="1" name="type" id="type"
													checked readonly>일반 </td>								
										<td><input type="radio" value="2" name="type" id="type">보호소</td>
								</c:if>
								<c:if test="${member.memberType.toString() eq '2'}">
										<td><input type="radio" value="1" name="type" id="type">일반 </td>
										<td><input type="radio" value="2" name="type" id="type"
													checked readonly>보호소 </td></c:if>
							</tr>
							<tr>
								<th>프로필</th>
								<td colspan="2">		
									<img
										src="/memberProfile/${member.memberPhotoName}"
										width="200px" id="img-view"><br> <input
										type="file" name="fileName" id="fileName"
										onchange="loadImg(this)" value = "${member.memberPhotoName}">
								</td>
							</tr>
							<tr>
								<th>아이디</th>
								<td colspan="2">
									<input 	type="text" name="userId"
											id="userId" class="form-control" value="${member.memberId}"
											readonly>
								</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td colspan="2">
									<input 	type="text" name="nickName"
											id="nickName" class="form-control"
											value="${member.memberNick}">
								</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td colspan="2">
									<input 	type="password" name="userPwd"
											id="userPwd" class="form-control">
								</td>
							</tr>
							<tr>
								<th>비밀번호확인</th>
								<td colspan="2">
									<input 	type="password" name="pwd_check"
											class="form-control" id="pwd_check">
								</td>
							</tr>
							<tr>
								<th>이름</th>
								<td colspan="2">
									<input 	type="text" name="userName"
											id="userName" class="form-control"
											value="${member.memberName}">
								</td>
							</tr>

							<tr>
								<c:if test="${sessionScope.member.memberType.toString()=='1'}">
								<th id="juminNum">주민등록번호</th>
									<td colspan="2">
										<input 	type="text" name="regNum"
												id="NumBox" class="form-control"
												value="${member.memberRegNum}" readonly>
									</td>
								</c:if>
								<c:if test="${sessionScope.member.memberType.toString()=='2'}">
								<th id="saupNum">사업자번호</th>
									<td colspan="2">
										<input 	type="text" name="regNum"
												id="NumBox" class="form-control"
												value="${member.memberRegNum}" readonly>
									</td>
								</c:if>
							</tr>

							<tr>
								<th>이메일</th>
									<td colspan="2">
										<input 	type="text" name="email"
												id="email" class="form-control"
												value="${member.memberEmail}">
									</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td colspan="2">
									<input 	type="text" name="phone"
											id="phone" class="form-control"
											value="${member.memberPhone}">
								</td>
							</tr>
							<tr>
								<th rowspan="2">주소</th>
								<td>
									<input 	type="text" id="postcode" name="postcode"
											placeholder="우편번호" class="form-inline"
											value="${member.memberPost}"> <input type="button"
											onclick="sample6_execDaumPostcode()" id="addrButton"
											name="addrButton" value="우편번호 찾기">
								</td>
								<td></td>
							</tr>
								<tr>
									<td colspan="2">
										<input 	type="text" id="address"
												name="addr" placeholder="주소" class="form-control"
												value="${member.memberAddress}"> 
										<input 	type="text"
												id="sample6_extraAddress" name="addr2" placeholder="상세주소"
													class="form-control" value="${member.memberAddressDetail}">
									</td>
								</tr>

								<tr>
									<td>
										<input 	type="submit" value="수정" id="modify"
												class="btn btn-success">
									</td>
									<td>
										<input type="reset" value="취소" id="cancel"
													class="btn btn-warning">
									</td>
									<td>
										<a href="/mdelete">
											<input 	type="button" id="mdel"
													value="회원탈퇴하기 " onclick="return question();"
													class="btn btn-danger">
										</a>
									</td>
								</tr>

						</table>
						</form>

					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
