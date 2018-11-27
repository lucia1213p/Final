package kr.or.tech.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class NFileDownServlet
 */
@WebServlet(name = "NFileDown", urlPatterns = { "/nFileDown.do" })
public class NFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 한글 인코딩처리
		request.getParameter("utf-8");
		
		//2. 파일 이름 저장
		String fileName=request.getParameter("fileName");
		System.out.println(fileName);
		//1. 해당 파일을 열람
		File file = new File(getServletContext().getRealPath("/")+"file"+"\\"+"notice"+"\\"+fileName); //java.io.File 임포트 
		
		//2. 파일이름을 운영체제(windows)에 맞게 인코딩해주어야함 <iso-8859-1/뭐 그런걸 사용함>
		String encFileName = new String(fileName.getBytes(),"ISO-8859-1");
		
		//3. 파일 전송을 위한 response 헤더 변경 
		response.setContentType("application/octet-stream");
		response.setContentLengthLong(file.length());
		response.setHeader("Content-Disposition", "attachment;filename="+encFileName);
		
		//4. 파일의 내용을 읽어와야 전송할 수 있으므로 inputStream 생성
		FileInputStream fileIn = new FileInputStream(file);
		
		//5. input스트림으로 연결된 정보를 클라이언트에게 보내기위한
		// outputStream을 생성함
		ServletOutputStream out = response.getOutputStream();
		
		byte[] output = new byte[4096]; //크기 1바이트당 디스크할당 크기는 4096바이트
		
		while(fileIn.read(output,0,4096) !=-1) //내용이있으면 1이 넘어감 없으면 -1이 리턴
		{
			out.write(output,0,4096);
		}
		fileIn.close();
		out.close();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
