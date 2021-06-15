package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import board.model.vo.AdoptionSurvey;
import board.model.vo.AnimalBoard;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardSurveyToForm
 */
@WebServlet("/animalBoardSurvey")
public class AnimalBoardSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalBoardSurveyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int memberNum = ((Member) session.getAttribute("member")).getMemberNum();
		AdoptionSurvey adoptionSurvey = new AdoptionSurvey();
		adoptionSurvey.setExprienceYn(request.getParameter("exprienceYn"));
		adoptionSurvey.setPetYn(request.getParameter("petYn"));
		adoptionSurvey.setMemberFamily(Integer.parseInt(request.getParameter("memberFamily")));
		adoptionSurvey.setHomeType(request.getParameter("homeType"));
		adoptionSurvey.setAdoptionCare(request.getParameter("adoptionCare"));
		adoptionSurvey.setExpectedCost(Integer.parseInt(request.getParameter("expectedCost")));
		adoptionSurvey.setAdoptionReason(request.getParameter("adoptionReason"));
		adoptionSurvey.setMemberNum(memberNum);
		adoptionSurvey.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		
		int result = new AnimalBoardService().insertAdoptionSurvey(adoptionSurvey);
		
		
//		int result = new AnimalBoardService().insertAdoptionSurvey(adoptionSurvey);
		request.getRequestDispatcher("/boardList").forward(request, response);
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
