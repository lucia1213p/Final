package kr.or.tech.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.board.model.vo.ShrTechAnswer;
import kr.or.tech.board.model.vo.SptTechAnswer;

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
		
		ShrTech shr=new BoardService().supportTechInfo(sptTechNo,boardCode);
		ArrayList<SptTechAnswer> answerList = new BoardService().sptAnswerList(sptTechNo);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
