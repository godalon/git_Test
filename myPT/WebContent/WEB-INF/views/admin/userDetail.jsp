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
                      회원 관리
                    </h3>
                  </div>

                  <div class="card-body">
                  <form action="adminUserUpdate.do" method="post" onsubmit="return check(this)">              
                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="userID"
                              >아이디</label
                            >
                            <input
                              class="form-control py-2"
                              id="userID"
                              name="userID"
                              type="text"
                              required
                              disabled
                              value="${user.id }"
                            />
                            <!-- 실제 보내는 ID값 -->
                            <input
                              class="form-control py-2"
                              id="hiddenUserID"
                              name="hiddenUserID"
                              type="text"
                              hidden="true"
                              required                              
                              value="${user.id }"
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
                              value="${user.name }"
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
                            <c:when test="${user.gender=='남성' }" >                            
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
                              value="${user.birth }"
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
                              minlength="2"
                              maxlengh="10" 
                              title="한글,숫자,영문자"                            
                              required
                              value="${user.nick }"
                            />
                            <input class="form-control py-2 mb-sm-2" id="confirmNick" type="text" hidden="true">
                            <input class="form-control py-2 mb-sm-2" id="nowNick" type="text" hidden="true" value="${user.nick}">
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
                              value="${user.email }"
                            />
                            <input class="form-control py-2 mb-sm-2" id="confirmEmail" type="email" hidden="true" value="${user.email }">
                            <input class="form-control py-2 mb-sm-2" id="nowEmail" type="email" hidden="true" value="${user.email }">
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
                              required
                              value="${user.pw }"
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
                              required
                              value="${user.pw }"
                            />
                            <div></div>
                            
                          </div>
                        </div>
                      </div>
                      
                      <div class="form-group">
                                      <label class="small mb-1" for="tel">HP(-)</label>
                                      <div class="form-row">
                                          <div class="col-md-6">
                                 				 <input type="text" class="form-control py-2" pattern="01\d{1}-\d{3,4}-\d{4}" title="01X-000(0)-0000" id="tel" name="tel"
                                 				 value="${user.tel }">
                                        		<div></div>
                                          
                                          </div>
                                      </div>    
                                  </div>

                      <div class="form-group">
                        <label class="small mb-1" for="zipcode">주소</label>
                        <div class="form-row">
                          <div class="col-md-9 mb-sm-2">
                            <input
                              class="form-control py-2"
                              id="zipcode"
                              name="zipcode"
                              type="text"
                              value="${user.zipcode }"
                              
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
                          value="${user.address}"
                        />
                        <input class="form-control py-2 mb-2" id="addrdetail" name="addrdetail" type="text" value="${user.addrdetail }">
                      </div>

                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="trainer"
                              >담당 트레이너</label
                            >
                            <select
                              class="form-control"
                              id="trainer"
                              name="trainer"
                            >
                            <option value="null" selected>미정</option>
                            <c:if test="${arr!=null }">
                            <c:forEach items="${arr }" var="t">
                            	<c:choose>
                            	<c:when test="${user.tid==t.t_id }">
                            
                              <option value="${t.t_id }" selected>${t.t_name }</option>                              
                              </c:when>
                              <c:otherwise>
                              <option value="${t.t_id }">${t.t_name }</option>
                              </c:otherwise>
                              </c:choose>
                              </c:forEach>
                              </c:if>
                            </select>
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="startDate"
                              >시작일</label
                            >
                            <input
                              class="form-control py-2"
                              id="startDate"
                              name="startDate"
                              type="date"
                              required
                              value="${user.startdate }"
                            />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="endDate"
                              >만료일</label
                            >
                            <input class="form-control py-2" id="endDate"
                            name="endDate" type="date" required
                            value="${user.enddate }"/>
                          </div>
                        </div>
                      </div>

                      <div class="form-row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label class="small mb-1" for="remainNum"
                              >남은횟수</label
                            >
                            <input
                              class="form-control py-2"
                              id="remainNum"
                              name="remainNum"
                              type="number"
                              min="0"
                              required
                              value="${user.ptcount }"
                            />
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
                            <a class="btn btn-light btn-block" onclick="cancel()"                            
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
    <script src="/myPT/js/userdetail.js"></script>
    <script src="/myPT/js/zipcode.js"></script>
  </body>
</html>
