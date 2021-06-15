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
import benefit.model.vo.PageDate;

@WebServlet("/searchTitle")
public class BenefitSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		String search = request.getParameter("search");
		int currentPage = 0;
		RequestDispatcher view = null;
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageDate pd = new BenefitService().searchTitle(currentPage, search, categoryNo);
		if(categoryNo==1) {
			view = request.getRequestDispatcher("/views/benefit/benefitEventSearch.jsp");
		} else if(categoryNo==2) {
			view = request.getRequestDispatcher("/views/benefit/benefitSaleSearch.jsp");
		} else if(categoryNo==3) {
			view = request.getRequestDispatcher("/views/benefit/benefitServiceSearch.jsp");
		} else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
		request.setAttribute("pageData", pd);
		request.setAttribute("search", search);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
