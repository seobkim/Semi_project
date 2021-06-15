package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardCommentWriteServlet
 */
@WebServlet("/AnimalBoardCommentWriteServlet")
public class AnimalBoardCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String comment = request.getParameter("comment");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String MemberName = null;
		int memberNum = 0;
		
		HttpSession session = request.getSession();
		if (session != null && (session.getAttribute("member") != null)) {
			MemberName = ((Member)session.getAttribute("member")).getMemberName();
			memberNum = ((Member)session.getAttribute("member")).getMemberNum();
			
		} else {
			/*response.sendRedirect("/views/notice/serviceFailed.html"); 이건 로그인이 필요할 경우*/
			MemberName = "annonymous";
		}
		
		int result = new AnimalBoardService().AnimalBoardWriteComment(comment, boardNo, MemberName, memberNum);
		
		if (result > 0) {
			response.sendRedirect("/animalBoardDetail?boardNo=" + boardNo);
		} else {
			response.sendRedirect("/views/animalBoard/animalBoardError.html");
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
