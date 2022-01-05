<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>회원 등록</title>
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
          <div class="container-fluid">
			          
			<div class="row justify-content-center">
                 <div class="col-lg-8 py-0">
                     <div class="card shadow-lg border-0 rounded-lg mt-5">
                		<div class="card-header"><h3 class="text-center font-weight-light my-1">회원등록</h3></div>

                        <div class="card-body">
                              <form action="userRegisterAction.do" method="post" onsubmit="return checkUser(this)">
                                  <div class="form-row">
                                      <div class="col-md-6"> 
                                          <div class="form-group">
                                              <label class="small mb-1" for="userName">이름*</label>
                                              <input class="form-control py-2" id="userName" name="userName" type="text" minlength="2" maxlength="10" required >
											  <div></div>
											  
                                          </div>
                                      </div>
                                  </div>

                                  <div class="form-row">
                                      <div class="col-md-6"> 
                                          <div class="form-group">
                                              <label class="small mb-1" for="gender">성별*</label>
                                              <select class="form-control" id="gender" name="gender" required >
	                                              <option value="남성" selected>남성</option>
	                                              <option value="여성">여성</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-md-6"> 
                                          <div class="form-group">
                                              <label class="small mb-1" for="birthdate">생년월일*</label>
                                              <input class="form-control py-2" id="birthdate" name="birthdate" type="date" required/>
                                          </div>
                                      </div>
                                  </div>

                                  <div class="form-group">
                                      <label class="small mb-1" for="nickname">닉네임*</label>
                                      <div class="form-row">
                                          <div class="col-md-9">
                                              <input class="form-control py-2 mb-sm-2" id="nickname" name="nickname" type="text" minlength="2" maxlength="10" required title="한글,숫자,영문자">
                                              <input class="form-control py-2 mb-sm-2" id="confirmNick" type="text" hidden="true">
                                              <div></div>
                                          </div>
                                          <div class="col-md-3">
                                              <button class="btn btn-outline-primary btn-block" type="button" id="nickCheck">중복확인</button>
                                              
                                          </div>
                                      </div>    
                                  </div>


                                  <div class="form-group">
                                      <label class="small mb-1" for="tel">HP(-)*</label>
                                      <div class="form-row">
                                          <div class="col-md-6">
                                 				 <input type="text" class="form-control py-2" pattern="01\d{1}-\d{3,4}-\d{4}" title="01X-000(0)-0000" maxlength="13" id="tel" name="tel">
                                        		<div></div>
                                          
                                          </div>
                                      </div>    
                                  </div>


                                  <div class="form-group">
                                      <label class="small mb-1" for="email">이메일*</label>
                                      <div class="form-row">
                                          <div class="col-md-9">
                                              <input class="form-control py-2 mb-sm-2" id="email" name="email" type="email" aria-describedby="emailHelp" required>
                                              <input class="form-control py-2 mb-sm-2" id="confirmEmail" type="email" hidden="true">    
                                             <div></div>       
                                          </div>
                                          <div class="col-md-3">
                                                  <Button type="button" class="btn btn-outline-primary btn-block" id="emailCheck">중복확인</button>
                                          </div>
                                      </div>
                                  </div>
                              
                                  <div class="form-row">
                                      <div class="col-md-6">
                                          <div class="form-group">
                                              <label class="small mb-1" for="password">비밀번호*</label>
                                              <input class="form-control py-2" id="password" name="password" type="password" required>
                                          </div>
                                      </div>
                                      <div class="col-md-6">
                                          <div class="form-group">
                                              <label class="small mb-1" for="confirmPassword">비밀번호 확인*</label>
                                              <input class="form-control py-2" id="confirmPassword" type="password" required>
                                              <div></div>
                                          </div>
                                      </div>
                                  </div>


                                  <div class="form-group">
                                      <label class="small mb-1" for="address">주소</label>
                                      <div class="form-row">
                                          <div class="col-md-9 mb-sm-2">
                                              <input class="form-control py-2" id="zipcode" name="zipcode" type="text">
                                          </div>
                                          <div class="col-md-3 mb-sm-2">
                                              <button class="btn btn-outline-primary btn-block" type="button" onclick="searchZipcode()">주소찾기</button>
                                          </div>
                                      </div>    
                                          <input class="form-control py-2 mb-2" id="address" name="address" type="text">
                                          <input class="form-control py-2 mb-2" id="addrdetail" name="addrdetail" type="text">
                                  </div>


                                  <div class="form-row">
                                      <div class="col-md-6"> 
                                          <div class="form-group">
                                              <label class="small mb-1" for="trainer">담당 트레이너</label>
                                              <select class="form-control" id="trainer" name="trainer">
                                              <option value="" selected>미정</option>
                                               <c:if test="${result!=null}">
						                           <c:forEach items="${result}" var="t">
						                             <option value="${t.t_id}">${t.t_name }</option>                              
						                           </c:forEach>
						                       </c:if>
                                              </select>
                                          </div>
                                      </div>
                                  </div>

                                  <div class="form-row">
                                      <div class="col-md-6"> 
                                          <div class="form-group">
                                              <label class="small mb-1" for="startdate">시작일</label>
                                              <input class="form-control py-2" id="startdate" name="startdate" type="date">
                                          </div>
                                      </div>
                                  </div>
									<input id="qrcode" name="qrcode" hidden="true"></input>

                                  <div class="form-group mt-4 mb-0">
                                     <input type="submit" class="btn btn-primary btn-block" value="회원등록">
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
    <script src="/myPT/js/qrcode/qrcode.js"></script>
    <script src="/myPT/js/register.js"></script>
    <script src="/myPT/js/zipcode.js"></script>
    
    <script>


    </script>
    
  </body>
</html>
