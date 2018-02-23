<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件列表</title>
	<%@include file="head.jsp" %>
</head>
<body>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading text-center">
			<h2>文件列表</h2>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
				<tr>
					<th>文件名</th>
					<th>文件路径</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="sk">
					<tr>
						<td>${sk.fileName}</td>
						<td>${sk.filePath}</td>
						<td><a href="download.do?fileName=${sk.fileName}&path=${sk.filePath}">下载</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		
		</div>
	</div>
</div>
<div align="center">
	<font size="2">第
		${page.pageNow} 页</font> <a href="todownload?pageNow=1" rel="external nofollow" rel="external nofollow">首页</a>
	<c:choose>
		<c:when test="${page.pageNow - 1 > 0}">
			<a href="todownload?pageNow=${page.pageNow - 1}" rel="external nofollow">上一页</a>
		</c:when>
		<c:when test="${page.pageNow - 1 <= 0}">
			<a href="todownload?pageNow=1" rel="external nofollow" rel="external nofollow">上一页</a>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${page.totalPageCount==0}">
			<a href="todownload?pageNow=${page.pageNow}" rel="external nofollow" rel="external nofollow">下一页</a>
		</c:when>
		<c:when test="${page.pageNow + 1 < page.totalPageCount}">
			<a href="todownload?pageNow=${page.pageNow + 1}" rel="external nofollow">下一页</a>
		</c:when>
		<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
			<a href="todownload?pageNow=${page.totalPageCount}" rel="external nofollow" rel="external nofollow">下一页</a>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${page.totalPageCount==0}">
			<a href="todownload?pageNow=${page.pageNow}" rel="external nofollow" rel="external nofollow">尾页</a>
		</c:when>
		<c:otherwise>
			<a href="todownload?pageNow=${page.totalPageCount}" rel="external nofollow" rel="external nofollow">尾页</a>
		</c:otherwise>
	</c:choose>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>


<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<a href="download.do?fileName=201705301151463.png">下载</a>
</body>
</html>--%>
