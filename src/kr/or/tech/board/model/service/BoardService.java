package kr.or.tech.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.tech.board.model.dao.BoardDao;
import kr.or.tech.board.model.vo.MainpageBoard;
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.common.JDBCTemplate;

public class BoardService {
	//공지사항 글 작성
	public int writeNotice(int memberNo, Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().writeNotice(conn,memberNo,n);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//공지사항목록
	public ArrayList<Notice> noticeList() {
		
		Connection conn =JDBCTemplate.getConnection();
		
		ArrayList<Notice> list=new BoardDao().noticeList(conn);
		
		if(list.isEmpty()) {
			System.out.println("리스트비어있음");
		}else {
			System.out.println("데이터들어옴");
		}
		JDBCTemplate.close(conn);
		
		return list;
		
	}
	
	//공지사항글보기
	public Notice noticeInfo(int noticeNo, String boardCode) {
		Connection conn =JDBCTemplate.getConnection();
		
		System.out.println("게시글번호:" +noticeNo);
		System.out.println("게시판코드: "+boardCode);
		Notice notice=new BoardDao().noticeInfo(conn,noticeNo,boardCode);
		
		if(notice!=null) {
			System.out.println("게시글받아옴");
		}
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
	
	//기술공유게시판 리스트
	public ArrayList<ShrTech> shareTechList() {
		Connection conn =JDBCTemplate.getConnection();
		
		ArrayList<ShrTech> shrList=  new BoardDao().shareTechList(conn);
		
		JDBCTemplate.close(conn);
		
		return shrList;
	}

}
