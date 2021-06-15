package benefit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.model.service.BenefitService;

@WebServlet("/modifyComment")
public class BenefitCommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitCommentModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String comment = request.getParameter("comment");
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		int result = new BenefitService().modifyComment(boardNo, commentNo, comment);
		
		if (result > 0) {
			response.sendRedirect("/benefitPost?boardNo="+boardNo);
		} else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
