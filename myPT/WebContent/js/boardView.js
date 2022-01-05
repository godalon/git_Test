
function changePage(page)
{
	  nowPage= page;
	  getChangedComment();
}

function changeBlock(block)
{
	if(block<nowBlock) //이전
	{
		nowPage= pagePerBlock*(block-1)+pagePerBlock;
	}
	else //이후
	{	
		nowPage= pagePerBlock*(block-1)+1;	  
	}
	
	nowBlock=block;
	getChangedComment();
}


//function changeBlock(block)
//{
//	nowPage= pagePerBlock*(block-1)+1;	  	
//	nowBlock=block;
//	getChangedComment();
//}


function setComment()
{
  	
	if(totalRecord==0)
	{	
		$('#commentsWrapper').append('<div class="text-center font-weight-bold py-5">첫번째 댓글을 남겨주세요!</div>');
		return false;
	}

	$('#commentsWrapper').empty();


	let htmlForNew=""; 
	 var today = new Date();
	 today.setHours(today.getHours()-24)					 


	$.each (comments, function (index, com) 
	{
		var commentdate= new Date(com.c_date)
				
		htmlForNew+='<hr><div class="comments"><div class="row"><div class="row col-9">';
		htmlForNew+='<div class="col font-weight-bold commentNick">'+com.c_nick
		
		if(boardWriter==com.c_nick)
		{
			htmlForNew+='<span class="badge badge-success ml-1">작성자</span>';
		}
		if(commentdate>today)
		{
			htmlForNew+='<img src="img/new.png" width="12px" class="ml-1 newImg">';
		}		
		
		htmlForNew+='</div></div>';			
											
		if(sessionNick== com.c_nick)
			{
				htmlForNew+='<div class="col-3 text-right dropdown"><a class="mt-1 text-secondary" href="#" role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></a><div class="dropdown-menu dropdown-menu-right">';
				htmlForNew+='<span class="dropdown-item commentUpdate" style="cursor:pointer;">수정</span><span class="dropdown-item commentDelete" style="cursor:pointer;">삭제</span><input type="number" hidden="true" value='+com.c_num+'></div></div>';
			}
		else if(sessionGrade==0)
			{
				htmlForNew+='<div class="col-3 text-right dropdown"><a class="mt-1 text-secondary" href="#" role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></a><div class="dropdown-menu dropdown-menu-right">';
				htmlForNew+='<a class="dropdown-item commentDelete">삭제</a><input type="number" hidden="true" value='+com.c_num+'></div></div>';
			}
										
								
		htmlForNew+='</div><div class="row"><div class="col py-2 content">'+com.c_content+'</div></div>';
		htmlForNew+='<div class="row"><div class="col text-secondary"><span class="commentDate">'+com.c_date+'</span><span class="ml-2 replyToComment" style="cursor:pointer;">댓글쓰기</span></div></div>';						
//		htmlForNew+='<div class="my-1 ml-md-4 reComment" style="display:none;"><div class="row"><div class="row col-9"><div class="col text-dark font-weight-bold commentNick">'+sessionNick+'</div></div><textarea id="forRecomment'+index+'" cols="50" rows="4" maxlength="500" style="width:100%; resize:none" class="border-1"></textarea></div>'
		htmlForNew+='<div class="my-1 ml-md-4" id="reComment'+index+'" style="display:none;"><div class="row"><div class="row col-9"><div class="col text-dark font-weight-bold commentNick">'+sessionNick+'</div></div><textarea id="forRecomment'+index+'" cols="50" rows="4" maxlength="500" style="width:100%; resize:none" class="border-1"></textarea></div>'	
		htmlForNew+='<div class="mt-2 text-right"><input type="button" class="btn btn-dark ml-auto" id="replyBtn'+index+'" value="등록"><input type="button" class="btn btn-danger mx-2" id="cancelReplyBtn'+index+'" value="취소"></div></div></div>';
	
	});
	$('#commentsWrapper').append(htmlForNew);

	
}


function setBlock()
{
	$('#page').empty();
	
	if(totalRecord!=0)
	{
			
		if(totalPage>0 && nowBlock>1)
		{
			$('#page').prepend('<li class="page-item"><span class="page-link" style="cursor:pointer;" onclick="changeBlock('+(nowBlock-1)+')"> < </span></li>');	
		}
	
		for(var i = pageStart; i<=pageEnd; i++)
			{
				
				if(nowPage==i)
					{
						$('#page').append('<li class="page-item" id="pageNum'+i+'"></li>');
						$('#pageNum'+i).append('<span class="page-link text-white bg-primary" style="cursor:pointer;" onclick="changePage('+i+');">'+i+'</span>')
					}		
				else
					{
						$('#page').append('<li class="page-item" id="pageNum'+i+'"></li>');
						$('#pageNum'+i).append('<span class="page-link" style="cursor:pointer;" onclick="changePage('+i+');">'+i+'</span>')
					}					
			}
		
		if(totalPage>pagePerBlock && nowBlock!=totalBlock)
		{
		   $('#page').append('<li class="page-item"><span class="page-link" style="cursor:pointer" onclick="changeBlock('+(nowBlock+1)+')"> > </span></li>');					   
		}
	}
}

	




function getChangedComment()
{
	 $.ajax({
			type:"post",
			url:"commentChangePage.do",
			data : {"nowPage": nowPage, "num": num},
			async: false,
			success: function(data)
			{
				console.log(data);
				
				nowBlock= data.paging.nowBlock;
				pageStart= data.paging.pageStart;
				pageEnd= data.paging.pageEnd;
									
				comments= data.comments;
				
				setComment();
				setBlock();
						
			},
			error: function(e){
				alert("에러가 발생했습니다.")
			}					
	})
}



  
  $('#commentBtn').click(function(){  
	
	$.ajax({
		type:"post",
		url:"commentInsert.do",
		data : {"nowPage": nowPage, "num": num, "c_content":$("#commentContent").val()},
		async: false,
		success: function(data)
		{
			console.log(data);
			let newComment= data;
			
			if(data.result==1)
			{
				totalRecord= data.paging.totalRecord;
				totalPage= data.paging.totalPage;
				
				$('#commentnum').text(totalRecord);

									
				if((totalRecord!=1 && totalRecord%numPerPage==1) || nowPage!= newComment.paging.nowPage) //페이지가 바뀐 경우
				{
					comments= newComment.comments;	

					if(newComment.paging.totalRecord%numPerPage!=0)
						{
							nowPage= totalPage;						
						}					
					else
						{
							nowPage= data.paging.nowPage;
						}
						nowBlock= data.paging.nowBlock;
						totalBlock=data.paging.totalBlock;
						pageStart= data.paging.pageStart;
						pageEnd= data.paging.pageEnd;
										
					getChangedComment();
					
				}
				else  //nowPage가 같아서 가장 최근 것만 덧붙이는 경우  //이 글이 작성될 때는 nowPage가 같기 때문에 밑에 더 붙음
				{
//					totalRecord= data.paging.totalRecord;
					
					  let today = new Date();
					  today.setHours(today.getHours()-24)
					
//					  		var commentdate= new Date(com.c_date)

					  let commentdate= new Date(newComment.comments.c_date);
					
					if(totalRecord==1)
					{
						$('#commentsWrapper').empty();
					}
					
					//페이지당 게시물 수- 현재페이지*페이지당게시물 수-총 게시물수  인덱스니까 -1
					let curIndex= numPerPage-(nowPage*numPerPage-totalRecord-1);
					
					let htmlForNew="";
					htmlForNew+='<hr><div class="comments"><div class="row"><div class="row col-9">';
//					htmlForNew+='<div class="col font-weight-bold commentNick">'+newComment.comments.c_nick+'</div></div>';
					
					
					htmlForNew+='<div class="col font-weight-bold commentNick">'+newComment.comments.c_nick
					
					if(boardWriter==newComment.comments.c_nick)
					{
						htmlForNew+='<span class="badge badge-success ml-1">작성자</span>';
					}
					if(commentdate>today)
					{
						htmlForNew+='<img src="img/new.png" width="12px" class="ml-1 newImg">';
					}	
					
					htmlForNew+='</div></div>';
					
					
					htmlForNew+='<div class="col-3 text-right dropdown"><a class="mt-1 text-secondary" href="#" role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></a>';
					htmlForNew+='<div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item commentUpdate" href="#">수정</a><a class="dropdown-item commentDelete" href="#">삭제</a> ';
					htmlForNew+='<input type="number" hidden="true" value='+newComment.comments.c_num+'></div></div></div>';
					htmlForNew+='<div class="row"><div class="col py-2 content">'+newComment.comments.c_content+'</div></div>';
					htmlForNew+='<div class="row"><div class="col text-secondary"><span class="commentDate">'+newComment.comments.c_date+'</span><span class="ml-2 replyToContent" style="cursor:pointer;">댓글쓰기</span></div></div>';			
					
					///
					htmlForNew+='<div class="my-1 ml-md-4" id="reComment'+curIndex+'" style="display:none;"><div class="row"><div class="row col-9"><div class="col text-dark font-weight-bold commentNick">'+sessionNick+'</div></div><textarea id="forRecomment'+curIndex+'" cols="50" rows="4" maxlength="500" style="width:100%; resize:none" class="border-1"></textarea></div>'	
					htmlForNew+='<div class="mt-2 text-right"><input type="button" class="btn btn-dark ml-auto" id="replyBtn'+curIndex+'" value="등록"><input type="button" class="btn btn-danger mx-2" id="cancelReplyBtn'+curIndex+'" value="취소"></div></div></div>';					
					$('#commentsWrapper').append(htmlForNew);
									
					
					
//					setBadge(curIndex);
					setBlock();
					
					
				}
			}
	
			
			$('#commentContent').val('');
			
			
		},
		error: function(e){
			alert("에러가 발생했습니다.")
		}					
	})
  
})



function setBadge(i)
{
	  var today = new Date();
	  today.setHours(today.getHours()-24)
	
	  var comDate= Date.parse($('.commentDate').eq(i).text())

		if(boardWriter== $('.commentNick').eq(i).text())
			{
				$('.commentNick').eq(i).append('<span class="badge badge-success ml-1">작성자</span>');		
			}
		if(comDate>today && $('.commentNick').eq(i).children('.newImg').length!=1)
			{
				$('.commentNick').eq(i).append('<img src="img/new.png" width="12px" class="ml-1 newImg">');
			}
}


var c_num;

	$(document).on("click", '.commentDelete', function(){
		 let $selectedComment= $(this).parents('.comments');		
		 
		 if(confirm("정말 삭제하시겠습니까?")==true)
			{					 
			 $.ajax({
					type:"post",
					url:"commentDelete.do",
					data : {"num": num, "c_num": $(this).siblings('input').val()},
					async: false,
					success: function(data)
					{
						console.log(data);						
						if(data.result==1)
						{
							
							totalPage= data.paging.totalPage;
							totalBlock=data.paging.totalBlock;
							totalRecord= data.paging.totalRecord;	
							
							$('#commentnum').text(totalRecord);
																			
							if(totalRecord==0)
							{
								$('#commentsWrapper').empty();
								$('#page').empty();
								setComment();
								
								return false;								
							}
							
							$selectedComment.remove();
							
							if(totalRecord%numPerPage==0 && nowPage!=totalPage &&nowPage*numPerPage>=totalRecord)
							{
								changePage(nowPage-1);
							}
							
							getChangedComment();							
							setBlock();
							
							
						}						
					},
					error: function(e){
						alert("에러가 발생했습니다.")
					}					
				})		 
			}
	})  	
	
	
	var selectedCommentForUpdate;
	var beforeContent;
	
	$(document).on("click", '.commentUpdate', function(){
		
		selectedCommentForUpdate = $(this).parents('.comments');
	
		c_num= $(this).siblings('input').val();		
		beforeContent= selectedCommentForUpdate.find('div.content').text();
		selectedCommentForUpdate.find('div.content').html('<textarea id="contentForUpdate" cols="30" rows="4" maxlength="500" style="width:100%; resize:none" class="border-0">'+beforeContent+'</textarea>');
		$('#contentForUpdate').focus();		
		let $btnWrapper= $('<div class="text-right"></div>');
		$('#contentForUpdate').after($btnWrapper);
		$btnWrapper.append('<input type="button" class="btn btn-primary ml-auto" id="updateBtn" value="수정"><input type="button" class="btn btn-danger mx-2" id="cancelUpdateBtn" value="취소">');		
			
	})	 
	 

	$(document).on("click", '#updateBtn', function(){
				let updateContent= $('#contentForUpdate').val();
							
			 if(confirm("수정하시겠습니까?")==true)
			{					 
				 $.ajax({
						type:"post",
						url:"commentUpdate.do",
						data : {"num": num, "c_num": c_num, "c_content":updateContent},
						async: false,
						success: function(data)
						{
							console.log(data);
							
							if(data==1)
							{
								selectedCommentForUpdate.find('div.content').text(updateContent);
							}								
						},
						error: function(e){
							alert("에러가 발생했습니다.")
						}					
					})
			}
	})		
	
	
	$(document).on("click", '#cancelUpdateBtn', function(){
	selectedCommentForUpdate.find('div.content').text(beforeContent);
	})		




var selectedCommentForReply;
var selectedCommentForReplyIndex;


	//대댓글
	$(document).on("click", '.replyToComment', function(){
		
		selectedCommentForReply= $(this).parents('.comments').find('div[id^="r"]').attr("id");		
		selectedCommentForReplyIndex= selectedCommentForReply.substr(-1);
			
		//다 돌려서 숨겨줌ㅋㅋㅋ 자기 빼고
		for(var i=0; i<numPerPage;i++)
		{
			if(selectedCommentForReply.substr(-1)!=i)
			{
				$('#reComment'+i).hide();	
			}
		}		
		
		$('#'+selectedCommentForReply).toggle();
	
	});

										
	
	
	$(document).on("click", '#replyBtn', function()
			{
				console.log($('#forRecomment').val());
				
				if($('#forRecomment').val()=='')
					{
						alert('내용을 입력해주세요');
						return false;
					}
								 
				 $.ajax({
						type:"post",
						url:"commentInsert.do",
						data : {"c_num": c_num, "depth":1},
						async: false,
						success: function(data)
						{
							console.log(data);						
							if(data==1)
							{
								$('.reComment');
								selectedCommentForReply.append('')
							}						
						},
						error: function(e)
						{
							alert("에러가 발생했습니다.")
						}					
					})	
						
				})	
	
	
		$(document).on("click", '#cancelReplyBtn', function()
		{
			selectedCommentForReply.children().last().remove();
		})



//사진 크기 조절
$(document).ready(function(){
	if (window.matchMedia("(max-width: 770px)").matches == true) {
  		$("#result img").attr("style","width:100%");
}	
})

$(window).resize(function () {
  if (window.matchMedia("(max-width: 770px)").matches == true) {
    $("#result img").attr("style","width:100%");
  } else {
	$("#result img").attr("style","");
  }
});		