<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Form Page</title>
</head>
<body>
<h1>Ajax를 이용한 파일 업로드 페이지</h1>
<%-- 다중파일 업로드 방법1: form 방식의 파일업로드 --%>
<div class="uploadDiv">
	<input class="inputFile" type="file" name="uploadFiles" multiple="multiple"/>&nbsp;
</div>
	<button id="btnFileUpload" type="button">File Upload With Ajax</button>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

//input 초기화를 위해 div 요소의 비어있는 input 요소를 복사해서 저장함.
var cloneFileInput = $(".uploadDiv").clone();  //clone(): 선택된 요소의 자식요소가 복사됨
//console.log("cloneFileInput: " + cloneFileInput.html()) ;

function checkUploadFile(fileName, fileSize) {
	
	var allowedMaxSize = 104857600 ;
	var regExpForbiddenFileExtension = /^.*(\.)(exe|dll|sh|c|zip|alz|tar)$/i ;
	//파일이름.확장자(image.jpg)
	
	if (fileSize > allowedMaxSize) {
		alert("업로드 파일의 제한된 크기(1MB)를 초과했습니다.") ;
		return false ;
	}
	//파일 확장자 검사
	if(regExpForbiddenFileExtension.test(fileName)){
		alert("확장명이 없거나 [exe,dll,sh,c,zip,alz,tar] 형식 파일들은\n업로드 할 수 없습니다.") ;
		return false ;
		
	}
	
	return true ;
}


$("#btnFileUpload").on("click", function(){
	//FormData() Ajax 파일 전송 시에 사용되는 Web API 클래스의 생성자
	var formData = new FormData() ;
	
	//uploadFiles 이름의 file 유형 input 요소를 변수에 저장
	var fileInputs = $("input[name='uploadFiles']") ;
	
	//fileInputs 변수에 대입된 input들에 저장된 파일들을 yourFiles 변수에 저장.
	//file 타입의 input 요소에는 files 속성에 파일이 저장됩니다.
	//fileInputs[0]은 첫번째 input 요소를 의미(input 요소가 하나만 있더라도 [0]을 명시해야 함).
	//[문제] input 요소가 여러개일 때, 파일정보를 담는 방법 구현.
	
	var yourFiles = fileInputs[0].files ;
	
	console.log(yourFiles) ;
	
	if(yourFiles == null || yourFiles.length == 0) {
		alert("파일을 선택하세요");
		return ;
	}
	
	//FormData() 객체에 input의 파일을 모두 저장함
	for(var i = 0 ; i < yourFiles.length ; i++) {
		
		//파일 크기 검사
		if(!checkUploadFile(yourFiles[i].name, yourFiles[i].size)){
			console.log("파일이름: " + yourFiles[i].name) ;
			console.log("파일크기: " + yourFiles[i].size) ;
			return ;
		}
		
		formData.append("yourUploadFiles", yourFiles[i])
		
	}
	
	//FormData() 객체(formData)를 서버로 전송(By Ajax)
	//url 키에 명시된 주소의 컨트롤러에게 formData 객체를 POST 방식으로 전송.
	$.ajax({
		type: "post" ,
		url: "${contextPath}/fileUploadAjaxAction" ,
		data: formData ,
		contentType: false ,//contentType에 MIME 타입을 지정하지 않음.
		processData: false ,//contentType에 설정된 형식으로 data를 처리하지 않음.
		dataType: "text" ,
		success: function(uploadResult, status){
			console.log("업로드 결과: " + uploadResult);
			
//			$(".uploadDiv").html(cloneFileInput.html()) ;
			$(".inputFile").val("") ;
		}
	});
	
}) ;

</script>



</body>
</html>