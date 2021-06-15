package donation.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donation.model.service.DonationBoardService;
import donation.model.vo.DonationBoard;
import donation.model.vo.DonationFile;
import donation.model.vo.PageData;
import member.model.vo.Member;


/**
 * Servlet implementation class DonationBoardServlet
 */
@WebServlet("/donationBoard")
public class DonationBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			HttpSession session=request.getSession();
			Member mem =(Member)session.getAttribute("member");
			if(session.getAttribute("member")!=null) {
				
				char id=((Member)session.getAttribute("member")).getMemberType();
			int currentPage=0;
			if(request.getParameter("currentPage")==null) {
				currentPage=1;
			}
			else {
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
			}
			PageData pgData= new DonationBoardService().donationBoardList(currentPage,id);
			
			RequestDispatcher view = request.getRequestDispatcher("/views/donation/donation.jsp");
			request.setAttribute("member",mem);
			request.setAttribute("pageData", pgData);
		
			view.forward(request, response);
		
	}
			else {
				
				response.sendRedirect("/views/member/login.html");
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
