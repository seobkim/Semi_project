package benefit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.model.service.BenefitService;
import benefit.model.vo.Benefit;


@WebServlet("/benefitModify")
public class BenefitModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		Benefit benefit  = new BenefitService().modifyBenefit(boardNo);
		
			RequestDispatcher view = request.getRequestDispatcher("/views/benefit/benefitModify.jsp");
			request.setAttribute("benefit", benefit);
			view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
