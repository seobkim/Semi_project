package donation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;



import donation.model.service.DonationBoardService;

/**
 * Servlet implementation class DonationFlagModi
 */
@WebServlet("/flagModi")
public class DonationFlagModi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationFlagModi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		char flag = request.getParameter("flag").charAt(0);
		int boardNo= Integer.parseInt(request.getParameter("boardNo"));
		
		int result = new DonationBoardService().flagModi(flag,boardNo);
		if(result > 0) {
			PrintWriter out= response.getWriter();
			
			JSONObject jResult = new JSONObject();
		    if(flag=='0') {
			jResult.put("val",1);
			String res = "공개";
			URLEncoder.encode(res,"UTF-8");
			
			jResult.put("text",URLEncoder.encode(res,"utf-8"));
			
		    }
		    else if(flag=='1') {
		    	jResult.put("val",0);
		    	String res = "비공개";
				
		    	jResult.put("text",URLEncoder.encode(res,"utf-8"));
		    }
			response.setContentType("application/json"); 
		     
		    out.print(jResult);
		      
		    out.flush();// 버퍼링되어서 아직 기록되지않은 데이터를 출력스트림에 모두 출력
		    out.close();// 출력되지않은데이터가 있으면 먼저 출력하고 스트림 종료
		}else {
			response.sendRedirect("/view/donation/error.html");
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
