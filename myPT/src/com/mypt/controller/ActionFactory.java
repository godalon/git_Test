package com.mypt.controller;

import java.util.HashMap;
import java.util.Map;

import com.mypt.action.board.DeleteAction;
import com.mypt.action.board.LikeAction;
import com.mypt.action.board.ReplyAction;
import com.mypt.action.board.SearchCommunityAction;
import com.mypt.action.board.SearchPhotoAction;
import com.mypt.action.board.SearchQuestionAction;
import com.mypt.action.board.TestViewAction;
import com.mypt.action.board.UpdateAction;
import com.mypt.action.board.UpdateViewAction;
import com.mypt.action.board.ViewAction;
import com.mypt.action.board.WriteAction;
import com.mypt.action.board.comment.CommentChangePageAction;
import com.mypt.action.board.comment.CommentDeleteAction;
import com.mypt.action.board.comment.CommentInsertAction;
import com.mypt.action.board.comment.CommentUpdateAction;
import com.mypt.action.check.EmailCheckAction;
import com.mypt.action.check.NickCheckAction;
import com.mypt.action.crowded.CrowdedAction;
import com.mypt.action.detail.AdminTrainerDeleteAction;
import com.mypt.action.detail.AdminTrainerDetailAction;
import com.mypt.action.detail.AdminTrainerUpdateAction;
import com.mypt.action.detail.AdminUserDeleteAction;
import com.mypt.action.detail.AdminUserDetailAction;
import com.mypt.action.detail.AdminUserUpdateAction;
import com.mypt.action.detail.TrainerMyDetailAction;
import com.mypt.action.detail.TrainerMyUpdateAction;
import com.mypt.action.detail.UserMyDetailAction;
import com.mypt.action.detail.UserMyUpdateAction;
import com.mypt.action.history.AdminHistoryDeleteAction;
import com.mypt.action.inbody.UserInbodyResultAction;
import com.mypt.action.income.IncomeAction;
import com.mypt.action.income.IncomeTrainerAction;
import com.mypt.action.income.MoveIncomeAction;
import com.mypt.action.list.AdminHeadCountListAction;
import com.mypt.action.list.AdminHistoryListAction;
import com.mypt.action.list.AdminScheduleListAction;
import com.mypt.action.list.AdminTrainerListViewAction;
import com.mypt.action.list.AdminUserListViewAction;
import com.mypt.action.list.TrainerUserListViewAction;
import com.mypt.action.list.UserBoardListViewAction;
import com.mypt.action.list.UserCommentListViewAction;
import com.mypt.action.list.UserLikeListViewAction;
import com.mypt.action.login.LogoutAction;
import com.mypt.action.login.TrainerCheckSessionId;
import com.mypt.action.login.TrainerLoginAction;
import com.mypt.action.login.UserCheckSessionId;
import com.mypt.action.login.UserLoginAction;
import com.mypt.action.move.MoveBoardDetailAction;
import com.mypt.action.move.MoveBoardUpdateAction;
import com.mypt.action.move.MoveCommunityAction;
import com.mypt.action.move.MoveInbody;
import com.mypt.action.move.MoveIntroAction;
import com.mypt.action.move.MovePayment;
import com.mypt.action.move.MovePhotoAction;
import com.mypt.action.move.MoveQuestionAction;
import com.mypt.action.move.MoveReplyAction;
import com.mypt.action.move.MoveSchedule;
import com.mypt.action.move.MoveTrainerProfileAction;
import com.mypt.action.move.MoveTrainerRegister;
import com.mypt.action.move.MoveUserMain;
import com.mypt.action.move.MoveUserProfileAction;
import com.mypt.action.move.MoveUserRegister;
import com.mypt.action.move.MoveWriteAction;
import com.mypt.action.payment.UserPaymentDataAction;
import com.mypt.action.qr.QrCheckAction;
import com.mypt.action.qr.QrViewAction;
import com.mypt.action.register.AdminTrainerRegisterAction;
import com.mypt.action.register.AdminUserRegisterAction;
import com.mypt.action.schedule.AdminScheduleDeleteAction;
import com.mypt.action.schedule.ScheduleLoadAction;
import com.mypt.action.schedule.TrainerPTFinishAction;
import com.mypt.action.schedule.TrainerScheduleSettingAction;
import com.mypt.action.schedule.TrainerTodayPTAction;
import com.mypt.action.schedule.UserScheduleViewAction;



public class ActionFactory 
{
	
	//싱글톤
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() 
	{
		return instance;
	}
	
	private ActionFactory()
	{

		//userList
		map.put("/userList", new AdminUserListViewAction());
		//trainerList
		map.put("/trainerList", new AdminTrainerListViewAction());
		//ptUserList
		map.put("/ptUserList", new TrainerUserListViewAction());
		
		// 인트로 화면
		map.put("/moveIntro", new MoveIntroAction());
		map.put("/moveInbody", new MoveInbody());
		
		//userLogin
		map.put("/userLogin", new UserLoginAction());
		map.put("/userMain", new MoveUserMain());
		map.put("/getInbodyResult", new UserInbodyResultAction());
			
		//trainerLogin
		map.put("/trainerLogin", new TrainerLoginAction());
		
		//트레이너 등록
		map.put("/moveTrainerRegister", new MoveTrainerRegister());		
		map.put("/trainerRegisterAction", new AdminTrainerRegisterAction());
		
		//회원가입
		map.put("/moveUserRegister", new MoveUserRegister());		
		map.put("/userRegisterAction", new AdminUserRegisterAction());
		map.put("/nickCheck", new NickCheckAction());
		map.put("/emailCheck", new EmailCheckAction());
		
		//결제
		map.put("/movePayment", new MovePayment());
		map.put("/paymentData", new UserPaymentDataAction());
		
		//매출
		map.put("/moveIncome", new MoveIncomeAction());
		map.put("/incomeChart", new IncomeAction());
		map.put("/incomeTrainerChart", new IncomeTrainerAction());
		
		//detail
		map.put("/adminTrainerDetail", new AdminTrainerDetailAction());
		map.put("/adminUserDetail", new AdminUserDetailAction());
		map.put("/userMyDetail", new UserMyDetailAction());
		
		//update
		map.put("/userMyUpdate", new UserMyUpdateAction());
		map.put("/adminTrainerUpdate", new AdminTrainerUpdateAction());
		map.put("/adminUserUpdate", new AdminUserUpdateAction());
		
		//qr코드
		map.put("/qrGet",new QrViewAction());
		
		// 출석
		map.put("/qrCheck",new QrCheckAction());
		
		// 스케줄 관리
		map.put("/moveSchedule",new MoveSchedule());
		map.put("/scheduleSetting",new TrainerScheduleSettingAction());
		
		//로그아웃
		map.put("/logout",new LogoutAction());
		
		// 혼잡도
		map.put("/crowded",new CrowdedAction());
		
		map.put("/moveCommunity",new MoveCommunityAction());
		
		// 글쓰기
		map.put("/write",new WriteAction());
		
		map.put("/moveWrite",new MoveWriteAction());
		// 게시판 상세보기
		map.put("/boardView",new ViewAction());
		
		// 유저 메인페이지 스케줄
		map.put("/userSchedule", new UserScheduleViewAction());
				
				
		//게시판 좋아요
		map.put("/boardLike", new LikeAction());
		
		map.put("/moveReply", new MoveReplyAction());
		
		//스케줄 불러오기
		map.put("/scheduleLoad",new ScheduleLoadAction());	
		
		//답글쓰기
		map.put("/boardReply",new ReplyAction());
		
		//글 삭제
		map.put("/boardDelete",new DeleteAction());
		
		
		
		// 포토게시판 이동
		map.put("/movePhoto",new MovePhotoAction());
		// 포토게시판 검색
		map.put("/searchPhoto",new SearchPhotoAction());
		
		map.put("/testBoardView",new TestViewAction());
		map.put("/commentInsert", new CommentInsertAction());
		map.put("/commentDelete", new CommentDeleteAction());
		map.put("/commentChangePage", new CommentChangePageAction());
		map.put("/commentUpdate", new CommentUpdateAction());
		

		
		//게시판 수정
		map.put("/boardUpdate", new UpdateAction());
		map.put("/moveUpdateBoard", new MoveBoardUpdateAction());
		map.put("/updateView", new UpdateViewAction());
		
		//질문게시판
		map.put("/moveQuestion", new MoveQuestionAction());
		map.put("/moveBoardDetail", new MoveBoardDetailAction());
		map.put("/searchQuestion", new SearchQuestionAction());
		
		//프로필 이동
		map.put("/moveUserProfile", new MoveUserProfileAction());		
		
		//유저 게시판 목록
		map.put("/userBoardList", new UserBoardListViewAction());
		map.put("/userLikeList", new UserLikeListViewAction());
		map.put("/userCommentList", new UserCommentListViewAction());
		
		//트레이너 마이 페이지
		map.put("/trainerMyUpdate", new TrainerMyUpdateAction());
		map.put("/trainerMyDetail", new TrainerMyDetailAction());
		map.put("/moveTrainerProfile", new MoveTrainerProfileAction());
		map.put("/trainerMain", new TrainerTodayPTAction());
		map.put("/trainerPTFinish", new TrainerPTFinishAction());
				
		map.put("/searchCommunity", new SearchCommunityAction());
		
		//관리자 기록
		map.put("/adminHistory", new AdminHistoryListAction());
		map.put("/adminHeadcount", new AdminHeadCountListAction());
		
		//스케줄 리스트
		map.put("/adminSchedule",new AdminScheduleListAction());
		
		//유저 삭제
		map.put("/adminUserDelete", new AdminUserDeleteAction());
		//트레이너 삭제
		map.put("/adminTrainerDelete", new AdminTrainerDeleteAction());
		//스케줄 삭제
		map.put("/adminScheduleDelete",new AdminScheduleDeleteAction());
		//결제내역 삭제
		map.put("/adminHistoryDelete",new AdminHistoryDeleteAction());
		
		//쿠키-세션아이디로 로그인 유지
		map.put("/userCheckSessionId", new UserCheckSessionId());
		map.put("/trainerCheckSessionId", new TrainerCheckSessionId());
		
	}
	
	private Map<String, Action> map = new HashMap< >();	
	
	
	public Action getAction(String command)
	{
		Action action = null;
		
		action = map.get(command);
		
		return action;
	}
	
}
