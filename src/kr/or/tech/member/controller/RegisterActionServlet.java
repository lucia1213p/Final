package kr.or.tech.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.tech.member.model.service.MemberService;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class RegisterActionServlet
 */
@WebServlet(name = "RegisterAction", urlPatterns = { "/registerAction.do" })
public class RegisterActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member m = new Member();
		m.setMemberId(request.getParameter("id"));
		m.setMemberName(request.getParameter("name"));
		m.setMemberPwd(request.getParameter("password"));
		m.setMemberPhone(request.getParameter("phone"));
		m.setMemberAddr(request.getParameter("addr"));
		m.setMemberEmail(request.getParameter("email"));
		m.setMemberGrade(request.getParameter("grade"));
		m.setMemCode(request.getParameter("belong"));
		
		int result= new MemberService().insertMember(m);
		
		if(result>0) {
			response.sendRedirect("/index.jsp");
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
