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

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.board.model.vo.SupportTech;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class SupportTechListServlet
 */
@WebServlet(name = "SupportTechList", urlPatterns = { "/supportTechList.do" })
public class SupportTechListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportTechListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		
		if(member!=null) {
			ArrayList<SupportTech> sptList=new BoardService().supportTechList(member.getMemCode());
			RequestDispatcher view = request.getRequestDispatcher("views/board/supportTech.jsp");
			request.setAttribute("sptList", sptList);
			view.forward(request, response);
		}else { // 회원이 아닐 경우
			response.sendRedirect("views/error/authorityError.jsp");
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
