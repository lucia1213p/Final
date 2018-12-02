<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "kr.or.tech.board.model.vo.*" 
	import="java.util.ArrayList"
%>

<%
	ArrayList<ShrTech> list=(ArrayList<ShrTech>)request.getAttribute("shrList");
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

<title>기술공유 게시판</title>

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
</style>

<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

</head>
<body>

<div class="container">
    <table class="table table-bordered table-hover">
    <thead class="table">
        <tr>
            <th class="text-center">번호</th>
            <th class="text-center">제목</th>
            <th class="text-center">작성자</th>
            <th class="text-center">수정</th>
            <th class="text-center">삭제</th>
            <th class="text-center">채택상태</th>
            <th class="text-center">조회수</th>
        </tr>
    </thead>
    <tbody>
    	<%if(!list.isEmpty()) {%>
    		<%for(ShrTech shr : list) {%>
            <tr>
                <td><a href="/shareTechInfo.do?shrTechNo=<%=shr.getShareNo()%>&boardCode=<%=shr.getBoardCode()%>"><%=shr.getShareNo() %></a></td>
                <td><%=shr.getShareTitle() %></td>
                <td><%=shr.getMemberId() %></td>
                <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a></td>
                <td class="text-center"><a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td>
           		<td><%=shr.getAddoptName() %></td>
           		<td><%=shr.getShareHits() %></td>
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
	 </tbody>
    </table>
</div>
    <!-- pagination -->
    <div class="container">  
 		
	  <div class="row">
	  		<button type="button" id="writeBtn" class="btn btn-primary btn-sm active pull-right">글작성</button>
	  </div>
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
                    <option>작성자</option>
                    <option>제목</option>
                </select>
                    
                <div class="input-group custom-search-form">
                     <input type="text" class="form-control" placeholder="Search...">
                         <!-- 
                         	<span class="input-group-btn">
			                  <button class="btn btn-default" id="writeBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
                        	</span>
                          -->
                          <span class="input-group-btn">
			                  <button class="btn btn-default" id="writeBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
                          </span>
                </div>
            </center>
        </form>
    	</div>
	</div>
   </div>
   
   <script>
	   $('#writeBtn').click(function(){
			location.href="/views/board/shareTechWrite.jsp";
		});
   </script>
   
   
   <!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>