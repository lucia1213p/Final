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

/**
 * Servlet implementation class ShareTechInfoServlet
 */
@WebServlet(name = "ShareTechInfo", urlPatterns = { "/shareTechInfo.do" })
public class ShareTechInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareTechInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int shareTechNo = Integer.parseInt(request.getParameter("shrTechNo"));
		String boardCode = request.getParameter("boardCode");
		
		ShrTech shr=new BoardService().shareTechInfo(shareTechNo,boardCode);
		ArrayList<ShrTechAnswer> answerList = new BoardService().srtAnswerList(shareTechNo);
		
		//채택된 답변이 있는지 확인하기 위한 
		int checkAdopt=0;
		for(ShrTechAnswer shrAnswer:answerList) {
			if(shrAnswer.getAnswAddopt().equals("Y")) {
				checkAdopt=1;
			}
		}
		
		if(checkAdopt>0){
			new BoardService().adoptY(shareTechNo);
		}else if(answerList!=null) { 
			new BoardService().adoptWait(shareTechNo);
		}else if(answerList==null) {
			new BoardService().adoptN(shareTechNo);
		}
		
		if(shr!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/board/shareTechInfo.jsp");
			request.setAttribute("shrTech", shr);
			request.setAttribute("answerList", answerList);
			view.forward(request, response);
		}else {
			//선택한 게시글을 찾을 수 없으면
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
