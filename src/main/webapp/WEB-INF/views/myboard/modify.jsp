<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<%@include file="../myinclude/myheader.jsp" %>  

<style>
 th {text-align: center;}
</style>  

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">Board - Modify</h3>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>
    <div class="row">
        <div class="col-lg-12">
        
            <div class="panel panel-default">
                <div class="panel-heading"><h4>게시물 수정-삭제</h4></div><%-- /.panel-heading --%>
                
                <div class="panel-body">

<form role="form" method="post" name="frmModify" id="frmModify">
	
	<div class="form-group">
	    <label>글번호</label>
	    <input class="form-control" name="bno" id="bno" 
	           value='<c:out value="${myboard.bno }"/>' readonly="readonly">
	</div>
	
	<div class="form-group">
	    <label>글제목</label>
	    <input class="form-control" name="btitle" id="btitle" 
	    	   value="${myboard.btitle }" >
	</div>
	<div class="form-group">
	    <label>글내용</label>
	    <textarea class="form-control" rows="3" name="bcontent" id="bcontent"
	    		  >${myboard.bcontent}</textarea>
	</div>
	<div class="form-group">
	    <label>작성자</label>
	    <input class="form-control" name="bwriter" id="bwriter" 
	      	  value="${myboard.bwriter }" readonly="readonly">
	</div>
	<div class="form-group">
	    <label>최종수정일</label> [등록일시: <fmt:formatDate value="${myboard.bregDate }" pattern="yyyy/MM/dd HH:mm:ss" />]
	    <input class="form-control" name="bmodDate" id="bmodDate" 
	      	   value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${myboard.bmodDate }"/>' 
	      	   disabled="disabled">
	</div>
	
	
	
	<button type="button" class="btn btn-primary mybtns" id="btnModify" data-oper="modify">수정</button>
	<button type="button" class="btn btn-primary mybtns" id="btnRemove" data-oper="remove">삭제</button>
	<button type="button" class="btn btn-warning mybtns" id="btnList" data-oper="list">취소</button>
 	
 	<input type="hidden" id="pageNum" name="pageNum" value="${myBoardPagingDTO.pageNum }" >
	<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${myBoardPagingDTO.rowAmountPerPage }" >
	<input type="hidden" id="scope" name="scope" value="${myBoardPagingDTO.scope }" >
	<input type="hidden" id="keyword" name="keyword" value="${myBoardPagingDTO.keyword }" >
	<input type="hidden" id="startDate" name="startDate" value="${myBoardPagingDTO.startDate }" >
	<input type="hidden" id="endDate" name="endDate" value="${myBoardPagingDTO.endDate }" >
</form>

<%-- 첨부파일 결과 표시 --%>    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">파일첨부</div><!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="form-group uploadDiv">
                        <input id="inputFile" class="btn btn-primary inputFile" type="file" name="uploadFiles" multiple="multiple" /><br>
                    </div>
	                <div class="form-group fileUploadResult">
	                    <ul>
	                        <%-- 업로드 후 처리결과가 표시될 영역 --%>
	                    </ul>
	                </div>
                </div><!-- /.panel-body -->
            </div><!-- /.panel -->
        </div><!-- /.col-lg-12 -->
    </div><!-- /.row -->

                </div><%-- /.panel-body --%>
                
            </div><%-- /.panel --%>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>

</div><%-- /#page-wrapper --%>


<script>

//var frmModify = document.getElementById("frmModify") ;
var frmModify = $("#frmModify") ;

/*
$("#btnModify").on("click", function(){
	//frmModify.action="${contextPath}/myboard/modify" ;
	frmModify.attr("action", "${contextPath}/myboard/modify") ;
	frmModify.submit() ;
});

$("#btnRemove").on("click", function(){
	frmModify.attr("action", "${contextPath}/myboard/remove") ;
	frmModify.submit() ;
});

$("#btnList").on("click", function(){
	frmModify.empty() ; //제이쿼리 empty(): form 안에 구현된 모든 자식요소를 없애라.
	frmModify.attr("action", "${contextPath}/myboard/list") ;
	frmModify.attr("method", "get") ;
	frmModify.submit() ;
});
*/

//수정된 게시물 입력값 유무 확인 함수
function checkBoardValue(){
	
	var btitle = document.getElementById("btitle").value ;
	var bcontent = document.getElementById("bcontent").value ;
	var bwriter = document.getElementById("bwriter").value ;
	
	if( btitle.length==0 || bcontent.length==0 || bwriter.length==0 ){
		return false ;
		
	} else {
		return true;
	}
}

<%-- 수정 삭제 --%>
$(".mybtns").on("click", function(){
	
	if(!checkBoardValue()){
		alert("글제목/글내용/작성자를 모두 입력해야 합니다.");
		return ;	
	}
	
	var operation = $(this).data("oper") ;
	//alert("operation: " + operation) ;
	//#page-wrapper > div:nth-child(2) > div > div > div.panel-body > div > div > div > div.panel-body > div.form-group.fileUploadResult > ul > li:nth-child(1)
	if (operation == "modify") {
		
		var atttachFileInputHTML = "";
		
		<%-- li요소의 값들을 읽어와서 hidden input을 생성하는 함수--%>
		<%-- div.form-group.fileUploadResult > ul > li:nth-child(1)--%>
		$("div.fileUploadResult ul li").each(function(i, obj){
			
			var objLi = $(obj);
			
			if(objLi == null){
				return;
			}
			
			atttachFileInputHTML
				+= "<input type='hidden' name='attachFileList["+ i +"].uuid' value='" + objLi.data("uuid") + "'>"
				+  "<input type='hidden' name='attachFileList["+ i +"].uploadPath' value='" + objLi.data("uploadpath") + "'>"
				+  "<input type='hidden' name='attachFileList["+ i +"].fileName' value='" + objLi.data("filename") + "'>"
				+  "<input type='hidden' name='attachFileList["+ i +"].fileType' value='" + objLi.data("filetype") + "'>";

		}); //each-end
		
		if(atttachFileInputHTML != ""){
			frmModify.append(atttachFileInputHTML);
		}
		
		frmModify.attr("action", "${contextPath}/myboard/modify") ;
	
	} else if (operation == "remove"){
		frmModify.attr("action", "${contextPath}/myboard/remove") ;
	
	} else {  //else if (operation == "list"){
		
		var pageNumInput = $("#pageNum").clone() ;
		var rowAmountPerPageInput = $("input[name='rowAmountPerPage']").clone() ;
		var scopeInput = $("#scope").clone() ;
		var keywordInput = $("#keyword").clone() ;
		var startDateInput = $("#startDate").clone() ;
		var endDateInput = $("#endDate").clone() ;
		
		frmModify.empty() ;
		
		frmModify.append(pageNumInput) ;
		frmModify.append(rowAmountPerPageInput) ;
		frmModify.append(scopeInput) ;
		frmModify.append(keywordInput) ;
		frmModify.append(startDateInput) ;
		frmModify.append(endDateInput) ;
		
		frmModify.attr("action", "${contextPath}/myboard/list").attr("method", "get") ; 
	}
	
	frmModify.submit() ;
});
</script>

<%-- 첨부파일 관련 코드 --%>
<script>

<%-- 수정페이지 게시물 표시 후, ajax로 첨부파일 정보 가져오는 함수 --%>
function getAttachFileInfo(){
	
	var bno = '<c:out value="${myboard.bno }"/>' ;
	
	$.ajax({
		
		type:"get",
		url:"${contextPath}/myboard/getFiles" ,
		data: {bno:bno},
		dataType:'json',
		success: function(uploadResult){
			
			showUploadResult(uploadResult);
		}
		
	})<%--ajax-end--%>
	
}<%-- function-end --%>




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



function showUploadResult(uploadResult) {
	
	var fileUploadResult = $(".fileUploadResult ul") ;
	var htmlStr = "";
	
	if(!uploadResult || uploadResult.length == 0) {
		
		htmlStr 
		+="<li style='font-size:12pt;'>첨부파일이 없습니다</li>"
	}else{
		$(uploadResult).each(function(i, attachFile){
			
			var fullFileName = encodeURI(attachFile.repoPath + "/" +
										 attachFile.uploadPath + "/" +
										 attachFile.uuid + "_" +
										 attachFile.fileName ) ;
			
			if(attachFile.fileType == "F"){
				
				htmlStr 
				+="<li data-uploadpath='"+attachFile.uploadPath + "'"
				+ "    data-uuid='" + attachFile.uuid + "'"
				+ "    data-filename='" + attachFile.fileName + "'"
				+ "    data-filetype='F'>"
//				+ "    <a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName +"'>"
				+ "        <img src='${contextPath}/resources/img/icon-attach.png' style='width:25px;'>"
				+ "        &nbsp;&nbsp;" + attachFile.fileName 
//				+ "    </a>"
				+ "    <button data-filename='" + fullFileName + "' data-filetype='F'>[삭제]</button>"
				+ "</li>" ;
				
			} else { //else if(attachFile.fileType == "I") {
				
				var thumbnail = encodeURI(attachFile.repoPath + "/" +
										  attachFile.uploadPath + "/s_" +
										  attachFile.uuid + "_" +
										  attachFile.fileName ) ;
			
				htmlStr 
				+="<li data-uploadpath='"+attachFile.uploadPath + "'"
				+ "    data-uuid='" + attachFile.uuid + "'"
				+ "    data-filename='" + attachFile.fileName + "'"
				+ "    data-filetype='I'>"
//				+ "    <a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName +"'>" //다운로드
//				+ "    <a href=\"javascript:showImage('" + fullFileName + "')\">"
				+ "        <img src='${contextPath}/displayThumbnail?fileName=" + thumbnail + "'>"
				+ "        &nbsp;&nbsp;" + attachFile.fileName 
//				+ "    </a>"
				+ "    <button data-filename='" + thumbnail + "' data-filetype='I'>[삭제]</button>"
				+ "</li>" ;
			}
			
			;		
		}); <%--foreach-end--%>
		
	}
	fileUploadResult.html(htmlStr) 
}
	
//#page-wrapper > div:nth-child(2) > div > div > div.panel-body > div > div > div > div.panel-body > div.form-group.fileUploadResult > ul > li:nth-child(1)
$(".fileUploadResult ul").on("click","li button", function(){
   $(this).parent('li').remove();
   
   if($(".fileUploadResult ul").children('li').length == 0){
      showUploadResult();
   }
});

<%-- 업로드 처리 --%>
<%-- 파일 업로드 처리: 파일 input 요소의 내용이 바뀌면 업로드가 자동으로 수행되도록 수정 --%>
$("#inputFile").on("change", function(){
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
			
<%--		복사된 file-inpyt을 삽입하는 경우, 첨부파일 삭제/추가 시에는, 초기화 되지 않음.
			$(".uploadDiv").html(cloneFileInput.html()) ;--%>
			$(".inputFile").val("") ;
			
			showUploadResult(uploadResult);
		}
	});
	
}) ;

<%-- 업로드 파일 삭제: 취소 기능을 고려해서 화면의 삭제를 클릭 시에는 
				  화면의 항목만 삭제되도록 수정 --%>
<%-- body > div.fileUploadResult > ul > li:nth-child(2) > span --%>
$(".fileUploadResult ul").on("click","li span", function(){
	
	var parentLi = $(this).parent() ;
	<%--
	var parentLi = $(this).closest("li") ;
	--%>
	
	if(confirm("이 파일을 삭제하시겠습니까?") ) {
		parentLi.remove() ;
		alert("파일이 삭제되었습니다.") ;	
	
	}
});

$(document).ready(function(){
	getAttachFileInfo();
})

</script>

<%@include file="../myinclude/myfooter.jsp" %>    