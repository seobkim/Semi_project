package donation.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donation.model.dao.DonationBoardDao;
import donation.model.service.DonationBoardService;
import donation.model.vo.DonationBoard;
import member.model.vo.Member;

/**
 * Servlet implementation class DonationBoardUpdateServlet
 */
@WebServlet("/donationUpdate")
public class DonationBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DonationBoard Dboard = new DonationBoard();
		request.setCharacterEncoding("utf-8");
		int boardNo=Integer.parseInt(request.getParameter("board_No"));
		Dboard.setBoard_No(Integer.parseInt(request.getParameter("board_No")));
		Dboard.setTitle(request.getParameter("title"));
		Dboard.setContent(request.getParameter("content"));
		Dboard.setFull_Amount(Integer.parseInt(request.getParameter("fullAccount")));
		Dboard.setEndDate(Date.valueOf(request.getParameter("endDate")));
		Dboard.setGrp1_No(Integer.parseInt(request.getParameter("grp1")));
		
		int grp1=Integer.parseInt(request.getParameter("grp1"));
		System.out.println(grp1);
		
		
		if(grp1==1) {
			Dboard.setGrp2_No(Integer.parseInt(request.getParameter("grp1-1")));
		}
		else if(grp1==2) {
			Dboard.setGrp2_No(Integer.parseInt(request.getParameter("grp2-1")));
		}
		else {
			Dboard.setGrp2_No(1);
		}
		int result = new DonationBoardService().donationUpdate(Dboard);
		
		if(result>0) {
			response.sendRedirect("/donationSelect?board_No="+boardNo+"&type=0");
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
