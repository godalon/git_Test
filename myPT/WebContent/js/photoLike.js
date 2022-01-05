$("svg").on("click",function(){
	var likeClass=$(this).parent().prev();
	
	if($(likeClass).attr("checked")=="checked"){
		$(likeClass).removeAttr("checked");
	}else{
		$(likeClass).attr("checked","checked");
	}
	
	var num=$(this).parent().prev().attr("id");
	var likeNum=$("#likeNum"+num).text()*1;
	
	var flag=0
	if($("#"+num).attr("checked")=="checked"){
		flag=1;
	}else{
		flag=0;
	}
	
	$.ajax({
		url:"boardLike.do",
		type:"post",
		data:{
			"num":num,
			"flag":flag
		},
		success:function(data){
			if(flag==1){
				likeNum+=1;
				$("#likeNum"+num).text(likeNum);
			}else{
				likeNum-=1;
				$("#likeNum"+num).text(likeNum);
			}
		},
		error:function(e){
			alert("실패");
			alert(e);
		}
	})
	
})














