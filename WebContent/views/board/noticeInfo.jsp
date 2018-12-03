<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.or.tech.board.model.vo.*"
		import = "kr.or.tech.member.model.vo.*"
		import="java.util.ArrayList"
 %>
<% 
	Notice n =(Notice)request.getAttribute("notice");
	
	ArrayList<NComment> ncList = (ArrayList<NComment>)request.getAttribute("nComment");
	
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


<title>공지사항 게시글 페이지</title>

<style>

.container{
		padding-top:50px;
}

table tr td:nth-child(1){
	font-weight: bold; 
}
</style>

</head>

<body>
	<!-- 헤더 내비 -->
	<jsp:include page="/header.jsp" flush="false" />
	
	<div class="container">
		<div class="row">
		  <form action="/views/board/noticeUpdate.jsp" method="post">
			<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align :center;">공지사항 게시글</th>
					</tr>
				</thead>
				
				<tbody id="titleInfo">
					<tr>
						<td style="width :20%;">제목</td>
						<td colspan="2" name="title"><%= n.getNoticeTitle()%></td>
						<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>">
					</tr>
					
					<tr>
						<td>작성자</td>
						<td colspan="2" name="writer"><%= n.getMemberName()%></td>
						
					</tr>
					<tr>
						<td>작성일자 </td>
						<td colspan="2" name="date"><%= n.getNoticeDate()%></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" name="contents"><div style="min-height: 200px; text-align: left;"><%=n.getNoticeContent()%></div></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<%if(n.getNoticeFile()==null){%>
						<td colspan="2"></td>
						<%}else{ %>
						<td colspan="2" name="fileName"><a href="/nFileDown.do?fileName=<%=n.getNoticeFile() %>"><%=n.getNoticeFile() %></a></td>
						<%} %>
					</tr>
				</tbody>
			  </table>
			</form>
				<a href="/noticeList.do" class="btn btn-primary btn-sm active">목록</a>
				<!-- (수정) 글 작성자나 관리자가 아니면 수정,삭제 못하게 -->
				<a href="/views/board/noticeUpdate.jsp?noticeNo=<%=n.getMemberNo()%>&boardCode=<%=n.getBoardCode() %>" class="btn btn-primary btn-sm active">수정</a>
				<a href="/noticeDelete.do?noticeNo=<%=n.getMemberNo()%>" class="btn btn-primary btn-sm active">삭제</a>
		</div>
	</div>
	<div class="container">
		<div class="row" id="comment">
			<form action="/nCommentWrite.do" method="post">
				<%if(session.getAttribute("member")!=null){%>
					<div class="commentWrite">
						<textarea id="comment" name="comment" class="form-control" rows="3" style="resize:none;margin:0px;"></textarea>
						<input type="hidden" name="boardCode" value="<%=n.getBoardCode() %>"/>
						<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
						<button id="cmtBtn" class="btn pull-right" onclick="return cmtCheck();">댓글작성</button>
					</div>
				<%}else{ %>
					<div class="commentWrite">
						<h3>로그인 후 댓글 작성이 가능합니다</h3>
					</div>
				<%}%>
			</form>
		</div>
		<div class="comment_list">
			<%if(!ncList.isEmpty()) {%>
				<%for(NComment nc : ncList) {%>
					<div class="comments">
						<div class="comment-wrap">
							<div style="width:20%; float:left;" class="name">
								<div><b><%=nc.getMemberName()%></b></div>
							</div>
							<div style="width:90%; float:left;" class="comment-block">
								<p class="comment-text"><%=nc.getCommCont() %></p>
								<div class="bottom-comment">
									<div class="comment-date"><%=nc.getCommDate() %></div>
									<%if(m.getMemberId().equals(nc.getMemberName())) {%>
										<ul class="comment-actions">
											<li class="complain"><a href="#" id="updateComment" >수정</a></li>
											<li class="reply"><a href="#" onclick="delComment(<%=nc.getCommNo()%>)">삭제</a></li>
										</ul>
								 	<%} %>
								</div>
							</div>
						</div>
					</div>
				<%} %>
			  <%}else{ %>
			  		<h3>댓글이 없습니다.</h3>
			  <%} %>
		</div>
	</div>
	<script>
		//댓글입력 확인
		function cmtCheck(){
			var comment = document.getElementById("comment").value;
			if(comment==""){
				alert("댓글을 입력해주세요");
				return false;
			}else{
				{return true;}
			}
		}
		//댓글 수정
		function updateCmt(cmtNum){
			 window.name = "parentForm";
	         window.open("updateComment.do?num="+comment_num,
	                        "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
		}
		function delComment(cmtNum){
			if(confirm("댓글을 삭제하시겠습니까?")){
				$.ajax({
					url:"/nCommentDelete.do",
					type:"post",
					data:{cmtNo:cmtNum},
					success: function(result){
						if (result==1) {
							alert("삭제되었습니다");
							location.reload();
						}else{
							alert("삭제 실패");
						}
					},
					error:function(){
						location.href="/views/error/errorPage.jsp"
					}
				});
			}else{
				return false;
			}			
	   }
	</script>
<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>