<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>포토 게시판</title>
<link href="/myPT/css/styles.css" rel="stylesheet" />
<link href="/myPT/css/index.css" rel="stylesheet" />
<link href="/myPT/css/widget.css" rel="stylesheet" />
<link href="/myPT/css/like.css" rel="stylesheet" />
<link href="/myPT/css/photo.css" rel="stylesheet" />

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
		<c:choose>
			<c:when test="${sessionScope.grade==0 }">
				<jsp:include page="/includeFiles/adminSideNav.jsp"></jsp:include>
			</c:when>
			<c:when test="${sessionScope.grade==1 }">
				<jsp:include page="/includeFiles/trainerSideNav.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="/includeFiles/userSideNav.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		<!-- /sideNav -->

		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid mt-3">
					<div class="row">
						<div class="col-12">
							<h3 class="mb-5 mt-3">포토 게시판</h3>

							<div class="row">
								<c:choose>
									<c:when test="${photoList[0]==null }">
										<div class="col-12 text-center mb-5">
											<hr>
											<h2 class="mt-5 mb-5">게시글이 없습니다.</h2>
											<hr>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach items="${photoList }" var="item" varStatus="status">
									<div class="col-md-6 col-xs-12 col-lg-4">
										<div class="card border-0" id="card${item.num}">
											<p class="card-text mb-0 text-muted" style="font-size: 14px">${item.date }</p>
											${item.photo }
											<div class="card-body p-0 pt-2">
												<h5 class="card-title mb-1 ml-2">
													<a class="text-dark text-decoration-none"
														href="boardView.do?num=${item.num}">${item.title }</a>
												</h5>
												<p class="card-text mb-1 ml-2">${item.writer }</p>

												<!-- 좋아요버튼 -->
												<div class="row">
													<div class="row ml-1 mr-1 pl-1 likebutton">
														<input type="checkbox" id="${item.num }" class="like"
															${likes[status.count-1] } />
														<div>
															<svg id="heart-svg" viewBox="467 392 58 57"
																xmlns="http://www.w3.org/2000/svg" style="width: 40px">
			                              <g id="Group" fill="none"
																	fill-rule="evenodd" transform="translate(467 392)">
			                                <path
																	d="M29.144 20.773c-.063-.13-4.227-8.67-11.44-2.59C7.63 28.795 28.94 43.256 29.143 43.394c.204-.138 21.513-14.6 11.44-25.213-7.214-6.08-11.377 2.46-11.44 2.59z"
																	id="heart" fill="#AAB8C2" />
			                                <circle id="main-circ" fill="#E2264D"
																	opacity="0" cx="29.5" cy="29.5" r="1.5" />
			
			                                <g id="grp7" opacity="0"
																	transform="translate(7 6)">
			                                  <circle id="oval1" fill="#9CD8C3"
																	cx="2" cy="6" r="2" />
			                                  <circle id="oval2" fill="#8CE8C3"
																	cx="5" cy="2" r="2" />
			                                </g>
			
			                                <g id="grp6" opacity="0"
																	transform="translate(0 28)">
			                                  <circle id="oval1" fill="#CC8EF5"
																	cx="2" cy="7" r="2" />
			                                  <circle id="oval2" fill="#91D2FA"
																	cx="3" cy="2" r="2" />
			                                </g>
			
			                                <g id="grp3" opacity="0"
																	transform="translate(52 28)">
			                                  <circle id="oval2" fill="#9CD8C3"
																	cx="2" cy="7" r="2" />
			                                  <circle id="oval1" fill="#8CE8C3"
																	cx="4" cy="2" r="2" />
			                                </g>
			
			                                <g id="grp2" opacity="0"
																	transform="translate(44 6)">
			                                  <circle id="oval2" fill="#CC8EF5"
																	cx="5" cy="6" r="2" />
			                                  <circle id="oval1" fill="#CC8EF5"
																	cx="2" cy="2" r="2" />
			                                </g>
			
			                                <g id="grp5" opacity="0"
																	transform="translate(14 50)">
			                                  <circle id="oval1" fill="#91D2FA"
																	cx="6" cy="5" r="2" />
			                                  <circle id="oval2" fill="#91D2FA"
																	cx="2" cy="2" r="2" />
			                                </g>
			
			                                <g id="grp4" opacity="0"
																	transform="translate(35 50)">
			                                  <circle id="oval1" fill="#F48EA7"
																	cx="6" cy="5" r="2" />
			                                  <circle id="oval2" fill="#F48EA7"
																	cx="2" cy="2" r="2" />
			                                </g>
			
			                                <g id="grp1" opacity="0"
																	transform="translate(24)">
			                                  <circle id="oval1" fill="#9FC7FA"
																	cx="2.5" cy="3" r="2" />
			                                  <circle id="oval2" fill="#9FC7FA"
																	cx="7.5" cy="2" r="2" />
			                                </g>
			                              </g>
			                            </svg>
														</div>
														<p class="mt-2 mb-0" id="likeNum${item.num }">${item.like}</p>

													</div>
													<div class="row m-0">
														<i class="far fa-comment-dots fa-lg mt-2 ml-3 text-muted"
															style="pointer-events: none"></i>
														<p class="mt-2 ml-2">${comment[status.count-1] }</p>
													</div>
												</div>
												<!-- 좋아요버튼 -->
											</div>
										</div>
									</div>
								</c:forEach>
									</c:otherwise>
								</c:choose>
								
							</div>

							<div class="form-group mt-3 float-right">
								<a class="btn btn-primary text-white" href="moveWrite.do">글쓰기</a>
							</div>


							<!-- 페이징 시작 -->
							<c:if test="${totalPage>0 }">
								<ul class="pagination mt-5 ml-5 justify-content-center">
									<!-- 이전 블럭 -->
									<c:choose>
										<c:when test="${nowBlock>1 }">
											<li class="page-item"><a class="page-link"
												onclick="ltPaging('${sessionScope.keyWord}','${nowBlock-1 }')"
												style="cursor: pointer">&lt;</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												style="pointer-events: none">&lt;</a></li>
										</c:otherwise>
									</c:choose>

									<!-- 이전 블럭 끝 -->
									<!-- 번호 페이징 시작 -->
									<c:forEach var="i" begin="${pageStart }" end="${pageEnd-1 }">
										<c:choose>
											<c:when test="${nowPage==i }">
												<li class="page-item active"><a class="page-link"
													style="pointer-events: none">${i }</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link text-primary"
													onclick="paging('${sessionScope.keyWord}',${i})"
													style="cursor: pointer">${i }</a></li>
											</c:otherwise>
										</c:choose>

									</c:forEach>
									<!-- 번호 페이징 끝 -->
									<!-- 다음 블럭 -->
									<c:choose>
										<c:when test="${totalBlock>nowBlock }">
											<li class="page-item"><a class="page-link text-muted"
												onclick="gtPaging('${sessionScope.keyWord}','${nowBlock+1 }')" style="cursor:pointer">&gt;</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												style="pointer-events: none">&gt;</a></li>
										</c:otherwise>
									</c:choose>
									<!-- 다음 블럭 끝-->
								</ul>
							</c:if>
							<div class="row justify-content-center mb-5 mt-3">
								<select name="keyField" id="keyField">
									<c:choose>
										<c:when test="${sessionScope.keyField=='pb_writer'}">
											<option value="default" disabled>카테고리</option>
											<option value="pb_writer" selected>작성자</option>
											<option value="pb_title">제목</option>
											<option value="pb_content">내용</option>
										</c:when>
										<c:when test="${sessionScope.keyField=='pb_title'}">
											<option value="default" disabled>카테고리</option>
											<option value="pb_writer">작성자</option>
											<option value="pb_title" selected>제목</option>
											<option value="pb_content">내용</option>
										</c:when>
										<c:when test="${sessionScope.keyField=='pb_content'}">
											<option value="default" disabled>카테고리</option>
											<option value="pb_writer">작성자</option>
											<option value="pb_title">제목</option>
											<option value="pb_content" selected>내용</option>
										</c:when>
										<c:otherwise>
											<option value="default" selected disabled>카테고리</option>
											<option value="pb_writer">작성자</option>
											<option value="pb_title">제목</option>
											<option value="pb_content">내용</option>
										</c:otherwise>
									</c:choose>
								</select> <input type="text" name="keyWord" class="mr-2" id="keyWord"
									value="${sessionScope.keyWord}"> <input type="button"
									class="btn btn-primary" value="검색" onClick="searchCheck()">
							</div>
						</div>
						<!-- ============================================================== -->
						<!-- End PAge Content -->
						<!-- 검색 -->


					</div>
				</div>
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
    </script>
	<script src="/myPT/js/scripts.js"></script>
	<script src="/myPT/js/photo.js"></script>
	<script src="/myPT/js/photoLike.js"></script>

</body>
</html>
