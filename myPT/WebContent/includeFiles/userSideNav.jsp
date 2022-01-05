<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
          <div class="sb-sidenav-menu">
            <div class="nav">
              <a class="nav-link" href="userMain.do">
                <div class="sb-nav-link-icon">
                  <i class="fas fa-home"></i>
                </div>
                메인페이지
              </a>
              <a class="nav-link" href="moveInbody.do">
                <div class="sb-nav-link-icon">
                  <i class="fas fa-weight"></i>
                </div>
                인바디
              </a>
              <a
                class="nav-link collapsed"
                href="#"
                data-toggle="collapse"
                data-target="#collapseLayouts"
                aria-expanded="false"
                aria-controls="collapseLayouts"
              >
                <div class="sb-nav-link-icon">
                  <i class="fas fa-columns"></i>
                </div>
                게시판
                <div class="sb-sidenav-collapse-arrow">
                  <i class="fas fa-angle-down"></i>
                </div>
              </a>
              <div
                class="collapse"
                id="collapseLayouts"
                aria-labelledby="headingOne"
                data-parent="#sidenavAccordion"
              >
                <nav class="sb-sidenav-menu-nested nav">
                  <a class="nav-link" href="moveCommunity.do"
                    >커뮤니티 게시판</a
                  >
                  <a class="nav-link" href="movePhoto.do"
                    >포토 게시판</a
                  >
                  <a class="nav-link" href="moveQuestion.do">Q & A</a>
                </nav>
              </div>
              <a class="nav-link" href="movePayment.do">
                <div class="sb-nav-link-icon">
                  <i class="fas fa-credit-card"></i>
                </div>
                결제
              </a>
              <a class="nav-link" href="moveUserProfile.do">
                <div class="sb-nav-link-icon"><i class="fas fa-user-circle"></i></div>
                마이페이지
              </a>
            </div>
          </div>

          <div class="sb-sidenav-footer"></div>
        </nav>
      </div>