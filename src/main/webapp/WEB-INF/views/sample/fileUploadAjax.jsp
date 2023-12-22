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

<style>
	 .bigImageWrapper {
		 position: absolute;
		 display: none;
		 justify-content: center;
		 align-items: center;
		 top:0%;
		 width: 100%;
		 height: 100%;
		 background-color: lightgray; 
		 z-index: 100;
	 }
	 .bigImage {
		 position: relative;
		 display:flex;
		 justify-content: center;
		 align-items: center;
	 }
	 .bigImage img { height: 100%; max-width: 100%; width: auto; overflow: hidden }
 </style>


</head>
<body>
<h1>Ajax를 이용한 파일 업로드 페이지</h1>
<%-- 다중파일 업로드 방법1: form 방식의 파일업로드 --%>
<div class="uploadDiv">
	<input class="inputFile" type="file" name="uploadFiles" multiple="multiple"/>&nbsp;
</div>
	<button id="btnFileUpload" type="button">File Upload With Ajax</button>

<div class="fileUploadResult">
	<ul>
		<%-- 업로드 후 처리결과가 표시될 영역 --%>
	</ul>
</div>

<div class='bigImageWrapper'>
	<div class='bigImage'>
		<%-- 이미지파일이 표시되는 DIV --%>
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

//input 초기화를 위해 div 요소의 비어있는 input 요소를 복사해서 저장함.
var cloneFileInput = $(".uploadDiv").clone();  //clone(): 선택된 요소의 자식요소가 복사됨
//console.log("cloneFileInput: " + cloneFileInput.html()) ;

function checkUploadFile(fileName, fileSize) {
	
	var allowedMaxSize = 104857600 ;
	var regExpForbiddenFileExtension = /((\.(exe|dll|sh|c|zip|alz|tar)$)|^[^.]+$|(^\..{1,}$))/i ;
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

<%-- 함수: 이미지를 다운로드 받아서 웹 브라우저에 표시 --%>
function showImage(fullFileName) {
	
	$(".bigImageWrapper").css("display", "flex").show() ;
	$(".bigImage").html("<img src='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>")
//				  .animate({width: '100%', heigth: "100%"}, 3000) ;
				  .animate({width: '50%'}, 3000) ;
}

<%-- 이미지 제거: 원본 이미지 표시 DIV 클릭 이벤트처리: 클릭 시 1초 후에 이미지 사라짐.--%>
$(".bigImageWrapper").on("click", function(){
//	$(".bigImage").animate({width: '0%', heigth: "0%"}, 3000) ;
	$(".bigImage").animate({width: '0%'}, 3000) ;
	
	$(".bigImageWrapper").hide() ;
/* 	setTimeout(
		function(){
		 $(".bigImageWrapper").hide() ;
		}, 
		2500); */
})

<%-- 업로드 결과 표시 함수 --%>
function showUploadResult(uploadResult) {
	
	if(!uploadResult || uploadResult.length == 0) {
		return ;
	}
	
	var fileUploadResult = $(".fileUploadResult ul") ;
	var htmlStr = "";
	
	$(uploadResult).each(function(i, attachFile){
		
		var fullFileName = encodeURI(attachFile.repoPath + "/" +
									 attachFile.uploadPath + "/" +
									 attachFile.uuid + "_" +
									 attachFile.fileName ) ;
		
		if(attachFile.fileType == "F") {
			htmlStr 
			+="<li>"
			+ "    <a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName +"'>"
			+ "        <img src='${contextPath}/resources/img/icon-attach.png' style='width:25px;'>"
			+ "        &nbsp;&nbsp;" + attachFile.fileName 
			+ "    </a>"
			+  "  <span data-filename='" + fullFileName + "' data-filetype='F'>[삭제]</span>"
			+ "</li>" ;
			
		} else { //else if(attachFile.fileType == "I") {
			
			var thumbnail = encodeURI(attachFile.repoPath + "/" +
									  attachFile.uploadPath + "/s_" +
									  attachFile.uuid + "_" +
									  attachFile.fileName ) ;
			
		
			htmlStr 
			+="<li>"
//			+ "    <a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName +"'>" //다운로드
			+ "    <a href=\"javascript:showImage('" + fullFileName + "')\">"
			+ "        <img src='${contextPath}/displayThumbnail?fileName=" + thumbnail + "'>"
			+ "        &nbsp;&nbsp;" + attachFile.fileName 
			+ "    </a>"
			+  "  <span data-filename='" + thumbnail + "' data-filetype='I'>[삭제]</span>"
			+ "</li>" ;
		}
			
	}); <%--foreach-end--%>
	
	fileUploadResult.html(htmlStr) ;
//	fileUploadResult.append(htmlStr) ;

	
}


<%-- 업로드 처리 --%>
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
		dataType: "json" ,
		success: function(uploadResult, status){
			console.log(uploadResult);
			
//			$(".uploadDiv").html(cloneFileInput.html()) ;
			$(".inputFile").val("") ;
			
			showUploadResult(uploadResult);
		}
	});
	
}) ;


<%-- 업로드 파일 삭제: 서버에 업로드된 파일이 삭제되고, 이를 화면에 반영해 주어야 함 --%>
<%-- body > div.fileUploadResult > ul > li:nth-child(2) > span --%>
$(".fileUploadResult ul").on("click","li span", function(e){
	var fileName = $(this).data("filename") ;
	var fileType = $(this).data("filetype") ;
	
	var parentLi = $(this).parent() ;
	
	$.ajax({
		type: "post" ,
		url: "${contextPath}/deleteFile" ,
		data: {fileName: fileName, fileType: fileType} ,
		dataType: "text" ,
		success: function(result){
			if(result == "S") {
				alert("파일이 삭제되었습니다.") ;
				parentLi.remove() ;
				
			} else {
				if(confirm("파일이 존재하지 않습니다. 해당 항목을 삭제하시겠습니까 ?") ) {
					parentLi.remove() ;
					alert("파일이 삭제되었습니다.") ;	
				
				}
			}
		} //success-end,
		
	});  //ajax-end
	
});




</script>



</body>
</html>