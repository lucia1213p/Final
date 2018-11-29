<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.or.tech.board.model.vo.*"
		import = "kr.or.tech.member.model.vo.*"
		import="java.util.ArrayList"
 %>
 <%
 	ShrTech shr = (ShrTech)request.getAttribute("shrTech");
 	ArrayList<ShrTechAnswer> answerList=(ArrayList<ShrTechAnswer>)request.getAttribute("answerList");
 	Member m = ((Member)request.getSession(false).getAttribute("member"));
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/comment.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>기술공유 게시글</title>
<style>

.container{
		padding-top:50px;
}

table tr td:nth-child(1){
	font-weight: bold; 
}
#answerText{

	color:#1294AB;
	font-weight:bold;
}
.answer{

	border:1px solid #578100;
	margin-top:50px;
}
.answer-wrap{
	padding:20px;
}
.top-answer{
 height:30%;
}
.top-left{
	float:left;
	width:70%;
}
.top-right{
	float:right;
	width:30%;
}


</style>

</head>
<body>
	<!-- 헤더 내비 -->
	<jsp:include page="/header.jsp" flush="false" />
	
	<!-- 게시글 정보 -->
	 <div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align :center;">기술공유 게시글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width :20%;">제목</td>
						<td colspan="2"><%= shr.getShareTitle()%></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= shr.getMemberId()%></td>
					</tr>
					<tr>
						<td>작성일자 </td>
						<td colspan="2"><%= shr.getShareDate()%></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2"><div style="min-height: 200px; text-align: left;"><%=shr.getShareCont()%></div></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="2"><a href="/sFileDown.do?fileName=<%=shr.getFileName()%>"><%=shr.getFileName() %></a></td>
					</tr>
				</tbody>
			</table>
				<a href="/shareTechList.do" class="btn btn-primary btn-sm active">목록</a>
				<!-- (수정) 글 작성자나 관리자가 아니면 수정,삭제 못하게 -->
				<a href="#" class="btn btn-primary btn-sm active">수정</a>
				<a href="#" class="btn btn-primary btn-sm active">삭제</a>
		</div>
	</div>
	
	<!-- 기술공유 답변 입력-->
	<div class="container">
		<div class="row" id="Answer">
			<form action="/sTAnswerWrite.do" method="post">
				<%if(session.getAttribute("member")!=null){%>
					<div class="commentWrite">
						<h4><b><%=m.getMemberId()%></b>님의 답변을 기다리고 있어요</h4>
						<textarea id="answer" name="answer" class="form-control" rows="3" style="resize:none; min-height:200px; margin:0px;"></textarea>
						<input type="hidden" name="boardCode" value="<%=shr.getBoardCode() %>"/>
						<input type="hidden" name="noticeNo" value="<%=shr.getShareNo()%>"/>
						<input type="submit" class="btn pull-right" value="답변등록"/>
					</div>
				<%}else{ %>
					<div class="commentWrite">
						<h3>로그인 후 답변 작성이 가능합니다</h3>
					</div>
				<%}%>
			</form>
		</div>
		<!-- 답변 리스트 -->
		<div class="answerList">
		<h3 id="answerText">↓ANSWER</h3>
		  <%if(!answerList.isEmpty()) {
		    for(ShrTechAnswer sta:answerList){ %>
		      <div class="answer">
		 		<div class="answer-wrap">
		 			<div class="top-answer">
		 			  <div class="top-left">
		 				<h4><b><%=sta.getMemberId() %></b>님의 답변</h4>
		 				<%=sta.getAnswDate()%>
		 			  </div>
		 			  <div class="top-right">
		 			  	<%if(sta.getAnswAddopt().equals("N")) {%>
		 			  		<img src="/img/adopt.png" width="50" height="50">
		 			  	<%}%>
		 			  </div>
		 			  </div>
		 			  <div class="content">
		 			  	<hr>
						<p class="comment-text" style="min-height:150px;"><%=sta.getAnswCont() %></p>
		 			  </div>
					 <div class="bottom-comment">
						<ul class="comment-actions">
							<li class="complain"><a href="#" id="updateComment" onclick="updateCmt()">수정</a></li>
							<li class="reply"><a href="#" onclick="delComment()">삭제</a></li>
						</ul>
					</div>
		 		</div>
		 	  </div>
		 	  <%}
		    }%>
	    </div>
	</div>
</body>
</html>