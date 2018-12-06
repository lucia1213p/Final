package kr.or.tech.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.tech.board.model.dao.BoardDao;
import kr.or.tech.board.model.vo.MainpageBoard;
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.NPageData;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.board.model.vo.ShrPageData;
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.board.model.vo.ShrTechAnswer;
import kr.or.tech.board.model.vo.SptCategory;
import kr.or.tech.board.model.vo.SupportTech;
import kr.or.tech.board.model.vo.TComment;
import kr.or.tech.common.JDBCTemplate;

public class BoardService {
	//공지사항 글 작성
	public int writeNotice(int memberNo, Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		
		if(n.getNoticeFile()!=null) {
			result = new BoardDao().writeNotice(conn,memberNo,n);
		}else {
			result = new BoardDao().writeNoticeNotFile(conn,memberNo,n);
		}
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//공지사항목록
	public NPageData noticeList(int noticeCurrentPage) {
		
		Connection conn =JDBCTemplate.getConnection();
		
		//게시물개수와 navi 개수 지정
		int recordCountPerPage = 10;
		int naviCountPerPage =5;
		
		//현재페이지의 게시물 리스트 
		ArrayList<Notice> list=new BoardDao().noticeList(conn,noticeCurrentPage,recordCountPerPage);
		String pageNavi = new BoardDao().getPageNavi(conn,noticeCurrentPage,recordCountPerPage,naviCountPerPage);
		
		NPageData npd=null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			npd= new NPageData();
			npd.setList(list);
			npd.setPageNavi(pageNavi);
		}else {
			System.out.println("데이터없음");
			System.out.println(npd);
		}
		JDBCTemplate.close(conn);
		
		return npd;
		
	}
	
	//공지사항 검색
	public NPageData noticeSearchList(String keyword, String selectSearch, int NSearchCurrentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시물개수와 navi 개수 지정
		int recordCountPerPage = 10;
		int naviCountPerPage =5;
		
		//현재페이지의 게시물 리스트 
		ArrayList<Notice> list=new BoardDao().noticeSearchList(conn,keyword,selectSearch,NSearchCurrentPage,recordCountPerPage);
		String pageNavi = new BoardDao().getNoticeSearchPageNavi(conn,keyword,selectSearch,NSearchCurrentPage,recordCountPerPage,naviCountPerPage);
		
		NPageData npd=null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			npd= new NPageData();
			npd.setList(list);
			npd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return npd;
		
	}
	
	
	//공지사항글보기
	public Notice noticeInfo(int noticeNo, String boardCode) {
		Connection conn =JDBCTemplate.getConnection();
		
		//조회수 증가
		int changeHits =  new BoardDao().changeHits(conn, noticeNo, boardCode);
		
		if(changeHits>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//공지사항내용 가져오기
		Notice notice=new BoardDao().noticeInfo(conn,noticeNo,boardCode);
		
		JDBCTemplate.close(conn);
		
		return notice;
		
	}
	
	//공지사항 댓글 등록
	public int insertNComment(NComment nc) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result=new BoardDao().insertNComment(conn,nc);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//게시글의 댓글 불러오기
	public ArrayList<NComment> noticeCommentInfo(int noticeNo, String boardCode) {
		Connection conn =JDBCTemplate.getConnection();
		ArrayList<NComment> list = new BoardDao().noticeCommentInfo(conn,noticeNo,boardCode);
		JDBCTemplate.close(conn);
		return list;
	}

	//댓글 삭제
	public int deleteNoticeComm(int commNo) {
		Connection conn =JDBCTemplate.getConnection();
		int result = new BoardDao().deleteNoticeComm(conn,commNo);
		System.out.println(commNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//관리자 권한일 경우 공지사항 게시글 1개 선택
	public boolean selectOneNotice(int noticeNo) {
		Connection conn =JDBCTemplate.getConnection();
		Boolean searchResult=new BoardDao().searchYNotice(conn);
		
		if(searchResult==true) {
			int result1=new BoardDao().changeNoticeState(conn);
			System.out.println("result1="+result1);
		}
		int result2=new BoardDao().selectOneNotice(conn,noticeNo);
		System.out.println("result2="+result2);
		boolean result=false;
		if(result2>0) {
			JDBCTemplate.commit(conn);
			result=true;
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public MainpageBoard mainOneSelectNotice() {
		Connection conn =JDBCTemplate.getConnection();
		//관리자가 선택한 공지사항 게시글 1개
		Notice selectNotice = new BoardDao().mainOneSelectNotice(conn);
		//최근 공지사항 게시글5개
		ArrayList<Notice> recentNotice = new BoardDao().mainRecentNotice(conn);
		
		MainpageBoard mpb=null;
		if(!recentNotice.isEmpty()) {
			mpb=new MainpageBoard();
			mpb.setSelectNotice(selectNotice);
			mpb.setRecentNotice(recentNotice);
		}
		JDBCTemplate.close(conn);
		
		return mpb;
	}
	
	//----기술공유게시판----
	
	//기술공유게시판 리스트
	public ShrPageData shareTechList(int shrCurrentPage) {
		Connection conn =JDBCTemplate.getConnection();
		
		//게시물개수와 navi 개수 지정
		int recordCountPerPage = 1;
		int naviCountPerPage =5;
		
		//현재페이지의 게시물 리스트 
		ArrayList<ShrTech> shrList=  new BoardDao().shareTechList(conn,shrCurrentPage,recordCountPerPage);
		String pageNavi = new BoardDao().getShrPageNavi(conn,shrCurrentPage,recordCountPerPage,naviCountPerPage);
		
		ShrPageData spd=null;
		
		if(!shrList.isEmpty() && !pageNavi.isEmpty()) {
			spd= new ShrPageData();
			spd.setList(shrList);
			spd.setPageNavi(pageNavi);
		}else {
			System.out.println("데이터없음");
		}
		JDBCTemplate.close(conn);
		
		return spd;
	}

	
	//기술공유게시글 작성
	public int writeShareTech(ShrTech shr) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		
		if(shr.getFileName()!=null) {
			result = new BoardDao().writeShareTech(conn,shr);
		}else {
			result = new BoardDao().writeShareTechNotFile(conn,shr);
		}

		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public ShrTech shareTechInfo(int shareTechNo, String boardCode) {
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		int shrHits=new BoardDao().changeShrHits(conn,shareTechNo,boardCode);
		
		if(shrHits>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		ShrTech shr = new BoardDao().shareTechInfo(conn,shareTechNo,boardCode);
		
		
		JDBCTemplate.close(conn);
		return shr;
	}

	//기술공유 게시판 답변 insert
	public int writeAnswer(ShrTechAnswer sta) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().writeAnswer(conn,sta);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	//해당 게시글의 기술공유답변리스트
	public ArrayList<ShrTechAnswer> srtAnswerList(int shareTechNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ShrTechAnswer> answerList = new BoardDao().srtAnswerList(conn,shareTechNo);
		
		JDBCTemplate.close(conn);
		return answerList;
	}
	
	//기술공유게시판 답변 채택
	public int adoptAnswer(int answNum, int shrNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().adoptAnswer(conn,answNum,shrNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	//답변채택 대기상태로 변경
	public void adoptWait(int shareTechNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().adoptWait(conn, shareTechNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
	}

	//답변이 아예 없을 경우
	public void adoptN(int shareTechNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().adoptN(conn, shareTechNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
	}

	//답변이 이미 채택되었을 경우 
	public void adoptY(int shareTechNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().adoptY(conn, shareTechNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
	}
	
	//----------기술지원게시판------------
	//기술지원게시판 리스트
	public ArrayList<SupportTech> supportTechList(String memberCode) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<SupportTech> sptList=null;
		sptList = new BoardDao().supportTechList(conn,memberCode);
		JDBCTemplate.close(conn);
		
		return sptList;
	}

	//기술지원게시글 작성
	public int writeSupportTech(SupportTech spt) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		
		if(spt.getFileName()!=null) {
			result = new BoardDao().writeSupportTech(conn,spt);
		}else {
			result = new BoardDao().writeSupportTechNotFile(conn,spt);
		}
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	//기술지원 게시판 제조사 관리자 자동할당 
	public int maAutoAssign(int maNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().maAutoAssign(conn,maNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//기술지원 게시글 정보
	public SupportTech supportTechInfo(int sptTechNo, String boardCode) {
		Connection conn = JDBCTemplate.getConnection();
		 SupportTech spt = new BoardDao().supportTechinfo(conn,sptTechNo,boardCode);
		
		 JDBCTemplate.close(conn);
		return spt;
	}

	//기술지원 게시글 답변 정보
	public ArrayList<TComment> sptAnswerList(int sptTechNo, String boardCode) {
		Connection conn =JDBCTemplate.getConnection();
		ArrayList<TComment> list = new BoardDao().supportCommentInfo(conn,sptTechNo,boardCode);
		JDBCTemplate.close(conn);
		return list;
	}

	//기술공유 답변 삭제
	public int deleteShareAnswer(int answNo, int shareNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().deleteShareAnswer(conn,answNo,shareNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Notice noticeUpdateForm(int noticeNo, String boardCode) {
	//공지사항내용 가져오기
		Connection conn = JDBCTemplate.getConnection();
		Notice notice=new BoardDao().noticeUpdateForm(conn,noticeNo,boardCode);
		
		JDBCTemplate.close(conn);
		
		return notice;
	}

	public int noticeUpdate(int noticeNo, String boardCode, String noticeTitle, String noticeContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().noticeUpdate(conn, noticeNo,boardCode,noticeTitle,noticeContent);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
		
	}

	public int noticeDelete(int noticeNo, String boardCode) {
		Connection conn = JDBCTemplate.getConnection();
		int result=0;
		//해당 게시글의 댓글 삭제
		int result1 = new BoardDao().noticeCommentDelete(conn, noticeNo,boardCode);
		System.out.println("result1:"+result1);
		//게시글 삭제
		int result2 = new BoardDao().noticeDelete(conn, noticeNo,boardCode);
		System.out.println("result2:"+result2);
		if(result2>0) {
			JDBCTemplate.commit(conn);
			result=1;
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	//기술지원 카테고리
	public ArrayList<SptCategory> sptCategoryList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<SptCategory> list=new BoardDao().sptCategoryList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	//기술지원댓글 insert
	public int insertTComment(TComment tc) {
		Connection conn = JDBCTemplate.getConnection();
		int result=0;
		//댓글 카테고리에 따라 게시글 상태 변경
		int active = new BoardDao().updateSupportActive(conn,tc);
		
		if(active>0) {
			result=new BoardDao().insertTComment(conn,tc);
		}
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	//기술지원페이지 제조사 담당자 선택
	public int selectSptMclerk(int sptNo, String boardCode, int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().selectSptMclerk(conn,sptNo,boardCode,memNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
