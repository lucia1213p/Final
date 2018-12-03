<%@page import="kr.or.tech.board.model.service.BoardService"%>
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
 	
 	//채택된 답변 여부 확인
 	int adoptCheck=0;
 	if(!answerList.isEmpty()) {
	 	for(ShrTechAnswer st:answerList){
	 		if(st.getAnswAddopt().equals("Y")){
	 			adoptCheck++;
	 		}
	 	}
 	}
 	
 	
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

<title>기술공유 게시글</title>

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
	 </div>
		<!-- 답변 리스트 -->
  	<section class="content-item" id="comments">
       <div class="container">      
		<div class="row">
                <h3 id="answerText">↓ ANSWER</h3>
                <hr>
                <!-- COMMENT 1 - START -->
                 <%if(!answerList.isEmpty()) {
				    for(ShrTechAnswer sta:answerList){ %>
		                <div class="media">
		                    <div class="media-body">
		                       <div class="body-top">
		                       	<h4 class="media-heading"><b><%=sta.getMemberId() %></b> 님의 답변</h4>
		                        <div class="body-right">
			                        <%if(sta.getAnswAddopt().equals("Y")) {%>
				 			  			<img src="/img/checkMark.png" class="media-check" width="50" height="50">
				 			  		<%}%>
			 			  		</div>
		                       </div>
		                       <div class="body-content">
			                        <p class="comment-text" style="min-height:150px;"><%=sta.getAnswCont() %></p>
			                        <ul class="list-unstyled list-inline media-detail pull-left">
			                            <li><i class="fa fa-calendar"></i><%=sta.getAnswDate() %></li>
			                        </ul>
			                        <ul class="list-unstyled list-inline media-detail pull-right">
			                            <li class="complain"><a href="#" id="updateComment" onclick="updateCmt()">수정</a></li>
										<li class="reply"><a href="#" onclick="delComment()">삭제</a></li>
			                        </ul>
		                       </div>
		                    </div>
		                    <!-- 채택버튼 채택된 글이 없을 경우  -->
		              		<%if(adoptCheck==0) {%>
			                <div class="adopt">
			                	<input type='button' onclick='adoptAnswer(<%=sta.getAnswNo()%>,<%=sta.getShrNo() %>,<%=sta.getMemberNo() %>)' class="btn btn-success pull-right" value="채택하기">
			                </div>
			                <%} %>
		                </div>
	                 <%}
			    }%>
           </div>
	</div>
 </section>
	
<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
<script>
	function adoptAnswer(answNum,shrNo,memNo){
		console.log("스크립트 들어옴");
		if(confirm("답변을 채택하시겠습니까?")){
			$.ajax({
				url:"/shareAnswerAdopt.do",
				type:"post",
				data:{answNum:answNum,shrNo:shrNo,memNo:memNo},
				success: function(result){
					if (result==1) {
						alert("채택되었습니다");
						location.reload();
					}else{
						alert("채택 실패");
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
</body>
</html>