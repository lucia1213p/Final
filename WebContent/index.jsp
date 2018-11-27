<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
</head>
<body>
	<%
		session=request.getSession(false);
		Member member=(Member)session.getAttribute("member");
		if(member!=null){ //로그인 상태일 경우
	%>
		<jsp:include page="/main.jsp" flush="false" />
	<% 		
		}else{ //로그인 하지 않았을 경우
	%>	
		<jsp:include page="/login.jsp" flush="false" />
	<%} %>

</body>
</html>