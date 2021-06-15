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
import board.model.vo.AnimalBoardGrp;
import board.model.vo.AnimalBoardGrp2;
import donation.model.service.DonationBoardService;

/**
 * Servlet implementation class AnimalBoardWirtePageServlet
 */
@WebServlet("/AnimalBoardWirtePageServlet")
public class AnimalBoardWirtePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardWirtePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<AnimalBoardGrp> list = new ArrayList<AnimalBoardGrp>();
		ArrayList<AnimalBoardGrp2> list2 = new ArrayList<AnimalBoardGrp2>();
	
		list = new AnimalBoardService().animalList();
		list2= new AnimalBoardService().animalList2();
		
		if(list!=null&&list2!=null) {
			
			RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoardWriteForm.jsp");
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			view.forward(request, response);
		}
//		/views/animalBoard/animalBoardWriteForm.html
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
