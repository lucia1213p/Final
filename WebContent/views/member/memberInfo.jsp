<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.member.model.vo.*" %>
<%
	Member m = (Member)request.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보페이지</title>
<style>
	.container{
		padding-top:100px;
	}
	table{
		
	}
	table tr td:nth-child(1){
		text-align:center;
		font-size:18px;
		font-weight: bold;
	}
	table tr td:nth-child(2){
		font-size:18px;
	}
	
	
</style>
</head>
<body>
	<!-- 헤더 내비 -->
	<jsp:include page="/header.jsp" flush="false"/>
	
	
	<div class="container">
    <table class="table table-bordered table-hover">
		<tr>
			<td id="list" width="30%">회원번호</td>
			<td><%=m.getMemberNo()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">이름</td>
			<td><%=m.getMemberName()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">연락처</td>
			<td><%=m.getMemberPhone()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">주소</td>
			<td><%=m.getMemberAddr()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">이메일</td>
			<td><%=m.getMemberEmail()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">회원등급</td>
			<td><%=m.getMemberGrade()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">소속명</td>
			<td><%=m.getMemCode()%></td>
		</tr>
		<tr>
			<td id="list" width="30%">가입일</td>
			<td><%=m.getMemberJoin()%></td>
		</tr>
	</table>
	</div>
	
</body>
</html>