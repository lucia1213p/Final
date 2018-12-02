package kr.or.tech.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.tech.common.JDBCTemplate;
import kr.or.tech.member.model.vo.BelongSelect;
import kr.or.tech.member.model.vo.Member;

public class MemberDao {

	public Member login(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Member member=null;
		String query="select * from member where member_id=? AND member_pwd=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				member=new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPwd(rset.getString("member_pwd"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberGrade(rset.getString("grade_code"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
		
	}

	public int insertMember(Member m, Connection conn) {
		PreparedStatement pstmt=null;
		int result=0;
		Member member=null;
		
		String query ="insert into member values(MEMBER_SQ.NEXTVAL,?,?,?,?,?,?,sysdate,'W',?,?)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPwd());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setString(5, m.getMemberAddr());
			pstmt.setString(6, m.getMemberEmail());
			pstmt.setString(7, m.getMemberGrade());
			pstmt.setString(8, m.getMemCode());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
				
		return result;
	}

	public Member memberInfo(Connection conn, String userId) {

		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query = "select MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_JOINDATE,GRADE_NAME,BELONG_NAME from mem_belong a, "
				+ "(select GRADE_NAME,MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_JOINDATE,MEMBER_CODE from mem_grade a,(select * from member) b"
				+ " where a.GRADE_CODE=b.grade_code AND MEMBER_ID=?) b where a.MEMBER_CODE=b.MEMBER_CODE ";
		
		Member m = null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				m=new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberJoin(rset.getString("member_joindate"));
				m.setMemberGrade(rset.getString("grade_name"));
				m.setMemCode(rset.getString("belong_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return m;
	}

	public String idCheck(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String memberId = null;
		String query="select * from member where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				memberId=rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return memberId;
	}

	public ArrayList<BelongSelect> loadBelong(Connection conn, String selectGrade, String belongCode) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		BelongSelect belong=null;
		ArrayList<BelongSelect> belongList= new ArrayList<BelongSelect>();
		String query = "select * from MEM_BELONG where belong_field=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, belongCode);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				belong=new BelongSelect();
				belong.setBelongCode(rset.getString("member_code"));
				belong.setBelongName(rset.getString("belong_name"));
				belongList.add(belong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return belongList;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "update member set member_phone=?,member_email=?,member_addr=? "
				+ " where member_no = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberAddr());
			pstmt.setInt(4, member.getMemberNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

}
