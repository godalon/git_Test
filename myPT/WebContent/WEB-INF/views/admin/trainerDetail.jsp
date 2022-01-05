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
    <title>트레이너 관리</title>
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
      <jsp:include page="/includeFiles/adminSideNav.jsp"></jsp:include>
      <!-- /sideNav -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container">
            <div class="row justify-content-center">
              <div class="col-lg-8">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                  <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">
                      트레이너 관리
                    </h3>
                  </div>                  
                  <div class="card-body">
                    <form action="adminTrainerUpdate.do" method="post" enctype="multipart/form-data" onsubmit="return check(this)">
                    <div class="form-row" id="tphoto">
                        <div class="col-md-6"> 
                            <div class="form-group">
                            <img class="col-md-12 col-xs-6"  src="img/TrainerPhoto/${t.t_photo }">
                            <input type="text" hidden="true" value="${t.t_photo }" name="prevphoto"> 
                                <label class="small mb-1" for="photo">                                	
                                	사진 변경 시 파일 선택(2mb미만의 .jpg .png)                                	          	
                                </label>
                                <input class="py-2" id="photo" name="photo" type="file" accept=".jpg, .png">
                                <div id="imgThumbnail"></div>
                            </div>
                        </div>
                    </div>
                    
                   
                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="trainerID"
                              >아이디</label
                            >
                            <input
                              class="form-control py-2"
                              id="trainerID"
                              name="trainerID"
                              type="text"
                              required
                              disabled
                              value="${t.t_id }"
                            />
                            <input
                              class="form-control py-2"
                              id="hiddentrainerID"
                              name="hiddentrainerID"
                              type="text"
                              required
                              hidden="true"
                              value="${t.t_id }"
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
                              id="trainerName"
                              name="trainerName"
                              type="text"
                              required
                              value="${t.t_name }"
                            />
                            <div></div>
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="gender">성별</label>
                            <select
                              class="form-control"
                              id="gender"
                              name="gender"
                              required
                            >
                              <c:choose>
                            <c:when test="${t.t_gender=='남성' }" >                            
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
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="birthdate"
                              >생년월일</label
                            >
                            <input
                              class="form-control py-2"
                              id="birthdate"
                              name="birthdate"
                              type="date"
                              required
                              value="${t.t_birth }"
                            />
                          </div>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="small mb-1" for="nickname">닉네임</label>
                        <div class="form-row">
                          <div class="col-md-9">
                            <input
                              class="form-control py-2 mb-sm-2"
                              id="nickname"
                              name="nickname"
                              type="text"
                              required
                              value="${t.t_nick }"
                            />
                            <input class="form-control py-2 mb-sm-2" id="confirmNick" type="text" hidden="true">
                            <input class="form-control py-2 mb-sm-2" id="nowNick" type="text" hidden="true" value="${t.t_nick}">
                            <div></div>
                          </div>
                          <div class="col-md-3">
                            <button
                              class="btn btn-outline-primary btn-block"
                              type="button"
                              id="nickCheck"
                            >
                              중복확인
                            </button>
                          </div>
                        </div>
                      </div>
                      
                      <div class="form-group">
                                      <label class="small mb-1" for="tel">HP(-)*</label>
                                      <div class="form-row">
                                          <div class="col-md-6">
                                 				 <input type="text" class="form-control py-2" pattern="01\d{1}-\d{3,4}-\d{4}" title="01X-000(0)-0000" id="tel" name="tel"
                                 				 value="${t.t_tel }">
                                        		<div></div>
                                          
                                          </div>
                                      </div>    
                                  </div>

                      <div class="form-group">
                        <label class="small mb-1" for="email">이메일</label>
                        <div class="form-row">
                          <div class="col-md-9">
                            <input
                              class="form-control py-2 mb-sm-2"
                              id="email"
                              name="email"
                              type="email"
                              aria-describedby="emailHelp"
                              required
                              value="${t.t_email }"
                            />
                            <input class="form-control py-2 mb-sm-2" id="confirmEmail" type="email" hidden="true" value="${t.t_email }">
                            <input class="form-control py-2 mb-sm-2" id="nowEmail" type="email" hidden="true" value="${t.t_email }">
                          	<div></div>
                          </div>
                          <div class="col-md-3">
                            <button
                              type="button"
                              class="btn btn-outline-primary btn-block"
                              id="emailCheck"
                            >
                              중복확인
                            </button>
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
                              class="form-control py-2"
                              id="password"
                              name="password"
                              type="password"
                              value="${t.t_pw }"
                              required
                            />                            
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="confirmPassword"
                              >비밀번호 확인</label
                            >
                            <input
                              class="form-control py-2"
                              id="confirmPassword"
                              type="password"
                              value="${t.t_pw }"
                              required
                            />
                            <div></div>                            
                          </div>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="small mb-1" for="address">주소</label>
                        <div class="form-row">
                          <div class="col-md-9 mb-sm-2">
                            <input
                              class="form-control py-2"
                              id="zipcode"
                              name="zipcode"
                              type="text"
                              value="${t.t_zipcode }"
                            />
                          </div>
                          <div class="col-md-3">
                            <button
                              class="btn btn-outline-primary btn-block"
                              type="button"
                              onclick="searchZipcode()"
                            >
                              주소찾기
                            </button>
                          </div>
                        </div>
                        <input
                          class="form-control py-2 mb-2"
                          id="address"
                          name="address"
                          type="text"
                          value="${t.t_address }"
                        />
                        <input class="form-control py-2 mb-2" id="addrdetail" name="addrdetail" type="text" value="${t.t_addrdetail }">
                      </div>

                      <div class="form-row mt-4 mb-0">
                        <div class="col-md-6">
                          <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" value="수정">
                            
                          </div>
                          </div>
                        
                        <div class="col-md-6">
                          <div class="form-group">
                            <a class="btn btn-light btn-block" onclick="cancel()">취소</a>
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
    <script src="/myPT/js/trainerdetail.js"></script>
    <script src="/myPT/js/zipcode.js"></script>
  </body>
</html>
