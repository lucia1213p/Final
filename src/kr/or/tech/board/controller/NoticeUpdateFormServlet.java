package kr.or.tech.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateFormServlet
 */
@WebServlet(name = "NoticeUpdateForm", urlPatterns = { "/noticeUpdateForm.do" })
public class NoticeUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String boardCode=request.getParameter("boardCode");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println("글번호:"+noticeNo);
		System.out.println("게시판코드:"+boardCode);
		Notice notice= new BoardService().noticeUpdateForm(noticeNo, boardCode);
		
		if(notice!=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/board/noticeUpdate.jsp");
			request.setAttribute("notice", notice);
			view.forward(request, response);
		}else {
			response.sendRedirect("views/error/errorPage");
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
