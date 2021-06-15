package benefit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import benefit.model.service.BenefitService;
import member.model.vo.Member;

@WebServlet("/commentWrite")
public class BenefitCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BenefitCommentWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String contents = request.getParameter("contents");
		
		String memberId = "admin"; // 나중에 로그인한 아이디를 가져와서 씀.
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int result = 0;
		/*HttpSession session = request.getSession();*/
		/*if(session != null && (session.getAttribute("memberId")!=null)) {*/
			result = new BenefitService().insertComment(contents, memberId, boardNo);
			
		/*}
		else {
			memberId = "익명";
		}*/
		if(result > 0) {
			response.sendRedirect("/benefitPost?boardNo="+boardNo);
		} else {
			response.sendRedirect("/views/benefit/benefitError.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
