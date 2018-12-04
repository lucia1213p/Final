<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<th colspan="4" style="background-color: #eeeeee; text-align :center;">기술공유 게시글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width :20%;">제목</td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td style="width :20%;">협력사 담당자</td>
						<td>Apartner</td>
						<td style="width :20%;">제조사 담당자</td>
						<td>Madmin</td>
					</tr>
					<tr>
						<td>작성일자 </td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3"><div style="min-height: 200px; text-align: left;"></div></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="3"><a href="#"></a></td>
					</tr>
				</tbody>
			</table>
				<a href="/shareTechList.do" class="btn btn-primary btn-sm active">목록</a>
				<!-- (수정) 글 작성자나 관리자가 아니면 수정,삭제 못하게 -->
				<a href="#" class="btn btn-primary btn-sm active">수정</a>
				<a href="#" class="btn btn-primary btn-sm active">삭제</a>
		</div>
	</div>
	
	<!-- 푸터 내비 -->
	<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>