<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.board.model.vo.*" 
         import = "kr.or.tech.member.model.vo.*"
	     import="java.util.ArrayList"
%>
<%
	
	Member m = ((Member)request.getSession(false).getAttribute("member"));
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

<title>공지사항 목록</title>

<style>
	.container{
		padding-top:50px;
	}
	.row{
		text-align:center;
		padding-bottom:20px;
	}
	
	table tr{
		text-align:center;
	}
	table{
		margin-top:40px;
	}
</style>
</head>
<body>
	  		
	  		
<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

<div class="container">
	<h3 class="text-center"><b>공지사항 게시판</b></h3>
    <table class="table table-bordered table-hover">
    <thead class="table">
        <tr>
            <th class="text-center">번호</th>
            <th class="text-center">제목</th>
            <th class="text-center">작성자</th>
            <th class="text-center">수정</th>
            <th class="text-center">삭제</th>
            <th class="text-center">조회수</th>
            <%if((m.getMemberGrade().equals("HA")||m.getMemberGrade().equals("MA"))) {%>
            <th class="text-center">선택</th>
            <%} %>
        </tr>
    </thead>
    	
    </table>
    <h4 class="text-center">게시글이 존재하지 않습니다</h4>
</div>
    <!-- pagination -->
    <div class="container">  
 		
	  <div class="row">
	  		<button type="button" id="writeBtn" class="btn btn-primary btn-sm active pull-right">글작성</button>
	  </div>
	  
	</div>

<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
	
<script>
	$('#writeBtn').click(function(){
		location.href="/views/board/noticeWrite.jsp";
	});
	
</script>

</body>
</html>