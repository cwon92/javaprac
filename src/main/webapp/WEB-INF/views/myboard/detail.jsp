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
            <h3 class="page-header">Board - Detail</h3>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
            
                <div class="panel-heading"><h4>게시물 상세 </h4></div>
                <!-- /.panel-heading -->
                             
                <div class="panel-body">

<%-- <form role="form" action="${contextPath }/myboard/register" 
      method="post" name="frmBoard" id="frmBoard"> --%>
      

	<div class="form-group">
	    <label>글번호</label>
	    <input class="form-control" name="bno" id="bno"
	           value='<c:out value="${myboard.bno }"/>' readonly="readonly" />
	</div>
	<div class="form-group">
	    <label>글제목</label>
	    <input class="form-control" name="btitle" id="btitle"
	           value="${myboard.btitle }" readonly="readonly" />
	</div>
	<div class="form-group">
        <label>글내용</label>
	    <textarea class="form-control" rows="3" name="bcontent" id="bcontent" 
	              readonly="readonly">${myboard.bcontent}</textarea>
    </div>
    <div class="form-group">
	    <label>작성자</label>
	    <input class="form-control" name="bwriter" id="bwriter" 
	           value="${myboard.bwriter }" readonly="readonly" />
	</div>
	    <div class="form-group">
	    <label>최종수정일</label> [등록일시: <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${myboard.bmodDate}"/>]
	   	<input class="form-control" name="bmodDate" id="bmodDate" 
	           value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${myboard.bmodDate }"/>' readonly="readonly" />
			
	</div> 
	<button type="button" class="btn btn-primary" id="btnToModify">수정페이지로 이동</button>
	<button type="button" class="btn btn-warning" id="btnToList">목록페이지로 이동</button>


	<form id="frmSendValue"><!-- 
		<input type="hidden" value="값" name="파라미터이름"> -->
		<input type="hidden" name="pageNum" value="${myboardPaging.pageNum}">
		<input type="hidden" name="rowAmountPerPage" value="${myboardPaging.rowAmountPerPage}">
	    <input type="hidden" name="scope" value="${myboardPaging.scope}">
	   	<input type="hidden" name="keyword" value="${myboardPaging.keyword}">
	    
	</form>

                </div><!-- /.panel-body -->
            </div><!-- /.panel -->
        </div><!-- /.col-lg-12 -->
    </div><!-- /.row -->
    

</div>
        <!-- /#page-wrapper -->
        
<%-- Modal 모달 시작 --%>
<div class="modal fade" id="yourModal" tabindex="-1" role="dialog" aria-labelledby="yourModalLabel" aria-hidden="true">
 <div class="modal-dialog">
 <div class="modal-content">
 <div class="modal-header">
 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
 <h4 class="modal-title" id="yourModalLabel">처리결과</h4>
 </div>
 <div class="modal-body" id="yourModal-body">메시지</div>
 	
 <div class="modal-footer">
 <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
 </div>
</div><%-- END .modal-content --%>
</div><%-- END .modal-dialog --%>
</div><%-- END .modal --%>  
        
<script>

var frmSendValue = $("#frmSendValue") ;

//게시물 목록 페이지 이동
$("#btnToList").on("click", function(){
//	window.location.href="${contextPath}/myboard/list" ;

	frmSendValue.attr("action", "${contextPath}/myboard/list").attr("method", "get") ;
	frmSendValue.submit() ;
});

//게시물 수정-삭제 페이지 이동
$("#btnToModify").on("click", function(){
//	window.location.href='${contextPath}/myboard/modify?bno=<c:out value="${myboard.bno}"/>' ;
	
	var bno = '<c:out value="${myboard.bno}"/>' ;
	
	frmSendValue.append("<input type='hidden' name='bno' value='" + bno + "'/>") ;
	frmSendValue.attr("action", "${contextPath}/myboard/modify").attr("method", "get") ;
	frmSendValue.submit() ;
});




var result = '<c:out value="${result}" />' ;
//alert("result" + result);

function runModal(result) {
	
	if (result.length == 0) {
		return ;
	
	} else if ( result == "successModify" ) {
		var myMsg =  "게시글이 수정되었습니다. " ;
		
	}  

	$("#yourModal-body").html(myMsg) ;
	
	$("#yourModal").modal("show") ;
	
	myMsg = "" ;
}

$(document).ready(function(){
	runModal(result) ;
});
</script>

<%@include file="../myinclude/myfooter.jsp" %>

