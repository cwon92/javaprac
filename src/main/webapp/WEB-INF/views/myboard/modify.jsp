<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<%@include file="../myinclude/myheader.jsp" %>

<style>
/*th {text-align: center;}*/
</style>

<div id="page-wrapper"> 
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">Board - Modify</h3>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
            
                <div class="panel-heading"><h4>게시물 수정 삭제 </h4></div>
                <!-- /.panel-heading -->
                             
                <div class="panel-body">

<form role="form" method="post" name="frmModify" id="frmModify">
    
	<div class="form-group">
	    <label>글번호</label>
	    <input class="form-control" name="bno" id="bno"
	           value='<c:out value="${myboard.bno }"/>' readonly="readonly" />
	</div>
	<div class="form-group">
	    <label>글제목</label>
	    <input class="form-control" name="btitle" id="btitle"
	           value="${myboard.btitle }" />
	</div>
	<div class="form-group">
        <label>글내용</label>
	    <textarea class="form-control" rows="3" name="bcontent" id="bcontent"
	    				>${myboard.bcontent}</textarea>
    </div>
    <div class="form-group">
	    <label>작성자</label>
	    <input class="form-control" name="bwriter" id="bwriter" 
	           value="${myboard.bwriter }" readonly="readonly" />
	</div>
	    <div class="form-group">
	    <label>최종수정일</label> [등록일시: <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${myboard.bmodDate}"/>]
	   	<input class="form-control" name="bmodDate" id="bmodDate" 
	           value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${myboard.bmodDate }"/>' 
	           disabled="disabled" />  <!-- yyyy-MM-dd로 변경 readonly="readonly -->
			
	</div> 
	
	<button type="button" class="btn btn-primary mybtns" id="btnModify" data-oper="modify">수정</button>
	<button type="button" class="btn btn-primary mybtns" id="btnRemove" data-oper="remove">삭제</button>
	<button type="button" class="btn btn-warning mybtns" id="btnList" data-oper="list">취소</button>
	
	<input type="hidden"  id="pageNum" name="pageNum" value="${myBoardPagingDTO.pageNum }">
	<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${myBoardPagingDTO.rowAmountPerPage }">
	<input type="hidden" id="scope" name="scope" value="${myBoardPagingDTO.scope}">
	<input type="hidden" id="keyword" name="keyword" value="${myBoardPagingDTO.keyword}">
	
	</form>

                </div><!-- /.panel-body -->
            </div><!-- /.panel -->
        </div><!-- /.col-lg-12 -->
    </div><!-- /.row -->
    

</div>
        <!-- /#page-wrapper -->
               
        
<script>

//var frmModify = document.getElementById("frmModify");
var frmModify = $("#frmModify");

/* 
$("#btnModify").on("click", function() {
	//frmModify.action="${contextPath}/myboard/modify" ;
	frmModify.attr("action", "${contextPath}/myboard/modify")  ;
	frmModify.submit() ;
});

$("#btnRemove").on("click", function() {
	frmModify.attr("action", "${contextPath}/myboard/remove") ;
	frmModify.submit() ;
});

$("#btnList").on("click", function() {
	frmModify.empty() ; //제이쿼리 empty(): form 안에 구현된 모든 자식요소를 비워라.
	frmModify.attr("action", "${contextPath}/myboard/list");
	frmModify.attr("method", "get");
	frmModify.submit();
});
 */

 
$(".mybtns").on("click", function(){
		
		var operation = $(this).data("oper") ;
		//alert("operation: " + operation) ;
		
		if (operation == "modify") {
			frmModify.attr("action", "${contextPath}/myboard/modify") ;
		
		} else if (operation == "remove"){
			frmModify.attr("action", "${contextPath}/myboard/remove") ;
		
		} else {  //else if (operation == "list"){
			
			var pageNumInput = $("#pageNum").clone() ;
			var rowAmountPerPageInput = $("input[name='rowAmountPerPage']").clone() ;
			var scopeInput = $("#scope").clone() ;
			var keywordInput = $("#keyword").clone() ;
			
			frmModify.empty() ;
			
			frmModify.append(pageNumInput) ;
			frmModify.append(rowAmountPerPageInput) ;
			frmModify.append(scopeInput) ;
			frmModify.append(keywordInput) ;
			
			
			frmModify.attr("action", "${contextPath}/myboard/list").attr("method", "get") ; 
		}
	/*	
	<input type="hidden" name="scope" value="${myBoardPagingDTO.scope}">
	<input type="hidden" name="keyword" value="${myBoardPagingDTO.keyword}">
	*/
		frmModify.submit() ;
	});
 
 
</script>

<%@include file="../myinclude/myfooter.jsp" %>

