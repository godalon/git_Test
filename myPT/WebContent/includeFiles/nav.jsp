<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>











<!-- 	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@700&display=swap" rel="stylesheet"> -->
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark p-1">
	<div id="navBrand" class="d-flex align-items-center">

		<c:choose>
			<c:when test="${sessionScope.grade==0}">
				<a class="navbar-brand" href="userList.do"><img
					src="img/colorChanged.png" /></a>
			</c:when>
			<c:when test="${sessionScope.grade==1}">
				<a class="navbar-brand" href="trainerMain.do"><img
					src="img/colorChanged.png" /></a>
			</c:when>
			<c:when test="${sessionScope.grade==2}">
				<a class="navbar-brand" href="userMain.do"><img
					src="img/colorChanged.png" /></a>
			</c:when>
		</c:choose>


	</div>

	<button class="btn btn-link btn-sm order-4 order-lg-0 mr-lg-auto"
		id="sidebarToggle" href="#">
		<i class="fas fa-bars"></i>
	</button>

	<div class="row ml-auto px-lg-3">
		<div class="d-flex justify-content-between">
			<!-- <div type="button" class="menus btn btn-light order-lg-1 mx-1 p-1">QR</div> -->

			<!--navbar 드롭다운-->
			<ul class="navbar-nav order-lg-2 mx-1">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle mt-1" id="userDropdown" href="#"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="userDropdown">

						<c:choose>
							<c:when test="${sessionScope.grade==2}">
								<a class="dropdown-item" data-toggle="modal"
									data-target="#QRModal">QR</a>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>

						<a class="dropdown-item" href="logout.do">Logout</a>
					</div></li>
			</ul>


			<div class="navbar-nav order-lg-2 mx-1 mr-2">
				<div class="nav-item nav-link" id="crowded"></div>
			</div>

			<!-- 혼잡도 -->
			<script src="https://code.jquery.com/jquery-3.5.1.min.js"
				crossorigin="anonymous"></script>
			<script src="/myPT/js/crowded.js"></script>
			<script src="/myPT/js/userAttendance.js"></script>
			<link rel="shortcut icon" href="/myPT/img/favi/favicon-16x16.png">
		</div>
	</div>
</nav>