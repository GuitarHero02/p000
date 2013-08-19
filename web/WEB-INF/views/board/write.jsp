<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>새글</title>
<!-- Bootstrap -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/docs.css' />" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function() {

	$('#addFile').click(function() {
		var fileIndex = $('#fileTable tr').children().length - 1;
		$('#fileTable').append(
				'<tr><td>'+
				'	<input type="file" name="files['+ fileIndex +']" />'+
				'</td></tr>');
	});
	
});
</script>
<script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
</head>
<body>
<div class="bs-docs-grid">
	<h1>목록</h1>
	<form:form commandName="boardVO" method="post" enctype="multipart/form-data">
	<div class="row">
		<div class="span9">
			<table class="table">
			<tr>
				<th><form:label path="title">제목</form:label></th>
				<td><form:input path="title"/><form:errors path="title" /></td>
			</tr>
			<tr>
				<th><form:label path="content">내용</form:label></th>
				<td><form:input path="content"/><form:errors path="content" /></td>
			</tr>
			<tr>
				<th><form:label path="writer">작성자</form:label></th>
				<td><form:input path="writer"/><form:errors path="writer" /></td>
			</tr>
			<tr>
				<th><form:label path="password">비밀번호</form:label></th>
				<td><form:input path="password"/><form:errors path="password" /></td>
			</tr>
			</table>
			<input id="addFile" type="button" value="Add File" />
			<table id="fileTable">
				<tr>
					<td><input name="files[0]" type="file" /></td>
				</tr>
			</table>
		</div>
		<div class="span9 text-right">
			<input type="submit" class="btn"/>
			<a href="<c:url value="/board/list" />" class="btn">목록</a>
		</div>
	</div>
	</form:form>
	
</div>
</body>
</html>