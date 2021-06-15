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

/**
 * Servlet implementation class ModifyServiceContentCommitServlet
 */
@WebServlet("/modifyContentCommit")
public class ModifyServiceContentCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServiceContentCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		ServiceBoard serviceBoard = new ServiceBoard();
		serviceBoard.setTitle(request.getParameter("title"));
		serviceBoard.setFlag(request.getParameter("flag"));
		serviceBoard.setVolunteer(Integer.parseInt(request.getParameter("volunteer")));
		serviceBoard.setQualfication(Integer.parseInt(request.getParameter("qualfication")));
		serviceBoard.setContent(request.getParameter("content"));
		serviceBoard.setDeadLine(Date.valueOf(request.getParameter("deadLine")));
		serviceBoard.setService_Fr(Date.valueOf(request.getParameter("service_Fr")));
		serviceBoard.setService_To(Date.valueOf(request.getParameter("service_To")));
		
		String loginId = ((Member)session.getAttribute("member")).getMemberId();
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		int result = new ServiceBoardService().modifyContent(serviceBoard, board_No);
		
		if(result > 0) {
			RequestDispatcher view = request.getRequestDispatcher("/selectOneContent?board_No="+board_No);
			request.setAttribute("loginId", loginId);
			view.forward(request, response);
		}else {
			response.sendRedirect("/views/service/ServiceBoardError.html");
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
