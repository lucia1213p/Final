package kr.or.tech.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdk.nashorn.internal.scripts.JD;
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.common.JDBCTemplate;

public class BoardDao {
	public int writeNotice(Connection conn, int memberNo, Notice n) {
		PreparedStatement pstmt=null;
		int result = 0;
		
		String query = "insert all into notice values(NOTICE_SQ.NEXTVAL,?,?,sysdate,0,?,'notice',?,?) "
				+ "into FILEINFO values(FILE_SQ.NEXTVAL,?,'notice',NOTICE_SQ.CURRVAL) "
				+ "select * from dual";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, memberNo);
			pstmt.setString(4, n.getNoticeGrade());
			pstmt.setString(5, n.getNoticeFile());
			pstmt.setString(6, n.getNoticeFile());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int writeNoticeFile(Connection conn, Notice n) {
		PreparedStatement pstmt=null;
		int result = 0;
		
		String query = "insert into FILEINFO values(FILE_SQ.NEXTVAL,?,'notice',NOTICE_SQ.CURRVAL) ";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeFile());
			
			result= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> noticeList(Connection conn) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		Notice n = null;
		
		String query = "select n.*, g.GRADE_NAME, m.MEMBER_NAME from notice n, NOTICE_GRADE g , member m where n.MEMBER_NO=m.MEMBER_NO and g.N_GRADE=n.N_GRADE";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("n_no"));
				n.setNoticeTitle(rset.getString("n_title"));
				n.setNoticeContent(rset.getString("n_cont"));
				n.setNoticeDate(rset.getDate("n_date"));
				n.setNoticeHits(rset.getInt("n_hits"));
				n.setMemberNo(rset.getInt("member_no"));
				n.setMemberName(rset.getString("member_name"));
				n.setBoardCode(rset.getString("b_code"));
				n.setNoticeFile(rset.getString("n_file"));
				n.setNoticeGrade(rset.getString("n_grade"));
				n.setNoticeGradeName(rset.getString("grade_name"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
		
	}

	public Notice noticeInfo(Connection conn, int noticeNo, String boardCode) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Notice notice=null;
		
		String query = "select n.*,g.GRADE_NAME,m.MEMBER_NAME from notice n, member m, NOTICE_GRADE g "
				+ "where n_no=? and b_code=? and n.MEMBER_NO=m.MEMBER_NO and g.N_GRADE=n.N_GRADE";

		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.setString(2, boardCode);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				notice=new Notice();
				notice.setNoticeNo(rset.getInt("n_no"));
				notice.setNoticeTitle(rset.getString("n_title"));
				notice.setNoticeContent(rset.getString("n_cont"));
				notice.setNoticeDate(rset.getDate("n_date"));
				notice.setNoticeHits(rset.getInt("n_hits"));
				notice.setMemberNo(rset.getInt("member_no"));
				notice.setMemberName(rset.getString("member_name"));
				notice.setBoardCode(rset.getString("b_code"));
				notice.setNoticeFile(rset.getString("n_file"));
				notice.setNoticeGrade(rset.getString("n_grade"));
				notice.setNoticeGradeName(rset.getString("grade_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return notice;
		
	}

	public int insertNComment(Connection conn, NComment nc) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String query="INSERT INTO COMMENT_TBL VALUES(COMMENT_SQ.NEXTVAL,?,SYSDATE,?,?,?)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,nc.getCommCont());
			pstmt.setString(2,nc.getBoardCode());
			pstmt.setInt(3, nc.getBoardNo());
			pstmt.setInt(4, nc.getMemberNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			
		}
		return result;
		
	}
	
	//공지사항의 댓글 리스트 불러오기
	public ArrayList<NComment> noticeCommentInfo(Connection conn, int noticeNo, String boardCode) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		NComment nc=null;
		ArrayList<NComment> list=new ArrayList<NComment>();
		String query = "select c.*,m.MEMBER_ID from COMMENT_TBL c, Member m where c.MEMBER_NO=m.MEMBER_NO and c.B_NO=? and c.B_CODE=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.setString(2, boardCode);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				nc=new NComment();
				
				nc.setCommNo(rset.getInt("comm_no"));
				nc.setCommCont(rset.getString("comm_cont"));
				nc.setCommDate(rset.getDate("comm_date"));
				nc.setBoardCode(rset.getString("b_code"));
				nc.setBoardNo(rset.getInt("b_no"));
				nc.setMemberNo(rset.getInt("MEMBER_NO"));
				nc.setMemberName(rset.getString("member_id"));
				
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int deleteNoticeComm(Connection conn, int commNo) {
		
		PreparedStatement pstmt=null;
		int result = 0;
		
		String query ="delete from COMMENT_TBL where comm_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, commNo);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

}
