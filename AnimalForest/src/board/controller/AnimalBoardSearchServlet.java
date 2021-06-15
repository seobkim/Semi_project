package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.AnimalBoardService;
import board.model.vo.AnimalBoard;
import board.model.vo.BoardAndMember;
import board.model.vo.PageData;

/**
 * Servlet implementation class AnimalBoardSearchServlet
 */
@WebServlet("/boardSearch")
public class AnimalBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int currentPage = 0;
		String search = request.getParameter("search");
		if(request.getParameter("currentPage") == null) {
			currentPage=1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new AnimalBoardService().memberListAllSearch(currentPage,search);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoardSearch.jsp");
		request.setAttribute("pageData", pageData);
		view.forward(request, response);
		
		
//		String search = request.getParameter("search");
//		ArrayList<BoardAndMember> memberList = new AnimalBoardService().memberListAllSearch(search);
//		request.setAttribute("memberList", memberList);
//		RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoardSearch.jsp");
//		view.forward(request, response);
		
		//위에가 원본
//		ArrayList<AnimalBoard> animalList = new AnimalBoardService().animalBoardSearchList(search); 맨처음 서치
//		request.setAttribute("animalList", animalList); // 맨처음
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
