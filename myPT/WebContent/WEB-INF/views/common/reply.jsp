<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    

    <title>답글쓰기</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />

    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet" />
    <link href="/myPT/css/widget.css" rel="stylesheet" />
    <link
      rel="stylesheet"
      href="assets/summernote-0.8.18-dist/summernote-bs4.css"
    />
    <link href="/myPT/css/write.css" rel="stylesheet" />

    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
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
              <h4>답글쓰기s</h4>
              <div class="text-right">
              <c:choose>
              <c:when test="${sessionScope.board=='cboard' }">
              <a href="moveCommunity.do" class="btn btn-light btn-sm font-weight-bold mr-1">목록</a>
              </c:when>
              <c:when test="${sessionScope.board=='qboard' }">
              <a href="moveQuestion.do" class="btn btn-light btn-sm font-weight-bold mr-1">목록</a>
              </c:when>                  
              </c:choose>             
              </div>
            </header>
          </div>

          <div>
            <main>
              <div class="cardWrapper p-2">
                <div class="card border border-secondary h-100">
                  
                    <div class="card-body p-1">
                    <form
                    action="boardReply.do"
                    method="post"
                    onsubmit="return repForm()"                    
                  >
                      <ul class="list-group list-group-flush h-100">
                        <li class="list-group-item">                                                     
                            <div class="col-lg-12 pl-lg-0">
                              <input
                                class="form-control"
                                id="subject"
                                name="title"
                                placeholder="제목"
                                value="${sessionScope.dto.title }"
                              />
                            </div>                          
                        </li>
                        <li class="list-group-item">
                          <textarea class="summernote" name="" id="summer"></textarea>
                          <input
                            type="text"
                            name="content"
                            id="content"
                            hidden="true"
                          />
                        </li>
                      </ul>                      
                      <input type="text" name="ref" value="${sessionScope.dto.ref}" hidden="true">
                      <input type="text" name="pos" value="${sessionScope.dto.pos}" hidden="true">
                      <input type="text" name="depth" value="${sessionScope.dto.depth}" hidden="true">
                      <div
                        class="text-right px-4 d-flex justify-content-end align-items-center"
                      >
                        <!-- 										<button class="btn btn-outline-warning m-2">수정하기</button> -->
                        <input
                          type="submit"
                          class="btn btn-primary"
                          value="글쓰기"
                        />
                      </div>
                      </form>
                    </div>
                  
                </div>
              </div>
            </main>
          </div>
        </div>
      </div>
    </div>
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
      crossorigin="anonymous"
    ></script>

    <script
      type="text/javascript"
      src="assets/summernote-0.8.18-dist/summernote-bs4.js"
    ></script>
    <script src="/myPT/assets/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
    <script src="/myPT/js/scripts.js"></script>
    <script src="/myPT/js/write.js"></script>
    <script src="/myPT/js/boardAlert.js"></script>
  </body>
</html>
