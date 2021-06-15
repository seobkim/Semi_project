package donation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import donation.model.dao.DonationBoardDao;
import donation.model.service.DonationBoardService;
import donation.model.vo.DonationBoard;
import donation.model.vo.animalGrp;
import donation.model.vo.animalGrp2;

/**
 * Servlet implementation class DonationBoardModifyServlet
 */
@WebServlet("/donationModify")
public class DonationBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationBoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<animalGrp> list = new ArrayList<animalGrp>();
		ArrayList<animalGrp2> list2 = new ArrayList<animalGrp2>();
		
		int boardNo=Integer.parseInt(request.getParameter("board_No"));
		
		DonationBoard donationBoard= new DonationBoardService().donationSelect(boardNo);
		list = new DonationBoardService().animalList();
		list2= new DonationBoardService().animalList2();
		
		if(list!=null&&list2!=null) {
			
			RequestDispatcher view = request.getRequestDispatcher("/views/donation/donationModify.jsp");
			request.setAttribute("board", donationBoard);
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
