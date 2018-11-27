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
 * Servlet implementation class MemberAllListServlet
 */
@WebServlet(name = "MemberAllList", urlPatterns = { "/memberAllList.do" })
public class MemberAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAllListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member admin = (Member)session.getAttribute("member");
		
		if(admin!=null) {
			if(admin.getMemberGrade().equals("HA")) {
				ArrayList<Member> list = new AdminMemberService().allMemberList();
				RequestDispatcher view =request.getRequestDispatcher("views/admin/memberAllList.jsp");
				request.setAttribute("allMemberList", list);
				view.forward(request, response);
			}else {
				//관리자가 아닌 회원이 접근시 접근권한이 없다는 페이지 띄우기
				response.sendRedirect("views/error/errorPage.jsp");
			}
		}else {
			response.sendRedirect("views/error/index.jsp");
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
