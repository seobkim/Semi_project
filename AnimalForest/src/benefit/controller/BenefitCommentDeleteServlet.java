package benefit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.model.service.BenefitService;

@WebServlet("/deleteComment")
public class BenefitCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitCommentDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		result = new BenefitService().deleteComment(commentNo);
		if (result>0) {
			response.sendRedirect("benefitPost?boardNo="+boardNo);
		} else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
