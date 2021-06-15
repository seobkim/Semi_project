package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import benefit.model.vo.Benefit;
import board.model.vo.AnimalBoard;
import donation.model.vo.DonationBoard;
import main.model.service.MainService;
import member.model.vo.Member;
import review.model.vo.Review;
import serviceBoard.model.vo.ServiceBoard;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/homePage")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AnimalBoard animalBoard = null;
		Review reviewBoard = null;
		DonationBoard donationBoard =null;
		ServiceBoard serviceBoard=null;
		Benefit benefitBoard =null;
		HttpSession session=request.getSession();
		
		
		
		animalBoard= new MainService().selectOneAnimal();
		reviewBoard = new MainService().selectOneReview();
		donationBoard= new MainService().selectOneDonation();
		serviceBoard = new MainService().selectOneService();
		benefitBoard = new MainService().selectoneBenefit();
		
		
		RequestDispatcher view = request.getRequestDispatcher("homePage.jsp");
		request.setAttribute("animal", animalBoard);
		request.setAttribute("review", reviewBoard);
		request.setAttribute("donation", donationBoard);
		request.setAttribute("service", serviceBoard);
		request.setAttribute("benefit", benefitBoard);
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
