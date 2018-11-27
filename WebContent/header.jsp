<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.member.model.vo.*" %>
<%
		session=request.getSession(false);
		Member member=(Member)session.getAttribute("member");
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>헤더</title>

<style>

	<!-- jumbotron -->
	.jumbotron{
    	margin-bottom: 0px;
    	padding-top:15px;
	}
	.jumbotron h1{
		margin-top:10px;
	}
	.jumbotron p{
		margin-bottom: 0px;
	}
	@media screen and (min-width: 768px)
	.jumbotron {
	    padding : 0;
	}
	
	/* 마이페이지 사이드바 */
	body {
    font-family: "Lato", sans-serif;
    transition: background-color .5s;
	}
	
	.sidenav {
	    height: 100%;
	    width: 0;
	    position: fixed;
	    z-index: 1;
	    top: 0;
	    left: 0;
	    background-color: #111;
	    overflow-x: hidden;
	    transition: 0.5s;
	    padding-top: 60px;
	}
	
	.sidenav a {
	    padding: 8px 8px 8px 32px;
	    text-decoration: none;
	    font-size: 17px;
	    color: #818181;
	    display: block;
	    transition: 0.3s;
	    font-weight:bold;
	}
	
	.sidenav a:hover {
	    color: #f1f1f1;
	}
	
	.sidenav .closebtn {
	    position: absolute;
	    top: 0;
	    right: 25px;
	    font-size: 36px;
	    margin-left: 50px;
	}
	
	
	@media screen and (max-height: 450px) {
	  .sidenav {padding-top: 15px;}
	  .sidenav a {font-size: 18px;}
	}
	
	/* 관리자 사이드바  */
	.sidenav_admin {
	    height: 100%;
	    width: 0;
	    position: fixed;
	    z-index: 1;
	    top: 0;
	    left: 0;
	    background-color: #111;
	    overflow-x: hidden;
	    transition: 0.5s;
	    padding-top: 60px;
	}
	
	.sidenav_admin a {
	    padding: 8px 8px 8px 32px;
	    text-decoration: none;
	    font-size: 17px;
	    color: #818181;
	    display: block;
	    transition: 0.3s;
	    font-weight:bold;
	}
	
	.sidenav_admin a:hover {
	    color: #f1f1f1;
	}
	
	.sidenav_admin .closebtn {
	    position: absolute;
	    top: 0;
	    right: 25px;
	    font-size: 36px;
	    margin-left: 50px;
	}

		
</style>
</head>
<body>

	<script>
			function openNav() {
				document.getElementById("mySidenav_admin").style.width = "0";
			    document.getElementById("navbar").style.marginLeft = "0";
			    document.getElementById("mySidenav").style.width = "250px";
			    document.getElementById("navbar").style.marginRight = "250px";
			}
			
			function closeNav() {
			    document.getElementById("mySidenav").style.width = "0";
			    document.getElementById("navbar").style.marginRight= "0";
			}
			
			function adminNav(){
				document.getElementById("mySidenav").style.width = "0";
			    document.getElementById("navbar").style.marginRight= "0";
				document.getElementById("mySidenav_admin").style.width = "250px";
			    document.getElementById("navbar").style.marginLeft = "250px";
			}
			function closeNav_admin(){
				document.getElementById("mySidenav_admin").style.width = "0";
			    document.getElementById("navbar").style.marginLeft = "0";
			}
    </script>
	
	  <!--jumbotron-->
	  <!--
	  <div class="jumbotron">
	    <h1>Bootstrap Tutorial</h1> 
	    <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing
	    responsive, mobile-first projects on the web.</p> 
	  </div>
	  -->
	   
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="/index.jsp">기술지원홈페이지</a>
	    </div>
	    <%if(member!=null) {%>
	    
	    <div id="mySidenav" class="sidenav">
		  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		  <a href="#"></a>
		  <a href="#">비밀번호 변경</a>
		  <a href="/memberInfo.do">회원정보</a>
		  <a href="#">회원탈퇴</a>
		  <a href="#">기술지원게시판 활동내역</a>
		  <a href="#">기술공유게시판 활동내역</a>
		</div>
		
		<div id="mySidenav_admin" class="sidenav_admin">
		  <a href="javascript:void(0)" class="closebtn" onclick="closeNav_admin()">&times;</a>
		  <a href="#"></a>
		  <a href="/memberWait.do">회원가입 승인</a>
		  <%if(member.getMemberGrade().equals("HA")){%>
		  <a href="/memberAllList.do">회원관리</a>
		  <%} %>
		  <a href="#">기술지원게시판 현황</a>
		</div>
	    <ul class="nav navbar-nav" id="navbar">
	      <li><a href="/noticeList.do">공지사항</a></li>
	      <li><a href="#">기술공유</a></li>
	      <li><a href="#">기술지원</a></li>
	      <%if((member.getMemberGrade().equals("HA") || member.getMemberGrade().equals("MA"))) {%>
	      <li><a href="#"><span onclick="adminNav()">관리자페이지</span></a></li>
	      <%} %>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a><span class="glyphicon glyphicon-user" onclick="openNav()">MyPage</span></a></li>      
	      <li><a href="/logoutAction.do"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	    </ul>
	    <%}else{%>
	    	<ul class="nav navbar-nav navbar-right">
		      <li><a href="/views/member/register.jsp"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
		      <li><a href="/login.jsp"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
	    	</ul>
	    <%}%>
	  </div>
	</nav>
	
</body>
</html>