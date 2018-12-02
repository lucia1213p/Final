package kr.or.tech.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.tech.admin.model.service.AdminMemberService;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class AdMemberUpdateServlet
 */
@WebServlet(name = "AdMemberUpdate", urlPatterns = { "/adMemberUpdate.do" })
public class AdMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMemberUpdateServlet() {
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
			if(m.getMemberGrade().equals("HA")) {
				request.setCharacterEncoding("utf-8");
				int memberNo = Integer.parseInt(request.getParameter("member_no"));
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				
				Member member = new Member ();
				member.setMemberNo(memberNo);
				member.setMemberPhone(phone);
				member.setMemberEmail(email);
				member.setMemberAddr(address);
				
				int result = new AdminMemberService().adUpdateMember(member);
				
				if(result>0) {
					response.sendRedirect("/memberInfo.do");
				}else {
					response.sendRedirect("/views/error/errorPage.jsp");
				}
			}else {
					//관리자가 아닌 회원이 접근시 접근권한이 없다는 페이지 띄우기
					response.sendRedirect("views/error/errorPage.jsp");
			}
		}else {
			response.sendRedirect("/index.jsp");
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
