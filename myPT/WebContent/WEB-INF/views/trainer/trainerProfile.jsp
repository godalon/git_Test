<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>마이페이지</title>
    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet"/>
    <link href="/myPT/css/widget.css" rel="stylesheet"/>

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
      <jsp:include page="/includeFiles/trainerSideNav.jsp"></jsp:include>
      <!-- /sideNav -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid">
            <h1 class="mt-4 mb-4">마이페이지</h1>
            <div class="row">
              <div class="col-md-6">
                <div class="card mb-4 p-0">
                  <div class="card-header">
                    <i class="fas fa-list"></i>
                   나의 활동
                  </div>
                  <div class="card-body">
                    <div class="card bg-danger text-white mb-4">
                      <div class="card-body">
                        <div class="widget-content p-0">
                          <div class="widget-content-wrapper">
                            <div class="widget-content-left">
                              <div class="widget-heading text-white">좋아요 누른 글</div>
                              <div class="widget-subheading"></div>
                            </div>
                            <div class="widget-content-right">
                              <div class="widget-numbers">${lcnt }회</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="userLikeList.do">상세 보기</a>
                        <div class="small text-white">
                          <i class="fas fa-angle-right"></i>
                        </div>
                      </div>
                    </div>
                    <div class="card text-white mb-4" style="background-color: rgba(4, 15, 119, 0.836)">
                      <div class="card-body">
                        <div class="widget-content p-0">
                          <div class="widget-content-wrapper">
                            <div class="widget-content-left">
                              <div class="widget-heading text-white">내가 작성한 글</div>
                              <div class="widget-subheading"></div>
                            </div>
                            <div class="widget-content-right">
                              <div class="widget-numbers">${bcnt }개</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="userBoardList.do">상세 보기 </a>
                        <div class="small text-white">
                          <i class="fas fa-angle-right"></i>
                        </div>
                      </div>
                    </div>
                    <div class="card bg-dark text-white mb-4">
                      <div class="card-body">
                        <div class="widget-content p-0">
                          <div class="widget-content-wrapper">
                            <div class="widget-content-left">
                              <div class="widget-heading text-white">댓글 작성 글</div>
                              <div class="widget-subheading"></div>
                            </div>
                            <div class="widget-content-right">
                              <div class="widget-numbers">${ccnt }개</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="userCommentList.do">상세 보기</a>
                        <div class="small text-white">
                          <i class="fas fa-angle-right"></i>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="card mb-4 p-0">
                  <div class="card-header">
                    <i class="fas fa-user-circle fa-lg"></i>
                    나의 정보
                  </div>
                  <div class="card-body">
                    <form>
                      <div class="form-row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label class="small mb-1" for="trainerID">아이디</label>
                            <input
                              class="form-control py-2"
                              id="trainerID"
                              name="trainerID"
                              type="text"
                              disabled
                              value="${dto.t_id }"
                            />
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label class="small mb-1" for="userName">이름</label>
                            <input class="form-control py-2" id="userName" name="userName" type="text" value="${dto.t_name }" disabled />
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label class="small mb-1" for="gender">성별</label>
                            <input class="form-control py-2" id="gender" name="gender" type="text" value="${dto.t_gender }" disabled />
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label class="small mb-1" for="birthdate">생년월일</label>
                            <input class="form-control py-2" id="birthdate" name="birthdate" type="text" value="${dto.t_birth }" disabled />
                          </div>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="small mb-1" for="nickname">닉네임</label>
                        <div class="form-row">
                          <div class="col-md-12">
                            <input class="form-control py-2 mb-sm-2" id="nickname" name="nickname" type="text" value="${dto.t_nick }" disabled />
                          </div>
                        </div>
                      </div>

                      <div class="form-row mt-4 mb-0">
                        <a class="btn btn-primary btn-block" href="trainerMyDetail.do">수정</a>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
    
    <!-- profile 모달 -->
    <jsp:include page="/includeFiles/modalProfile.jsp"></jsp:include>
    <!-- /profile 모달 -->
    
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
    </script>
    <script src="/myPT/js/scripts.js"></script>
  </body>
</html>
