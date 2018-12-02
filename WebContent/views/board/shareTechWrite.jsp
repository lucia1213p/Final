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

<title>기술공유 글작성</title>

<style>
	.container{
		padding-top:50px;
	}
</style>
</head>
<body>

<script>
	//첨부파일 업로드
	$(document).ready(function(){
        $('#browsebutton :file').change(function(e){
            var fileName = e.target.files[0].name;
            $("#label").attr('placeholder',fileName);
        });
    });
</script>
<!-- 헤더 내비 -->
<jsp:include page="/header.jsp" flush="false" />

<div class="container">
	<div class="row">
		<form method="post" action="/shareTechWrite.do" enctype="multipart/form-data">
		<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="2" style="background-color : #eeeeee; text-align:center;"><h4>기술문의</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2"><input type="text" class="form-control" placeholder="글 제목" name="shareTechTitle" maxlength="50"></td>
				<tr>
					<td colspan="2"><textarea type="text" class="form-control" placeholder="글 내용" name="shareTechContent" maxlength="2048" style="height:550px; resize: none;" ></textarea></td>
				</tr>
				<tr>
					<td width="20%">첨부파일</td>
					<td>
						<div class="input-group">
					        <label id="browsebutton" class="btn btn-default input-group-addon" for="my-file-selector" style="background-color:white">
					            <input id="my-file-selector" type="file" accept=".jpg, .gif, .png, .zip" name="fileName" style="display:none; ">
					            <span class="glyphicon glyphicon-folder-open"></span>
                        		<span class="image-preview-input-title">Browse</span>
					        </label>
					        <input id="label" type="text" class="form-control" readonly="">
					    </div>
			       </td>
				</tr>
			</tbody>
		</table>
		<input type="submit" class="btn btn-primary pull-right" value="글작성">
		</form>
	</div>
</div>

<!-- 푸터 내비 -->
<jsp:include page="/footer.jsp" flush="false" />
	
</body>
</html>