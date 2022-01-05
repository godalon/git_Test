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
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>템플릿</title>
    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet"/>
    <link href="/myPT/css/widget.css" rel="stylesheet"/>

<!-- 주소  api -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
          <div class="container">
            <div class="row justify-content-center">
              <div class="col-lg-7">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                  <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">트레이너정보</h3>
                  </div>
                  <div class="card-body">
                    <form action="trainerMyUpdate.do" method="post" onsubmit="return check(this)">
                    <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="tid"
                              >아이디</label
                            >
                            <input
                              class="form-control py-2"
                              id="tid"
                              name="tid"
                              type="text"
                              required
                              disabled
                              value="${dto.t_id }"
                            />
                            <input
                              class="form-control py-2"
                              id="hiddentid"
                              name="hiddentid"
                              type="text"
                              required
                              hidden="true"                             
                              value="${dto.t_id }"
                            />
                          </div>
                        </div>
                      </div>
                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="userName"
                              >이름</label
                            >
                            <input
                              class="form-control py-2"
                              id="userName"
                              name="userName"
                              type="text"
                              required
                              disabled
                              value="${dto.t_name }"
                            />
                          </div>
                        </div>
                      </div>
                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="userNick"
                              >닉네임</label
                            >
                            <input
                              class="form-control py-2"
                              id="nickname"
                              name="nickname"
                              type="text"
                              required
                              disabled
                              value="${dto.t_nick }"
                            />
                          </div>
                        </div>
                        
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="gender">성별</label>
                            <select
                              class="form-control"
                              id="gender"
                              name="gender"
                              required
                              disabled
                            >                            
                            <c:choose>
                            <c:when test="${dto.t_gender=='남성' }" >                            
                              <option value="남성" selected>남성</option>
                              <option value="여성">여성</option>                            
                            </c:when>
                            <c:otherwise>                           
                              <option value="남성">남성</option>
                              <option value="여성" selected>여성</option>                            
                            </c:otherwise>
                            </c:choose>
                            </select> 
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="email"
                              >이메일</label
                            >
                            <input
                              class="form-control py-2"
                              id="email"
                              name="email"
                              type="text"
                              required                              
                              value="${dto.t_email }"
                            />
                            
                          </div>
                        </div>
                      </div>
                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="password"
                              >비밀번호</label
                            >
                            <input
                              class="form-control py-4"
                              id="password"
                              name="password"
                              type="password"
                              placeholder="Enter password"
                            />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="confirmPassword"
                              >비밀번호 확인</label
                            >
                            <input
                              class="form-control py-4"
                              id="confirmPassword"
                              type="password"
                              placeholder="Confirm password"
                            />
                            <div></div>
                          </div>
                        </div>
                      </div>
                      
                      <div class="form-group">
                                      <label class="small mb-1" for="tel">HP(-)*</label>
                                      <div class="form-row">
                                          <div class="col-md-6">
                                 				 <input type="text" class="form-control py-2" pattern="01\d{1}-\d{3,4}-\d{4}" title="01X-000(0)-0000" id="tel" name="tel"
                                 				 value="${dto.t_tel }">
                                        		<div></div>
                                          
                                          </div>
                                      </div>    
                                  </div>

                      <div class="form-group">
                        <label class="small mb-1" for="zipcode"
                          >주소</label
                        >
                        <div class="form-row">
                          <div class="col-md-6">
                            <input
                              class="form-control"
                              id="zipcode"
                              name="zipcode"
                              value="${dto.t_zipcode }"
                            />
                          </div>
                          <div class="col-md-3 col-xs-12">
                            <button
                              class="btn btn-outline-primary btn-block"
                              type="button"
                              onclick="searchZipcode()"
                            >
                              주소찾기
                            </button>
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <input
                              class="form-control"
                              id="address"
                              name="address"
                              value="${dto.t_address }"
                            />
                            <input class="form-control py-2 mb-2" id="addrdetail" name="addrdetail" type="text" value="${user.addrdetail }">
                          </div>
                        </div>
                      </div>

                      <div class="form-row mt-4 mb-0">
                        <div class="col-md-6">
                          <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" value="수정">
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <a class="btn btn-light btn-block" href="javascript:history.back()"
                              >취소</a
                            >
                          </div>
                        </div>
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
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
    </script>
    <script src="/myPT/js/scripts.js"></script>
    <script src="/myPT/js/userUpdate.js"></script>
    <script src="/myPT/js/zipcode.js"></script>
  </body>
</html>
