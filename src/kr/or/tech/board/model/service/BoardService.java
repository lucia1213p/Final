package kr.or.tech.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.tech.board.model.dao.BoardDao;
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.Notice;
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

}
