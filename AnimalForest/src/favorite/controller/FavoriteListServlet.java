package favorite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import everyboard.model.service.BoardService;
import everyboard.model.vo.BoardPageData;
import favorite.model.service.FavoriteService;
import favorite.model.vo.FavoritePageData;
import member.model.vo.Member;

/**
 * Servlet implementation class FavoriteListServlet
 */
@WebServlet("/favoriteList")
public class FavoriteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNum = ((Member)session.getAttribute("member")).getMemberNum();
		int currentPage = 0;

		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		FavoritePageData pd = new FavoriteService().myFavoriteList(memberNum, currentPage); 
			
		request.setAttribute("pd", pd);
		request.getRequestDispatcher("/views/member/mypage/favoriteList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
