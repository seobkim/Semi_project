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
import donation.model.vo.PageData;

/**
 * Servlet implementation class DonationSelectNameServlet
 */
@WebServlet("/donationSelectName")
public class DonationSelectNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationSelectNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String val =request.getParameter("searchval");
		
		int currentPage=0;
		if(request.getParameter("currentPage")==null) {
			currentPage=1;
		}
		else {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pgData= new DonationBoardService().donationSelectName(val,currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/donation/donationSelectName.jsp");
		request.setAttribute("pageData", pgData);
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
