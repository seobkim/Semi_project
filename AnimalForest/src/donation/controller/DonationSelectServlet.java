package donation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donation.model.service.DonationBoardService;

import donation.model.vo.DonationBoard;
import donation.model.vo.DonationHistory;
import member.model.vo.Member;

/**
 * Servlet implementation class DonationSelectServlet
 */
@WebServlet("/donationSelect")
public class DonationSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("board_No"));
		HttpSession session=request.getSession();
		Member mem =(Member)session.getAttribute("member");
		DonationBoard board = new DonationBoard();
		int type = Integer.parseInt(request.getParameter("type"));
		
		board = new DonationBoardService().donationSelect(boardNo);
		DonationHistory Dhistory = new DonationBoardService().selectHistory(boardNo);
		if(board!=null && Dhistory!=null) {
			
			RequestDispatcher view =request.getRequestDispatcher("/views/donation/donation_detail.jsp");
			
			float num=(((float)Dhistory.getFull_Amount()/(float)board.getFull_Amount())*100);
			request.setAttribute("num", num);
			request.setAttribute("board", board);
			request.setAttribute("result", Dhistory);
			request.setAttribute("mem", mem);
			request.setAttribute("type", type);
			view.forward(request, response);
		}
		else {
			response.sendRedirect("/views/donation/error.html");
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
