<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Q&A게시판 </title>
<!-- Bootstrap -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/docs.css' />" rel="stylesheet">
<script type="text/javascript">
<!--
function fnWrite() {
	window.location.href = "<c:url value='/qna/write' />";	
}
//-->
</script> 
</head>
<body>
<div class="bs-docs-grid">
	<h1>Q&A게시판</h1>
	<div class="row">
		<div class="span9">
		<form action="/board/search">
			<input type="text" name="keyword"/>
			<input type="submit" value="search">
		</form>
			<table class="table">
			<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			</thead>
<c:forEach var="board" items="${list}" varStatus="loop">
			<tr>
				<td>${board.seq}</td>
				<td><a href="<c:url value="/qna/view/${board.seq}" />">${board.title}</a></td>
				<td>${board.writer}</td>
				<td>${board.regDate}</td>
				<td>${board.cnt}</td>
			</tr>
</c:forEach>
			</table>
		</div>
		<div class="span9 text-right">
			<button class="btn" onclick="window.location.herf=fnWrite();">새글</button>
		</div>
	</div>
</div>
</body>
</html>