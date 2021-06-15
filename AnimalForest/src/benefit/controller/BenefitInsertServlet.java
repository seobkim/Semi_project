package benefit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.oreilly.servlet.MultipartRequest;

import benefit.model.service.BenefitService;
import benefit.model.vo.Benefit;
import member.model.vo.Member;

@WebServlet("/insertBenefit")
public class BenefitInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BenefitInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * if(session != null && (session.getAttribute("member")!=null)) { String
		 * memberName = ((Member)session.getAttribute("member")).getMemberName();
		 * ServiceBoardAndMember member = new
		 * ServiceBoardService().selectMemberOne(memberName); RequestDispatcher view =
		 * request.getRequestDispatcher("/views/service/insertServiceContent.jsp");
		 * request.setAttribute("member", member); view.forward(request, response);
		 * }else { response.sendRedirect("/views/service/ServiceBoardNoLogin.html"); }
		 */
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		int size = 1024 * 1024 * 5;
		String imgpath = request.getSession().getServletContext().getRealPath("/img");

		MultipartRequest multi = new MultipartRequest(request, imgpath, size, "utf-8");
		Benefit benefit = new Benefit();
		int categoryNo = Integer.parseInt(multi.getParameter("categoryNo"));
		benefit.setTitle(multi.getParameter("title"));
		benefit.setContents(multi.getParameter("contents"));
		benefit.setEndDate(Date.valueOf(multi.getParameter("endDate")));
		// String title = request.getParameter("title");
		// String content = request.getParameter("content");
		// String endDate = request.getParameter("endDate");
		
		String imgName = multi.getFile("file").getName();
		
		File f = new File(imgpath + "/" + imgName);
		FileInputStream fis = new FileInputStream(f);

		System.out.println(f);
		System.out.println(fis);
		if (session != null) {
			int result = new BenefitService().insertBenefit(categoryNo, benefit, imgName, fis, f);
			if (result > 0) {
				response.sendRedirect("/benefitList?categoryNo=" + categoryNo);
			} else {
				
				 	response.sendRedirect("/views/benefit/benefitError.html");
				  }
		} else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
