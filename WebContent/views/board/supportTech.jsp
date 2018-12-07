<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "kr.or.tech.board.model.vo.*" 
		import="java.util.ArrayList"
		import = "kr.or.tech.member.model.vo.*"
 %>
 <%
 SptPageData spd= (SptPageData)request.getAttribute("sptPageData");
 
 ArrayList<SupportTech> list=spd.getList();
 String pageNavi = spd.getPageNavi();
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
<title>기술지원 게시판</title>

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
	<h3 class="text-center"><b>기술지원 게시판</b></h3>
    <table class="table table-bordered table-hover">
    <thead class="table">
        <tr>
            <th class="text-center">번호</th>
            <th class="text-center">제목</th>
            <th class="text-center">작성자</th>
            <th class="text-center">진행상황</th>
            <th class="text-center">조회수</th>
        </tr>
    </thead>
    <tbody>
    	<%if(!list.isEmpty()) {%>
    		<%for(SupportTech spt : list) {%>
            <tr>
                <td><a href="/supportTechInfo.do?sptTechNo=<%=spt.getBoardNo()%>&boardCode=<%=spt.getBoardCode()%>"><%=spt.getBoardNo() %></a></td>
                <td><%=spt.getTitle() %></td>
                <td><%=spt.getPartnerId() %></td>
                <td><%=spt.getStateName() %></td>
           		<td><%=spt.getHits() %></td>
            </tr>
         	<%} %>
		<%}else{%> 
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>	
		<%} %>
	 </tbody>
    </table>
</div>
    <!-- pagination -->
    <div class="container">  
 		
	  <div class="row">
	 <% if(m.getMemberGrade().equals("PE")){ %>
	  		<button type="button" id="writeBtn" class="btn btn-primary btn-sm active pull-right">글작성</button>
	  <%} %>
	  </div>
	   <div class="row">              
		  <ul class="pagination">
		  	<li><%=pageNavi%></li>
		  </ul>
	  </div>
	  
	   <div class="row">    
        <div class="col-sm-12 pull-center well">
        <form class="form-inline" action="#" method="POST">
            <center>  
                <select class="form-control">
                	<option>----------</option>
                    <option>작성자</option>
                    <option>제목</option>
                </select>
                <input type="text" name="search" class="form-control" placeholder="Search...">
		        <button class="btn btn-default form-control" id="writeBtn" onclick="document.getElementById('searchBtn').submit();" type="button"><span class="glyphicon glyphicon-search"></span></button>
            </center>
        </form>
    	</div>
	</div>
   </div>
   
   <script>
	   $('#writeBtn').click(function(){
			location.href="/views/board/supportTechWrite.jsp";
		});
   </script>
   
   
   <!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
</body>
</html>