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
<title>스케줄 관리</title>
<link href="/myPT/css/styles.css" rel="stylesheet" />
<link href="/myPT/css/index.css" rel="stylesheet" />
<link href="/myPT/css/widget.css" rel="stylesheet" />

<!-- 스케줄 css -->
<link href="css/scheduleManager.css" rel="stylesheet" />
<!-- /스케줄 css -->

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
				<div class="container-fluid">
					<h1 class="mt-4 mb-4">${user.name}님 스케줄 조정</h1>
					<!-- 달 주 선택 -->
					<div class="container h-100 mb-4">
						<div class="row h-100 justify-content-center align-items-center">
							<div class="input-group w-50">
								<div class="custom-input input-group-prepend">
									<span class="input-group-text">선택하세요</span>
								</div>

								<select class="custom-select" id="monthSelect"
									style="border-top-left-radius: 0.25rem; border-bottom-left-radius: 0.25rem;">
									<option selected disabled>월</option>
									<option value="1">1월</option>
									<option value="2">2월</option>
									<option value="3">3월</option>
									<option value="4">4월</option>
									<option value="5">5월</option>
									<option value="6">6월</option>
									<option value="7">7월</option>
									<option value="8">8월</option>
									<option value="9">9월</option>
									<option value="10">10월</option>
									<option value="11">11월</option>
									<option value="12">12월</option>
								</select> <select class="custom-select" id="weekSelect">
									<option selected disabled>월 선택</option>
									
								</select>
							</div>
						</div>
					</div>

					<!-- 스케줄 조정 -->
					<form>
						<div>
							<h3 id="weekend"></h3>
						</div>
						<div class="card-group text-center">
							<div class="card">
								<div class="card-header bg-dark text-white" id="mon">월</div>
								<input type="text" name="date" disabled
										id="date0" style="display: none" />
								<div class="card-body" id="week1">
									<div class="btn btn-danger" id="btnPT1" data-toggle="modal"
										data-target="#PT">PT</div>
									<input class="PT text-center" type="time" name="time" disabled
										id="time1" style="display: none" />
									<div class="btn btn-light" id="btnProgram1" data-toggle="modal"
										data-target="#program">프로그램</div>
									<input class="pro text-center" type="text" name="program"
										disabled id="pro1" style="display: none" />
									<input class="pro" type="text" name="proMention" disabled
										id="proMention1" style="display: none" />
								</div>
							</div>
							<div class="card">
								<div class="card-header bg-dark text-white" id="tue">화</div>
								<input type="text" name="date" disabled
										id="date1" style="display: none" />
								<div class="card-body" id="week2">
									<div class="btn btn-danger" id="btnPT2" data-toggle="modal"
										data-target="#PT">PT</div>
									<input class="PT text-center" type="time" name="time" disabled
										id="time2" style="display: none" />
									<div class="btn btn-light" id="btnProgram2" data-toggle="modal"
										data-target="#program">프로그램</div>
									<input class="pro text-center" type="text" name="program"
										disabled id="pro2" style="display: none" />
									<input class="pro" type="text" name="proMention" disabled
										id="proMention2" style="display: none" />
								</div>
							</div>
							<div class="card">
								<div class="card-header bg-dark text-white" id="wed">수</div>
								<input type="text" name="date" disabled
										id="date2" style="display: none" />
								<div class="card-body" id="week3">
									<div class="btn btn-danger" id="btnPT3" data-toggle="modal"
										data-target="#PT">PT</div>
									<input class="PT text-center" type="time" name="time" disabled
										id="time3" style="display: none" />
									<div class="btn btn-light" id="btnProgram3" data-toggle="modal"
										data-target="#program">프로그램</div>
									<input class="pro text-center" type="text" name="program"
										disabled id="pro3" style="display: none" />
									<input class="pro" type="text" name="proMention" disabled
										id="proMention3" style="display: none" />
								</div>
							</div>
							<div class="card">
								<div class="card-header bg-dark text-white" id="thu">목</div>
								<input type="text" name="date" disabled
										id="date3" style="display: none" />
								<div class="card-body" id="week4">
									<div class="btn btn-danger" id="btnPT4" data-toggle="modal"
										data-target="#PT">PT</div>
									<input class="PT text-center" type="time" name="time" disabled
										id="time4" style="display: none" />
									<div class="btn btn-light" id="btnProgram4" data-toggle="modal"
										data-target="#program">프로그램</div>
									<input class="pro text-center" type="text" name="program"
										disabled id="pro4" style="display: none" />
									<input class="pro" type="text" name="proMention" disabled
										id="proMention4" style="display: none" />
								</div>
							</div>
							<div class="card">
								<div class="card-header bg-dark text-white" id="fri">금</div>
								<input type="text" name="date" disabled
										id="date4" style="display: none" />
								<div class="card-body" id="week5">
									<div class="btn btn-danger" id="btnPT5" data-toggle="modal"
										data-target="#PT">PT</div>
									<input class="PT text-center" type="time" name="time" disabled
										id="time5" style="display: none" />
									<div class="btn btn-light" id="btnProgram5" data-toggle="modal"
										data-target="#program">프로그램</div>
									<input class="pro text-center" type="text" name="program"
										disabled id="pro5" style="display: none" />
									<input class="pro" type="text" name="proMention" disabled
										id="proMention5" style="display: none" />
								</div>
							</div>
						</div>
						<div
							class="btn btn-success mt-2 mb-5 float-right custom-submit btn-confirm"
							id="confirm"
							onclick="setting()"
						>
							완료</div>
					</form>
				</div>
			</main>
		</div>
	</div>

	<!-- 스케줄 모달 -->
	<jsp:include page="/includeFiles/modalSchedule.jsp"></jsp:include>
	<!-- /스케줄 모달 -->

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
		
	</script>
	<script src="/myPT/js/scripts.js"></script>

	<!-- 스케줄 조정 js -->
	<script src="/myPT/js/scheduleManager.js"></script>
	<!-- /스케줄 조정 js -->

</body>
</html>
