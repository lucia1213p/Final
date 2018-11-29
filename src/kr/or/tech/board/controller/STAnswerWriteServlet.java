package kr.or.tech.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.ShrTechAnswer;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class STAnswerWriteServlet
 */
@WebServlet(name = "STAnswerWrite", urlPatterns = { "/sTAnswerWrite.do" })
public class STAnswerWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public STAnswerWriteServlet() {
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
			request.setCharacterEncoding("utf-8");
			ShrTechAnswer sta=new ShrTechAnswer();
			sta.setMemberNo(member.getMemberNo());
			sta.setShrNo(Integer.parseInt(request.getParameter("noticeNo")));
			sta.setAnswCont(request.getParameter("answer"));
			sta.setBoardCode(request.getParameter("boardCode"));
			int result = new BoardService().writeAnswer(sta);
			
			if(result>0) {
				response.sendRedirect("/shareTechInfo.do?shrTechNo="+sta.getShrNo()+"&boardCode="+sta.getBoardCode());
			}else {
				response.sendRedirect("/views/error/errorPage.jsp");
			}
		}else {
			//권한이 없음 => 로그인 페이지로 넘어간다
			response.sendRedirect("/index.jsp");
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
