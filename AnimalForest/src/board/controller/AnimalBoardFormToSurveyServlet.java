package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import board.model.vo.AdoptionForm;
import board.model.vo.AdoptionSurvey;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardFormToSurveyServlet
 */
@WebServlet("/AnimalBoardFormToSurveyServlet")
public class AnimalBoardFormToSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardFormToSurveyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int memberNum = ((Member) session.getAttribute("member")).getMemberNum();
		AdoptionForm adoptionForm = new AdoptionForm();
		adoptionForm.setMemberNum(memberNum);
		adoptionForm.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		adoptionForm.setContent(request.getParameter("content"));
		adoptionForm.setJob(request.getParameter("job"));
		adoptionForm.setPhone(request.getParameter("phone"));
		adoptionForm.setEmail(request.getParameter("email"));
		adoptionForm.setAddress(request.getParameter("address"));
		
		request.setAttribute("boardNo", boardNo);
		int result = new AnimalBoardService().insertAdoptionForm(adoptionForm);
		
		
//		int result = new AnimalBoardService().insertAdoptionSurvey(adoptionSurvey);
		
		
		
		request.getRequestDispatcher("/views/animalBoard/animalAdoptionSurvey.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
