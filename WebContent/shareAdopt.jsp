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
<title>채택</title>
<style>
	.answer_wrap{
		display: block;
		border:2px dashed black;
		width:100%;
		min-height:200px;
	}
	.answer_wrap .answer-top{
		border:2px dashed red;
		min-height:60px;
	}
	.answer-top .top-left{
		border:1px dashed blue;
		width:60%;
		height:100%;
		float:left;
	}
	.answer-top .top-right{
	border:1px dashed yellow;
		width:40%;
		height:100%;
		float:right;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="answer_wrap">
				<div class="answer-top">
					<span id="top-left">
						<p>왼쪽(작성자)</p>
					</span>
					<span id="top-right">
						<p>오른쪽(채택현황)</p>
					</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>