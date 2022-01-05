<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal" id="PT">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">PT 일정</h4>
            <button class="close" type="button" data-dismiss="modal">
              &times
            </button>
          </div>
          <div class="modal-body">
            <div class="main-card mb-3 card">
              <div class="card-body">
                <div class="widget-content p-0">
                  <div class="widget-content-wrapper">
                    <div class="widget-content-left">
                      <div class="widget-heading">예약된 시간</div>
                      <div class="widget-subheading"></div>
                    </div>
                    <div class="widget-content-right">
                      <div
                        class="widget-numbers"
                        style="color: #3ac47d !important"
                        id="ptTime"
                      >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-dismiss="modal">
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="modal" id="program">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">프로그램</h4>
            <button class="close" type="button" data-dismiss="modal">
              &times
            </button>
          </div>
          <div class="modal-body">
            <ul class="list-group">
              <li class="list-group-item">
                <div class="row">
                  <div class="text-left h4 col-6">목표 부위</div>
                  <div class="text-right h5 col-6 text-danger" id="part">
                  </div>
                </div>
              </li>
              <li class="list-group-item">
                <div class="h5" id="mention"></div>
              </li>
            </ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-dismiss="modal">
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>