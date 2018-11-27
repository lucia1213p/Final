<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

</head>
<body>

<div class="container">
	<div class="row">
		<table class="table talbe-striped" style="text-align :center; border:1px solid #dddddd">
			<thead>
				<th style="background-color : #eeeeee; text-align:center;">번호</th>
				<th style="background-color : #eeeeee; text-align:center;">제목</th>
				<th style="background-color : #eeeeee; text-align:center;">작성자</th>
				<th style="background-color : #eeeeee; text-align:center;">조회수</th>
			</thead>
		</table>
	</div>
</div>

</body>
</html>