package kr.or.tech.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.tech.board.model.service.BoardService;
import kr.or.tech.board.model.vo.Notice;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet(name = "NoticeWrite", urlPatterns = { "/noticeWrite.do" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member m =  (Member)session.getAttribute("member");
		
		
		
		if(m!=null) {
			
			//최대 업로드 파일 사이즈
			int fileSizeLimit = 5 * 1024 * 1024;
			
			//업로드 될 경로
			String uploadPath=getServletContext().getRealPath("/")+"file"+"\\"+"notice";
			//인코딩 타입(파일 인코딩 타입)
			String encType = "UTF-8";
			//MultipartRequest 객체를 생성
			MultipartRequest multi = new MultipartRequest(request,
					uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
			
			System.out.println(uploadPath);
			//업로드
			request.setCharacterEncoding("utf-8");
			
			Notice n =new Notice();
			n.setNoticeGrade(multi.getParameter("category"));
			n.setNoticeTitle(multi.getParameter("noticeTitle"));
			n.setNoticeContent(multi.getParameter("noticeContent"));
			n.setNoticeFile(multi.getFilesystemName("fileName"));
			
			int result = new BoardService().writeNotice(m.getMemberNo(),n);
			
			if(result>0) {
				response.sendRedirect("/noticeList.do");
			}else {
				//승인에 실패했다는 페이지
				response.sendRedirect("views/error/errorPage.jsp");
			}
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
