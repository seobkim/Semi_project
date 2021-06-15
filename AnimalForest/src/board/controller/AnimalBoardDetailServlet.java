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
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardDetailServlet
 */
@WebServlet("/animalBoardDetail")
public class AnimalBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalBoardDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AnimalBoard animalBoard = null;
		Member member = null;
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		animalBoard = new AnimalBoardService().AnimalBoardSelectOne(boardNo);
		int memberNum = animalBoard.getMemberNum();
		member = new AnimalBoardService().memeberSelectOne(memberNum);
		HttpSession session = request.getSession();
		int memberNum2 = ((Member) session.getAttribute("member")).getMemberNum();
		char typeNum = ((Member) session.getAttribute("member")).getMemberType();

		int selectNum = animalBoard.getSelectMemberNum();
		if(selectNum ==0 ||memberNum2==memberNum || typeNum =='0') {
			RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoardDetail.jsp");
			request.setAttribute("member", member);
			request.setAttribute("animalBoard", animalBoard);
			view.forward(request, response);		
		}
		else {
			 RequestDispatcher rd = request.getRequestDispatcher("/views/animalBoard/msg.jsp");
	            request.setAttribute("msg", "마감된 공고입니다.");
	            request.setAttribute("loc", "/boardList");
	            rd.forward(request, response);
		}
		


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
