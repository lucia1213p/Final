package kr.or.tech.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.tech.board.model.dao.BoardDao;
import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.NPageData;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet(name = "NoticeSearch", urlPatterns = { "/noticeSearch.do" })
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("search");
		String selectSearch=request.getParameter("selectSearch");
		
		System.out.println(keyword);
		System.out.println(selectSearch);
		if(selectSearch.equals("writer")) {
			selectSearch="member_name";
		}else if(selectSearch.equals("title")){
			selectSearch="n_title";
		}
		
		//페이징처리
		int searchCurrentPage;
		if(request.getParameter("searchCurrentPage")==null) {
			searchCurrentPage=1;
		}else {
			searchCurrentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//비즈니스 로직
		NPageData npd = new BoardService().noticeSearchList(keyword,selectSearch,searchCurrentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("views/board/noticeSearchList.jsp");
		request.setAttribute("nPageData", npd);
		request.setAttribute("keyword", keyword);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
