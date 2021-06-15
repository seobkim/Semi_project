package donation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import donation.model.service.DonationBoardService;
import donation.model.vo.DonationBoard;
import donation.model.vo.animalGrp2;
import donation.model.vo.animalGrp;

/**
 * Servlet implementation class DonationWriteServlet
 */
@WebServlet("/donationWritePage")
public class DonationWritepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationWritepageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<animalGrp> list = new ArrayList<animalGrp>();
		ArrayList<animalGrp2> list2 = new ArrayList<animalGrp2>();
	
		list = new DonationBoardService().animalList();
		list2= new DonationBoardService().animalList2();
		
		if(list!=null&&list2!=null) {
			
			RequestDispatcher view = request.getRequestDispatcher("/views/donation/donationWrite.jsp");
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			view.forward(request, response);
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
