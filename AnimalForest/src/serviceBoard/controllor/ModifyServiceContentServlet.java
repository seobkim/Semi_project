package serviceBoard.controllor;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.ServiceBoardAndMember;
import serviceBoard.model.vo.Service_Comment;

/**
 * Servlet implementation class ModifyServiceContentServlet
 */
@WebServlet("/modifyContent")
public class ModifyServiceContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyServiceContentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int board_No = Integer.parseInt(request.getParameter("board_No"));
		ServiceBoard serviceBoard = new ServiceBoardService().selectContentOne(board_No);
		ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardService().selectMemberOne(board_No);
		HttpSession session = request.getSession();
		String loginId = ((Member)session.getAttribute("member")).getMemberId();
		
		if (serviceBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/service/modifyServiceContent.jsp");
			request.setAttribute("serviceContent", serviceBoard);
			request.setAttribute("serviceBoardAndMember", serviceBoardAndMember);
			request.setAttribute("loginId", loginId);
			view.forward(request, response);
		} else {
			response.sendRedirect("/views/service/ServiceBoardError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
