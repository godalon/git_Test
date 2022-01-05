

//페이징
function paging(keyWord,page,head){
	if((keyWord==null||keyWord=="")&&head=="all"){
		window.location.href="moveCommunity.do?page="+page;
	}else{
		window.location.href="searchCommunity.do?page="+page+"&head="+head;
	}
}
function ltPaging(keyWord,arrow,head){
	if((keyWord==null||keyWord=="")&&head=="all"){
		window.location.href="moveCommunity.do?prev="+arrow;
	}else{
		window.location.href="searchCommunity.do?prev="+arrow+"&head="+head;
	}
}

function gtPaging(keyWord,arrow,head){
	if((keyWord==null||keyWord=="")&&head=="all"){
		window.location.href="moveCommunity.do?next="+arrow;
	}else{
		window.location.href="searchCommunity.do?next="+arrow+"&head="+head;
	}
}

//검색 확인
function searchCheck(){
	var keyField=$("#keyField").val();
	var keyWord=$("#keyWord").val();
	if(keyField==null){
		alert("카테고리를 지정하세요.");			
		return false;
	}
	else if(keyWord==""){
		window.location.href="moveCommunity.do"
	}else{
		window.location.href="searchCommunity.do?keyField="+keyField+"&keyWord="+keyWord;
	}
}

//말머리 선택
$("#head").change(function() {
	var head = $("#head").val();
	window.location.href="searchCommunity.do?head="+head; 
	})

// 이미지 클릭 시 게시물 상세 페이지로 이동
$("img").on("click",function(){
	var id=$(this).parent().attr("id").substring(4);
	window.location.href="boardView.do?num="+id;
})