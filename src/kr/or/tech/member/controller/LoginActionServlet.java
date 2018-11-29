package kr.or.tech.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.tech.member.model.service.MemberService;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class LoginActionServlet
 */
@WebServlet(name = "LoginAction", urlPatterns = { "/loginAction.do" })
public class LoginActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId=request.getParameter("id");
		String userPwd=request.getParameter("password");
		
		Member member = new MemberService().login(userId,userPwd);
		
		if(member!=null) {
			
			// true : 세션값이 없으면 새롭게 생성
		    // false : 세션값이 없으면 null 리턴
			HttpSession session  = request.getSession(true);
			
			session.setAttribute("member", member);
			
			response.sendRedirect("/views/member/loginSuccess.jsp");
		}else {
			response.sendRedirect("/views/member/loginFailed.jsp");
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
