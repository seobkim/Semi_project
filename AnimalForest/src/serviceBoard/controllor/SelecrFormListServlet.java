package serviceBoard.controllor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.ServiceBoardAndMember;
import serviceBoard.model.vo.Service_Form;

/**
 * Servlet implementation class SelecrFormListServlet
 */
@WebServlet("/formList")
public class SelecrFormListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelecrFormListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		//ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardService().selectMemberOneByForm(board_No);
		ArrayList<ServiceBoardAndMember>formList = new ServiceBoardService().selectFormList(board_No);
		
		if(formList!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/service/formList.jsp");		
			request.setAttribute("formList", formList);
			//request.setAttribute("serviceBoardAndMember", serviceBoardAndMember);
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
