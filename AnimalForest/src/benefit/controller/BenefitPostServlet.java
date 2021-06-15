package benefit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.model.service.BenefitService;
import benefit.model.vo.Benefit;
import benefit.model.vo.BenefitComment;
import member.model.vo.Member;

@WebServlet("/benefitPost")
public class BenefitPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		Benefit benefit = new BenefitService().benefitPost(boardNo);
		ArrayList<BenefitComment> comment = new BenefitService().commentList(boardNo);
		
		if(benefit != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/benefit/benefitPost.jsp");
			request.setAttribute("benefit", benefit);
			request.setAttribute("comment", comment);
			view.forward(request, response);
		}else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
