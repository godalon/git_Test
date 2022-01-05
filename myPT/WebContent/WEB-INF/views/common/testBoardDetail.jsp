<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>${dto.title}</title>
    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet"/>
    <link href="/myPT/css/widget.css" rel="stylesheet"/>
    <link href="/myPT/css/like.css" rel="stylesheet"/>
    <link href="/myPT/css/boardView.css" rel="stylesheet"/>

    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
    ></script>
    

  </head>
  <body class="sb-nav-fixed">
    <!-- nav -->
    <jsp:include page="/includeFiles/nav.jsp"></jsp:include>
    <!-- nav -->

    <!-- QR 모달 -->
    <jsp:include page="/includeFiles/modalQR.jsp"></jsp:include>
    <!--//QR 모달-->

    <div id="layoutSidenav">
      <!-- sideNav -->
      <c:choose>
      <c:when test="${sessionScope.grade==0 }">
      <jsp:include page="/includeFiles/adminSideNav.jsp"></jsp:include>
      </c:when>
      <c:when test="${sessionScope.grade==1 }" >
      <jsp:include page="/includeFiles/trainerSideNav.jsp"></jsp:include>
      </c:when>
      <c:otherwise>
      <jsp:include page="/includeFiles/userSideNav.jsp"></jsp:include>
      </c:otherwise>
      </c:choose> 
      <!-- /sideNav -->
      <div id="layoutSidenav_content">
        
          <div class="container py-3">
          <div>
         
            <header class="px-4">
              <h3>글보기</h3>
              <div class="text-right">              
              <a href="reply.do" class="mr-2" type="submit()">답변</a>/
              <a href="#" class="ml-2">목록으로</a>
              </div>
            </header>
          </div>

          <div>
            <main>             
                <div class="cardWrapper p-2">
                 <form>
                  <div class="card h-100">
                    <div class="card-body p-1">
                      <ul class="list-group list-group-flush h-100">
                        <li class="list-group-item">
                          <div class="row">
                            <div class="col-lg-12">
                            <div class="row ml-2 mt-3 mb-0 d-flex justify-content-between">
                            <input type="text" id="num" name="num" hidden="true" value="${dto.num }">                            
                            <input type="text" id="board" name="board" hidden="true" value="cboard">
                              <h3 class=" ">${dto.title }</h3>
                              <!-- 좋아요버튼 -->
                        <div class="row m-0 likebutton">
                        <c:choose>
                        <c:when test="${lflag==1 }">
                          <input type="checkbox" class="like" id="like" checked="checked"/>
                          </c:when>
                          <c:otherwise>
                          <input type="checkbox" class="like" id="like"/>
                          </c:otherwise>
                          </c:choose>
                          <div>
                            <svg
                              id="heart-svg"
                              viewBox="467 392 58 57"
                              xmlns="http://www.w3.org/2000/svg"
                              style="width: 50px"
                            >
                              <g
                                id="Group"
                                fill="none"
                                fill-rule="evenodd"
                                transform="translate(467 392)"
                              >
                                <path
                                  d="M29.144 20.773c-.063-.13-4.227-8.67-11.44-2.59C7.63 28.795 28.94 43.256 29.143 43.394c.204-.138 21.513-14.6 11.44-25.213-7.214-6.08-11.377 2.46-11.44 2.59z"
                                  id="heart"
                                  fill="#AAB8C2"
                                />
                                <circle
                                  id="main-circ"
                                  fill="#E2264D"
                                  opacity="0"
                                  cx="29.5"
                                  cy="29.5"
                                  r="1.5"
                                />

                                <g
                                  id="grp7"
                                  opacity="0"
                                  transform="translate(7 6)"
                                >
                                  <circle
                                    id="oval1"
                                    fill="#9CD8C3"
                                    cx="2"
                                    cy="6"
                                    r="2"
                                  />
                                  <circle
                                    id="oval2"
                                    fill="#8CE8C3"
                                    cx="5"
                                    cy="2"
                                    r="2"
                                  />
                                </g>

                                <g
                                  id="grp6"
                                  opacity="0"
                                  transform="translate(0 28)"
                                >
                                  <circle
                                    id="oval1"
                                    fill="#CC8EF5"
                                    cx="2"
                                    cy="7"
                                    r="2"
                                  />
                                  <circle
                                    id="oval2"
                                    fill="#91D2FA"
                                    cx="3"
                                    cy="2"
                                    r="2"
                                  />
                                </g>

                                <g
                                  id="grp3"
                                  opacity="0"
                                  transform="translate(52 28)"
                                >
                                  <circle
                                    id="oval2"
                                    fill="#9CD8C3"
                                    cx="2"
                                    cy="7"
                                    r="2"
                                  />
                                  <circle
                                    id="oval1"
                                    fill="#8CE8C3"
                                    cx="4"
                                    cy="2"
                                    r="2"
                                  />
                                </g>

                                <g
                                  id="grp2"
                                  opacity="0"
                                  transform="translate(44 6)"
                                >
                                  <circle
                                    id="oval2"
                                    fill="#CC8EF5"
                                    cx="5"
                                    cy="6"
                                    r="2"
                                  />
                                  <circle
                                    id="oval1"
                                    fill="#CC8EF5"
                                    cx="2"
                                    cy="2"
                                    r="2"
                                  />
                                </g>

                                <g
                                  id="grp5"
                                  opacity="0"
                                  transform="translate(14 50)"
                                >
                                  <circle
                                    id="oval1"
                                    fill="#91D2FA"
                                    cx="6"
                                    cy="5"
                                    r="2"
                                  />
                                  <circle
                                    id="oval2"
                                    fill="#91D2FA"
                                    cx="2"
                                    cy="2"
                                    r="2"
                                  />
                                </g>

                                <g
                                  id="grp4"
                                  opacity="0"
                                  transform="translate(35 50)"
                                >
                                  <circle
                                    id="oval1"
                                    fill="#F48EA7"
                                    cx="6"
                                    cy="5"
                                    r="2"
                                  />
                                  <circle
                                    id="oval2"
                                    fill="#F48EA7"
                                    cx="2"
                                    cy="2"
                                    r="2"
                                  />
                                </g>

                                <g
                                  id="grp1"
                                  opacity="0"
                                  transform="translate(24)"
                                >
                                  <circle
                                    id="oval1"
                                    fill="#9FC7FA"
                                    cx="2.5"
                                    cy="3"
                                    r="2"
                                  />
                                  <circle
                                    id="oval2"
                                    fill="#9FC7FA"
                                    cx="7.5"
                                    cy="2"
                                    r="2"
                                  />
                                </g>
                              </g>
                            </svg>
                          </div>                          
                        </div>
                        <!-- 좋아요버튼 -->
                        </div>
                              
                              <h5 class="mt-2 ml-2">${dto.writer }</h5>
                              <div class="row mt-2 ml-2 mb-0">
                                <div class="container pl-0 mb-0">
                                  <p class="text-muted mb-0 float-left">
                                    ${dto.date }
                                  </p>
                                  <div class="row float-right">
                                    <p class="mb-0">
                                      조회수 <span id="readnum" class=""
                                        >${dto.hit }</span
                                      >  ｜
                                    </p>
                                    <p class="mb-0 ml-2">
                                      추천수 <span id="likenum" class=""
                                        >${dto.like }</span
                                      > ｜
                                    </p>
                                    <p class="mb-0 ml-2 mr-1">
                                      댓글 <span id="commentnum" class=""
                                        >10</span
                                      >
                                    </p>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </li>
                        <li class="list-group-item">
                          <div id="result">${dto.content }</div>
                        </li>
                      </ul>
                    </div>
                  </div>
                  </form>
                            
                  
		<!-- 댓글 s-->
                  <div class="card">
                  	<div class="card-body" id="commentsWrapper">
                  </div>
                </div>  
         <!-- 댓글 e-->
                
                 
                								
		<!-- 페이징 및 블럭 Start -->
           <ul class="pagination mt-5 ml-5 justify-content-center" id="page">
           </ul>
          <!-- 페이징 및 블럭 e-->
           
                
      
                <div class="card my-4 border border-secondary">
                  <div class="card-body p-3" style="width:100%;">
	            	<div class="mb-2 font-weight-bold">${sessionScope.nick}</div>
	                  <textarea name="commentContent" id="commentContent" cols="30" rows="5" maxlength="500"
                              style="width:100%; resize:none" class="border-0"></textarea>
                     </div>
	           
            	 	  <div class="card-footer bg-white">
                      <input type="button" class="btn btn-dark float-right" id="commentBtn" value="등록">
                  </div>
               </div>
     

                                      
                         
            </main>
          </div>
        </div>
      </div>
    </div>
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
    </script>
    <script src="/myPT/js/scripts.js"></script>
    <script src="/myPT/js/like.js"></script>
    <!-- 게시판 관련 -->
  <script type="text/javascript" src="assets/summernote-0.8.18-dist/summernote-bs4.js"></script>
  <script src="assets/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
    <script src="/myPT/js/boardView.js"></script>
  
  <script>  
  
  
		  
var nowPage= ${paging.nowPage};
var numPerPage= ${paging.numPerPage};
var pagePerBlock= ${paging.pagePerBlock};
var totalPage= ${paging.totalPage};
var nowBlock= ${paging.nowBlock}
var pageStart= ${paging.pageStart}
var pageEnd= ${paging.pageEnd};
var totalBlock= ${paging.totalBlock};

var sessionNick= '${sessionScope.nick}';
var sessionGrade= ${sessionScope.grade};

var comments= ${dto.comments};

var num = ${dto.num};
var boardWriter= '${dto.writer}'; 

var boardNum= ${dto.num};

setComment(comments.length);
setBlock();



// function changePage(page)
// {
// 	  nowPage= page;
// 	  getChangedComment();
// }

// function changeBlock(block)
// {
// 	 nowPage= pagePerBlock*(block-1)+1;	  
// 	 getChangedComment();
// }


// function setComment()
// {
// 	let htmlForNew=""; 
	
// 	$.each (comments, function (index, com) 
// 	{
// 		htmlForNew+='<div class="comments"><div class="row"><div class="row col-9">';
// 		htmlForNew+='<div class="col font-weight-bold commentNick">'+com.c_nick+'</div></div>';			
											
// 		if(sessionNick== com.c_nick)
// 			{
// 				htmlForNew+='<div class="col-3 text-right dropdown"><a class="mt-1 text-secondary" href="#" role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></a><div class="dropdown-menu dropdown-menu-right">';
// 				htmlForNew+='<a class="dropdown-item commentUpdate">수정</a><a class="dropdown-item commentDelete">삭제</a><input type="number" hidden="true" value='+com.c_num+'></div></div>';
// 			}
// 		else if(sessionGrade==0)
// 			{
// 				htmlForNew+='<div class="col-3 text-right dropdown"><a class="mt-1 text-secondary" href="#" role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></a><div class="dropdown-menu dropdown-menu-right">';
// 				htmlForNew+='<a class="dropdown-item commentDelete">삭제</a><input type="number" hidden="true" value='+com.c_num+'></div></div>';
// 			}
									
// 		htmlForNew+='</div><div class="row"><div class="col py-2 content">'+com.c_content+'</div></div>';
// 		htmlForNew+='<div class="row"><div class="col text-secondary"><span class="commentDate">'+com.c_date+'</span><span class="ml-2">댓글쓰기</span></div></div></div>';						
			
// 	});
	
// 	$('#commentsWrapper').append(htmlForNew);
// 	setBadge(comments.length);

	

// 	if(totalPage>0 && nowBlock>1)
// 		{
// 		   $('#page').prepend('<li class="page-item"><span class="page-link" style="cursor:pointer;" onclick="changeBlock('+(nowBlock-1)+')"> < </span></li>');					   
// 		}
	
// 	for(var i = pageStart; i<=pageEnd; i++)
// 		{
			
// 			if(nowPage==i)
// 				{
// 					$('#page').append('<li class="page-item" id="pageNum'+i+'"></li>');
// 					$('#pageNum'+i).append('<span class="page-link text-white bg-primary" style="cursor:pointer;" onclick="changePage('+i+');">'+i+'</span>')
// 				}		
// 			else
// 				{
// 					$('#page').append('<li class="page-item" id="pageNum'+i+'"></li>');
// 					$('#pageNum'+i).append('<span class="page-link" style="cursor:pointer;" onclick="changePage('+i+');">'+i+'</span>')
// 				}					
// 		}
	
// 	if(totalPage>pagePerBlock && nowBlock!=totalBlock)
// 	{
// 	   $('#page').append('<li class="page-item"><span class="page-link" style="cursor:pointer" onclick="changeBlock('+(nowBlock+1)+')"> > </span></li>');					   
// 	}
// }



// function getChangedComment()
// {
// 	 $.ajax({
// 			type:"post",
// 			url:"commentChangePage.do",
// 			data : {"nowPage": nowPage, "num": num},
// 			async: false,
// 			success: function(data)
// 			{
// 				console.log(data);
				
// 				nowBlock= data.paging.nowBlock;
// 				pageStart= data.paging.pageStart;
// 				pageEnd= data.paging.pageEnd;
									
// 				comments= data.comments;
				
// 				$('#commentsWrapper').empty();
// 				$('#page').empty();
				
				
// 				setComment(comments.length);
// 				setEvent();
						
// 			},
// 			error: function(e){
// 				alert("에러가 발생했습니다.")
// 			}					
// 	})
// }


//   var boardNum= ${dto.num};
  
//   $('#commentBtn').click(function(){  
	
// 	$.ajax({
// 		type:"post",
// 		url:"commentInsert.do",
// 		data : {"nowPage": nowPage, "num": num, "c_content":$("#commentContent").val()},
// 		async: false,
// 		success: function(data)
// 		{
// 			console.log(data);
// 			let newComment= data;
// 			let lengthOfBeforeComments = comments.length=undefined?1:comments.length+1;
			
// 			if(nowPage!= newComment.paging.nowPage) //페이지가 바뀐 경우
// 				{
// 					comments= newComment.comments;	

// 					nowPage= data.paging.nowPage;
// 					nowBlock= data.paging.nowBlock;
// 					pageStart= data.paging.pageStart;
// 					pageEnd= data.paging.pageEnd;
										
// 					getChangedComment()
// 				}
// 			else //nowPage가 같아서 가장 최근 것만 덧붙이는 경우
// 			{
// 				let htmlForNew="";
// 				htmlForNew+='<div class="comments"><div class="row"><div class="row col-9">';
// 				htmlForNew+='<div class="col font-weight-bold commentNick">'+newComment.comments.c_nick+'</div></div>';
// 				htmlForNew+='<div class="col-3 text-right dropdown"><a class="mt-1 text-secondary" href="#" role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></a>';
// 				htmlForNew+='<div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item commentUpdate" href="#">수정</a><a class="dropdown-item commentDelete" href="#">삭제</a> ';
// 				htmlForNew+='<input type="number" hidden="true" value='+newComment.comments.c_num+'></div></div></div>';
// 				htmlForNew+='<div class="row"><div class="col py-2 content">'+newComment.comments.c_content+'</div></div>';
// 				htmlForNew+='<div class="row"><div class="col text-secondary"><span class="commentDate">'+newComment.comments.c_date+'</span><span class="ml-2">댓글쓰기</span></div></div></div>';			

// 				$('#commentsWrapper').append(htmlForNew);
// 				setBadge(lengthOfBeforeComments);
// 			}
			
// 			$('#commentContent').val('');
			
			
// 		},
// 		error: function(e){
// 			alert("에러가 발생했습니다.")
// 		}					
// 	})
  
// })



// function setBadge(numOfComments)
// {
// 	  var today = new Date();
// 	  today.setHours(today.getHours()-24)
	
// 	for(var i=0; i<numOfComments;i++)
// 	{
// 		  var commentdate= new Date($('.commentDate').eq(i).text())

// 		if(boardWriter== $('.commentNick').eq(i).text())
// 			{
// 				$('.commentNick').eq(i).append('<span class="badge badge-success ml-1">작성자</span>');		
// 			}
// 		if(commentdate>today && $('.commentNick').eq(i).children('.newImg').length!=1)
// 			{
// 				$('.commentNick').eq(i).append('<img src="img/new.png" width="12px" class="ml-1 newImg">');
// 			}
// 	}
// }


// function setEvent()
// {  
// 	$('.commentDelete').click(function(){	 
// 		 let $selectedComment= $(this).parents('.comments');		 
// 		 if(confirm("정말 삭제하시겠습니까?")==true)
// 			{					 
// 			 $.ajax({
// 					type:"post",
// 					url:"commentDelete.do",
// 					data : {"num": num, "c_num": $(this).siblings('input').val()},
// 					async: false,
// 					success: function(data)
// 					{
// 						console.log(data);						
// 						if(data==1)
// 						{
// 							$selectedComment.remove();
// 						}						
// 					},
// 					error: function(e){
// 						alert("에러가 발생했습니다.")
// 					}					
// 				})		 
// 			}
// 	})  	
// 	$('.commentUpdate').click(function(){		
// 		let $selectedComment= $(this).parents('.comments');	
// 		let c_num= $(this).siblings('input').val();		
// 		let $beforeContent= $selectedComment.find('div.content').text();
// 		$selectedComment.find('div.content').html('<textarea id="contentForUpdate" cols="30" rows="3" maxlength="500" style="width:100%; resize:none" class="border-0">'+$beforeContent+'</textarea>');
// 		$('#contentForUpdate').focus();		
// 		let $btnWrapper= $('<div class="text-right"></div>');
// 		$('#contentForUpdate').after($btnWrapper);
// 		$btnWrapper.append('<input type="button" class="btn btn-primary ml-auto" id="updateBtn" value="수정"><input type="button" class="btn btn-danger mx-2" id="cancelBtn" value="취소">');		
// 		$('#cancelBtn').click(function(){
// 			$selectedComment.find('div.content').text($beforeContent);
// 			return false;
// 		})		
// 		$('#updateBtn').click(function()
// 				{
// 					let updateContent= $('#contentForUpdate').val();				
// 				 if(confirm("수정하시겠습니까?")==true)
// 				{					 
// 					 $.ajax({
// 							type:"post",
// 							url:"commentUpdate.do",
// 							data : {"num": num, "c_num": c_num, "c_content":updateContent},
// 							async: false,
// 							success: function(data)
// 							{
// 								console.log(data);
// 								if(data==1)
// 								{
// 									$selectedComment.find('div.content').text(updateContent);
// 								}								
// 							},
// 							error: function(e){
// 								alert("에러가 발생했습니다.")
// 							}					
// 						})
// 				}
// 		})		 				
// 	})	  
// }




  
  </script>
  
  
  </body>
</html>
