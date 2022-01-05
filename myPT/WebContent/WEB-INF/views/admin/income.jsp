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
<link href="/myPT/css/styles.css" rel="stylesheet" />
<link href="/myPT/css/index.css" rel="stylesheet" />
<link href="/myPT/css/widget.css" rel="stylesheet" />

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
		<jsp:include page="/includeFiles/adminSideNav.jsp"></jsp:include>
		<!-- /sideNav -->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid mt-3">
					<!-- ============================================================== -->
					<!-- Start Page Content -->
					<!-- ============================================================== -->

					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-3" id="card1">
							<div class="card bg-primary text-white mb-4">
								<div class="card-body">
									<div class="widget-content p-0">
										<div class="widget-content-wrapper">
											<div class="widget-content-left">
												<div class="widget-heading text-white">
													<h4>
														<i class="fas fa-won-sign ml-2"></i>
													</h4>
												</div>
												<div class="widget-subheading"></div>
											</div>
											<div class="widget-content-right">
												<div class="widget-numbers">${nowIncome}만원</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer text-center">
									<p class="small text-white mb-0">이달의 수입</p>
								</div>
							</div>
						</div>

						<div class="col-xs-12 col-sm-6 col-md-3" id="card2">
							<div class="card bg-primary text-white mb-4">
								<div class="card-body">
									<div class="widget-content p-0">
										<div class="widget-content-wrapper">
											<div class="widget-content-left">
												<div class="widget-heading text-white">
													<h4>
														<i class="fas fa-exchange-alt ml-2"></i>
													</h4>
												</div>
												<div class="widget-subheading"></div>
											</div>
											<div class="widget-content-right">
												<div class="widget-numbers">${var }만원</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer text-center">
									<p class="small text-white mb-0">전월대비 변화량</p>
								</div>
							</div>
						</div>

						<div class="col-xs-12 col-sm-6 col-md-3" id="card3">
							<div class="card bg-success text-white mb-4">
								<div class="card-body">
									<div class="widget-content p-0">
										<div class="widget-content-wrapper">
											<div class="widget-content-left">
												<div class="widget-heading text-white">
													<h4>
														<i class="fas fa-user-friends ml-2"></i>
													</h4>
												</div>
												<div class="widget-subheading"></div>
											</div>
											<div class="widget-content-right">
												<div class="widget-numbers">${nowUser }명</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer text-center">
									<p class="small text-white mb-0">총 회원수</p>
								</div>
							</div>
						</div>

						<div class="col-xs-12 col-sm-6 col-md-3" id="card4">
							<div class="card bg-success text-white mb-4">
								<div class="card-body">
									<div class="widget-content p-0">
										<div class="widget-content-wrapper">
											<div class="widget-content-left">
												<div class="widget-heading text-white">
													<h4>
														<i class="fas fa-user-plus ml-2"></i>
													</h4>
												</div>
												<div class="widget-subheading"></div>
											</div>
											<div class="widget-content-right">
												<div class="widget-numbers">${newUser }명</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer text-center">
									<p class="small text-white mb-0">신규회원 수</p>
								</div>
							</div>
						</div>

						<div class="col-12">
							<select class="text-center form-control" id="selectyear1">
								<option value="default" disabled selected>연도 선택</option>
								<option value="2019">2019년</option>
								<option value="2020">2020년</option>
							</select>
							<div class="card">
								<div class="card-header p-4">
									<h3 class="card-title text-center">월별 수익 및 신규 회원</h3>
								</div>
								<div class="card-body">
									<canvas id="myChart1" width="100%" height="50"></canvas>
								</div>
							</div>
						</div>

						<div class="col-md-4 mt-5">
							<div class="form-row">
								<div class="form-group">
									<select class="text-center form-control" id="selectyear2">
										<option value="default" disabled selected>연도 선택</option>
										<option value="2019">2019년</option>
										<option value="2020">2020년</option>
									</select>
								</div>
								<div class="form-group">
									<select class="text-center form-control" id="selectmonth">
										<option value="default" disabled selected>월 선택</option>
									</select>
								</div>
							</div>
						</div>
						<div class="card-group col-12 mb-5">
							<div class="card col-md-8 col-sm-8 col-xs-12 p-0">
								<div class="card-header p-4">
									<h3 class="card-title text-center" id="tincome">이번달 트레이너
										수익</h3>
								</div>
								<div class="card-body">
									<canvas id="myChart2"></canvas>
								</div>
							</div>

							<div class="card col-md-4 col-sm-4 col-xs-12 p-0">
								<div class="card-header p-4">
									<h3 class="card-title text-center" id="tmonthhead">이달의 트레이너</h3>
								</div>
								<div id="tmonth">								
								</div>
							</div>
						</div>

						<!-- ============================================================== -->
						<!-- End PAge Content -->
					</div>
				</div>
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
	<!-- 차트 링크 -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

	<script src="/myPT/js/chart1.js" charset="utf-8"></script>
	<script src="/myPT/js/chart2.js" charset="utf-8"></script>
	<script src="/myPT/js/income.js" charset="utf-8"></script>

</body>
</html>
