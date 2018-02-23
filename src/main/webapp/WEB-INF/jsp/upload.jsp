<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<form action="upload.do" method="post" enctype="multipart/form-data">
	文件1: <input type="file" name="myfiles"/><br/>
	文件2: <input type="file" name="myfiles"/><br/>
<%--	文件3: <input type="file" name="myfiles"/><br/>--%>
	<input type="submit" value="上传">
</form>
</body>
</html>