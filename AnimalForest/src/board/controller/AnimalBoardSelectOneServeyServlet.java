package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.AnimalBoardService;
import board.model.vo.AdoptionForm;
import board.model.vo.AdoptionSurvey;

/**
 * Servlet implementation class AnimalBoardSelectOneServeyServlet
 */
@WebServlet("/AnimalBoardSelectOneServeyServlet")
public class AnimalBoardSelectOneServeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardSelectOneServeyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int memberNum = Integer.parseInt(request.getParameter("memberNum"));
		
		AdoptionSurvey adoptionSurvey = new AnimalBoardService().selectOneSurvey(boardNo,memberNum);
		request.setAttribute("adoptionSurvey", adoptionSurvey);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/selectOneSurvey.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
