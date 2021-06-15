package benefit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.model.vo.Benefit;
import benefit.model.vo.BenefitFile;
import benefit.model.vo.PageDate;
import benefit.model.service.BenefitService;

@WebServlet("/benefitList")
public class BenefitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		ArrayList<Benefit> list = new BenefitService().BenefitList(categoryNo);
		RequestDispatcher view = null;
		
		int currentPage=0;
		if(request.getParameter("currentPage")==null) {
			currentPage=1;
		}
		else {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		PageDate pgData= new BenefitService().selectList(currentPage, categoryNo);
		
		request.setAttribute("list",list);
		request.setAttribute("pageData", pgData);
		
		if(!list.isEmpty()&&list.get(0).getCategoryNo()==1) {
			view = request.getRequestDispatcher("/views/benefit/benefitEventBoard.jsp");
			view.forward(request, response);
		} else if(!list.isEmpty()&&list.get(0).getCategoryNo()==2) {
			view = request.getRequestDispatcher("/views/benefit/benefitSaleBoard.jsp");
			view.forward(request, response);
		} else if(!list.isEmpty()&&list.get(0).getCategoryNo()==3) {
			view = request.getRequestDispatcher("/views/benefit/benefitServiceBoard.jsp");
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("/views/benefit/benefitBoard.jsp");
			view.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
