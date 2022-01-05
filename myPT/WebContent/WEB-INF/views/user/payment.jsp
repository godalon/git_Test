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

    <title>결제</title>
    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet"/>
    <link href="/myPT/css/widget.css" rel="stylesheet"/>
    
    <link rel="stylesheet" href="css/payment.css" />
    
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
          <div class="container-fluid">
          	<h2>결제</h2>
			
			<div class="my-3">
			
			<div class="row">
<!-- 		      <div class="d-flex flex-column align-items-center m-4"> -->
		        <label for="startDate" class="d-inline col-6">시작 날짜</label>
				<label for="trainer" class="d-inline col-6">담당 트레이너</label>   
<!-- 		      </div> -->
		    </div>
		    
		    <div class="row">
<!-- 		      <div class="d-flex flex-column align-items-center m-4"> -->

				<div class="col-6">
					<c:choose>
				      <c:when test="${not empty user.startdate}">
				      	  <input type="text" id="startdate" class="form-control w-100" value="${user.startdate}" readonly>
				      </c:when>				      
					  <c:otherwise>
						  <input
				          type="date"
				          id="startdate"
				          name="startdate"
				          class="form-control w-100"/>
					  </c:otherwise>
			 		 </c:choose>	  
			     </div>
		          
		          <div class="col-6">	    
					<c:choose>
				      <c:when test="${not empty user.tid}">
				      	  <input type="text" id="trainer1" class="form-control w-100" value="${user.tid}" readonly>
				      </c:when>
					  <c:otherwise>
					  <select class="form-control" id="trainer2" class="form-control w-100" onchange="changeTrainer()">
		               <option value="" selected disabled>트레이너 선택</option>
		               <c:forEach items="${trainer}" var="t">
		                 <option value="${t.t_id}">${t.t_name}</option>                              
		               </c:forEach>
				        </select>
					  </c:otherwise>
			 		 </c:choose>
			 		 <input type="text" id="selectedTrainer" hidden="true">
			 	 </div>
<!-- 		      </div> -->
		    </div>
		   </div>
					
		      <div class="row">
		        <div class="col-lg-3 col-md-6 col-sm-6 mb-4 pTableWrapper">
		          <div class="pricingTable">
		            <div class="pricingTable-header mt-3">
		              <h3><span>10</span>회권/30일</h3>
		            </div>
		
		            <div class="price-value">
		              <p><span class="price">39</span>만원</p>
		              <span class="subtitle">기본 이용권</span>
		            </div>
		
		            <div class="pricingContent">
		              <ul>
		                <li><b>10회</b> PT</li>
		                <li><b>30일</b> 헬스 이용</li>
		                <li><b>락커, 운동복</b> 대여 무료</li>
		              </ul>
		            </div>
		
		            <div class="pricingTable-sign-up">
		              <input type="button" class="btn btn-block btn-default" value="결제" id="0" onclick="pay(this)">
		            </div>
		          </div>
		        </div>
		
		        <div class="col-lg-3 col-md-6 col-sm-6 mb-4 pTableWrapper">
		          <div class="pricingTable">
		            <div class="pricingTable-header mt-3">
		              <h3><span>23</span>회권/60일</h3>
		            </div>
		
		            <div class="price-value">
		              <p><span class="price">73</span>만원</p>
					<span class="subtitle">20+3회 <del>89만 7,000원</del></span>
		            </div>
		
		            <div class="pricingContent">
		              <ul>
		                <li><b>23회</b> PT</li>
		                <li><b>60일</b> 헬스 이용</li>
		                <li><b>락커, 운동복</b> 대여 무료</li>

		              </ul>
		            </div>
		
		            <div class="pricingTable-sign-up">
		              <input type="button" class="btn btn-block btn-default" value="결제" id="1" onclick="pay(this)">
		            </div>
		          </div>
		        </div>
		
		        <div class="col-lg-3 col-md-6 col-sm-6 mb-4 pTableWrapper">
		          <div class="pricingTable">
		            <div class="pricingTable-header mt-3">
		              <div class="best-offer">인기 상품</div>
		              <h3><span>35</span>회권/90일</h3>
		            </div>
		
		            <div class="price-value">
		              <p><span class="price">103</span>만원</p>
		              <span class="subtitle">30+5회 <del>136만 5000원</del></span>
		            </div>
		
		            <div class="pricingContent">
		               <ul>
		                <li><b>35회</b> PT</li>
		                <li><b>90일</b> 헬스 이용</li>
		                <li><b>락커, 운동복</b> 대여 무료</li>
		                <li><b>프로틴</b> 증정</li>
		              </ul>
		            </div>
		
		            <div class="pricingTable-sign-up">
				<input type="button" class="btn btn-block btn-default" value="결제" id="2" onclick="pay(this)">
	            </div>
		          </div>
		        </div>
		
		        <div class="col-lg-3 col-md-6 col-sm-6 mb-4 pTableWrapper">
		          <div class="pricingTable">
		            <div class="pricingTable-header mt-3">
		              <h3><span>60</span>회권/180일</h3>
		            </div>
		
		            <div class="price-value">
		              <p><span class="price">164</span>만원</p>
					 <span class="subtitle">50+10회권 <del>234만원</del></span>		            
					 </div>
		
		            <div class="pricingContent">
		             <ul>
		                <li><b>60회</b> PT</li>
		                <li><b>180일</b> 헬스 이용</li>
		                <li><b>락커, 운동복</b> 대여 무료</li>
		                <li><b>프로틴</b> 증정</li>
		                <li><b>프로필 사진</b> 촬영 무료</li>
		              </ul>
		            </div>
		
		            <div class="pricingTable-sign-up">
		              <input type="button" class="btn btn-block btn-default" value="결제" id="3" onclick="pay(this)">
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
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    
    <script>
    
    var userId= '${user.id}';
	var userEmail= '${user.email}';
	var userName= '${user.name}';
	var userTel= '${user.tel}';
	var merchant_uid= '${merchant_uid}';
	
    
    </script>
    <script src="/myPT/js/scripts.js"></script>
    <script src="/myPT/js/payment.js"></script>
    
  </body>
</html>
