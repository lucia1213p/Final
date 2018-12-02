package kr.or.tech.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.tech.member.model.service.MemberService;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class MemberInfoServlet
 */
@WebServlet(name = "MemberInfo", urlPatterns = { "/memberInfo.do" })
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoServlet() {
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
			Member member=new MemberService().memberInfo(m.getMemberId());
			
			if(member!=null) {
				RequestDispatcher view=request.getRequestDispatcher("views/member/memberInfo.jsp");
				request.setAttribute("member", member);
				view.forward(request,response);
			}else {
				response.sendRedirect("/views/error/errorPage.jsp");
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
