package kr.or.tech.board.controller;

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
import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class ShareAnswerAddoptServlet
 */
@WebServlet(name = "ShareAnswerAdopt", urlPatterns = { "/shareAnswerAdopt.do" })
public class ShareAnswerAdoptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareAnswerAdoptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		request.setCharacterEncoding("utf-8");
		int answNum=Integer.parseInt(request.getParameter("answNum"));
		int shrNo=Integer.parseInt(request.getParameter("shrNo"));
		int memNo=Integer.parseInt(request.getParameter("memNo"));
		
		if(member!=null) {
			if(member.getMemberNo()==memNo||member.getMemberGrade().equals("HA")) {
				int result = new BoardService().adoptAnswer(answNum,shrNo);
				response.getWriter().print(result);
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
