package serviceBoard.controllor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceBoard.model.service.ServiceBoardService;

/**
 * Servlet implementation class DeleteServiceContentServlet
 */
@WebServlet("/deleteContent")
public class DeleteServiceContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServiceContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		int result = new ServiceBoardService().deleteServiceContent(board_No);
		
		if(result > 0) {
			response.sendRedirect("/serviceBoard");
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
