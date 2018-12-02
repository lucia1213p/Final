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
import kr.or.tech.board.model.vo.NPageData;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet(name = "NoticeList", urlPatterns = { "/noticeList.do" })
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
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
			//페이징 처리
			int NoticeCurrentPage;
			if(request.getParameter("noticeCurrentPage")==null) { //메인페이지에서 공지사항 페이지를 눌렀을 경우
				NoticeCurrentPage=1;
			}else {
				NoticeCurrentPage=Integer.parseInt(request.getParameter("noticeCurrentPage"));
			}
			//비즈니스 로직 
			NPageData npd=new BoardService().noticeList(NoticeCurrentPage);
			
			RequestDispatcher view = request.getRequestDispatcher("views/board/notice.jsp");
			request.setAttribute("nPageData", npd);
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
