<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex04.jsp</title>
</head>
<body>
	<h2>sampleDTO: ${sampleDTO }</h2>
	<h2>dto: ${dto.name} </h2>
	<h2>dto: ${dto.age} </h2>
	<h2>sampleDTO.name: ${sampleDTO.name} </h2>
	<h2>sampleDTO.age: ${sampleDTO.age} </h2>
	
	<h2>name: ${name }</h2>
	<h2>age: ${age }</h2>
	
	<h2>page: ${page }</h2>
	<h2>page1: ${page1 }</h2>

	<%-- <h2>page: ${page[0] }</h2> --%>
		
	<h2>ArrayList를 통해 전달된 myIds</h2>
	<h2>myIds[0]: ${myIds[0] }</h2>
	<h2>myIds[1]: ${myIds[1] }</h2>
	<h2>myIds[2]: ${myIds[2] }</h2>


</body>
</html>