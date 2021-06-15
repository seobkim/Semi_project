package review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Review;




@WebServlet("/reviewModify")
public class ReviewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReviewModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		//HttpSession session = request.getSession();
		//if(review != null) {

			int board_No = Integer.parseInt(request.getParameter("board_No"));
			Review review = new ReviewService().reviewSelect(board_No);

			request.setAttribute("review", review);
			RequestDispatcher view = request.getRequestDispatcher("/views/review/reviewModify.jsp");
			view.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
