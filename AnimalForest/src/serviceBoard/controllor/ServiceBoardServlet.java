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
import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.PageData;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.ServiceBoardAndMember;

/**
 * Servlet implementation class ServiceBoardServlet
 */
@WebServlet("/serviceBoard")
public class ServiceBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		
		if( session != null && (session.getAttribute("member")!=null)) {
			int currentPage = 0;
			if(request.getParameter("currentPage") == null) {
				currentPage = 1;
			}else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			char memberType = ((Member)session.getAttribute("member")).getMemberType();
			PageData pageData = new ServiceBoardService().selectContentList1(currentPage, memberType );
			
			RequestDispatcher view = request.getRequestDispatcher("/views/service/ServiceBoardList.jsp");
			request.setAttribute("pageData", pageData);
			view.forward(request, response);
		
		}else {
			response.sendRedirect("/views/service/ServiceBoardNoLogin.html");
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
