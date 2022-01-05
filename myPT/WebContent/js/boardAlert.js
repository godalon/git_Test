function btnDel(){
	if(confirm("삭제하시겠습니까?")){
		window.location.href="boardDelete.do";
	}else {
		return false;
	}
}

function btnList(){
	if(confirm("쓴 내용은 사라집니다 계속하시겠습니까?")){
		window.location.href="moveCommunity.do";
	}else {
		return false;
	}
}