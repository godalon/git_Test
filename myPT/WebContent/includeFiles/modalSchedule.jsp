<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal" id="PT">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="">
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
                        <div class="widget-heading">예약할 시간</div>
                        <div class="widget-subheading"></div>
                      </div>
                      <div class="widget-content-right">
                        <input type="time" name="time" id="ptTime" />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button
                type="submit"
                class="btn btn-primary"
                id="ptSubmit"
                data-dismiss="modal"
              >
                확인
              </button>
              <button
                type="button"
                class="btn btn-danger"
                data-target="#"
                data-dismiss="modal"
                id="ptCancel"
              >
                취소
              </button>
            </div>
          </form>
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
                  <input type="text" id="proPart" class="text-right col-6" />
                </div>
              </li>
              <li class="list-group-item text-center">
                <textarea class="pro" id="proMention"></textarea>
              </li>
            </ul>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-primary"
              data-dismiss="modal"
              id="proSubmit"
            >
              확인
            </button>
            <button
              type="button"
              class="btn btn-danger"
              data-dismiss="modal"
              id="proCancel"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>