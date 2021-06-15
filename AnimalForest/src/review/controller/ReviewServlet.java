package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import review.model.service.ReviewService;
import review.model.vo.PageData;
import review.model.vo.Review;




@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentPage =0;
		if(request.getParameter("currentPage") == null) {
			currentPage=1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
			PageData reviewList = new ReviewService().ReviewList(currentPage);
			RequestDispatcher view = request.getRequestDispatcher("/views/review/reviewAll.jsp");
			request.setAttribute("review", reviewList);
			view.forward(request, response);
	
		
		}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
