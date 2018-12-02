package kr.or.tech.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import kr.or.tech.member.model.service.MemberService;
import kr.or.tech.member.model.vo.*;

/**
 * Servlet implementation class BelongLoadServlet
 */
@WebServlet(name = "BelongLoad", urlPatterns = { "/belongLoad.do" })
public class BelongLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BelongLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("utf-8");
		String selectGrade=request.getParameter("params");
		String BelongCode=null;
		if(selectGrade.equals("HA")||selectGrade.equals("MA")||selectGrade.equals("ME")) {
			BelongCode="Manufacturer";
		}else {
			BelongCode="Partner";
		}
		ArrayList<BelongSelect> belongList=new MemberService().loadBelong(selectGrade,BelongCode);
		HashMap<String,Object> listData=new HashMap<String,Object>();
		listData.put("belongList",belongList);
		JSONObject jso=new JSONObject();
		jso.put("result", listData);
		
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jso);
			out.close();
		} catch (Exception e) {
			 e.printStackTrace();
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
