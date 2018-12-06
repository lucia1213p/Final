package kr.or.tech.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.board.model.vo.ShrTechAnswer;
import kr.or.tech.board.model.vo.SptCategory;
import kr.or.tech.board.model.vo.SupportTech;
import kr.or.tech.board.model.vo.TComment;

/**
 * Servlet implementation class SupportTechInfoServlet
 */
@WebServlet(name = "SupportTechInfo", urlPatterns = { "/supportTechInfo.do" })
public class SupportTechInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportTechInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int sptTechNo = Integer.parseInt(request.getParameter("sptTechNo"));
		String boardCode = request.getParameter("boardCode");
		
		System.out.println("글번호:"+sptTechNo);
		SupportTech spt=new BoardService().supportTechInfo(sptTechNo,boardCode);
		ArrayList<TComment> answerList = new BoardService().sptAnswerList(sptTechNo,boardCode);
		ArrayList<SptCategory> categoryList=new BoardService().sptCategoryList();
		
		if(spt!=null) {
				RequestDispatcher view = request.getRequestDispatcher("views/board/supportTechInfo.jsp");
				request.setAttribute("support", spt);
				request.setAttribute("answerList",answerList);
				request.setAttribute("categoryList",categoryList);
				view.forward(request, response);
		}else {
			System.out.println("게시글 찾을 수 없음");
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
