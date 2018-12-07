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
import kr.or.tech.board.model.vo.MainpageBoard;
import kr.or.tech.board.model.vo.SupportTech;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class MainPageBoardServlet
 */
@WebServlet(name = "MainPageBoard", urlPatterns = { "/mainPageBoard.do" })
public class MainPageBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPageBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		MainpageBoard mpb=null;
		ArrayList<SupportTech> sptList=null;
		if(member!=null) {
			
			mpb=new BoardService().mainOneSelectNotice(member.getMemberNo());
			mpb.setRecentShare(new BoardService().mainRecentShare());
			System.out.println("회원 코드:"+member.getMemCode());
			if(member.getMemCode().equals("ABC Manufacturer")) { //제조사일경우
				sptList= new BoardService().mainRecentSupportM(member.getMemCode());
				}
			}else { 
				sptList= new BoardService().mainRecentSupportP(member.getMemCode());
			}
			mpb.setRecentSupport(sptList);
			
			if(mpb!=null) {
				RequestDispatcher view = request.getRequestDispatcher("/main.jsp");
				request.setAttribute("mainPageBoard", mpb);
				view.forward(request, response);
			}else {
				//데이터 정보가 아무것도 없을 경우
				response.sendRedirect("/noticeList.do");
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
