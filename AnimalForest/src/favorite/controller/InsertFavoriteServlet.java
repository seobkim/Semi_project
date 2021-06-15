package favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import favorite.model.service.FavoriteService;
import favorite.model.vo.Favorite;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertFavoriteServlet
 */
@WebServlet("/insertFavorite")
public class InsertFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNum = Integer.parseInt(request.getParameter("boardNo"));
		int currentPage = 0;
		HttpSession session = request.getSession();
		int memberNum = ((Member)session.getAttribute("member")).getMemberNum();
		

		if(request.getParameter("currentPage") == null || request.getParameter("currentPage") == "") {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		Favorite favorite = new Favorite();
		favorite.setBoardNo(boardNum);
		favorite.setMemberNo(memberNum);
		
		ArrayList<Integer> list = new FavoriteService().insertFavorite(favorite);
		
		if(list.get(0) > 0) {
			PrintWriter out= response.getWriter();
			
			JSONObject jResult = new JSONObject();
		      
			jResult.put("src","/img/like.png");
			jResult.put("cnt", list.get(1));
		    response.setContentType("application/json"); //핵중요
		     
		    out.print(jResult);
		      
		    out.flush();// 버퍼링되어서 아직 기록되지않은 데이터를 출력스트림에 모두 출력
		    out.close();// 출력되지않은데이터가 있으면 먼저 출력하고 스트림 종료
			
		}else {
			response.sendRedirect("/favoriteList?currentPage="+currentPage);
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
