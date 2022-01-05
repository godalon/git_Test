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
    <title>회원 출입 내역</title>
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
      <jsp:include page="/includeFiles/adminSideNav.jsp"></jsp:include>
      <!-- /sideNav -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid mt-3">
          	<div class="card mb-4">
              <div class="card-header font-weight-bold">
                <i class="fas fa-history"></i>
                회원 출입 내역
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
                        <th>이름</th>
                        <th>회원번호</th>
                        <th>들어온 시간</th>                        
                        <th>나간 시간</th>                                             
                      </tr>
                    </thead>
                    <tbody class="text-center">
                      <c:forEach items="${arr }" var="item">
                      <tr>
                        <td>${item.u_name }</td>
                        <td>${item.h_id }</td>
                        <td>${item.intime }</td>
                        <td>
                        <c:if test="${item.outtime==null }">
                        X                        
                        </c:if>
                        <c:if test="${item.outtime!=null }">
                        ${item.outtime }
                        </c:if>
                        </td>
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
    <script src="/myPT/js/headcountlist.js"></script>
    
    <!-- 데이터 테이블 js -->
    <script src="/myPT/js/dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
    <script src="/myPT/assets/demo/datatables-demo.js"></script>
    <!-- /데이터 테이블 js -->
    
  </body>
</html>
