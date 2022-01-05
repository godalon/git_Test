<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>템플릿</title>
<link href="css/styles.css" rel="stylesheet" />
<link href="css/index.css" rel="stylesheet" />
<link href="css/widget.css" rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>

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
				<div class="container-fluid mt-3">
					<!-- ============================================================== -->
					<!-- Start Page Content -->
					<!-- ============================================================== -->
					<div class="row">
						<form class="col-12" method="post" action="trainerPTFinish.do">
							

								<div class="card">
									<div class="card-body">
										<h3 class="card-title m-b-0 text-center">오늘의 PT 회원</h3>
									</div>

									<table class="table mb-0">
										<thead class="thead-light text-center">
											<tr>
												<c:if test="${uarr[0]!=null }">
													<th><label class=""> <input type="checkbox"
															id="mainCheckbox" /> <span class="checkmark"></span>
													</label></th>
												</c:if>

												<th scope="col">회원번호</th>
												<th scope="col">이름</th>
												<th scope="col">성별</th>
												<th scope="col">나이</th>
												<th scope="col">전화번호</th>
												<th scope="col">남은횟수</th>
												<th scope="col">시간</th>
											</tr>
										</thead>
										<tbody class="customtable text-center">
											<c:choose>
												<c:when test="${uarr[0]==null }">
													<tr>
														<td colspan="6">오늘의 스케줄이 없습니다.</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${uarr}" var="item" varStatus="status">
														<tr>
															<th><label class=""> <input type="checkbox"
																	name="id" class="listCheckbox" value="${item.id}" /> <span
																	class="checkmark"></span>
															</label></th>
															<td>${item.id }</td>
															<td>${item.name }</td>
															<td>${item.gender }</td>
															<td>${item.birth }</td>
															<td>${item.tel }</td>
															<td>${item.ptcount }</td>
															<td>${sarr[status.index].s_time }</td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>

										</tbody>
									</table>

								</div>
								<button type="submit" class="btn btn-primary text-white float-right mt-4" >수업완료</button>
							
						</form>

					</div>
					<!-- ============================================================== -->
					<!-- End PAge Content -->
				</div>
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
		
	</script>
	<script src="js/scripts.js"></script>
	<!-- 체크박스 -->
	<script src="assets/extra-libs/multicheck/datatable-checkbox-init.js"></script>
	<script src="assets/extra-libs/multicheck/jquery.multicheck.js"></script>
</body>
</html>
