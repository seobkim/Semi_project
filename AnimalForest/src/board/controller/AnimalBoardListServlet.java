package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import board.model.vo.AnimalBoard;
import board.model.vo.BoardAndMember;
import board.model.vo.PageData;
import member.model.vo.Member;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/boardList")
public class AnimalBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalBoardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	int currentPage = 0;
//		// href="/noticecurrentPage=1"
//		if(request.getParameter("currentPage") == null) {
//			currentPage=1;
//		} else {
//			currentPage = Integer.parseInt(request.getParameter("currentPage"));
//		}
//		PageData pageData = new AnimalBoardService().selectAnimalBoardList(currentPage);
		
//		request.setAttribute("pageData", pageData);
//		request.getRequestDispatcher("/WEB-INF/views/photo/photoList.jsp").forward(request, response);
    	
    	
    	
    	int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage=1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		char typeNum = member.getMemberType();
		int myMemberNum = member.getMemberNum();
		PageData pageData = new AnimalBoardService().memberListAll(currentPage,typeNum, myMemberNum);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoard.jsp");


		request.setAttribute("pageData", pageData);
		view.forward(request, response);
		
		
    	// 원본!!
//		ArrayList<AnimalBoard> animalList = new AnimalBoardService().AnimalBoardListAll();
//		ArrayList<BoardAndMember> memberList = new AnimalBoardService().memberListAll();
//		request.setAttribute("animalList", animalList);
//		request.setAttribute("memberList", memberList);
//		RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoard.jsp");
//		view.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
