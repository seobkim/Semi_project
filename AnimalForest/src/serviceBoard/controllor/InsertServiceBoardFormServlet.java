package serviceBoard.controllor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.ServiceBoardAndMember;

/**
 * Servlet implementation class InsertServiceBoardFormServlet
 */
@WebServlet("/insertContentForm")
public class InsertServiceBoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServiceBoardFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		/*if(session != null && (session.getAttribute("member")!=null)) {
			int member_Num = ((Member)session.getAttribute("member")).getMemberNum();
			ServiceBoardAndMember member = new ServiceBoardService().selectMemberOne(member_Num);
			RequestDispatcher view = request.getRequestDispatcher("/views/service/insertServiceContent.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		}else {
			response.sendRedirect("/views/service/ServiceBoardNoLogin.html");
		}*/
		if(session != null && (session.getAttribute("member")!=null)) {
			int member_Num = ((Member)session.getAttribute("member")).getMemberNum();
			Member member = new ServiceBoardService().selectMemberOnee(member_Num);
			RequestDispatcher view = request.getRequestDispatcher("/views/service/insertServiceContent.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		} else {
			response.sendRedirect("/views/service/ServiceBoardNoLogin.html");
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
