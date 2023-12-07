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
            <h3 class="page-header">Board - Register</h3>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
            
                <div class="panel-heading"><h4>게시물 등록</h4></div>
                <!-- /.panel-heading -->
                             
                <div class="panel-body">

<form role="form" action="${contextPath }/myboard/register" 
      method="post" name="frmBoard" id="frmBoard">
	<div class="form-group">
	    <label>글제목</label>
	    <input class="form-control" name="btitle" id="btitle">
	</div>
	<div class="form-group">
        <label>글내용</label>
        <textarea class="form-control" rows="3" name="bcontent" id="bcontent"
                 ></textarea>
    </div>
    <div class="form-group">
	    <label>작성자</label>
	    <input class="form-control" name="bwriter" id="bwriter">
	</div>
	
	<button type="button" class="btn btn-primary" onclick="sendBoard()" id="btnRegister">등록</button>
	<button type="button" class="btn btn-warning"
			onclick="location.href='${contextPath}/myboard/list';">취소</button>
    
</form>
<script>
function sendBoard() {

	var frmBoard = document.getElementById("frmBoard") ;
    var btitle = document.getElementById("btitle").value ;
    var bcontent = document.getElementById("bcontent").value ;
    var bwriter = document.getElementById("bwriter").value ;

    if (btitle.length == 0 || bcontent.length == 0 || bwriter.length == 0) {
        alert("글제목/내용/작성자을 입력하세요.") ;
    } else {
    	frmBoard.submit();
    }   
    
}

/* $("btnRegister").on("click", function sendBoard() {
	sendBoard();
}); */


</script>




                </div><!-- /.panel-body -->
            </div><!-- /.panel -->
        </div><!-- /.col-lg-12 -->
    </div><!-- /.row -->
    

</div>
        <!-- /#page-wrapper -->
        
        

<%@include file="../myinclude/myfooter.jsp" %>

