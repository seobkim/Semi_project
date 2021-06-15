package benefit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import benefit.model.service.BenefitService;

/**
 * Servlet implementation class BenefitModifyCommitServlet
 */
@WebServlet("/benefitModifyCommit")
public class BenefitModifyCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BenefitModifyCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String endDate = request.getParameter("endDate");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int result = new BenefitService().modifyBenefitCommit(categoryNo, title, contents, endDate, boardNo);
		if (result > 0) {
			response.sendRedirect("/benefitList?categoryNo="+categoryNo);
		}else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
