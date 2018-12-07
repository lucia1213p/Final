<%@page import="kr.or.tech.common.JDBCTemplate"%>
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

<title>회원가입 페이지</title>

<style>

	.send-button{
	background: #54C7C3;
	width:100%;
	font-weight: 600;
	color:#fff;
	padding: 8px 25px;
	}
	input[type=number]::-webkit-inner-spin-button, 
	input[type=number]::-webkit-outer-spin-button { 
	  -webkit-appearance: none; 
	  margin: 0; 
	}
	.g-button{
	color: #fff !important;
	border: 1px solid #EA4335;
	background: #ea4335 !important;
	width:100%;
	font-weight: 600;
	color:#fff;
	padding: 8px 25px;
	}
	.my-input{
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
	cursor: text;
	padding: 8px 10px;
	transition: border .1s linear;
	}
	.header-title{
	margin: 5rem 0;
	}
	h1{
	font-size: 31px;
	line-height: 40px;
	font-weight: 600;
	color:#4c5357;
	}
	h2{
	color: #5e8396;
	font-size: 21px;
	line-height: 32px;
	font-weight: 400;
	}
	.login-or {
	position: relative;
	color: #aaa;
	margin-top: 10px;
	margin-bottom: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
	}
	.span-or {
	display: block;
	position: absolute;
	left: 50%;
	top: -2px;
	margin-left: -25px;
	background-color: #fff;
	width: 50px;
	text-align: center;
	}
	.hr-or {
	height: 1px;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
	}
	@media screen and (max-width:480px){
	h1{
	font-size: 26px;
	}
	h2{
	font-size: 20px;
	}
	}
</style>
</head>
<body>

<script>

	$(function(){
		//아이디 중복체크	
		$('#idCheck').click(function(){
			if($("#id").val()==""){
				alert("아이디를 입력해주세요");	
			}else if(!/^[0-9a-zA-Z]{4,15}$/.test($("#id").val())){
				alert("영문자,숫자로 4~15자 입력해주세요");
			}else{
				var id= $('#id').val();
				$.ajax({
					type:'POST',
					url:'/idCheck.do',
					data:{userId:id},
					success : function(data){
						if(data=="true")
						{
							alert("ID사용불가");
							//아이디가 중복될 경우 입력했던 아이디 지우고 focus
							$('#id').val('').focus();
						}else{
							alert("사용가능");
						}
					},
					error:function(){
						location.href="/views/error/errorPage.jsp"
					}
				});
			}
		});
		
	});
	
	/* 하위 셀렉트 가져오기 */
	$('#selectGrade').change(function(){
		$('#selectBelong').find('option').each(function(){
			$(this).remove();
		});
		$('#selectBelong').append("<option value=''>---- SELECT ----</option>");
		
		var selectGrade=$(this).val();
		if(selectGrade!=""){
			var params=$('selectGrade').serialize();
			$.ajax({
				type:'POST',
				url:'/belongLoad.do',
				data:params,
				dataType:'json',
				success:function(data){
					console.log('success');
					var result=new Array();
					result=data.result.belongList;
					console.log(result[0].BelongCode);
					if(data==null){
						data=0;
					}
					for(var i=0;i<data.length;i++){
						$('#selectBelong').append("<option value='"+result.BelongCode+"'>"+result.BelongName+"</option>");
					}
				},
				error:function(){
					location.href="/views/error/errorPage.jsp"
				}
			});
		}
	}
		
</script>
<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

<div class="container">
       <div class="col-sm-6 col-md-offset-3">
         <div class="header-title">
            <h1 class="wv-heading--title">
               	회원가입
            </h1>
         </div>
      </div>
      <div class="row">
         <div class="col-sm-6 col-md-offset-3">
            <div class="myform form ">
               <form action="/registerAction.do" method="post" name="login">
               	  <div class="form-group">
               	  	<label for="inputId">등급 / 소속</label>
               	  	<select name="grade" id="selectGrade" class="selectpicker ">
               	  		<option value="">Select Grade</option>
               	  		<option value="HA">홈페이지 관리자</option>
               	  		<option value="MA">제조사 관리자</option>
               	  		<option value="ME">제조사 엔지니어</option>
               	  		<option value="PE">협력사 엔지니어</option>
               	  	</select>
               	  	<select name="belong" id="selectBelong" class="selectpicker">
               	  		<option value="">Select Belong</option>
               	  		<option value="A_Partner">A협력사</option>
               	  		<option value="B_Partner">B협력사</option>
               	  		<option value="C_Partner">C협력사</option>
               	  		<option value="D_Partner">D협력사</option>
               	  		<option value="E_Partner">E협력사</option>
               	  	</select>
               	  </div>
                  <div class="form-group">
                  	<label for="inputId">아이디</label>
                  	 <table>
                  	 	<tr>
                  	 		<td width="90%"><input type="text" name="id" id="id" class="form-control my-input" placeholder="아이디를 입력하세요"></td>
                  	 		<td><button type="button" id="idCheck" class="btn btn-info">중복체크</button></td>
                  	 	</tr>
                  	 </table>
                  </div>
                  <div class="form-group">
                  	<label for="inputPwd">비밀번호</label>
                     <input type="password" name="password"  class="form-control my-input" id="password" placeholder="비밀번호를 입력하세요">
                     <p id="pwdMsg" style="color:red;"></p>
                     <span></span>
                  </div>
                  <div class="form-group">
                  	<label for="pwdCheck">비밀번호 확인</label>
                     <input type="password" name="pwdCheck"  class="form-control my-input" id="pwdCheck" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
                     <p id="pwdCheckMsg" style="color:red;"></p>
                     <span></span>
                  </div>
                  <div class="form-group">
                  	<label for="inputName">이름</label>
                     <input type="text" name="name"  class="form-control my-input" id="name" placeholder="이름을 입력하세요">
                  </div>
                  <div class="form-group">
                  	<label for="inputEmail">이메일</label>
                     <input type="text" name="email"  class="form-control my-input" id="email" placeholder="이메일을 입력하세요">
                  </div>
                  <div class="form-group">
                  	<label for="inputPhone">연락처</label>
                     <input type="text" name="phone" id="phone"  class="form-control my-input" placeholder="연락처를 입력하세요">
                  </div>
                  <div class="form-group">
                  	<label for="inputPhone">주소</label>
                     <input type="text" name="addr" id="addr"  class="form-control my-input" placeholder="주소를 입력하세요">
                  </div>
                  <div class="form-group">
                    <label>약관 동의</label>
                   	<div class="checkbox">
      				<label><input type="checkbox" value=""> <a href="#">이용약관</a>에 동의합니다.</label>
   					 </div>
                   </div>
                    <div class="text-center ">
                     <button type="submit" class=" btn btn-block send-button tx-tfm">Create Your Free Account</button>
                  	</div>
               </form>
              </div>
            </div>
         </div>
      </div>
      
<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>