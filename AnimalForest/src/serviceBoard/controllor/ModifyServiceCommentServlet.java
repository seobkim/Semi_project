package serviceBoard.controllor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceBoard.model.service.ServiceBoardService;

/**
 * Servlet implementation class ModifyServiceCommentServlet
 */
@WebServlet("/modifyServiceComment")
public class ModifyServiceCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServiceCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("modContent");
		int comment_No = Integer.parseInt(request.getParameter("modCommentNo"));
		int board_No = Integer.parseInt(request.getParameter("modBoardNo"));
		int result = new ServiceBoardService().modifyComment(content, comment_No,board_No);
		
		if(result > 0) {
			response.sendRedirect("/selectOneContent?board_No=" + board_No);
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
