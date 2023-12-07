<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exUpload</title>
</head>
<body> 
<form action="/mypro00/sample/exUploadPost" method="post" enctype="multipart/form-data"><!-- 
<form action="/mypro00/sample/exUploadPost" method="post" enctype="application/x-www-form-urlencoded"> -->
    <div><input type="text" name="name" placeholder="이름입력">
         <input type="text" name="age" placeholder="나이입력"></div>
    <div><input type="file" name="myFiles"></div>
    <div><input type="file" name="myFiles"></div>
    <div><input type="file" name="myFiles"></div>
    <div><input type="file" name="myFiles"></div>
    <div><input type="file" name="myFiles"></div>
    <div><button type="submit">전송</button></div>
</form>

</body>
</html>