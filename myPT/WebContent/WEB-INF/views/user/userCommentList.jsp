<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>내가 쓴 댓글</title>
    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet"/>
    <link href="/myPT/css/widget.css" rel="stylesheet"/>
	<!-- 데이터 테이블 css -->
	<link
      href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
      rel="stylesheet"
      crossorigin="anonymous"
    />
    <!-- /데이터 테이블 css -->
	
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
      <jsp:include page="/includeFiles/userSideNav.jsp"></jsp:include>
      <!-- /sideNav -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid mt-3">
          <div class="text-left">              
              <a href="userBoardList.do" class="mr-2 btn btn-light btn-sm font-weight-bold">내가 쓴 글</a>
              <a href="userLikeList.do" class="btn btn-light btn-sm font-weight-bold mr-1">좋아요 한 글</a>             
              <a href="userCommentList.do" class="btn btn-light btn-sm font-weight-bold mr-1">내가 쓴 댓글</a>             
              </div>
          	<div class="card mb-4 mt-2">
              <div class="card-header font-weight-bold">
                <i class="fas fa-table mr-1"></i>
               내가 쓴 댓글
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table
                    class="table table-bordered"
                    id="dataTable"
                    width="100%"
                    cellspacing="0"
                  >
                    <thead class="text-center">
                      <tr>
                        <th style="width:20%">게시판</th>
                        <th>댓글</th>
                        <th style="width:10%">원본글</th>
                        <th style="width:20%">작성일</th>                                                                    
                      </tr>
                    </thead>
                    <tbody class="text-center">
                      <c:forEach items="${ccom }" var="item" varStatus="status">
                      <tr>
                        <td>커뮤니티</td> 
                        <td align="left">
                        <div class="ml-2 mr-0" style="display:inline-block;width:400px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${item.c_content }</div>                        
                        </td>
                        <td><a href="boardView.do?num=${item.boardNum }&b=c">글보기</a></td>
                        <td>${item.c_date }</td>                        
                      </tr>
                      </c:forEach>
                      
                      <c:forEach items="${qcom }" var="item" varStatus="status">
                      <tr>
                        <td>질문게시판</td>
                        <td align="left">
                        <div class="ml-2 mr-0" style="display:inline-block;width:400px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${item.c_content }</div>
                        </td>
                        <td><a href="boardView.do?num=${item.boardNum }&b=q">글보기</a></td>
                        <td>${item.c_date }</td>
                      </tr>
                      </c:forEach>
                      
                      <c:forEach items="${pcom }" var="item" varStatus="status">
                      <tr>
                        <td>포토게시판</td>
                        <td align="left">
                        <div class="ml-2 mr-0" style="display:inline-block;width:400px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${item.c_content }</div>
                        </td>
                        <td><a href="boardView.do?num=${item.boardNum }&b=p">글보기</a></td>
                        <td>${item.c_date }</td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
    </script>
    <script src="/myPT/js/scripts.js"></script>
    
    <!-- 데이터 테이블 js -->
    <script src="/myPT/js/dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
    <script src="/myPT/assets/demo/datatables-demo.js"></script>
    <!-- /데이터 테이블 js -->
    
  </body>
</html>
