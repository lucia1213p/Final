<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.member.model.vo.*" 
	import="java.util.ArrayList"
%>
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("allMemberList");
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

<title>회원관리</title>

<style>

	.container{
		padding-top:100px;
	}
	.row{
		text-align:center;
	}
	<!--회원목록 테이블 스타일-->
	
	table tr:nth-child(1){
		background-color:#269BAF;
	}
	table tr{
		text-align:center;
	}
</style>

</head>
<body>

<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

	<div class="container">
	<h4><b>회원관리</b></h4>
		<table class="table table-bordered table-hover">
		<thead class="table">
			<tr>
				<th class="text-center">회원번호</th>
				<th class="text-center">아이디</th>
				<th class="text-center">이름</th>
				<th class="text-center">회원등급</th>
				<th class="text-center">소속부서</th>
				<th class="text-center">가입일</th>
				<th class="text-center">탈퇴</th>
			</tr>
			<%if(!list.isEmpty()) {%>
				<%for(Member m : list) {%>
				<tr>
						<td><a href="/adMemberInfo.do?memberNo=<%=m.getMemberNo() %>" id="memberNo"><%=m.getMemberNo() %></a></td>
						<td><%=m.getMemberId() %></td>
						<td><%=m.getMemberName() %></td>
						<td><%=m.getMemberGrade() %></td>
						<td><%=m.getMemCode() %></td>
						<td><%=m.getMemberJoin() %></td>
						<td><button type="button" onclick="delMember(<%=m.getMemberNo() %>)" class="btn btn-danger">탈퇴</button></td>
				</tr>
					<%} %>
				<%}else{%>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				<%} %>
		</thead>
		</table>
	</div>
	 <!-- pagination -->
    <div class="container">  
     <div class="row">              
		  <ul class="pagination">
		  	<li><a href="#">«</a></li>
		    <li><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">»</a></li>
		  </ul>
	  </div>
	  <div class="row">    
        <div class="col-sm-12 pull-center well">
        <form class="form-inline" action="#" method="POST">
            <center>  
                <select class="form-control">
                	<option>----------</option>
                    <option value="memberId">아이디</option>
                    <option value="memberName">회원이름</option>
                    <option value="memCode">소속코드</option>
                </select>
                    
                <div class="input-group custom-search-form">
                     <input type="text" class="form-control" placeholder="Search...">
                         <span class="input-group-btn">
                             <span class="input-group-btn">
			                  <button class="btn btn-default" id="writeBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
			              	</span>
                        </span>
                 </div>
            </center>
        </form>
    	</div>
	</div>
   </div>
   
<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
	
<script>
	function delMember(memberNo){
		if(confirm("회원을 탈퇴시키겠습니까?")){
			$.ajax({
				url:"/adMemberDelete.do",
				type:"post",
				data:{memberNo:memberNo},
				success:function(result){
					if(result==1){
						alert("탈퇴 완료");
						location.reload();
					}else{
						alert("탈퇴 실패");
					}
				},
				error:function(){
					location.href="/views/error/errorPage.jsp"	;
				}
			});
		}else{
			return false;
		}
	}
</script>
</body>
</html>