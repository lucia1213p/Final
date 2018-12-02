<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.member.model.vo.*" 
	import="java.util.ArrayList"
%>
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("waitList");
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

<style>
	.container{
		padding-top:100px;
	}
	.row{
		text-align:center;
	}
</style>

<title>회원가입 승인대기 목록</title>
</head>
<body>

<script>

	function approve(memberNo){
		if(window.confirm("회원 가입을 승인하시겠습니까?")){
			location.href="/memberApprove.do?memNo="+memberNo;
		}else{
			return false;
		}
	}
	
	function refuse(memberNo){
		if(window.confirm("회원 가입을 거부하시겠습니까?")){
			location.href="/memberRefuse.do?memNo="+memberNo;
		}else{
			return false;
		}
	}

</script>

<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

	<div class="container">
		<table class="table table-bordered table-hover">
		<thead class="table">
			<tr>
				<th>회원번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>회원등급</th>
				<th>소속부서</th>
				<th>가입일</th>
				<th>승인</th>
			</tr>
				<%if(!list.isEmpty()) {%>
					<%for(Member m : list) {%>
					<tr>
						<td><%=m.getMemberNo() %></td>
						<input type="hidden" name="memberNo" value="<%=m.getMemberNo() %>"/>
						<td><%=m.getMemberId() %></td>
						<td><%=m.getMemberName() %></td>
						<td><%=m.getMemberGrade() %></td>
						<td><%=m.getMemCode() %></td>
						<td><%=m.getMemberJoin() %></td>
						<td><button onclick="approve('<%=m.getMemberNo()%>');">승인</button>
						&nbsp;&nbsp;<button onclick="refuse('<%=m.getMemberNo()%>');">거부</button></td>
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
	
</body>
</html>