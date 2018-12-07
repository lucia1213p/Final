package kr.or.tech.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.NComment;
import kr.or.tech.board.model.vo.TComment;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class SptCommentWriteServlet
 */
@WebServlet(name = "SptCommentWrite", urlPatterns = { "/sptCommentWrite.do" })
public class SptCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SptCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("member");
		
		if(m!=null) { //홈페이지에 가입되어있는 회원일 경우
			request.setCharacterEncoding("utf-8");
			
			TComment tc=new TComment();
			tc.setCategory(request.getParameter("category"));
			tc.setBoardCode(request.getParameter("boardCode"));
			tc.setBoardNo(Integer.parseInt(request.getParameter("sptTechNo")));
			tc.setMemberNo(m.getMemberNo());
			tc.setCommCont(request.getParameter("comment"));
			int result = new BoardService().insertTComment(tc);
			
			if(result>0) { //댓글작성이 됐을경우 해당 댓글을 작성한 게시글로 이동
				response.sendRedirect("/supportTechInfo.do?sptTechNo="+tc.getBoardNo()+"&boardCode="+tc.getBoardCode());
			}else { //댓글업데이트안됨
				response.sendRedirect("views/error/errorPage.jsp");
			}
		}else { //비정상적인 방법으로 접근시
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
