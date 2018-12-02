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

<title>로그인 페이지</title>
<style>
	div input{
		margin-bottom:20px;
	}
	#login{
		padding-top:100px;
	}
	#login .form-wrap{
		width:30%;
		margin: 0 auto;
	}
	#login h1{
		color:#1fa67b;
		font-align:center;
		font-weight:bold;
		padding-bottom:20px;
	}
	.btn-custom {
    color: #fff;
	background-color: #1fa67b;
	}
	#login .form-group {
	    margin-bottom: 25px;
	}
	
	#login .btn.btn-custom {
	    font-size: 14px;
		margin-bottom: 20px;
	}
	#login .forget {
	    font-size: 13px;
		text-align: center;
		display: block;
	}
	#login-form{
		padding-top:30px;
	}
	/*    --------------------------------------------------
		:: Inputs & Buttons
		-------------------------------------------------- */
	.form-control {
	    color: #212121;
	}
	.btn-custom {
	    color: #fff;
		background-color: #1fa67b;
	}
	.btn-custom:hover,
	.btn-custom:focus {
	    color: #fff;
	}
	
	/*    --------------------------------------------------
	    :: Footer
		-------------------------------------------------- */
	#footer {
	    color: #6d6d6d;
	    font-size: 12px;
	    text-align: center;
	}
	#footer p {
	    margin-bottom: 0;
	}
	#footer a {
	    color: inherit;
	}
</style>
</head>
<body>
	<!-- 헤더 내비 -->
	<jsp:include page="/header.jsp" flush="false" />
	<div id="content">
		<section id="login">
		<div class="container-fluid">
			 <div class="form-wrap">
	          <center><h3>로그인</h3></center>
	              <form role="form" action="loginAction.do" method="post" id="login-form" autocomplete="off">
	                  <div class="form-group">
	                      <label for="id" class="sr-only">ID</label>
	                      <input type="text" name="id" id="id" class="form-control" placeholder="아이디를 입력하세요">
	                  </div>
	                  <div class="form-group">
	                      <label for="key" class="sr-only">Password</label>
	                      <input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력하세요">
	                  </div>
	                  <button id="btn-login" class="btn btn-custom btn-lg btn-block" onclick="return logCheck();">Log in</button>
	              </form>
		              <a href="javascript:;" class="forget" data-toggle="modal" data-target=".forget-modal">Forgot your password?</a>
		              <a href="/views/member/register.jsp" class="register" >Register</a>
	              <hr>
		      </div>
			</div>
		</section>
	</div>
	<script>
		function logCheck(){
			var userId = document.getElementById("id").value;
			var userPwd = document.getElementById("password").value;
			
			if(userId=="")
				{
					alert("ID를 입력해주세요");
					return false;
				}else if(userPwd=="")
				{
					alert("비밀번호를 입력해주세요");
					return false;
				}else
					{return true;}
		}
	</script>
	
	<!-- 푸터 내비 -->
	<jsp:include page="/footer.jsp" flush="false" />
	
</body>

</html>