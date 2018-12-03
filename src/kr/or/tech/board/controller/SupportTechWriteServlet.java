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
import kr.or.tech.board.model.vo.ShrTech;
import kr.or.tech.board.model.vo.SupportTech;
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class SupportTechWriteServlet
 */
@WebServlet(name = "SupportTechWrite", urlPatterns = { "/supportTechWrite.do" })
public class SupportTechWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportTechWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member m =  (Member)session.getAttribute("member");
		
		if(m!=null&&m.getMemberGrade().equals("PE")) {
			
			//파일 업로드
			int fileSizeLimit = 5 * 1024 * 1024;
			String uploadPath=getServletContext().getRealPath("/")+"file"+"\\"+"supportTech";
			String encType = "UTF-8";
			
			MultipartRequest multi = new MultipartRequest(request,
			uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
		
			request.setCharacterEncoding("utf-8");
			
			SupportTech spt=new SupportTech();
			
			spt.setPartnerNo(m.getMemberNo());
			spt.setTitle(multi.getParameter("shareTechTitle"));
			spt.setContents(multi.getParameter("shareTechContent"));
			spt.setFileName(multi.getFilesystemName("fileName"));
			
			int result=new BoardService().writeSupportTech(spt);
			
			if(result>0) {
				response.sendRedirect("/supportTechList.do");
			}else { //insertError;
				response.sendRedirect("/views/error/errorPage.jsp");
			}
		}else {
			response.sendRedirect("/views/error/authorityError.jsp");
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
