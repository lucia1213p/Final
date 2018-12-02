package kr.or.tech.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.board.model.vo.ShrTechAnswer;
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.common.JDBCTemplate;

public class BoardDao {
	public int writeNotice(Connection conn, int memberNo, Notice n) {
		PreparedStatement pstmt=null;
		int result = 0;
		
		String query = "insert all into notice values(NOTICE_SQ.NEXTVAL,?,?,sysdate,0,?,'notice',?,?,'N') "
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

	public int writeNoticeNotFile(Connection conn,int memberNo, Notice n) {
		PreparedStatement pstmt=null;
		int result = 0;
		
		String query = "insert into notice values(NOTICE_SQ.NEXTVAL,?,?,sysdate,0,?,'notice',?,?,'N') ";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, memberNo);
			pstmt.setString(4, n.getNoticeGrade());
			pstmt.setString(5, n.getNoticeFile());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//공지사항 목록 게시물 페이징
	public ArrayList<Notice> noticeList(Connection conn,int noticeCurrentPage, int recordCountPerPage) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		Notice n = null;
		
		//시작 게시물 계산
		int start = noticeCurrentPage*recordCountPerPage-(recordCountPerPage-1);
		//끝 게시물
		int end = noticeCurrentPage*recordCountPerPage;
		
		String query = "select * from (select row_number() over (order by n_no desc) num ,n.*, g.GRADE_NAME, m.MEMBER_NAME from notice n, NOTICE_GRADE g , member m where n.MEMBER_NO=m.MEMBER_NO and g.N_GRADE=n.N_GRADE) where num between ? and ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
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
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}
	
	//공지사항목록 navi
	public String getPageNavi(Connection conn, int noticeCurrentPage, int recordCountPerPage, int naviCountPerPage) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		//게시물의 토탈 개수
		int recordTotalCount=0;
		String query ="select count(*) as totalcount from notice";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount= rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		//페이지 총 개수
		int pageTotalCount = 0;
		
		if(recordTotalCount % recordCountPerPage!=0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		//에러방지 코드
		if(noticeCurrentPage<1) {
			noticeCurrentPage=1;
		}else if(noticeCurrentPage>pageTotalCount){
			noticeCurrentPage = pageTotalCount;
		}
		
		//시작페이지 구하기
		int startNavi = ((noticeCurrentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		//끝페이지 구하기
		int endNavi = startNavi + naviCountPerPage -1;
		
		// 끝 navi를 구할 때 주의해야할점
		// 토탈 개수가 122개라면 총 토탈페이지는 13개 만들어져야함
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 끝 navi가 이상하게구해질 수 있음
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		
		//'<'과 '>'
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) {needPrev = false;}
		if(endNavi==pageTotalCount) {needNext=false;}
		
		StringBuilder sb = new StringBuilder();
		//needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) {
			sb.append("<a href='/noticeList.do?noticeCurrentPage="+(startNavi-1)+"'> << </a> ");
		}
		for(int i = startNavi;i<=endNavi;i++) {
			if(i==noticeCurrentPage) {
				sb.append("<a href='/noticeList.do?noticeCurrentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/noticeList.do?noticeCurrentPage="+i+"'>"+i+"</a> ");
			}
		}

		if(needNext) {
			sb.append("<a href='/noticeList.do?noticeCurrentPage="+(endNavi+1)+"'> >> </a> ");
		}
		return sb.toString();
	}
	
	//공지사항 검색 페이징
	public ArrayList<Notice> noticeSearchList(Connection conn, String keyword, String selectSearch,
		    int nSearchCurrentPage, int recordCountPerPage) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		Notice n = null;
		
		//시작 게시물 계산
		int start = nSearchCurrentPage*recordCountPerPage-(recordCountPerPage-1);
		//끝 게시물
		int end = nSearchCurrentPage*recordCountPerPage;
		
		String query = "select * from (select row_number() over (order by n_no desc) num ,n.*, g.GRADE_NAME, m.member_name from notice n, NOTICE_GRADE g , member m where n.MEMBER_NO=m.MEMBER_NO and g.N_GRADE=n.N_GRADE and ? like ?) where num between ? and ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, selectSearch);
			pstmt.setString(2, '%'+keyword+'%');
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
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
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//공지사항 검색 내비
	public String getNoticeSearchPageNavi(Connection conn, String keyword, String selectSearch, int nSearchCurrentPage,
			int recordCountPerPage, int naviCountPerPage) {
		
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		//게시물의 토탈 개수
		int recordTotalCount=0;
		String query ="select count(*) as totalcount from notice n, member m where n.MEMBER_NO=m.MEMBER_NO and ? like ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, selectSearch);
			pstmt.setString(2, '%'+keyword+'%');
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount= rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		//페이지 총 개수
		int pageTotalCount = 0;
		
		if(recordTotalCount % recordCountPerPage!=0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		//에러방지 코드
		if(nSearchCurrentPage<1) {
			nSearchCurrentPage=1;
		}else if(nSearchCurrentPage>pageTotalCount){
			nSearchCurrentPage = pageTotalCount;
		}
		
		//시작페이지 구하기
		int startNavi = ((nSearchCurrentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		//끝페이지 구하기
		int endNavi = startNavi + naviCountPerPage -1;
		
		// 끝 navi를 구할 때 주의해야할점
		// 토탈 개수가 122개라면 총 토탈페이지는 13개 만들어져야함
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 끝 navi가 이상하게구해질 수 있음
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		
		//'<'과 '>'
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) {needPrev = false;}
		if(endNavi==pageTotalCount) {needNext=false;}
		
		StringBuilder sb = new StringBuilder();
		//needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) {
			sb.append("<a href='/noticeSearch.do?searchCurrentPage="+(startNavi-1)+"'> << </a> ");
		}
		for(int i = startNavi;i<=endNavi;i++) {
			if(i==nSearchCurrentPage) {
				sb.append("<a href='/noticeSearch.do?searchCurrentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/noticeSearch.do?searchCurrentPage="+i+"'>"+i+"</a> ");
			}
		}

		if(needNext) {
			sb.append("<a href='/noticeSearch.do?searchCurrentPage="+(endNavi+1)+"'> >> </a> ");
		}
		return sb.toString();
	}
	
	//조회수 증가
	public int changeHits(Connection conn, int noticeNo, String boardCode) {
		PreparedStatement pstmt=null;
		int result = 0;
		String query ="update notice set N_HITS=N_HITS+1 where n_no=? and b_code=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.setString(2, boardCode);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
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
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
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
	//이전에 선택한 공지사항 게시물이 있었는지 확인
	public Boolean searchYNotice(Connection conn) {
		Statement stmt=null;
		ResultSet rset=null;
		int noticeNo=0;
		Boolean result=false;
		String query = "select * from notice where n_state='Y'";
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			if(rset.next()) {
				noticeNo=rset.getInt("n_no");
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(stmt);
		}
		System.out.println("dao1실행완료");
		return result;
	}
	
	//관리자 권한일 경우 공지사항 게시글 1개 선택 => 원래 선택되었던 상태 N으로
	public int changeNoticeState(Connection conn) {
		Statement stmt=null;
		int result=0;
		String query = "update notice set n_state='N' where n_state='Y'";
		try {
			stmt=conn.createStatement();
			result=stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(stmt);
		}
		System.out.println("dao1실행완료");
		return result;
	}
	
	//선택한 공지사항 게시글 상태 변경
	public int selectOneNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String query ="update notice set n_state='Y' where n_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Notice mainOneSelectNotice(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Notice selectNotice=null;
		String query="select n.*,m.MEMBER_NAME from notice n, member m where n.member_no=m.MEMBER_NO and n_state='Y'";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				selectNotice=new Notice();
				selectNotice.setNoticeNo(rset.getInt("n_no"));
				selectNotice.setNoticeTitle(rset.getString("n_title"));
				selectNotice.setNoticeContent(rset.getString("n_cont"));
				selectNotice.setNoticeDate(rset.getDate("n_date"));
				selectNotice.setNoticeHits(rset.getInt("n_hits"));
				selectNotice.setMemberNo(rset.getInt("member_no"));
				selectNotice.setMemberName(rset.getString("member_name"));
				selectNotice.setBoardCode(rset.getString("b_code"));
				selectNotice.setNoticeGrade(rset.getString("n_grade"));
				selectNotice.setNoticeFile(rset.getString("n_file"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return selectNotice;
	}

	//최근 공지사항 게시글 5개 불러오기
	public ArrayList<Notice> mainRecentNotice(Connection conn) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Notice> recentNotice=new ArrayList<Notice>();
		String query = "select a.*,m.MEMBER_NAME from(SELECT * FROM notice ORDER BY n_date DESC)a, member m where a.member_no=m.MEMBER_NO and rownum<=5";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice();
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
				
				recentNotice.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return recentNotice;
	}
	
	//----------기술공유게시판

	public ArrayList<ShrTech> shareTechList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<ShrTech> shrList=new ArrayList<ShrTech>();
		ShrTech shr=null;
		
		String query="select s.*, m.MEMBER_NAME,t.S_APTNAME from SHR_TECH s , member m, SBOARD_ST t where s.MEMBER_NO=m.MEMBER_NO and s.S_ADDOPT=t.S_ADDOPT";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				shr=new ShrTech();
				shr.setShareNo(rset.getInt("s_no"));
				shr.setShareTitle(rset.getString("s_title"));
				shr.setShareCont(rset.getString("s_cont"));
				shr.setShareDate(rset.getDate("s_date"));
				shr.setShareHits(rset.getInt("s_hits"));
				shr.setMemberNo(rset.getInt("member_no"));
				shr.setBoardCode(rset.getString("b_code"));
				shr.setFileName(rset.getString("s_file"));
				shr.setShareAddopt(rset.getString("s_addopt"));
				shr.setAddoptName(rset.getString("s_aptname"));
				shr.setMemberId(rset.getString("member_name"));
				
				shrList.add(shr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return shrList;
	}
	
	//기술공유게시글 작성
	public int writeShareTech(Connection conn, ShrTech shr) {
		PreparedStatement pstmt=null;
		String query="insert into SHR_TECH values(SHARE_SQ.NEXTVAL,?,?,sysdate,0,?,'share',?,'N')";
		int result=0;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, shr.getShareTitle());
			pstmt.setString(2, shr.getShareCont());
			pstmt.setInt(3, shr.getMemberNo());
			pstmt.setString(4, shr.getFileName());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	//기술공유 게시글 조회수 증가
	public int changeShrHits(Connection conn, int shareTechNo, String boardCode) {
		PreparedStatement pstmt=null;
		int result = 0;
		String query ="update SHR_TECH set S_HITS=S_HITS+1 where s_no=? and b_code=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, shareTechNo);
			pstmt.setString(2, boardCode);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	//기술공유 게시글 불러오기
	public ShrTech shareTechInfo(Connection conn, int shareTechNo, String boardCode) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ShrTech shr=null;
		String query = "select s.*,m.MEMBER_ID,t.S_APTNAME from SHR_TECH s, member m, SBOARD_ST t where s.MEMBER_NO=m.MEMBER_NO and s.S_ADDOPT=t.S_ADDOPT and  s_no=? and b_code=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, shareTechNo);
			pstmt.setString(2, boardCode);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				shr = new ShrTech();
				shr.setShareNo(rset.getInt("s_no"));
				shr.setShareTitle(rset.getString("s_title"));
				shr.setShareCont(rset.getString("s_cont"));
				shr.setShareDate(rset.getDate("s_date"));
				shr.setShareHits(rset.getInt("s_hits"));
				shr.setMemberNo(rset.getInt("member_no"));
				shr.setBoardCode(rset.getString("b_code"));
				shr.setFileName(rset.getString("s_file"));
				shr.setShareAddopt(rset.getString("s_addopt"));
				shr.setMemberId(rset.getString("member_id"));
				shr.setAddoptName(rset.getString("s_aptname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return shr;
		
	}

	public int writeAnswer(Connection conn, ShrTechAnswer sta) {
		PreparedStatement pstmt=null;
		int result =0;
		String query = "insert into SHR_ANSWER values (ANSWER_SQ.NEXTVAL,?,sysdate,?,?,'N')";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, sta.getAnswCont());
			pstmt.setInt(2, sta.getShrNo());
			pstmt.setInt(3, sta.getMemberNo());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	//해당 게시글의 기술공유답변리스트
	public ArrayList<ShrTechAnswer> srtAnswerList(Connection conn, int shareTechNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query = "select s.*,m.MEMBER_ID from SHR_ANSWER s, member m where s.MEMBER_NO=m.MEMBER_NO and s_no=?";
		ArrayList<ShrTechAnswer> answerList= new ArrayList<ShrTechAnswer>();
		ShrTechAnswer shr=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, shareTechNo);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				shr=new ShrTechAnswer();
				shr.setAnswNo(rset.getInt("answ_no"));
				shr.setAnswCont(rset.getString("answ_cont"));
				shr.setAnswDate(rset.getDate("answ_date"));
				shr.setShrNo(rset.getInt("s_no"));
				shr.setMemberNo(rset.getInt("member_no"));
				shr.setMemberId(rset.getString("member_id"));
				shr.setAnswAddopt(rset.getString("answ_adopt"));
				answerList.add(shr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return answerList;
	}





}
