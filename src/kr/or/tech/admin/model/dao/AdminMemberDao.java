package kr.or.tech.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.tech.common.JDBCTemplate;
import kr.or.tech.member.model.vo.Member;

public class AdminMemberDao {

	//회원가입 승인 대기 리스트
	public ArrayList<Member> waitMemberList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Member> list = new ArrayList<Member>();
		Member member=null;
		String query = "select * from member where MEMBER_ACTIVE='W'";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				member=new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberAddr(rset.getString("member_addr"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberJoin(rset.getString("member_joindate"));
				member.setMemberGrade(rset.getString("grade_code"));
				member.setMemCode(rset.getString("member_code"));
				
				list.add(member);
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

	public ArrayList<Member> allMemberList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Member> list=new ArrayList<Member>();
		Member member=null;
		String query = "select * from member where MEMBER_ACTIVE='Y'";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				member=new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberAddr(rset.getString("member_addr"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberJoin(rset.getString("member_joindate"));
				member.setMemberGrade(rset.getString("grade_code"));
				member.setMemCode(rset.getString("member_code"));
				
				list.add(member);
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

	public int approveMember(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String query = "update member set member_active='Y' where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public int refuseMember(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String query = "delete from member where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Member adMemberInfo(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query = "select MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_JOINDATE,GRADE_NAME,BELONG_NAME from mem_belong a, "
				+ "(select GRADE_NAME,MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_JOINDATE,MEMBER_CODE from mem_grade a,(select * from member) b"
				+ " where a.GRADE_CODE=b.grade_code AND MEMBER_NO=?) b where a.MEMBER_CODE=b.MEMBER_CODE ";
		
		Member m = null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			
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

	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		
		String query = "delete from member where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int adUpdateMember(Connection conn, Member member) {
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
