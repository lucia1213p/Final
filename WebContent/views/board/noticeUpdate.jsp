<%@page import="kr.or.tech.board.model.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.or.tech.board.model.vo.*"
	import = "kr.or.tech.member.model.vo.*"
	import="kr.or.tech.board.model.service.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	Notice n = (Notice)request.getAttribute("notice");
	System.out.println("등급:"+n.getNoticeGradeName());
	
	Member m = ((Member)request.getSession(false).getAttribute("member"));
	if(m.getMemberNo()!=n.getMemberNo()){
		response.sendRedirect("views/error/authorityError.jsp");
		
	}
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>공지사항 글 수정</title>

<style>
	.container{
		padding-top:50px;
	}
</style>
</head>
<body>

<script>
	//첨부파일 업로드
	$(document).ready(function(){
        $('#browsebutton :file').change(function(e){
            var fileName = e.target.files[0].name;
            $("#label").attr('placeholder',fileName);
        });
        $('#resetFile').onclick(function(){
        	document.getElementById("fileName").value = "";
        });
    });
</script>
<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

<div class="container">
	<div class="row">
		<form method="post" action="/noticeUpdate.do">
		<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
			<thead>
				<tr>
					<th rowspan="2" colspan="2" style="background-color : #eeeeee; text-align:center;"><h4>공지사항 작성</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%">카테고리</td>
					<td colspan="2"><%=n.getNoticeGradeName() %></td>
				</tr>
				<tr>
					<td width="20%">글 제목</td>
					<td colspan="2"><input type="text" class="form-control" placeholder="글 제목" name="noticeTitle" maxlength="50" value=<%=n.getNoticeTitle() %>></td>
					<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
					<input type="hidden" name="boardCode" value="<%=n.getBoardCode()%>"/>
				</tr>
				<tr>
					<td colspan="2"><textarea type="text" class="form-control" placeholder="글 내용" name="noticeContent" maxlength="2048" style="height:550px; resize: none;"><%=n.getNoticeContent()%></textarea></td>
				</tr>
				<tr>
					<td width="10%">첨부파일</td>
						<%if(n.getNoticeFile()==null){%>
						<td colspan="2">첨부파일 없음</td>
						<%}else{ %>
						<td colspan="2" name="fileName"><a href="/nFileDown.do?fileName=<%=n.getNoticeFile() %>"><%=n.getNoticeFile() %></a></td>
						<%} %>
				</tr>
			</tbody>
		</table>
		<input type="submit" class="btn btn-primary pull-right" value="글 수정">
		</form>
	</div>
</div>

<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>