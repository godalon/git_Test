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
    <title>트레이너 관리</title>
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
			<div class="container-fluid">
            	<div class="card mb-4">
              		<div class="card-header font-weight-bold">               		 
               		 <div class="justify-content-between d-flex ">
              <div class="align-self-center">
              	<i class="fas fa-users-cog"></i>
              	트레이너 관리
              </div>
                <div class="btn btn-dark" onclick="location.href='moveTrainerRegister.do'">트레이너 등록</div>
              </div>
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
                        <th>TID</th>
                        <th>이름</th>
                        <th>성별</th>
                        <th>생년월일</th>
                        <th>전화번호</th>
                        <th>수정 / 삭제</th>
                      </tr>
                    </thead>
                    <tbody class="text-center">
                      <c:forEach items="${trainerList }" var="item">
                      <tr>
                        <td>${item.t_id}</td>
                        <td>${item.t_name }</td>
                        <td>${item.t_gender }</td>
                        <td>${item.t_birth }</td>
                        <td>${item.t_tel }</td>
                        <td>
                          <button onclick="location.href='adminTrainerDetail.do?t_id=${item.t_id}'" class="btn btn-primary custom-btn">
                            <i class="fas fa-user-edit"></i>
                          </button>
                          <button onclick="deleteTrainer('${item.t_id}')" class="btn btn-danger custom-btn">
                            <i class="fas fa-user-times"></i>
                          </button>
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
    
    <!-- 데이터 테이블 js -->
    <script src="/myPT/js/dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
    <script src="/myPT/assets/demo/datatables-demo.js"></script>
    <!-- /데이터 테이블 js -->
    
    <!-- 삭제 js -->
    <script src="/myPT/js/delete.js"></script>
    
  </body>
</html>
