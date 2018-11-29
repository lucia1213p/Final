package kr.or.tech.board.controller;

import java.io.IOException;
import java.security.spec.MGF1ParameterSpec;

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
import kr.or.tech.member.model.vo.Member;

/**
 * Servlet implementation class ShareTechWriteServlet
 */
@WebServlet(name = "ShareTechWrite", urlPatterns = { "/shareTechWrite.do" })
public class ShareTechWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareTechWriteServlet() {
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
			//파일 업로드
			int fileSizeLimit = 5 * 1024 * 1024;
			String uploadPath=getServletContext().getRealPath("/")+"file"+"\\"+"shareTech";
			String encType = "UTF-8";
			
			MultipartRequest multi = new MultipartRequest(request,
			uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
		
			request.setCharacterEncoding("utf-8");
			
			ShrTech shr=new ShrTech();
			
			shr.setMemberNo(m.getMemberNo());
			
			shr.setShareTitle(multi.getParameter("shareTechTitle"));
			shr.setShareCont(multi.getParameter("shareTechContent"));
			shr.setFileName(multi.getFilesystemName("fileName"));
			
			new BoardService().writeShareTech(shr);
			
			response.sendRedirect("/shareTechList.do");
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
