package review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import review.model.service.ReviewService;


@WebServlet("/reviewCommentWrite")
public class ReviewCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReviewCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String comment = request.getParameter("comment");
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		String MemberName = null;
		int member_Num = 0;
		
		HttpSession session = request.getSession();
		if (session != null && (session.getAttribute("member") != null)) {		
			MemberName = ((Member)session.getAttribute("member")).getMemberName();
			member_Num = ((Member)session.getAttribute("member")).getMemberNum();
			
		} else {
			//response.sendRedirect("/views/notice/serviceFailed.html"); 
			MemberName = "annonymous";
		}
		
		int result = new ReviewService().ReviewCommentWrite(comment, board_No, member_Num);
		
		if (result > 0) {
			response.sendRedirect("/reviewSelect?board_No=" + board_No);
			
		} else {
			response.sendRedirect("/views/review/reviewError.html");
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
