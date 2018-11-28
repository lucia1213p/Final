<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>비밀번호 수정</title>
</head>
<body>
	<script>
		$(function(){
			$("#currentPwd").keyup(function(){
				$("#currentPwdMsg").text("현재 비밀번호를 입력해주세요");
			});
			$("#newPwd").keyup(function(){
				if($("#newPwd").val()==""){
					$("#newPwdMsg").text("");
					$("#newPwdMsg").text("새 비밀번호를 입력해주세요");
				}else if(!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test($("#newPwd").val())){
					$("#newPwdMsg").text("");
					$("#newPwdMsg").text("숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.");
				}else{
					$("#newPwdMsg").text("");
				}
			});
			$("#newPwdCheck").keyup(function(){
				if($("#newPwdCheck").val()==""){
					$("#newPwdCheckMsg").text("");
					$("#newPwdCheckMsg").text("비밀번호를 입력해주세요");
				}else if(!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test($("#newPwdCheck").val())){
					$("#newPwdCheckMsg").text("");
					$("#newPwdCheckMsg").text("숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.");
				}else if($("#newPwdCheck").val()!=$("#newPwd").val()){
					$("#newPwdCheckMsg").text("비밀번호가 같지 않습니다");
				}else{
					$("#newPwdCheckMsg").text("");
				}
			});
		});
	</script>
	<!-- 헤더 내비 -->
	<jsp:include page="/header.jsp" flush="false" />
	
	<div class="container">
		<form action="#" method="post" name="changePwd">
			<table>
				<tr>
					<td><label>현재 비밀번호</label></td>
					<td><input type="password" name="currentPwd"  class="form-control my-input" id="currentPwd">
					<p id="currentPwdMsg" style="color:red;"></p></td>
				</tr>
				<tr>
					<td><label>새 비밀번호 입력</label></td>
					<td><input type="password" name="newPwd"  class="form-control my-input" id="newPwd">
					<p id="newPwdMsg" style="color:red;"></p></td>
				</tr>
				<tr>
					<td><label>비밀번호 확인</label></td>
					<td><input type="password" name="newPwdCheck"  class="form-control my-input" id="newPwdCheck">
					<p id="newPwdCheckMsg" style="color:red;"></p></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>