<%@page import="kr.or.tech.board.model.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.or.tech.board.model.vo.*"
	import="kr.or.tech.board.model.service.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	String boardCode=request.getParameter("boardCode");
	Notice notice = new BoardService().noticeInfo(noticeNo,boardCode);
	
	System.out.println(notice.getNoticeTitle());
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>공지사항 글작성</title>

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
		<form method="post" action="/noticeWrite.do" enctype="multipart/form-data">
		<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="2" style="background-color : #eeeeee; text-align:center;"><h4>공지사항 작성</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%">
						<select name="category" class="form-control">
							<option value="">-------------</option>
	                    	<option value="general">일반</option>
	                    	<option value="emergency">긴급</option>
	                    	<option value="important">중요</option>
                		</select>
                	</td>
					<td><input type="text" class="form-control" placeholder="글 제목" name="noticeTitle" maxlength="50" value=<%=notice.getNoticeTitle() %>></td>
				</tr>
				<tr>
					<td colspan="2"><textarea type="text" class="form-control" placeholder="글 내용" name="noticeContent" maxlength="2048" style="height:550px; resize: none;"><%=notice.getNoticeContent()%></textarea></td>
				</tr>
				<tr>
					<td width="10%">첨부파일</td>
					<td>
						<div class="input-group">
					        <label id="browsebutton" class="btn btn-default input-group-addon" for="my-file-selector" style="background-color:white">
					            <input id="my-file-selector" type="file" accept=".jpg, .gif, .png, .zip" id="fileName" name="fileName" style="display:none; ">
					            <span class="glyphicon glyphicon-folder-open"></span>
                        		<span class="image-preview-input-title">Browse</span>
					        </label>
					        <input id="label" type="text" class="form-control" readonly="">
					    </div>
					  <!--   <input type="button" id="resetFile" value="취소"> -->
			       </td>
				</tr>
			</tbody>
		</table>
		<input type="submit" class="btn btn-primary pull-right" value="글작성">
		</form>
	</div>
</div>

<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>