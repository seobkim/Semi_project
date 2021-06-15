package serviceBoard.controllor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import serviceBoard.model.service.ServiceBoardService;

/**
 * Servlet implementation class InsertServiceCommentServlet
 */
@WebServlet("/insertServiceComment")
public class InsertServiceCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServiceCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		int board_No = Integer.parseInt(request.getParameter("board_No"));
		HttpSession session = request.getSession();
		int memberNum = 0;
		String memberId = null;
		
		if(session != null && (session.getAttribute("member")!=null)) {
			memberNum = ((Member)session.getAttribute("member")).getMemberNum();
			memberId = ((Member)session.getAttribute("member")).getMemberId();
		}else {
			memberId = "anonymous";
		}
		
		int result = new ServiceBoardService().insertServiceComment(content,board_No,memberNum,memberId);
		
		if(result > 0) {
			response.sendRedirect("/selectOneContent?board_No="+board_No);
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
