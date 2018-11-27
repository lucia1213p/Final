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
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class NoticeInfoServlet
 */
@WebServlet(name = "NoticeInfo", urlPatterns = { "/noticeInfo.do" })
public class NoticeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInfoServlet() {
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
			request.setCharacterEncoding("utf-8");
			int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
			String boardCode = request.getParameter("boardCode");
			
			Notice notice=new BoardService().noticeInfo(noticeNo,boardCode);
			
			ArrayList<NComment> list = new BoardService().noticeCommentInfo(noticeNo,boardCode);
			
			if(notice!=null) {
				RequestDispatcher view = request.getRequestDispatcher("views/board/noticeInfo.jsp");
				request.setAttribute("notice", notice);
				request.setAttribute("nComment", list);
				view.forward(request, response);
			}else {
				//선택한 게시글을 찾을 수 없으면
				System.out.println("게시글 찾을 수 없음");
				response.sendRedirect("views/error/errorPage.jsp");
			}
		}else { 
			//로그인 안한 상태로 접근시 => 에러메시지와 함께 로그인 페이지로 넘어갈 것
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
