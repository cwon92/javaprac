/**
* mycomment.js: 댓글/답글 데이터 처리용 Ajax Closure Module
*/
//alert("댓글 클로저 모듈 실행됨==========");

var myReplyClsr = (function(){
    //댓글 목록(페이징) - ajax() 함수 사용
    function getCmtList(myParam, callback, error){
        var bno = myParam.bno;
        // var pageNum = (myParam.pageNum != null)? myParam.pageNum:1
        var pageNum = myParam.pageNum || 1;
        console.log("getCmtList()가 전달받은 bno: " + bno) ;
        console.log("getCmtList()가 전달받은 pageNum: " + pageNum) ;
    
        //댓글 목록 조회 컨트롤러의 매핑 URL: GET /replies/{bno}/page/{pageNum}
        //$.ajax() 함수는, 자바스크립트 객체를 매개값으로 받아 처리
        $.ajax({
            type : "get" ,
            url: "/mypro00/replies/" + bno + "/page/" + pageNum ,
            dataType: "json" , //나는 json으로 받고 싶어 , ajax가 받는 데이터 타입
            //data: //ajax가 서버로 보내는 값들
            success: function(myReplyPagingCreator, status, xhr){
                    if(callback){
                        callback(myReplyPagingCreator);
                    }
            },

            error: function(xhr, status, err) {
                if(error){
                    error(err);
                }
            }

        }); //ajax-end

    } //getCmtList-end

    return {
        getCmtList: getCmtList

    };

})();