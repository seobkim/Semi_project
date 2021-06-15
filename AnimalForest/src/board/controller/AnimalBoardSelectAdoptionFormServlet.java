package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.AnimalBoardService;

/**
 * Servlet implementation class AnimalBoardSelectAdoptionFormServlet
 */
@WebServlet("/selectForm")
public class AnimalBoardSelectAdoptionFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardSelectAdoptionFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int memberNum = Integer.parseInt(request.getParameter("memberNum"));
		int result = new AnimalBoardService().selectAdoptionForm(memberNum, boardNo);
		
		if(result>0)
		{
            RequestDispatcher rd = request.getRequestDispatcher("/views/animalBoard/msg.jsp");
            request.setAttribute("msg", "채택완료.");
            request.setAttribute("loc", "/boardList");
            rd.forward(request, response);
		}
		else {
			
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
