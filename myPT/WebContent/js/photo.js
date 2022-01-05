

//페이징
function paging(keyWord,page){
	if(keyWord==null||keyWord==""){
		window.location.href="movePhoto.do?page="+page;
	}else{
		window.location.href="searchPhoto.do?page="+page;
	}
}

function ltPaging(keyWord,arrow){
	if(keyWord==null||keyWord==""){
		window.location.href="movePhoto.do?prev="+arrow;
	}else{
		window.location.href="searchPhoto.do?prev="+arrow;
	}s
}

function gtPaging(keyWord,arrow){
	if(keyWord==null||keyWord==""){
		window.location.href="movePhoto.do?next="+arrow;
	}else{
		window.location.href="searchPhoto.do?next="+arrow;
	}s
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
		window.location.href="movePhoto.do"
	}else{
		window.location.href="searchPhoto.do?keyField="+keyField+"&keyWord="+keyWord;
	}
}
$("img").attr("style","cursor:pointer");
// 이미지 클릭 시 게시물 상세 페이지로 이동
$("img").on("click",function(){
	var id=$(this).parent().attr("id").substring(4);
	window.location.href="boardView.do?num="+id;
})
