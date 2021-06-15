package serviceBoard.controllor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import serviceBoard.model.DAO.ServiceBoardDAO;
import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.ServiceBoardAndMember;
import serviceBoard.model.vo.Service_Comment;

/**
 * Servlet implementation class SelectOneServiceBoardServlet
 */
@WebServlet("/selectOneContent")
public class SelectOneServiceBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneServiceBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/*int board_No = Integer.parseInt(request.getParameter("board_No"));
		ServiceBoard serviceBoard = new ServiceBoardService().selectContentOne(board_No);
		//board_No로 찾은 하나의 객체를 serviceBoard에 저장
		ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardService().selectMemberOne(board_No);
		
		if(serviceBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/service/selectOne.jsp");
			request.setAttribute("serviceContent", serviceBoard);
			request.setAttribute("serviceBoardAndMember", serviceBoardAndMember);
			view.forward(request, response);
		}else {
			response.sendRedirect("/views/service/ServiceBoardError.html");
		}*/
		//보내주는 곳으로 list 변수 보냄
		//serviceBoard 받는데이터 
		ServiceBoard serviceBoard = null;
		HttpSession session = request.getSession();
		Member member = null;
		
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		serviceBoard = new ServiceBoardService().selectContentOne(board_No);
		int memberNum = serviceBoard.getMember_Num();
		member = new ServiceBoardService().selectMemberOnee(memberNum);
		String logInId = ((Member)session.getAttribute("member")).getMemberId();
		int logInNum = ((Member)session.getAttribute("member")).getMemberNum();
		char logInType = ((Member)session.getAttribute("member")).getMemberType();
		
		RequestDispatcher view = request.getRequestDispatcher("/views/service/selectOne.jsp");
		request.setAttribute("member", member);
		request.setAttribute("serviceBoard", serviceBoard);
		request.setAttribute("logInId", logInId );
		request.setAttribute("logInNum",logInNum);
		request.setAttribute("logInType", logInType);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
