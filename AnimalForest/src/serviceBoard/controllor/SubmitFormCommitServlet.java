package serviceBoard.controllor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import member.model.vo.Member;
import serviceBoard.model.service.ServiceBoardService;
import serviceBoard.model.vo.PageData;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.Service_Form;

/**
 * Servlet implementation class SubmitFormCommitServlet
 */
@WebServlet("/submitFormCommit")
public class SubmitFormCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitFormCommitServlet() {
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
		  HttpSession session =  request.getSession();
		  
		  int board_No = Integer.parseInt(request.getParameter("board_No"));
		  int memberNum = ((Member)session.getAttribute("member")).getMemberNum();
		  
		  Service_Form serviceForm = new Service_Form();
		  serviceForm.setMember_Num(memberNum);
		  serviceForm.setBoard_No(board_No);
		  serviceForm.setJob(request.getParameter("job"));
		  serviceForm.setPhone(request.getParameter("phone"));
		  serviceForm.setEmail(request.getParameter("email"));
		  serviceForm.setAddress(request.getParameter("address"));
		  serviceForm.setDetail_Address(request.getParameter("detail_address"));
		  serviceForm.setContent(request.getParameter("content"));
		  
		  int result = new
		  ServiceBoardService().submitForm(serviceForm);
		  
		  if(result > 0) {
		  response.sendRedirect("/selectOneContent?board_No="+board_No); }
		  else {
		  response.sendRedirect("/views/service/ServiceBoardError.html"); }
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
