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
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class ShareTechListServlet
 */
@WebServlet(name = "ShareTechList", urlPatterns = { "/shareTechList.do" })
public class ShareTechListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareTechListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		
		if(member!=null) {
			ArrayList<ShrTech> shrList=new BoardService().shareTechList();
			RequestDispatcher view = request.getRequestDispatcher("views/board/shareTech.jsp");
			request.setAttribute("shrList", shrList);
			view.forward(request, response);
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
