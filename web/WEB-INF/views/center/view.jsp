<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>읽기</title>
<!-- Bootstrap -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/docs.css' />" rel="stylesheet">
</head>
<body>
<div class="bs-docs-grid">
	<h1>읽기</h1>
	<div class="row">
		<div class="span9">
			<table class="table">
			<tr>
				<th>제목</th>
				<td>${boardVO.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${boardVO.content}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${boardVO.writer}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${boardVO.regDate}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${boardVO.cnt}</td>
			</tr>
			</table>
		</div>
		<div class="span9 text-right">
			<a href="<c:url value="/board/edit/${boardVO.seq}" />" class="btn">수정</a>
			<a href="<c:url value="/board/list" />" class="btn">목록</a>
		</div>
	</div>
</div>
</body>
</html>