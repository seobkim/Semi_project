package review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.File;
import review.model.vo.Review;




@WebServlet("/reviewSelect")
public class ReviewSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReviewSelectServlet() {
        super();
  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		Review review = new Review();
		
	
		review = new ReviewService().reviewSelect(board_No);
		if(review !=null) {
			RequestDispatcher view =request.getRequestDispatcher("/views/review/reviewContent.jsp");
			request.setAttribute("review", review);

			
			view.forward(request, response);
		}
		else {
			response.sendRedirect("/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
