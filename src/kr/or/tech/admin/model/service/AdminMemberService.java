package kr.or.tech.admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.tech.admin.model.dao.AdminMemberDao;
import kr.or.tech.common.JDBCTemplate;
import kr.or.tech.member.model.vo.Member;

public class AdminMemberService {

	public ArrayList<Member> waitMember() {
		Connection conn=JDBCTemplate.getConnection();
		
		ArrayList<Member> list= new AdminMemberDao().waitMemberList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Member> allMemberList() {
		Connection conn=JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new AdminMemberDao().allMemberList(conn);
		
		JDBCTemplate.close(conn);
		
 		return list;
	}

	public int approveMember(int memberNo) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new AdminMemberDao().approveMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int refuseMember(int memberNo) {
		Connection conn=JDBCTemplate.getConnection();
		int result = new AdminMemberDao().refuseMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
