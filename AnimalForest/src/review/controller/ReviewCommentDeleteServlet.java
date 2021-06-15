package review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.AnimalBoardService;
import review.model.service.ReviewService;



@WebServlet("/reviewCommentDelete")
public class ReviewCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReviewCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int comment_No = Integer.parseInt(request.getParameter("comment_No"));
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		ReviewService reviewService = new ReviewService();
		int result = reviewService.ReviewCommentDelete(comment_No);
		if(result>0) {
			response.sendRedirect("/reviewSelect?board_No="+board_No);
		}else {
			response.sendRedirect("/views/review/reviewError.html");
		}
	}


		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
