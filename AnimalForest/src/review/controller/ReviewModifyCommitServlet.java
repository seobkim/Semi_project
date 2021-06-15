package review.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Review;



@WebServlet("/reviewModifyCommit")
public class ReviewModifyCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReviewModifyCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");


	
		Review review = new Review();
		review.setTitle(request.getParameter("title"));
		review.setAdoptionTime(Date.valueOf(request.getParameter("adoptionTime")));
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		review.setContent(request.getParameter("content"));
		
		int result = new ReviewService().ReviewModify(review, board_No);

		if( result > 0 ) {
			response.sendRedirect("/reviewSelect?board_No="+board_No);
		} else {
			response.sendRedirect("/views/review/reviewError.html");
		}
	}
		
		
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
