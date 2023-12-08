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
         <h3 class="page-header"
            style="white-space: nowrap;" >Board - Detail
            <small>
            	&nbsp;&nbsp;&nbsp;<c:out value="${myboard.bno}"/>번 게시물
            </small>
         </h3>        
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
        
            <div class="panel panel-default"> 
            	<div class="panel-heading">
                   <div class="row">
	                  <div class="col-md-2" style="white-space: nowrap; height: 45px; padding-top:11px;">
	                     <strong style="font-size:16px;">${myboard.bwriter}님 작성</strong>
	                  </div>
	                  <div class="col-md-3" style="white-space: nowrap; height: 45px; padding-top:16px;">
	                     <span class="text-primary" style="font-size: smaller; height: 45px; padding-top: 19px;">
	                        <span>
	                           <span>등록일:&nbsp;</span>
	                           <strong><fmt:formatDate 
	                                    pattern="yyyy-MM-dd HH:mm:ss"
	                                    value="${myboard.bregDate}"
	                                  /></strong>
	                           <span>&nbsp;&nbsp;</span>
	                        </span>
	                        <span>조회수:&nbsp;<strong><c:out value="${myboard.bviewCnt}"/></strong>
	                        </span>
	                     </span>
	                  </div>
	                  <div class="col-md-7" style="height: 45px; padding-top:6px;"><%-- vertical-align: middle; --%>
	                     <div class="button-group pull-right">
	                     
	
	
	
	                     <button type="button" id="btnToModify" data-oper="modify"
	                           class="btn btn-primary"><span>수정페이지로 이동</span></button>
	
	
	                           
	                     <button type="button" id="btnToList" data-oper="list"
	                           class="btn btn-warning"><span>목록페이지로 이동</span></button>
	                     </div>
	                  </div>
	               </div>
	                </div>         		
                <!-- /.panel-heading -->
                             
                <div class="panel-body">

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
	    <label>최종수정일</label>
	   	<input class="form-control" name="bmodDate" id="bmodDate" 
	           value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${myboard.bmodDate }"/>' readonly="readonly" />
	</div> 


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
</script>
<script src="${contextPath }/resources/js/mycomment.js"></script>
<script>

var bnoValue = '<c:out value="${myboard.bno}"/>' ;

//댓글 목록 호출
myReplyClsr.getCmtList(
		{bno: bnoValue} ,
		function(myReplyPagingCreator){
			console.log(myReplyPagingCreator.replyTotCnt);
			for(var reply of myReplyPagingCreator.myreplyList){
				console.log(reply);
				console.log(reply.rregDate);
			}
		}
			
);

</script>

<script>
$(document).ready(function(){
	runModal(result) ;
});
</script>

<%@include file="../myinclude/myfooter.jsp" %>

