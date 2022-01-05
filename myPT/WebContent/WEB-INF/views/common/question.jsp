<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.mypt.dao.CboardDao"%>
<%@page import="com.mypt.dto.CboardDto"%>
<%@page import="java.util.Vector"%>
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
<title>Q&A</title>
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
					<!-- 여기서부터 작성 -->
					<div class="row">
						<div class="col-12">
							<div class="card" style="overflow: auto">
								<div class="card-body">
									<a href="moveQuestion.do"
										class="text-decoration-none text-dark"><h3
											class="card-title text-center">Q&A</h3></a>
								</div>	
								
								<div class="table-responsive">
									<table class="table mb-0">
										<thead class="thead-light text-center">
											<tr>
												
												<th id="title" style="width: 50%">제목</th>
												<th>작성자</th>
												<th>작성일자</th>
												<th>조회수</th>
												
											</tr>
										</thead>
										<tbody class="customtable text-center">

											<c:choose>
												<c:when test="${qarr[0]==null }">
													<tr align="center">
														<td colspan="6">등록된 게시물이 없습니다.</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${qarr }" var="item" varStatus="status">
														<tr align="center">
															<c:if test="${item.depth>0 }">
																<td align="left">
																<c:forEach var="i" begin="1"
																		end="${item.depth-1}" varStatus="a">
																		&nbsp;&nbsp;&nbsp;&nbsp;
																</c:forEach>
																	<div class="badge badge-dark mr-2 ml-2">답변</div> <a
																	class="text-dark text-decoration-none ml-2"
																	href="boardView.do?num=${item.num }">
																		${item.title } </a>
																		<span class="text-primary"><c:if test="${comment[status.index]>0 }">[${comment[status.index] }]</c:if></span>
																		</td>
															</c:if>
															<c:if test="${item.depth<=0 }">
																<td align="left"><a
																class="text-dark text-decoration-none ml-2"
																	href="boardView.do?num=${item.num }">
																		${item.title }</a>
																		<span class="text-primary"><c:if test="${comment[status.index]>0 }">[${comment[status.index] }]</c:if></span>
																		</td>
															</c:if>

															<td>${item.writer }</td>
															<td>${item.date }</td>
															<td>${item.hit }</td>
															
														</tr>

													</c:forEach>


												</c:otherwise>
											</c:choose>




										</tbody>
									</table>
								</div>
							</div>

							<div class="form-group mt-3 float-right">
								<a class="btn btn-primary text-white"
									href="moveWrite.do">글쓰기</a>
							</div>

							<ul class="pagination mt-5 ml-5 justify-content-center">
								<!-- 페이징 및 블럭 Start -->

								<c:if test="${totalPage>0 }">
									<!-- 이전 블럭 -->
									<c:choose>
										<c:when test="${nowBlock>1 }">
											<li class="page-item"><a class="page-link"
												onclick="ltPaging('${sessionScope.keyWord}','${nowBlock-1 }','${sessionScope.head }')"
												style="cursor: pointer"
												>&lt;</a></li>
										</c:when>
										<c:otherwise>

											<li class="page-item"><a class="page-link text-muted" style="pointer-events: none">&lt;</a>
											</li>
										</c:otherwise>

									</c:choose>
									<!-- 번호 페이징 시작 -->
									<c:forEach var="i" begin="${pageStart }" end="${pageEnd-1 }">
										<c:choose>
											<c:when test="${nowPage==i }">
												<li class="page-item active"><a class="page-link"
													style="pointer-events: none">${i }</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link text-primary"
													onclick="paging('${sessionScope.keyWord}',${i},'${sessionScope.head }')"
													style="cursor: pointer"
													>${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- 번호 페이징 끝 -->
									
									<!-- 다음 블럭 -->
									<c:choose>
										<c:when test="${totalBlock>nowBlock }">
											<li class="page-item"><a class="page-link text-muted"
												onclick="gtPaging('${sessionScope.keyWord}','${nowBlock+1 }')"
												style="cursor: pointer"
												>&gt;</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												style="pointer-events: none">&gt;</a></li>
										</c:otherwise>
									</c:choose>
									</c:if>
								</ul>

							<!-- 검색 -->
							<div class="row justify-content-center mb-5">
								<select name="keyField" id="keyField">
									<c:choose>
										<c:when test="${sessionScope.keyField=='qb_writer'}">
											<option value="default" disabled>카테고리</option>
											<option value="qb_writer" selected>작성자</option>
											<option value="qb_title">제목</option>
											<option value="qb_content">내용</option>
										</c:when>
										<c:when test="${sessionScope.keyField=='qb_title'}">
											<option value="default" disabled>카테고리</option>
											<option value="qb_writer">작성자</option>
											<option value="qb_title" selected>제목</option>
											<option value="qb_content">내용</option>
										</c:when>
										<c:when test="${sessionScope.keyField=='qb_content'}">
											<option value="default" disabled>카테고리</option>
											<option value="qb_writer">작성자</option>
											<option value="qb_title">제목</option>
											<option value="qb_content" selected>내용</option>
										</c:when>
										<c:otherwise>
											<option value="default" selected disabled>카테고리</option>
											<option value="qb_writer">작성자</option>
											<option value="qb_title">제목</option>
											<option value="qb_content">내용</option>
										</c:otherwise>
									</c:choose>
								</select> <input type="text" name="keyWord" class="mr-2" id="keyWord"
									value="${sessionScope.keyWord}"> <input type="button"
									class="btn btn-primary" value="검색" onClick="searchCheck()">
							</div>
						</div>
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
	<script src="/myPT/js/question.js"></script>

</body>
</html>
