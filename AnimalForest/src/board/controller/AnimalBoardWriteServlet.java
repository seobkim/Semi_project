package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.AnimalBoardService;
import board.model.vo.AnimalBoard;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardWriteServlet
 */
@WebServlet("/animalBoardWriteServlet")
public class AnimalBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalBoardWriteServlet() {
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
		int size = 1024 *1024*5;
		
		String imgpath=request.getSession().getServletContext().getRealPath("img"); //저장될 위치
		
		MultipartRequest multi = new MultipartRequest(request,imgpath,size,"utf-8");
		AnimalBoard animalBoard = new AnimalBoard();
		
		animalBoard.setTitle(multi.getParameter("title"));
		animalBoard.setFindPlace(multi.getParameter("findplace"));
		animalBoard.setGrp1No(Integer.parseInt(multi.getParameter("grp1")));
		animalBoard.setFeature(multi.getParameter("feature"));
		animalBoard.setNeutralizationyn(multi.getParameter("neutralizationyn"));
		animalBoard.setContent(multi.getParameter("content"));
		animalBoard.setWeight(Integer.parseInt(multi.getParameter("weight")));
		animalBoard.setGender(multi.getParameter("gender"));
		animalBoard.setColor(multi.getParameter("color"));
		animalBoard.setBirthDate(Date.valueOf(multi.getParameter("birthdate")));	
		int grp1=Integer.parseInt(multi.getParameter("grp1"));
		if(grp1==1) {
			animalBoard.setGrp2No(Integer.parseInt(multi.getParameter("grp1-1")));
		}
		else if(grp1==2) {
			animalBoard.setGrp2No(Integer.parseInt(multi.getParameter("grp2-1")));
		}
		else {
			animalBoard.setGrp2No(1);
		}
		
		
		String imgName=multi.getFile("file").getName();
		File f = new File(imgpath+"/"+imgName);
		FileInputStream fis = new FileInputStream(f);
		HttpSession session = request.getSession();
		if (session != null && (session.getAttribute("member") != null)) {
			int MemberNum = ((Member) session.getAttribute("member")).getMemberNum();
			int result = new AnimalBoardService().AnimalBoardWrite(animalBoard,imgName,fis,f,MemberNum);
			
			if (result > 0) {
				response.sendRedirect("/boardList");
			} else {
				response.sendRedirect("/views/animalBoard/animalBoardError.html");
			}
		}
		
		
		/*AnimalBoard animalBoard = new AnimalBoard();
		animalBoard.setTitle(request.getParameter("title"));
		animalBoard.setFindPlace(request.getParameter("findplace"));
		animalBoard.setGrp1No(Integer.parseInt(request.getParameter("grp1No")));
		animalBoard.setGrp2No(Integer.parseInt(request.getParameter("grp2No")));
		animalBoard.setFeature(request.getParameter("feature"));
		animalBoard.setNeutralizationyn(request.getParameter("neutralizationyn"));
		animalBoard.setContent(request.getParameter("content"));
		animalBoard.setWeight(Integer.parseInt(request.getParameter("weight")));
		animalBoard.setGender(request.getParameter("gender"));
		animalBoard.setColor(request.getParameter("color"));
		animalBoard.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
		HttpSession session = request.getSession();
		if (session != null && (session.getAttribute("member") != null)) {
			int MemberNum = ((Member) session.getAttribute("member")).getMemberNum();
			int result = new AnimalBoardService().animalBoardWrite(animalBoard, MemberNum);
			if (result > 0) {
				response.sendRedirect("/boardList");
			} else {
				response.sendRedirect("/views/animalBoard/animalBoardError.html");
			}
		}*/

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
