<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>List</title>
<!-- Bootstrap -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/docs.css' />" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<script type="text/javascript">
<!--
function fnWrite() {
	window.location.href = "<c:url value='/board/write' />";	
}
//-->
</script>
<script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.js"></script>

<script type="text/javascript">
$(function() {
	    $( "#keyword" ).autocomplete({
	        source: function( request, response ) {
	          $.ajax({
	            url: "/board/list.json",
	            dataType: "json",
	            data: {
	            	keyword:$( "#keyword" ).val()
	            },
	            success: function( data ) {
	            	var items = [];
	            	$.each(data.boardList, function(index, value) {
	            		  items.push(value.title);
	            		});
	              response( $.map( items, function( item ) {
	                return [item]
	              }));
	            }
	          });
	        },
	        minLength: 2,
	        delimiter: /(,|;)\s*/, // regex or character
	        maxHeight:400,
	        width:300,
	        zIndex: 9999,
	        deferRequestBy: 0 //miliseconds
	    });
	});
</script>
<style>
/* city Search Loading by Justin 2013.04.02 */
.ui-autocomplete-loading
{
        background: white url('http://www.goodkiss.co.kr/img/waiting.gif') right center no-repeat;
}
</style>

</head>
<body>
<div class="bs-docs-grid">
	<h1>목록</h1>
	<div class="row">
		<div class="span9">
		<form action="/board/search">
			<input type="text" id="keyword" name="keyword"/>
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
				<td><a href="<c:url value="/board/view/${board.seq}" />">${board.title}</a></td>
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