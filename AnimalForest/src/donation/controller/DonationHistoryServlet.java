package donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donation.model.service.DonationBoardService;
import donation.model.vo.DonationHistory;
import member.model.vo.Member;

/**
 * Servlet implementation class DonationHistoryServlet
 */
@WebServlet("/donationHistory")
public class DonationHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Member member= (Member)session.getAttribute("member");
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int amount =Integer.parseInt(request.getParameter("amount"));
		
		
		DonationHistory Dhistory= new DonationHistory();
		Dhistory.setBoard_No(boardNo);
		Dhistory.setMember_Num(member.getMemberNum());
		Dhistory.setDonation_Amount(amount);
		int result = 0;
		
		result = new DonationBoardService().insertDonation(Dhistory);
		
		if(result>0) {
			response.sendRedirect("/donationBoard");
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
