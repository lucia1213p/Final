package kr.or.tech.member.model.service;

import java.sql.Connection;

import kr.or.tech.common.JDBCTemplate;
import kr.or.tech.member.model.dao.MemberDao;
import kr.or.tech.member.model.vo.Member;

public class MemberService {

	public Member login(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member member=new MemberDao().login(conn,userId,userPwd);
		
		return member;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result= new MemberDao().insertMember(m,conn);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Member memberInfo(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member member = new MemberDao().memberInfo(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return member;
		
	}

	public String idCheck(String userId) {
		Connection conn=JDBCTemplate.getConnection();
		
		String memberId=new MemberDao().idCheck(conn,userId);
		JDBCTemplate.close(conn);
		
		return memberId;
	}

}
