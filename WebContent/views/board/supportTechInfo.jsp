<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.or.tech.board.model.vo.*"
		import = "kr.or.tech.member.model.vo.*"
		import="java.util.ArrayList"
 %>
<% 
	SupportTech spt=(SupportTech)request.getAttribute("support");

	ArrayList<TComment> answerList=(ArrayList<TComment>)request.getAttribute("answerList");
	
	Member m = ((Member)request.getSession(false).getAttribute("member"));
	
	ArrayList<SptCategory> categoryList=(ArrayList<SptCategory>)request.getAttribute("categoryList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/comment.css">
<link rel="stylesheet" href="/css/Answer.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>기술 지원 게시글</title>

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
	
	<!-- 게시글 정보 -->
	
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; text-align :center;">기술지원 게시글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width :20%;"><b>제목</b></td>
						<td colspan="3"><%=spt.getTitle() %></td>
					</tr>
					<tr>
						<td style="width :20%;"><b>협력사 담당자</b></td>
						<td><%=spt.getPartnerId() %></td>
						<td style="width :20%;"><b>제조사 담당자</b></td>
						<%if(spt.getmClerkId()!=null) {%>
						<td><%=spt.getmClerkId() %></td>
						<%}else{ %>
						<td>담당자 없음</td>
						<%} %>
					</tr>
					<tr>
						<td><b>작성일자 </b></td>
						<td><%=spt.getDate() %></td>
						<td><b>진행상황</b></td>
						<td><%=spt.getStateName() %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3"><div style="min-height: 200px; text-align: left;"><%=spt.getContents() %></div></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<%if(spt.getFileName()==null){%>
						<td colspan="3">첨부파일 없음</td>
						<%}else{ %>
						<td colspan="3" name="fileName"><a href="/tFileDown.do?fileName=<%=spt.getFileName() %>"><%=spt.getFileName() %></a></td>
						<%} %>
					</tr>
				</tbody>
			</table>
				<a href="/supportTechList.do" class="btn btn-primary btn-sm active">목록</a>
				<!-- (수정) 글 작성자나 관리자가 아니면 수정,삭제 못하게 -->
				<a href="#" class="btn btn-primary btn-sm active">수정</a>
				<a href="#" class="btn btn-primary btn-sm active">삭제</a>
				<%if(spt.getmClerkId()==null && m.getMemCode().equals("ABC Manufacturer")){%>
					<a href="/selectSptMclerk.do?sptNo=<%=spt.getBoardNo()%>&boardCode=<%=spt.getBoardCode() %>" onclick="select" class="btn btn-success btn-sm active pull-right ">담당자선택 </a>
				<%} %>
		</div>
	</div>
	<!-- 기술지원 답변 -->
	
	<div class="container">
		<div class="row" id="comment">
			<form action="/sptCommentWrite.do" method="post">
				<%if(session.getAttribute("member")!=null){%>
					<div class="commentWrite">
						<select name="category" class="form-control">
							<%if(categoryList!=null) {
								for(SptCategory sc:categoryList){
							%>
								<option value=<%=sc.getCategoryCode()%>><%=sc.getCategoryName() %></option>
							<%}
							}else {%>
								<option value="">----------</option>
							<%} %>
	                
                		</select>
						<textarea id="comment" name="comment" class="form-control" rows="3" style="resize:none;margin:0px;"></textarea>
						<input type="hidden" name="boardCode" value="<%=spt.getBoardCode() %>"/>
						<input type="hidden" name="sptTechNo" value="<%=spt.getBoardNo()%>"/>
						<button id="cmtBtn" class="btn pull-right" onclick="return cmtCheck();">댓글작성</button>
					</div>
				<%}else{ %>
					<div class="commentWrite">
						<h3>로그인 후 댓글 작성이 가능합니다</h3>
					</div>
				<%}%>
			</form>
		</div>
		<div class="row">
			<div class="comment_list">
				<%if(!answerList.isEmpty()) {%>
					<%for(TComment tc : answerList) {%>
						<div class="comments">
							<div class="comment-wrap">
								<div style="width:20%; float:left;" class="name">
									<div><b><%=tc.getMemberName()%></b></div>
								</div>
								<div style="width:100%; float:left;" class="comment-block">
									<p id="comment-text" style="min-height:70px;"class="comment-text"><%=tc.getCommCont() %></p>
									<input type="hidden" name="comment-update" id="comment-update" style="height:50;" value="<%=tc.getCommCont() %>"/>
									<div class="bottom-comment">
										<div class="comment-date"><%=tc.getCommDate() %></div>
										<%if(m.getMemberId().equals(tc.getMemberName())) {%>
											<ul class="comment-actions">
												<li class="complain"><a href="#" onclick="updateComment()" >수정</a></li>
												<li class="reply"><a href="#" onclick="delComment()">삭제</a></li>
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
		
	</div>
	
	<!-- 푸터 내비 -->
	<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>