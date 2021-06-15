package serviceBoard.controllor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
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
import serviceBoard.model.vo.Service_File;

/**
 * Servlet implementation class insertServiceContentServlet
 */
@WebServlet("/insertContent")
public class InsertServiceContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServiceContentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * ServiceBoard serviceBoard = new ServiceBoard();
		 * serviceBoard.setTitle(request.getParameter("title"));
		 * serviceBoard.setVolunteer(Integer.parseInt(request.getParameter("volunteer"))
		 * ); serviceBoard.setQualfication(Integer.parseInt(request.getParameter(
		 * "qualfication"))); serviceBoard.setContent(request.getParameter("content"));
		 * serviceBoard.setDeadLine(Date.valueOf(request.getParameter("deadLine")));
		 * serviceBoard.setService_Fr(Date.valueOf(request.getParameter("service_Fr")));
		 * serviceBoard.setService_To(Date.valueOf(request.getParameter("service_To")));
		 * 
		 * HttpSession session = request.getSession(); if(session != null &&
		 * (session.getAttribute("member") != null)) { //int MemberNum =
		 * ((Member)session.getAttribute("member")).getMemberNum(); int result = new
		 * ServiceBoardService().insertServiceContent(serviceBoard); if (result > 0) {
		 * response.sendRedirect("/serviceBoard"); } else {
		 * response.sendRedirect("/views/service/ServiceBoardError.html"); } }
		 */
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		int size = 1024 * 1024 * 5; // file 사이즈 지정
		String imgpath = request.getSession().getServletContext().getRealPath("img"); // 저장될 위치

		MultipartRequest multi = new MultipartRequest(request, imgpath, size, "utf-8");
		ServiceBoard serviceBoard = new ServiceBoard();
		serviceBoard.setTitle(multi.getParameter("title"));
		serviceBoard.setVolunteer(Integer.parseInt(multi.getParameter("volunteer")));
		serviceBoard.setQualfication(Integer.parseInt(multi.getParameter("qualfication")));
		serviceBoard.setContent(multi.getParameter("content"));
		serviceBoard.setDeadLine(Date.valueOf(multi.getParameter("deadLine")));
		serviceBoard.setService_Fr(Date.valueOf(multi.getParameter("service_Fr")));
		serviceBoard.setService_To(Date.valueOf(multi.getParameter("service_To")));

		String imgName = multi.getFile("files").getName();
		File f = new File(imgpath + "/" + imgName);
		FileInputStream fis = new FileInputStream(f);

		if (session != null && (session.getAttribute("member") != null)) {
			int MemberNum = ((Member) session.getAttribute("member")).getMemberNum();
			int result = new ServiceBoardService().insertServiceContent(serviceBoard, imgName, fis, f, MemberNum);

			if (result > 0) {
				response.sendRedirect("/serviceBoard");
			}else {
				response.sendRedirect("/views/service/ServiceBoardError.html");
			}
		}
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
