<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传图片测试</title>
</head>
<body>
	<h1>welcome to my world!</h1>
	<form action="save.do" method="post" enctype="multipart/form-data">  
	<input type="file" name="file" /> <input type="submit" value="Submit" /></form>
</body>
</html>