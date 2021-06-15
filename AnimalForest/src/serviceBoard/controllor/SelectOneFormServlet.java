package serviceBoard.controllor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.ServiceBoardAndMember;
import serviceBoard.model.vo.Service_Form;

/**
 * Servlet implementation class SelectOneFormServlet
 */
@WebServlet("/selectOneForm")
public class SelectOneFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int memberNum = Integer.parseInt(request.getParameter("memberNum"));
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		//Service_Form serviceForm = new ServiceBoardService().selectOneForm(memberNum,board_No);
		ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardService().selectMemberOneByForm(board_No,memberNum);
		//ServiceBoard serviceBoard = new ServiceBoardService().selectBoardOneByForm(board_No,memberNum);
		
		if(serviceBoardAndMember != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/service/selectOneForm.jsp");
			request.setAttribute("member", serviceBoardAndMember);
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
