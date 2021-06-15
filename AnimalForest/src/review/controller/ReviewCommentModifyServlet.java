package review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.AnimalBoardService;
import review.model.service.ReviewService;



@WebServlet("/reviewCommentModify")
public class ReviewCommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReviewCommentModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String comment = request.getParameter("modComment");
		int board_No = Integer.parseInt(request.getParameter("modBoardNo"));
		int comment_No = Integer.parseInt(request.getParameter("modCommentNo"));

		int result = new ReviewService().ReviewCommentModify(comment_No, board_No, comment);
		//System.out.println(result);
		if(result > 0){
			response.sendRedirect("/reviewSelect?board_No=" + board_No);
		}else{
			response.sendRedirect("/views/review/reviewError.html");
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
