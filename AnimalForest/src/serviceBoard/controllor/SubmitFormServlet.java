package serviceBoard.controllor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.ServiceBoard;

/**
 * Servlet implementation class SubmitFormServlet
 */
@WebServlet("/submitForm")
public class SubmitFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		ServiceBoard serviceBoard = new ServiceBoardService().selectContentOne(board_No);
		if(serviceBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/service/submitServiceForm.jsp");
			request.setAttribute("serviceContent", serviceBoard);
			view.forward(request, response);
		}else {
			response.sendRedirect("/views/serivce/ServiceBoardError.html");
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
