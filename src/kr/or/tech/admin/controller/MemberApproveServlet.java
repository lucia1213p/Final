package kr.or.tech.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.tech.admin.model.service.AdminMemberService;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class MemberApproveServlet
 */
@WebServlet(name = "MemberApprove", urlPatterns = { "/memberApprove.do" })
public class MemberApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("member");
		
		if(m!=null) {
			if((m.getMemberGrade().equals("HA") || m.getMemberGrade().equals("MA"))) {
				
				request.setCharacterEncoding("utf-8");
				//승인할 회원 번호 
				int memberNo = Integer.parseInt(request.getParameter("memNo"));
				
				int result = new AdminMemberService().approveMember(memberNo);
				
				if(result>0) {
					response.sendRedirect("/memberAllList.do");
				}else {
					//승인에 실패했다는 페이지
					response.sendRedirect("views/error/index.jsp");
				}
			}else {
				//관리자가 아닌 회원이 접근시 접근권한이 없다는 페이지 띄우기
				response.sendRedirect("views/error/errorPage.jsp");
			}
		}else {
			response.sendRedirect("views/error/errorPage.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
