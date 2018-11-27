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

}
