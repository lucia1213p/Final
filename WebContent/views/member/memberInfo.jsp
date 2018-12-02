<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.tech.member.model.vo.*" %>
<%
	Member m = (Member)request.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보페이지</title>
<style>
	.container{
		padding-top:100px;
	}
	table{
		
	}
	table tr td:nth-child(1){
		text-align:center;
		font-size:18px;
		font-weight: bold;
	}
	table tr td:nth-child(2){
		font-size:18px;
	}
	
	/* 테이블 css  */
	
	    /*회원테이블*/
    .memberTbl{
            border:1px solid #dedede;
            border-right:0;
            font-size:15px;
            margin:40px auto 12px;
            line-height:35px;
            width:900px;
            text-align: center;
            
        }
    
    .memberTbl tr{
        border:1px solid black;
    }
    .content_wrap .searchArea{
        height:10%;
        padding: 30px;
    }
    .memberInfo_table{
        border-width: 1px 0;
        border-style: solid;
        border-color: #c7c7c7;
    }
    .memberInfo_table table{
        width: 100%;
        border-spacing: 0;
    }
    .memberInfo_table th{
        
        border-bottom: 1px dashed #dedede;
        padding: 30px 0 15px 20px;
        text-align: left;
        line-height: 40px;
        letter-spacing: -1px;
        color: black;
        font-size: 16px;
        width: 200px;
    }
    .memberInfo_table td{
        text-align: center;
        border-bottom: 1px dashed #dedede;
        font-size: 14px;
        text-align: left;
    }
    .memberInfo_table input[type="text"]{
        height:25px;
        font-size:14px;
    }
    
    #memUpdate{
        padding-top:40px;
        text-align: center;
    }
    /*버튼 스타일*/
    button{
      background:#4ABFD3;
      color:#fff;
      border:none;
      position:relative;
      height:60px;
      font-size:1.6em;
      font-weight: 600;
      padding:0 2em;
      cursor:pointer;
      transition:800ms ease all;
      outline:none;
    }
    button:hover{
      background:#fff;
      color:#4ABFD3;
    }
    button:before,button:after{
      content:'';
      position:absolute;
      top:0;
      right:0;
      height:2px;
      width:0;
      background: #4ABFD3;
      transition:400ms ease all;
    }
    button:after{
      right:inherit;
      top:inherit;
      left:0;
      bottom:0;
    }
    button:hover:before,button:hover:after{
      width:100%;
      transition:800ms ease all;
    }
    
</style>
</head>
<body>
	<!-- 헤더 내비 -->
	<jsp:include page="/header.jsp" flush="false"/>
	
	
	<div class="container">
           <form action ="/memberUpdate.do" method="post" id="updateForm">
               <div class="main_area">
                   <div class="memberInfo_table">
                    <table>
                        <tbody>
                            <tr>
                                <th>회원번호</th>
                                <td><%=m.getMemberNo() %></td>
                                <input type="hidden" name="member_no" value="<%=m.getMemberNo() %>"/>
                            </tr>
                            <tr>
                                <th>아이디</th>
                                <td><%=m.getMemberId() %></td>
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td><%=m.getMemberName()%></td>
                            </tr>
                            <tr>
                                <th>연락처</th>
                                <td><input type="text" id="phone" name="phone" value="<%=m.getMemberPhone()%>"></td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td><input type="text" id="address" name="address" value="<%=m.getMemberAddr()%>"></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input type="text" id="email" name="email" value="<%=m.getMemberEmail()%>"></td>
                            </tr>
                            <tr>
								<th>회원등급</th>
								<td><%=m.getMemberGrade()%></td>
							</tr>
                            <tr>
								<th>소속명</th>
								<td><%=m.getMemCode()%></td>
							</tr>
							<tr>
								<th>가입일</th>
								<td><%=m.getMemberJoin()%></td>
							</tr>
                        </tbody>
                    </table>
                </div>
                   <div id="memUpdate">
                    <button id="updateBtn" onclick = "return updateSubmit();" >회원정보 수정</button>
                   </div>
               </div>
               </form>

	</div>

<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
	
<script>
	function updateSubmit(){
		var phone = document.getElementById("phone").value;
		var address = document.getElementById("address").value; 
		var email = document.getElementById("email").value; 
		
		if(phone==""){
			alert("연락처를 입력하세요 ");
			return false;
		}else if(address==""){
			alert("주소를 입력하세요");
			return false;
		}else if(!(/^[a-z0-9]{4,20}@/.test(email))||email==""){
			alert("이메일을 입력하세요");
			return false;
		}else{
			document.getElementById("updateForm").submit();
			return true;
		}
		
	}
</script>
</body>
</html>